package mentoringmatching.app.pairing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Represents a single pair, with evaluated score
 * @param <T>
 */
public class ScoringResult<T> {

    private final List<T> elements = new ArrayList<>();
    private final double score;

    public ScoringResult(double score, Collection<T> elements) {
        this.elements.addAll(elements);
        this.score = score;
    }

    public List<T> getElements() {
        return elements;
    }

    public double getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoringResult<?> that = (ScoringResult<?>) o;
        return Objects.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elements);
    }

    @Override
    public String toString() {
        return "ScoringResult{" +
                "elements=" + elements +
                ", score=" + score +
                '}';
    }
}
