package PegGame.main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TextReader {

    public static char[][] readArrayFromFile(String filename) {
        InputStream inputStream = TextReader.class.getResourceAsStream("/A1/" + filename);
        if (inputStream == null) {
            System.err.println("Failed to find the file: " + filename);
            return null;
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            int size = Integer.parseInt(br.readLine());
            char[][] array = new char[size][size];
            String line;
            int rowIndex = 0;
            while ((line = br.readLine()) != null && rowIndex < size) {
                char[] rowChars = line.toCharArray();
                for (int colIndex = 0; colIndex < size; colIndex++) {
                    array[rowIndex][colIndex] = rowChars[colIndex];
                }
                rowIndex++;
            }
            return array;
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
}
