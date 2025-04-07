package service.move;

import entity.Board;

public class KingMove implements Move{
    @Override
    public boolean canMove(Board board, int sRow, int sCol, int eRow, int eCol) {
        int rDiff = Math.abs(sRow - eRow), cDiff = Math.abs(sCol - eCol);
        return !(sRow == eRow && sCol == eCol) && (rDiff <= 1 && cDiff <= 1);
    }
}
