package com.homework.gomoku.game;

public class TutorialPlayer implements Player{
    
    boolean isBlack;
    int[] rowSeq;
    int[] colSeq;

    public TutorialPlayer(boolean isBlack) {
        this.isBlack = isBlack;
    }

    public void setMoves(int[] rows, int[] cols){
        rowSeq = rows;
        colSeq = cols;
    }

    @Override
    public boolean getColor() {
        return isBlack;
    }

    @Override
    public Move getMove(Game game) {
        int step = game.getNumTurn()/2;
        System.out.println(step);
        return new PlayerMove(rowSeq[step], colSeq[step], this);
    }
}