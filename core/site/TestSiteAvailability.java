package core.site;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestSiteAvailability {
	private Lamma lamma;
	@SuppressWarnings("unused")
	private MeteoAM am;
	@SuppressWarnings("unused")
	private ThreeB threeB;

	@Before
	public void setUp() throws Exception {
		lamma = new Lamma(); 
		am = new MeteoAM();
		threeB = new ThreeB();
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
