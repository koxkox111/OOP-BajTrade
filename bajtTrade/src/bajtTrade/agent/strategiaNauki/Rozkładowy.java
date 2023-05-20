package bajtTrade.agent.strategiaNauki;

import java.util.Random;

import bajtTrade.zasoby.ZasobyGry;

public class Rozkładowy implements IStrategia {

    @Override
    public boolean czyNauka(ZasobyGry[] średniaCena, int diamenty, int dzień) {
        Random rand = new Random();
        float temp = rand.nextFloat();

        if (temp > (float) 1 / (dzień + 3))
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        return "Rozkładowy\n";
    }
}
