/* STEP FOUR, implements peg game to create a square implementation of the game
 * NOT PLAYABLE
 * Aliya, Leen, Mariam, Zoya
 */
 
 package PegGame.main;
 
 import java.util.ArrayList;// Import for managing ArrayList data structure
 import java.util.Collection;// Import for working with collections of moves
 
 
 public class StepFour implements PegGame {
     private char[][] board; //represents the game board
 
    //constructor
     public StepFour(char[][] board) {
         this.board = board;
 }
 
 
     // Method to get the game state
     @Override
     public GameState getGameState() {
         boolean hasMoves = false;
         int pegCount = 0;
         for (int row = 0; row < board.length; row++) {
             for (int col = 0; col < board[row].length; col++) {
                 if (board[row][col] == 'o') {
                     pegCount++;
                 }
             }
         }
 
         // Now, just call getPossibleMoves() once since it checks the entire board
         Collection<Move> possibleMoves = getPossibleMoves(); // Updated line
         if (!possibleMoves.isEmpty()) {
             hasMoves = true;
         }
     // determine the game state based on peg count and available moves
         if (pegCount == 0) {
             return GameState.NOT_STARTED; 
         } else if (!hasMoves) {
             if (pegCount == 1) {
                 return GameState.WON;
             } else {
                 return GameState.STALEMATE;
             }
         } else {
             return GameState.IN_PROGRESS;
         }
     }
 
 
     // Method to make a move 
     @Override
     public void makeMove(Move move) {
         Location from = move.getFrom();
         Location to = move.getTo();
 
         // Make the move on the board
         board[from.getRow()][from.getCol()] = '.';
         board[to.getRow()][to.getCol()] = 'o';
 
         // Jump and remove the peg in between
         int jumpedRow = (from.getRow() + to.getRow()) / 2;
         int jumpedCol = (from.getCol() + to.getCol()) / 2;
         board[jumpedRow][jumpedCol] = '.';
     }
 
     // Method to display the board
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
 
     @Override
     public Collection<Move> getPossibleMoves() {
         Collection<Move> possibleMoves = new ArrayList<>();
 
         // Check for the specified pattern in rows
         for (int row = 0; row < board.length; row++) {// Loop through each row in the board
             for (int col = 0; col < board[row].length - 2; col++) {// Loop through each column in the current row ...excluding the last two columns
                 if (board[row][col] == 'o' && board[row][col + 1] == 'o' && board[row][col + 2] == '.') {
                     possibleMoves.add(new Move(new Location(row, col), new Location(row, col + 2)));
                 } else if (board[row][col] == '.' && board[row][col + 1] == 'o' && board[row][col + 2] == 'o') {
                     possibleMoves.add(new Move(new Location(row, col + 2), new Location(row, col)));
                 }
             }
         }
 
         // Check for the specified pattern in columns
         for (int row = 0; row < board.length - 2; row++) {
             for (int col = 0; col < board[row].length; col++) { // Loop through each column in the current row
                 if (board[row][col] == 'o' && board[row + 1][col] == 'o' && board[row + 2][col] == '.') { // check if there is a peg ('o') in the current cell and two consecutive pegs below it with an empty hole ('.')
                     possibleMoves.add(new Move(new Location(row, col), new Location(row + 2, col)));// If the pattern is found it will add a move representing a jump from the current cell to the empty hole
                 } else if (board[row][col] == '.' && board[row + 1][col] == 'o' && board[row + 2][col] == 'o') {// Check if there is an empty hole in the current cell and two consecutive pegs below it
                     possibleMoves.add(new Move(new Location(row + 2, col), new Location(row, col)));// if the pattern is found then add a move representing a jump from the two pegs to the empty hole
                 }
             }
         }
 
         return possibleMoves;
     }
 
     public static Location[][] convertToLocations(char[][] board) { //converts pegs and holes into locations
         int rows = board.length;
         int cols = board[0].length; // Assuming at least one row exists and is a perfect square
 
         Location[][] locationBoard = new Location[rows][cols];
 
         for (int i = 0; i < rows; i++) {
             for (int j = 0; j < cols; j++) {
                 locationBoard[i][j] = new Location(i, j);
             }
         }
 
         return locationBoard;
     }
 
 
 
     //MAIN METHOD FOR TESTING PURPOSES ONLYY
     public static void main(String[] args) {
         // Sample board initialization
         char[][] sampleBoard = {
             {'o','o','o','.'},{'o','o','o','o'},{'o','o','o','o'},{'o','o','o','o'}
         };
 
         // Initialize your game board or game class here
         StepFour game = new StepFour(sampleBoard); 
 
         GameState gameState = game.getGameState();
         System.out.println("Game State: " + gameState);
 
         // Fetch and print possible moves
         System.out.println("Possible Moves:");
         Collection<Move> possibleMoves = game.getPossibleMoves();
         for (Move move : possibleMoves) {
             System.out.println(move);
         } 
         System.out.println("\nBoard:");
         for (char[] row : game.board) {
             for (char cell : row) {
                 System.out.print(cell + " ");
             }
             System.out.println();
         }
 
         // Perform a move
         Move move = new Move(new Location(2, 3), new Location(0, 3));
         System.out.println("\nMaking move: " + move);
         game.makeMove(move);
 
         // Print the updated board
         System.out.println("\nUpdated Board:");
         for (char[] row : game.board) {
             for (char cell : row) {
                 System.out.print(cell + " ");
             }
             System.out.println();
         }
     // Print possible moves after the first move
         System.out.println("Possible Moves:");
         Collection<Move> possibleMoves1 = game.getPossibleMoves();
         for (Move move1 : possibleMoves1) {
             System.out.println(move1);
         } 
 
    // Perform another move
         Move move2 = new Move(new Location(1, 1), new Location(1, 3));
         System.out.println("\nMaking move: " + move2);
         game.makeMove(move2);
 
         // prints the updated board 
         System.out.println("\nUpdated Board:");
         for (char[] row : game.board) {
             for (char cell : row) {
                 System.out.print(cell + " ");
             }
             System.out.println();
         }
  // Print possible moves after the second move
         System.out.println("Possible Moves:");
         Collection<Move> possibleMoves2 = game.getPossibleMoves();
         for (Move move3 : possibleMoves2) {
             System.out.println(move3);
         } 
         // Perform another move
         Move move3 = new Move(new Location(2, 1), new Location(2, 3));
         System.out.println("\nMaking move: " + move3);
         game.makeMove(move3); 
 
          // Print the updated board after the third move
          System.out.println("\nUpdated Board:");
          for (char[] row : game.board) {
              for (char cell : row) {
                  System.out.print(cell + " ");
              }
              System.out.println();
          }
        // Print possible moves after the third move
          System.out.println("Possible Moves:");
          Collection<Move> possibleMoves3 = game.getPossibleMoves();
          for (Move move4 : possibleMoves3) {
              System.out.println(move4);
          } 
        // Get and print the final game state
          GameState gameState2 = game.getGameState();
         System.out.println("Game State: " + gameState2);
     }
 }