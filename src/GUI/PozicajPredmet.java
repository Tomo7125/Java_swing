package GUI;

import Objekty.Start;
import Predmety.VypozicnyPredmet;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PozicajPredmet implements ActionListener {
    private DefaultTableModel tableModel;
    private JPanel pozicajPredmet;
    private JTable tablePredmety;
    private JButton butPozicaj;
    private JButton butMenu;
    Frame frame;
    Start start;

    //Konštruktor pre PozicajPredmet ktory ma aj metody pre tabulku
    public PozicajPredmet(Frame frame, Start start) {
        this.frame = frame;
        this.start = start;
        butPozicaj.addActionListener(this);
        butMenu.addActionListener(this);
        vytvorModelTabulky();
        vypisPredmetyDoTabulky();
    }

    //Metoda vytvara stlpce a  potom prida tento model do tablePredmety( tabulky)
    private void vytvorModelTabulky() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("index");
        tableModel.addColumn("nazov");
        tableModel.addColumn("cena");
        tableModel.addColumn("je zapozicany?");
        tablePredmety.setModel(tableModel);
    }

    //Metoda pre odoslanie predmetov do tabulky
    private void vypisPredmetyDoTabulky() {
        // Vymažeme všetky riadky z modelu tabu¾ky
        tableModel.setRowCount(0);

        // Pomocný index
        int index = 0;
        //For each prechadza zoznam predmetov
        for (Object objekt : this.start.getPozicovna().getZoznamPredmetov()) {
            // Ak predmet nieje null tak vloží do premennych potebné parametre ( Je potrebne aj pretypovanie )
            if (objekt != null) {
                String nazov = ((VypozicnyPredmet) objekt).getNazov();
                double cena = ((VypozicnyPredmet) objekt).getCena();
                boolean zapozicany = ((VypozicnyPredmet) objekt).isZapozicany();

                // Pridáme nový riadok do tabu¾ky
                tableModel.addRow(new Object[]{index, nazov, cena, zapozicany});
                index++;
            }
        }
    }

    // Sledovanie akcii
    @Override
    public void actionPerformed(ActionEvent e) {
        // Ak klikol uživatel na požièaj tak sa vykonajú nasledovne riadky
        if (e.getSource().equals(butPozicaj)){
            //Vezmem si int èislo riadku kde je kliknute
            int selectedRow = tablePredmety.getSelectedRow();
            //Skontrolujem èi nepresiahol poèet výpožièiek
            if (start.zakaznik.getPocetVypoziciek() < start.zakaznik.getMaxPocetVypoziciek()){
                // Požièiam predmet , je potrebne zmeni parametre aj vo vypožièkach aj v uložisku
                start.pozicovna.pozicajPredmetZakaznikovi((selectedRow));
                start.zakaznik.pozicajSiPredmet(start.pozicovna.getPredmetNaIndexe(selectedRow));
            }
            //Znovu vypíšem predmety , zaruèujem si tak že je tabulka neustale aktualizovaná
            vypisPredmetyDoTabulky();
        }
        //Ak klikol uživatel na menu tak nasetujem context zase na menu
        if (e.getSource().equals(butMenu)){
            frame.setContext(new Menu(frame , start).getContent());
        }
    }

    public JPanel getContent(){
        return this.pozicajPredmet;
    }


}
