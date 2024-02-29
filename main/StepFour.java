package PegGame.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

public class StepFour implements PegGame {
    private char[][] board; // Represents the game board
    private GameState GAME_STATE;

   // Constructor
public StepFour(char[][] board) {
    this.board = board;
}

// Method to get possible moves (not implemented yet)
@Override
    public Collection<Move> getPossibleMoves(int row2, int col2) {
        Collection<Move> possibleMoves = new ArrayList<>();

        // Check for the specified pattern in rows
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length - 2; col++) {
                if (board[row][col] == 'o' && board[row][col + 1] == 'o' && board[row][col + 2] == '.') {
                    possibleMoves.add(new Move(new Location(row, col), new Location(row, col + 2)));
                } else if (board[row][col] == '.' && board[row][col + 1] == 'o' && board[row][col + 2] == 'o') {
                    possibleMoves.add(new Move(new Location(row, col), new Location(row, col)));
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
}

    // Method to get the game state (not implemented yet)
    @Override
    public GameState getGameState() {
        boolean hasMoves = false;
        int pegCount = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 'o') {                        
                    pegCount++;
                        Collection<Move> possibleMoves = getPossibleMoves(row, col);
                        if (!possibleMoves.isEmpty()) {
                            hasMoves = true;
                        }
                    }
                }
            }
    
            if (pegCount == 0) {
                return GameState.NOT_fromED;
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

