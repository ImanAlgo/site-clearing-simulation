package code_challenge.site_clearing_simulation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BulldozerTest {

    private Bulldozer bulldozer;

    @Before
    public void setUp() throws Exception {
        bulldozer = new Bulldozer();
        bulldozer.rotate(Face.EAST);
    }

    @Test
    public void rotate() {
        bulldozer.rotate(Face.EAST);
        assertEquals(bulldozer.getDirection(), Face.EAST);

        bulldozer.rotate(Face.SOUTH);
        assertEquals(bulldozer.getDirection(), Face.SOUTH);

        bulldozer.rotate(Face.WEST);
        assertEquals(bulldozer.getDirection(), Face.WEST);

        bulldozer.rotate(Face.NORTH);
        assertEquals(bulldozer.getDirection(), Face.NORTH);
    }

    @Test
    public void turnLeft() {
        bulldozer.turnLeft();
        assertEquals(bulldozer.getDirection(), Face.NORTH);

        bulldozer.turnLeft();
        assertEquals(bulldozer.getDirection(), Face.WEST);

        bulldozer.turnLeft();
        assertEquals(bulldozer.getDirection(), Face.SOUTH);

        bulldozer.turnLeft();
        assertEquals(bulldozer.getDirection(), Face.EAST);
    }

    @Test
    public void turnRight() {
        bulldozer.turnRight();
        assertEquals(bulldozer.getDirection(), Face.SOUTH);

        bulldozer.turnRight();
        assertEquals(bulldozer.getDirection(), Face.WEST);

        bulldozer.turnRight();
        assertEquals(bulldozer.getDirection(), Face.NORTH);

        bulldozer.turnRight();
        assertEquals(bulldozer.getDirection(), Face.EAST);
    }
}