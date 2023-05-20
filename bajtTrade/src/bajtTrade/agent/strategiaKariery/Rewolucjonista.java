package bajtTrade.agent.strategiaKariery;

import bajtTrade.Fabryka;
import bajtTrade.zasoby.ZasobyGry;

public class Rewolucjonista extends Kariera {
    public Rewolucjonista(int poziom, int zawód, int id, ZasobyGry[] występowanie) {
        super(poziom, zawód, id, występowanie);
    }

    public boolean czyZmiana(int dzień) {
        return dzień % 7 == 0;
    }

    public int zmianaNaCo() {
        int n = Math.max(1, id % 17);
        int ret = 0;
        int obecnyDzień = występowanie.length;
        double maxWartość = 0;
        double obWartość = 0;

        //
        for (int j = obecnyDzień - 1; j > obecnyDzień - n && j >= 0; j--) {
            obWartość += występowanie[j].jedzenieIlość();
        }
        if (obWartość > maxWartość) {
            maxWartość = obWartość;
            ret = 0;
        }
        obWartość = 0;

        return ret;
    }

    @Override
    public String toString() {
        return "Rewolucjonista " + Fabryka.podajZawód(obecnyZawód, poziomZawodu[obecnyZawód]);
    }

}
