package PegGame.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import PegGame.main.Location;

public class LocationTest {

    @Test
    public void testGetRow() {
        Location location = new Location(3, 5);
        assertEquals(3, location.getRow());
    }

    @Test
    public void testGetCol() {
        Location location = new Location(3, 5);
        assertEquals(5, location.getCol());
    }

    @Test
    public void testToString() {
        Location location = new Location(3, 5);
        assertEquals("Row: 3 Column: 5", location.toString());
    }

    @Test
    public void testEquals() {
        Location location1 = new Location(3, 5);
        Location location2 = new Location(3, 5);
        Location location3 = new Location(4, 6);

        assertTrue(location1.equals(location2));
        assertFalse(location1.equals(location3));
    }
}
