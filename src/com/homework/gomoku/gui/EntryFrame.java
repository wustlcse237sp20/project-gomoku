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
    JPanel entryPage;
    public EntryFrame(String title, Game game){
        super(title);
        this.game = game;
        init();
    }

    public void init(){
        this.setSize(panelWidth,  panelHeight);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.gamePanel = new GamePage(game);
        entryPage = new EntryPage(this, gamePanel);
        this.setContentPane(entryPage);
    }

}
