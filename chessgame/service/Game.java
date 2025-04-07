package service;

import entity.Board;
import entity.Piece;
import enums.Color;
import enums.PieceType;
import service.move.Move;

import static service.CommonUtil.isValid;

public class Game {

    Game game;
    Board board;

    Color turn;

    Color winner;

    private Game(){
        turn = Color.BLACK;
        winner = null;
        String[][] arr = {
                {"WR", "WH", "WB", "WQ", "WK", "WB", "WH", "WR"},
                {"WP", "WP", "WP", "WP", "WP", "WP", "WP", "WP"},
                {"",   "",   "",   "",   "",   "",   "",   ""},
                {"",   "",   "",   "",   "",   "",   "",   ""},
                {"",   "",   "",   "",   "",   "",   "",   ""},
                {"",   "",   "",   "",   "",   "",   "",   ""},
                {"BP", "BP", "BP", "BP", "BP", "BP", "BP", "BP"},
                {"BR", "BH", "BB", "BQ", "BK", "BB", "BH", "BR"}
        };
        board = new Board(arr);
    }

    static class GameInstance{
        static final Game game = new Game();
    }

    public static Game getInstance(){
        return GameInstance.game;
    }

    public void playGame(){
        while(winner==null){

        }
        System.out.println("Winner is " + winner.name());
    }

    public void move(int sRow,int sCol,int eRow,int eCol){
        if(!isValid(eRow,eCol)){
            System.out.println("invalid move to " + eRow + " " + eCol);
            return;
        }
        Piece[][] arr = board.getBoard();
        Piece sPiece = arr[sRow][sCol];
        Piece ePiece = arr[eRow][eCol];
        if(ePiece!=null && ePiece.getColor().equals(sPiece.getColor())){
            System.out.println("same color at " + eRow + " " + eCol);
            return;
        }
        boolean canMove = false;
        for(Move move: sPiece.getMoves()){
            if(move.canMove(board,sRow, sCol, eRow, eCol)){
                canMove = true;
                break;
            }
        }
        if(canMove) {
            arr[sRow][sCol] = null;
            arr[eRow][eCol] = sPiece;
            if(ePiece!=null && ePiece.getType().equals(PieceType.KING)) {
                winner = turn;
            }
        }

        turn = (turn == Color.BLACK) ? Color.WHITE : Color.BLACK;
    }
}
