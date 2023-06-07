package GUI;


import Objekty.Start;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private Registracia registracia;
    //Kon�truktor okna ktor� ma na vstupe metodu ktora bude riadi� program
    // Obsahuje zakladne prikazy na na�t�lovanie okna
    public Frame(Start start) {
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800,800);


        registracia = new Registracia(this,start);
        setContentPane(registracia.getContent());

        setVisible(true);
    }

    //Metoda nastavy obsah okna
    public void setContext(JPanel novyContext){
        setContentPane(novyContext);
        revalidate();
        repaint();
    }
}