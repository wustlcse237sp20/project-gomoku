package com.homework.gomoku.game;

public class PlayerMove implements Move {

    int row, col;
    Player player;

    public PlayerMove(int row, int col, Player player){
        this.row = row;
        this.col = col;
        this.player = player;
    }

    @Override
    public int getCol() {
        return col;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public Player getPlayer() {
        return player;
    }
}
