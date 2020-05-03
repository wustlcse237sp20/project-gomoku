package com.homework.gomoku.game;

import java.io.Serializable;

public class HumanPlayer implements Player, Serializable {

    boolean isBlack;

    public HumanPlayer(boolean isBlack){
        this.isBlack = isBlack;
    }

    @Override
    public boolean getColor() {
        return isBlack;
    }

    @Override
    public Move getMove(Game game) {
        return null;
    }
}
