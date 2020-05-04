package com.homework.gomoku.game;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TwoPlayerRuleTest {

    @Test
    public void isEndPlayer1() {
        Player plyr1 = new HumanPlayer(true);
        Player plyr2 = new HumanPlayer(false);
        Rule tpRule = new TwoPlayerRule();
        Board tpBoard = new TwoPlayerBoard(15);
        tpBoard.placeMove(new PlayerMove(5,5,plyr1));
        tpBoard.placeMove(new PlayerMove(6,5,plyr1));
        tpBoard.placeMove(new PlayerMove(7,5,plyr1));
        tpBoard.placeMove(new PlayerMove(8,5,plyr1));
        tpBoard.placeMove(new PlayerMove(5,4,plyr2));
        tpBoard.placeMove(new PlayerMove(6,4,plyr2));
        tpBoard.placeMove(new PlayerMove(7,4,plyr2));
        tpBoard.placeMove(new PlayerMove(8,4,plyr2));
        assertTrue(tpRule.isEnd(tpBoard, new PlayerMove(9, 5, plyr1)));
    }

    @Test
    public void isEndPlayer2() {
        Player plyr1 = new HumanPlayer(true);
        Player plyr2 = new HumanPlayer(false);
        Rule tpRule = new TwoPlayerRule();
        Board tpBoard = new TwoPlayerBoard(15);
        tpBoard.placeMove(new PlayerMove(5,5,plyr1));
        tpBoard.placeMove(new PlayerMove(6,5,plyr1));
        tpBoard.placeMove(new PlayerMove(7,5,plyr1));
        tpBoard.placeMove(new PlayerMove(8,5,plyr1));
        tpBoard.placeMove(new PlayerMove(5,4,plyr2));
        tpBoard.placeMove(new PlayerMove(6,4,plyr2));
        tpBoard.placeMove(new PlayerMove(7,4,plyr2));
        tpBoard.placeMove(new PlayerMove(8,4,plyr2));
        assertFalse(tpRule.isEnd(tpBoard, new PlayerMove(9, 5, plyr2)));
    }

    @Test
    public void isValidMovePlayer1() {
        Player plyr1 = new HumanPlayer(true);
        Player plyr2 = new HumanPlayer(false);
        Rule tpRule = new TwoPlayerRule();
        Board tpBoard = new TwoPlayerBoard(15);
        tpBoard.placeMove(new PlayerMove(5,5,plyr1));
        tpBoard.placeMove(new PlayerMove(6,5,plyr1));
        tpBoard.placeMove(new PlayerMove(7,5,plyr1));
        tpBoard.placeMove(new PlayerMove(8,5,plyr1));
        tpBoard.placeMove(new PlayerMove(5,4,plyr2));
        tpBoard.placeMove(new PlayerMove(6,4,plyr2));
        tpBoard.placeMove(new PlayerMove(7,4,plyr2));
        tpBoard.placeMove(new PlayerMove(8,4,plyr2));
        assertFalse(tpRule.isValidMove(tpBoard, new PlayerMove(5,5,plyr1)));
    }

    @Test
    public void isValidMovePlayer2() {
        Player plyr1 = new HumanPlayer(true);
        Player plyr2 = new HumanPlayer(false);
        Rule tpRule = new TwoPlayerRule();
        Board tpBoard = new TwoPlayerBoard(15);
        tpBoard.placeMove(new PlayerMove(5,5,plyr1));
        tpBoard.placeMove(new PlayerMove(6,5,plyr1));
        tpBoard.placeMove(new PlayerMove(7,5,plyr1));
        tpBoard.placeMove(new PlayerMove(8,5,plyr1));
        tpBoard.placeMove(new PlayerMove(5,4,plyr2));
        tpBoard.placeMove(new PlayerMove(6,4,plyr2));
        tpBoard.placeMove(new PlayerMove(7,4,plyr2));
        tpBoard.placeMove(new PlayerMove(8,4,plyr2));
        assertFalse(tpRule.isValidMove(tpBoard, new PlayerMove(5,5,plyr2)));
        assertTrue(tpRule.isValidMove(tpBoard, new PlayerMove(4,4,plyr2)));
        assertTrue(tpRule.isValidMove(tpBoard, new PlayerMove(9,5,plyr2)));
    }
}