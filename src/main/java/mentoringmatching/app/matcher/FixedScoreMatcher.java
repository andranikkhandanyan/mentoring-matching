package mentoringmatching.app.matcher;

abstract class FixedScoreMatcher<T> implements Matcher<T> {

    private final int score;

    public FixedScoreMatcher(Config config) {
        this.score = config.getScore();
    }

    protected int getScore() {
        return this.score;
    }
}
