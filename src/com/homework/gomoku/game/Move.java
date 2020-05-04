package com.homework.gomoku.game;

public interface Move {
    int getCol();
    int getRow();
    Player getPlayer();
}
