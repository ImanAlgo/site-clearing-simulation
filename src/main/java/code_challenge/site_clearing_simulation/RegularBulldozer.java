package code_challenge.site_clearing_simulation;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class RegularBulldozer implements Bulldozer{

    private Face direction = Face.NORTH;
    private Position position = Position.of(0,0);

    /**
     * Rotate the bulldozer to specific direction facing NORTH, WEST, SOUTH or EAST
     *
     * @param direction On of the 4 Arbitrary directions
     */
    public void rotate(Face direction) {
        this.direction = direction;
    }

    public void move(int steps, BiConsumer<Position, Integer> traverse) {
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
            traverse.accept(getPosition(), i+1);
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
