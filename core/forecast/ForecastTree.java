package core.forecast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import core.site.Lamma;
import core.site.MeteoAM;
import core.site.Site;
import core.site.ThreeB;

public class ForecastTree {
	public final static int LAMMA = 0;
	public final static int AM = 1;
	public final static int THREEB = 2;
	public final static int OGGI = 0;
	public final static int DOMANI = 1;
	public final static int DOPODOMANI = 2;
	public final static String NOTTE = Forecast.NOTTE;
	public final static String MATTINA = Forecast.MATTINA;
	public final static String POMERIGGIO = Forecast.POMERIGGIO;
	public final static String SERA = Forecast.SERA;

	private ArrayList<Map<String, ArrayList<Forecast>>> forecastTree;
	private Lamma lamma;
	private MeteoAM am;
	private ThreeB threeB;
	private ArrayList<Integer> dayIDs;
	private ArrayList<Integer> meteoIDs;

	public ForecastTree() {
		lamma = new Lamma();
		am = new MeteoAM();
		threeB = new ThreeB();
		setupDayList();
		setupMeteoList();
		setupTree();
	}
	
	private void setupDayList(){
		dayIDs = new ArrayList<>();
		dayIDs.add(OGGI);
		dayIDs.add(DOMANI);
		dayIDs.add(DOPODOMANI);
	}
	
	private void setupMeteoList(){
		meteoIDs = new ArrayList<>();
		meteoIDs.add(LAMMA);
		meteoIDs.add(AM);
		meteoIDs.add(THREEB);
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
		case (LAMMA): {
			fullForecast = getFullForecast(lamma, location); break;
			}
		case (AM): {
			fullForecast = getFullForecast(am, location); break;
			}
		case (THREEB): {
			fullForecast = getFullForecast(threeB, location); break;
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
	
	private void checkAvailability(int meteoID, String location){
		if(!isAvailable(meteoID, location))
			createLocationForecast(meteoID, location);
	}

	/* Metodi per prendere le informazioni dall'albero */
	public boolean isAvailable(int meteoID, String location){
		return forecastTree.get(meteoID).containsKey(location);
	}
	
	public Map<String, ArrayList<Forecast>> getForecastByMeteo(int meteoID) {
		return forecastTree.get(meteoID);
	}

	public ArrayList<ArrayList<Forecast>> getForecastByLocation(String location) {
		meteoIDs.forEach((id) -> checkAvailability(id, location));
		ArrayList<ArrayList<Forecast>> byLoc = new ArrayList<>();
		for (int meteo : meteoIDs) {
			byLoc.add(forecastTree.get(meteo).get(location));
		}
		return byLoc;
	}

	public ArrayList<Forecast> getForecastByDay(String location, int dayID) {
		meteoIDs.forEach((id) -> checkAvailability(id, location)); 
		ArrayList<Forecast> byDay = new ArrayList<>();
		for (int meteo : meteoIDs) {
			byDay.add(getDayForecast(meteo, location, dayID));
		}
		return byDay;
	}

	public Forecast getDayForecast(int meteoID, String location, int dayID) {
		checkAvailability(meteoID, location);
		return forecastTree.get(meteoID).get(location).get(dayID);
	}
	
	

}
