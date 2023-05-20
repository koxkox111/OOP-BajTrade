package bajtTrade.giełda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import bajtTrade.agent.IAgent;
import bajtTrade.agent.ISpekulant;
import bajtTrade.agent.Robotnik;
import bajtTrade.agent.Spekulant;
import bajtTrade.agent.oferta.Oferta;
import bajtTrade.zasoby.Diamenty;
import bajtTrade.zasoby.Jedzenie;
import bajtTrade.zasoby.ZasobyGry;

public abstract class Giełda implements IGiełda {
    protected Comparator<Oferta> spekulantComp;
    protected Comparator<Oferta> robotnikComp;

    protected int długość;
    protected int kara_za_brak_ubrań;

    protected ZasobyGry[] średniaCena;
    protected ZasobyGry[] występowanie;

    protected ArrayList<Oferta> ofertyKupnaRobotnik;
    protected ArrayList<Oferta> ofertyKupnaSpekulant;
    protected ArrayList<Oferta> ofertySprzedażyRobotnik;
    protected ArrayList<Oferta> ofertySprzedażySpekulant;

    protected Robotnik[] robotnik;
    protected ISpekulant[] spekulant;
    int obrot;

    // rodzaj giełdy

    public Giełda(int długość, int kara_za_brak_ubrań, ZasobyGry[] średniaCena,
            ZasobyGry[] występowanie, Robotnik[] robotnik, Spekulant[] spekulant) {
        this.długość = długość;
        this.kara_za_brak_ubrań = kara_za_brak_ubrań;
        this.średniaCena = średniaCena;
        this.robotnik = robotnik;
        this.spekulant = spekulant;
        this.występowanie = występowanie;
        ofertyKupnaRobotnik = new ArrayList<Oferta>();
        ofertyKupnaSpekulant = new ArrayList<Oferta>();
        ofertySprzedażyRobotnik = new ArrayList<Oferta>();
        ofertySprzedażySpekulant = new ArrayList<Oferta>();
        spekulantComp = new Comparator<Oferta>() {
            @Override
            public int compare(Oferta o1, Oferta o2) {
                return compareSpekulant(o1, o2);
            }
        };

        robotnikComp = new Comparator<Oferta>() {
            @Override
            public int compare(Oferta o1, Oferta o2) {
                return compareRobotnik(o1, o2);
            }
        };

    }

    public int compareSpekulant(Oferta o1, Oferta o2) {
        int zasób1 = o1.getProdukt();
        int zasób2 = o2.getProdukt();
        int poziom1 = o1.getPoziom();
        int poziom2 = o2.getPoziom();
        double cena1 = o1.getCena();
        double cena2 = o2.getCena();
        if (zasób1 > zasób2) {
            return 1;
        } else if (zasób1 < zasób2) {
            return -1;
        } else {
            if (poziom1 > poziom2)
                return -1;
            else if (poziom1 < poziom2)
                return 1;
            else if (cena1 > cena2)
                return 1;
            else if (cena1 < cena2)
                return -1;
            else
                return 0;
        }
    }

    public int compareRobotnik(Oferta o1, Oferta o2) {
        int id1 = o1.getAgent().getId();
        int id2 = o1.getAgent().getId();
        double diam1 = o1.getAgent().getZasoby().diamentyIlość();
        double diam2 = o2.getAgent().getZasoby().diamentyIlość();
        if (diam1 > diam2) {
            return 1;
        } else if (diam1 < diam2) {
            return -1;
        } else {
            return id1 - id2;
        }
    }

    public void stwórzOferty(int dzień) {
        for (int i = 0; i < this.robotnik.length; i++) {
            if (robotnik[i].czyŻyje())
                robotnik[i].wykonajDzień(dzień, ofertyKupnaRobotnik, ofertySprzedażyRobotnik,
                        średniaCena);
        }
        for (int i = 0; i < this.spekulant.length; i++) {
            spekulant[i].dodajOferty(średniaCena, występowanie, ofertyKupnaSpekulant,
                    ofertySprzedażySpekulant);
        }
    }

