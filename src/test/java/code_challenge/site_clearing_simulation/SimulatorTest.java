package code_challenge.site_clearing_simulation;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

public class SimulatorTest {

    private Simulator simulator;

    @Before
    public void setup() {
        char[][] site = {
                {'o', 'o', 't', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
                {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'T', 'o', 'o'},
                {'r', 'r', 'r', 'o', 'o', 'o', 'o', 'T', 'o', 'o'},
                {'r', 'r', 'r', 'r', 'o', 'o', 'o', 'o', 'o', 'o'},
                {'r', 'r', 'r', 'r', 'r', 't', 'o', 'o', 'o', 'o'}
        };

        simulator = new Simulator(site, new Bulldozer());
    }

    @Test
    public void initialSimulator() {
        SimulatorBeautifier beautifier = SimulatorBeautifier.of(simulator);
        String displaySite = beautifier.beautifySite();

        // Assert if SimulatorBeautifier some how shows the site squares in order
        MatcherAssert.assertThat(displaySite, Matchers.stringContainsInOrder(
                // an iterator which iterate the siteMap in a row-first order
                () -> new Iterator<String>() {
                    int index = 0;
                    int rows = simulator.getSite().length;
                    int cols = simulator.getSite().length;

                    @Override
                    public boolean hasNext() {
                        return (rows*cols) > index;
                    }

                    @Override
                    public String next() {
                        if (hasNext()) {
                            return String.valueOf(simulator.getSite()[Math.floorDiv(index, cols)][Math.floorMod(index++, cols)]);
                        }
                        return null;
                    }
                }
        ));
    }

    @Test
    public  void initialStateOfBulldozer() {
        Bulldozer bulldozer = new Bulldozer();
        bulldozer.rotate(Face.WEST);
        Simulator newSimulator = new Simulator(new char[][]{{'0'}}, bulldozer);

        Assert.assertSame(newSimulator.getBulldozer(), bulldozer);
        Assert.assertEquals(bulldozer.getDirection(), Face.EAST);
        Assert.assertEquals(bulldozer.getPosition(), Position.of(0,0));
    }

    @Test
    public void turnLeft() {
        Bulldozer bulldozer = simulator.getBulldozer();

        simulator.turnLeft();
        assertEquals(bulldozer.getDirection(), Face.NORTH);

        simulator.turnLeft();
        assertEquals(bulldozer.getDirection(), Face.WEST);

        simulator.turnLeft();
        assertEquals(bulldozer.getDirection(), Face.SOUTH);

        simulator.turnLeft();
        assertEquals(bulldozer.getDirection(), Face.EAST);
    }

    @Test
    public void advance() {
        simulator.advance(1);
        Assert.assertEquals(Position.of(1,0), simulator.getBulldozer().getPosition());

        simulator.advance(2);
        Assert.assertEquals(Position.of(3,0), simulator.getBulldozer().getPosition());

        Assert.assertThrows(RuntimeException.class, ()->simulator.advance(-1));
    }

    @Test
    public void turnRight() {
        Bulldozer bulldozer = simulator.getBulldozer();

        simulator.turnRight();
        assertEquals(bulldozer.getDirection(), Face.SOUTH);

        simulator.turnRight();
        assertEquals(bulldozer.getDirection(), Face.WEST);

        simulator.turnRight();
        assertEquals(bulldozer.getDirection(), Face.NORTH);

        simulator.turnRight();
        assertEquals(bulldozer.getDirection(), Face.EAST);
    }

    @Test
    public void quite() {
        simulator.quite();
        assertIsTerminated(simulator, "After run quite, the simulator must be terminated");
    }

    @Test
    public void navigateBeyondBoundaries() {
        simulator.advance(11);
        assertIsTerminated(simulator, "Simulator must terminate if navigate beyond the boundaries");
    }

    private void assertIsTerminated(Simulator simulator, String message) {
        Assert.assertThrows(message, RuntimeException.class, ()-> simulator.advance(1));
        Assert.assertThrows(message, RuntimeException.class, simulator::turnLeft);
        Assert.assertThrows(message, RuntimeException.class, simulator::turnRight);
    }


}
