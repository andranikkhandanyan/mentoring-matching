package mentoringmatching.app.util;

import mentoringmatching.app.domain.Division;
import mentoringmatching.app.domain.Employee;

import java.util.Arrays;
import java.util.List;

public final class EmployeeUtil {

    public static Employee GABRIELLE = new Employee("Gabrielle Clarkson","tamas@me_example.com",Division.Accounting, 25, 2);
    public static Employee ZOE = new Employee("Zoe Peters","gozer@icloud_example.com", Division.Finance, 30, 3);
    public static Employee JACOB = new Employee("Jacob Murray","lstein@me_example.com", Division.Accounting, 22, 2);
    public static Employee NICOLAS = new Employee("Nicholas Vance","saridder@outlook_example.com", Division.Engineering, 27, -3);
    public static Employee JASON = new Employee("Jason Hamilton","osaru@live_example.com", Division.HR, 35, 4);
    public static Employee SALLY = new Employee("Sally Bower","bulletin@att_example.com", Division.Engineering, 20, 10);
    public static Employee MAX = new Employee("Max Baker","kodeman@gmail_example.com", Division.Engineering, 32, 0);
    public static Employee OLIVIA = new Employee("Olivia Ogden","gator@outlook_example.com", Division.Finance, 32, -4);
    public static Employee ADRIAN = new Employee("Adrian Ince","delpino@comcast_example.com", Division.Finance, 28, -5);
    public static Employee WILLIAM = new Employee("William Clarkson","goldberg@hotmail_example.com", Division.Accounting, 29, 3);

    public static List<Employee> ALL_EMPLOYEES = Arrays.asList(GABRIELLE, ZOE, JACOB, NICOLAS,
            JASON, SALLY, MAX, OLIVIA, ADRIAN, WILLIAM);

    private EmployeeUtil() {

    }
}
