package Objekty;

import Predmety.CD;
import Predmety.Casopis;
import Predmety.Kniha;
import Predmety.VypozicnyPredmet;


public class Pozicovna {
    /** globalna premenna nazov a geter pre nazov. */

    private String nazov;

    public String getNazov() {
        return nazov;
    }

    /**
    Tu vytvarame uloûiöko  / pole typu VypozicanyPredmet
    œalej si uËujeme max pocet pre knihy , CD , casopisy a aj aktualnePocty
     */
    private VypozicnyPredmet[] ulozisko;
    private int maxPocetKnih;
    private int maxPocetCD;
    private int maxPocetCasopisov;
    private int aktualnyPocetKnih;
    private int aktualnyPocetCD;
    private int aktualnyPocetCasopisov;

    /** Konötruktor pre poûiËovÚu */
    public Pozicovna(String nazov) {
        this.nazov = nazov;
    }

    /** Metoda vytvorPoûiËovÚu aplikujeme na vytvorenu poûiËovÚu a priradime jej max poËty pre dane predmety
    / SËitame max poËty a urËime jej touto hodnotou velkosù uloûiska a nastavime aktualne poËty na 0 */
    public void vytvorPozicovnu(int maxPocetKnih , int maxPocetCasopisov , int maxPocetCD){
        this.maxPocetKnih = maxPocetKnih;
        this.maxPocetCasopisov = maxPocetCasopisov;
        this.maxPocetCD = maxPocetCD;
        this.ulozisko = new VypozicnyPredmet[maxPocetCasopisov + maxPocetCD + maxPocetKnih];
        this.aktualnyPocetCD = 0;
        this.aktualnyPocetCasopisov = 0;
        this.aktualnyPocetKnih = 0;
    }

/**
    Metoda ma navratovy typ boolean , v prvej podmienke overim ËÌ je aktualny poËet knÌh menöÌ ako max poËet knih
    ak ·no pokraËujem v bloku
    vytvoril som pomocnu int s nazvom "pom" a priradil som poËet nula pretoûe aj index pola ktory budem prehladavat zaËina nulou
    for each prehladava ulozisko a uklada data do premennej "v"
    v podmienke porovnavam "v" s hodnotou null ( hodnota null mi hovori ûe pole je pr·zdne )
    akon·hle je hodnota null tak podmienka je pravdiva a vch·dzame do vn˙tra kde na uloûisko s indexom "pom" vloûime knihu
    premennu "pom" som navyöoval pri kaûdom prejdeni cyklu for each keÔ hodnota "v" nebola null
    po zapisani novej knihy do uloziska dalsi riadok kodu nav˝öi hodnotu aktualnypocetknih o 1 a potom vr·ti true
    pokial prejde cyklus for each do konca a nenajde volne pole kod zbehne na koniec kde je retur false
 */
    public boolean pridajKnihu(Kniha novaKniha){
        if (aktualnyPocetKnih < maxPocetKnih){
            int pom = 0;
            for (VypozicnyPredmet v : ulozisko){
                if (v == null){
                    ulozisko[pom] = novaKniha;
                    aktualnyPocetKnih++;
                    return true;
                }
                pom++;
            }
        }
        /** Ak vrati false knihu nebolo moûnÈ pridaù z dÙvodu ûe bol presiahnuty maximalny poËet knih
         alebo je uloûisko plne */
        return false;
    }

    /** rovnaky postup ako v popise pridaj knihu */
    public boolean pridajCasopis(Casopis novyCasopis){
        if (aktualnyPocetCasopisov < maxPocetCasopisov){
            int pom =0;
            for (VypozicnyPredmet v : ulozisko){
                if (v == null){
                    ulozisko[pom] = novyCasopis;
                    aktualnyPocetCasopisov++;
                    return true;
                }
                pom++;
            }
        }
        return false;
    }

