package com.homework.gomoku.game;

public class GomokuUtil {

    public enum Direction {
        EAST, NORTH, NORTHEAST, NORTHWEST
    }

    public static boolean isInBoard(Board board, int row, int col) {
        return row < board.getBoardSize() && row > 0 && col < board.getBoardSize() && col > 0;
    }

    public static boolean isPieceOnDir(Board board, Move move, Direction dir, int num) {
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
        return count >= num;
    }

    public static int gridRound(int length, int loc, int numInt) {
        int rem = length % numInt;
        int gap = length / numInt;
        return loc > rem * (gap + 1) ? (int) Math.round((double) loc / (gap + 1)) :
                (int) Math.round((double) (loc - rem * (gap + 1)) / gap) + rem;
    }
}
