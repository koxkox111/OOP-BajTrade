package bajtTrade;

import bajtTrade.agent.Robotnik;
import bajtTrade.agent.Spekulant;
import bajtTrade.agent.SpekulantŚredni;
import bajtTrade.agent.strategiaKariery.IKariera;
import bajtTrade.agent.strategiaKariery.Konserwatysta;
import bajtTrade.agent.strategiaKariery.Rewolucjonista;
import bajtTrade.agent.strategiaKupna.Gadżeciarz;
import bajtTrade.agent.strategiaKupna.IKupno;
import bajtTrade.agent.strategiaKupna.Technofob;
import bajtTrade.agent.strategiaKupna.Zmechanizowany;
import bajtTrade.agent.strategiaNauki.IStrategia;
import bajtTrade.agent.strategiaNauki.Okresowy;
import bajtTrade.agent.strategiaNauki.Rozkładowy;
import bajtTrade.agent.strategiaNauki.Student;
import bajtTrade.agent.strategiaProdukcji.IProdukcja;
import bajtTrade.agent.strategiaProdukcji.Losowy;
import bajtTrade.agent.strategiaProdukcji.Perspektywiczny;
import bajtTrade.agent.strategiaProdukcji.Średniak;
import bajtTrade.giełda.Giełda;
import bajtTrade.giełda.Socjalistyczna;
import bajtTrade.zasoby.Diamenty;
import bajtTrade.zasoby.Jedzenie;
import bajtTrade.zasoby.Narzędzia;
import bajtTrade.zasoby.Program;
import bajtTrade.zasoby.Ubrania;
import bajtTrade.zasoby.Zasoby;
import bajtTrade.zasoby.ZasobyGry;

public class TestMain {

    public static void main(String[] args) {
        // Giełda:
        int długość = 100;
        int kara_za_brak_ubrań = 2;
        Diamenty diam3 = new Diamenty(1);
        Jedzenie jedz3 = new Jedzenie(125.27);
        ZasobyGry średnia = new ZasobyGry(diam3, jedz3, 100, 100, 100);
        ZasobyGry[] średniaCena = new ZasobyGry[1];
        średniaCena[0] = średnia;
        ZasobyGry[] występowanie = new ZasobyGry[1];
        występowanie[0] = new ZasobyGry(new Diamenty(1), new Jedzenie(1), 1, 1, 1);
//        // ROBOTNIK
        Diamenty diam1 = new Diamenty(100);
        Jedzenie jedz1 = new Jedzenie(100);
        Diamenty diam2 = new Diamenty(100);
        Jedzenie jedz2 = new Jedzenie(100);
        ZasobyGry mnożnikR = new ZasobyGry(diam1, jedz1, 100, 100, 100);
        Zasoby zasobyR = new Zasoby(diam2, jedz2, new Narzędzia(100), new Ubrania(100),
                new Program(100));
        int idR = 1;
        IKariera karieraR = new Rewolucjonista(1, 0, 1, występowanie);
        IKupno kupnoR = new Technofob();
        IStrategia naukaR = new Student(1, 1);
        IProdukcja produkcjaR = new Perspektywiczny(1, średniaCena);
        Robotnik andrzej = new Robotnik(idR, zasobyR, mnożnikR, karieraR, naukaR, produkcjaR,
                kupnoR, kara_za_brak_ubrań);

        ZasobyGry mnożnikR2 = new ZasobyGry(diam1, jedz1, 100, 100, 100);
        Zasoby zasobyR2 = new Zasoby(new Diamenty(1234), jedz2, new Narzędzia(100),
                new Ubrania(100), new Program(100));
        int idR2 = 2;
        IKariera karieraR2 = new Konserwatysta(2, 0, 2, występowanie);
        IKupno kupnoR2 = new Gadżeciarz(1);
        IStrategia naukaR2 = new Okresowy(3);
        IProdukcja produkcjaR2 = new Losowy(średniaCena);

        Robotnik marian = new Robotnik(idR2, zasobyR2, mnożnikR2, karieraR2, naukaR2, produkcjaR2,
                kupnoR2, kara_za_brak_ubrań);

        ZasobyGry mnożnikR3 = new ZasobyGry(new Diamenty(100), new Jedzenie(100), 100, 100, 100);
        Zasoby zasobyR3 = new Zasoby(new Diamenty(500), new Jedzenie(100), new Narzędzia(1000),
                new Ubrania(0), new Program(0));
        int idR3 = 3;
        IKariera karieraR3 = new Rewolucjonista(2, 2, 3, występowanie);
        IKupno kupnoR3 = new Zmechanizowany(50);
        IStrategia naukaR3 = new Rozkładowy();
        IProdukcja produkcjaR3 = new Średniak(5, średniaCena);

        Robotnik michal = new Robotnik(idR3, zasobyR3, mnożnikR3, karieraR3, naukaR3, produkcjaR3,
                kupnoR3, kara_za_brak_ubrań);

//
        // Spekulant
        Diamenty diam4 = new Diamenty(10000);
        Jedzenie jedz4 = new Jedzenie(100);
        Zasoby zasobyS = new Zasoby(diam4, jedz4, new Narzędzia(100), new Ubrania(100),
                new Program(100));
        int idS = 15;
        Spekulant adam = new SpekulantŚredni(idS, zasobyS, 3);

        Diamenty diam5 = new Diamenty(12345);
        Jedzenie jedz5 = new Jedzenie(12345);
        Zasoby zasobyS5 = new Zasoby(diam5, jedz5, new Narzędzia(1000), new Ubrania(1000),
                new Program(1000));
        int idS5 = 20;
        Spekulant wojtek = new SpekulantŚredni(idS5, zasobyS5, 2);

        Robotnik[] tabR = new Robotnik[3];
        tabR[0] = andrzej;
        tabR[1] = marian;
        tabR[2] = michal;
        Spekulant[] tabS = new Spekulant[2];
        tabS[0] = adam;
        tabS[1] = wojtek;
//

        Giełda giełda = new Socjalistyczna(długość, kara_za_brak_ubrań, średniaCena, występowanie,
                tabR, tabS);

        giełda.wykonajSymulacje();

        System.out.println("Wynik po końcu symulacji:\n" + giełda.toString());
    }

}
