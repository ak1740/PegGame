package PegGame.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Test;

import PegGame.main.GameState;
import PegGame.main.Location;
import PegGame.main.Move;
import PegGame.main.PegGame;
import PegGame.main.PegGameException;
import week1.Testable;

@Testable
public class PegGameTest {

    private static class PegGameImpl implements PegGame {
        @Override
        public Collection<Move> getPossibleMoves() {
            // return a dummy set of moves
            HashSet<Move> moves = new HashSet<>();
            moves.add(new Move(new Location(0, 0), new Location(1, 1)));
            moves.add(new Move(new Location(1, 1), new Location(2, 2)));
            return moves;
        }

        @Override
        public GameState getGameState() {
            // return a dummy game state
            return GameState.IN_PROGRESS;
        }

        @Override
        public void makeMove(Move move) throws PegGameException {
            // only testing so do nothing
        }
    }

    @Test
    public void testGetPossibleMoves() {
        // Create an instance of PegGameImpl 
        PegGame pegGame = new PegGameImpl();

        // Call the getPossibleMoves method
        Collection<Move> possibleMoves = pegGame.getPossibleMoves();

        // Assert that the returned value is not null
        assertNotNull(possibleMoves);

        // Add more specific assertions if there are any expected behaviors
        assertEquals(2, possibleMoves.size()); // Example assertion for the number of possible moves
    }

    @Test
    public void testGetGameState() {
        // Create an instance of PegGameImpl for testing
        PegGame pegGame = new PegGameImpl();

        // Call the getGameState method
        GameState gameState = pegGame.getGameState();

        // Assert that the returned value is not null
        assertNotNull(gameState);

        // Add more specific assertions if there are any expected behaviors
        assertEquals(GameState.IN_PROGRESS, gameState); // Example assertion for the game state
    }

}