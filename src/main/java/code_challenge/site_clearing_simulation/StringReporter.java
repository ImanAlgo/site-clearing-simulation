package code_challenge.site_clearing_simulation;

import java.util.Arrays;

public class StringReporter implements Reporter {

    private  Simulator simulator;

    public StringReporter(Simulator simulator) {
        this.simulator = simulator;
    }

    public static StringReporter of(Simulator simulator) {
        return new StringReporter(simulator);
    }

    public String getSite() {
        return Arrays.deepToString(simulator.getSite());
    }

}
