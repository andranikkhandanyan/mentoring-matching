package mentoringmatching.app.matcher.predicate;

import java.util.List;

public abstract class Predicate<T> {

    public abstract boolean test(List<? extends T> operands);
}
