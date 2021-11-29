package mentoringmatching.app.domain;

import java.util.Arrays;
import java.util.List;

public class EmployeeMeta_ {
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String DIVISION = "division";
    public static final String AGE = "age";
    public static final String TIMEZONE_OFFSET = "timezoneOffset";

    public static List<String> getFields() {
        return Arrays.asList(NAME, EMAIL, DIVISION, AGE, TIMEZONE_OFFSET);
    }
}
