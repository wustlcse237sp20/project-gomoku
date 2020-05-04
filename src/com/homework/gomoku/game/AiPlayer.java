package com.homework.gomoku.game;

import static com.homework.gomoku.game.GomokuUtil.isInBoard;
import static com.homework.gomoku.game.GomokuUtil.isPieceOnDir;
import com.homework.gomoku.game.GomokuUtil.Direction;

public class AiPlayer implements Player{
    
    boolean isBlack;

    public AiPlayer(boolean isBlack) {
        this.isBlack = isBlack;
    }

    @Override
    public boolean getColor() {
        return isBlack;
    }

    @Override
    public Move getMove(Game game) {
        Board board = game.getBoard();
        Move move = game.getBoard().getLasMove();
        int[] index;
        Move aiMove;
        for (Direction dir : Direction.values()){
            if (isPieceOnDir(board, move, dir, 3)){
                index = nextAvailableCell(board, move, dir);
                if (isInBoard(board, index[0], index[1])){
                    aiMove = new PlayerMove( index[0], index[1], this);
                    return aiMove;
                }
            }
        }
        for (Direction dir : Direction.values()){
            if (isPieceOnDir(board, move, dir, 2)){
                index = nextAvailableCell(board, move, dir);
                if (isInBoard(board, index[0], index[1])){
                    aiMove = new PlayerMove( index[0], index[1], this);
                    return aiMove;
                }
            }
        }
        for (Direction dir : Direction.values()){
            if (isPieceOnDir(board, move, dir, 1)){
                index = nextAvailableCell(board, move, dir);
                if (isInBoard(board, index[0], index[1])){
                    aiMove = new PlayerMove( index[0], index[1], this);
                    return aiMove;
                }
            }
        }
        aiMove = new PlayerMove(1, 1, this);
        return aiMove;
    }

    private int[] nextAvailableCell(Board board, Move move, Direction dir){
        int[] posIndex = new int[2];
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
        int count1 = 0;
        int count2 = 0;
        boolean validPos1 = false;
        boolean validPos2 = false;
        for (int i = 1; i < 5; i++){
            int nei_row = row + i * rowIncrement;
            int nei_col = col + i * colIncrement;
            count1 ++;
            if (isInBoard(board, nei_row, nei_col) && board.getPieceAt(nei_row, nei_col) != null &&board.getPieceAt(nei_row, nei_col).getPlayer().getColor() == isBlack){
                break;
            }
            if (isInBoard(board, nei_row, nei_col) && board.getPieceAt(nei_row, nei_col) == null){
                validPos1 = true;
                break;
            }
        }
        for (int i = 1; i < 5; i++){
            int nei_row = row - i * rowIncrement;
            int nei_col = col - i * colIncrement;
            count2 --;
            if (isInBoard(board, nei_row, nei_col) && board.getPieceAt(nei_row, nei_col) != null &&board.getPieceAt(nei_row, nei_col).getPlayer().getColor() == isBlack){
                break;
            }
            if (isInBoard(board, nei_row, nei_col) && board.getPieceAt(nei_row, nei_col) == null){
                validPos2 = true;
                break;
            }
        }

        if (!validPos1){
            count1 = board.getBoardSize() + 2;
        }
        if (!validPos2){
            count2 = board.getBoardSize() + 2;
        }
        if (count1 <= count2){
            posIndex[0] = move.getRow() + count1 *rowIncrement;
            posIndex[1] = move.getCol() + count1 *colIncrement;
        } else {
            posIndex[0] = move.getRow() + count2 *rowIncrement;
            posIndex[1] = move.getCol() + count2 *colIncrement;
        }
        return posIndex;
    }
}