package com.homework.gomoku.game;

import java.io.Serializable;

import static com.homework.gomoku.game.GomokuUtil.isInBoard;
import static com.homework.gomoku.game.GomokuUtil.isPieceOnDir;
import com.homework.gomoku.game.GomokuUtil.Direction;

public class TwoPlayerRule implements Rule, Serializable {

    @Override
    public boolean isEnd(Board board, Move move) {
        return isPieceOnDir(board, move, Direction.EAST, 5) ||
                isPieceOnDir(board, move, Direction.NORTHEAST, 5) ||
                isPieceOnDir(board, move, Direction.NORTH, 5) ||
                isPieceOnDir(board, move, Direction.NORTHWEST, 5);
    }

    @Override
    public boolean isValidMove(Board board, Move move) {
        return isOverlap(board, move) && ruleBreaker(board, move) && isInBoard(board, move.getRow(), move.getCol());
    }

    public boolean isOverlap(Board board, Move move) {
        return board.getPieceAt(move.getRow(), move.getCol()) == null;
    }

    public boolean ruleBreaker(Board board, Move move) {
        return true;
    }
}