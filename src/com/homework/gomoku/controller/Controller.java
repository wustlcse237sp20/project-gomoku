package com.homework.gomoku.controller;

import com.homework.gomoku.game.Board;
import com.homework.gomoku.game.Move;
import com.homework.gomoku.game.Player;
import com.homework.gomoku.game.PlayerMove;
import com.homework.gomoku.gui.EntryFrame;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Controller {

    static JFrame mainFrame;
    static JPanel p;

    public Controller(){
        mainFrame = new EntryFrame("new game");
    }

    public void launch(){
        mainFrame.setVisible(true);
        Player plyr1  = new Player() {
            @Override
            public boolean getColor() {
                return true;
            }

            @Override
            public Move getMove(Board board) {
                int[] loc = new int[2];
                mainFrame.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        loc[0] = 0;
                        loc[1] = 0;
                        mainFrame.removeMouseListener(this);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
                return new PlayerMove(loc[0],loc[1],this);
            }
        };
    }

    public void makeEntryWindow(){

    }

}
