package PegGame.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TextReader {
    public static void main(String[] args) {
        char[][] array = readArrayFromFile("board.txt");
        if (array != null) {
            // Print the array for verification
            for (char[] row : array) {
                for (char val : row) {
                    System.out.print(val + " ");
                }
                System.out.println();
            }
        }
    }

    public static char[][] readArrayFromFile(String filename) {
        try (InputStream inputStream = TextReader.class.getResourceAsStream("/A1/" + filename);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            // Read the first line to determine the dimensions
            int size = Integer.parseInt(br.readLine());

            // Create the square array with the specified size
            char[][] array = new char[size][size];

            // Read the rest of the file to fill the array
            String line;
            int rowIndex = 0;
            int colIndex = 0;
            while ((line = br.readLine()) != null && rowIndex < size) {
                for (char c : line.toCharArray()) {
                    if (c == 'o') {
                        array[rowIndex][colIndex] = c;
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
            return array;
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
}