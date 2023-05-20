package bajtTrade.agent.strategiaNauki;

import bajtTrade.zasoby.ZasobyGry;

public interface IStrategia {
    boolean czyNauka(ZasobyGry[] średniaCena, int diamenty, int dzień);
}
