package chessGame;

import chessLib.Position;
import java.util.ArrayList;
import java.util.Collection;

public class QueenMove implements Piece {
    public final static int[][] HORIZONTAL_MOVES = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public final static int[][] DIAGONAL_MOVES = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    @Override
    public Collection<Position> validMovesFor(Position pos) {
        Collection<Position> moves = new ArrayList<>();
        int x = pos.x();
        int y = pos.y();

        // Add horizontal and vertical moves
        for (int[] move : HORIZONTAL_MOVES) {
            int dx = move[0];
            int dy = move[1];
            int newX = x + dx;
            int newY = y + dy;

            while (isValidPosition(newX, newY)) {
                moves.add(new Position(newX, newY));
                newX += dx;
                newY += dy;
            }
        }

        // Add diagonal moves
        for (int[] move : DIAGONAL_MOVES) {
            int dx = move[0];
            int dy = move[1];
            int newX = x + dx;
            int newY = y + dy;

            while (isValidPosition(newX, newY)) {
                moves.add(new Position(newX, newY));
                newX += dx;
                newY += dy;
            }
        }

        return moves;
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 1 && x <= 8 && y >= 1 && y <= 8;
    }

    @Override
    public String getType() {
        return "Queen";
    }
}
