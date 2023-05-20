package bajtTrade.agent;

import java.util.ArrayList;

import bajtTrade.Fabryka;
import bajtTrade.agent.oferta.Oferta;
import bajtTrade.agent.oferta.OfertaSpekulant;
import bajtTrade.zasoby.Ubrania;
import bajtTrade.zasoby.Ubranie;
import bajtTrade.zasoby.Zasoby;
import bajtTrade.zasoby.ZasobyGry;

public class SpekulantWypukły extends Spekulant {

    public SpekulantWypukły(int id, Zasoby zasoby) {
        super(id, zasoby);
    }

    public void dodajOferty(ZasobyGry[] średniaCena, ZasobyGry[] występowanie,
            ArrayList<Oferta> ofertaKupna, ArrayList<Oferta> ofertaSprzedaży) {

        // dodaj jedzenie
        if (Fabryka.wklęsła(średniaCena, 0) == 0 || Fabryka.wklęsła(średniaCena, 0) == 2)
            if (zasoby.jedzenieIlość() > 0) {
                Oferta oferta = new OfertaSpekulant((int) zasoby.jedzenieIlość(), 0, this,
                        średniaCena[średniaCena.length - 1].jedzenieIlość() * 1.1);
                ofertaSprzedaży.add(oferta);
            }

        if (Fabryka.wklęsła(średniaCena, 0) == 0 || Fabryka.wklęsła(średniaCena, 0) == 1)
            if (zasoby.jedzenieIlość() > 0) {
                Oferta oferta = new OfertaSpekulant((int) zasoby.jedzenieIlość(), 0, this,
                        średniaCena[średniaCena.length - 1].jedzenieIlość() * 0.9);
                ofertaKupna.add(oferta);
            }
        // ubrania
        Ubrania ubrania = zasoby.ubraniaDaj();
        ArrayList<Ubranie> ubraniaLista = ubrania.lista();
        if (Fabryka.wklęsła(średniaCena, 1) == 0 || Fabryka.wklęsła(średniaCena, 1) == 2) {
            for (int i = 0; i < ubraniaLista.size(); i++) {
                Ubranie ubr = ubraniaLista.get(i);
                if (ubr.dajIlość() > 0) {
                    Oferta ofertaS = new OfertaSpekulant((int) ubr.dajIlość(), 1, ubr.dajJakość(),
                            this, średniaCena[średniaCena.length - 1].ubraniaIlość() * 1.1);
                    ofertaSprzedaży.add(ofertaS);
                }
            }
        }
        if (Fabryka.wklęsła(średniaCena, 1) == 0 || Fabryka.wklęsła(średniaCena, 1) == 1) {
            for (int i = 0; i < ubraniaLista.size(); i++) {
                Ubranie ubr = ubraniaLista.get(i);
                if (ubr.dajIlość() > 0) {
                    Oferta ofertaK = new OfertaSpekulant(100, 1, ubr.dajJakość(), this,
                            średniaCena[średniaCena.length - 1].ubraniaIlość() * 0.9);
                    ofertaKupna.add(ofertaK);
                }
            }
        }
        // narzedzia
        if (Fabryka.wklęsła(średniaCena, 2) == 0 || Fabryka.wklęsła(średniaCena, 2) == 2) {
            for (int i = 0; i < zasoby.narzędziaDaj().dajIlość().length; i++) {
                if (zasoby.narzędziaDaj().getIlość(i + 1) > 0) {
                    Oferta ofertaS = new OfertaSpekulant(
                            (int) zasoby.narzędziaDaj().getIlość(i + 1), 2, i + 1, this,
                            średniaCena[średniaCena.length - 1].narzędziaIlość() * 1.1);
                    ofertaSprzedaży.add(ofertaS);
                }
            }
        }
        if (Fabryka.wklęsła(średniaCena, 2) == 0 || Fabryka.wklęsła(średniaCena, 2) == 1) {
            for (int i = 0; i < zasoby.narzędziaDaj().dajIlość().length; i++) {
                if (zasoby.narzędziaDaj().getIlość(i + 1) > 0) {
                    Oferta ofertaK = new OfertaSpekulant(100, 2, i + 1, this,
                            średniaCena[średniaCena.length - 1].narzędziaIlość() * 0.9);
                    ofertaKupna.add(ofertaK);
                }
            }
        }
        // programy
        if (Fabryka.wklęsła(średniaCena, 4) == 0 || Fabryka.wklęsła(średniaCena, 4) == 2) {
            for (int i = 0; i < zasoby.programyDaj().dajIlość().length; i++) {
                if (zasoby.programyDaj().getIlość(i + 1) > 0) {
                    Oferta ofertaS = new OfertaSpekulant((int) zasoby.programyDaj().getIlość(i + 1),
                            4, i + 1, this,
                            średniaCena[średniaCena.length - 1].programyIlość() * 1.1);
                    ofertaSprzedaży.add(ofertaS);
                }
            }
        }
        if (Fabryka.wklęsła(średniaCena, 4) == 0 || Fabryka.wklęsła(średniaCena, 4) == 1) {
            for (int i = 0; i < zasoby.programyDaj().dajIlość().length; i++) {
                if (zasoby.programyDaj().getIlość(i + 1) > 0) {
                    Oferta ofertaK = new OfertaSpekulant(100, 4, i + 1, this,
                            średniaCena[średniaCena.length - 1].programyIlość() * 0.9);
                    ofertaKupna.add(ofertaK);
                }
            }
        }
    }

    @Override
    public String toString() {
        String ret = "id: " + id + "\n" + "Kariera: " + "Wypukły" + "\n" + zasoby + "\n";
        return ret;
    }
}