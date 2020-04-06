package com.homework.gomoku.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePage extends JPanel {

    JPanel boardArea;
    boolean waitOnClick;
    public GamePage() {
        super();
        init();
    }

    private int borderDim() {
        return Math.min(150, (this.getWidth() - this.getHeight()) / 2);
    }

    public void init() {
        this.setLayout(new BorderLayout());
        this.boardArea = new BoardPane();
        this.add(boardArea, BorderLayout.CENTER);
        JPanel leftWing = new JPanel();
        leftWing.setBackground(Color.cyan);
        leftWing.setPreferredSize(new Dimension(150, 500));
        JPanel rightWing = new JPanel();
        rightWing.setBackground(Color.cyan);
        rightWing.setPreferredSize(new Dimension(150, 500));
        this.add(leftWing, BorderLayout.LINE_START);
        this.add(rightWing, BorderLayout.LINE_END);
        boardArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int height = boardArea.getHeight();
                int width = boardArea.getWidth();
                int rows = 15;
                int hRem = width % rows;
                int vRem = height % rows;
                int hGap = width / rows;
                int vGap = height / rows;
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

    public JPanel getBoard(){
        return this.boardArea;
    }

    public void listenOnMove(){
        waitOnClick = true;
        boardArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(waitOnClick){
                    System.out.println("hello");
                    waitOnClick = false;
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