    /** rovnaky postup ako v popise pridaj knihu */
    public boolean pridajCD(CD noveCD){
        if (aktualnyPocetCD < maxPocetCD){
            int pom = 0;
            for (VypozicnyPredmet v : ulozisko){
                if (v == null){
                    ulozisko[pom] = noveCD;
                    aktualnyPocetCD++;
                    return true;
                }
                pom++;
            }
        }
        return false;
    }
    /**
    Metoda na vstupe prijme ËÌslo index a nasledne v podmienke if skontroluje Ëi na uloûisku s dan˝m indexom nieje null
    Ak platÌ != Ëiûe dane pole nieje null tak pokraËujeme dalej
    Na ulozisko[index] aplikujeme metodu zapoûiËaj predmet
     */
    public boolean pozicajPredmetZakaznikovi(int index){
        if (ulozisko[index] != null){
            ulozisko[index].zapozicajPredmet();
            return true;
        }
        return false;
    }
    /**
    Dosù podobn˝ postup ako v predoölej metode no tu porovnavame vstupny parameter nazov
    V podmienke if skontrolujeme Ëi uloûisko nieje prazdne aby nam nepadol program na NullPointerException
    Nasledne prehladavame v cykle for each pole a ukladame objekty do "v"
    V Ôalöiom if si porovnavame nazov z objektu s nazvom zo vstupneho parametru ak sa zhoduju zavolame metodu zapozicajPredmet
     */
    public boolean pozicajPredmetZakaznikovi(String nazovPredmetu){
        if (ulozisko != null){
            for (VypozicnyPredmet v : ulozisko){
                if (v.getNazov().equals(nazovPredmetu)){
                    v.zapozicajPredmet();
                    return true;
                }
            }
        }
        return false;
    }
    /**
    Novöie verzie javy poznaju aj Arrays.isEmpty() no vzhladom na pouûitie javy 1.8 nieje moûne pouûiù tuto funkciu
    vytvorim preto cyklus ktory overy Ëi su polia prazdne ak ano napÌöe sa mi na konzolu hlaöka ûe ulozisko je prazdne
    funkcia je zbytoËn· vzhladom na to ûe na zaËiatku si inicializujeme kniûnicu ale keby sme mali aplikaciu
    postavenu tak ûe zadavame kniûnicu ruËne mÙûe sa staù ûe bude prazdna
    Niûöie si v cykle for prehladavame uloûisko dal by sa pouûiù aj cyklus for each no lepöie sa mi pracuje s klasickym cyklom for
    tak pouûivam for each len zriedka kym si naÚ zvyknem
    Pokial ulozisko[i] nieje null tak sa mi uloûÌ v dalöiom riadku predmet do pomocnej premennej predmet ktora
    by tam nemusela byù nakolko mÙûem metody aplikovaù aj öt˝lom ulozisko[i].getNazov() atÔ no opeù som bol lenivejöÌ a ulahËil si pisanie
    nakolko je pohodlnejöie len ùuknuù po prvom pismene enter a pokraËovaù metodou
    sout mam nastaveny tak aby mi na zaËiatku napisal Ëislo indexu potom meno classu aby som vedel o aky predmet sa jedna
    nasledne jeho zakladne atrinbuty ako nazov , cena , farba a Ëi je aktualne zapoûiËan˝
     */
    public void vypisAktualneUlozisko(){
        boolean jePrazdne = true;

        for (int i = 0; i < ulozisko.length; i++) {
            if (ulozisko[i] != null) {
                jePrazdne = false;
                break;
            }
        }
   /**     if (Arrays.isEmpty(ulozisko)){   Toto by bolo moûne v novöÌch verziach javy */
        if (jePrazdne){
            System.out.println("⁄loûisko je pr·zdne");
        }else {
            System.out.println("V ˙loûisku sa nach·dzaj˙ n·sledovne predmety :");
            for (int i = 0;  i < ulozisko.length ; i++){
                if (ulozisko[i] != null){
                    VypozicnyPredmet predmet = ulozisko[i];
                    System.out.println("[" + (i) + "]" + " Predmet : " + predmet.getClass().getSimpleName() +
                            " --- N·zov : " + predmet.getNazov() + ", Cena: " + predmet.getCena() + ", Farba : "  + predmet.getFarba() +
                             ", predmet je aktualne " + (predmet.isZapozicany() ? "zapoûiËan˝" : "voln˝ k zapoûiËaniu"));
                }
            }
        }
    }

    /**
    Tato metoda vracia predmet na indexe ktory priöiel ako vstupny parameter navratova hodnota je VypozicanyPredmet
    Sl˙ûi na vybratie predmetu a pracu s nim
     */
    public VypozicnyPredmet getPredmetNaIndexe(int index){

        return this.ulozisko[index];
    }
    /**
    Vstupny parameter je predmet ktory budem vracat
    nasledne si v cykle for prejdem uloûisko a hladam v podmienke if kedy bude predmet v ulozisku rovnak˝ ako predmet na vstupe
    pokial sa bude zhodovaù predmet v uloûisku a predmet na vstupe tak mu nasetujem hodnotu zapozicany na false takûe bude
    opeù dotupn˝
     */
    public void vratPredmet(VypozicnyPredmet predmet){
        if (predmet != null){
        for (int i = 0 ; i < ulozisko.length ; i++) {

            if (ulozisko[i] == predmet) {
                ulozisko[i].setZapozicany(false);
                System.out.println("Predmet bol vr·ten˝");
            }
        }
        }else System.out.println("Pole na indexe je prazdne");
    }
    /**
    Tuto metodu som pouûil ako pomocnu pri rieöeni problemov na switch v niektorom z case:
    vracia len dlzku uloziska nakolko ku nej neviem pristupovat z inych tried bez pouzitia takehoto geteru
     */
    public int getVelkostPozicovne(){
        int i = ulozisko.length;
        return i;
    }


    public VypozicnyPredmet[] getZoznamPredmetov() {
        return ulozisko;
    }
}
