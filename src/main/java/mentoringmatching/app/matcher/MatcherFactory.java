package mentoringmatching.app.matcher;

import mentoringmatching.app.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MatcherFactory {

    private static final Logger log = LoggerFactory.getLogger(MatcherFactory.class);

    public static <T> List<Matcher<T>> createMatchers(List<Config> configs, Class<T> clazz) {
        List<Matcher<T>> rv = new ArrayList<>();

        for (Config config : configs) {
            rv.add(createMatcher(config, clazz));
        }

        log.debug("Created {} matchers", rv.size());

        return rv;
    }

    public static <T> Matcher<T> createMatcher(Config config, Class<T> clazz) {
        try {
            return new FieldPredicateMatcher<>(config, clazz);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException();
        }
    }
}
