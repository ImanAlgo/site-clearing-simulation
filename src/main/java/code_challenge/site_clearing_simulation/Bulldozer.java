package code_challenge.site_clearing_simulation;

public class Bulldozer {

    private Face direction = Face.NORTH;

    public Face getDirection() {
        return direction;
    }

    public void turnLeft() {
        if(direction == Face.NORTH) {
            direction = Face.WEST;
        } else if(direction == Face.WEST) {
            direction = Face.SOUTH;
        } else if(direction == Face.SOUTH) {
            direction = Face.EAST;
        } else if(direction == Face.EAST) {
            direction = Face.NORTH;
        }
    }

    public void turnRight() {
        if(direction == Face.NORTH) {
            direction = Face.EAST;
        } else if(direction == Face.WEST) {
            direction = Face.NORTH;
        } else if(direction == Face.SOUTH) {
            direction = Face.WEST;
        } else if(direction == Face.EAST) {
            direction = Face.SOUTH;
        }
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
