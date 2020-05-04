package com.homework.gomoku.game;

public class AiPlayer implements Player{
    
    boolean isBlack;
    int[] rowSeq;
    int[] colSeq;
    enum Direction {
        EAST, NORTH, NORTHEAST, NORTHWEST
    }

    public AiPlayer(boolean isBlack) {
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
        // int step = game.getNumTurn()/2;
        // System.out.println(step);
        // return new PlayerMove(rowSeq[step], colSeq[step], this);
        // int col = game.getBoard().getLasMove().getCol();
        // int row = game.getBoard().getLasMove().getRow();
        Board board = game.getBoard();
        Move move = game.getBoard().getLasMove();
        int[] index = new int[2];
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

    private boolean isPieceOnDir(Board board, Move move, Direction dir, int num) {
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

    private boolean isInBoard(Board board, int row, int col) {
        return row < board.getBoardSize() && row > 0 && col < board.getBoardSize() && col >= 0;
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
        boolean playerColor = move.getPlayer().getColor();
        boolean AiColor = !playerColor;
        for (int i = 1; i < 5; i++){
            int nei_row = row + i * rowIncrement;
            int nei_col = col + i * colIncrement;
            count1 ++;
            if (isInBoard(board, nei_row, nei_col) && board.getPieceAt(nei_row, nei_col) != null &&board.getPieceAt(nei_row, nei_col).getPlayer().getColor() == AiColor){
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
            count2 ++;
            if (isInBoard(board, nei_row, nei_col) && board.getPieceAt(nei_row, nei_col) != null &&board.getPieceAt(nei_row, nei_col).getPlayer().getColor() == AiColor){
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