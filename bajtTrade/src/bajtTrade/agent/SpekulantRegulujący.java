package bajtTrade.agent;

import java.util.ArrayList;

import bajtTrade.agent.oferta.Oferta;
import bajtTrade.agent.oferta.OfertaSpekulant;
import bajtTrade.zasoby.Ubrania;
import bajtTrade.zasoby.Ubranie;
import bajtTrade.zasoby.Zasoby;
import bajtTrade.zasoby.ZasobyGry;

public class SpekulantRegulujący extends Spekulant {

    public SpekulantRegulujący(int id, Zasoby zasoby) {
        super(id, zasoby);
    }

    public void dodajOferty(ZasobyGry[] średniaCena, ZasobyGry[] występowanie,
            ArrayList<Oferta> ofertaKupna, ArrayList<Oferta> ofertaSprzedaży) {
        if (średniaCena.length == 1)
            return;

        int dzień = średniaCena.length - 1;

        // jedzenie
        double pi = występowanie[dzień].jedzenieIlość();
        double pi1 = występowanie[dzień].jedzenieIlość();
        double suma = średniaCena[dzień].jedzenieIlość() * pi / Math.max(1, pi1);
        Oferta ofertaK = new OfertaSpekulant(100, 0, this, suma * 0.9);
        ofertaKupna.add(ofertaK);
        if (zasoby.jedzenieIlość() > 0) {
            Oferta ofertaS = new OfertaSpekulant((int) zasoby.jedzenieIlość(), 0, this, suma * 1.1);
            ofertaSprzedaży.add(ofertaS);
        }

        // ubranie
        Ubrania ubrania = zasoby.ubraniaDaj();
        ArrayList<Ubranie> ubraniaLista = ubrania.lista();
        for (int i = 0; i < ubraniaLista.size(); i++) {
            Ubranie ubr = ubraniaLista.get(i);
            ofertaK = new OfertaSpekulant(100, 1, ubr.dajJakość(), this, suma * 0.9);
            ofertaKupna.add(ofertaK);
            if (ubr.dajIlość() > 0) {
                Oferta ofertaS = new OfertaSpekulant((int) ubr.dajIlość(), 1, ubr.dajJakość(), this,
                        suma * 1.1);
                ofertaSprzedaży.add(ofertaS);
            }
        }

        for (int i = 0; i < zasoby.narzędziaDaj().dajIlość().length; i++) {
            ofertaK = new OfertaSpekulant(100, 2, i + 1, this, suma * 0.9);
            ofertaKupna.add(ofertaK);
            if (zasoby.narzędziaDaj().getIlość(i + 1) > 0) {
                Oferta ofertaS = new OfertaSpekulant((int) zasoby.narzędziaDaj().getIlość(i + 1), 2,
                        i + 1, this, suma * 1.1);
                ofertaSprzedaży.add(ofertaS);
            }
        }
        for (int i = 0; i < zasoby.programyDaj().dajIlość().length; i++) {
            ofertaK = new OfertaSpekulant(100, 4, i + 1, this, suma * 0.9);
            ofertaKupna.add(ofertaK);
            if (zasoby.programyDaj().getIlość(i + 1) > 0) {
                Oferta ofertaS = new OfertaSpekulant((int) zasoby.programyDaj().getIlość(i + 1), 4,
                        i + 1, this, suma * 1.1);
                ofertaSprzedaży.add(ofertaS);
            }
        }
    }
}
