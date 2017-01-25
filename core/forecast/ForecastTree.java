package core.forecast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import core.site.Lamma;
import core.site.MeteoAM;
import core.site.Site;
import core.site.ThreeB;

public class ForecastTree {
	private ArrayList<Map<String, ArrayList<Forecast>>> forecastTree;
	private Lamma lamma;
	private MeteoAM am;
	private ThreeB threeB;
	private ArrayList<Integer> dayIDs;
	private ArrayList<Integer> meteoIDs;

	public ForecastTree() {
		setupMeteoService();
		setupDayList();
		setupMeteoList();
		setupTree();
	}
	
	private void setupMeteoService(){
		lamma = new Lamma();
		am = new MeteoAM();
		threeB = new ThreeB();
	}
	
	private void setupDayList(){
		dayIDs = new ArrayList<>();
		dayIDs.add(ForecastConstants.OGGI);
		dayIDs.add(ForecastConstants.DOMANI);
		dayIDs.add(ForecastConstants.DOPODOMANI);
	}
	
	private void setupMeteoList(){
		meteoIDs = new ArrayList<>();
		meteoIDs.add(ForecastConstants.LAMMA);
		meteoIDs.add(ForecastConstants.AM);
		meteoIDs.add(ForecastConstants.THREEB);
	}
	
	private void setupTree(){
		forecastTree = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			forecastTree.add(new HashMap<>());
		}
	}
	
	/* Metodi per inserire informazioni nell'albero */
	public void createLocationForecast(int meteoID, String location) {
		ArrayList<Forecast> fullForecast;
		switch (meteoID) {
		case (ForecastConstants.LAMMA): { fullForecast = getFullForecast(lamma, location); break; }
		case (ForecastConstants.AM): { fullForecast = getFullForecast(am, location); break; }
		case (ForecastConstants.THREEB): { fullForecast = getFullForecast(threeB, location); break; }
		default: { fullForecast = new ArrayList<>(); }
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
	
	private void checkAvailability(int meteoID, String location){
		if(!isAvailable(meteoID, location))
			createLocationForecast(meteoID, location);
	}

	/* Metodi per prendere le informazioni dall'albero */
	public boolean isAvailable(int meteoID, String location){
		return forecastTree.get(meteoID).containsKey(location);
	}
	
	public Forecast getDayForecast(int meteoID, String location, int dayID) {
		checkAvailability(meteoID, location);
		return forecastTree.get(meteoID).get(location).get(dayID);
	}
	
	
	/* Meteodi per funzionalità future */
	public Map<String, ArrayList<Forecast>> getForecastByMeteo(int meteoID) {
		return forecastTree.get(meteoID);
	}

	public ArrayList<ArrayList<Forecast>> getForecastByLocation(String location) {
		ArrayList<ArrayList<Forecast>> byLoc = new ArrayList<>();
		meteoIDs.forEach((id) -> checkAvailability(id, location));
		meteoIDs.forEach(meteo -> byLoc.add(forecastTree.get(meteo).get(location)));
		
		return byLoc;
	}

	public ArrayList<Forecast> getForecastByDay(String location, int dayID) {
		ArrayList<Forecast> byDay = new ArrayList<>();
		meteoIDs.forEach((id) -> checkAvailability(id, location)); 
		meteoIDs.forEach(meteo -> byDay.add(getDayForecast(meteo, location, dayID)));
		return byDay;
	}

}
