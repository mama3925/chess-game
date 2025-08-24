package chessGame;

import chessLib.Position;
import java.util.Collection;

/**
 * 计分装饰器，用于为棋子的移动计算得分
 */
public class ScoringPiece extends PieceDecorator {
    
    private int score = 0;
    
    /**
     * 构造函数，接收被装饰的Piece对象
     * @param decoratedPiece 要被装饰的Piece对象
     */
    public ScoringPiece(Piece decoratedPiece) {
        super(decoratedPiece);
    }
    
    /**
     * 重写validMovesFor方法，添加计分功能
     */
    @Override
    public Collection<Position> validMovesFor(Position pos) {
        Collection<Position> moves = super.validMovesFor(pos);
        
        // 根据有效移动数量增加分数
        int moveBonus = moves.size();
        score += moveBonus;
        
        System.out.println("[SCORE] " + super.getType() + " gained " + moveBonus + 
                          " points for valid moves. Total: " + score);
        
        return moves;
    }
    
    /**
     * 获取当前分数
     * @return 当前分数值
     */
    public int getScore() {
        return score;
    }
    
    /**
     * 重写getType方法，标识这是一个带计分功能的棋子
     */
    @Override
    public String getType() {
        return "Scoring[" + super.getType() + "]";
    }
}