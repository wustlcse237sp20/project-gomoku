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
    JButton saveBut;
    JButton undoBut;
    JLabel timeElapseLabel;
    JLabel movesHeader;
    JLabel totalMovesHeader;
    JLabel informationBarHeader;
    JLabel functionBarHeader;
    JLabel currentPlayerStatusHeader;

    JLabel currentPlayerStatus;
    JLabel tickerUpdate;
    JLabel movesUpdate;
    JLabel totalMovesUpdate;
    JButton colorChange;
    JPanel leftWing;
    JPanel rightWing;
    JButton exitBut;
    JButton mainBut;
    JTextArea promptText;

    public GamePage(Game game) {
        super();
        this.game = game;
        init();
    }

    public void init() {
        this.setLayout(new BorderLayout());
        this.boardArea = new BoardPane(game.getBoard());
        this.add(boardArea, BorderLayout.CENTER);
        leftWing = new JPanel();
        leftWing.setBackground(Color.cyan);
        leftWing.setPreferredSize(new Dimension(150, 500));

        //setup for all the elements
        saveBut = new JButton("Save Game");
        saveBut.setBounds(40, 100, 100, 60);
        undoBut = new JButton("Undo");
        movesUpdate = new JLabel("0");
        tickerUpdate = new JLabel();
        movesHeader = new JLabel("Current Moves:");
        timeElapseLabel = new JLabel("Time Elapsed: ");
        totalMovesHeader = new JLabel("Moves Left: ");
        totalMovesUpdate = new JLabel(game.getBoard().getBoardSize() * game.getBoard().getBoardSize() + "");
        colorChange = new JButton("Change");
        colorChange.addActionListener(e -> {
            double switchSelector = 1 * Math.random();
            if (switchSelector < 0.33) {
                leftWing.setBackground(Color.GRAY);
                rightWing.setBackground(Color.GRAY);
            }
            if (switchSelector >= 0.33) {
                if (switchSelector < 0.66) {
                    leftWing.setBackground(Color.GREEN);
                    rightWing.setBackground(Color.GREEN);
                } else {
                    leftWing.setBackground(Color.YELLOW);
                    rightWing.setBackground(Color.YELLOW);
                }
            }
        });
        currentPlayerStatusHeader = new JLabel("Player: ");
        currentPlayerStatus = new JLabel("BLACK");
        currentPlayerStatus.setForeground(Color.BLACK);

        //infobar
        informationBarHeader = new JLabel("Information Bar");
        informationBarHeader.setOpaque(true);
        informationBarHeader.setBackground(Color.PINK);
        informationBarHeader.setForeground(Color.white);
        informationBarHeader.setFont(new Font("Serif", Font.BOLD, 20));

        //functionBarHeader
        functionBarHeader = new JLabel("Function Bar");
        functionBarHeader.setOpaque(true);
        functionBarHeader.setBackground(Color.PINK);
        functionBarHeader.setForeground(Color.white);
        functionBarHeader.setFont(new Font("Serif", Font.BOLD, 20));


        //What's being displayed on the left
        leftWing.add(informationBarHeader);
        leftWing.add(currentPlayerStatusHeader);
        leftWing.add(currentPlayerStatus);
        leftWing.add(movesHeader);
        leftWing.add(movesUpdate);
        leftWing.add(totalMovesHeader);
        leftWing.add(totalMovesUpdate);

        //what's being displayed on the right
        rightWing = new JPanel();
        rightWing.setBackground(Color.cyan);
        rightWing.setPreferredSize(new Dimension(150, 500));

        rightWing.add(functionBarHeader);
        rightWing.add(timeElapseLabel);
        rightWing.add(tickerUpdate);
        rightWing.add(undoBut);
        rightWing.add(colorChange);
        rightWing.add(saveBut);
        exitBut = new JButton("Exit Game");
        mainBut = new JButton("Return to Menu");
        promptText = new JTextArea();
        promptText.setEditable(false);
        promptText.setPreferredSize(new Dimension(120, 200));
        promptText.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 22));
        promptText.setLineWrap(true);
        leftWing.add(mainBut);
        leftWing.add(exitBut);

        rightWing.add(promptText);
        this.add(leftWing, BorderLayout.LINE_START);
        this.add(rightWing, BorderLayout.LINE_END);

        updateInfoBar();
    }

    public JPanel getBoardArea() {
        return boardArea;
    }

    public JButton getSaveBut() {
        return saveBut;
    }

    public JButton getUndoBut() {
        return undoBut;
    }

    public JButton getColorChangeBut() {
        return colorChange;
    }

    public JButton getExitBut() {
        return exitBut;
    }

    public JButton getMainBut() {
        return mainBut;
    }

    public JTextArea getPromptText() {
        return promptText;
    }

    public JLabel getTickerUpdate() {
        return tickerUpdate;
    }

    public void updateInfoBar() {
        int movesLeft = game.getBoard().getBoardSize() * game.getBoard().getBoardSize() - game.getNumTurn();
        movesUpdate.setText(game.getNumTurn() + "");
        totalMovesUpdate.setText(movesLeft + "");
        totalMovesUpdate.setForeground(Color.red);
        movesUpdate.setForeground(Color.red);

        if (game.getCurrentPlayer().getColor()) {
            currentPlayerStatus.setText("BLACK");
            currentPlayerStatus.setForeground(Color.BLACK);
        } else {
            currentPlayerStatus.setText("WHITE");
            currentPlayerStatus.setForeground(Color.WHITE);
        }
    }
}
