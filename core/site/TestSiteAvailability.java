package core.site;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestSiteAvailability {
	private Lamma lamma;
	private MeteoAM am;
	private ThreeB threeB;

	@Before
	public void setUp() throws Exception {
		lamma = new Lamma(""); //url fake, non mi serve
		am = new MeteoAM("");
		threeB = new ThreeB("");
	}

	@Test
	public void testLammaTrue() {
		lamma.showAvailableLocations();
		assertTrue(lamma.isAvailable("firenze"));
	}
	
	@Test
	public void testLammaFalse(){
		assertFalse(lamma.isAvailable("asd"));
	}

}
