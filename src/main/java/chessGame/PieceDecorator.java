package chessGame;

import chessLib.Position;
import java.util.Collection;

/**
 * Piece装饰器的抽象基类，实现Piece接口并持有一个Piece引用
 * 遵循装饰器模式，允许动态地向棋子对象添加额外的责任
 */
public abstract class PieceDecorator implements Piece {
    protected Piece decoratedPiece;
    
    /**
     * 构造函数，接收被装饰的Piece对象
     * @param decoratedPiece 要被装饰的Piece对象
     */
    public PieceDecorator(Piece decoratedPiece) {
        this.decoratedPiece = decoratedPiece;
    }
    
    /**
     * 委托给被装饰对象的validMovesFor方法
     */
    @Override
    public Collection<Position> validMovesFor(Position pos) {
        return decoratedPiece.validMovesFor(pos);
    }
    
    /**
     * 委托给被装饰对象的getType方法
     */
    @Override
    public String getType() {
        return decoratedPiece.getType();
    }
}