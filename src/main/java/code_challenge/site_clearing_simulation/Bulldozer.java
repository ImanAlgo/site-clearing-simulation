package code_challenge.site_clearing_simulation;

import java.util.function.BiConsumer;

public interface Bulldozer {
    Position getPosition();
    void setPosition(int i, int i1);
    Face getDirection();
    void rotate(Face east);
    void move(int steps, BiConsumer<Position, Integer> traverse);

}
