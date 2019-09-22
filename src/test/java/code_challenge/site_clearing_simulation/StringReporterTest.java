package code_challenge.site_clearing_simulation;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Iterator;

public class StringReporterTest {

    private char[][] site;

    @Mock
    private Simulator simulatorMock;

    @Before
    public void setup() {
        site = new char[][] {
                {'o', 'o', 't', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
                {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'T', 'o', 'o'},
                {'r', 'r', 'r', 'o', 'o', 'o', 'o', 'T', 'o', 'o'},
                {'r', 'r', 'r', 'r', 'o', 'o', 'o', 'o', 'o', 'o'},
                {'r', 'r', 'r', 'r', 'r', 't', 'o', 'o', 'o', 'o'}
        };
        simulatorMock = Mockito.mock(Simulator.class);
        Mockito.when(simulatorMock.getSite()).thenReturn(site);
    }

    @Test
    public void getSite() {
        StringReporter reporter = StringReporter.of(simulatorMock);
        String displaySite = reporter.getSite();

        // Assert if Reporter some how shows the site squares in order
        MatcherAssert.assertThat(displaySite, Matchers.stringContainsInOrder(
                // an iterator which iterate the siteMap in a row-first order
                () -> new Iterator<String>() {
                    int index = 0;
                    int rows = site.length;
                    int cols = site.length;

                    @Override
                    public boolean hasNext() {
                        return (rows*cols) > index;
                    }

                    @Override
                    public String next() {
                        if (hasNext()) {
                            return String.valueOf(site[Math.floorDiv(index, cols)][Math.floorMod(index++, cols)]);
                        }
                        return null;
                    }
                }
        ));
    }

}