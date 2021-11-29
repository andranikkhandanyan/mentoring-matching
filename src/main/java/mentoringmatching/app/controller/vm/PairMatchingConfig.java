package mentoringmatching.app.controller.vm;

import mentoringmatching.app.matcher.Config;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

public class PairMatchingConfig implements Serializable {
    public static final int DEFAULT_PEERS_COUNT = 2;

    private int peersCount = DEFAULT_PEERS_COUNT;

    @NotEmpty
    private List<Config> matcherConfigs;

    public int getPeersCount() {
        return peersCount;
    }

    public void setPeersCount(int peersCount) {
        this.peersCount = peersCount;
    }

    public List<Config> getMatcherConfigs() {
        return matcherConfigs;
    }

    public void setMatcherConfigs(List<Config> matcherConfigs) {
        this.matcherConfigs = matcherConfigs;
    }

    @Override
    public String toString() {
        return "PairMatchingConfig{" +
                "peersCount=" + peersCount +
                ", matcherConfigs=" + matcherConfigs +
                '}';
    }
}
