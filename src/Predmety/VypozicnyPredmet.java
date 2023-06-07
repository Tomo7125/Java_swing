package Predmety;

public abstract class VypozicnyPredmet {

    protected String nazov;
    protected double cena;
    protected Farba farba;
    private boolean zapozicany = false;

    public VypozicnyPredmet() {
    }

    /**
     Na zaËiatku je konötruktor s parametramy nazov , cena a farba. Hodnota zapoûiËany je od zaËiatku nastavena na false
     œalej tu mame len geteri a seteri pre globalne premenne
 */
    public VypozicnyPredmet(String nazov, double cena, Farba farba) {
        this.nazov = nazov;
        this.cena = cena;
        this.farba = farba;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public double getCena() {
        return cena;
    }

    public boolean isZapozicany() {
        return zapozicany;
    }

    public void setZapozicany(boolean zapozicany) {
        this.zapozicany = zapozicany;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Farba getFarba() {
        return farba;
    }

    public void setFarba(Farba farba) {
        this.farba = farba;
    }

    /**
    Tato metoda len zmeni hodnotu Ëi je predmet zapoûiËany na true
    Metodu je potrebne aplikovat na konkretny predmet
     */
    public boolean zapozicajPredmet(){
        this.setZapozicany(true);

        return true;
    }

    /**
    Tato metoda zmeni hodnotu predmetu na false takûe je predmet znovu dostupny taktieû je tuto metodu potrebne aplikovaù na konkretny predmet
     */
    public void vratPredmet(){
        this.setZapozicany(false);
    }



}
