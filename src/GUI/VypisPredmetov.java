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

    //Konötruktor pre vypisPredmetov
    public VypisPredmetov(Frame frame, Start start) {
        this.frame = frame;
        this.start = start;
        butOK.addActionListener(this);
        vypisPredmetyDoTabulky();
    }

    //Metoda pre vypis predmetov
    private void vypisPredmetyDoTabulky() {
        DefaultTableModel model = (DefaultTableModel) tabPredmety.getModel();

        // vytvoren˝ mame default model , tu mu nasetujem row na 0
        model.setRowCount(0);

        //VytvorÌm model tabulky
        model.addColumn("index");
        model.addColumn("nazov");
        model.addColumn("cena");
        model.addColumn("je zapozicany?");
        model.addRow(new Object[]{"Index" , "N·zov" , "Cena" , "Je zapoûiËan˝ ?"});
        TableColumn column = tabPredmety.getColumnModel().getColumn(0); // ZÌskanie stÂpca podæa indexu (v tomto prÌpade index 0)
        column.setPreferredWidth(30); // nastav˝ öÌrku prvÈho stlpca

        //pomocn· index nam rata poËet predmetov po kaûdom cykle ju nav˝öime
        int index = 0;
        for (Object objekt : this.start.getPozicovna().getZoznamPredmetov()) {

            if (objekt != null) {
                //Ak objekt nieje null tak z neho zaËnem vyùahovaù data do premennych ( treba aj pretypovaù objekt )
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
