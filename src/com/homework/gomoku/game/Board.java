package com.homework.gomoku.game;

public interface Board {
    public Move getPieceAt(int row, int col);
    public Boolean placeMove(Move move);
    public int getBoardSize();
    public Move[][] getBoardGrid();
}
