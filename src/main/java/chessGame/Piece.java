package chessGame;

import chessLib.Position;
import java.util.Collection;

public interface Piece {
    Collection<Position> validMovesFor(Position pos);
    String getType();
}