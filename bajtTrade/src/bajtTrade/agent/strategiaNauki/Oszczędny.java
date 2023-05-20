package bajtTrade.agent.strategiaNauki;

import bajtTrade.zasoby.ZasobyGry;

public class Oszczędny implements IStrategia {
    private int limit_diamentów;

    public Oszczędny(int limit_diamentów) {
        this.limit_diamentów = limit_diamentów;
    }

    @Override
    public boolean czyNauka(ZasobyGry[] średniaCena, int diamenty, int dzień) {
        return diamenty > limit_diamentów;
    }

    @Override
    public String toString() {
        return "Oszczędny\n";
    }

}
