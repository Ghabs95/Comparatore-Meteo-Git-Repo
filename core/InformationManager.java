package core;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

import core.forecast.Forecast;
import core.forecast.ForecastTree;
import core.internet.WebHandler;

public class InformationManager {
	public final static int LAMMA = ForecastTree.LAMMA;
	public final static int AM = ForecastTree.AM;
	public final static int THREEB = ForecastTree.THREEB;
	public final static int OGGI = ForecastTree.OGGI;
	public final static int DOMANI = ForecastTree.DOMANI;
	public final static int DOPODOMANI = ForecastTree.DOPODOMANI;
	
	private ForecastTree ft;
	
	public InformationManager(){
		ft = new ForecastTree();				
	}
	
	
	public void gatherForecastLocation(int meteoID, String location){
		ft.createLocationForecast(meteoID, location);
	}
	
	public String getPrintableForecast(int meteoID, String location, int dayID){
		Forecast tmp = ft.getDayForecast(meteoID, location, dayID);
		String str = tmp.toString();	
		return str;
	}
	
	public BufferedImage getMeteoSatImage(){
		String path = "http://img2.meteo.it/forecastimg/realtime/europa/eu_sat_07.jpg";
		return WebHandler.getInstance().getPhoto(path);
	}
	
	//Solo per Esempio!
	public void showAll(){
		Map<String,ArrayList<Forecast>> meteoForecast;
		System.out.println("\n###### LAMMA #####");
		meteoForecast = ft.getForecastByMeteo(LAMMA);
		meteoForecast.forEach((loc, fullForecast) -> printMap(loc, fullForecast));
		
		System.out.println("\n###### METEO AM #####");
		meteoForecast = ft.getForecastByMeteo(AM);
		meteoForecast.forEach((loc, fullForecast) -> printMap(loc, fullForecast));
		
		System.out.println("\n###### 3B Meteo #####");
		meteoForecast = ft.getForecastByMeteo(THREEB);
		meteoForecast.forEach((loc, fullForecast) -> printMap(loc, fullForecast));
	}
	
	private void printMap(String location, ArrayList<Forecast> fullForecast) {
		System.out.println("\n+++++"+location.toUpperCase()+"+++++");
		fullForecast.forEach(forecast -> forecast.show());
		System.out.println("\n");
	}
}
