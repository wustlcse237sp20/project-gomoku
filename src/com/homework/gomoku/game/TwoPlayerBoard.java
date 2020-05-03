package com.homework.gomoku.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TwoPlayerBoard implements Board, Serializable {

    int boardSize;

    Move[][] boardGrid;
    List<Move> moveSeq;

    public TwoPlayerBoard(int size){
        boardSize = size;
        boardGrid = new Move[size][size];
        moveSeq = new ArrayList<>();
    }

    @Override
    public Move getPieceAt(int row, int col) {
        return boardGrid[row][col];
    }

    @Override
    public Boolean placeMove(Move move) {
        if(getPieceAt(move.getRow(), move.getCol())==null){
            boardGrid[move.getRow()][move.getCol()] = move;
            return true;
        }
        return false;
    }
    
    
    //just need to determine how to grab the last move
    public Boolean undoMove(Move move) {
    	if(getPieceAt(move.getRow(),move.getCol())!=null) {
    		boardGrid[move.getRow()][move.getCol()] = null;
    	}
            return false;
    }

    @Override
    public int getBoardSize() {
        return boardSize;
    }


}
