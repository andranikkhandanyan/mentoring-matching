package mentoringmatching.app.matcher.predicate;

import mentoringmatching.app.matcher.predicate.LtePredicate;
import mentoringmatching.app.matcher.predicate.Predicate;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LtePredicateTest {

    @Test
    public void givenIntegerList_evaluateTest_assertTrue() {
        List<Integer> values = Arrays.asList(1, 6, 3);

        Predicate<Integer> predicate = new LtePredicate<>(5);

        assertTrue(predicate.test(values));
    }

    @Test
    public void givenIntegerList_evaluateTest_assertFalse() {
        List<Integer> values = Arrays.asList(1, 5, 8);

        Predicate<Integer> predicate = new LtePredicate<>(5);

        assertFalse(predicate.test(values));
    }
}
