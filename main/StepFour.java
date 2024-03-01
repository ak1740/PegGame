package PegGame.main;

import java.util.ArrayList;
import java.util.Collection;


public class StepFour implements PegGame {
    private char[][] board; // Represents the game board

   // Constructor
public StepFour(char[][] board) {
    this.board = board;
}

// Method to get possible moves (not implemented yet)


    // Method to get the game state (not implemented yet)
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
    
        if (pegCount == 0) {
            return GameState.NOT_STARTED; // Assuming there was a typo in "NOT_fromED"
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
    

    // Method to make a move (not implemented yet)
    @Override
    public void makeMove(Move move) {
        Location from = move.getFrom();
        Location to = move.getTo();

        // Make the move on the board
        char peg = board[from.getRow()][to.getCol()];
        board[from.getRow()][from.getCol()] = '.';
        board[to.getRow()][to.getCol()] = peg;

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
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length - 2; col++) {
                if (board[row][col] == 'o' && board[row][col + 1] == 'o' && board[row][col + 2] == '.') {
                    possibleMoves.add(new Move(new Location(row, col), new Location(row, col + 2)));
                } else if (board[row][col] == '.' && board[row][col + 1] == 'o' && board[row][col + 2] == 'o') {
                    possibleMoves.add(new Move(new Location(row, col + 2), new Location(row, col)));
                }
            }
        }

        // Check for the specified pattern in columns
        for (int row = 0; row < board.length - 2; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 'o' && board[row + 1][col] == 'o' && board[row + 2][col] == '.') {
                    possibleMoves.add(new Move(new Location(row, col), new Location(row + 2, col)));
                } else if (board[row][col] == '.' && board[row + 1][col] == 'o' && board[row + 2][col] == 'o') {
                    possibleMoves.add(new Move(new Location(row + 2, col), new Location(row, col)));
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

    public static void main(String[] args) {
        // Sample board initialization
        char[][] sampleBoard = {
            {'o','o','o','.'},{'o','o','o','o'},{'o','o','o','o'},{'o','o','o','o'}
        };
    
        // Initialize your game board or game class here
        StepFour game = new StepFour(sampleBoard); // Assuming your class is named GameBoard
    
        // Fetch and print possible moves
        
    
        /* System.out.println("Possible Moves:");
        Collection<Move> possibleMoves = game.getPossibleMoves();
        for (Move move : possibleMoves) {
            System.out.println(move);
        } */
        System.out.println("\nUpdated Board:");
        for (char[] row : game.board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }

        GameState gameState = game.getGameState();
        System.out.println("Game State: " + gameState);

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

    }
    
}