package com.homework.gomoku.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class GomokuUtilTest {

    @Test
    public void isInBoardTest() {
        Board board = new TwoPlayerBoard(15);
        assertFalse(GomokuUtil.isInBoard(board, 0, 0));
        assertFalse(GomokuUtil.isInBoard(board, 0, 14));
        assertFalse(GomokuUtil.isInBoard(board, 14, 0));
        assertTrue(GomokuUtil.isInBoard(board, 14, 14));
        assertTrue(GomokuUtil.isInBoard(board, 7, 6));
        assertFalse(GomokuUtil.isInBoard(board, -1, 0));
        assertFalse(GomokuUtil.isInBoard(board, 0, -0));
        assertFalse(GomokuUtil.isInBoard(board, 0, 15));
        assertFalse(GomokuUtil.isInBoard(board, 15, 0));
    }

    @Test
    public void isPieceOnDirTest() {
        Board board = new TwoPlayerBoard(15);
        Player nullPlayer = new HumanPlayer(true);
        board.placeMove(new PlayerMove(5, 5, nullPlayer));
        board.placeMove(new PlayerMove(5, 6, nullPlayer));
        board.placeMove(new PlayerMove(5, 7, nullPlayer));
        assertTrue(GomokuUtil.isPieceOnDir(board, board.getLasMove(), GomokuUtil.Direction.NORTH, 3));
        assertFalse(GomokuUtil.isPieceOnDir(board, board.getLasMove(), GomokuUtil.Direction.EAST, 3));
        assertFalse(GomokuUtil.isPieceOnDir(board, board.getLasMove(), GomokuUtil.Direction.EAST, 4));
    }

    @Test
    public void gridRoundTest1() {
        int index = GomokuUtil.gridRound(100, 0, 10);
        assertTrue(index == 0);
        assertFalse(index > 0 || index < 0);
    }

    @Test
    public void gridRoundTest2() {
        int index = GomokuUtil.gridRound(100, 50, 10);
        assertTrue(index == 5);
        assertFalse(index > 5 || index < 5);
    }
}