package mentoringmatching.app.matcher;

import java.util.List;

class EmptyMatcher<T> implements Matcher<T>{
    @Override
    public int match(List<T> elements) {
        return 0;
    }
}
