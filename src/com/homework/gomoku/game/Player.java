package com.homework.gomoku.game;

public interface Player {
    boolean getColor();
    Move getMove(Game game);
}
