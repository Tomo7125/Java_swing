package Predmety;

public class Casopis extends VypozicnyPredmet {

    /**
    Na zaèiatku mame globalnu premennu
    Nižšie najdeme konštruktor s parametrami ktore dedi od rodièa a prida sa pocet stran
    Nižšie mame geter a seter pre pocet stran
     */
    private int pocetStran;

    public Casopis(String nazov, double cena, Farba farba, int pocetStran) {
        super(nazov, cena, farba);
        this.pocetStran = pocetStran;
    }

    public int getPocetStran() {
        return pocetStran;
    }

    public void setPocetStran(int pocetStran) {
        this.pocetStran = pocetStran;
    }
}
