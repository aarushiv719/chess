/**
 * Pawn Piece. Inherited from the abstract class Piece
 * Includes string name "wP" if it belongs to the white team
 * Includes string name "bP" if it belongs to the black team
 *
 * @author Aarushi Vashistha
 * @author Riddhi Patel
 */
public class Pawn extends Piece {
    /**
     * Pawn constructor, initializes Pawns referencing superclass Piece
     * @param row  x coordinate of the designated spot
     * @param col  y coordinate of the designated spot
     * @param isWhite
     *
     * isWhite = true means the piece is a part of the white team
     * isWhite = false means the piece is a part of the black team
     */
    public Pawn(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
    }

    @Override
    public String toString() {
        if(isWhite) return "wP";
        return "bP";
    }

    @Override
    public boolean canMoveTo(Piece[][] board, int newRow, int newCol) {
        if (board[newRow][newCol]!=null && board[newRow][newCol].isWhite==isWhite) return false;
        if (!isValidSquare(newRow, newCol)) return false;
        if(isWhite) {
            if(newRow==row-1 && newCol==col && board[newRow][newCol]==null) return true;
            if (row==6 && board[newRow][newCol]==null && newRow < 7 && board[newRow+1][newCol]==null) {
                if(newRow==row-2 && newCol==col) return true;
            }
            if (newRow==row-1 && Math.abs(newCol-col)==1 && board[newRow][newCol]!=null) return true;

        } else {
            if(newRow==row+1 && newCol==col && board[newRow][newCol]==null) return true;
            if (row==1 && board[newRow][newCol]==null && newRow > 0 && board[newRow-1][newCol]==null) {
                if(newRow==row+2 && newCol==col) return true;
            }
            if (newRow==row+1 && Math.abs(newCol-col)==1 && board[newRow][newCol]!=null) return true;
        }
        return false;
    }
}
