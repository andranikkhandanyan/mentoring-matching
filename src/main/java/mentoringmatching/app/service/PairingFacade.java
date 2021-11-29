package mentoringmatching.app.service;

import akhandanyan.csv.CsvParser;
import mentoringmatching.app.matcher.Config;
import mentoringmatching.app.domain.Employee;
import mentoringmatching.app.matcher.Matcher;
import mentoringmatching.app.matcher.MatcherFactory;
import mentoringmatching.app.pairing.PairingSet;
import mentoringmatching.app.pairing.PairingHelper;
import mentoringmatching.app.service.dto.FileMeta;
import mentoringmatching.app.service.dto.PairingResultDTO;
import mentoringmatching.app.service.exception.AppIllegalArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import static mentoringmatching.app.util.FileUtil.MIME_TYPE_CSV;

@Service
public class PairingFacade {
    private final Logger log = LoggerFactory.getLogger(PairingFacade.class);

    private final CsvParser csvParser;

    public PairingFacade(CsvParser csvParser) {
        this.csvParser = csvParser;
    }

    public PairingResultDTO<Employee> evaluatePairingsFromFile(FileMeta fileMeta, List<Config> configs) throws IOException {
        if (!MIME_TYPE_CSV.equals(fileMeta.getContentType())) {
            throw new AppIllegalArgumentException("Only csv files allowed");
        }
        log.debug("CSV parsing started");

        List<Employee> employees;
        try {
            employees = csvParser.parse(fileMeta.getInputStream(), Employee.class);
        } catch (RuntimeException e) {
            if (e.getCause() instanceof NoSuchMethodException) {
                throw new AppIllegalArgumentException("Invalid field name" + e.getCause().getMessage());
            } else {
                throw e;
            }
        }

        log.debug("CSV parsing completed with {} elements", employees.size());

        return evaluatePairings(employees, configs);
    }

    public PairingResultDTO<Employee> evaluatePairings(List<Employee> employees, List<Config> configs) {
        List<Matcher<Employee>> matchers = MatcherFactory.createMatchers(configs, Employee.class);

        PairingHelper<Employee> pairingHelper = new PairingHelper<>(matchers);
        List<PairingSet<Employee>> result = pairingHelper.combinations(new HashSet<>(employees));
        PairingResultDTO<Employee> rv = new PairingResultDTO<>();
        PairingSet<Employee> topPairing = result.stream()
                .max(Comparator.comparing(PairingSet::getAverage)).orElse(null);
        rv.setTopPairing(topPairing);
        rv.setPairingSets(result);
        log.debug("Pairing and matching completed, top match {}", topPairing);
        return rv;
    }

}
