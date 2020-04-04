package com.homework.gomoku.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntryFrame extends JFrame {

    int panelWidth = 800;
    int panelHeight = 500;

    public EntryFrame(String title){
        super(title);
        init();
    }

    public void init(){
        this.setSize(panelWidth,  panelHeight);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel entryPage = new JPanel(new GridBagLayout());
        JLabel gameTitleLab = new JLabel("GOMOKU");
        JPanel buttonsPanel = new JPanel();
        JPanel gamePanel = new GamePage();
        GridLayout buttonsLayout = new GridLayout(0, 2);
        buttonsLayout.setHgap(40);
        buttonsLayout.setVgap(20);
        buttonsPanel.setLayout(buttonsLayout);
        JButton newGameBut = new JButton("New Game");
        JButton loadGameBut = new JButton("Load Game");
        JButton tutorialBut = new JButton("Tutorial");
        JButton exitGameBut = new JButton("Exit to Desktop");
        JFrame thisFrame = this;
        exitGameBut.addActionListener(e -> this.dispose());
        newGameBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thisFrame.setContentPane(gamePanel);
                thisFrame.revalidate();
                thisFrame.repaint();
            }
        });
        buttonsPanel.add(newGameBut);
        buttonsPanel.add(loadGameBut);
        buttonsPanel.add(tutorialBut);
        buttonsPanel.add(exitGameBut);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        entryPage.add(gameTitleLab, c);
        c.gridx = 0;
        c.gridy = 1;
        entryPage.add(buttonsPanel, c);
        this.setContentPane(entryPage);
    }

}
