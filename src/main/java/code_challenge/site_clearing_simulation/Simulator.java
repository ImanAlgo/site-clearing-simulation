package code_challenge.site_clearing_simulation;

public class Simulator {

    private char[][] site;
    private Bulldozer bulldozer;

    public Simulator(char[][] site, Bulldozer bulldozer) {
        this.site = site;
        this.bulldozer = bulldozer;
    }

    public char[][] getSite() {
        return site;
    }
}
