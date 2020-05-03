package com.homework.gomoku.game;

public interface Player {
    public boolean getColor();
    public Move getMove(Game game);
}
