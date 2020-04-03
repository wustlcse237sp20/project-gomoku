package com.homework.gomoku.game;

public interface Board {
    public Move getPieceAt(int row, int col);
    public Boolean placePieceAt(int row, int col);
}
