package GUI;

import Objekty.Start;
import Predmety.VypozicnyPredmet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VypisPredmetov implements ActionListener {
    private JTable tabPredmety;
    private JLabel nadpis;
    private JButton butOK;
    private JPanel vypisPredmety;
    Frame frame;
    Start start;

    //Kon�truktor pre vypisPredmetov
    public VypisPredmetov(Frame frame, Start start) {
        this.frame = frame;
        this.start = start;
        butOK.addActionListener(this);
        vypisPredmetyDoTabulky();
    }

    //Metoda pre vypis predmetov
    private void vypisPredmetyDoTabulky() {
        DefaultTableModel model = (DefaultTableModel) tabPredmety.getModel();

        // vytvoren� mame default model , tu mu nasetujem row na 0
        model.setRowCount(0);

        //Vytvor�m model tabulky
        model.addColumn("index");
        model.addColumn("nazov");
        model.addColumn("cena");
        model.addColumn("je zapozicany?");
        model.addRow(new Object[]{"Index" , "N�zov" , "Cena" , "Je zapo�i�an� ?"});
        TableColumn column = tabPredmety.getColumnModel().getColumn(0); // Z�skanie st�pca pod�a indexu (v tomto pr�pade index 0)
        column.setPreferredWidth(30); // nastav� ��rku prv�ho stlpca

        //pomocn� index nam rata po�et predmetov po ka�dom cykle ju nav��ime
        int index = 0;
        for (Object objekt : this.start.getPozicovna().getZoznamPredmetov()) {

            if (objekt != null) {
                //Ak objekt nieje null tak z neho za�nem vy�ahova� data do premennych ( treba aj pretypova� objekt )
                String nazov = ((VypozicnyPredmet) objekt).getNazov();
                double cena = ((VypozicnyPredmet) objekt).getCena();
                boolean zapozicany = ((VypozicnyPredmet) objekt).isZapozicany();

                //pridame novy riadok do tabulky
                model.addRow(new Object[]{index, nazov, cena, zapozicany});
                index++;
            }
        }
        //vezme tabulku a nasetuje model
        tabPredmety.setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(butOK)){
            frame.setContext(new Menu(frame,start).getContent());
        }
    }

    public JPanel getContent(){
        return this.vypisPredmety;
    }
}
