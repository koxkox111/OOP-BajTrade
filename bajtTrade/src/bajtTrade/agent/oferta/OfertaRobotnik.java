package bajtTrade.agent.oferta;

import bajtTrade.agent.Robotnik;

public class OfertaRobotnik extends Oferta {

    public OfertaRobotnik(int ilość, int produkt, Robotnik robotnik) {
        super(ilość, produkt, robotnik);
    }

    public OfertaRobotnik(int ilość, int produkt, int poziom, Robotnik robotnik) {
        super(ilość, produkt, poziom, robotnik);
    }

}
