package bajtTrade.agent.strategiaKupna;

import java.util.ArrayList;

import bajtTrade.agent.Robotnik;
import bajtTrade.agent.oferta.Oferta;
import bajtTrade.agent.oferta.OfertaRobotnik;

public class Czyścioszek extends Technofob {

    public void dodajOferteUbrania(Robotnik robotnik, ArrayList<Oferta> ofertyKupna) {
        int ile = robotnik.getZasoby().ubraniaIle();
        int oferta = 0;
        if (ile < 100) {
            oferta = 100 - ile;
        } else if (ile < 200) {
            oferta = 200 - ile;
        } else {
            return;
        }
        Oferta ofertakupna = new OfertaRobotnik(oferta, 1, robotnik);
        ofertyKupna.add(ofertakupna);
    }

    @Override
    public void dodajOferty(Robotnik robotnik, ArrayList<Oferta> ofertyKupna) {
        dodajOferteJedzenia(robotnik, ofertyKupna);
        dodajOferteUbrania(robotnik, ofertyKupna);
    }

    @Override
    public String toString() {
        return "Czyścioszek\n";
    }
}
