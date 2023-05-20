package bajtTrade.agent;

import java.util.ArrayList;

import bajtTrade.agent.oferta.Oferta;
import bajtTrade.agent.oferta.OfertaRobotnik;
import bajtTrade.agent.strategiaKariery.IKariera;
import bajtTrade.agent.strategiaKupna.IKupno;
import bajtTrade.agent.strategiaNauki.IStrategia;
import bajtTrade.agent.strategiaProdukcji.IProdukcja;
import bajtTrade.zasoby.Zasoby;
import bajtTrade.zasoby.ZasobyGry;

public class Robotnik extends Agent {
    private IKariera kariera;
    private IStrategia nauka;
    private IProdukcja produkcja;
    private IKupno kupno;
    private ZasobyGry mnożnik;
    private int dniBezJedzenia;
    private boolean czyŻyje;
    private int karaUbranie;

    public Robotnik(int id, Zasoby zasoby, ZasobyGry mnożnik, IKariera kariera, IStrategia nauka,
            IProdukcja produkcja, IKupno kupno, int karaUbranie) {
        super(id, zasoby);
        this.kariera = kariera;
        this.nauka = nauka;
        this.mnożnik = mnożnik;
        this.produkcja = produkcja;
        this.kupno = kupno;
        dniBezJedzenia = 0;
        czyŻyje = true;
        this.karaUbranie = karaUbranie;
    }

    public int karaZaJedzenie() {
        if (dniBezJedzenia == 0)
            return 0;
        else if (dniBezJedzenia == 1)
            return -100;
        else
            return -300;
    }

    public boolean czyŻyje() {
        return czyŻyje;
    }

    public int ileWyprodkuje(int k) {
        int bonusNarzędzia = zasoby.narzędziaPremia();
        int karaUbrania = 0;
        if (!zasoby.ubraniaCzyMożna()) {
            karaUbrania = karaUbranie;
        }
        if (k == 0)
            return Math.max(0, (int) mnożnik.jedzenieIlość() * (100 + kariera.podajBonus(k)
                    + karaZaJedzenie() + bonusNarzędzia - karaUbrania) / 100);
        else if (k == 1)
            return Math.max(0, (int) mnożnik.ubraniaIlość() * (100 + kariera.podajBonus(k)
                    + karaZaJedzenie() + bonusNarzędzia - karaUbrania) / 100);
        else if (k == 2)
            return Math.max(0, (int) mnożnik.narzędziaIlość() * (100 + kariera.podajBonus(k)
                    + karaZaJedzenie() + bonusNarzędzia - karaUbrania) / 100);
        else if (k == 3)
            return Math.max(0, (int) mnożnik.diamentyIlość() * (100 + kariera.podajBonus(k)
                    + karaZaJedzenie() + bonusNarzędzia - karaUbrania) / 100);
        else
            return Math.max(0, (int) mnożnik.programyIlość() * (100 + kariera.podajBonus(k)
                    + karaZaJedzenie() + bonusNarzędzia - karaUbrania) / 100);

    }

    public void wykonajDzień(int dzień, ArrayList<Oferta> ofertyKupna,
            ArrayList<Oferta> ofertySprzedaży, ZasobyGry[] średniaCena) {
        if (nauka.czyNauka(średniaCena, (int) zasoby.diamentyIlość(), dzień)) {
            if (kariera.czyZmiana(dzień)) {
                kariera.zmieńZawód(kariera.zmianaNaCo());
            } else {
                kariera.zmieńZawód(kariera.podajZawód());
            }
            dniBezJedzenia = 0;
        } else {
            int coProdukuje = produkcja.coProdukuje(this);
            int ileWyprodukuje = ileWyprodkuje(coProdukuje);

            if (coProdukuje != 3) {
                if (coProdukuje == 1 || coProdukuje == 2) {
                    int ilosc[] = zasoby.wykonajProgramy(ileWyprodukuje);
                    for (int i = 0; i < ilosc.length; i++) {
                        if (ilosc[i] > 0) {

                            Oferta oferta = new OfertaRobotnik(ilosc[i], coProdukuje, i + 1, this);
                            ofertySprzedaży.add(oferta);
                        }
                    }
                } else if (coProdukuje == 0) {
                    Oferta oferta = new OfertaRobotnik(ileWyprodukuje, coProdukuje, this);
                    ofertySprzedaży.add(oferta);
                } else if (coProdukuje == 4) {
                    if (kariera.podajZawód() == 4) {

                        Oferta oferta = new OfertaRobotnik(ileWyprodukuje, coProdukuje,
                                kariera.podajZawód(), this);
                        ofertySprzedaży.add(oferta);
                    } else {

                        Oferta oferta = new OfertaRobotnik(ileWyprodukuje, coProdukuje, this);
                        ofertySprzedaży.add(oferta);
                    }
                }
            } else { // diamenty
                zasoby.diamentyDodaj(ileWyprodukuje);
            }

            kupno.dodajOferty(this, ofertyKupna);

            if (zasoby.jedzenieIlość() < 100) {
                dniBezJedzenia++;
                zasoby.jedzenieZmień(0);
            } else {
                dniBezJedzenia = 0;
                zasoby.jedzenieDodaj(-100);
            }
            if (dniBezJedzenia == 3) {
                czyŻyje = false;
            }
            if (zasoby.ubraniaCzyMożna()) {
                zasoby.załóżUbrania();
            }
            zasoby.narzędziaWyczyść();

        }

    }

    public IProdukcja produkcja() {
        return produkcja;
    }

    @Override
    public String toString() {
        String ret = "";

        ret += "id: " + id + "\n";
        ret += "pożywienie: " + dniBezJedzenia + ",czy żyje: " + czyŻyje + "\n";
        ret += "Zasoby: " + zasoby.toString();
        ret += "Mnożnik: " + mnożnik.toString();
        ret += "Kariera: " + kariera.toString();
        ret += "Nauka: " + nauka.toString();
        ret += "Kupno: " + kupno.toString();
        ret += "Produkcja: " + produkcja.toString();

        return ret;
    }
}
