package com.homework.gomoku.gui;

import com.homework.gomoku.game.Board;
import com.homework.gomoku.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntryFrame extends JFrame {

    int panelWidth = 800;
    int panelHeight = 500;
    Game game;
    GamePage gamePanel;
    EntryPage entryPage;
    public EntryFrame(String title){
        super(title);
        init();
    }

    public void init(){
        this.setSize(panelWidth,  panelHeight);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        entryPage = new EntryPage();
        this.setContentPane(entryPage);

    }

    public EntryPage getEntryPage(){
        return entryPage;
    }

    public GamePage getGamePanel(){
        return gamePanel;
    }

}
