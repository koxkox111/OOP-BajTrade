package bajtTrade.agent;

import bajtTrade.zasoby.Zasoby;

public abstract class Spekulant extends Agent implements ISpekulant {
    // strategia

    public Spekulant(int id, Zasoby zasoby) {
        super(id, zasoby);
    }
}
