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

    //Konötruktor
    public vratPredet(Frame frame, Start start) {
        this.frame = frame;
        this.start = start;
        menuButton.addActionListener(this);
        vratPredmetButton.addActionListener(this);
        vytvorModel();
        vypisPredmety();
    }

    //Vytv·ra model pre tabulku
    public void vytvorModel(){
        tableModel = new DefaultTableModel();
        tableModel.addColumn("index");
        tableModel.addColumn("nazov");
        tableModel.addColumn("cena");
        tablePredmety.setModel(tableModel);
    }

    //VypÌöe predmety z v˝poûiËiek do tabulky
    public void vypisPredmety(){
        tableModel.setRowCount(0);

        // Pomocn˝ index
        int index = 0;
        //Prech·dzam v˝poûiËky a vkladam ich do objektu
        for (Object objekt : this.start.getZakaznik().getVypozicky()) {
            if (objekt != null) {
                //Overim Ëi nieje objekt null a potom nasetujem potrebne data
                String nazov = ((VypozicnyPredmet) objekt).getNazov();
                double cena = ((VypozicnyPredmet) objekt).getCena();
                // Prid·me nov˝ riadok do tabuæky
                tableModel.addRow(new Object[]{index, nazov, cena});
                index++;
            }
        }
    }

    //Metoda vr·ti oznaËen˝ predmet
    public void vratPredmet(int select){
        //Do nazvu uloûÌm nazov z riadku ktor˝ je oznaËen˝ (n·zov sa nachadza na select(vybran˝ riadok) a index 1 je stlpec s nazvami)
        String nazov = tablePredmety.getValueAt(select,1).toString();
        //V cykle prechadzam zoznam predmetov
        for (VypozicnyPredmet v : start.getPozicovna().getZoznamPredmetov()){
                if (v != null){
                    // tu eöte kontrolujem Ëi predmet nieje null a potom vkladam nazov predmetu do stringu predmet
                String predmet  = v.getNazov();
                //Porovn·vam Ëi je nazov predmetu ktor˝ chcem vratiù rovnak˝ ako nazov predmetu v poûiËovni
                if (nazov == predmet) {
                    // Ak sa predmety zhoduj˙ tak zavolam metodu ktora v zakaznikovi zmaûe predmet z vypoûiËiek
                    // Nasledne zavolam metodu ktora nasetuje v poûiËovni na predmet hodnotu false ûe nieje zapoûiËan˝
                    start.getZakaznik().vratPredmet(v);
                    start.getPozicovna().vratPredmet(v);
                }
            }
        }
    }

    //Tu sledujem na Ëo bolo kliknute a podla toho vykon·vam akcie
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vratPredmetButton)){
            int selectedRow = tablePredmety.getSelectedRow();
            //Metoda vrati predmet z oznaËeneho riadku
            vratPredmet(selectedRow);
            //Metoda znovu vypÌöe tabulku a tabulka je tak vûdy aktualna
            vypisPredmety();
        }
        //Ak klikol zakaznik na menu tak sa zmeni context na menu
        if (e.getSource().equals(menuButton)){
            frame.setContext(new Menu(frame,start).getContent());
        }
    }

    public JPanel setContent(){return this.vratPredmetPan;}

}
