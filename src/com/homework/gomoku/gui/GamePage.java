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
        saveBut = new JButton("Save Game");
        exitBut = new JButton("Exit Game");
        mainBut = new JButton("Return to Menu");
        promptText = new JTextArea();
        promptText.setEditable(false);
        promptText.setPreferredSize(new Dimension(120, 200));
        promptText.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 22));
        promptText.setLineWrap(true);
        JPanel leftWing = new JPanel();
        leftWing.setBackground(Color.cyan);
        leftWing.setPreferredSize(new Dimension(150, 500));
        leftWing.add(saveBut);
        leftWing.add(mainBut);
        leftWing.add(exitBut);
        JPanel rightWing = new JPanel();
        rightWing.setBackground(Color.cyan);
        rightWing.setPreferredSize(new Dimension(150, 500));
        rightWing.add(promptText);
        this.add(leftWing, BorderLayout.LINE_START);
        this.add(rightWing, BorderLayout.LINE_END);
    }

    public JPanel getBoardArea(){
        return boardArea;
    }

    public JButton getSaveBut() {
        return saveBut;
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
}
