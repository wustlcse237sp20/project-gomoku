package com.homework.gomoku.controller;

import com.homework.gomoku.game.*;
import com.homework.gomoku.gui.EntryFrame;
import com.homework.gomoku.gui.GamePage;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Controller {

    static EntryFrame mainFrame;
    static JPanel p;
    static Game game;

    public Controller(){
        game = new Game(new TwoPlayerBoard(15), new HumanPlayer(true), new HumanPlayer(false), new TwoPlayerRule());
        mainFrame = new EntryFrame("new game", game);
    }

    public void launch(){
        mainFrame.setVisible(true);
    }


}
