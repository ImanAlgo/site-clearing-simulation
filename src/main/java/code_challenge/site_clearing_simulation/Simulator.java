package code_challenge.site_clearing_simulation;

public class Simulator {

    private boolean terminated;
    private final char[][] site;
    private final Bulldozer bulldozer;

    public Simulator(char[][] site, Bulldozer bulldozer) {
        this.terminated = false;
        this.site = site;
        bulldozer.rotate(Face.EAST);
        this.bulldozer = bulldozer;
        this.bulldozer.setPosition(-1,0);
    }

    public char[][] getSite() {
        return site;
    }

    public Bulldozer getBulldozer() {
        return bulldozer;
    }

    public Simulator turnLeft() {
        if (terminated) {
            throw new RuntimeException("Simulator is terminated and can not turn left any more");
        }

        if(bulldozer.getDirection() == Face.NORTH) {
            bulldozer.rotate(Face.WEST);
        } else if(bulldozer.getDirection() == Face.WEST) {
            bulldozer.rotate(Face.SOUTH);
        } else if(bulldozer.getDirection() == Face.SOUTH) {
            bulldozer.rotate(Face.EAST);
        } else if(bulldozer.getDirection() == Face.EAST) {
            bulldozer.rotate(Face.NORTH);
        }

        return this;
    }

    public Simulator turnRight() {
        if (terminated) {
            throw new RuntimeException("Simulator is terminated and can not turn right any more");
        }
        if(bulldozer.getDirection() == Face.NORTH) {
            bulldozer.rotate(Face.EAST);
        } else if(bulldozer.getDirection() == Face.WEST) {
            bulldozer.rotate(Face.NORTH);
        } else if(bulldozer.getDirection() == Face.SOUTH) {
            bulldozer.rotate(Face.WEST);
        } else if(bulldozer.getDirection() == Face.EAST) {
            bulldozer.rotate(Face.SOUTH);
        }

        return this;
    }

    public Simulator advance(int steps) {
        if (terminated) {
            throw new RuntimeException("Simulator is terminated and can not advance any more");
        }
        if(steps < 0) {
            throw new RuntimeException("Can not advance in reverse direction");
        }

        try {
            bulldozer.move(steps, (p) -> {
                if (p.getX() < 0 || p.getX() >= getSite()[0].length) {
                    throw new IndexOutOfBoundsException("Can not navigate bulldozer beyond the site boundaries");
                }

                if(getSite()[p.getY()][p.getX()] == 'T') {
                    throw new RuntimeException("Navigating on a protected tree is not allowed");
                }
            });
        } catch (RuntimeException e) {
            quite();
        }

        return this;
    }

    public void quite() {
        this.terminated = true;
    }
}
