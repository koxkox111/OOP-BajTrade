package bajtTrade.agent.strategiaNauki;

import bajtTrade.zasoby.ZasobyGry;

public class Okresowy implements IStrategia {
    private int okresowosc_nauki;

    public Okresowy(int okresowosc_nauki) {
        this.okresowosc_nauki = okresowosc_nauki;
    }

    @Override
    public boolean czyNauka(ZasobyGry[] średniaCena, int diamenty, int dzień) {
        return dzień % okresowosc_nauki == 0;
    }

    @Override
    public String toString() {
        return "Okresowy\n";
    }

}
