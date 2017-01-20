package core;

import org.junit.Before;
import org.junit.Test;

import core.forecast.ForecastTree;

public class InformationManagerTest {
	public final static int LAMMA = ForecastTree.LAMMA;
	public final static int AM = ForecastTree.AM;
	public final static int THREEB = ForecastTree.THREEB;

	private InformationManager im;

	@Before
	public void setUp() throws Exception {
		im = new InformationManager();
		im.gatherForecastLocation(LAMMA, "Firenze");
		im.gatherForecastLocation(LAMMA, "Prato");
		im.gatherForecastLocation(AM, "pisa");
		im.gatherForecastLocation(THREEB, "Pistoia");
	}

	@Test
	public void test() {
		im.showAll();
	}

}
