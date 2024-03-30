package PegGame.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

import PegGame.main.TextReader;
import week1.Testable;

@Testable
public class TextReaderTest {

    @Test
    void testValidFileReading() {
        String input = "3\n" +   // Size of the board
                       "o..\n" + // Row 1
                       ".o.\n" + // Row 2
                       "..o";   // Row 3
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        char[][] expectedBoard = {
                {'o', '.', '.'},
                {'.', 'o', '.'},
                {'.', '.', 'o'}
        };

        char[][] actualBoard = TextReader.readArrayFromFile("testFile.txt");

        assertArrayEquals(expectedBoard, actualBoard);
    }

    @Test
    void testInvalidCharacter() {
        String input = "2\n" +   // Size of the board
                       "o..\n" + // Row 1
                       ".x.\n";  // Invalid character 'x'
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        assertNull(TextReader.readArrayFromFile("testFile.txt"));
    }

    @Test
    void testIOException() {
        // Testing IOException by providing a non-existent file name
        assertNull(TextReader.readArrayFromFile("nonexistent.txt"));
    }

    @Test
    void testNumberFormatException() {
        String input = "abc\n" + // Invalid size
                       "o..\n"; // Row 1
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        assertNull(TextReader.readArrayFromFile("testFile.txt"));
    }
}
