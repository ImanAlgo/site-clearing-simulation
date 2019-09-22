package code_challenge.site_clearing_simulation;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class ConstructionSiteClearingTest {

    private char[][] siteMap;

    @Before
    public void setup() {
        siteMap = new char[][]{
                {'o', 'o', 't', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
                {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'T', 'o', 'o'},
                {'r', 'r', 'r', 'o', 'o', 'o', 'o', 'T', 'o', 'o'},
                {'r', 'r', 'r', 'r', 'o', 'o', 'o', 'o', 'o', 'o'},
                {'r', 'r', 'r', 'r', 'r', 't', 'o', 'o', 'o', 'o'}
        };
    }

    @Test
    public void initialSimulator() {
        Bulldozer bulldozer = new Bulldozer();
        Simulator simulator = new Simulator(siteMap, bulldozer);
        SimulatorBeautifier beautifier = SimulatorBeautifier.of(simulator);
        String displaySite = beautifier.beautifySite();

        // Assert if SimulatorBeautifier some how shows the site squares in order
        MatcherAssert.assertThat(displaySite, Matchers.stringContainsInOrder(
                // an iterator which iterate the siteMap in a row-first order
                () -> new Iterator<String>() {
                    int index = 0;
                    int rows = siteMap.length;
                    int cols = siteMap[0].length;

                    @Override
                    public boolean hasNext() {
                        return (rows*cols) > index;
                    }

                    @Override
                    public String next() {
                        if (hasNext()) {
                            return String.valueOf(siteMap[Math.floorDiv(index, cols)][Math.floorMod(index++, cols)]);
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
        Simulator simulator = new Simulator(siteMap, bulldozer);

        Assert.assertEquals(bulldozer.getDirection(), Face.EAST);
        Assert.assertEquals(simulator.getBulldozerPosition(), Position.of(0,0));
    }


    private void assertIsTerminated(Simulator simulator) {
        Assert.assertThrows(RuntimeException.class, ()->simulator.advance(1));
        Assert.assertThrows(RuntimeException.class, simulator::left);
        Assert.assertThrows(RuntimeException.class, simulator::right);
    }

}
