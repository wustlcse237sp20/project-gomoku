package com.homework.gomoku.game;

public interface Rule {
    public boolean isEnd(Board board, Move move);
    public boolean isValidMove(Board board, Move move);
}