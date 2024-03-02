package PegGame.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import PegGame.main.TextReader;

public class TextReaderTest {

    @Test
    public void testReadArrayFromFile() {
        char[][] array = TextReader.readArrayFromFile("test_board.txt");

        // Assert that the returned array is not null
        assertNotNull(array);

        // Assert that the array has the expected size
        assertEquals(4, array.length);
        assertEquals(4, array[0].length);

        // Add more specific assertions if needed
    }

    @Test
    public void testGetBoard() {
        char[][] array = TextReader.getBoard();

        // Assert that the returned array is not null
        assertNotNull(array);

        // Assert that the array has the expected size
        assertEquals(4, array.length);
        assertEquals(4, array[0].length);

        // Add more specific assertions if needed
    }

    // Add more test methods as needed
}
