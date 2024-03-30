package PegGame.JavaFX;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import PegGame.main.GameState;
import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
//import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class PegGameModel {
    public static Button[][] buttons;
    public static GameState gameState = GameState.NOT_STARTED;


    public static int countPossibleMoves() {
        int moves = 0;
        
        // Check for horizontal moves
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length - 2; j++) {
                String pattern = PegGameController.checkButton(buttons[i][j]) + PegGameController.checkButton(buttons[i][j + 1]) + PegGameController.checkButton(buttons[i][j + 2]);
                if (pattern.equals("PegPegHole") || pattern.equals("HolePegPeg")) {
                    moves++;
                }
            }
        }
        
        // Check for vertical moves
        for (int i = 0; i < buttons.length - 2; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                String pattern = PegGameController.checkButton(buttons[i][j]) + PegGameController.checkButton(buttons[i + 1][j]) + PegGameController.checkButton(buttons[i + 2][j]);
                if (pattern.equals("PegPegHole") || pattern.equals("HolePegPeg")) {
                    moves++;
                }
            }
        }
        
        return moves;
    }

    public static void updateGameState(Stage primaryStage) {
        int pegCount = 0;
        int possibleMoves = countPossibleMoves();
    
        // Count pegs on the board
        for (Button[] buttonRow : buttons) {
            for (Button button : buttonRow) {
                if (PegGameController.checkButton(button).equals("Peg")) {
                    pegCount++;
                }
            }
        }
    
        // Check conditions and update game state
        if (possibleMoves == 0 && pegCount == 1) {
            gameState = GameState.WON;
            PegGameController.switchToGameOverScene(gameState.getMessage(),Color.GREEN);
        } else if (possibleMoves == 0 && pegCount > 1) {
            gameState = GameState.STALEMATE;
            PegGameController.switchToGameOverScene(gameState.getMessage(),Color.RED);
        }
    
    }

    public static void initializeButtons(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader("peggame\\" + filename + ".txt"))) {
            int size = Integer.parseInt(br.readLine());
            buttons = new Button[size][size];

            String line;
            int rowIndex = 0;
            while ((line = br.readLine()) != null && rowIndex < size) {
                for (int colIndex = 0; colIndex < line.length(); colIndex++) {
                    char c = line.charAt(colIndex);
                    Button button;
                    if (c == '.') {
                        button = PegGameView.createHoleButton();
                    } else if (c == 'o') {
                        button = PegGameView.createPegButton(rowIndex, colIndex);
                    } else {
                        throw new IllegalArgumentException("Invalid character in file: " + c);
                    }
                    buttons[rowIndex][colIndex] = button;
                }
                rowIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveGameStateToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("peggame\\" + filename + ".txt"))) {
            // Write size of the game board
            writer.write(buttons.length + "\n");

            // Write pegs and holes to the file
            for (Button[] buttonRow : buttons) {
                StringBuilder line = new StringBuilder();
                for (Button button : buttonRow) {
                    if (PegGameController.checkButton(button).equals("Hole")) {
                        line.append(".");
                    } else if (PegGameController.checkButton(button).equals("Peg")) {
                        line.append("o");
                    }
                }
                writer.write(line.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}