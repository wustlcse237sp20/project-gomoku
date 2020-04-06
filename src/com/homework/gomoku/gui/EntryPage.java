package com.homework.gomoku.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntryPage extends JPanel {

    JFrame parentFrame;
    JPanel gamePage;
    JPanel tutorialPage;

    public EntryPage(JFrame pFrame, JPanel gamePage) {
        super(new GridBagLayout());
        this.parentFrame = pFrame;
        this.gamePage = gamePage;
        init();
    }

    public void init(){
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
        exitGameBut.addActionListener(e -> parentFrame.dispose());
        newGameBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setContentPane(gamePage);
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });
        buttonsPanel.add(newGameBut);
        buttonsPanel.add(loadGameBut);
        buttonsPanel.add(tutorialBut);
        buttonsPanel.add(exitGameBut);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        this.add(gameTitleLab, c);
        c.gridx = 0;
        c.gridy = 1;
        this.add(buttonsPanel, c);
    }

}
