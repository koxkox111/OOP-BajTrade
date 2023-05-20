package bajtTrade.agent.oferta;

import bajtTrade.agent.Spekulant;

public class OfertaSpekulant extends Oferta {
    private double cena;

    public OfertaSpekulant(int ilość, int produkt, Spekulant spekulant, double cena) {
        super(ilość, produkt, spekulant);
        this.cena = cena;
    }

    public OfertaSpekulant(int ilość, int produkt, int poziom, Spekulant spekulant, double cena) {
        super(ilość, produkt, poziom, spekulant);
        this.cena = cena;
    }

    @Override
    public double getCena() {
        return cena;
    }
}
