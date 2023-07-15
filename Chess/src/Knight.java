/**
 * Knight Piece. Inherited from the abstract class Piece
 * Includes string name "wN" if it belongs to the white team
 * Includes string name "bN" if it belongs to the black team
 *
 * @author Aarushi Vashistha
 * @author Riddhi Patel
 */
public class Knight extends Piece {
    /**
     * Knight constructor, initializes Knight referencing superclass Piece
     * @param row  x coordinate of the designated spot
     * @param col  y coordinate of the designated spot
     * @param isWhite
     *
     * isWhite = true means the piece is a part of the white team
     * isWhite = false means the piece is a part of the black team
     */
    public Knight(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
    }

    @Override
    public String toString() {
        if(isWhite) return "wN";
        return "bN";
    }

    @Override
    public boolean canMoveTo(Piece[][] board, int newRow, int newCol) {
        if (board[newRow][newCol]!=null && board[newRow][newCol].isWhite==isWhite) return false;
        if (isValidSquare(newRow,newCol)) {
            if (Math.abs(newRow-row)==1 && Math.abs(newCol-col)==2)  return true;
            if (Math.abs(newRow-row)==2 && Math.abs(newCol-col)==1) return true;
        }
        return false;
    }
}



