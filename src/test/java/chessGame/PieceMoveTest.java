package chessGame;

import chessLib.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

public class PieceMoveTest {

    @Test
    public void testKnightMoves() {
        KnightMovePiece knight = new KnightMovePiece();
        Position pos = new Position(3, 3);
        Collection<Position> moves = knight.validMovesFor(pos);

        // A knight should have 8 possible moves from the center
        assertEquals(8, moves.size());

        // Check some specific moves
        assertTrue(moves.contains(new Position(1, 2)));
        assertTrue(moves.contains(new Position(1, 4)));
        assertTrue(moves.contains(new Position(2, 1)));
        assertTrue(moves.contains(new Position(2, 5)));
        assertTrue(moves.contains(new Position(4, 1)));
        assertTrue(moves.contains(new Position(4, 5)));
        assertTrue(moves.contains(new Position(5, 2)));
        assertTrue(moves.contains(new Position(5, 4)));
    }

    @Test
    public void testBishopMoves() {
        BishopMove bishop = new BishopMove();
        Position pos = new Position(3, 3);
        Collection<Position> moves = bishop.validMovesFor(pos);

        // A bishop should have 13 possible moves from the center
        assertEquals(13, moves.size());

        // Check some specific moves
        assertTrue(moves.contains(new Position(0, 0)));
        assertTrue(moves.contains(new Position(1, 1)));
        assertTrue(moves.contains(new Position(2, 2)));
        assertTrue(moves.contains(new Position(4, 4)));
        assertTrue(moves.contains(new Position(5, 5)));
        assertTrue(moves.contains(new Position(6, 6)));
        assertTrue(moves.contains(new Position(7, 7)));
        assertTrue(moves.contains(new Position(0, 6)));
        assertTrue(moves.contains(new Position(1, 5)));
        assertTrue(moves.contains(new Position(2, 4)));
        assertTrue(moves.contains(new Position(4, 2)));
        assertTrue(moves.contains(new Position(5, 1)));
        assertTrue(moves.contains(new Position(6, 0)));
    }

    @Test
    public void testQueenMoves() {
        QueenMove queen = new QueenMove();
        Position pos = new Position(3, 3);
        Collection<Position> moves = queen.validMovesFor(pos);

        // A queen should have 27 possible moves from the center
        assertEquals(27, moves.size());

        // Check some specific moves
        // Horizontal moves
        assertTrue(moves.contains(new Position(3, 0)));
        assertTrue(moves.contains(new Position(3, 1)));
        assertTrue(moves.contains(new Position(3, 2)));
        assertTrue(moves.contains(new Position(3, 4)));
        assertTrue(moves.contains(new Position(3, 5)));
        assertTrue(moves.contains(new Position(3, 6)));
        assertTrue(moves.contains(new Position(3, 7)));

        // Vertical moves
        assertTrue(moves.contains(new Position(0, 3)));
        assertTrue(moves.contains(new Position(1, 3)));
        assertTrue(moves.contains(new Position(2, 3)));
        assertTrue(moves.contains(new Position(4, 3)));
        assertTrue(moves.contains(new Position(5, 3)));
        assertTrue(moves.contains(new Position(6, 3)));
        assertTrue(moves.contains(new Position(7, 3)));

        // Diagonal moves
        assertTrue(moves.contains(new Position(0, 0)));
        assertTrue(moves.contains(new Position(1, 1)));
        assertTrue(moves.contains(new Position(2, 2)));
        assertTrue(moves.contains(new Position(4, 4)));
        assertTrue(moves.contains(new Position(5, 5)));
        assertTrue(moves.contains(new Position(6, 6)));
        assertTrue(moves.contains(new Position(7, 7)));
        assertTrue(moves.contains(new Position(0, 6)));
        assertTrue(moves.contains(new Position(1, 5)));
        assertTrue(moves.contains(new Position(2, 4)));
        assertTrue(moves.contains(new Position(4, 2)));
        assertTrue(moves.contains(new Position(5, 1)));
        assertTrue(moves.contains(new Position(6, 0)));
    }
}