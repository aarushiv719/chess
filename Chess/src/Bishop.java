/**
 * Bishop Piece. Inherited from the abstract class Piece
 * Includes string name "wB" if it belongs to the white team
 * Includes string name "bB" if it belongs to the black team
 *
 * @author Aarushi Vashistha
 * @author Riddhi Patel
 */
public class Bishop extends Piece{
    /**
     * Bishop constructor, initializes the Bishop referencing superclass Piece
     * @param row  x coordinate of the designated spot
     * @param col  y coordinate of the designated spot
     * @param isWhite
     *
     * isWhite = true means the piece is a part of the white team
     * isWhite = false means the piece is a part of the black team
     */
    public Bishop(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
    }

    @Override
    public String toString() {
        if(isWhite) { return "wB"; }
        return "bB";
    }
    @Override
    public boolean canMoveTo(Piece[][] board, int newRow, int newCol) {
        if (board[newRow][newCol]!=null && board[newRow][newCol].isWhite==isWhite) return false;
        if (row == newRow || col == newCol) return false;
       if (isValidSquare(newRow,newCol)) {
           int rowIncrement, colIncrement;
           if (newRow + newCol == row + col) {
               if (row < newRow) {
                   rowIncrement = 1;
                   colIncrement = -1;
               }
               else {
                   rowIncrement = -1;
                   colIncrement = 1;
               }
           }
           else if (newRow - newCol == row - col) {
                if (row < newRow) {
                    rowIncrement = 1;
                    colIncrement = 1;
                }
                else {
                    rowIncrement = -1;
                    colIncrement = -1;
                }
           }
           else return false;

           for (int i = row+rowIncrement, j = col+colIncrement; i != newRow && j != newCol; i += rowIncrement, j += colIncrement) {
               if (board[i][j] != null) return false;
           }
           return true;
       }
       return false;
    }
}

