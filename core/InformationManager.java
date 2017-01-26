package core;

import java.util.Map;
import static java.util.stream.Collectors.*;

import core.forecast.Forecast;
import core.forecast.ForecastTree;

public class InformationManager {
	private ForecastTree ft;

	public InformationManager() {
		ft = new ForecastTree();
	}

	public String getPrintablePartialForecast(int meteoID, String location, int dayID, String timeID) {
		Forecast tmp = ft.getDayForecast(meteoID, location, dayID);
		Map<String, String> pf = tmp.getPartialForecast(timeID);
		return pf.keySet().stream()
						  .map(key -> key + ": " + pf.get(key) + "\n")
						  .collect(joining());
	}

	public String getPrintableForecastElement(int meteoID, String location, int dayID, String timeID, String element) {
		Forecast forecast = ft.getDayForecast(meteoID, location, dayID);
		Map<String, String> partial = forecast.getPartialForecast(timeID);
		String strElement = partial.get(element);
		if (strElement == null) {
			strElement = "Non Disponibile!";
		}
		return strElement;
	}
}
