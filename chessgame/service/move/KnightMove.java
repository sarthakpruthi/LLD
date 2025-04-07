package service.move;

import entity.Board;

public class KnightMove implements Move{
    @Override
    public boolean canMove(Board board, int sRow, int sCol, int eRow, int eCol) {
        int rDiff = Math.abs(sRow - eRow), cDiff = Math.abs(sCol - eCol);
        return (rDiff == 2 && cDiff == 1) || (rDiff == 1 && cDiff == 2);
    }
}
