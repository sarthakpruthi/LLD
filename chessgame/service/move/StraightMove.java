package service.move;

import entity.Board;

public class StraightMove implements Move{
    @Override
    public boolean canMove(Board board, int sRow, int sCol, int eRow, int eCol) {
        int colDiff = eCol - sCol;
        int rowDiff = eRow - sRow;

        int colDelta = colDiff > 0 ? 1 : -1;
        int roDelta = rowDiff > 0 ? 1 : -1;
        if(colDiff == 0){
            while(sRow != eRow){
                sRow += roDelta;
                if(board.getBoard()[sRow][sCol]!=null) return false;
            }
            return true;
        }
        else if(rowDiff == 0){
            while(sCol != eCol){
                sCol += colDelta;
                if(board.getBoard()[sRow][sCol]!=null) return false;
            }
            return true;
        }
        return false;
    }
}
