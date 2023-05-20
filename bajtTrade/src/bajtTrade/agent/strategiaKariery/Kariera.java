package bajtTrade.agent.strategiaKariery;

import bajtTrade.zasoby.ZasobyGry;

public abstract class Kariera implements IKariera {
    protected int[] poziomZawodu;
    protected int obecnyZawód;
    protected int id;
    protected ZasobyGry[] występowanie;

    public Kariera(int poziom, int zawód, int id, ZasobyGry[] występowanie) {
        poziomZawodu = new int[5];
        for (int i = 0; i < 5; i++)
            poziomZawodu[i] = 1;
        poziomZawodu[zawód] = poziom;
        obecnyZawód = zawód;
        this.id = id;
        this.występowanie = występowanie;
    }

    @Override
    public int podajZawód() {
        return obecnyZawód;
    }

    @Override
    public void zmieńZawód(int zawód) {
        if (zawód == obecnyZawód)
            poziomZawodu[zawód]++;
        else
            obecnyZawód = zawód;
    }

    @Override
    public int podajBonus(int zawód) {
        if (obecnyZawód == zawód) {
            if (poziomZawodu[zawód] == 1)
                return 50;
            else if (poziomZawodu[zawód] == 2)
                return 150;
            else if (poziomZawodu[zawód] == 3)
                return 300;
            else
                return 300 + 25 * (poziomZawodu[zawód] - 3);
        } else {
            return 0;
        }
    }

    public int podajPoziom(int zawód) {
        return poziomZawodu[zawód];
    }

}
