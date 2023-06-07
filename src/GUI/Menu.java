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

    // Konštruktor pre Menu , Na vstupe je náš základný Frame a Metoda pre riadenie programu
    // Na všetky buttony mam nastavený listener
    public Menu(Frame frame, Start start) {
        this.frame = frame;
        this.start = start;
        butVypis.addActionListener(this);
        butPozicaj.addActionListener(this);
        butVypisVypozicky.addActionListener(this);
        butVratPredmet.addActionListener(this);
        butExit.addActionListener(this);
        JLabText.setText("Zákazník : " + this.start.getZakaznik().getMeno() + " " + this.start.getZakaznik().getPriezvisko());
    }

    //Action event zaznamena kliknutie a porovnáme kde bolo kliknutie spravené ( na akom buttone )
    //Podla butonu vykonám frame.setContext èim nastavim nový kontext a vytvorim daný predmet so vstupnými parametramy
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
    // Slúži na nastavenie Contextu ak zavolam menu aplikujem tuto metodu
    public JPanel getContent(){
        return this.panelMenu;
    }
}
