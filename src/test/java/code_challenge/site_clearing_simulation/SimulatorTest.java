package code_challenge.site_clearing_simulation;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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

        simulator = new Simulator(site, new RegularBulldozer(), new NormalCostCalculator());
    }

    @Test
    public  void initialStateOfBulldozer() {
        RegularBulldozer bulldozer = new RegularBulldozer();
        bulldozer.rotate(Face.WEST);
        Simulator newSimulator = new Simulator(new char[][]{{'0'}}, bulldozer, new NormalCostCalculator());

        Assert.assertSame(newSimulator.getBulldozer(), bulldozer);
        Assert.assertEquals(bulldozer.getDirection(), Face.EAST);
        Assert.assertEquals(bulldozer.getPosition(), Position.of(-1,0));
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
        Assert.assertEquals(Position.of(0,0), simulator.getBulldozer().getPosition());

        simulator.advance(2);
        Assert.assertEquals(Position.of(2,0), simulator.getBulldozer().getPosition());

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

    @Test
    public void navigateOnProtectedTree() {
        simulator.advance(8).turnRight().advance(1);
        assertIsTerminated(simulator, "Simulator must terminate if navigate on protected tree");
    }

    @Test
    public void passingThroughProtectedTree() {
        simulator.advance(8).turnRight().advance(4);
        assertIsTerminated(simulator, "Simulator must terminate if pass through a protected tree");
    }

    @Test
    public void commandHistory() {
        simulator.advance(3).turnRight().advance(1).turnLeft().turnLeft();
        MatcherAssert.assertThat(simulator.getCommandHistory(), Matchers.contains(
                "Advance 3", "Turn Right", "Advance 1", "Turn Left", "Turn Left"
        ));
    }

    @Test
    public void unmodifiableCommandHistoryFromOutside() {
        assertThrows(UnsupportedOperationException.class, ()->simulator.getCommandHistory().add("Turn Right"));
    }

    private void assertIsTerminated(Simulator simulator, String message) {
        Assert.assertThrows(message, RuntimeException.class, ()-> simulator.advance(1));
        Assert.assertThrows(message, RuntimeException.class, simulator::turnLeft);
        Assert.assertThrows(message, RuntimeException.class, simulator::turnRight);
    }


}
