package bajtTrade.zasoby;

public class ZasobyGry {
    private Diamenty diamenty;
    private Jedzenie jedzenie;
    private double ubrania;
    private double narzędzia;
    private double programy;

    public ZasobyGry(Diamenty diamenty, Jedzenie jedzenie, double ubrania, double narzędzia,
            double programy) {
        this.diamenty = diamenty;
        this.jedzenie = jedzenie;
        this.narzędzia = narzędzia;
        this.programy = programy;
        this.ubrania = ubrania;
    }

    public double diamentyIlość() {
        return diamenty.ilość();
    }

    public void diamentyDodaj(double x) {
        diamenty.dodaj(x);
    }

    public void diamentyZmień(double x) {
        diamenty.zmieńNa(x);
    }

    public double jedzenieIlość() {
        return jedzenie.ilość();
    }

    public void jedzenieDodaj(double x) {
        jedzenie.dodaj(x);
    }

    public void jedzenieZmień(double x) {
        jedzenie.zmieńNa(x);
    }

    public double ubraniaIlość() {
        return ubrania;
    }

    public void ubraniaDodaj(double x) {
        ubrania += x;
    }

    public void ubraniaZmień(double x) {
        ubrania = x;
    }

    public double narzędziaIlość() {
        return narzędzia;
    }

    public void narzędziaDodaj(double x) {
        narzędzia += x;
    }

    public void narzędziaZmień(double x) {
        narzędzia = x;
    }

    public double programyIlość() {
        return programy;
    }

    public void programyDodaj(double x) {
        programy += x;
    }

    public void programyZmień(double x) {
        programy = x;
    }

    @Override
    public String toString() {
        String ret = "\n";
        ret += "Jedzenie: " + jedzenieIlość() + "\n" + "Diamenty: " + diamentyIlość() + "\n";
        ret += "Ubrania: " + ubraniaIlość() + "\n" + "Narzędzia: " + narzędziaIlość()
                + "\nProgramy: " + programyIlość() + "\n";
        return ret;
    }

}
