package Objekty;


import Entita.Zakaznik;
import Predmety.CD;
import Predmety.Casopis;
import Predmety.Farba;
import Predmety.Kniha;
import GUI.Frame;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Start {
    public Zakaznik zakaznik;
    public Pozicovna pozicovna;


    public Zakaznik getZakaznik() {
        return zakaznik;
    }
    public Pozicovna getPozicovna(){
        return pozicovna;
    }

    //Vytvorim zakaznika , pozicovnu
    public void inicializuj(){
        zakaznik = new Zakaznik();
        pozicovna = new Pozicovna("Altex");
        pozicovna.vytvorPozicovnu(10,10,5);

    }
    //Metoda naèíta knihy z txt dokumentu
    public void nacitajKnihyzTXT(){
        // Do file pathu si vložime cestu k našemu suboru alebo si to vlozim do priecinku a do pathu dam len nazov suboru
        String filePath = "D:\\Kurz\\Macrosoft\\Projekt_10_Swing\\src\\Texty\\knihy.txt";
        Path path = Paths.get(filePath);


        try (BufferedReader reader = new BufferedReader(new FileReader(path.toString()))){
            String line;
            while ((line = reader.readLine()) != null){
                // split funkcia rozkúskuje riadok podla toho èo zadám , v tomto prípade ";"
                String[] atributy = line.split(";");
                //Do premennych si nahádžem hodnoty tam kde treba ich aj preparsujem  pretože mi prichádzaju ako string
                String nazov = atributy[0];
                double cena = Double.parseDouble(atributy[1]);
                String farba = atributy[2];
                Farba pomFarba;
                int pocetStran = Integer.parseInt(atributy[3]);
                String ISBN = atributy[4];
                String autor = atributy[5];
                //switch na rozpoznanie farby
                switch (farba){
                    case "BIELA":
                        pomFarba = Farba.BIELA;
                        break;
                    case "CERVENA":
                        pomFarba = Farba.CERVENA;
                        break;
                    case "MODRA":
                        pomFarba = Farba.MODRA;
                        break;
                    case "ZLTA":
                        pomFarba = Farba.ZLTA;
                        break;
                    default:
                        pomFarba = Farba.PRIESVITNA;
                }
                // Vytvorím novu knihu s hodnotami ktore som ziskal z txt dokumentu a pridam knihu do uloziska
                Kniha novaKniha = new Kniha(nazov , cena , pomFarba , ISBN , autor , pocetStran);
                pozicovna.pridajKnihu(novaKniha);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    // rovnaký postup ako kniha
    public void nacitajCasopisyZTXT(){
        String filePath = "D:\\Kurz\\Macrosoft\\Projekt_10_Swing\\src\\Texty\\casopisy.txt";
        Path path = Paths.get(filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(path.toString()))){
            String line;
            while((line = reader.readLine())!= null)
            {
                //Zem a vek;5.99;BIELA;20;CA123456;Nezavisle spravodajstvo = line
                String[] atributy = line.split(";");

                String nazov = atributy[0];
                double cena = Double.parseDouble(atributy[1]);
                String farba = atributy[2]; //pomocna pomFarba typu enum FARBA - zo stringu si musime pomocou switch zmenit premennu na enum
                Farba pomFarba;
                int pocetStran = Integer.parseInt(atributy[3]);
                String ISBN = atributy[4];
                String autor = atributy[5];

                //farba.equals("CERVENA") => pomFarba = Predmety.Farba.CERVENA;

                //switch na rozpoznanie farby z nacitaneho atributu STRING -> ENUM FARBA
                switch (farba)
                {
                    case "BIELA" :
                        pomFarba = Farba.BIELA;
                        break;
                    case "CERVENA":
                        pomFarba = Farba.CERVENA;
                        break;
                    case "MODRA":
                        pomFarba = Farba.MODRA;
                    case "ZLTA":
                        pomFarba = Farba.ZLTA;
                        break;
                    default:
                        pomFarba = Farba.PRIESVITNA;
                        break;
                }

                Casopis casopis = new Casopis(nazov,cena,pomFarba,pocetStran);
                pozicovna.pridajCasopis(casopis);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    //Rovnaký postup ako predošle metody
    public void nacitajCDzTXT(){
        //cesta k txt suboru v nasom pocitaci, kde sa nachadza subor odkial budeme citat knihy
        String filePath = "D:\\Kurz\\Macrosoft\\Projekt_10_Swing\\src\\Texty\\cds.txt";
        Path path = Paths.get(filePath);

        //String fileFromSrc = "knihy.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toString()))){
            String line;
            while((line = reader.readLine())!= null)
            {
                //Atomove navyky;19.99;BIELA;308;SK21385468979;James Cameron = line
                String[] atributy = line.split(";");

                String nazov = atributy[0];
                double cena = Double.parseDouble(atributy[1]);
                String farba = atributy[2];
                //pomocna pomFarba typu enum FARBA
                Farba pomFarba;
                double dlzkaNahravky  = Double.parseDouble(atributy[3]);
                String serioveCislo  = atributy[4];
                String umelec = atributy[5];

                //farba.equals("CERVENA") => pomFarba = Predmety.Farba.CERVENA;

                //switch na rozpoznanie farby z nacitaneho atributu STRING -> ENUM FARBA
                switch (farba)
                {
                    case "BIELA" :
                        pomFarba = Farba.BIELA;
                        break;
                    case "CERVENA":
                        pomFarba = Farba.CERVENA;
                        break;
                    case "MODRA":
                        pomFarba = Farba.MODRA;
                    case "ZLTA":
                        pomFarba = Farba.ZLTA;
                        break;
                    default:
                        pomFarba = Farba.PRIESVITNA;
                        break;
                }

                CD cd = new CD(nazov,cena,pomFarba,dlzkaNahravky,serioveCislo,umelec);
                pozicovna.pridajCD(cd);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    //Metoda pre zapis vypoziciek do txt dokumentu, metodu zavolam na konci programu pri ukonèení
    //Metoda je skopirovana z projektu
    public void zapisVypozickydoTXT()
    {
        String fileName = "D:\\Kurz\\Macrosoft\\Projekt_10_Swing\\src\\Texty\\vypozicky.txt";
        Path path = Paths.get(fileName);

        /*
        kniznica bufferwriter zapisuje znaky do suboru ktory sme jej definovali v konstruktore
        v nasom pripade ideme zapisovat do txt suboru vypozicky.txt, ktory keby sme nemali vytvoreny
        tak nam ho fileWriter vytvori sam v umiestneni projektu
         */
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString()))){
            //pomocou metody write zapisujem string (retazec slov) do suboru
            writer.write(this.zakaznik.getMeno() + " " + this.zakaznik.getPriezvisko()+ " VYPOZICKY:\n");
            //VypoznicnyPredmet[] vypozicky = this.zakaznik.getVypozicky()
            //pomocna na zratenie celkovej sumy vypozicanych predmetov
            double celkovaCenaVzpoziciek = 0;
            writer.write("Zákazník " + getZakaznik().getMeno() + " " + getZakaznik().getPriezvisko() +
                    " má zapožièané následovné predmety : \n");
            //prechadzam pole zakaznikovych vypoziciek a kontrolujem ci nie je prvok v poli null
            for (int i =0; i < this.zakaznik.getVypozicky().length ; i++){
                if (this.zakaznik.getVypozicky()[i] != null) {
                    //ak sa tam nachadza nejaky predmet tak si necham vypisat jeho index, nazov a cenu
                    writer.write("[" + i + "] " + this.zakaznik.getVypozicky()[i].getNazov() + " cena: " +
                            this.zakaznik.getVypozicky()[i].getCena() + " €\n");
                    //pripocitam tuto cenu jedneho predmetu k celkovej cene
                    celkovaCenaVzpoziciek += this.zakaznik.getVypozicky()[i].getCena();
                }

                //po prehladani a vypisani pola este zapisem do suboru aj vyslednu cenu
                // metoda je skopirovana z projektu

            }
            writer.write("\n CELKOVA SUMA VYPOZICIEK: " + celkovaCenaVzpoziciek + " €");
            //kontrola na konzole ci sa mi podarilo uspesne zapisat do suboru
            System.out.printf("uspesne zapisane do suboru");

        }catch  (IOException e){
            System.out.println("Vyskytla sa chyba pri zapisovani do suboru " + e.getMessage());
        }
    }
    //V main si vytvorim Start start a zavolam tuto metodu ktorá mi vykoná potrebne metody pre chod programu
    public void startPozicovna(){
            //inicializujem pozicovnu a dalsimy metodami pridam vsetky predmety do uloziska
            this.inicializuj();
            nacitajKnihyzTXT();
            nacitajCasopisyZTXT();
            nacitajCDzTXT();
            //Vytvorím okno
            Frame registracia = new Frame(this);
    }
}
