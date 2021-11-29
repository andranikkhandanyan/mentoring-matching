package mentoringmatching.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mentoringmatching.app.controller.vm.PairMatchingConfig;
import mentoringmatching.app.domain.Employee;
import mentoringmatching.app.domain.EmployeeMeta_;
import mentoringmatching.app.service.FileService;
import mentoringmatching.app.service.PairingFacade;
import mentoringmatching.app.service.dto.FileMeta;
import mentoringmatching.app.service.dto.PairingResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    private final PairingFacade pairingFacade;
    private final FileService fileService;

    public EmployeeController(PairingFacade pairingFacade, FileService fileService) {
        this.pairingFacade = pairingFacade;
        this.fileService = fileService;
    }

    @GetMapping("/fields")
    public ResponseEntity<List<String>> getEmployeeFields() {
        return ResponseEntity.ok(EmployeeMeta_.getFields());
    }

    @PostMapping(path = "/pairing", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PairingResultDTO<Employee>> uploadFile(@RequestParam MultipartFile file,
                                                                 @RequestParam(name = "config") String configModel
                                                                 ) throws IOException {
        log.debug("REST request to get pairs from csv : {}", file.getOriginalFilename());
        FileMeta fileMeta = fileService.processFile(file);

        PairMatchingConfig config = new ObjectMapper().readValue(configModel, PairMatchingConfig.class);
        PairingResultDTO<Employee> response = pairingFacade.evaluatePairingsFromFile(fileMeta,
                config.getMatcherConfigs());
        return ResponseEntity.ok(response);
    }
}
