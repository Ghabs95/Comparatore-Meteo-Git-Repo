package core.forecast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.site.Lamma;
import core.site.MeteoAM;
import core.site.Site;
import core.site.ThreeB;

public class ForecastTree {
	private List<Map<String, List<Forecast>>> forecastTree;
	private Lamma lamma;
	private MeteoAM am;
	private ThreeB threeB;
	private List<Integer> dayIDs;
	private List<Integer> meteoIDs;

	public ForecastTree() {
		setupMeteoService();
		setupDayList();
		setupMeteoList();
		setupTree();
	}

	private void setupMeteoService() {
		lamma = new Lamma();
		am = new MeteoAM();
		threeB = new ThreeB();
	}

	private void setupDayList() {
		dayIDs = new ArrayList<>();
		dayIDs.add(ForecastConstants.OGGI);
		dayIDs.add(ForecastConstants.DOMANI);
		dayIDs.add(ForecastConstants.DOPODOMANI);
	}

	private void setupMeteoList() {
		meteoIDs = new ArrayList<>();
		meteoIDs.add(ForecastConstants.LAMMA);
		meteoIDs.add(ForecastConstants.AM);
		meteoIDs.add(ForecastConstants.THREEB);
	}

	private void setupTree() {
		forecastTree = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			forecastTree.add(new HashMap<>());
		}
	}

	/* Metodi per inserire informazioni nell'albero */
	public void createLocationForecast(int meteoID, String location) {
		List<Forecast> fullForecast;
		switch (meteoID) {
		case (ForecastConstants.LAMMA):
			fullForecast = getFullForecast(lamma, location);
			break;

		case (ForecastConstants.AM):
			fullForecast = getFullForecast(am, location);
			break;

		case (ForecastConstants.THREEB):
			fullForecast = getFullForecast(threeB, location);
			break;

		default:
			fullForecast = new ArrayList<>();

		}
		forecastTree.get(meteoID).put(location, fullForecast);
	}

	private List<Forecast> getFullForecast(Site meteo, String location) {
		ArrayList<Forecast> tmp = new ArrayList<>();
		dayIDs.forEach(day -> tmp.add(meteo.getForecast(location, day)));
		return tmp;
	}

	private void checkAvailability(int meteoID, String location) {
		if (!isAvailable(meteoID, location)) {
			createLocationForecast(meteoID, location);
		}
	}

	/* Metodi per prendere le informazioni dall'albero */
	public boolean isAvailable(int meteoID, String location) {
		return forecastTree.get(meteoID).containsKey(location);
	}

	public Forecast getDayForecast(int meteoID, String location, int dayID) {
		checkAvailability(meteoID, location);
		return forecastTree.get(meteoID).get(location).get(dayID);
	}

	/* Meteodi per funzionalità future */
	public Map<String, List<Forecast>> getForecastByMeteo(int meteoID) {
		return forecastTree.get(meteoID);
	}

	public List<List<Forecast>> getForecastByLocation(String location) {
		List<List<Forecast>> byLoc = new ArrayList<>();
		meteoIDs.forEach(id -> checkAvailability(id, location));
		meteoIDs.forEach(meteo -> byLoc.add(forecastTree.get(meteo).get(location)));
		return byLoc;
	}

	public List<Forecast> getForecastByDay(String location, int dayID) {
		List<Forecast> byDay = new ArrayList<>();
		meteoIDs.forEach(id -> checkAvailability(id, location));
		meteoIDs.forEach(meteo -> byDay.add(getDayForecast(meteo, location, dayID)));
		return byDay;
	}
}
