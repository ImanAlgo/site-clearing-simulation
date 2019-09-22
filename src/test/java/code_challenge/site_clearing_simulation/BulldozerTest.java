package code_challenge.site_clearing_simulation;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BulldozerTest {

    private Bulldozer bulldozer;

    @Before
    public void setUp() throws Exception {
        bulldozer = new Bulldozer();
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

    @Test
    public void advance() {
        List<Position> traversedPositions = new ArrayList<>();

        bulldozer.setPosition(5,5);

        traversedPositions.clear();
        bulldozer.rotate(Face.EAST);
        bulldozer.advance(3, traversedPositions::add);
        MatcherAssert.assertThat(traversedPositions, Matchers.contains(
                Position.of(6,5), Position.of(7,5), Position.of(8,5)
        ));

        traversedPositions.clear();
        bulldozer.rotate(Face.SOUTH);
        bulldozer.advance(4, traversedPositions::add);
        MatcherAssert.assertThat(traversedPositions, Matchers.contains(
                Position.of(8,6), Position.of(8,7),
                Position.of(8,8), Position.of(8,9)
        ));

        traversedPositions.clear();
        bulldozer.rotate(Face.WEST);
        bulldozer.advance(1, traversedPositions::add);
        MatcherAssert.assertThat(traversedPositions, Matchers.contains(Position.of(7,9)));

        traversedPositions.clear();
        bulldozer.rotate(Face.NORTH);
        bulldozer.advance(2, traversedPositions::add);
        MatcherAssert.assertThat(traversedPositions, Matchers.contains(
                Position.of(7,8), Position.of(7,7)
        ));
    }

    @Test
    public void setPosition() {
        bulldozer.setPosition(3,5);
        assertEquals(bulldozer.getPosition(), Position.of(3,5));
    }
}