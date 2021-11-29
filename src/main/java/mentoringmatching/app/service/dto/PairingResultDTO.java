package mentoringmatching.app.service.dto;

import mentoringmatching.app.pairing.PairingSet;

import java.io.Serializable;
import java.util.List;

public class PairingResultDTO<T> implements Serializable {

    private PairingSet<T> topPairing;

    private List<PairingSet<T>> pairingSets;

    public PairingSet<T> getTopPairing() {
        return topPairing;
    }

    public void setTopPairing(PairingSet<T> topPairing) {
        this.topPairing = topPairing;
    }

    public List<PairingSet<T>> getPairingSets() {
        return pairingSets;
    }

    public void setPairingSets(List<PairingSet<T>> pairingSets) {
        this.pairingSets = pairingSets;
    }

    @Override
    public String toString() {
        return "PairingResultDTO{" +
                "topPairing=" + topPairing +
                ", pairingSets=" + pairingSets +
                '}';
    }
}
