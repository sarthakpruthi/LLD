package entity;

import enums.Color;
import enums.PieceType;
import service.move.Move;

public class Piece {
    Color color;
    PieceType type;

    public Move[] getMoves() {
        return moves;
    }

    public void setMoves(Move[] moves) {
        this.moves = moves;
    }

    Move[] moves;


    public Piece(Color color, PieceType type, Move[] moves) {
        this.color = color;
        this.type = type;
        this.moves = moves;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public PieceType getType() {
        return type;
    }

    public void setType(PieceType type) {
        this.type = type;
    }
}
