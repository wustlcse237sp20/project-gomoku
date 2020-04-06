package com.homework.gomoku.game;

public class TwoPlayerRule implements Rule{
    
    @Override
    public boolean isEnd(Board board, Move move){
        return helper(board, move, 1, 0) || helper(board, move, 1, 1) || helper(board, move, 0, 1) || helper(board, move, 1, -1);
    }

    public boolean helper(Board board, Move move, int colIncrement, int rowIncrement){
        boolean end = false;
        int col = move.getCol();
        int row = move.getRow();
        boolean playerColor = move.getPlayer().getColor();
        int boardSize = board.getBoardSize();
        int count = 1;
        boolean cont1 = true;
        boolean cont2 = true;
        Move[][] grid = board.getBoardGrid();
        for (int i = 1; i < 5 ; i++){
            if (cont1 && col + i*colIncrement < boardSize && col + i*colIncrement >= 0 && row + i*rowIncrement < boardSize && row + i*rowIncrement >= 0){
                if(grid[row + i*rowIncrement][col + i*colIncrement] == null){
                    cont1 = false;
                }
                else if (playerColor == grid[row + i*rowIncrement][col + i*colIncrement].getPlayer().getColor()){
                    count++;
                }else{
                    cont1 = false;
                }
            }
            if (cont2 && col - i*colIncrement < boardSize && col - i*colIncrement >= 0 && row - i*rowIncrement < boardSize && row - i*rowIncrement >= 0){
                if(grid[row - i*rowIncrement][col - i*colIncrement] == null){
                    cont2 = false;
                }
                else if (playerColor == grid[row - i*rowIncrement][col - i*colIncrement].getPlayer().getColor()){
                    count++;
                }else{
                    cont2 = false;
                }
            }
            if (count >= 5){
                return true;
            }
        }
        return end;
    }

    @Override
    public boolean isValidMove(Board board, Move move){
        return isOverlap(board, move) && ruleBreaker(board, move);
    }

    public boolean isOverlap(Board board, Move move){
        if (board.getPieceAt(move.getRow(), move.getCol()) != null){
            return false;
        }
        return true;
    }

    public boolean ruleBreaker(Board board, Move move){
        return true;
    }
}