package com.homework.gomoku.game;

import java.util.ArrayList;
import java.util.List;

public class TwoPlayerBoard implements Board {

    int boardSize;

    Move[][] boardGrid;
    List<Move> moveSeq;

    public TwoPlayerBoard(int size){
        boardGrid = new Move[size][size];
        moveSeq = new ArrayList<>();
    }

    @Override
    public Move getPieceAt(int row, int col) {
        return boardGrid[row][col];
    }

    @Override
    public Boolean placePieceAt(Move move ,int row, int col) {
        if(getPieceAt(row,col)!=null){
            boardGrid[row][col] = move;
            return true;
        }
        return false;
    }

    @Override
    public int getBoardSize(){
        return boardSize;
    }

    @Override
    public Move[][] getBoardGrid(){
        return boardGrid;
    }
}
