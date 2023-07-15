import java.util.ArrayList;

/**
 * Board class. Sets up the board with a 8x8 2D array display in ASCII form along with the respected
 * pieces within each designated team.
 * Includes constructors to initiliaze a new Board, methods that display the board and pieces, and
 * methods to check for valid moves.
 *
 * @author Aarushi Vashistha
 * @author Riddhi Patel
 */
public class Board {

    Piece[][] board;
    boolean whiteCastleShort;
    boolean whiteCastleLong;
    boolean blackCastleShort;
    boolean blackCastleLong;

    /**
     * Constructor to create the Pieces to line up on the checkered
     * board for both respected teams White and Black.
     *
     * Each piece for White and Black teams will be manually inserted into
     * the empty array by calling each designated subclass (King, Queen, Pawn, etc) derived from the
     * Piece class
     *
     * Row 0 includes all Pieces except Pawns for the Black team
     * Row 7 includes all Pieces except Pawns for the White team
     * End of the constructor populates the rows 1 and 6 with Pawn Pieces using a for loop
     *
     */

    public Board() {
        board = new Piece[8][8];
        whiteCastleShort = true;
        whiteCastleLong = true;
        blackCastleShort = true;
        blackCastleLong = true;

        board[0][0] = new Rook(0,0,false);
        board[0][1] = new Knight(0,1,false);
        board[0][2] = new Bishop(0,2,false);
        board[0][3] = new Queen(0,3,false);
        board[0][4] = new King(0,4,false);
        board[0][5] = new Bishop(0,5,false);
        board[0][6] = new Knight(0,6,false);
        board[0][7] = new Rook(0,7,false);

        board[7][0] = new Rook(7,0,true);
        board[7][1] = new Knight(7,1,true);
        board[7][2] = new Bishop(7,2,true);
        board[7][3] = new Queen(7,3,true);
        board[7][4] = new King(7,4,true);
        board[7][5] = new Bishop(7,5,true);
        board[7][6] = new Knight(7,6,true);
        board[7][7] = new Rook(7,7,true);

        for (int col = 0; col < 8; col++) {
            board[1][col] = new Pawn(1, col, false);
            board[6][col] = new Pawn(6, col, true);
        }
    }

