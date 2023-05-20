package bajtTrade.zasoby;

public class Zasoby {
    private Diamenty diamenty;
    private Jedzenie jedzenie;
    private Ubrania ubrania;
    private Narzędzia narzędzia;
    private Program program;

    public Zasoby(Diamenty diamenty, Jedzenie jedzenie, Narzędzia narzędzia, Ubrania ubrania,
            Program program) {
        this.diamenty = diamenty;
        this.jedzenie = jedzenie;
        this.program = program;
        this.narzędzia = narzędzia;
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

    public Narzędzia narzędziaDaj() {
        return narzędzia;
    }

    public void narzędziaDodaj(int poziom, int x) {
        narzędzia.dodaj(poziom, x);
    }

    public void narzędziaUstaw(int poziom, int x) {
        narzędzia.ustaw(poziom, x);
    }

    public int narzędziaPremia() {
        return narzędzia.dajPremie();
    }

    public void narzędziaWyczyść() {
        narzędzia.wyczyść();
    }

    public Program programyDaj() {
        return program;
    }

    public void programyDodaj(int poziom, int x) {
        program.dodaj(poziom, x);
    }

    public void programyaUstaw(int poziom, int x) {
        program.ustaw(poziom, x);
    }

    public int[] wykonajProgramy(int x) {
        int[] ilość = program.getIlośćTablica();
        int[] ret = new int[ilość.length];
        for (int i = ilość.length - 1; i >= 0; i--) {
            if (x > 0 && ilość[i] > 0) {
                int ile = Math.min(x, ilość[i]);
                program.dodaj(i + 1, -ile);
                ret[i] = ile;
                x -= ile;
            } else {
                ret[i] = 0;
            }
        }
        if (x > 0)
            ret[0] += x;
        return ret;
    }

    public void ubraniaDodaj(int poziom, int x) {
        ubrania.dodaj(new Ubranie(x, poziom));
    }

    public boolean ubraniaCzyMożna() {
        return ubrania.czyMożna();
    }

    public int ubraniaIle() {
        return ubrania.ile();
    }

    public void załóżUbrania() {
        ubrania.załóż();
    }

    public Ubrania ubraniaDaj() {
        return ubrania;
    }

    @Override
    public String toString() {
        String ret = "\n";
        ret += "Jedzenie: " + jedzenieIlość() + "\n" + "Diamenty: " + diamentyIlość() + "\n";
        ret += "Ubrania: " + ubrania.toString();
        ret += "Narzędzia: " + narzędzia.toString();
        ret += "Programy: " + program.toString();
        return ret;
    }

}
