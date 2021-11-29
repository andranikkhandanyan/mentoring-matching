package mentoringmatching.app.matcher;

import java.util.List;

public interface Matcher<T> {
    /**
     * Match elements and return score
     * @return score
     */
    int match(List<T> elements);
}
