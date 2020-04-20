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
    public GamePage(Game game) {
        super();
        this.game = game;
        init();
    }

    public void init() {
        this.setLayout(new BorderLayout());
        this.boardArea = new BoardPane(game.getBoard());
        this.add(boardArea, BorderLayout.CENTER);
        JPanel leftWing = new JPanel();
        leftWing.setBackground(Color.cyan);
        leftWing.setPreferredSize(new Dimension(150, 500));
        saveBut = new JButton("Save Game");
        leftWing.add(saveBut);
        JPanel rightWing = new JPanel();
        rightWing.setBackground(Color.cyan);
        rightWing.setPreferredSize(new Dimension(150, 500));
        this.add(leftWing, BorderLayout.LINE_START);
        this.add(rightWing, BorderLayout.LINE_END);
    }

    public JPanel getBoardArea(){
        return boardArea;
    }

    public JButton getSaveBut() {
        return saveBut;
    }

}
