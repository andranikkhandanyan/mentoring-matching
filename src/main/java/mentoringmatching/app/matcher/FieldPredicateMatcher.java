package mentoringmatching.app.matcher;

import mentoringmatching.app.matcher.predicate.Predicate;
import mentoringmatching.app.matcher.predicate.PredicateFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

class FieldPredicateMatcher<T> extends FixedScoreMatcher<T>{
    Field field;
    Predicate<?> predicate;

    public FieldPredicateMatcher(Config config, Class<T> clazz) throws NoSuchFieldException {
        super(config);
        field = clazz.getDeclaredField(config.getFieldName());

        predicate = PredicateFactory.getPredicate(field.getType(), config);
    }

    @Override
    public int match(List<T> elements) {
        List operands = elements.stream().map(o -> {
            try {
                field.setAccessible(true);
                return field.get(o);
            } catch (IllegalAccessException e) {
                throw new RuntimeException();
            }
        }).collect(Collectors.toList());
        return predicate.test(operands) ? getScore() : 0;
    }
}
