package mentoringmatching.app.matcher;

import mentoringmatching.app.domain.Division;
import mentoringmatching.app.domain.Employee;
import mentoringmatching.app.domain.EmployeeMeta_;
import mentoringmatching.app.matcher.Config;
import mentoringmatching.app.matcher.Matcher;
import mentoringmatching.app.matcher.MatcherFactory;
import mentoringmatching.app.matcher.predicate.OperationType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MatcherTest {
    private static final List<Config> CONFIGS = new ArrayList<>();

    @BeforeAll
    public static void initMatcherConfig() {
        CONFIGS.add(new Config(EmployeeMeta_.DIVISION, 0, 30, OperationType.EQUALS));
        CONFIGS.add(new Config(EmployeeMeta_.AGE, 5, 30, OperationType.LTE));
        CONFIGS.add(new Config(EmployeeMeta_.TIMEZONE_OFFSET, 0, 40, OperationType.EQUALS));
    }

    @Test
    public void givenDivisionMatcher_evaluateMatching_assertEquals() {
        Matcher<Employee> matcher = MatcherFactory
                .createMatcher(new Config(EmployeeMeta_.DIVISION, 0, 30, OperationType.EQUALS), Employee.class);

        Employee employee = new Employee("Gabrielle Clarkson","tamas@me_example.com", Division.Accounting, 25, 2);
        Employee employee2 = new Employee("Zoe Peters","gozer@icloud_example.com", Division.Finance, 30, 3);

        assertEquals(0, matcher.match(Arrays.asList(employee, employee2)));
        assertEquals(0, matcher.match(Arrays.asList(employee2, employee)));
    }

    @Test
    public void givenDivisionMatcher_evaluateMatching_assertEquals30() {
        Matcher<Employee> matcher = MatcherFactory
                .createMatcher(new Config(EmployeeMeta_.DIVISION, 0, 30, OperationType.EQUALS), Employee.class);

        Employee employee = new Employee("Gabrielle Clarkson","tamas@me_example.com", Division.Accounting, 25, 2);
        Employee employee2 = new Employee("Zoe Peters","gozer@icloud_example.com", Division.Accounting, 30, 3);

        assertEquals(30, matcher.match(Arrays.asList(employee, employee2)));
        assertEquals(30, matcher.match(Arrays.asList(employee2, employee)));
    }

    @Test
    public void givenAgeMatcher_evaluateMatching_assertEquals0() {
        Matcher<Employee> matcher = MatcherFactory
                .createMatcher(new Config(EmployeeMeta_.AGE, 5, 30, OperationType.LTE), Employee.class);

        Employee employee = new Employee("Gabrielle Clarkson","tamas@me_example.com", Division.Accounting, 25, 2);
        Employee employee2 = new Employee("Zoe Peters","gozer@icloud_example.com", Division.Finance, 31, 3);

        assertEquals(0, matcher.match(Arrays.asList(employee, employee2)));
        assertEquals(0, matcher.match(Arrays.asList(employee2, employee)));
    }

    @Test
    public void givenAgeMatcher_evaluateMatching_assertEquals30() {
        Matcher<Employee> matcher = MatcherFactory
                .createMatcher(new Config(EmployeeMeta_.AGE, 5, 30, OperationType.LTE), Employee.class);

        Employee employee = new Employee("Gabrielle Clarkson","tamas@me_example.com", Division.Accounting, 25, 2);
        Employee employee2 = new Employee("Zoe Peters","gozer@icloud_example.com", Division.Accounting, 30, 3);

        assertEquals(30, matcher.match(Arrays.asList(employee, employee2)));
        assertEquals(30, matcher.match(Arrays.asList(employee2, employee)));
    }

    @Test
    public void givenAgeMatcher_calculateTimezoneMatching_assertNonEquals() {
        Matcher<Employee> matcher = MatcherFactory
                .createMatcher(new Config(EmployeeMeta_.TIMEZONE_OFFSET, 0, 40, OperationType.EQUALS), Employee.class);

        Employee employee = new Employee("Gabrielle Clarkson","tamas@me_example.com", Division.Accounting, 25, 2);
        Employee employee2 = new Employee("Zoe Peters","gozer@icloud_example.com", Division.Finance, 31, 3);

        assertEquals(0, matcher.match(Arrays.asList(employee, employee2)));
        assertEquals(0, matcher.match(Arrays.asList(employee2, employee)));
    }
}
