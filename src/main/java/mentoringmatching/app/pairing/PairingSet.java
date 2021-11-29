package mentoringmatching.app.pairing;

import java.util.*;

/**
 * Holds pairs of {@link ScoringResult<T>}, with average score of included pairs
 */
public class PairingSet<T> {
    private final Set<ScoringResult<T>> scoringResults = new HashSet<>();
    private double average;
    private double sum;

    public PairingSet() {
    }

    public PairingSet(Collection<ScoringResult<T>> scoringResults) {
        addAll(scoringResults);
    }

    public double getAverage() {
        return this.average;
    }

    public void addAll(Collection<ScoringResult<T>> scoringResults) {
        for (ScoringResult<T> scoringResult : scoringResults) {
            add(scoringResult);
        }
    }

    /**
     * Add an instance of {@link ScoringResult<T>} and update average
     */
    public void add(ScoringResult<T> scoringResult) {
        scoringResults.add(scoringResult);

        sum += scoringResult.getScore();
        average = sum / scoringResults.size();
    }

    public Set<ScoringResult<T>> getScoringResults() {
        return new HashSet<>(scoringResults);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PairingSet<?> that = (PairingSet<?>) o;
        return Objects.equals(scoringResults, that.scoringResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scoringResults);
    }

    @Override
    public String toString() {
        return "PairingSet{" +
                "scoringResults=" + scoringResults +
                ", average=" + average +
                ", sum=" + sum +
                '}';
    }
}
