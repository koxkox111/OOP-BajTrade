package bajtTrade.agent;

import bajtTrade.zasoby.Zasoby;

public abstract class Agent implements IAgent {
    protected int id;
    protected Zasoby zasoby;

    public Agent(int id, Zasoby zasoby) {
        this.id = id;
        this.zasoby = zasoby;
    }

    public Zasoby getZasoby() {
        return zasoby;
    }

    public int getId() {
        return id;
    }
}
