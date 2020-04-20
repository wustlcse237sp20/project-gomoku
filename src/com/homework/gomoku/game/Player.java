package com.homework.gomoku.game;

public interface Player {
    public boolean getColor();
    public Move getMove(Board board);
    public void toByte();
}
