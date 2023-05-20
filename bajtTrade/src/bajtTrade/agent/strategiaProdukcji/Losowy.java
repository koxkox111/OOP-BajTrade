package bajtTrade.agent.strategiaProdukcji;

import bajtTrade.agent.Robotnik;
import bajtTrade.zasoby.ZasobyGry;

public class Losowy extends Produkcja {
    public Losowy(ZasobyGry[] średniaCena) {
        super(średniaCena);
    }

    @Override
    public int coProdukuje(Robotnik robotnik) {
        double x = Math.random();
        for (int i = 1; i <= 5; i++) {
            if (x < 5 / i)
                return i - 1;
        }
        return 4;
    }

    @Override
    public String toString() {
        return "Losowy\n";
    }
}
