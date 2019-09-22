package code_challenge.site_clearing_simulation;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import static org.junit.Assert.*;

public class RegularBulldozerTest {

    private RegularBulldozer bulldozer;

    @Before
    public void setUp() throws Exception {
        bulldozer = new RegularBulldozer();
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
    public void move() {
        List<Position> traversedPositions = new ArrayList<>();
        BiConsumer<Position, Integer> addPos = (p,i)->traversedPositions.add(p);
        bulldozer.setPosition(5,5);

        traversedPositions.clear();
        bulldozer.rotate(Face.EAST);
        bulldozer.move(3, addPos);
        MatcherAssert.assertThat(traversedPositions, Matchers.contains(
                Position.of(6,5), Position.of(7,5), Position.of(8,5)
        ));

        traversedPositions.clear();
        bulldozer.rotate(Face.SOUTH);
        bulldozer.move(4, addPos);
        MatcherAssert.assertThat(traversedPositions, Matchers.contains(
                Position.of(8,6), Position.of(8,7),
                Position.of(8,8), Position.of(8,9)
        ));

        traversedPositions.clear();
        bulldozer.rotate(Face.WEST);
        bulldozer.move(1, addPos);
        MatcherAssert.assertThat(traversedPositions, Matchers.contains(Position.of(7,9)));

        traversedPositions.clear();
        bulldozer.rotate(Face.NORTH);
        bulldozer.move(2, addPos);
        MatcherAssert.assertThat(traversedPositions, Matchers.contains(
                Position.of(7,8), Position.of(7,7)
        ));

        // Test move in reverse direction
        traversedPositions.clear();
        bulldozer.rotate(Face.NORTH);
        bulldozer.move(-2, addPos);
        MatcherAssert.assertThat(traversedPositions, Matchers.contains(
                Position.of(7,8), Position.of(7,9)
        ));
    }

    @Test
    public void setPosition() {
        bulldozer.setPosition(3,5);
        assertEquals(bulldozer.getPosition(), Position.of(3,5));
    }
}