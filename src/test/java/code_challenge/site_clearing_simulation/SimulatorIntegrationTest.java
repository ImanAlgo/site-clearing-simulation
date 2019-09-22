package code_challenge.site_clearing_simulation;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimulatorIntegrationTest {
    Simulator simulator;
    NormalCostCalculator calculator;

    @Before
    public void setup() {
        char[][] site = {
                {'o', 'o', 't', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
                {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'T', 'o', 'o'},
                {'r', 'r', 'r', 'o', 'o', 'o', 'o', 'T', 'o', 'o'},
                {'r', 'r', 'r', 'r', 'o', 'o', 'o', 'o', 'o', 'o'},
                {'r', 'r', 'r', 'r', 'r', 't', 'o', 'o', 'o', 'o'}
        };

        calculator = new NormalCostCalculator();
        simulator = new Simulator(site, new RegularBulldozer(), calculator);
    }

    @Test
    public void simulationScenario_1() {
        simulator
                .advance(4)
                .turnRight()
                .advance(4)
                .turnLeft()
                .advance(2)
                .advance(4)
                .turnLeft()
                .quite();

        MatcherAssert.assertThat(simulator.getCommandHistory(), Matchers.contains(
                "Advance 4", "Turn Right", "Advance 4", "Turn Left", "Advance 2", "Advance 4", "Turn Left", "Quite"
        ));

        Assert.assertEquals(7, calculator.getNumberOfCommunications());
        Assert.assertEquals(7, calculator.getCostOfCommunications());

        Assert.assertEquals(19, calculator.getNumberOfFuelUsage());
        Assert.assertEquals(19, calculator.getCostOfFuelUsage());

        Assert.assertEquals(34, calculator.getNumberOfUnclearedBlocks());
        Assert.assertEquals(102, calculator.getCostOfUnclearedBlocks());

        Assert.assertEquals(0, calculator.getNumberOfProtectedTreeRemove());
        Assert.assertEquals(0, calculator.getCostOfProtectedTreeRemove());

        Assert.assertEquals(1, calculator.getNumberOfPaintDamageBulldozer());
        Assert.assertEquals(2, calculator.getCostOfPaintDamageBulldozer());

        Assert.assertEquals(130, calculator.getTotalCost());
    }
}
