package core.forecast.factory;

import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import core.forecast.Forecast;
import core.forecast.ForecastConstants;

public abstract class ForecastAbstractFactory {

	public Forecast createForecast(Document doc, int day) {
		Elements root = createRoot(doc);
		Map<String, Map<String, String>> forecast = new LinkedHashMap<>();
		forecast.put(ForecastConstants.INFOGIORNO, getInfoGiorno(root, day));
		forecast.put(ForecastConstants.NOTTE, getPrevisioniOrarie(root, day, FactoryConstants.NOTTE));
		forecast.put(ForecastConstants.MATTINA, getPrevisioniOrarie(root, day, FactoryConstants.MATTINA));
		forecast.put(ForecastConstants.POMERIGGIO, getPrevisioniOrarie(root, day, FactoryConstants.POMERIGGIO));
		forecast.put(ForecastConstants.SERA, getPrevisioniOrarie(root, day, FactoryConstants.SERA));
		return new Forecast(forecast);
	}

	public abstract Elements createRoot(Document doc);

	public abstract Map<String, String> getInfoGiorno(Elements root, int day);

	public abstract Map<String, String> getPrevisioniOrarie(Elements root, int day, int orario);

}
