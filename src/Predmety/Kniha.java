package Predmety;

public class Kniha extends VypozicnyPredmet {

    /**
    Na zaèiatku mame globalnu premennu ISBN ako identifikator knihy
    Konštruktor ktory zavola parametre rodièa a pridda ISBN
    geteri a seteri pre všetky globalne premenne vratane tych od rodièa
     */
    private String ISBN;

    public Kniha(String nazov, double cena, Farba farba, String ISBN, String autor, int pocetStran) {
        super(nazov, cena, farba);
        this.ISBN = ISBN;
        this.autor = autor;
        this.pocetStran = pocetStran;
    }

    private String autor;
    private int pocetStran;

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPocetStran() {
        return pocetStran;
    }

    public void setPocetStran(int pocetStran) {
        this.pocetStran = pocetStran;
    }
}
