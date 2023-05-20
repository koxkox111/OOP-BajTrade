package bajtTrade.agent.strategiaKupna;

import java.util.ArrayList;

import bajtTrade.agent.Robotnik;
import bajtTrade.agent.oferta.Oferta;
import bajtTrade.agent.oferta.OfertaRobotnik;

public class Gadżeciarz extends Zmechanizowany {

    public Gadżeciarz(int x) {
        super(x);
    }

    public void dodajOfertaProgram(Robotnik robotnik, ArrayList<Oferta> ofertyKupna) {
        int coProdukuje = robotnik.produkcja().coProdukuje(robotnik);
        int ileProdukuje = robotnik.ileWyprodkuje(coProdukuje);
        Oferta ofertakupna = new OfertaRobotnik(ileProdukuje, 4, robotnik);
        ofertyKupna.add(ofertakupna);
    }

    @Override
    public void dodajOferty(Robotnik robotnik, ArrayList<Oferta> ofertyKupna) {
        dodajOferteJedzenia(robotnik, ofertyKupna);
        dodajOferteUbrania(robotnik, ofertyKupna);
        dodajOfertaNarzędzia(robotnik, ofertyKupna);
    }

    @Override
    public String toString() {
        return "Gadżeciarz\n";
    }
}
