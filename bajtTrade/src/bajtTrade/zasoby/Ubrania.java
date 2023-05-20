package bajtTrade.zasoby;

import java.util.ArrayList;

public class Ubrania {
    private ArrayList<Ubranie> lista;

    public Ubrania(int x) {
        lista = new ArrayList<Ubranie>();
        lista.add(new Ubranie(x, 1));
    }

    public boolean czyMożna() {
        int ret = ile();
        if (ret >= 100)
            return true;
        else
            return false;
    }

    public int ile() {
        int ret = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).dajIlość() > 0 && lista.get(i).dajZdrowie() > 0) {
                ret += lista.get(i).dajIlość();
            }
        }
        return ret;
    }

    public void załóż() {
        int doZałożenia = 100;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).dajIlość() > 0 && lista.get(i).dajZdrowie() > 0) {
                int zakładam = Math.min(doZałożenia, lista.get(i).dajIlość());
                Ubranie nowe = lista.get(i).weź(zakładam);
                lista.add(nowe);
                doZałożenia -= zakładam;
                if (doZałożenia <= 0)
                    break;
            }
        }
    }

    public void usun(int poziom, int ilosc) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).dajIlość() > 0 && lista.get(i).dajJakość() == poziom) {
                int ile = Math.min(ilosc, lista.get(i).dajIlość());
                lista.get(i).usunIlosc(ile);
                ilosc -= ile;
            }
        }
    }

    public void dodaj(Ubranie ubranie) {
        lista.add(ubranie);
    }

    public ArrayList<Ubranie> lista() {
        return lista;
    }

    public String toString() {
        String ret = "[ ";
        for (int i = 0; i < lista.size(); i++) {
            ret += lista.get(i).dajIlość() + " ";
        }
        return ret + "]\n";
    }
}
