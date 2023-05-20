package bajtTrade.giełda;

import bajtTrade.agent.Robotnik;
import bajtTrade.agent.Spekulant;
import bajtTrade.zasoby.ZasobyGry;

public class Kapitalistyczna extends Giełda {

    public Kapitalistyczna(int długość, int kara_za_brak_ubrań, ZasobyGry[] średniaCena,
            ZasobyGry[] występowanie, Robotnik[] robotnik, Spekulant[] spekulant) {
        super(długość, kara_za_brak_ubrań, średniaCena, występowanie, robotnik, spekulant);
        obrot = 2;

    }

}
