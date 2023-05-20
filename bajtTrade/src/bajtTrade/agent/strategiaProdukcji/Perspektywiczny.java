package bajtTrade.agent.strategiaProdukcji;

import bajtTrade.agent.Robotnik;
import bajtTrade.zasoby.ZasobyGry;

public class Perspektywiczny extends Produkcja {
    private int historia_perspektywy;

    public Perspektywiczny(int historia_perspektywy, ZasobyGry[] średniaCena) {
        super(średniaCena);
        this.historia_perspektywy = historia_perspektywy;
    }

    @Override
    public int coProdukuje(Robotnik robotnik) {
        int dzień = średniaCena.length;
        int dzieńDrugi = Math.max(1, dzień - historia_perspektywy);
        int ret = 0;

        double maxWartość = średniaCena[dzień - 1].jedzenieIlość()
                - średniaCena[dzieńDrugi - 1].jedzenieIlość();

        double różnica = średniaCena[dzień - 1].jedzenieIlość()
                - średniaCena[dzieńDrugi - 1].jedzenieIlość();
        if (maxWartość <= różnica) {
            maxWartość = różnica;
            ret = 0;
        }
        różnica = średniaCena[dzień - 1].ubraniaIlość()
                - średniaCena[dzieńDrugi - 1].ubraniaIlość();
        if (maxWartość <= różnica) {
            maxWartość = różnica;
            ret = 1;
        }
        różnica = średniaCena[dzień - 1].narzędziaIlość()
                - średniaCena[dzieńDrugi - 1].narzędziaIlość();
        if (maxWartość <= różnica) {
            maxWartość = różnica;
            ret = 2;
        }
        różnica = średniaCena[dzień - 1].programyIlość()
                - średniaCena[dzieńDrugi - 1].programyIlość();
        if (maxWartość <= różnica) {
            maxWartość = różnica;
            ret = 4;
        }
        return ret;

    }

    @Override
    public String toString() {
        return "Perspektywiczny\n";
    }

}
