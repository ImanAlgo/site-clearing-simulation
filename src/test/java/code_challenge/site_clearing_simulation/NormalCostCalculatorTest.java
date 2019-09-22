package code_challenge.site_clearing_simulation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NormalCostCalculatorTest {

    private NormalCostCalculator calculator;

    @Before
    public void setup() {
        calculator = new NormalCostCalculator();
    }

    @Test
    public void advance() {
        calculator.advance(10);
        calculator.advance(0);
        calculator.advance(1);

        Assert.assertEquals(2, calculator.getNumberOfCommunications());
        Assert.assertEquals(2, calculator.getCostOfCommunications());

        Assert.assertEquals(0, calculator.getNumberOfFuelUsage());
        Assert.assertEquals(0, calculator.getCostOfFuelUsage());

        Assert.assertEquals(0, calculator.getNumberOfPaintDamageBulldozer());
        Assert.assertEquals(0, calculator.getCostOfPaintDamageBulldozer());

        Assert.assertEquals(0, calculator.getNumberOfProtectedTreeRemove());
        Assert.assertEquals(0, calculator.getCostOfProtectedTreeRemove());

        Assert.assertEquals(0, calculator.getNumberOfUnclearedBlocks());
        Assert.assertEquals(0, calculator.getNumberOfUnclearedBlocks());
    }

    @Test
    public void left() {
        calculator.left();
        calculator.left();
        calculator.left();

        Assert.assertEquals(3, calculator.getNumberOfCommunications());
        Assert.assertEquals(3, calculator.getCostOfCommunications());

        Assert.assertEquals(0, calculator.getNumberOfFuelUsage());
        Assert.assertEquals(0, calculator.getCostOfFuelUsage());

        Assert.assertEquals(0, calculator.getNumberOfPaintDamageBulldozer());
        Assert.assertEquals(0, calculator.getCostOfPaintDamageBulldozer());

        Assert.assertEquals(0, calculator.getNumberOfProtectedTreeRemove());
        Assert.assertEquals(0, calculator.getCostOfProtectedTreeRemove());

        Assert.assertEquals(0, calculator.getNumberOfUnclearedBlocks());
        Assert.assertEquals(0, calculator.getNumberOfUnclearedBlocks());
    }

    @Test
    public void right() {
        calculator.right();
        calculator.right();
        calculator.right();

        Assert.assertEquals(3, calculator.getNumberOfCommunications());
        Assert.assertEquals(3, calculator.getCostOfCommunications());

        Assert.assertEquals(0, calculator.getNumberOfFuelUsage());
        Assert.assertEquals(0, calculator.getCostOfFuelUsage());

        Assert.assertEquals(0, calculator.getNumberOfPaintDamageBulldozer());
        Assert.assertEquals(0, calculator.getCostOfPaintDamageBulldozer());

        Assert.assertEquals(0, calculator.getNumberOfProtectedTreeRemove());
        Assert.assertEquals(0, calculator.getCostOfProtectedTreeRemove());

        Assert.assertEquals(0, calculator.getNumberOfUnclearedBlocks());
        Assert.assertEquals(0, calculator.getNumberOfUnclearedBlocks());
    }

    @Test
    public void passThroughPlainBlock() {
        calculator.pass('o');
        calculator.pass('o');

        Assert.assertEquals(0, calculator.getNumberOfCommunications());
        Assert.assertEquals(0, calculator.getCostOfCommunications());

        Assert.assertEquals(2, calculator.getNumberOfFuelUsage());
        Assert.assertEquals(2, calculator.getCostOfFuelUsage());

        Assert.assertEquals(0, calculator.getNumberOfPaintDamageBulldozer());
        Assert.assertEquals(0, calculator.getCostOfPaintDamageBulldozer());

        Assert.assertEquals(0, calculator.getNumberOfProtectedTreeRemove());
        Assert.assertEquals(0, calculator.getCostOfProtectedTreeRemove());

        Assert.assertEquals(0, calculator.getNumberOfUnclearedBlocks());
        Assert.assertEquals(0, calculator.getNumberOfUnclearedBlocks());
    }

    @Test
    public void passThroughRockyBlock() {
        calculator.pass('r');
        calculator.pass('r');

        Assert.assertEquals(0, calculator.getNumberOfCommunications());
        Assert.assertEquals(0, calculator.getCostOfCommunications());

        Assert.assertEquals(4, calculator.getNumberOfFuelUsage());
        Assert.assertEquals(4, calculator.getCostOfFuelUsage());

        Assert.assertEquals(0, calculator.getNumberOfPaintDamageBulldozer());
        Assert.assertEquals(0, calculator.getCostOfPaintDamageBulldozer());

        Assert.assertEquals(0, calculator.getNumberOfProtectedTreeRemove());
        Assert.assertEquals(0, calculator.getCostOfProtectedTreeRemove());

        Assert.assertEquals(0, calculator.getNumberOfUnclearedBlocks());
        Assert.assertEquals(0, calculator.getNumberOfUnclearedBlocks());
    }

    @Test
    public void passThroughAlreadyClearedBlock() {
        calculator.pass('-');
        calculator.pass('-');

        Assert.assertEquals(0, calculator.getNumberOfCommunications());
        Assert.assertEquals(0, calculator.getCostOfCommunications());

        Assert.assertEquals(2, calculator.getNumberOfFuelUsage());
        Assert.assertEquals(2, calculator.getCostOfFuelUsage());

        Assert.assertEquals(0, calculator.getNumberOfPaintDamageBulldozer());
        Assert.assertEquals(0, calculator.getCostOfPaintDamageBulldozer());

        Assert.assertEquals(0, calculator.getNumberOfProtectedTreeRemove());
        Assert.assertEquals(0, calculator.getCostOfProtectedTreeRemove());

        Assert.assertEquals(0, calculator.getNumberOfUnclearedBlocks());
        Assert.assertEquals(0, calculator.getNumberOfUnclearedBlocks());
    }

    @Test
    public void passThroughTree() {
        calculator.pass('t');
        calculator.pass('t');
        calculator.pass('T');

        Assert.assertEquals(0, calculator.getNumberOfCommunications());
        Assert.assertEquals(0, calculator.getCostOfCommunications());

        Assert.assertEquals(6, calculator.getNumberOfFuelUsage());
        Assert.assertEquals(6, calculator.getCostOfFuelUsage());

        Assert.assertEquals(3, calculator.getNumberOfPaintDamageBulldozer());
        Assert.assertEquals(6, calculator.getCostOfPaintDamageBulldozer());

        Assert.assertEquals(1, calculator.getNumberOfProtectedTreeRemove());
        Assert.assertEquals(10, calculator.getCostOfProtectedTreeRemove());

        Assert.assertEquals(0, calculator.getNumberOfUnclearedBlocks());
        Assert.assertEquals(0, calculator.getNumberOfUnclearedBlocks());
    }

    @Test
    public void passThroughUnknownBlock() {
        Assert.assertThrows(RuntimeException.class, ()->calculator.pass('x'));
    }

    @Test
    public void visitPlainBlock() {
        calculator.visit('o');
        calculator.visit('o');

        Assert.assertEquals(0, calculator.getNumberOfCommunications());
        Assert.assertEquals(0, calculator.getCostOfCommunications());

        Assert.assertEquals(2, calculator.getNumberOfFuelUsage());
        Assert.assertEquals(2, calculator.getCostOfFuelUsage());

        Assert.assertEquals(0, calculator.getNumberOfPaintDamageBulldozer());
        Assert.assertEquals(0, calculator.getCostOfPaintDamageBulldozer());

        Assert.assertEquals(0, calculator.getNumberOfProtectedTreeRemove());
        Assert.assertEquals(0, calculator.getCostOfProtectedTreeRemove());

        Assert.assertEquals(0, calculator.getNumberOfUnclearedBlocks());
        Assert.assertEquals(0, calculator.getNumberOfUnclearedBlocks());
    }

    @Test
    public void visitRockyBlock() {
        calculator.visit('r');
        calculator.visit('r');

        Assert.assertEquals(0, calculator.getNumberOfCommunications());
        Assert.assertEquals(0, calculator.getCostOfCommunications());

        Assert.assertEquals(4, calculator.getNumberOfFuelUsage());
        Assert.assertEquals(4, calculator.getCostOfFuelUsage());

        Assert.assertEquals(0, calculator.getNumberOfPaintDamageBulldozer());
        Assert.assertEquals(0, calculator.getCostOfPaintDamageBulldozer());

        Assert.assertEquals(0, calculator.getNumberOfProtectedTreeRemove());
        Assert.assertEquals(0, calculator.getCostOfProtectedTreeRemove());

        Assert.assertEquals(0, calculator.getNumberOfUnclearedBlocks());
        Assert.assertEquals(0, calculator.getNumberOfUnclearedBlocks());
    }

    @Test
    public void visitAlreadyClearedBlock() {
        calculator.visit('-');
        calculator.visit('-');

        Assert.assertEquals(0, calculator.getNumberOfCommunications());
        Assert.assertEquals(0, calculator.getCostOfCommunications());

        Assert.assertEquals(2, calculator.getNumberOfFuelUsage());
        Assert.assertEquals(2, calculator.getCostOfFuelUsage());

        Assert.assertEquals(0, calculator.getNumberOfPaintDamageBulldozer());
        Assert.assertEquals(0, calculator.getCostOfPaintDamageBulldozer());

        Assert.assertEquals(0, calculator.getNumberOfProtectedTreeRemove());
        Assert.assertEquals(0, calculator.getCostOfProtectedTreeRemove());

        Assert.assertEquals(0, calculator.getNumberOfUnclearedBlocks());
        Assert.assertEquals(0, calculator.getNumberOfUnclearedBlocks());
    }

    @Test
    public void visitTree() {
        calculator.visit('t');
        calculator.visit('t');
        calculator.visit('T');

        Assert.assertEquals(0, calculator.getNumberOfCommunications());
        Assert.assertEquals(0, calculator.getCostOfCommunications());

        Assert.assertEquals(6, calculator.getNumberOfFuelUsage());
        Assert.assertEquals(6, calculator.getCostOfFuelUsage());

        Assert.assertEquals(0, calculator.getNumberOfPaintDamageBulldozer());
        Assert.assertEquals(0, calculator.getCostOfPaintDamageBulldozer());

        Assert.assertEquals(1, calculator.getNumberOfProtectedTreeRemove());
        Assert.assertEquals(10, calculator.getCostOfProtectedTreeRemove());

        Assert.assertEquals(0, calculator.getNumberOfUnclearedBlocks());
        Assert.assertEquals(0, calculator.getNumberOfUnclearedBlocks());
    }

    @Test
    public void visitUnknownBlock() {
        Assert.assertThrows(RuntimeException.class, ()->calculator.pass('x'));
    }

    @Test
    public void setUnclearedBlocks() {
        calculator.setUnclearedBlocks(23);
        calculator.setUnclearedBlocks(30);

        Assert.assertEquals(0, calculator.getNumberOfCommunications());
        Assert.assertEquals(0, calculator.getCostOfCommunications());

        Assert.assertEquals(0, calculator.getNumberOfFuelUsage());
        Assert.assertEquals(0, calculator.getCostOfFuelUsage());

        Assert.assertEquals(0, calculator.getNumberOfPaintDamageBulldozer());
        Assert.assertEquals(0, calculator.getCostOfPaintDamageBulldozer());

        Assert.assertEquals(0, calculator.getNumberOfProtectedTreeRemove());
        Assert.assertEquals(0, calculator.getCostOfProtectedTreeRemove());

        Assert.assertEquals(30, calculator.getNumberOfUnclearedBlocks());
        Assert.assertEquals(30, calculator.getNumberOfUnclearedBlocks());
    }

    @Test
    public void getTotalCost() {
        calculator.left();
        calculator.advance(20);
        calculator.right();
        calculator.pass('t');
        calculator.pass('T');
        calculator.pass('r');
        calculator.pass('-');
        calculator.pass('o');
        calculator.visit('t');
        calculator.visit('T');
        calculator.visit('r');
        calculator.visit('-');
        calculator.visit('o');
        calculator.setUnclearedBlocks(22);

        Assert.assertEquals(109, calculator.getTotalCost());
    }

}
