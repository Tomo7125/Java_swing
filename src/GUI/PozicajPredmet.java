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

    //Kon�truktor pre PozicajPredmet ktory ma aj metody pre tabulku
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
        // Vyma�eme v�etky riadky z modelu tabu�ky
        tableModel.setRowCount(0);

        // Pomocn� index
        int index = 0;
        //For each prechadza zoznam predmetov
        for (Object objekt : this.start.getPozicovna().getZoznamPredmetov()) {
            // Ak predmet nieje null tak vlo�� do premennych potebn� parametre ( Je potrebne aj pretypovanie )
            if (objekt != null) {
                String nazov = ((VypozicnyPredmet) objekt).getNazov();
                double cena = ((VypozicnyPredmet) objekt).getCena();
                boolean zapozicany = ((VypozicnyPredmet) objekt).isZapozicany();

                // Prid�me nov� riadok do tabu�ky
                tableModel.addRow(new Object[]{index, nazov, cena, zapozicany});
                index++;
            }
        }
    }

    // Sledovanie akcii
    @Override
    public void actionPerformed(ActionEvent e) {
        // Ak klikol u�ivatel na po�i�aj tak sa vykonaj� nasledovne riadky
        if (e.getSource().equals(butPozicaj)){
            //Vezmem si int �islo riadku kde je kliknute
            int selectedRow = tablePredmety.getSelectedRow();
            //Skontrolujem �i nepresiahol po�et v�po�i�iek
            if (start.zakaznik.getPocetVypoziciek() < start.zakaznik.getMaxPocetVypoziciek()){
                // Po�i�iam predmet , je potrebne zmeni� parametre aj vo vypo�i�kach aj v ulo�isku
                start.pozicovna.pozicajPredmetZakaznikovi((selectedRow));
                start.zakaznik.pozicajSiPredmet(start.pozicovna.getPredmetNaIndexe(selectedRow));
            }
            //Znovu vyp�em predmety , zaru�ujem si tak �e je tabulka neustale aktualizovan�
            vypisPredmetyDoTabulky();
        }
        //Ak klikol u�ivatel na menu tak nasetujem context zase na menu
        if (e.getSource().equals(butMenu)){
            frame.setContext(new Menu(frame , start).getContent());
        }
    }

    public JPanel getContent(){
        return this.pozicajPredmet;
    }


}
