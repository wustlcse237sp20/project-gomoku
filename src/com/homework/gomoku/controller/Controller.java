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
                boolean[] b = new boolean[1];
                b[0] = true;
                mainFrame.getBoardArea().addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        loc[0] = e.getX();
                        loc[1] = e.getY();
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
