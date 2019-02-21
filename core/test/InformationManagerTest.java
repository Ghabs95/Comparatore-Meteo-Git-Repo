package core.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import core.InformationManager;

public class InformationManagerTest {
	InformationManager informationManager = new InformationManager();

	@Test
	public void getPrintablePartialForecast() {
		String printableForecastElement = informationManager.getPrintablePartialForecast(0, "firenze", 0, "giorno");
		String dayTime = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		assertTrue(printableForecastElement.contains(dayTime));
	}

	@Test
	public void getPrintableForecastElement() {
		String printableForecastElement = informationManager.getPrintableForecastElement(0, "firenze", 0, "giorno", "");
		assertTrue(printableForecastElement.equals("Non Disponibile!"));
		printableForecastElement = informationManager.getPrintableForecastElement(0, "firenze", 0, "giorno", "aggiornamento");
		String dayTime = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		assertTrue(printableForecastElement.contains(dayTime));
	}

}
