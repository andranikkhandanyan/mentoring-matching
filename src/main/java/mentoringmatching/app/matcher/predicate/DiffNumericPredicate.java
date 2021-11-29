package mentoringmatching.app.matcher.predicate;

import java.util.List;

abstract class DiffNumericPredicate<T extends Number> extends NumericPredicate<T>{
    final int diff;

    protected DiffNumericPredicate(int diff) {
        this.diff = diff;
    }

    @Override
    public boolean test(List<? extends T> operands) {
        for (int i = 0; i < operands.size() - 1; i++) {
            for (int j = i + 1; j < operands.size(); j++) {
                if (!test(operands.get(i), operands.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public abstract boolean test(T o1, T o2);
}
