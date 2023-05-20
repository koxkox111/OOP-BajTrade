package bajtTrade.zasoby;

public class Ubranie {
    private int ilość;
    private int jakość;
    private int zdrowie;

    public Ubranie(int ilość, int jakość) {
        this.ilość = ilość;
        this.jakość = jakość;
        this.zdrowie = jakość * jakość;
    }

    public Ubranie(int ilość, int jakość, int zdrowie) {
        this.ilość = ilość;
        this.jakość = jakość;
        this.zdrowie = zdrowie;
    }

    public int dajIlość() {
        return ilość;
    }

    public int dajJakość() {
        return jakość;
    }

    public int dajZdrowie() {
        return zdrowie;
    }

    public void usunIlosc(int x) {
        ilość -= x;
    }

    public Ubranie weź(int x) {
        ilość -= x;
        Ubranie ret = new Ubranie(x, jakość, zdrowie - 1);
        return ret;
    }
}
