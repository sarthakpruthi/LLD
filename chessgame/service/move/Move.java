package service.move;

import entity.Board;

public interface Move {
    boolean canMove(Board board, int sRow, int sCol, int eRow, int eCol);
}
