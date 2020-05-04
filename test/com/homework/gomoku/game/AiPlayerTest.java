package com.homework.gomoku.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class AiPlayerTest {

    @Test
    public void getMoveThreePieces() {
        Board board = new TwoPlayerBoard(15);
        Player nullPlayer = new HumanPlayer(true);
        Player aiPlayer = new AiPlayer(false);
        Rule tpRule = new TwoPlayerRule();
        Game game = new Game(board, nullPlayer, aiPlayer);
        board.placeMove(new PlayerMove(5,5,nullPlayer));
        board.placeMove(new PlayerMove(5,6,nullPlayer));
        board.placeMove(new PlayerMove(5,7,nullPlayer));
        Move move = aiPlayer.getMove(game);
        boolean effectiveMove = false;
        int row = move.getRow();
        int col = move.getCol();
        if (row == 5 && (col == 8 || col == 4)){
            effectiveMove = true;
        }
        assertTrue(effectiveMove);
    }

    @Test
    public void getMoveFourPieces() {
        Board board = new TwoPlayerBoard(15);
        Player nullPlayer = new HumanPlayer(true);
        Player aiPlayer = new AiPlayer(false);
        Rule tpRule = new TwoPlayerRule();
        Game game = new Game(board, nullPlayer, aiPlayer);
        board.placeMove(new PlayerMove(5,5,nullPlayer));
        board.placeMove(new PlayerMove(5,6,nullPlayer));
        board.placeMove(new PlayerMove(5,7,nullPlayer));
        board.placeMove(new PlayerMove(5,8,nullPlayer));
        board.placeMove(new PlayerMove(5,9,aiPlayer));
        Move move = aiPlayer.getMove(game);
        boolean effectiveMove = false;
        int row = move.getRow();
        int col = move.getCol();
        if (row == 5 &&  col == 4){
            effectiveMove = true;
        }
        assertTrue(effectiveMove);
    }
}