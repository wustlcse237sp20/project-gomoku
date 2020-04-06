package com.homework.gomoku.gui;

import com.homework.gomoku.game.Board;
import com.homework.gomoku.game.Game;
import com.homework.gomoku.game.Move;
import com.homework.gomoku.game.PlayerMove;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePage extends JPanel {

    JPanel boardArea;
    Game game;
    boolean waitOnClick;
    public GamePage(Game game) {
        super();
        this.game = game;
        init();
    }

    private int borderDim() {
        return Math.min(150, (this.getWidth() - this.getHeight()) / 2);
    }

    private int gridRound(int length, int loc, int numInt){
        int rem = length % numInt;
        int gap = length / numInt;
        return loc > rem * (gap+1)? (int)Math.round((double)loc/(gap+1)):
                (int)Math.round((double)(loc-rem*(gap+1))/gap)+rem;
    }

    public void init() {
        this.setLayout(new BorderLayout());
        this.boardArea = new BoardPane(game.getBoard());
        this.add(boardArea, BorderLayout.CENTER);
        JPanel leftWing = new JPanel();
        leftWing.setBackground(Color.cyan);
        leftWing.setPreferredSize(new Dimension(150, 500));
        JPanel rightWing = new JPanel();
        rightWing.setBackground(Color.cyan);
        rightWing.setPreferredSize(new Dimension(150, 500));
        this.add(leftWing, BorderLayout.LINE_START);
        this.add(rightWing, BorderLayout.LINE_END);
        JPanel thisP = this;
        boardArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowNum = gridRound(boardArea.getHeight(), e.getY(), game.getBoard().getBoardSize());
                int colNum = gridRound(boardArea.getWidth(), e.getX(), game.getBoard().getBoardSize());
                Move cMove = new PlayerMove(rowNum, colNum, game.getCurrentPlayer());
                if(game.isValidMove(cMove)){
                    if(game.isEnd(cMove)){
                        game.getBoard().placeMove(cMove);
                        thisP.repaint();
                        JDialog d = new JDialog();
                        d.add(new JLabel("GameEnd"));
                        d.setSize(200, 200);
                        d.setVisible(true);
                        boardArea.removeMouseListener(this);
                    }
                    else{
                        game.getBoard().placeMove(cMove);
                        thisP.repaint();
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
