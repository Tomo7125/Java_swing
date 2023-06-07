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

    // Kon�truktor
    public Vypozicky(Frame frame, Start start) {
        this.frame = frame;
        this.start = start;
        butMenu.addActionListener(this);
        vypisVypozicky();
    }
    //Metoda pre v�pis vypo�i�iek
    public void vypisVypozicky(){
        //Nastavim hore meno a priezvisko zakaznika aby bolo vypisane ka�demu zakaznikovi jeho meno
        vypozickyZakaznikJLab.setText("V�po�i�ky pre z�kaznika : " +
                start.getZakaznik().getMeno() + " " + start.getZakaznik().getPriezvisko());

        //Vytvor�m model tabulky
        DefaultTableModel model = (DefaultTableModel) tabVypozicky.getModel();
        model.setRowCount(0);
        model.addColumn("index");
        model.addColumn("nazov");
        model.addColumn("cena");

        int index = 0;
        double cenaVypoziciek = 0;
            for (Object objekt : this.start.getZakaznik().getVypozicky()) {
                //Prech�dzam pole vypo�i�iek a potom overim �i nieje hodnota null
                if (objekt != null) {
                    // Hodnoty pretipujem a vlo�im do premennych
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
        String form�tovaneCislo = decimalFormat.format(cenaVypoziciek);
        model.addRow(new Object[]{null, "Cena spolu : " , form�tovaneCislo});
        //Vlo�im model do tabulky
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
