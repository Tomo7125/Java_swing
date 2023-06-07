package GUI;

import Objekty.Start;
import Predmety.VypozicnyPredmet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Vypozicky implements ActionListener {
    private JPanel vypozickyPanel;
    private JLabel vypozickyZakaznikJLab;
    private JTable tabVypozicky;
    private JButton butMenu;
    Frame frame;
    Start start;

    // Konštruktor
    public Vypozicky(Frame frame, Start start) {
        this.frame = frame;
        this.start = start;
        butMenu.addActionListener(this);
        vypisVypozicky();
    }
    //Metoda pre výpis vypožièiek
    public void vypisVypozicky(){
        //Nastavim hore meno a priezvisko zakaznika aby bolo vypisane každemu zakaznikovi jeho meno
        vypozickyZakaznikJLab.setText("Výpožièky pre zákaznika : " +
                start.getZakaznik().getMeno() + " " + start.getZakaznik().getPriezvisko());

        //Vytvorím model tabulky
        DefaultTableModel model = (DefaultTableModel) tabVypozicky.getModel();
        model.setRowCount(0);
        model.addColumn("index");
        model.addColumn("nazov");
        model.addColumn("cena");

        int index = 0;
        double cenaVypoziciek = 0;
            for (Object objekt : this.start.getZakaznik().getVypozicky()) {
                //Prechádzam pole vypožièiek a potom overim èi nieje hodnota null
                if (objekt != null) {
                    // Hodnoty pretipujem a vložim do premennych
                    String nazov = ((VypozicnyPredmet) objekt).getNazov();
                    double cena = ((VypozicnyPredmet) objekt).getCena();

                    //pridame novy riadok do tabulky
                    model.addRow(new Object[]{index, nazov, cena});
                    index++;
                    //cenaVypoziciek reprezentuje celkovu cenu vypoziciek
                    cenaVypoziciek += cena;
                }
            }
            // Naformatujem si hodnotu aby mala cena len 2 desatinne miesta
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formátovaneCislo = decimalFormat.format(cenaVypoziciek);
        model.addRow(new Object[]{null, "Cena spolu : " , formátovaneCislo});
        //Vložim model do tabulky
        tabVypozicky.setModel(model);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(butMenu)){
            frame.setContext(new Menu(frame,start).getContent());
        }
    }
    public JPanel setContent(){
        return this.vypozickyPanel;
    }
}
