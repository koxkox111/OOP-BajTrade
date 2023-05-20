package bajtTrade.agent.strategiaKupna;

import java.util.ArrayList;

import bajtTrade.agent.Robotnik;
import bajtTrade.agent.oferta.Oferta;
import bajtTrade.agent.oferta.OfertaRobotnik;

public class Technofob implements IKupno {

    public void dodajOferty(Robotnik robotnik, ArrayList<Oferta> ofertyKupna) {
        dodajOferteJedzenia(robotnik, ofertyKupna);
    }

    public void dodajOferteJedzenia(Robotnik robotnik, ArrayList<Oferta> ofertyKupna) {
        Oferta oferta = new OfertaRobotnik(100, 0, robotnik);
        ofertyKupna.add(oferta);
    }

    @Override
    public String toString() {
        return "Technofob\n";
    }
}
