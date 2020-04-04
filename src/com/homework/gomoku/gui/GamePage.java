package com.homework.gomoku.gui;

import javax.swing.*;
import java.awt.*;

public class GamePage extends JPanel {

    public GamePage() {
        super();
        init();
    }

    class BoardPane extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(Color.BLACK);
            int height = this.getHeight();
            int width = this.getWidth();
            int rows = 15;
            int hRem = width % rows;
            int vRem = height % rows;
            int hGap = width / rows;
            int vGap = height / rows;
            int i = 0;
            while (i < height) {
                g.drawLine(0, i, width, i);
                if (vRem > 0) {
                    i += vGap + 1;
                    vRem -= 1;
                } else {
                    i += vGap;
                }
            }
            g.drawLine(0, i-1, width, i-1);
            i = 0;
            while (i < width) {
                g.drawLine(i, 0, i, height);
                if (hRem > 0) {
                    i += hGap + 1;
                    hRem -= 1;
                } else {
                    i += hGap;
                }
            }
            g.drawLine(i-1, 0, i-1, height);
        }
    }

    public void init() {
        this.setLayout(new BorderLayout());
        this.add(new BoardPane(), BorderLayout.CENTER);
        JPanel leftWing = new JPanel();
        leftWing.setBackground(Color.cyan);
        leftWing.setPreferredSize(new Dimension(150, 500));
        JPanel rightWing = new JPanel();
        rightWing.setBackground(Color.cyan);
        rightWing.setPreferredSize(new Dimension(150, 500));
        this.add(leftWing, BorderLayout.LINE_START);
        this.add(rightWing, BorderLayout.LINE_END);
    }

}
