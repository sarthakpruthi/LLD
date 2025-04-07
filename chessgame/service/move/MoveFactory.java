package service.move;

public class MoveFactory {
    public Move[] getMove(char piece){
        switch (piece){
            case 'Q' :{
                DiagonalMove dMove = new DiagonalMove();
                StraightMove sMove = new StraightMove();
                return new Move[]{dMove, sMove};
            }
            case 'K' :{
                Move move = new KingMove();
                return new Move[]{move};
            }
            case 'H': {
                Move move = new KnightMove();
                return new Move[]{move};
            }
            case 'P': {
                Move move = new PawnMove();
                return new Move[]{move};
            }
            case 'B': {
                Move move = new DiagonalMove();
                return new Move[]{move};
            }
            case 'R': {
                Move move = new StraightMove();
                return new Move[]{move};
            }
            default: return null;
        }
    }
}
