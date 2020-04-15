package com.homework.gomoku.game;

public class TutorialPlayer implements Player{
    
    boolean isBlack;
    int row =7;
    int col =7;

    @Override
    public boolean getColor() {
        return isBlack;
    }

    @Override
    public Move getMove(Board board) {
        PlayerMove tutorialMove;
        if (isBlack){
            tutorialMove = new PlayerMove(row, col, this);
        }else {
            tutorialMove = new PlayerMove(row+1, col, this);
            col ++;
        }
        return tutorialMove;
    }
}