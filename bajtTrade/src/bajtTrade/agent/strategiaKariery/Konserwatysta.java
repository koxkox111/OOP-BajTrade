package bajtTrade.agent.strategiaKariery;

import bajtTrade.Fabryka;
import bajtTrade.zasoby.ZasobyGry;

public class Konserwatysta extends Kariera {
    public Konserwatysta(int poziom, int zawód, int id, ZasobyGry[] występowanie) {
        super(poziom, zawód, id, występowanie);
    }

    public boolean czyZmiana(int dzień) {
        return false;
    }

    @Override
    public int zmianaNaCo() {
        return -1;
    }

    @Override
    public String toString() {
        return "Konserwatysta " + Fabryka.podajZawód(obecnyZawód, poziomZawodu[obecnyZawód]);
    }
}
