package PegGame.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;

import PegGame.main.GameState;
import PegGame.main.Location;
import PegGame.main.Move;
import PegGame.main.PegGameException;
import PegGame.main.StepFour;
import week1.Testable;

@Testable
public class StepFourTest {

    @Test
    public void testGetGameState() {
        // Sample board initialization
        char[][] sampleBoard = {
            {'o', 'o', 'o', '.'},
            {'o', 'o', 'o', 'o'},
            {'o', 'o', 'o', 'o'},
            {'o', 'o', 'o', 'o'}
        };

        // Initialize the game
        StepFour game = new StepFour(sampleBoard);

        // Get the game state
        GameState gameState = game.getGameState();

        // Assert that the returned value is not null
        assertNotNull(gameState);

        // Add more specific assertions based on the expected game state
        assertEquals(GameState.IN_PROGRESS, gameState);
    }

    @Test
    public void testGetPossibleMoves() {
        // Sample board initialization
        char[][] sampleBoard = {
            {'o', 'o', 'o', '.'},
            {'o', 'o', 'o', 'o'},
            {'o', 'o', 'o', 'o'},
            {'o', 'o', 'o', 'o'}
        };

        // Initialize the game
        StepFour game = new StepFour(sampleBoard);

        // Get possible moves
        Collection<Move> possibleMoves = game.getPossibleMoves();

        // Assert that the returned value is not null
        assertNotNull(possibleMoves);

        // Add more specific assertions if there are any expected possible moves
        // Example assertion: assertEquals(expectedSize, possibleMoves.size());
    }

    @Test
    public void testMakeMove() throws PegGameException {
        // Sample board initialization
        char[][] sampleBoard = {
            {'o', 'o', 'o', '.'},
            {'o', 'o', 'o', 'o'},
            {'o', 'o', 'o', 'o'},
            {'o', 'o', 'o', 'o'}
        };

        // Initialize the game
        StepFour game = new StepFour(sampleBoard);

        // Create a move
        Move move = new Move(new Location(2, 3), new Location(0, 3));

        // Make the move
        game.makeMove(move);
    }

}
