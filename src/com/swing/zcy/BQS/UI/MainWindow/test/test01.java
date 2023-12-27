package com.swing.zcy.BQS.UI.MainWindow.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test01 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("CardLayout Example");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel cardPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        JButton card1Button = new JButton("Card 1");
        JButton card2Button = new JButton("Card 2");
        JButton card3Button = new JButton("Card 3");

        card1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Card 1");
            }
        });

        card2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Card 2");
            }
        });

        card3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Card 3");
            }
        });

        cardPanel.add(card1Button, "Card 1");
        cardPanel.add(card2Button, "Card 2");
        cardPanel.add(card3Button, "Card 3");

        frame.add(cardPanel);

        frame.setVisible(true);
    }
}

