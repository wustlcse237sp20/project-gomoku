package com.homework.gomoku.game;

public interface Board {
    Move getPieceAt(int row, int col);
    Boolean placeMove(Move move);
    Move undoMove();
    int getBoardSize();
    Move getLasMove();
}
