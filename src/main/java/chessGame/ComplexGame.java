package chessGame;

import chessLib.Position;
import java.util.*;

public class ComplexGame extends BaseGame {
    private final Piece[][] board;
    private final Map<Position, Piece> piecePositions;
    private final Random random;

    public ComplexGame() {
        this.board = new Piece[8][8];
        this.piecePositions = new HashMap<>();
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void setup() {
        // Clear the board
        for (int i = 0; i < 8; i++) {
            Arrays.fill(board[i], null);
        }
        piecePositions.clear();

        System.out.println("Setting up game with decorated pieces...\n");
        
        // Place predefined pieces with decorators
        // Knight at (1, 1) - with logging only
        Piece knight1 = new KnightMovePiece();
        knight1 = new LoggingPiece(knight1);
        placePiece(new Position(1, 1), knight1);

        // Bishop at (3, 3) - with scoring only
        Piece bishop1 = new BishopMove();
        bishop1 = new ScoringPiece(bishop1);
        placePiece(new Position(3, 3), bishop1);

        // Queen at (5, 5) - with both logging and scoring (decorator stacking)
        Piece queen1 = new QueenMove();
        queen1 = new LoggingPiece(queen1);
        queen1 = new ScoringPiece(queen1);
        placePiece(new Position(5, 5), queen1);

        // Additional pieces with different decorator combinations
        // Knight at (8, 8) - with both logging and scoring
        Piece knight2 = new KnightMovePiece();
        knight2 = new ScoringPiece(knight2);
        knight2 = new LoggingPiece(knight2);
        placePiece(new Position(8, 8), knight2);
        
        // Bishop at (5, 1) - with logging only
        Piece bishop2 = new BishopMove();
        bishop2 = new LoggingPiece(bishop2);
        placePiece(new Position(5, 1), bishop2);
        
        // Queen at (1, 8) - with scoring only
        Piece queen2 = new QueenMove();
        queen2 = new ScoringPiece(queen2);
        placePiece(new Position(1, 8), queen2);

        // Print initial board state
        printBoard();
    }

    private void placePiece(Position pos, Piece piece) {
        int x = pos.x();
        int y = pos.y();

        if (isValidPosition(x, y)) {
            // 调整为数组索引（0-7）
            board[x-1][y-1] = piece;
            piecePositions.put(pos, piece);
        }
    }

    @Override
    public void play(int moves) {
        for (int i = 1; i <= moves; i++) {
            System.out.println("\nMove " + i + ":");

            // Check if there are any pieces to move
            if (piecePositions.isEmpty()) {
                System.out.println("No pieces left to move!");
                break;
            }

            // Randomly select a piece
            List<Position> positions = new ArrayList<>(piecePositions.keySet());
            int randomIndex = random.nextInt(positions.size());
            Position currentPos = positions.get(randomIndex);
            Piece piece = piecePositions.get(currentPos);

            System.out.println("Selected " + piece.getType() + " at position " + currentPos);

            // Get valid moves for the selected piece
            Collection<Position> validMoves = piece.validMovesFor(currentPos);

            // Filter out occupied positions
            List<Position> availableMoves = new ArrayList<>();
            for (Position move : validMoves) {
                // 调整为数组索引（0-7）
                if (board[move.x()-1][move.y()-1] == null) {
                    availableMoves.add(move);
                }
            }

            if (availableMoves.isEmpty()) {
                System.out.println("No available moves for this piece!");
                continue;
            }

            // Randomly select a move
            Position newPos = availableMoves.get(random.nextInt(availableMoves.size()));

            // Move the piece
            // 调整为数组索引（0-7）
            board[currentPos.x()-1][currentPos.y()-1] = null;
            board[newPos.x()-1][newPos.y()-1] = piece;
            piecePositions.remove(currentPos);
            piecePositions.put(newPos, piece);

            System.out.println("Moved to " + newPos);

            // Print board state after move
            printBoard();
        }
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 1 && x <= 8 && y >= 1 && y <= 8;
    }

    /**
     * 获取原始棋子类型，穿透装饰器层
     */
    private Piece getOriginalPiece(Piece piece) {
        // 递归获取被装饰的原始棋子
        while (piece instanceof PieceDecorator) {
            piece = ((PieceDecorator) piece).decoratedPiece;
        }
        return piece;
    }
    
    private void printBoard() {
        System.out.println("  0 1 2 3 4 5 6 7");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece == null) {
                    System.out.print(". ");
                } else {
                    // 获取原始棋子类型
                    Piece originalPiece = getOriginalPiece(piece);
                    String symbol;
                    
                    if (originalPiece instanceof KnightMovePiece) {
                        symbol = "K";
                    } else if (originalPiece instanceof BishopMove) {
                        symbol = "B";
                    } else if (originalPiece instanceof QueenMove) {
                        symbol = "Q";
                    } else {
                        symbol = "?";
                    }
                    
                    // 添加装饰器标记
                    if (piece instanceof ScoringPiece) {
                        symbol = symbol + "+";
                    } else if (piece instanceof LoggingPiece) {
                        symbol = symbol + "!";
                    }
                    
                    System.out.print(symbol + " ");
                }
            }
            System.out.println();
        }
        System.out.println("\nLegend: K=Knight, B=Bishop, Q=Queen");
        System.out.println("Symbols: + (scoring), ! (logging), * (both scoring and logging)");
    }
}