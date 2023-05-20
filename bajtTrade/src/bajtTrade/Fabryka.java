package bajtTrade;

import java.util.Arrays;

import bajtTrade.agent.oferta.Oferta;
import bajtTrade.zasoby.ZasobyGry;

public class Fabryka {
    public static void dodaj(Oferta oferta, Oferta[] oferty) {
        oferty = Arrays.copyOf(oferty, oferty.length + 1);
        oferty[oferty.length - 1] = oferta;
    }

    public static int wklęsła(ZasobyGry[] zasoby, int i) {

        // zakldamay ze chodzi o jedzenie :)
        if (zasoby.length == 1) {
            return 0; // wklesla i wypukla
        } else if (zasoby.length == 2) {
            if (zasoby[1].jedzenieIlość() - zasoby[0].jedzenieIlość() > 0) {
                return 1; // wypukla
            } else if (zasoby[1].jedzenieIlość() - zasoby[0].jedzenieIlość() < 0) {
                return 2; // wklesla
            } else {
                return 0;
            }
        } else {
            double A = zasoby[zasoby.length - 1].jedzenieIlość();
            double B = zasoby[zasoby.length - 2].jedzenieIlość();
            double C = zasoby[zasoby.length - 3].jedzenieIlość();
            if (B - A == C - B)
                return 0;
            else if (B - A < C - B)
                return 1;// wypukla
            else
                return 2;// wklesla
        }

    }

    public static void wyczysc(Oferta[] oferta) {
        oferta = Arrays.copyOf(oferta, 0);
    }

    public static String podajZawód(int zawód, int poziom) {
        if (zawód == 0) {
            return "Rolnik: " + "poziom: " + poziom + "\n";
        } else if (zawód == 3) {
            return "Górnik: " + "poziom: " + poziom + "\n";
        } else if (zawód == 1) {
            return "Rzemieślnik " + "poziom: " + poziom + "\n";

        } else if (zawód == 2) {
            return "Inżynier " + "poziom: " + poziom + "\n";

        } else {
            return "Programista " + "poziom: " + poziom + "\n";

        }
    }
}
