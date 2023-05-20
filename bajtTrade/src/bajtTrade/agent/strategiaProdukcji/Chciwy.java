package bajtTrade.agent.strategiaProdukcji;

import bajtTrade.agent.Robotnik;
import bajtTrade.zasoby.ZasobyGry;

public class Chciwy extends Produkcja {
    public Chciwy(ZasobyGry[] średniaCena) {
        super(średniaCena);
    }

    @Override
    public int coProdukuje(Robotnik robotnik) {
        int obecnyDzień = średniaCena.length;
        double maxWartość = 0;
        int ret = 0;
        // 0 - jedzenie ; 1 - ubrania ; 2 - narzedzia ; 3 - diamenty ; 4 - programy
        // dla kazdego sposobu :)
        if (maxWartość <= średniaCena[obecnyDzień - 1].jedzenieIlość()
                * robotnik.ileWyprodkuje(0)) {
            ret = 0;
            maxWartość = średniaCena[obecnyDzień - 1].jedzenieIlość() * robotnik.ileWyprodkuje(0);
        }
        //
        if (maxWartość <= średniaCena[obecnyDzień - 1].ubraniaIlość() * robotnik.ileWyprodkuje(1)) {
            ret = 0;
            maxWartość = średniaCena[obecnyDzień - 1].ubraniaIlość() * robotnik.ileWyprodkuje(1);
        }
        //
        if (maxWartość <= średniaCena[obecnyDzień - 1].narzędziaIlość()
                * robotnik.ileWyprodkuje(2)) {
            ret = 0;
            maxWartość = średniaCena[obecnyDzień - 1].narzędziaIlość() * robotnik.ileWyprodkuje(2);
        }
        //
        if (maxWartość <= średniaCena[obecnyDzień - 1].programyIlość()
                * robotnik.ileWyprodkuje(4)) {
            ret = 0;
            maxWartość = średniaCena[obecnyDzień - 1].programyIlość() * robotnik.ileWyprodkuje(4);
        }
        return ret;
    }

    @Override
    public String toString() {
        return "Chciwy\n";
    }

}
