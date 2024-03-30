package PegGame.test;

import org.junit.Test;

import PegGame.main.GameState;
import week1.Testable;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

@Testable
public class GameStateTest {

@Test
public void testGameStateValues() {
    // Test that all values in the GameState enum are present
    GameState[] values = GameState.values();
    assertEquals(4, values.length);

    // Test that each value is unique
    Set<GameState> uniqueValues = new HashSet<>();
    for (GameState state : values) {
        assertTrue(uniqueValues.add(state));
    }
}
    

    @Test
    public void testGameStateToString() {
        // Test that each value has a unique string representation
        assertEquals("NOT_STARTED", GameState.NOT_STARTED.toString());
        assertEquals("IN_PROGRESS", GameState.IN_PROGRESS.toString());
        assertEquals("STALEMATE", GameState.STALEMATE.toString());
        assertEquals("WON", GameState.WON.toString());
    }

}