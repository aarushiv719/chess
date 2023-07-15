

import java.util.Scanner;

/**
 * Game class: the main method runs the entire game where
 * two players can provide inputs for both White and Black teams.
 * Depending on the inputs, the board will update based on movement,
 * illegal movement, and so on.
 * The main method will call previous methods and classes
 *
 * @author Aarushi Vashistha
 * @author Riddhi Patel
 */
public class Game {
    public static void main(String[] args) {
        String[] moves = {"e2 e4", "d7 d5", "e4 d5", "d8 d5", "d1 f3", "c8 f5", "b1 c3", "b8 c6", "d2 d3" ,"e8 c8", "c1 f4" ,"h7 h6" ,"e1 c1"};
        int index = 0;
        Board board = new Board();
        boolean isWhite = true;
        Scanner in = new Scanner(System.in);
        int whiteEPR = -1;
        int whiteEPC = -1;
        int blackEPR = -1;
        int blackEPC = -1;

        while (true) {
            if (isWhite) {
                whiteEPR = -1;
                whiteEPC = -1;
            } else {
                blackEPR = -1;
                blackEPC = -1;
            }
            System.out.println(Board.allPossibleMoves(!isWhite, board.board));
            if (board.isInCheckmate(isWhite)) {
                System.out.println("Checkmate");
                if (isWhite) System.out.println("Black wins!");
                else System.out.println("White wins!");
                break;
            }
            if (board.isInCheck(isWhite)) System.out.println("Check");
            board.printBoard();
            boolean resign = false;
            boolean draw = false;

            while (true) {
                if (!isWhite) System.out.print("Black's move: ");
                if (isWhite) System.out.print("White's move: ");
                String input = in.nextLine();
                String[] temp = input.split(" ");

                if (temp[0].equalsIgnoreCase("resign")) {
                    if(isWhite) System.out.println("Black wins!");
                    else System.out.println("White wins!");
                    resign = true;
                    break;
                }

                if(temp.length >2) {
                    if (temp[2].equalsIgnoreCase("draw?")) {
                        in.next();
                        draw = true;
                        break;
                    }
                }
                String filerank1 = temp[0];
                String filerank2 = temp[1];

                int row = 8-(filerank2.charAt(1)-'0');
                int col = filerank2.charAt(0)-'a';
                int row1 = 8-(filerank1.charAt(1)-'0');
                int col1 = filerank1.charAt(0)-'a';

                if(isWhite && board.board[row1][col1] instanceof Pawn && Math.abs(col1-blackEPC)==1 && row1==blackEPR && row == row1-1 && col == blackEPC) {
                    board.board[row][col] = board.board[row1][col1];
                    board.board[row][col].col = col;
                    board.board[row][col].row = row;
                    board.board[row1][col1]=null;
                    board.board[blackEPR][blackEPC]= null;
                    break;
                }

                if(!isWhite && board.board[row1][col1] instanceof Pawn && Math.abs(col1-whiteEPC)==1 && row1==whiteEPR && row == row1+1 && col == whiteEPC) {
                    board.board[row][col] = board.board[row1][col1];
                    board.board[row][col].col = col;
                    board.board[row][col].row = row;
                    board.board[row1][col1]=null;
                    board.board[whiteEPR][whiteEPC]= null;
                    break;
                }

                if (board.makeMove(filerank1, filerank2, isWhite)) {
                    if(isWhite && filerank2.charAt(1)=='8' && board.board[row][col] instanceof Pawn) {
                        if(temp.length>2) {
                            if (temp[2].equalsIgnoreCase("Q")) board.board[row][col]= new Queen(row,col,true);
                            if (temp[2].equalsIgnoreCase("N")) board.board[row][col]= new Knight(row,col,true);
                            if (temp[2].equalsIgnoreCase("R")) board.board[row][col]= new Rook(row,col,true);
                            if (temp[2].equalsIgnoreCase("B")) board.board[row][col]= new Bishop(row,col,true);
                        } else board.board[row][col]= new Queen(row,col,true);
                    }
                    if(!isWhite && filerank2.charAt(1)=='1' && board.board[row][col] instanceof Pawn) {
                        if(temp.length>2) {
                            if (temp[2].equalsIgnoreCase("Q")) board.board[row][col]= new Queen(row,col,false);
                            if (temp[2].equalsIgnoreCase("N")) board.board[row][col]= new Knight(row,col,false);
                            if (temp[2].equalsIgnoreCase("R")) board.board[row][col]= new Rook(row,col,false);
                            if (temp[2].equalsIgnoreCase("B")) board.board[row][col]= new Bishop(row,col,false);
                        } else board.board[row][col]= new Queen(row,col,false);
                    }

                    if (isWhite && board.board[row][col] instanceof Pawn && Math.abs(row-row1)==2) {
                        whiteEPR = row;
                        whiteEPC = col;
                    }
                    if (!isWhite && board.board[row][col] instanceof Pawn && Math.abs(row-row1)==2) {
                        blackEPR = row;
                        blackEPC = col;
                    }
                    break;
                }
                else System.out.println("Illegal move, try again!\n");
            }
            if (resign) break;
            if(draw) break;
            isWhite = !isWhite;
        }
    }
}
