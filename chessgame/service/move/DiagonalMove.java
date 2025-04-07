package service.move;

import entity.Board;

import static service.CommonUtil.isValid;

public class DiagonalMove implements Move{
    @Override
    public boolean canMove(Board board, int sRow, int sCol, int eRow, int eCol) {
        int rDiff = eRow - sRow;
        int cDiff = eCol - sCol;
        int rDelta = rDiff > 0 ? 1 : -1;
        int cDelta = cDiff > 0 ? 1 : -1;

        while(isValid(sRow, sCol) &&  !(sRow == eRow && sCol == eCol)){
            sRow += rDelta;
            sCol += cDelta;
        }
        return sRow == eRow && sCol == eCol;
    }
}
