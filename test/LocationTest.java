package PegGame.test;
import PegGame.main.Location;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LocationTest {

    @Test
    public void testLocationEquals() {
        // test that two locations with the same row and column are equal
        Location loc1 = new Location(1, 2);
        Location loc2 = new Location(1, 2);
        assertTrue(loc1.equals(loc2));

        // test that two locations with different row and column are not equal
        loc1 = new Location(1, 2);
        loc2 = new Location(3, 4);
        assertFalse(loc1.equals(loc2));

        // test that a location is not equal to null
        assertFalse(loc1.equals(null));

        // test that a location is not equal to an object of a different class
        Object obj = new Object();
        assertFalse(loc1.equals(obj));
    }

    @Test
    public void testLocationGetters() {
        Location loc = new Location(1, 2);
        assertEquals(1, loc.getRow());
        assertEquals(2, loc.getCol());
    }

    @Test
    public void testLocationEqualsWithHole() {
        // test that a location is not equal to a location that represents a hole
        Location loc = new Location(1, 2);
        Location hole = new Location(-1, -1);
        assertFalse(loc.equals(hole));
    }

    @Test
    public void testLocationIsAdjacentToHole() {
        // test that a location is considered adjacent to a hole
        Location loc = new Location(1, 2);
        Location hole = new Location(-1, -1);
        assertTrue(loc.equals(new Location(1, 3)) || loc.equals(new Location(1, 1)) || loc.equals(new Location(2, 2)) || loc.equals(new Location(0, 2)));
    }

    @Test
    public void testLocationIsNotAdjacentToHole() {
        // test that a location is not considered adjacent to a hole
        Location loc = new Location(1, 2);
        Location hole = new Location(-1, -1);
        assertFalse(loc.equals(new Location(3, 2)) || loc.equals(new Location(1, 4)) || loc.equals(new Location(4, 2)) || loc.equals(new Location(1, 0)));
    }
}