package GUI;

import Objekty.Start;
import Predmety.VypozicnyPredmet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class vratPredet implements ActionListener {
    private DefaultTableModel tableModel;
    private JTable tablePredmety;
    private JPanel vratPredmetPan;
    private JButton vratPredmetButton;
    private JButton menuButton;
    private JLabel vratJLabel;
    Frame frame;
    Start start;

    //Kon�truktor
    public vratPredet(Frame frame, Start start) {
        this.frame = frame;
        this.start = start;
        menuButton.addActionListener(this);
        vratPredmetButton.addActionListener(this);
        vytvorModel();
        vypisPredmety();
    }

    //Vytv�ra model pre tabulku
    public void vytvorModel(){
        tableModel = new DefaultTableModel();
        tableModel.addColumn("index");
        tableModel.addColumn("nazov");
        tableModel.addColumn("cena");
        tablePredmety.setModel(tableModel);
    }

    //Vyp�e predmety z v�po�i�iek do tabulky
    public void vypisPredmety(){
        tableModel.setRowCount(0);

        // Pomocn� index
        int index = 0;
        //Prech�dzam v�po�i�ky a vkladam ich do objektu
        for (Object objekt : this.start.getZakaznik().getVypozicky()) {
            if (objekt != null) {
                //Overim �i nieje objekt null a potom nasetujem potrebne data
                String nazov = ((VypozicnyPredmet) objekt).getNazov();
                double cena = ((VypozicnyPredmet) objekt).getCena();
                // Prid�me nov� riadok do tabu�ky
                tableModel.addRow(new Object[]{index, nazov, cena});
                index++;
            }
        }
    }

    //Metoda vr�ti ozna�en� predmet
    public void vratPredmet(int select){
        //Do nazvu ulo��m nazov z riadku ktor� je ozna�en� (n�zov sa nachadza na select(vybran� riadok) a index 1 je stlpec s nazvami)
        String nazov = tablePredmety.getValueAt(select,1).toString();
        //V cykle prechadzam zoznam predmetov
        for (VypozicnyPredmet v : start.getPozicovna().getZoznamPredmetov()){
                if (v != null){
                    // tu e�te kontrolujem �i predmet nieje null a potom vkladam nazov predmetu do stringu predmet
                String predmet  = v.getNazov();
                //Porovn�vam �i je nazov predmetu ktor� chcem vrati� rovnak� ako nazov predmetu v po�i�ovni
                if (nazov == predmet) {
                    // Ak sa predmety zhoduj� tak zavolam metodu ktora v zakaznikovi zma�e predmet z vypo�i�iek
                    // Nasledne zavolam metodu ktora nasetuje v po�i�ovni na predmet hodnotu false �e nieje zapo�i�an�
                    start.getZakaznik().vratPredmet(v);
                    start.getPozicovna().vratPredmet(v);
                }
            }
        }
    }

    //Tu sledujem na �o bolo kliknute a podla toho vykon�vam akcie
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vratPredmetButton)){
            int selectedRow = tablePredmety.getSelectedRow();
            //Metoda vrati predmet z ozna�eneho riadku
            vratPredmet(selectedRow);
            //Metoda znovu vyp�e tabulku a tabulka je tak v�dy aktualna
            vypisPredmety();
        }
        //Ak klikol zakaznik na menu tak sa zmeni context na menu
        if (e.getSource().equals(menuButton)){
            frame.setContext(new Menu(frame,start).getContent());
        }
    }

    public JPanel setContent(){return this.vratPredmetPan;}

}
