package mentoringmatching.app.matcher.predicate;

class GtPredicate<T extends Number> extends DiffNumericPredicate<T>{
    public GtPredicate(int diff) {
        super(diff);
    }

    @Override
    public boolean test(T o1, T o2) {
        return Math.abs(o1.doubleValue() - o2.doubleValue()) > diff;
    }
}
