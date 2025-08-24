package chessGame;

import chessLib.KnightMove;
import chessLib.Position;
import java.util.Collection;

public class KnightMovePiece implements Piece {
    private final KnightMove knightMove;

    public KnightMovePiece() {
        this.knightMove = new KnightMove();
    }

    @Override
    public Collection<Position> validMovesFor(Position pos) {
        return knightMove.validMovesFor(pos);
    }

    @Override
    public String getType() {
        return "Knight";
    }
}