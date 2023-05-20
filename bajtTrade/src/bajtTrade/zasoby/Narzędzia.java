package bajtTrade.zasoby;

import java.util.Arrays;

public class Narzędzia {
    private int[] ilość;

    public Narzędzia(int x) {
        ilość = new int[1];
        ilość[0] = x;
    }

    public Narzędzia(int[] ilość) {
        this.ilość = ilość;
    }

    public int[] dajIlość() {
        return ilość;
    }

    public int getIlość(int poziom) {
        if (poziom > ilość.length)
            return 0;
        else
            return ilość[poziom - 1];
    }

    public void dodaj(int poziom, int x) {
        if (poziom > ilość.length)
            ilość = Arrays.copyOf(ilość, poziom);
        ilość[poziom - 1] += x;
    }

    public void ustaw(int poziom, int x) {
        if (poziom > ilość.length)
            ilość = Arrays.copyOf(ilość, poziom);
        ilość[poziom - 1] = x;
    }

    public int dajPremie() {
        int ret = 0;
        for (int i = 0; i < ilość.length; i++) {
            ret += (i + 1) * ilość[i];
        }
        return ret;
    }

    public void wyczyść() {
        ilość = new int[1];
    }

    public String toString() {
        String ret = "[ ";
        for (int i = 0; i < ilość.length; i++) {
            ret += ilość[i] + " ";
        }
        return ret + "]\n";
    }
}
