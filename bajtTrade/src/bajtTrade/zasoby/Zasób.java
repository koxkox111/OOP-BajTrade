package bajtTrade.zasoby;

public abstract class Zasób implements IZasoby {

    protected double ilość;

    public Zasób(double ilość) {
        this.ilość = ilość;
    }

    @Override
    public double ilość() {
        return ilość;
    }

    public void dodaj(double x) {
        ilość += x;
    }

    @Override
    public void podziel(double x) {
        ilość /= x;
    }

    public void zmieńNa(double x) {
        this.ilość = x;
    }

}
