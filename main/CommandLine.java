/* This class creates a command line interface implemnting the Peggame interface for the user to be able to play the game
 * Works independently of stepfour as expected, but uses the same game logic
 * Aliya, Leen, Mariam, Zoya
 */
package PegGame.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class CommandLine implements PegGame {
    private char[][] board; // represents the game board as a 2d array

    // constructor
    public CommandLine(char[][] board) {
        this.board = board;
    }

    // play game method
    public static void playPegGame(PegGame pegGame, Scanner scanner) { // takes scanner as parameter to avoid closing scanner error in main
        System.out.println("Welcome to PegGame! Enter your commands or type 'quit' to exit.");

        while (true) { // while loop to keep asking for moves until the user loses, wins or quits
            // printing the current state of the board so the user can see
            printBoard(pegGame);

            // displaying possible moves to the user to make the game easier
            displayPossibleMoves(pegGame);

            // checking if the game is over to break out of the loop
            if (pegGame.getGameState() != GameState.IN_PROGRESS) {
                printGameState(pegGame.getGameState());
                break;
            }

            System.out.print("> ");
            String input = scanner.nextLine().trim();

            // if user wants to quit game we will break out of the loop
            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Goodbye!");
                break;
            }

            // user command, only works in the specified format
            String[] tokens = input.split(" ");
            if (tokens.length == 5 && tokens[0].equalsIgnoreCase("move")) {
                try {
                    int r1 = Integer.parseInt(tokens[1].substring(1)); // extract numeric part
                    int c1 = Integer.parseInt(tokens[2].substring(1));
                    int r2 = Integer.parseInt(tokens[3].substring(1));
                    int c2 = Integer.parseInt(tokens[4].substring(1));

                    try {
                        pegGame.makeMove(new Move(new Location(r1, c1), new Location(r2, c2)));
                    } catch (PegGameException e) {
                        System.out.println("Invalid move: " + e.getMessage());
                    }
                } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                    System.out.println("Invalid input format for move command.");
                }
            } else {
                System.out.println("Invalid command. Please use 'move r1 c1 r2 c2' or 'quit'.");
            }
        }
        scanner.close();
    }

    // method for printing board so user can see
    private static void printBoard(PegGame pegGame) {
        System.out.println("Current Board:");
        char[][] board = TextReader.getBoard(); // using the getBoard method from TextReader
        if (board != null) {
            for (char[] row : board) {
                for (char cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Error: Board is not available.");
        }
    }

    // uses gameState to set print the status 
    private static void printGameState(GameState gameState) {
        switch (gameState) {
            case WON:
                System.out.println("Congratulations! You won!");
                break;
            case STALEMATE:
                System.out.println("Stalemate! No more valid moves.");
                break;
            default:
                System.out.println("Game Over.");
        }
    }

    @Override
    //overrinding the gamestate method from peggame class
    // this method depends on the current moves present and the pegcount to change the game state
    public GameState getGameState() {
        boolean hasMoves = false; //setting the boolean variable to false
        int pegCount = 0; // setting the pegcount to 0
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 'o') {
                    pegCount++; // incrementing peg count when we find a peg
                }
            }
        }

        //calling possibleMoves as the gamestate depends on the amount of moves the player has left
        Collection<Move> possibleMoves = getPossibleMoves();
        if (!possibleMoves.isEmpty()) {
            hasMoves = true; //changing the value of the boolean variable if there are moves
        }
    
        if (pegCount == 0) { // if there is more than one peg on the board, the game is ongoing
            return GameState.NOT_STARTED; 
        } else if (!hasMoves) {
            if (pegCount == 1) {
                return GameState.WON; //if the player doesnt have moves left and only 1 peg they win
            } else {
                return GameState.STALEMATE; //if the player has no moves left BUT more than 1 peg they lose/stalemate
            }
        } else {
            return GameState.IN_PROGRESS; //otherwise, theyre still playing
        }
    }

     //overrinding the method to make a move from peg game class
     @Override
     public void makeMove(Move move) {
         Location from = move.getFrom();
         Location to = move.getTo();
         //checking specific conditions for a move to be valid
         try {
             // Check if the 'from' location contains a peg
             if (board[from.getRow()][from.getCol()] != 'o') {
                 throw new PegGameException("Invalid move: No peg at the starting location."); //printing excpetion messages from peggame exception class
             }
     
             // Check if the 'to' location is an empty hole
             if (board[to.getRow()][to.getCol()] != '.') {
                 throw new PegGameException("Invalid move: Destination is not an empty hole."); //printing excpetion messages from peggame exception class
             }
     
             // Check if moving to the same row
             if (from.getRow() == to.getRow()) {
                 // Check for left jump(peg int the middle)
                 if (from.getCol() == to.getCol() + 2 && board[from.getRow()][from.getCol() - 1] == 'o') {
                     int jumpedCol = from.getCol() - 1;
                     if (board[from.getRow()][jumpedCol] != 'o') {
                         throw new PegGameException("Invalid move: Not jumping over a peg."); //printing excpetion messages from peggame exception class
                     }
                     board[from.getRow()][from.getCol()] = '.';
                     board[to.getRow()][to.getCol()] = 'o';
                     board[from.getRow()][jumpedCol] = '.';
                     return;
                 }
                 // Check for right jump(peg in the middle)
                 else if (from.getCol() == to.getCol() - 2 && board[from.getRow()][from.getCol() + 1] == 'o') {
                     int jumpedCol = from.getCol() + 1;
                     if (board[from.getRow()][jumpedCol] != 'o') {
                         throw new PegGameException("Invalid move: Not jumping over a peg."); //printing excpetion messages from peggame exception class
                     }
                     board[from.getRow()][from.getCol()] = '.';
                     board[to.getRow()][to.getCol()] = 'o';
                     board[from.getRow()][jumpedCol] = '.';
                     return;
                 }
             }
             // Check if moving to the same column
             else if (from.getCol() == to.getCol()) {
                 // Check for up jump (peg in the middle)
                 if (from.getRow() == to.getRow() + 2 && board[from.getRow() - 1][from.getCol()] == 'o') {
                     int jumpedRow = from.getRow() - 1;
                     if (board[jumpedRow][from.getCol()] != 'o') {
                         throw new PegGameException("Invalid move: Not jumping over a peg."); //printing excpetion messages from peggame exception class
                     }
                     board[from.getRow()][from.getCol()] = '.';
                     board[to.getRow()][to.getCol()] = 'o';
                     board[jumpedRow][from.getCol()] = '.';
                     return;
                 }
                 // Check for down jump (peg in the middel)
                 else if (from.getRow() == to.getRow() - 2 && board[from.getRow() + 1][from.getCol()] == 'o') {
                     int jumpedRow = from.getRow() + 1;
                     if (board[jumpedRow][from.getCol()] != 'o') {
                         throw new PegGameException("Invalid move: Not jumping over a peg."); //printing excpetion messages from peggame exception class
                     }
                     board[from.getRow()][from.getCol()] = '.';
                     board[to.getRow()][to.getCol()] = 'o';
                     board[jumpedRow][from.getCol()] = '.';
                     return;
                 }
             }
             throw new PegGameException("Invalid move: Not a valid jump."); //printing excpetion messages from peggame exception class
         } catch (PegGameException e) {
             System.out.println("Invalid move: " + e.getMessage()); //printing excpetion messages from peggame exception class
         }
     }
     
 
     //method to display the board
     @Override
     public String toString() {
         StringBuilder sb = new StringBuilder();
         for (int row = 0; row < board.length; row++) {
             for (int col = 0; col < board[row].length; col++) {
                 sb.append(board[row][col]).append(" ");
             }
             sb.append("\n");
         }
         return sb.toString();
     }
 
     //method that retrieves possible moves using checkMoves 
     @Override // overriden from peggame class
     public Collection<Move> getPossibleMoves() {
         Collection<Move> possibleMoves = new ArrayList<>();
         // specified patter that represents a possible move is oo. or .oo (horizontally if in the same row and vertically if in the same colums)
         // Check for the specified pattern in rows
         for (int row = 0; row < board.length; row++) {
             for (int col = 0; col < board[row].length - 2; col++) {
                 if (board[row][col] == 'o' && board[row][col + 1] == 'o' && board[row][col + 2] == '.') { //oo.
                     possibleMoves.add(new Move(new Location(row, col), new Location(row, col + 2)));
                 } else if (board[row][col] == '.' && board[row][col + 1] == 'o' && board[row][col + 2] == 'o') { //.oo
                     possibleMoves.add(new Move(new Location(row, col + 2), new Location(row, col)));
                 }
             }
         }
         // Check for the specified pattern in columns
         for (int row = 0; row < board.length - 2; row++) {
             for (int col = 0; col < board[row].length; col++) {
                 if (board[row][col] == 'o' && board[row + 1][col] == 'o' && board[row + 2][col] == '.') { //oo.
                     possibleMoves.add(new Move(new Location(row, col), new Location(row + 2, col)));
                 } else if (board[row][col] == '.' && board[row + 1][col] == 'o' && board[row + 2][col] == 'o') { //.oo
                     possibleMoves.add(new Move(new Location(row + 2, col), new Location(row, col)));
                 }
             }
         }
         return possibleMoves;
     }
 
     //method to be able to display the possible moves to the user ,uses getpossiblemoves
     private static void displayPossibleMoves(PegGame pegGame) {
         Collection<Move> possibleMoves = pegGame.getPossibleMoves();
         if (!possibleMoves.isEmpty()) {
             System.out.println("Possible Moves:");
             for (Move move : possibleMoves) {
                 System.out.println("Move: " + move);
             }
         } else {
             System.out.println("No possible moves. Game over.");
         }
     }
     
 
     public static Location[][] convertToLocations(char[][] board) { //converts pegs and holes into locations
         int rows = board.length;
         int cols = board[0].length; //assuming at least one row exists and is a perfect square
 
         Location[][] locationBoard = new Location[rows][cols];
 
         for (int i = 0; i < rows; i++) {
             for (int j = 0; j < cols; j++) {
                 locationBoard[i][j] = new Location(i, j);
             }
         }
 
         return locationBoard;
     }
     
 }
 
 
