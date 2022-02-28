package me.khryszkiewicz.pablibraryapp;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.*;

public class FramePasekStanu extends JFrame {

    public FramePasekStanu() {

//        JFrame this = new JFrame("Pasek stanu przykład");
        JPanel panelPaskuStanu = new JPanel();
        panelPaskuStanu.setLayout(new BorderLayout());
        JLabel pasekStanu = new JLabel("Jestes w menu glownym.");
        pasekStanu.setBackground(Color.WHITE);
        pasekStanu.setOpaque(true);
        panelPaskuStanu.add(pasekStanu, SOUTH);
        this.add(panelPaskuStanu);
        //this.add(new JLabel(new ImageIcon("icons/ikona.jpg")).setLayout(CENTER););

//        ImageIcon image = new ImageIcon("icons/ikona.jpg");
//        JLabel imageLabel = new JLabel(image);

//        this.add(imageLabel,BorderLayout.CENTER);
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);




    }

}