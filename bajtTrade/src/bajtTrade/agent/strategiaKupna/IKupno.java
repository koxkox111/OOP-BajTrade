package bajtTrade.agent.strategiaKupna;

import java.util.ArrayList;

import bajtTrade.agent.Robotnik;
import bajtTrade.agent.oferta.Oferta;

public interface IKupno {
    void dodajOferty(Robotnik robotnik, ArrayList<Oferta> ofertyKupna);
}
