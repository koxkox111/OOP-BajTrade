package bajtTrade.agent;

import java.util.ArrayList;

import bajtTrade.agent.oferta.Oferta;
import bajtTrade.agent.oferta.OfertaSpekulant;
import bajtTrade.zasoby.Ubrania;
import bajtTrade.zasoby.Ubranie;
import bajtTrade.zasoby.Zasoby;
import bajtTrade.zasoby.ZasobyGry;

public class SpekulantŚredni extends Spekulant {
    private int historia_spekulanta_sredniego;

    public SpekulantŚredni(int id, Zasoby zasoby, int historia) {
        super(id, zasoby);
        this.historia_spekulanta_sredniego = historia;
    }

    public void dodajOferty(ZasobyGry[] średniaCena, ZasobyGry[] występowanie,
            ArrayList<Oferta> ofertaKupna, ArrayList<Oferta> ofertaSprzedaży) {
        // jedzenie
        double suma = 0;
        for (int j = średniaCena.length - 1; j >= średniaCena.length
                - this.historia_spekulanta_sredniego && j >= 0; j--) {
            suma += średniaCena[j].jedzenieIlość();
        }
        suma /= Math.min(średniaCena.length, historia_spekulanta_sredniego);

        if (zasoby.jedzenieIlość() > 0) {
            Oferta ofertaK = new OfertaSpekulant(100, 0, this, suma * 0.9);
            Oferta ofertaS = new OfertaSpekulant((int) zasoby.jedzenieIlość(), 0, this, suma * 1.1);
            ofertaSprzedaży.add(ofertaS);
            ofertaKupna.add(ofertaK);
        } else {
            Oferta ofertaK = new OfertaSpekulant(100, 0, this, suma * 0.95);
            ofertaKupna.add(ofertaK);
        }

        // ubrania
        suma = 0;
        for (int j = średniaCena.length - 1; j >= średniaCena.length
                - this.historia_spekulanta_sredniego && j >= 0; j--) {
            suma += średniaCena[j].ubraniaIlość();
        }
        suma /= Math.min(średniaCena.length, historia_spekulanta_sredniego);

        Ubrania ubrania = zasoby.ubraniaDaj();
        ArrayList<Ubranie> ubraniaLista = ubrania.lista();
        for (int i = 0; i < ubraniaLista.size(); i++) {
            Ubranie ubr = ubraniaLista.get(i);
            if (ubr.dajIlość() > 0) {
                Oferta ofertaK = new OfertaSpekulant(100, 1, ubr.dajJakość(), this, suma * 0.9);
                Oferta ofertaS = new OfertaSpekulant((int) ubr.dajIlość(), 1, ubr.dajJakość(), this,
                        suma * 1.1);
                ofertaSprzedaży.add(ofertaS);
                ofertaKupna.add(ofertaK);
            } else {
                Oferta ofertaK = new OfertaSpekulant(100, 1, ubr.dajJakość(), this, suma * 0.95);
                ofertaKupna.add(ofertaK);
            }
        }
        // narzedzia
        suma = 0;
        for (int j = średniaCena.length - 1; j >= średniaCena.length
                - this.historia_spekulanta_sredniego && j >= 0; j--) {
            suma += średniaCena[j].narzędziaIlość();
        }
        suma /= Math.min(średniaCena.length, historia_spekulanta_sredniego);
        for (int i = 0; i < zasoby.narzędziaDaj().dajIlość().length; i++) {
            if (zasoby.narzędziaDaj().getIlość(i + 1) > 0) {
                Oferta ofertaK = new OfertaSpekulant(100, 2, i + 1, this, suma * 0.9);
                Oferta ofertaS = new OfertaSpekulant((int) zasoby.narzędziaDaj().getIlość(i + 1), 2,
                        i + 1, this, suma * 1.1);
                ofertaSprzedaży.add(ofertaS);
                ofertaKupna.add(ofertaK);
            } else {
                Oferta ofertaK = new OfertaSpekulant(100, 2, i + 2, this, suma * 0.95);
                ofertaKupna.add(ofertaK);
            }
        }
        // programy
        for (int j = średniaCena.length - 1; j >= średniaCena.length
                - this.historia_spekulanta_sredniego && j >= 0; j--) {
            suma += średniaCena[j].programyIlość();
        }
        suma /= Math.min(średniaCena.length, historia_spekulanta_sredniego);
        for (int i = 0; i < zasoby.programyDaj().dajIlość().length; i++) {
            if (zasoby.programyDaj().getIlość(i + 1) > 0) {
                Oferta ofertaK = new OfertaSpekulant(100, 4, i + 1, this, suma * 0.9);
                Oferta ofertaS = new OfertaSpekulant((int) zasoby.programyDaj().getIlość(i + 1), 4,
                        i + 1, this, suma * 1.1);
                ofertaSprzedaży.add(ofertaS);
                ofertaKupna.add(ofertaK);
            } else {
                Oferta ofertaK = new OfertaSpekulant(100, 4, i + 2, this, suma * 0.95);
                ofertaKupna.add(ofertaK);
            }
        }
    }

    public String toString() {
        String ret = "id: " + id + "\n" + "Kariera: " + "Średni" + "\n" + zasoby + "\n";
        return ret;
    }
}