    public void wykonajDzień(int dzień) {
        stwórzOferty(dzień);
        średniaCena = Arrays.copyOf(średniaCena, średniaCena.length + 1);
        średniaCena[średniaCena.length - 1] = new ZasobyGry(new Diamenty(0), new Jedzenie(0), 0, 0,
                0);
        występowanie = Arrays.copyOf(występowanie, występowanie.length + 1);
        występowanie[występowanie.length - 1] = new ZasobyGry(new Diamenty(0), new Jedzenie(0), 0,
                0, 0);

        Collections.sort(ofertyKupnaRobotnik, robotnikComp);
        Collections.sort(ofertySprzedażyRobotnik, robotnikComp);
        Collections.sort(ofertyKupnaSpekulant, spekulantComp);
        Collections.sort(ofertySprzedażySpekulant, spekulantComp);
        if ((obrot == 1 && dzień % 2 == 0) || obrot == 2) {
            Collections.reverse(ofertyKupnaRobotnik);
            Collections.reverse(ofertySprzedażyRobotnik);
        }

        // sprzedawanie
        for (int i = 0; i < ofertySprzedażyRobotnik.size(); i++) {

            int coSprzedaje = ofertySprzedażyRobotnik.get(i).getProdukt();
            int ileSprzedaje = ofertySprzedażyRobotnik.get(i).getIlość();
            IAgent ktoSprzedaje = ofertySprzedażyRobotnik.get(i).getAgent();
            int poziomSprzedaży = ofertySprzedażyRobotnik.get(i).getPoziom();

            for (int j = ofertyKupnaSpekulant.size() - 1; j >= 0; j--) {

                int coKupuje = ofertyKupnaSpekulant.get(j).getProdukt();
                int ileKupuje = ofertyKupnaSpekulant.get(j).getIlość();
                IAgent ktoKupuje = ofertyKupnaSpekulant.get(j).getAgent();
                double zaIleKupuje = ofertyKupnaSpekulant.get(j).getCena();
                int poziomKupna = ofertyKupnaSpekulant.get(j).getPoziom();

                if (coKupuje == coSprzedaje && poziomSprzedaży == poziomKupna && ileSprzedaje > 0
                        && ileKupuje > 0) {
                    int ilosc = Math.min(ileSprzedaje, ileKupuje);
                    double ileMoznaKupic = ktoKupuje.getZasoby().diamentyIlość() / zaIleKupuje;
                    ilosc = Math.min(ilosc, (int) ileMoznaKupic);

                    // jedzenie
                    if (coSprzedaje == 0) {
                        średniaCena[średniaCena.length - 1].jedzenieDodaj(ilosc * zaIleKupuje);
                        występowanie[występowanie.length - 1].jedzenieDodaj(ilosc);

                        ktoKupuje.getZasoby().jedzenieDodaj(ilosc);
                        ktoKupuje.getZasoby().diamentyDodaj(-ilosc * zaIleKupuje);
                        ktoSprzedaje.getZasoby().diamentyDodaj(ilosc * zaIleKupuje);
                    }
                    if (coSprzedaje == 1) {
                        średniaCena[średniaCena.length - 1].ubraniaDodaj(ilosc * zaIleKupuje);
                        występowanie[występowanie.length - 1].ubraniaDodaj(ilosc);

                        ktoKupuje.getZasoby().ubraniaDodaj(poziomKupna, ilosc);
                        ktoKupuje.getZasoby().diamentyDodaj(-ilosc * zaIleKupuje);
                        ktoSprzedaje.getZasoby().diamentyDodaj(ilosc * zaIleKupuje);
                    }
                    if (coSprzedaje == 2) {
                        średniaCena[średniaCena.length - 1].narzędziaDodaj(ilosc * zaIleKupuje);
                        występowanie[występowanie.length - 1].narzędziaDodaj(ilosc);

                        ktoKupuje.getZasoby().narzędziaDodaj(poziomKupna, ilosc);
                        ktoKupuje.getZasoby().diamentyDodaj(-ilosc * zaIleKupuje);
                        ktoSprzedaje.getZasoby().diamentyDodaj(ilosc * zaIleKupuje);
                    }
                    if (coSprzedaje == 4) {

                        średniaCena[średniaCena.length - 1].programyDodaj(ilosc * zaIleKupuje);
                        występowanie[występowanie.length - 1].programyDodaj(ilosc);

                        ktoKupuje.getZasoby().programyDodaj(poziomKupna, ilosc);
                        ktoKupuje.getZasoby().diamentyDodaj(-ilosc * zaIleKupuje);
                        ktoSprzedaje.getZasoby().diamentyDodaj(ilosc * zaIleKupuje);
                    }
                    ofertySprzedażyRobotnik.get(i).zmieńIlość(ilosc);
                    ofertyKupnaSpekulant.get(j).zmieńIlość(ilosc);
                }
            }
        }

        // kupowanie
        for (int i = 0; i < ofertyKupnaRobotnik.size(); i++) {

            int coKupuje = ofertyKupnaRobotnik.get(i).getProdukt();
            int ileKupuje = ofertyKupnaRobotnik.get(i).getIlość();
            IAgent ktoKupuje = ofertyKupnaRobotnik.get(i).getAgent();

            for (int j = 0; j < ofertySprzedażySpekulant.size(); j++) {
                int coSprzedaje = ofertySprzedażySpekulant.get(j).getProdukt();
                int ileSprzedaje = ofertySprzedażySpekulant.get(j).getIlość();
                IAgent ktoSprzedaje = ofertySprzedażySpekulant.get(j).getAgent();
                double zaIleSprzedaje = ofertySprzedażySpekulant.get(j).getCena();
                int poziomSprzedaży = ofertySprzedażySpekulant.get(j).getPoziom();

                if (coKupuje == coSprzedaje && ileSprzedaje > 0 && ileKupuje > 0) {
                    int ilosc = Math.min(ileSprzedaje, ileKupuje);
                    double ileMoznaKupic = ktoKupuje.getZasoby().diamentyIlość() / zaIleSprzedaje;
                    ilosc = Math.min(ilosc, (int) ileMoznaKupic);

                    if (coKupuje == 1) {
                        średniaCena[średniaCena.length - 1].ubraniaDodaj(ilosc * zaIleSprzedaje);
                        występowanie[występowanie.length - 1].ubraniaDodaj(ilosc);
                        ktoKupuje.getZasoby().ubraniaDodaj(poziomSprzedaży, ilosc);
                        ktoKupuje.getZasoby().diamentyDodaj(-ilosc * zaIleSprzedaje);
                        ktoSprzedaje.getZasoby().diamentyDodaj(ilosc * zaIleSprzedaje);
                        ktoSprzedaje.getZasoby().ubraniaDaj().usun(poziomSprzedaży, ilosc);
                    }
                    if (coKupuje == 2) {
                        średniaCena[średniaCena.length - 1].narzędziaDodaj(ilosc * zaIleSprzedaje);
                        występowanie[występowanie.length - 1].narzędziaDodaj(ilosc);
                        ktoKupuje.getZasoby().narzędziaDodaj(poziomSprzedaży, ilosc);
                        ktoKupuje.getZasoby().diamentyDodaj(-ilosc * zaIleSprzedaje);
                        ktoSprzedaje.getZasoby().diamentyDodaj(ilosc * zaIleSprzedaje);
                        ktoSprzedaje.getZasoby().narzędziaDaj().dodaj(poziomSprzedaży, -ilosc);
                    }
                    if (coKupuje == 4) {

                        średniaCena[średniaCena.length - 1].programyDodaj(ilosc * zaIleSprzedaje);
                        występowanie[występowanie.length - 1].programyDodaj(ilosc);
                        ktoKupuje.getZasoby().programyDodaj(poziomSprzedaży, ilosc);
                        ktoKupuje.getZasoby().diamentyDodaj(-ilosc * zaIleSprzedaje);
                        ktoSprzedaje.getZasoby().diamentyDodaj(ilosc * zaIleSprzedaje);
                        ktoSprzedaje.getZasoby().programyDaj().dodaj(poziomSprzedaży, -ilosc);
                    }
                    if (coKupuje == 0) {
                        średniaCena[średniaCena.length - 1].jedzenieDodaj(ilosc * zaIleSprzedaje);
                        występowanie[występowanie.length - 1].jedzenieDodaj(ilosc);
                        ktoKupuje.getZasoby().jedzenieDodaj(ilosc);
                        ktoKupuje.getZasoby().diamentyDodaj(-ilosc * zaIleSprzedaje);
                        ktoSprzedaje.getZasoby().diamentyDodaj(ilosc * zaIleSprzedaje);
                        ktoSprzedaje.getZasoby().jedzenieDodaj(-ilosc);
                    }
                    ofertyKupnaRobotnik.get(i).zmieńIlość(ilosc);
                    ofertySprzedażySpekulant.get(j).zmieńIlość(ilosc);
                }
            }
        }

        // skupowanie
        for (int i = 0; i < ofertySprzedażyRobotnik.size(); i++) {
            int coSprzedaje = ofertySprzedażyRobotnik.get(i).getProdukt();
            int ileSprzedaje = ofertySprzedażyRobotnik.get(i).getIlość();
            IAgent ktoSprzedaje = ofertySprzedażyRobotnik.get(i).getAgent();
            if (ileSprzedaje > 0) {

                // jedzenie
                if (coSprzedaje == 0) {
                    double zaIleSprzedaje = średniaCena[średniaCena.length - 2].diamentyIlość()
                            * 0.5;
                    if (zaIleSprzedaje == 0)
                        zaIleSprzedaje = średniaCena[0].jedzenieIlość();
                    średniaCena[średniaCena.length - 1]
                            .jedzenieDodaj(ileSprzedaje * zaIleSprzedaje);
                    występowanie[występowanie.length - 1].jedzenieDodaj(ileSprzedaje);
                    ktoSprzedaje.getZasoby().diamentyDodaj(zaIleSprzedaje * ileSprzedaje);
                }
                if (coSprzedaje == 1) {
                    double zaIleSprzedaje = średniaCena[średniaCena.length - 2].diamentyIlość()
                            * 0.5;
                    if (zaIleSprzedaje == 0)
                        zaIleSprzedaje = średniaCena[0].jedzenieIlość();
                    średniaCena[średniaCena.length - 1]
                            .jedzenieDodaj(ileSprzedaje * zaIleSprzedaje);
                    występowanie[występowanie.length - 1].ubraniaDodaj(ileSprzedaje);
                    ktoSprzedaje.getZasoby().diamentyDodaj(zaIleSprzedaje * ileSprzedaje);
                }
                if (coSprzedaje == 2) {
                    double zaIleSprzedaje = średniaCena[średniaCena.length - 2].diamentyIlość()
                            * 0.5;
                    if (zaIleSprzedaje == 0)
                        zaIleSprzedaje = średniaCena[0].jedzenieIlość();
                    średniaCena[średniaCena.length - 1]
                            .jedzenieDodaj(ileSprzedaje * zaIleSprzedaje);
                    występowanie[występowanie.length - 1].narzędziaDodaj(ileSprzedaje);
                    ktoSprzedaje.getZasoby().diamentyDodaj(zaIleSprzedaje * ileSprzedaje);
                }
                if (coSprzedaje == 4) {
                    double zaIleSprzedaje = średniaCena[średniaCena.length - 2].diamentyIlość()
                            * 0.5;
                    if (zaIleSprzedaje == 0)
                        zaIleSprzedaje = średniaCena[0].jedzenieIlość();
                    średniaCena[średniaCena.length - 1]
                            .jedzenieDodaj(ileSprzedaje * zaIleSprzedaje);
                    występowanie[występowanie.length - 1].programyDodaj(ileSprzedaje);
                    ktoSprzedaje.getZasoby().diamentyDodaj(zaIleSprzedaje * ileSprzedaje);
                }
            }
        }

        if (występowanie[występowanie.length - 1].jedzenieIlość() != 0)
            średniaCena[średniaCena.length - 1]
                    .jedzenieZmień(średniaCena[średniaCena.length - 1].jedzenieIlość()
                            / występowanie[występowanie.length - 1].jedzenieIlość());
        if (występowanie[występowanie.length - 1].ubraniaIlość() != 0)
            średniaCena[średniaCena.length - 1]
                    .ubraniaZmień(średniaCena[średniaCena.length - 1].ubraniaIlość()
                            / występowanie[występowanie.length - 1].ubraniaIlość());
        if (występowanie[występowanie.length - 1].narzędziaIlość() != 0)
            średniaCena[średniaCena.length - 1]
                    .narzędziaZmień(średniaCena[średniaCena.length - 1].narzędziaIlość()
                            / występowanie[występowanie.length - 1].narzędziaIlość());
        if (występowanie[występowanie.length - 1].programyIlość() != 0)
            średniaCena[średniaCena.length - 1]
                    .programyZmień(średniaCena[średniaCena.length - 1].programyIlość()
                            / występowanie[występowanie.length - 1].programyIlość());

        ofertySprzedażyRobotnik.clear();
        ofertyKupnaRobotnik.clear();
        ofertyKupnaSpekulant.clear();
        ofertySprzedażySpekulant.clear();
    }

    @Override
    public void wykonajSymulacje() {
        for (int i = 1; i <= this.długość; i++) {
            wykonajDzień(i);
        }
    }

    @Override
    public String toString() {
        String ret = "\nŚredniaCena " + średniaCena[średniaCena.length - 1].toString() + "\n";
        ret += "Występowanie " + występowanie[występowanie.length - 1].toString() + "\n";

        ret += "Robotnicy: \n";

        for (int i = 0; i < robotnik.length; i++) {
            ret += robotnik[i].toString() + "\n";
        }

        ret += "Spekulanci: \n";

        for (int i = 0; i < spekulant.length; i++) {
            ret += spekulant[i].toString() + "\n";
        }

        return ret + "\n";
    }

}
