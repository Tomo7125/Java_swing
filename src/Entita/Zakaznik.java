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

    /** Konštruktor pre zakaznika ktorý vytvorí pole výpožičky s dlžkou pola maxPocetVypoziciek (5) */
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

    // Na začiatku overím či nejde do metódy null
    public boolean pozicajSiPredmet(VypozicnyPredmet predmet){
        if (predmet != null){
            //Zistím či nepresiahol zakaznik počet výpožičiek
            if (getPocetVypoziciek() < maxPocetVypoziciek) {
                // Prechádzam v cykle for výpožičky
                for (int i = 0 ; i < vypozicky.length ; i++) {
                    // Ak sa zhoduje predmet s predmetom vo výpožičkach tak už je vypožičaný
                    if (vypozicky[i] == predmet){
                        System.out.println("Predmet už je vypožičaný");
                    }
                    // Ak vypožičaný nieje dostane sa program sem a overi čí je pole prazdne ak ano vloží doň predmet
                    if (vypozicky[i] == null){
                        vypozicky[i] = predmet;
                        System.out.println("Predmet bol zapožičaný");
                        return true;
                    }
                }
            }else System.out.println("Počet výpožičiek bol presihnutý");
        }else System.out.println("Predmet neexistuje");
        return false;
    }

    //Metoda prechadza v cykle for vypozičky a pripočitava do pocet +1 ak je na poli nejaky predmet.
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
    // testovacia metoda ktora vypise vypozicky používal som pri písani kodu ako pomôcku či funguju bloky kodov
    public void vypisVypozicky(){
        boolean jePrazdne = true;

        // V cykle for prejdem vypozicky a zisťujem či je tam nejaký predmet ak ano tak jePrazdne sa zmeni na true
        for (int i = 0; i < vypozicky.length; i++) {
            if (vypozicky[i] != null) {
                jePrazdne = false;
                break;
            }
        }
        //Ak je hodnota jePrazdne true ( v poli sa nenachadzaju predmety) vypíše sa že je pole prazdne
        if (jePrazdne){
            System.out.println("Aktuálne zákaznik nemá vypožičané nič");
            // inak sa vykoná nasledujuci vípis  výpožičiek  vo formáte :
            // Index ; Predmet ; Nazov ; Cena ; Farba
        }else {
            System.out.println("Zakaznik ma aktualne vypožičané nasledovné predmety :");
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
    // Keď sa predmety zhoduju tak predmet vo vypozickach dostane hodnotu null takže je v podstate zmazaný
    public boolean vratPredmet(VypozicnyPredmet predmet){
        for (int i = 0 ; i < vypozicky.length ; i++){
            if (vypozicky[i] == predmet){
                vypozicky[i] = null;
            }
        }
        return true;
    }
    //Vracia predmet na indexe v poli výpožičky
    public VypozicnyPredmet getPredmetNaIndexe(int index){
        return vypozicky[index];
    }

}
