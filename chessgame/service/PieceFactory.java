package service;

import entity.Piece;
import enums.Color;
import enums.PieceType;
import service.move.Move;
import service.move.MoveFactory;

public class PieceFactory {

    MoveFactory factory;

    public PieceFactory(){
        factory = new MoveFactory();
    }

    public Piece createPiece(char color, char type){
        Color currColor = (color == 'W') ? Color.WHITE : Color.BLACK;
        switch (type){
            case 'Q': return new Piece(currColor, PieceType.QUEEN, factory.getMove(type));
            case 'K': return new Piece(currColor, PieceType.KING, factory.getMove(type));
            case 'B': return new Piece(currColor, PieceType.BISHOP, factory.getMove(type));
            case 'P': return new Piece(currColor, PieceType.PAWN, factory.getMove(type));
            case 'H': return new Piece(currColor, PieceType.KNIGHT, factory.getMove(type));
            case 'R': return new Piece(currColor, PieceType.ROOK, factory.getMove(type));
            default: return null;
        }
    }
}
