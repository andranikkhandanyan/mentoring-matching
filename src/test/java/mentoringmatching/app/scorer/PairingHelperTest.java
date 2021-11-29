package mentoringmatching.app.scorer;

import mentoringmatching.app.domain.Employee;
import mentoringmatching.app.domain.EmployeeMeta_;
import mentoringmatching.app.matcher.*;
import mentoringmatching.app.matcher.predicate.OperationType;
import mentoringmatching.app.pairing.PairingSet;
import mentoringmatching.app.pairing.PairingHelper;
import mentoringmatching.app.pairing.ScoringResult;
import mentoringmatching.app.util.EmployeeUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PairingHelperTest {

    private static final List<Employee> employees = new ArrayList<>();
    private static final List<Config> CONFIGS = new ArrayList<>();
    private static List<Matcher<Employee>> matchers = new ArrayList<>();
    private static PairingHelper<Employee> pairingHelper;

    @BeforeAll
    public static void initEmployees() {
        employees.add(EmployeeUtil.GABRIELLE);
        employees.add(EmployeeUtil.ZOE);
        employees.add(EmployeeUtil.JACOB);
        employees.add(EmployeeUtil.NICOLAS);
    }

    @BeforeAll
    public static void initMatcherConfig() {
        CONFIGS.add(new Config(EmployeeMeta_.DIVISION, 0, 30, OperationType.EQUALS));
        CONFIGS.add(new Config(EmployeeMeta_.AGE, 5, 30, OperationType.LTE));
        CONFIGS.add(new Config(EmployeeMeta_.TIMEZONE_OFFSET, 0, 40, OperationType.EQUALS));
    }

    @BeforeAll
    public static void initPairing() {
        matchers = MatcherFactory.createMatchers(CONFIGS, Employee.class);
        pairingHelper = new PairingHelper<>(matchers);
    }

    @Test
    public void employeeScore() {
        Employee employee = EmployeeUtil.GABRIELLE;
        Employee employee2 = EmployeeUtil.ZOE;
        ScoringResult<Employee> result = pairingHelper.evaluateMatching(Arrays.asList(employee, employee2));

        assertEquals(30, result.getScore());

        employee = EmployeeUtil.GABRIELLE;
        employee2 = EmployeeUtil.JACOB;
        result = pairingHelper.evaluateMatching(Arrays.asList(employee, employee2));

        assertEquals(100, result.getScore());
    }

    @Test
    public void givenFourEmployees_constructPairing_assertPairingCountFour() {
        List<PairingSet<Employee>> pairingSets = pairingHelper.combinations(new HashSet<>(employees));
        for (PairingSet<Employee> result : pairingSets)
        {
            System.out.println(result);
        }

        assertEquals(3, pairingSets.size());
    }

    @Test
    public void givenSixEmployees_constructPairing_assertPairingCount() {
        List<Employee> employeesList = new ArrayList<>(employees);
        employeesList.add(EmployeeUtil.JASON);
        employeesList.add(EmployeeUtil.SALLY);
        List<PairingSet<Employee>> pairingSets = pairingHelper.combinations(new HashSet<>(employeesList));
        for (PairingSet<Employee> result : pairingSets)
        {
            System.out.println(result);
        }

        assertEquals(15, pairingSets.size());
    }
}
