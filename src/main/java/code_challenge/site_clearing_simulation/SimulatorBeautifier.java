package code_challenge.site_clearing_simulation;

import java.util.Arrays;

public class SimulatorBeautifier {

    private  Simulator simulator;

    public SimulatorBeautifier(Simulator simulator) {
        this.simulator = simulator;
    }

    public static SimulatorBeautifier of(Simulator simulator) {
        return new SimulatorBeautifier(simulator);
    }

    public String beautifySite() {
        return Arrays.deepToString(simulator.getSite());
    }
}
