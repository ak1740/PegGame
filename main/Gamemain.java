package PegGame.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Gamemain implements PegGame {
        private char[][] board; // Represents the game board
    private int rows;       // Number of rows on the board
    private int cols;       // Number of columns on the board

   // Constructor
public Gamemain(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    this.board = new char[rows][cols];
    initializeBoard();
}

// Method to initialize the game board
private void initializeBoard() {
    // Fill the board with empty holes represented by "-"
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            board[i][j] = '.';
        }
    }
}

// Method to set the pegs and holes on the board
public void setBoardState() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the row and column numbers for each peg (separated by a space), or enter 'done' to finish:");
    String input;
    while (!(input = scanner.nextLine().trim().toLowerCase()).equals("done")) {
        String[] coordinates = input.split(" ");
        if (coordinates.length != 2) {
            System.out.println("Invalid input. Please enter row and column numbers separated by a space.");
            continue;
        }
        try {
            int row = Integer.parseInt(coordinates[0]);
            int col = Integer.parseInt(coordinates[1]);
            if (row < 0 || row >= rows || col < 0 || col >= cols) {
                System.out.println("Invalid row or column number. Please enter numbers within the board dimensions.");
                continue;
            }
            board[row][col] = '-'; // Place a hole at the specified location
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid row and column numbers.");
        }
    }
    scanner.close();
}

    // Method to get possible moves (not implemented yet)
    @Override
    public Collection<Move> getPossibleMoves() {
        // TODO: Implement method to determine possible moves
        return new ArrayList<>();
    }

    // Method to get the game state (not implemented yet)
    @Override
    public GameState getGameState() {
        // TODO: Implement method to determine game state
        return null;
    }

    // Method to make a move (not implemented yet)
    @Override
    public void makeMove(Move move) throws PegGameException {
        // TODO: Implement method to make a move
    }

    // Method to display the board
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // Main method for testing
    public static void main(String[] args) {
        // Create a new SquarePegGame instance with 5 rows and 5 columns
        Gamemain game = new Gamemain(4, 4);
        // Allow the user to set the board state
        game.setBoardState();
        // Display the final state of the board
        System.out.println("Final state of the board:");
        System.out.println(game);
    }
}
