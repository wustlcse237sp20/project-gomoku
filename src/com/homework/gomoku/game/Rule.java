package com.homework.gomoku.game;

public interface Rule {
    boolean isEnd(Board board, Move move);
    boolean isValidMove(Board board, Move move);
}