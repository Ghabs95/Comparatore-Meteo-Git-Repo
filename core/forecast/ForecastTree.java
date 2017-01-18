package core.forecast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import core.forecast.factory.ForecastAbstractFactory;
import core.site.Lamma;
import core.site.MeteoAM;
import core.site.Site;
import core.site.ThreeB;

public class ForecastTree {
	public final static int LAMMA = 0;
	public final static int AM = 1;
	public final static int THREEB = 2;
	public final static int OGGI = ForecastAbstractFactory.OGGI;
	public final static int DOMANI = ForecastAbstractFactory.DOMANI;
	public final static int DOPODOMANI = ForecastAbstractFactory.DOPODOMANI;

	private ArrayList<Map<String, ArrayList<Forecast>>> forecastTree;
	private Lamma lamma;
	private MeteoAM am;
	private ThreeB threeB;
	private int[] dayIDs = { OGGI, DOMANI, DOPODOMANI };
	private int[] meteoIDs = { LAMMA, AM, THREEB };

	public ForecastTree() {
		lamma = new Lamma("http://www.lamma.rete.toscana.it/previ/ita/xml/comuni_web/dati/");
		am = new MeteoAM("http://www.meteoam.it/ta/previsione/");
		threeB = new ThreeB("http://www.3bmeteo.com/meteo/");
		// inizializzo l'albero
		forecastTree = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			forecastTree.add(new HashMap<>());
		}
	}

	public void createLocationForecast(int meteoID, String location) {
		ArrayList<Forecast> fullForecast;
		switch (meteoID) {
		case (LAMMA): {
			fullForecast = getFullForecast(lamma, location);
			break;
		}
		case (AM): {
			fullForecast = getFullForecast(am, location);
			break;
		}
		case (THREEB): {
			fullForecast = getFullForecast(threeB, location);
			break;
		}
		default: {
			fullForecast = new ArrayList<>();
		}
		}
		forecastTree.get(meteoID).put(location, fullForecast);
	}

	private ArrayList<Forecast> getFullForecast(Site meteo, String location) {
		ArrayList<Forecast> tmp = new ArrayList<>();
		for (int day : dayIDs) {
			tmp.add(meteo.getForecast(location, day));
		}
		return tmp;
	}

	/* Metodi per prendere le informazioni dall'albero */
	public Map<String, ArrayList<Forecast>> getForecastByMeteo(int meteoID) {
		return forecastTree.get(meteoID);
	}

	public ArrayList<ArrayList<Forecast>> getForecastByLocation(String location) {
		ArrayList<ArrayList<Forecast>> byLoc = new ArrayList<>();
		for (int meteo : meteoIDs) {
			byLoc.add(forecastTree.get(meteo).get(location));
		}
		return byLoc;
	}

	public ArrayList<Forecast> getForecastByDay(String location, int dayID) {
		ArrayList<Forecast> byDay = new ArrayList<>();
		for (int meteo : meteoIDs) {
			byDay.add(getDayForecast(meteo, location, dayID));
		}
		return byDay;
	}

	public Forecast getDayForecast(int meteoID, String location, int dayID) {
		return forecastTree.get(meteoID).get(location).get(dayID);
	}

}
