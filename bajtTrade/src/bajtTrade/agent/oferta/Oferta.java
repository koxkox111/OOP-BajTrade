package bajtTrade.agent.oferta;

import bajtTrade.agent.IAgent;

public class Oferta {
    protected int ilość;
    protected int produkt;
    protected IAgent agent;
    protected int poziom;

    public Oferta(int ilość, int produkt, IAgent agent) {
        this.ilość = ilość;
        this.produkt = produkt;
        this.agent = agent;
        this.poziom = 1;
    }

    public Oferta(int ilość, int produkt, int poziom, IAgent agent) {
        this.agent = agent;
        this.poziom = poziom;
        this.ilość = ilość;
        this.produkt = produkt;
    }

    public int getIlość() {
        return ilość;
    }

    public void zmieńIlość(int x) {
        ilość -= x;
    }

    public int getProdukt() {
        return produkt;
    }

    public IAgent getAgent() {
        return agent;
    }

    public double getCena() {
        return 0;
    }

    public int getPoziom() {
        return poziom;
    }
}
