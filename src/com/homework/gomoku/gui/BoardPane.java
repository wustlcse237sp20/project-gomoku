package com.homework.gomoku.gui;

import com.homework.gomoku.game.Board;
import com.homework.gomoku.game.HumanPlayer;
import com.homework.gomoku.game.PlayerMove;
import com.homework.gomoku.game.TwoPlayerBoard;

import javax.swing.*;
import java.awt.*;

public class BoardPane extends JPanel {
    int rows;
    Board board;

    public BoardPane(Board board) {
        super();
        this.board = board;
        rows = board.getBoardSize();
    }

    public void setBoard(Board bd) {
        this.board = bd;
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        int height = this.getHeight();
        int width = this.getWidth();
        int hRem = width % rows;
        int hRemCount = hRem;
        int vRem = height % rows;
        int vRemCount = vRem;
        int hGap = width / rows;
        int vGap = height / rows;
        int i = 0;
        while (i < height) {
            g.drawLine(0, i, width, i);
            if (vRemCount > 0) {
                i += vGap + 1;
                vRemCount -= 1;
            } else {
                i += vGap;
            }
        }
        g.drawLine(0, i - 1, width, i - 1);
        i = 0;
        while (i < width) {
            g.drawLine(i, 0, i, height);
            if (hRemCount > 0) {
                i += hGap + 1;
                hRemCount -= 1;
            } else {
                i += hGap;
            }
        }
        g.drawLine(i - 1, 0, i - 1, height);
        int pieceWidth = hGap*3/4;
        int pieceHeight = vGap*3/4;
        if (board != null) {
            for(int j = 0; j< board.getBoardSize(); j++){
                for(int k = 0; k< board.getBoardSize(); k++){
                    if(board.getPieceAt(j,k)!=null){
                        if(board.getPieceAt(j,k).getPlayer().getColor()){
                            g.setColor(Color.BLACK);
                        }
                        else{
                            g.setColor(Color.WHITE);
                        }
                        int x = k>hRem?hRem*(hGap+1) + (k-hRem)*hGap: k*(hGap+1);
                        int y = j>vRem?vRem*(vGap+1) + (j-vRem)*vGap: j*(vGap+1);
                        g.fillOval(x-pieceWidth/2, y-pieceHeight/2, pieceWidth, pieceHeight);
                    }
                }
            }
        }
    }
}
