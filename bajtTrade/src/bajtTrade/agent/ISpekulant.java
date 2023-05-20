package bajtTrade.agent;

import java.util.ArrayList;

import bajtTrade.agent.oferta.Oferta;
import bajtTrade.zasoby.ZasobyGry;

public interface ISpekulant extends IAgent {
    void dodajOferty(ZasobyGry[] średniaCena, ZasobyGry[] występowanie,
            ArrayList<Oferta> ofertaKupna, ArrayList<Oferta> ofertaSprzedaży);

}
