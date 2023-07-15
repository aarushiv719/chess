/**
 * Rook Piece. Inherited from the abstract class Piece
 * Includes string name "wR" if it belongs to the white team
 * Includes string name "bR" if it belongs to the black team
 *
 * @author Aarushi Vashistha
 * @author Riddhi Patel
 */

public class Rook extends Piece{
    /**
     * Rook constructor, initializes the Rook referencing superclass Piece
     * @param row  x coordinate of the designated spot
     * @param col  y coordinate of the designated spot
     * @param isWhite
     *
     * isWhite = true means the piece is a part of the white team
     * isWhite = false means the piece is a part of the black team
     */
    public Rook(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
    }

    @Override
    public String toString() {
        if (isWhite) return "wR";
        return "bR";
    }
    @Override
    public boolean canMoveTo(Piece[][] board, int newRow, int newCol) {
        if (board[newRow][newCol]!=null && board[newRow][newCol].isWhite==isWhite) return false;
        if (isValidSquare(newRow,newCol)) {
            if (newCol==col && newRow!=row) {
                int increment;
                if (row < newRow) increment = 1;
                else increment = -1;
                for (int i=row+increment; i!=newRow; i+= increment) {
                    if (board[i][newCol]!=null) {
                        return false;
                    }
                }
                return true;
            }
            if(newRow==row && newCol!=col) {
                int increment;
                if (col < newCol) increment = 1;
                else increment = -1;
                for (int j=col+increment; j!=newCol; j+=increment) {
                    if (board[newRow][j]!=null) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}