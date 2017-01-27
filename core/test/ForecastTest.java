package core.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import core.forecast.Forecast;
import core.forecast.ForecastConstants;

public class ForecastTest {
	Forecast forecast;

	@Before
	public void initMap() {
		Map<String, Map<String, String>> root = new HashMap<>();
		Map<String, String> tempMap = new HashMap<>();
		tempMap.put("temp_min", "4");
		tempMap.put("temp_max", "15");
		root.put("giorno", tempMap);
		forecast = new Forecast(root);
	}
	
	@Test
	public void getAvailableElementsTest() {
		LinkedList<String> availableElements = Forecast.getAvailableElements();
		assertTrue(availableElements.contains(ForecastConstants.AGGIORNAMENTO));
		assertTrue(availableElements.contains(ForecastConstants.MAX));
		assertTrue(availableElements.contains(ForecastConstants.MIN));
		assertTrue(availableElements.contains(ForecastConstants.ALLERTA));
		assertTrue(availableElements.contains(ForecastConstants.CIELO));
		assertTrue(availableElements.contains(ForecastConstants.PROB_PIOGGIA));
		assertTrue(availableElements.contains(ForecastConstants.TEMPERATURA));
		assertTrue(availableElements.contains(ForecastConstants.TEMP_PERCEPITA));
		assertEquals(8, availableElements.size());
	}
	
	@Test
	public void toStringTest() {
		assertEquals(forecast.toString(), "giorno:\n\ttemp_min: 4\n\ttemp_max: 15\n");
	}
	
	@Test
	public void getPartialForecast() {
		Map<String, String> partialForecast = forecast.getPartialForecast("giorno");
		assertEquals(partialForecast.get("temp_min"), "4");
		assertEquals(partialForecast.get("temp_max"), "15");
	}

}
