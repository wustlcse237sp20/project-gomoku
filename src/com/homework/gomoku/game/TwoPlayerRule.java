package com.homework.gomoku.game;

public class TwoPlayerRule implements Rule {

    enum Direction {
        EAST, NORTH, NORTHEAST, NORTHWEST
    }

    @Override
    public boolean isEnd(Board board, Move move) {
        return isFiveOnDir(board, move, Direction.EAST) ||
                isFiveOnDir(board, move, Direction.NORTHEAST) ||
                isFiveOnDir(board, move, Direction.NORTH) ||
                isFiveOnDir(board, move, Direction.NORTHWEST);
    }

    private boolean isInBoard(Board board, int row, int col) {
        return row < board.getBoardSize() && row > 0 && col < board.getBoardSize() && col >= 0;
    }

    public boolean isFiveOnDir(Board board, Move move, Direction dir) {
        int rowIncrement = 0;
        int colIncrement = 0;
        switch (dir) {
            case EAST:
                rowIncrement = 1;
                break;
            case NORTH:
                colIncrement = 1;
                break;
            case NORTHEAST:
                rowIncrement = 1;
                colIncrement = 1;
                break;
            case NORTHWEST:
                rowIncrement = 1;
                colIncrement = -1;
        }
        int col = move.getCol();
        int row = move.getRow();
        boolean playerColor = move.getPlayer().getColor();
        int count = 1;
        for (int i = 1; i < 5; i++) {
            int nei_row = row + i * rowIncrement;
            int nei_col = col + i * colIncrement;
            if (isInBoard(board, nei_row, nei_col) && board.getPieceAt(nei_row, nei_col) != null
                    && board.getPieceAt(nei_row, nei_col).getPlayer().getColor() == playerColor) {
                count++;
            } else {
                break;
            }
        }
        for (int i = 1; i < 5; i++) {
            int nei_row = row - i * rowIncrement;
            int nei_col = col - i * colIncrement;
            if (isInBoard(board, nei_row, nei_col) && board.getPieceAt(nei_row, nei_col) != null
                    && board.getPieceAt(nei_row, nei_col).getPlayer().getColor() == playerColor) {
                count++;
            } else {
                break;
            }
        }
        return count == 5;
    }

    @Override
    public boolean isValidMove(Board board, Move move) {
        return isOverlap(board, move) && ruleBreaker(board, move);
    }

    public boolean isOverlap(Board board, Move move) {
        return board.getPieceAt(move.getRow(), move.getCol()) == null;
    }

    public boolean ruleBreaker(Board board, Move move) {
        return true;
    }
}