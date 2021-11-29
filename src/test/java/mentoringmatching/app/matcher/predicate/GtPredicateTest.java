package mentoringmatching.app.matcher.predicate;

import mentoringmatching.app.matcher.predicate.GtPredicate;
import mentoringmatching.app.matcher.predicate.Predicate;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GtPredicateTest {

    @Test
    public void givenIntegerList_evaluateTest_assertTrue() {
        List<Integer> values = Arrays.asList(1, 7, 13);

        Predicate<Integer> predicate = new GtPredicate<>(5);

        assertTrue(predicate.test(values));
    }

    @Test
    public void givenIntegerList_evaluateTest_assertFalse() {
        List<Integer> values = Arrays.asList(1, 5, 13);

        Predicate<Integer> predicate = new GtPredicate<>(5);

        assertFalse(predicate.test(values));
    }
}
