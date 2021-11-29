package mentoringmatching.app.pairing;

import mentoringmatching.app.matcher.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class PairingHelper<T> {
    private final Logger log = LoggerFactory.getLogger(PairingHelper.class);

    private final List<Matcher<T>> matchers = new ArrayList<>();
    private Map<List<T>, ScoringResult<T>> evaluations = new HashMap<>();

    public PairingHelper(List<Matcher<T>> matchers) {
        this.matchers.addAll(matchers);
    }

    public synchronized List<PairingSet<T>> combinations(Set<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            return new ArrayList<>();
        }
        evaluations = new HashMap<>();
        List<PairingSet<T>> rv = new ArrayList<>();

        log.debug("Start pairing");

        combinations(data, new ArrayList<>(), new ArrayList<>(), rv, 2);

        // In case of odd number of data add combinations without the first
        if (data.size() % 2 != 0) {
            List<T> d = new ArrayList<>(data);
            // Remove the first element and constructs pairs without it
            d.remove(0);
            combinations(new LinkedHashSet<>(d), new ArrayList<>(), new ArrayList<>(), rv, 2);
        }

        log.debug("Pairing completed with possible {} combinations", rv.size());

        return rv;
    }

    /**
     * @param data Remaining elements to be paired (updated with each call)
     * @param currentPairs Pair(group) of elements not included in <code>currentResults</code>
     * @param currentResults Computed set of pairs(groups) not included in final <code>results</code>
     * @param results Current set of computed pairs(groups)
     * @param r Count of peers to be included in pair (group).
     */
    private synchronized void combinations(Set<T> data,
                                           List<T> currentPairs,
                                           List<ScoringResult<T>> currentResults,
                                           List<PairingSet<T>> results,
                                           int r)
    {
        // Last remaining set of elements doesn't need be paired as it is already paired
        if (data.size() == r)
        {
            List<T> lastChunk = new ArrayList<>(data);
            // Compute and create ScoringResult
            ScoringResult<T> lastScoringResult = new ScoringResult<>(evaluateMatching(lastChunk).getScore(), lastChunk);
            currentResults.add(lastScoringResult);

            // Add to results list
            results.add(new PairingSet<>(currentResults));
            // Remove current result so next iteration will compute pairs without it
            currentResults.remove(lastScoringResult);
            return;
        }

        // Not enough elements to be paired with those, fix current result ser
        // This will happen if total number of peers is not dividable to r
        if (data.size() < r) {
            results.add(new PairingSet<>(currentResults));
            return;
        }

        List<T> list = new ArrayList<>(data);
        // Poll the first element and compute combinations with it
        T first = list.remove(0);
        currentPairs.add(first);
        for (int i = 0; i < list.size(); i++)
        {
            T next = list.get(i);
            // nextSet represents elements for next calculations (ignore previously paired elements)
            Set<T> nextSet = new LinkedHashSet<>(list);
            currentPairs.add(next);

            // Check if this element can be paired(grouped) with previously polled and not grouped peers
            // This check will be executed always if r = 2 (else statement will be ignored)
            if (currentPairs.size() == r) {
                // Remove the element from next
                nextSet.remove(next);

                // Store current combination
                List<T> elements = new ArrayList<>(currentPairs);
                ScoringResult<T> pair = new ScoringResult<>(evaluateMatching(elements).getScore(), elements);
                currentResults.add(pair);

                // Continue computing remaining pairs
                combinations(nextSet, new ArrayList<>(), currentResults, results, r);
                // Remove current element and pair, so new pair can be generated on next iteration
                currentResults.remove(pair);
                currentPairs.remove(next);
            } else {
                // FIXME currently I have a bug here
                // If r > 2, continue, with this element
                combinations(nextSet, currentPairs, currentResults, results, r);
                currentPairs.remove(next);
            }
        }
    }

    public ScoringResult<T> evaluateMatching(List<T> elements) {
        if (evaluations.containsKey(elements)) {
            return evaluations.get(elements);
        }

        int score = 0;
        for (Matcher<T> matcher : matchers) {
            score += matcher.match(elements);
        }

        ScoringResult<T> rv = new ScoringResult<>(score, elements);
        evaluations.put(elements, rv);

        return rv;
    }
}
