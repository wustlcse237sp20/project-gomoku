package com.homework.gomoku.controller;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Controller {

    static JFrame mainFrame;
    static JPanel p;
    int panelWidth = 800;
    int panelHeight = 500;
    public Controller(){
        mainFrame = new JFrame("new game");
        mainFrame.setSize(panelWidth,  panelHeight);
        mainFrame.setLayout(new GridBagLayout());
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        makeEntryWindow();
    }

    public void launch(){
        mainFrame.setVisible(true);
    }

    public void makeEntryWindow(){
        JLabel gameTitleLab = new JLabel("GOMOKU");
        JPanel buttonsPanel = new JPanel();
        GridLayout buttonsLayout = new GridLayout(0, 2);
        buttonsLayout.setHgap(40);
        buttonsLayout.setVgap(20);
        buttonsPanel.setLayout(buttonsLayout);
        JButton newGameBut = new JButton("New Game");
        JButton loadGameBut = new JButton("Load Game");
        JButton tutorialBut = new JButton("Tutorial");
        JButton exitGameBut = new JButton("Exit to Desktop");
        exitGameBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
            }
        });
        buttonsPanel.add(newGameBut);
        buttonsPanel.add(loadGameBut);
        buttonsPanel.add(tutorialBut);
        buttonsPanel.add(exitGameBut);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        mainFrame.add(gameTitleLab, c);
        c.gridx = 0;
        c.gridy = 1;
        mainFrame.add(buttonsPanel, c);
    }

}
