package core.test;

import static org.junit.Assert.*;

import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import core.forecast.ForecastConstants;
import core.forecast.factory.ForecastAbstractFactory;
import core.forecast.factory.LammaForecastFactory;
import core.internet.WebHandler;
import core.site.Lamma;
import core.site.Site;

public class LammaForecastFactoryTest {
	Site lamma;
	ForecastAbstractFactory lammaFactory;
	Document document;
	Elements root;

	@Before
	public void initForecast() {
		lamma = new Lamma();
		lammaFactory = new LammaForecastFactory();
		document = Jsoup.parse(WebHandler.getInstance().getSite(lamma.getLocationUrl("firenze", ForecastConstants.OGGI)));
		root = lammaFactory.createRoot(document);
	}

	@Test
	public void getInfoGiornoTest() {
		Map<String, String> infoGiorno = lammaFactory.getInfoGiorno(root, 0);
		assertEquals(5, infoGiorno.size());
		assertTrue(infoGiorno.containsKey(ForecastConstants.AGGIORNAMENTO));
		assertTrue(infoGiorno.containsKey(ForecastConstants.GIORNO));
		assertTrue(infoGiorno.containsKey(ForecastConstants.ALLERTA));
	}

	@Test
	public void getPrevisioniOrarieTest() {
		Map<String, String> prevOrarie = lammaFactory.getPrevisioniOrarie(root, 0, 0);
		assertEquals(0, prevOrarie.size());
		prevOrarie = lammaFactory.getPrevisioniOrarie(root, 0, 1);
		assertEquals(4, prevOrarie.size());
		assertTrue(prevOrarie.containsKey(ForecastConstants.CIELO));
		assertTrue(prevOrarie.containsKey(ForecastConstants.TEMP_PERCEPITA));
		assertTrue(prevOrarie.containsKey(ForecastConstants.TEMPERATURA));

	}

}
