package mentoringmatching.app.matcher.predicate;

import mentoringmatching.app.matcher.predicate.EqualsPredicate;
import mentoringmatching.app.matcher.predicate.Predicate;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EqualsPredicateTest {

    @Test
    public void givenEqualIntegerList_evaluateTest_assertTrue() {
        List<Integer> values = Arrays.asList(1, 1, 1);

        Predicate<Integer> predicate = new EqualsPredicate<>();

        assertTrue(predicate.test(values));
    }
}
