package PegGame.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TextReader {
    private static char[][] board; // Class-level variable to store the board array

    public static char[][] readArrayFromFile(String filename) {
        try (InputStream inputStream = TextReader.class.getResourceAsStream("/peggame/" + filename);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            // Read the first line to determine the dimensions
            int size = Integer.parseInt(br.readLine());

            // Create the square array with the specified size
            board = new char[size][size];

            // Read the rest of the file to fill the array
            String line;
            int rowIndex = 0;
            int colIndex = 0;
            while ((line = br.readLine()) != null && rowIndex < size) {
                for (char c : line.toCharArray()) {
                    if (c == 'o' || c == '.') {
                        board[rowIndex][colIndex] = c;
                    } else {
                        System.err.println("Invalid value in file: " + c);
                        return null;
                    }
                    colIndex++;
                    if (colIndex == size) {
                        rowIndex++;
                        colIndex = 0;
                    }
                }
            }
            return board;
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to retrieve the board array
    public static char[][] getBoard() {
        return board;
    }
}
