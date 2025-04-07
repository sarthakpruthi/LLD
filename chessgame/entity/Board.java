package entity;

import enums.Color;
import enums.PieceType;
import service.PieceFactory;
import service.move.Move;
import service.move.MoveFactory;

import java.util.Objects;

public class Board {
    Piece[][] board;
    PieceFactory factory;

    public Board(String[][] arr) {
        factory = new PieceFactory();
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(Objects.equals(arr[i][j], "")) continue;
                char color = arr[i][j].charAt(0), piece = arr[i][j].charAt(1);
                board[i][j] = factory.createPiece(color, piece);
            }
        }
    }

    public Piece[][] getBoard(){
        return board;
    }
}
