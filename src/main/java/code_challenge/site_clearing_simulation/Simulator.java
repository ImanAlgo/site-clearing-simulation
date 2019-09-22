package code_challenge.site_clearing_simulation;

import com.sun.istack.internal.NotNull;

public class Simulator {

    private final char[][] site;
    private final Position bulldozerPosition;
    private final Bulldozer bulldozer;

    public Simulator(char[][] site, Bulldozer bulldozer) {
        this.site = site;
        bulldozer.rotate(Face.EAST);
        this.bulldozer = bulldozer;
        bulldozerPosition = Position.of(0,0);
    }

    public char[][] getSite() {
        return site;
    }

    public void advance(int step) {
    }

    public void left() {
    }

    public void right() {
    }

    public Position getBulldozerPosition() {
        return this.bulldozerPosition;
    }
}
