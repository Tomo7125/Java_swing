package Entita;

import Objekty.Pozicovna;
import Predmety.VypozicnyPredmet;

public class Zakaznik {

    private String meno;

    //Kon�truktor z�kaznika
    public Zakaznik(String meno, String priezvisko) {
        this.meno = meno;
        this.priezvisko = priezvisko;
    }

    private String priezvisko;
    private VypozicnyPredmet[] vypozicky;

    private final int maxPocetVypoziciek = 5;

    /** Kon�truktor pre zakaznika ktor� vytvor� pole v�po�i�ky s dl�kou pola maxPocetVypoziciek (5) */
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

    // Na za�iatku over�m �i nejde do met�dy null
    public boolean pozicajSiPredmet(VypozicnyPredmet predmet){
        if (predmet != null){
            //Zist�m �i nepresiahol zakaznik po�et v�po�i�iek
            if (getPocetVypoziciek() < maxPocetVypoziciek) {
                // Prech�dzam v cykle for v�po�i�ky
                for (int i = 0 ; i < vypozicky.length ; i++) {
                    // Ak sa zhoduje predmet s predmetom vo v�po�i�kach tak u� je vypo�i�an�
                    if (vypozicky[i] == predmet){
                        System.out.println("Predmet u� je vypo�i�an�");
                    }
                    // Ak vypo�i�an� nieje dostane sa program sem a overi �� je pole prazdne ak ano vlo�� do� predmet
                    if (vypozicky[i] == null){
                        vypozicky[i] = predmet;
                        System.out.println("Predmet bol zapo�i�an�");
                        return true;
                    }
                }
            }else System.out.println("Po�et v�po�i�iek bol presihnut�");
        }else System.out.println("Predmet neexistuje");
        return false;
    }

    //Metoda prechadza v cykle for vypozi�ky a pripo�itava do pocet +1 ak je na poli nejaky predmet.
    //Na konci vrati int pocet ktor� reprezentuje pocet predmetov v poli
    public int getPocetVypoziciek(){
        int pocet = 0;
        for (int i = 0 ; i < vypozicky.length ; i++){
            if (vypozicky[i] != null){
                pocet++;
            }
        }
        return pocet;
    }
    // testovacia metoda ktora vypise vypozicky pou��val som pri p�sani kodu ako pom�cku �i funguju bloky kodov
    public void vypisVypozicky(){
        boolean jePrazdne = true;

        // V cykle for prejdem vypozicky a zis�ujem �i je tam nejak� predmet ak ano tak jePrazdne sa zmeni na true
        for (int i = 0; i < vypozicky.length; i++) {
            if (vypozicky[i] != null) {
                jePrazdne = false;
                break;
            }
        }
        //Ak je hodnota jePrazdne false ( v poli sa nenachadzaju predmety) vyp�e sa �e je pole prazdne
        if (jePrazdne){
            System.out.println("Aktu�lne z�kaznik nem� vypo�i�an� ni�");
            // inak sa vykon� nasledujuci v�pis  v�po�i�iek  vo form�te :
            // Index ; Predmet ; Nazov ; Cena ; Farba
        }else {
            System.out.println("Zakaznik ma aktualne vypo�i�an� nasledovn� predmety :");
            for (int i = 0 ; i < vypozicky.length ; i++){
                if (vypozicky[i] != null){
                    VypozicnyPredmet predmet = vypozicky[i];
                    System.out.println("[" + (i) + "]" + "Predmet :" + predmet.getClass().getSimpleName() +
                            ", Nazov : " + predmet.getNazov() + " Cena: " + predmet.getCena() + " Farba : "  + predmet.getFarba());
                }
            }

        }
    }
    // Na vstupe mi pride predmet , potom v cykle for porovn�vam tento predmet s predmetom v poli
    // Ke� sa predmety zhoduju tak predmet vo vypozickach dostane hodnotu null tak�e je v podstate zmazan�
    public boolean vratPredmet(VypozicnyPredmet predmet){
        for (int i = 0 ; i < vypozicky.length ; i++){
            if (vypozicky[i] == predmet){
                vypozicky[i] = null;
            }
        }
        return true;
    }
    //Vracia predmet na indexe v poli v�po�i�ky
    public VypozicnyPredmet getPredmetNaIndexe(int index){
        return vypozicky[index];
    }

}
