package chessGame;

import chessLib.Position;
import java.util.Collection;

/**
 * 日志装饰器，用于记录棋子的移动信息
 */
public class LoggingPiece extends PieceDecorator {
    
    /**
     * 构造函数，接收被装饰的Piece对象
     * @param decoratedPiece 要被装饰的Piece对象
     */
    public LoggingPiece(Piece decoratedPiece) {
        super(decoratedPiece);
    }
    
    /**
     * 重写validMovesFor方法，添加日志记录功能
     */
    @Override
    public Collection<Position> validMovesFor(Position pos) {
        System.out.println("[LOG] Calculating moves for " + decoratedPiece.getType() + " at position (" + 
                          pos.x() + ", " + pos.y() + ")");
        
        // 调用被装饰对象的方法
        Collection<Position> moves = super.validMovesFor(pos);
        
        System.out.println("[LOG] Found " + moves.size() + " valid moves for " + decoratedPiece.getType());
        return moves;
    }
    
    /**
     * 重写getType方法，标识这是一个带日志功能的棋子
     */
    @Override
    public String getType() {
        return "Logging[" + super.getType() + "]";
    }
}