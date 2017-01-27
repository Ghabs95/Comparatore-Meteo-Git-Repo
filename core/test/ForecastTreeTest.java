package core.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import core.forecast.Forecast;
import core.forecast.ForecastConstants;
import core.forecast.ForecastTree;

public class ForecastTreeTest {

	ForecastTree forecastTree = new ForecastTree();

	@Test
	public void createLocationForecastTest() {
		int meteoID = 0;
		forecastTree.createLocationForecast(meteoID, "firenze");
		assertTrue(ForecastConstants.LAMMA == meteoID);
		assertTrue(forecastTree.getForecastTree().toString().contains("firenze"));

		String dayTime = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		assertTrue(forecastTree.getForecastTree().toString().contains(dayTime));
	}

	@Test
	public void isAvailableTest() {
		int meteoID = 0;
		assertFalse(forecastTree.isAvailable(meteoID, "firenze"));
		createLocationForecastTest();
		assertTrue(forecastTree.isAvailable(meteoID, "firenze"));
	}

	@Test
	public void getDayForecast() {
		int meteoID = 0;
		Forecast dayForecast = forecastTree.getDayForecast(meteoID, "firenze", 0);
		String dayTime = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		assertTrue(dayForecast.toString().contains(dayTime));
	}

}
