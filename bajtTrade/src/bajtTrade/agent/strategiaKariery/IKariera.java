package bajtTrade.agent.strategiaKariery;

public interface IKariera {
    int podajZawód();

    void zmieńZawód(int zawód);

    int podajBonus(int zawód);

    boolean czyZmiana(int dzień);

    int zmianaNaCo();

    public int podajPoziom(int zawód);
}
