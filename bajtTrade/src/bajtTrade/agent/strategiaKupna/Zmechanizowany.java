package bajtTrade.agent.strategiaKupna;

import java.util.ArrayList;

import bajtTrade.agent.Robotnik;
import bajtTrade.agent.oferta.Oferta;
import bajtTrade.agent.oferta.OfertaRobotnik;

public class Zmechanizowany extends Czyścioszek {
    protected int liczba_narzedzi;

    public Zmechanizowany(int x) {
        this.liczba_narzedzi = x;
    }

    public void dodajOfertaNarzędzia(Robotnik robotnik, ArrayList<Oferta> ofertyKupna) {
        Oferta ofertakupna = new OfertaRobotnik(liczba_narzedzi, 2, robotnik);
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
        return "Zmechanizowany\n";
    }
}
