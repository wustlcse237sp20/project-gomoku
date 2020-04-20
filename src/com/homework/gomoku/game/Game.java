package com.homework.gomoku.game;

import java.io.Serializable;

public class Game implements Serializable {
    Board board;
    Player player1;
    Player player2;
    boolean isBlackTurn;
    Rule rule;
    public Game(Board board, Player ply1, Player ply2, Rule rule){
        this.board = board;
        player1 = ply1;
        player2 = ply2;
        isBlackTurn = true;
        this.rule = rule;
    }

    public void nextTurn(){
        isBlackTurn = !isBlackTurn;
    }

    public boolean getTurn(){
        return isBlackTurn;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer(){
        return isBlackTurn ? player1 : player2;
    }

    public boolean isValidMove(Move move){
        return rule.isValidMove(board, move);
    }

    public boolean isEnd(Move move){
        return rule.isEnd(board, move);
    }
}
