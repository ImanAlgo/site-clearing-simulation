package code_challenge.site_clearing_simulation;

public interface CostCalculator {
    void left();
    void right();
    void advance(int steps);
    void pass(char item);
    void visit(char item);
    void addUnclearedBlocks(int count);

    int getNumberOfCommunications();
    int getCostOfCommunications();

    int getNumberOfFuelUsage();
    int getCostOfFuelUsage();

    int getNumberOfUnclearedBlocks();
    int getCostOfUnclearedBlocks();

    int getNumberOfProtectedTreeRemove();
    int getCostOfProtectedTreeRemove();

    int getNumberOfPaintDamageBulldozer();
    int getCostOfPaintDamageBulldozer();

    int getTotalCost();
}
