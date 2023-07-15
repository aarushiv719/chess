/**
 * Abstract class that represents the general characteristics of all the chess pieces.
 * Each piece belongs to a team: White or Black
 * Each piece takes up a specific position (row and column) of the board
 *
 * @author Aarushi Vashistha
 * @author Riddhi Patel
 */


public abstract class Piece {
    int row;
    int col;
    boolean isWhite;

    /**
     *Piece constructor
     *@param row  x coordinate of the designated spot
     *@param col  y coordinate of the designated spot
     *
     *@param isWhite
     * isWhite = true means the piece is a part of the white team
     * isWhite = false means the piece is a part of the black team
     */

    public Piece(int row, int col, boolean isWhite) {
        this.row = row;
        this.col = col;
        this.isWhite = isWhite;
    }

    /**
     * Two string method for each piece type
     * @return the String of the piece displayed on board
     */
    public abstract String toString();

    /**
     * Boolean method checking if desired spot is within board indices
     *
     *  @param newRow (new row)
     *  @param newCol (new column)
     *
     */
    public boolean isValidSquare(int newRow, int newCol) {
        return (0 <= newRow && newRow <= 7 && 0 <= newCol && newCol <= 7);
    }

    /**
     * Boolean abstract method which checks if piece type can move wherever user inputs it to move (unique moves for every subclass)
     * @param board (chess board)
     * @param newRow (desired row)
     * @param newCol (desired column)
     * @return true or false
     */
    public abstract boolean canMoveTo(Piece[][] board, int newRow, int newCol);
}

