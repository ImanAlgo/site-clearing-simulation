package code_challenge.site_clearing_simulation;

import java.util.function.Consumer;

public class Bulldozer {

    private Face direction = Face.NORTH;
    private Position position = Position.of(0,0);

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


    public void advance(int steps, Consumer<Position> traverse) {
        for (int i = 0; i < Math.abs(steps); ++i) {
            int step = steps < 0 ? -1 : 1;
            switch (getDirection()) {
                case NORTH:
                    setPosition(getPosition().getX(), getPosition().getY() - step);
                    break;
                case SOUTH:
                    setPosition(getPosition().getX(), getPosition().getY() + step);
                    break;
                case WEST:
                    setPosition(getPosition().getX() - step, getPosition().getY());
                    break;
                case EAST:
                    setPosition(getPosition().getX() + step, getPosition().getY());
                    break;
            }
            traverse.accept(getPosition());
        }
    }

    public Face getDirection() {
        return direction;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        this.position = Position.of(x,y);
    }
}
