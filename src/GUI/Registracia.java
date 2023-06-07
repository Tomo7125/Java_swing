package GUI;

import Objekty.Start;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registracia implements ActionListener {
    private JLabel JRegistracia;
    private JLabel JMeno;
    private JLabel JPriezvisko;
    private JPanel panelRegistracia;
    private JTextField textFieldMeno;
    private JTextField textFieldPriezvisko;
    private JButton JButOk;

    private Frame frame;

    private Start start;



    public Registracia(Frame frame , Start start) {
        this.start = start;
        this.frame = frame;
        JButOk.addActionListener(this);
    }
    //Skontrolujem èi je vyplnene meno a priezvisko , ak ano tak nasetujem pre zakaznika toto meno a priezvisko
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(JButOk)){
            if (!textFieldMeno.getText().isEmpty() && !textFieldPriezvisko.getText().isEmpty()){
                this.start.getZakaznik().setMeno(textFieldMeno.getText());
                this.start.getZakaznik().setPriezvisko(textFieldPriezvisko.getText());

                //Nastavim context na menu
                frame.setContext(new Menu(frame , start).getContent());

            }
        }
    }

    public JPanel getContent() {
        return this.panelRegistracia;
    }

}
