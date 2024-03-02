package PegGame.test;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import PegGame.main.Location;
import PegGame.main.Move;

public class MoveTest {

    @Test
    public void testGetFrom() {
        Location from = new Location(2, 3);
        Location to = new Location(4, 5);
        Move move = new Move(from, to);
        assertEquals(from, move.getFrom());
    }

    @Test
    public void testGetTo() {
        Location from = new Location(2, 3);
        Location to = new Location(4, 5);
        Move move = new Move(from, to);
        assertEquals(to, move.getTo());
    }

    @Test
    public void testToString() {
        Location from = new Location(2, 3);
        Location to = new Location(4, 5);
        Move move = new Move(from, to);
        assertEquals("Move from Row: 2 Column: 3 to Row: 4 Column: 5", move.toString());
    }
}
