package Entita;

import Objekty.Pozicovna;
import Predmety.VypozicnyPredmet;

public class Zakaznik {

    private String meno;

    //Konštruktor zákaznika
    public Zakaznik(String meno, String priezvisko) {
        this.meno = meno;
        this.priezvisko = priezvisko;
    }

    private String priezvisko;
    private VypozicnyPredmet[] vypozicky;

    private final int maxPocetVypoziciek = 5;

    /** Konštruktor pre zakaznika ktorý vytvorí pole výpožièky s dlžkou pola maxPocetVypoziciek (5) */
    public Zakaznik() {
        vypozicky = new VypozicnyPredmet[maxPocetVypoziciek];
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public VypozicnyPredmet[] getVypozicky() {
        return vypozicky;
    }

    public void setVypozicky(VypozicnyPredmet[] vypozicky) {
        this.vypozicky = vypozicky;
    }

    public int getMaxPocetVypoziciek() {
        return maxPocetVypoziciek;
    }

    // Na zaèiatku overím èi nejde do metódy null
    public boolean pozicajSiPredmet(VypozicnyPredmet predmet){
        if (predmet != null){
            //Zistím èi nepresiahol zakaznik poèet výpožièiek
            if (getPocetVypoziciek() < maxPocetVypoziciek) {
                // Prechádzam v cykle for výpožièky
                for (int i = 0 ; i < vypozicky.length ; i++) {
                    // Ak sa zhoduje predmet s predmetom vo výpožièkach tak už je vypožièaný
                    if (vypozicky[i] == predmet){
                        System.out.println("Predmet už je vypožièaný");
                    }
                    // Ak vypožièaný nieje dostane sa program sem a overi èí je pole prazdne ak ano vloží doò predmet
                    if (vypozicky[i] == null){
                        vypozicky[i] = predmet;
                        System.out.println("Predmet bol zapožièaný");
                        return true;
                    }
                }
            }else System.out.println("Poèet výpožièiek bol presihnutý");
        }else System.out.println("Predmet neexistuje");
        return false;
    }

    //Metoda prechadza v cykle for vypozièky a pripoèitava do pocet +1 ak je na poli nejaky predmet.
    //Na konci vrati int pocet ktoré reprezentuje pocet predmetov v poli
    public int getPocetVypoziciek(){
        int pocet = 0;
        for (int i = 0 ; i < vypozicky.length ; i++){
            if (vypozicky[i] != null){
                pocet++;
            }
        }
        return pocet;
    }
    // testovacia metoda ktora vypise vypozicky používal som pri písani kodu ako pomôcku èi funguju bloky kodov
    public void vypisVypozicky(){
        boolean jePrazdne = true;

        // V cykle for prejdem vypozicky a zisujem èi je tam nejaký predmet ak ano tak jePrazdne sa zmeni na true
        for (int i = 0; i < vypozicky.length; i++) {
            if (vypozicky[i] != null) {
                jePrazdne = false;
                break;
            }
        }
        //Ak je hodnota jePrazdne false ( v poli sa nenachadzaju predmety) vypíše sa že je pole prazdne
        if (jePrazdne){
            System.out.println("Aktuálne zákaznik nemá vypožièané niè");
            // inak sa vykoná nasledujuci vípis  výpožièiek  vo formáte :
            // Index ; Predmet ; Nazov ; Cena ; Farba
        }else {
            System.out.println("Zakaznik ma aktualne vypožièané nasledovné predmety :");
            for (int i = 0 ; i < vypozicky.length ; i++){
                if (vypozicky[i] != null){
                    VypozicnyPredmet predmet = vypozicky[i];
                    System.out.println("[" + (i) + "]" + "Predmet :" + predmet.getClass().getSimpleName() +
                            ", Nazov : " + predmet.getNazov() + " Cena: " + predmet.getCena() + " Farba : "  + predmet.getFarba());
                }
            }

        }
    }
    // Na vstupe mi pride predmet , potom v cykle for porovnávam tento predmet s predmetom v poli
    // Keï sa predmety zhoduju tak predmet vo vypozickach dostane hodnotu null takže je v podstate zmazaný
    public boolean vratPredmet(VypozicnyPredmet predmet){
        for (int i = 0 ; i < vypozicky.length ; i++){
            if (vypozicky[i] == predmet){
                vypozicky[i] = null;
            }
        }
        return true;
    }
    //Vracia predmet na indexe v poli výpožièky
    public VypozicnyPredmet getPredmetNaIndexe(int index){
        return vypozicky[index];
    }

}
