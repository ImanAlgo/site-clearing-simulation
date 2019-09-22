package code_challenge.site_clearing_simulation;

public class Bulldozer {

    private Face direction = Face.NORTH;

    public Face getDirection() {
        return direction;
    }

    /**
     * Rotate the bulldozer to face NORTH,WEST,SOUTH,EAST
     * This method is not for trainee but it is useful for
     * test and setting the initial direction in simulator
     *
     * @param direction On of the 4 Arbitrary directions
     */
    public void rotate(Face direction) {
        this.direction = direction;
    }
}
