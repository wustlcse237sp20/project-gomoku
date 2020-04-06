package com.homework.gomoku.game;

public class HumanPlayer implements Player {

    boolean isBlack;

    public HumanPlayer(boolean isBlack){
        this.isBlack = isBlack;
    }

    @Override
    public boolean getColor() {
        return isBlack;
    }

    @Override
    public Move getMove(Board board) {
        return null;
    }
}
