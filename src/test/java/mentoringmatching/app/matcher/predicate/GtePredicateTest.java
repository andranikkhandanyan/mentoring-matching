package mentoringmatching.app.matcher.predicate;

import mentoringmatching.app.matcher.predicate.GtePredicate;
import mentoringmatching.app.matcher.predicate.Predicate;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GtePredicateTest {

    @Test
    public void givenIntegerList_evaluateTest_assertTrue() {
        List<Integer> values = Arrays.asList(1, 6, 13);

        Predicate<Integer> predicate = new GtePredicate<>(5);

        assertTrue(predicate.test(values));
    }

    @Test
    public void givenIntegerList_evaluateTest_assertFalse() {
        List<Integer> values = Arrays.asList(1, 5, 13);

        Predicate<Integer> predicate = new GtePredicate<>(5);

        assertFalse(predicate.test(values));
    }
}
