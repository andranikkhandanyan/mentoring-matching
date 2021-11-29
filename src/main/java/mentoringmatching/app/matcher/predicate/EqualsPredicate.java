package mentoringmatching.app.matcher.predicate;

import java.util.Collections;
import java.util.List;

class EqualsPredicate<T> extends Predicate<T> {
    @Override
    public boolean test(List<? extends T> operands) {
        return operands.isEmpty() || Collections.frequency(operands, operands.get(0)) == operands.size();
    }
}
