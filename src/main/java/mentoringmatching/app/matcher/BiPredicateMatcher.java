package mentoringmatching.app.matcher;

import java.util.List;
import java.util.function.BiPredicate;

abstract class BiPredicateMatcher<T> extends FixedScoreMatcher<T> {

    private final BiPredicate<T, T> predicate;

    public BiPredicateMatcher(Config config) {
        super(config);
        predicate = createPredicate(config);
    }

    protected abstract BiPredicate<T, T> createPredicate(Config config);

    @Override
    public int match(List<T> elements) {
        if (elements.size() != 2) {
            throw new IllegalArgumentException("Only 2 elements must be matched with BiPredicateMatcher");
        }
        return predicate.test(elements.get(0), elements.get(1)) ? getScore() : 0;
    }
}
