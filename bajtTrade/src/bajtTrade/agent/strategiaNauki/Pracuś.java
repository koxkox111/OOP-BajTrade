package bajtTrade.agent.strategiaNauki;

import bajtTrade.zasoby.ZasobyGry;

public class Pracuś implements IStrategia {
    @Override
    public boolean czyNauka(ZasobyGry[] średniaCena, int diamenty, int dzień) {
        return false;
    }

    @Override
    public String toString() {
        return "Pracuś\n";
    }
}
