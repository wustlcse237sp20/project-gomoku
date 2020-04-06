package com.homework.gomoku.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntryFrame extends JFrame {

    int panelWidth = 800;
    int panelHeight = 500;

    GamePage gamePanel;
    JPanel entryPage;

    public EntryFrame(String title){
        super(title);
        init();
    }

    public void init(){
        this.setSize(panelWidth,  panelHeight);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gamePanel = new GamePage();
        entryPage = new EntryPage(this, gamePanel);
        this.setContentPane(entryPage);
    }

    public JPanel getBoardArea(){
        if (this.getContentPane().getClass() == GamePage.class){
            return gamePanel.getBoard();
        }
        return null;
    }
}
