package bajtTrade.agent.strategiaProdukcji;

import bajtTrade.zasoby.ZasobyGry;

public abstract class Produkcja implements IProdukcja {
    protected ZasobyGry[] średniaCena;

    public Produkcja(ZasobyGry[] średniaCena) {
        this.średniaCena = średniaCena;
    }

}
