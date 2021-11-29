package mentoringmatching.app.matcher.predicate;

import mentoringmatching.app.matcher.predicate.LtPredicate;
import mentoringmatching.app.matcher.predicate.Predicate;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LtPredicateTest {

    @Test
    public void givenIntegerList_evaluateTest_assertTrue() {
        List<Integer> values = Arrays.asList(1, 5, 3);

        Predicate<Integer> predicate = new LtPredicate<>(5);

        assertTrue(predicate.test(values));
    }

    @Test
    public void givenIntegerList_evaluateTest_assertFalse() {
        List<Integer> values = Arrays.asList(1, 5, 10);

        Predicate<Integer> predicate = new LtPredicate<>(5);

        assertFalse(predicate.test(values));
    }
}
