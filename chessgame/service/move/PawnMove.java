package service.move;

import entity.Board;
import entity.Piece;

public class PawnMove implements Move{
    @Override
    public boolean canMove(Board board, int sRow, int sCol, int eRow, int eCol) {
        Piece[][] arr = board.getBoard();
        Piece currPiece = arr[sRow][sCol];
        Piece endPiece = arr[eRow][eCol];
        int colDiff = Math.abs(sCol - eCol);
        int rowDiff = Math.abs(sRow - eRow);

        if(endPiece!=null){
            return rowDiff == 1 && colDiff == 1 && (currPiece.getColor() != endPiece.getColor());
        }
        else{
            return rowDiff == 1 && colDiff == 0;
        }
    }
}
