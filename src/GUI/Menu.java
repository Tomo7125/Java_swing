package GUI;

import Objekty.Start;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener {

    private JButton butVypis;
    private JButton butPozicaj;
    private JButton butVypisVypozicky;
    private JButton butExit;
    private JButton butVratPredmet;
    private JPanel panelMenu;
    private JLabel JLabText;
    Frame frame;
    Start start;

    // Kon�truktor pre Menu , Na vstupe je n� z�kladn� Frame a Metoda pre riadenie programu
    // Na v�etky buttony mam nastaven� listener
    public Menu(Frame frame, Start start) {
        this.frame = frame;
        this.start = start;
        butVypis.addActionListener(this);
        butPozicaj.addActionListener(this);
        butVypisVypozicky.addActionListener(this);
        butVratPredmet.addActionListener(this);
        butExit.addActionListener(this);
        JLabText.setText("Z�kazn�k : " + this.start.getZakaznik().getMeno() + " " + this.start.getZakaznik().getPriezvisko());
    }

    //Action event zaznamena kliknutie a porovn�me kde bolo kliknutie spraven� ( na akom buttone )
    //Podla butonu vykon�m frame.setContext �im nastavim nov� kontext a vytvorim dan� predmet so vstupn�mi parametramy
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(butVypis)){
            frame.setContext(new VypisPredmetov(frame,start).getContent());
        }
        if (e.getSource().equals(butPozicaj)){
            frame.setContext(new PozicajPredmet(frame,start).getContent());
        }
        if (e.getSource().equals(butVypisVypozicky)){
            frame.setContext(new Vypozicky(frame , start).setContent());
        }
        if (e.getSource().equals(butVratPredmet)){
            frame.setContext(new vratPredet(frame, start).setContent());
        }
        if (e.getSource().equals(butExit)){
            start.zapisVypozickydoTXT();
            System.exit(0);
        }
    }
    // Sl��i na nastavenie Contextu ak zavolam menu aplikujem tuto metodu
    public JPanel getContent(){
        return this.panelMenu;
    }
}
