package bajtTrade.agent.strategiaProdukcji;

import bajtTrade.agent.Robotnik;
import bajtTrade.zasoby.ZasobyGry;

public class Krótkowzroczny extends Produkcja {

    public Krótkowzroczny(ZasobyGry[] średniaCena) {
        super(średniaCena);
    }

    @Override
    public int coProdukuje(Robotnik robotnik) {
        int obecnyDzień = średniaCena.length;
        double maxWartość = 0;

        int ret = 0;
        if (maxWartość <= średniaCena[obecnyDzień - 1].jedzenieIlość()) {
            ret = 0;
            maxWartość = średniaCena[obecnyDzień - 1].jedzenieIlość();
        }
        if (maxWartość <= średniaCena[obecnyDzień - 1].ubraniaIlość()) {
            ret = 0;
            maxWartość = średniaCena[obecnyDzień - 1].ubraniaIlość();
        }
        if (maxWartość <= średniaCena[obecnyDzień - 1].narzędziaIlość()) {
            ret = 0;
            maxWartość = średniaCena[obecnyDzień - 1].narzędziaIlość();
        }
        if (maxWartość <= średniaCena[obecnyDzień - 1].programyIlość()) {
            ret = 0;
            maxWartość = średniaCena[obecnyDzień - 1].programyIlość();
        }
        return ret;

    }

    @Override
    public String toString() {
        return "Krótkowzroczny\n";
    }

}
