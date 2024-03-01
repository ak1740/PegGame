package PegGame.main;

import java.util.Collection;
import java.util.Scanner;

public class CommandLine implements PegGame{

public static void playPegGame(PegGame pegGame) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to PegGame! Enter your commands or type 'quit' to exit.");

    while (true) {
        // Print the current state of the board
        printBoard(pegGame);

        // Check if the game is over
        if (pegGame.getGameState() != GameState.IN_PROGRESS) {
            printGameState(pegGame.getGameState());
            break;
        }

        System.out.print("> ");
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("quit")) {
            System.out.println("Goodbye!");
            break;
        }

        String[] tokens = input.split(" ");
        if (tokens.length == 5 && tokens[0].equalsIgnoreCase("move")) {
            try {
                int r1 = Integer.parseInt(tokens[1]);
                int c1 = Integer.parseInt(tokens[2]);
                int r2 = Integer.parseInt(tokens[3]);
                int c2 = Integer.parseInt(tokens[4]);

                try {
                    pegGame.makeMove(new Move(new Location(r1, c1), new Location(r2, c2)));
                } catch (PegGameException e) {
                    System.out.println("Invalid move: " + e.getMessage());
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input format for move command.");
            }
        } else {
            System.out.println("Invalid command. Please use 'move r1 c1 r2 c2' or 'quit'.");
        }
    }

    // Close the Scanner
    scanner.close();
}

private static void printBoard(PegGame pegGame) {
    System.out.println("Current Board:");
    char[][] board = TextReader.getBoard(); // Using the getBoard method from TextReader
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
public Collection<Move> getPossibleMoves() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getPossibleMoves'");
}

@Override
public GameState getGameState() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getGameState'");
}

@Override
public void makeMove(Move move) throws PegGameException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'makeMove'");
}
}