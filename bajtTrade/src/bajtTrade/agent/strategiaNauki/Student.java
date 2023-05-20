package bajtTrade.agent.strategiaNauki;

import bajtTrade.zasoby.ZasobyGry;

public class Student implements IStrategia {
    private int zapas;
    private int okres;

    public Student(int zapas, int okres) {
        this.okres = okres;
        this.zapas = zapas;
    }

    @Override
    public boolean czyNauka(ZasobyGry[] średniaCena, int diamenty, int dzień) {
        double historia = 0;
        int ilosc = Math.min(średniaCena.length, okres);
        for (int i = średniaCena.length - 1; i >= średniaCena.length - ilosc; i--) {
            historia += średniaCena[i].jedzenieIlość();
        }
        historia /= ilosc;

        return diamenty >= historia * 100 * zapas;
    }

    @Override
    public String toString() {
        return "Student\n";
    }

}
