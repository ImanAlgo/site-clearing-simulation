package code_challenge.site_clearing_simulation;

public class NormalCostCalculator implements CostCalculator {

    int numberOfCommunications = 0;
    int costOfCommunications = 0;

    int numberOfFuelUsage = 0;

    int numberOfUnclearedSquares = 0;
    int costOfUnclearedSquares = 0;

    int numberOfProtectedTreeRemove = 0;
    int costOfProtectedTreeRemove = 0;

    int numberOfPaintDamageBulldozer = 0;
    int costOfPaintDamageBulldozer = 0;


    @Override
    public void left() {
        ++numberOfCommunications;
        ++costOfCommunications;
    }

    @Override
    public void right() {
        ++numberOfCommunications;
        ++costOfCommunications;
    }

    @Override
    public void advance(int steps) {
        if(steps>0) {
            ++numberOfCommunications;
            ++costOfCommunications;
        }
    }

    @Override
    public void quite() {
        // IGNORE: Cause there is no cost for quite
    }

    @Override
    public void pass(char item) {
        switch (item) {
            case '-':
                ++numberOfFuelUsage;
                break;
            case 'o':
                ++numberOfFuelUsage;
                break;
            case 'r':
                numberOfFuelUsage += 2;
                break;
            case 't':
                numberOfFuelUsage += 2;
                ++numberOfPaintDamageBulldozer;
                costOfPaintDamageBulldozer += 2;
                break;
            case 'T':
                numberOfFuelUsage += 2;
                ++numberOfPaintDamageBulldozer;
                costOfPaintDamageBulldozer += 2;
                ++numberOfProtectedTreeRemove;
                costOfProtectedTreeRemove += 10;
                break;
            default:
                throw new RuntimeException(String.format("Can not calculate when passing a block containing '%s'", item));
        }
    }

    @Override
    public void visit(char item) {
        switch (item) {
            case '-':
                ++numberOfFuelUsage;
                break;
            case 'o':
                ++numberOfFuelUsage;
                break;
            case 'r':
                numberOfFuelUsage += 2;
                break;
            case 't':
                numberOfFuelUsage += 2;
                break;
            case 'T':
                numberOfFuelUsage += 2;
                ++numberOfProtectedTreeRemove;
                costOfProtectedTreeRemove += 10;
                break;
            default:
                throw new RuntimeException(String.format("Can not calculate when visiting a block containing '%s'", item));
        }
    }

    @Override
    public void setUnclearedBlocks(int count) {
        this.numberOfUnclearedSquares = count;
        this.costOfUnclearedSquares = 3 * count;
    }

    @Override
    public int getNumberOfCommunications() {
        return this.numberOfCommunications;
    }

    @Override
    public int getCostOfCommunications() {
        return this.costOfCommunications;
    }

    @Override
    public int getNumberOfFuelUsage() {
        return numberOfFuelUsage;
    }

    @Override
    public int getCostOfFuelUsage() {
        return numberOfFuelUsage;
    }

    @Override
    public int getNumberOfUnclearedBlocks() {
        return this.numberOfUnclearedSquares;
    }

    @Override
    public int getCostOfUnclearedBlocks() {
        return this.costOfUnclearedSquares;
    }

    @Override
    public int getNumberOfProtectedTreeRemove() {
        return this.numberOfProtectedTreeRemove;
    }

    @Override
    public int getCostOfProtectedTreeRemove() {
        return this.costOfProtectedTreeRemove;
    }

    @Override
    public int getNumberOfPaintDamageBulldozer() {
        return this.numberOfPaintDamageBulldozer;
    }

    @Override
    public int getCostOfPaintDamageBulldozer() {
        return this.costOfPaintDamageBulldozer;
    }

    @Override
    public int getTotalCost() {
        return getCostOfCommunications() +
                getCostOfFuelUsage() +
                getCostOfPaintDamageBulldozer() +
                getCostOfProtectedTreeRemove() +
                getCostOfUnclearedBlocks();
    }
}
