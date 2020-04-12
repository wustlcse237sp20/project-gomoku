package com.homework.gomoku.controller;

import com.homework.gomoku.game.*;
import com.homework.gomoku.gui.EntryFrame;
import com.homework.gomoku.gui.GamePage;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Controller {

    EntryFrame mainFrame;
    JPanel p;
    Game game;
    GamePage gp;

    public Controller(){
        game = new Game(new TwoPlayerBoard(15), new HumanPlayer(true), new HumanPlayer(false), new TwoPlayerRule());
        mainFrame = new EntryFrame("new game", game);
    }

    public void launch(){
        mainFrame.setVisible(true);
        mainFrame.getEntryPage().getExitBut().addActionListener(e->mainFrame.dispose());
        mainFrame.getEntryPage().getNewGameBut().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeNewGame();
                mainFrame.setContentPane(gp);
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });
    }

    private int gridRound(int length, int loc, int numInt){
        int rem = length % numInt;
        int gap = length / numInt;
        return loc > rem * (gap+1)? (int)Math.round((double)loc/(gap+1)):
                (int)Math.round((double)(loc-rem*(gap+1))/gap)+rem;
    }

    private void makeNewGame(){
        game = new Game(new TwoPlayerBoard(15), new HumanPlayer(true), new HumanPlayer(false), new TwoPlayerRule());
        gp = new GamePage(game);
        JPanel boardArea = gp.getBoardArea();
        boardArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowNum = gridRound(boardArea.getHeight(), e.getY(), game.getBoard().getBoardSize());
                int colNum = gridRound(boardArea.getWidth(), e.getX(), game.getBoard().getBoardSize());
                Move cMove = new PlayerMove(rowNum, colNum, game.getCurrentPlayer());
                if(game.isValidMove(cMove)){
                    if(game.isEnd(cMove)){
                        game.getBoard().placeMove(cMove);
                        gp.repaint();
                        JDialog d = new JDialog();
                        d.add(new JLabel("GameEnd"));
                        d.setSize(200, 200);
                        d.setVisible(true);
                        boardArea.removeMouseListener(this);
                    }
                    else{
                        game.getBoard().placeMove(cMove);
                        gp.repaint();
                        game.nextTurn();
                    }
                }
                else{
                    JDialog d = new JDialog();
                    d.add(new JLabel("Invalid Move"));
                    d.setSize(200, 200);
                    d.setVisible(true);
                }
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
    }


}