    /**
     * Method to print the board "##" "  " using a nested for loop to
     * traverse the 8x8 2D array (without the pieces)
     *
     * If i and j iteration remainder isn't present, print "  "
     * otherwise, print "##"
     *
     * Print char array for bottom end row (helps for labeling piece movement)
     * Print number column for right end (helps for labeling piece movement)
     *
     */
    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    if ((i+j)%2==0) {
                        System.out.print("   ");
                    } else {
                        System.out.print("## ");
                    }
                } else {
                    System.out.print(board[i][j] + " ");
                }
                if (j==7) {
                    System.out.print(8-i);
                }
            }
            System.out.println();
        }
        for (char c = 'a'; c <= 'h'; c++) {
            System.out.print(" " + c + " ");
        }
        System.out.println("\n");
    }

    /**
     * Boolean method that checks if a piece is under attack for en passant
     * @param row (takes in row of piece)
     * @param col (takes in column of piece)
     * @param isWhite (color of piece)
     * @return if piece is under attack by opponent (true or false)
     */

    public boolean isAttack(int row, int col, boolean isWhite) {
        ArrayList<String> oppMove = Board.allPossibleMoves(isWhite,board);
        ArrayList<String> attackSq = new ArrayList<>();

        for(String s : oppMove) {
            String[] temp = s.split(" ");
            char file = temp[1].charAt(0);
            int rank = temp[1].charAt(1)-'0';
            int attackRow = 8-rank;
            int attackCol = file-'a';
            if (attackRow==row && attackCol==col) return true;
        }
        return false;
    }

    /**
     * Boolean Method that makes moves and en passant
     *
     * @param  fileRank (first input)
     * @param  fileRank2 (second input)
     * @param isWhite (color of piece)
     *
     *  @return  if it is possible to make a move (true or false)
     */

    public boolean makeMove(String fileRank, String fileRank2, boolean isWhite) {
        char file1 = fileRank.charAt(0), file2 = fileRank2.charAt(0);
        int rank1 = fileRank.charAt(1) - '0', rank2 = fileRank2.charAt(1) - '0';

        int row = 8-rank1, newRow = 8-rank2;
        int col = file1-'a', newCol = file2-'a';

        if (board[row][col]==null||board[row][col].isWhite!=isWhite) return false;

        if (whiteCastleShort && fileRank.equals("e1") && fileRank2.equals("g1")) {
            if (board[row][col+1]!=null||board[row][col+2]!=null) return false;
            if (isAttack(row, col, !isWhite) || isAttack(row,col+1,!isWhite)||isAttack(row,col+2,!isWhite)) return false;
            board[row][col+2]= board[row][col];
            board[row][col+2].col=col+2;
            board[row][col] = null;
            board[row][col+1] = board[row][col+3];
            board[row][col+1].col = col+1;
            board[row][col+3]=null;
            return true;
        }

        if (blackCastleShort && fileRank.equals("e8") && fileRank2.equals("g8")) {
            if (board[row][col+1]!=null||board[row][col+2]!=null) return false;
            if (isAttack(row, col, !isWhite) || isAttack(row,col+1,!isWhite)||isAttack(row,col+2,!isWhite)) return false;
            board[row][col+2]= board[row][col];
            board[row][col+2].col=col+2;
            board[row][col] = null;
            board[row][col+1] = board[row][col+3];
            board[row][col+1].col = col+1;
            board[row][col+3]=null;
            return true;
        }

        if (whiteCastleLong && fileRank.equals("e1") && fileRank2.equals("c1")) {
            if (board[row][col-1]!=null||board[row][col-2]!=null||board[row][col-3]!=null) return false;
            if (isAttack(row, col, !isWhite) || isAttack(row,col-1,!isWhite)||isAttack(row,col-2,!isWhite)) return false;
            board[row][col-2]= board[row][col];
            board[row][col-2].col=col-2;
            board[row][col] = null;
            board[row][col-1] = board[row][col-4];
            board[row][col-1].col = col-1;
            board[row][col-4]=null;
            return true;
        }

        if (blackCastleLong && fileRank.equals("e8") && fileRank2.equals("c8")) {
            if (board[row][col-1]!=null||board[row][col-2]!=null||board[row][col-3]!=null) return false;
            if (isAttack(row, col, !isWhite) || isAttack(row,col-1, !isWhite)||isAttack(row,col-2,!isWhite)) return false;
            board[row][col-2]= board[row][col];
            board[row][col-2].col=col-2;
            board[row][col] = null;
            board[row][col-1] = board[row][col-4];
            board[row][col-1].col = col-1;
            board[row][col-4]=null;
            return true;
        }

        if (board[row][col] == null || board[row][col].isWhite != isWhite
                || !board[row][col].canMoveTo(board, newRow, newCol)) {
            return false;
        }
        Piece temp = board[newRow][newCol];
        board[newRow][newCol] = board[row][col];
        board[newRow][newCol].row = newRow;
        board[newRow][newCol].col = newCol;
        board[row][col] = null;
        if (isInCheck(isWhite)) {
            board[row][col] = board[newRow][newCol];
            board[row][col].row = row;
            board[row][col].col = col;
            board[newRow][newCol] = temp;
            return false;
        }
        if (board[newRow][newCol] instanceof King) {
            if (isWhite) {
                whiteCastleShort = false;
                whiteCastleLong = false;
            }
            else {
                blackCastleShort = false;
                blackCastleLong = false;
            }
        }
        if (fileRank.equals("a8")) blackCastleLong=false;
        if (fileRank.equals("h8")) blackCastleShort=false;
        if(fileRank.equals("a1")) whiteCastleLong = false;
        if(fileRank.equals("h1")) whiteCastleShort = false;
        return true;
    }


    /** Boolean method to check if a move can be made
     *
     * @param fileRank (first input)
     * @param fileRank2 (second input)
     * @param isWhite (color of piece)
     *
     * @return toReturn (true or false)
     */

    public boolean checkMove(String fileRank, String fileRank2, boolean isWhite) {
        char file1 = fileRank.charAt(0), file2 = fileRank2.charAt(0);
        int rank1 = fileRank.charAt(1) - '0', rank2 = fileRank2.charAt(1) - '0';

        int row = 8-rank1, newRow = 8-rank2;
        int col = file1-'a', newCol = file2-'a';

        if (board[row][col] == null || board[row][col].isWhite != isWhite
                || !board[row][col].canMoveTo(board, newRow, newCol)) {
            return false;
        }
        Piece temp = board[newRow][newCol];
        board[newRow][newCol] = board[row][col];
        board[newRow][newCol].row = newRow;
        board[newRow][newCol].col = newCol;
        board[row][col] = null;

        boolean toReturn = true;
        if (isInCheck(isWhite)) {
            toReturn = false;
        }
        board[row][col] = board[newRow][newCol];
        board[row][col].row = row;
        board[row][col].col = col;
        board[newRow][newCol] = temp;
        return toReturn;
    }

    /** Boolean method to identify check
     *

     * @param isWhite
     *
     * @return true or false boolean value
     */
    public boolean isInCheck(boolean isWhite) {
        ArrayList<String> opponentMoves = allPossibleMoves(!isWhite, board);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] instanceof King && board[i][j].isWhite == isWhite) {
                    int rank = 8-i;
                    char file = (char)('a'+j);
                    String fileRank = String.valueOf(file) + String.valueOf(rank);
                    for (String move : opponentMoves) {
                        String[] temp = move.split(" ");
                        if (fileRank.equals(temp[1])) return true;
                    }
                    return false;
                }
            }
        }

        return false;
    }

    /** Boolean method to find if a checkmate is present or not
     * depending on which team: White or Black and its conditions
     *

     * @param isWhite (color of piece)
     *
     * @return toReturn
     */
    public boolean isInCheckmate(boolean isWhite) {
        if (!isInCheck(isWhite)) return false;
        ArrayList<String> moves = allPossibleMoves(isWhite, board);

        for (String move : moves) {
            String[] temp = move.split(" ");
            String fileRank1 = temp[0], fileRank2 = temp[1];
            if (checkMove(fileRank1, fileRank2, isWhite)) {
                return false;
            }
        }

        return true;
    }


    /** Static method recording and listing possible moves that
     * can be made and store it into an arraylist.
     *
     * @param isWhite (color of piece)
     * @param b (board)
     *
     * @return sol  returning stored arraylist filled with possible moves
     *              that can be made
     */

    public static ArrayList<String> allPossibleMoves(boolean isWhite, Piece[][] b) {
        ArrayList<String> sol = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (b[i][j] != null && b[i][j].isWhite == isWhite) {
                    int rank1 = 8-i;
                    char file1 = (char)('a'+j);
                    String fileRank1 = String.valueOf(file1) + String.valueOf(rank1);
                    for (int i2 = 0; i2 < 8; i2++) {
                        for (int j2 = 0; j2 < 8; j2++) {
                            if (b[i][j].canMoveTo(b, i2, j2)) {
                                int rank = 8-i2;
                                char file = (char)('a'+j2);
                                String fileRank = String.valueOf(file) + String.valueOf(rank);
                                sol.add(fileRank1 + " " + fileRank);
                            }
                        }
                    }
                }
            }
        }

        return sol;
    }
}