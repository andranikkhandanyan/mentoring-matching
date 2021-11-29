package mentoringmatching.app.matcher.predicate;

import mentoringmatching.app.matcher.Config;
import mentoringmatching.app.service.exception.AppIllegalArgumentException;

public class PredicateFactory {
    
    public static Predicate<?> getPredicate(Class<?> fieldType, Config config) {
        if (Number.class.isAssignableFrom(fieldType)) {
            return PredicateFactory.getNumericPredicate(config.getOperationType(), config.getDiff());
        } else {
            return PredicateFactory.getPredicate(config.getOperationType());

        }
    }

    private static Predicate<?> getPredicate(OperationType operationType) {
        switch (operationType) {
            case EQUALS:
                return new EqualsPredicate<>();
            default:
                throw new AppIllegalArgumentException("Not supported operation");
        }
    }
    
    private static Predicate<? extends Number> getNumericPredicate(OperationType operationType,
                                                                      int diff) {
        Predicate<? extends Number> rv;
        switch (operationType) {
            case EQUALS:
                return new EqualsPredicate<>();
            case GTE:
                rv = new GtePredicate<>(diff);
                break;
            case GT:
                rv = new GtPredicate<>(diff);
                break;
            case LTE:
                rv = new LtePredicate<>(diff);
                break;
            case LT:
                rv = new LtPredicate<>(diff);
                break;
            default:
                throw new AppIllegalArgumentException("Not supported numeric operation");
        }
        
        return rv;
    }
}
