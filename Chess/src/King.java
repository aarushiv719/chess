/**
 * King Piece. Inherited from the abstract class Piece
 * Includes string name "wK" if it belongs to the white team
 * Includes string name "bK" if it belongs to the black team
 *
 * @author Aarushi Vashistha
 * @author Riddhi Patel
 */
public class King extends Piece {
    /**
     * King constructor, initializes the king referencing superclass Piece
     * @param row  x coordinate of the designated spot
     * @param col  y coordinate of the designated spot
     * @param isWhite
     *
     * isWhite = true means the piece is a part of the white team
     * isWhite = false means the piece is a part of the black team
     */
    public King(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
    }
    @Override
    public String toString() {
        if(isWhite) return "wK";
        return "bK";
    }
    @Override
    public boolean canMoveTo(Piece[][] board, int newRow, int newCol) {
        if (board[newRow][newCol]!=null && board[newRow][newCol].isWhite==isWhite) return false;
        if(!isValidSquare(newRow,newCol)) return false;
        if(Math.abs(newRow-row)==1 && Math.abs(newCol-col)==1 || Math.abs(newRow-row)==1 && newCol==col || Math.abs(newCol-col)==1 && newRow==row) return true;

        return false;
    }
}

