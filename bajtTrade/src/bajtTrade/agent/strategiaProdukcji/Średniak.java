package bajtTrade.agent.strategiaProdukcji;

import bajtTrade.agent.Robotnik;
import bajtTrade.zasoby.ZasobyGry;

public class Średniak extends Produkcja {
    private int historia_średniej_produkcji;

    public Średniak(int historia_średniej_produkcji, ZasobyGry[] średniaCena) {
        super(średniaCena);
        this.historia_średniej_produkcji = historia_średniej_produkcji;
    }

    @Override
    public int coProdukuje(Robotnik robotnik) {
        double maxWartość = 0;
        int ret = 0;

        //
        for (int j = średniaCena.length - 1; j > średniaCena.length - historia_średniej_produkcji
                - 1 && j >= 0; j--) {
            if (maxWartość < średniaCena[j].jedzenieIlość()) {
                maxWartość = średniaCena[j].jedzenieIlość();
                ret = 0;
            }
        }
        for (int j = średniaCena.length - 1; j > średniaCena.length - historia_średniej_produkcji
                - 1 && j >= 0; j--) {
            if (maxWartość < średniaCena[j].ubraniaIlość()) {
                maxWartość = średniaCena[j].ubraniaIlość();
                ret = 1;
            }
        }
        for (int j = średniaCena.length - 1; j > średniaCena.length - historia_średniej_produkcji
                - 1 && j >= 0; j--) {
            if (maxWartość < średniaCena[j].narzędziaIlość()) {
                maxWartość = średniaCena[j].narzędziaIlość();
                ret = 2;
            }
        }
        for (int j = średniaCena.length - 1; j > średniaCena.length - historia_średniej_produkcji
                - 1 && j >= 0; j--) {
            if (maxWartość < średniaCena[j].programyIlość()) {
                maxWartość = średniaCena[j].programyIlość();
                ret = 4;
            }
        }

        return ret;
    }

    @Override
    public String toString() {
        return "Średniak\n";
    }

}
