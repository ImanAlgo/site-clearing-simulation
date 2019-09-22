package code_challenge.site_clearing_simulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Simulator {

    private boolean terminated;
    private final char[][] site;
    private final Bulldozer bulldozer;
    private final CostCalculator calculator;
    private final List<String> commandHistory;

    public Simulator(char[][] site, Bulldozer bulldozer, CostCalculator calculator) {
        this.terminated = false;
        this.site = site;
        bulldozer.rotate(Face.EAST);
        this.bulldozer = bulldozer;
        this.bulldozer.setPosition(-1,0);
        this.calculator = calculator;
        commandHistory = new ArrayList<>();
    }

    public char[][] getSite() {
        return site;
    }

    public Bulldozer getBulldozer() {
        return bulldozer;
    }

    public Simulator turnLeft() {
        commandHistory.add("Turn Left");
        calculator.left();

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
        commandHistory.add("Turn Right");
        calculator.right();

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
        commandHistory.add(String.format("Advance %s", steps));
        calculator.advance(steps);

        if (terminated) {
            throw new RuntimeException("Simulator is terminated and can not advance any more");
        }
        if(steps < 0) {
            throw new RuntimeException("Can not advance in reverse direction");
        }

        try {
            bulldozer.move(steps, (position, step) -> {
                if (position.getX() < 0 || position.getX() >= getSite()[0].length) {
                    throw new IndexOutOfBoundsException("Can not navigate bulldozer beyond the site boundaries");
                }

                char content = getSite()[position.getY()][position.getX()];

                if(steps > step) {
                    calculator.pass(content);
                } else {
                    calculator.visit(content);
                }

                // Clean the block
                site[position.getY()][position.getX()] = '-';

                if(content == 'T') {
                    throw new RuntimeException("Navigating on a protected tree is not allowed");
                }
            });
        } catch (RuntimeException e) {
            setUnclearedBlocks();
            this.terminated = true;
        }

        return this;
    }

    public void quite() {
        commandHistory.add("Quite");
        calculator.quite();
        setUnclearedBlocks();
        this.terminated = true;
    }

    public List<String> getCommandHistory() {
        return Collections.unmodifiableList(commandHistory);
    }

    private void setUnclearedBlocks() {
        int numberOfUnclearedBlocks = 0;
        List<Character> unclearedBlocks = Arrays.asList('r', 't', 'o');

        for(int row = 0; row < getSite().length; ++row) {
            for(int col = 0; col < getSite()[0].length; ++col) {
                if(unclearedBlocks.contains(getSite()[row][col])) {
                    ++numberOfUnclearedBlocks;
                }
            }
        }

        calculator.setUnclearedBlocks(numberOfUnclearedBlocks);
    }
}
