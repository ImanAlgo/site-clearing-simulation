package code_challenge.site_clearing_simulation;

public interface CostCalculator {
    void left();
    void right();
    void advance(int steps);
    void quite();
    void pass(char item);
    void visit(char item);
    void setUnclearedBlocks(int count);

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
