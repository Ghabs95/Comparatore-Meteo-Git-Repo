package core.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import core.forecast.factory.ForecastAbstractFactory;
import core.forecast.factory.LammaForecastFactory;
import core.forecast.factory.MeteoAMForecastFactory;
import core.forecast.factory.ThreeBForecastFactory;
import core.site.Lamma;
import core.site.MeteoAM;
import core.site.Site;
import core.site.ThreeB;

public class SiteTest {
	Site lamma;
	Site meteoAM;
	Site threeb;

	@Before
	public void beforetestLammaFactory() {
		lamma = new Lamma();
		meteoAM = new MeteoAM();
		threeb = new ThreeB();
	}

	@Test
	public void locationUrlTest() {
		String locationUrlLamma = lamma.getLocationUrl("firenze", 0);
		String locationUrlAM = meteoAM.getLocationUrl("firenze", 1);
		String locationUrl3B = threeb.getLocationUrl("firenze", 2);
		assertTrue(locationUrlLamma.equals("http://www.lamma.rete.toscana.it/previ/ita/xml/comuni_web/dati/firenze.xml"));
		assertTrue(locationUrlAM.equals("http://www.meteoam.it/ta/previsione/286"));
		assertTrue(locationUrl3B.equals("http://www.3bmeteo.com/meteo/firenze/2"));
	}

	@Test
	public void getForecastConstructorTest() {
		ForecastAbstractFactory lammaForecastFactory = lamma.getForecastConstructor();
		ForecastAbstractFactory meteoAmForecastFactory = meteoAM.getForecastConstructor();
		ForecastAbstractFactory threeBForecastFactory = threeb.getForecastConstructor();
		assertTrue(lammaForecastFactory instanceof LammaForecastFactory);
		assertTrue(meteoAmForecastFactory instanceof MeteoAMForecastFactory);
		assertTrue(threeBForecastFactory instanceof ThreeBForecastFactory);
	}

}
