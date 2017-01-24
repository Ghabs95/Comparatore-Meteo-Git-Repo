package core;

import java.util.Map;

import core.forecast.Forecast;
import core.forecast.ForecastTree;

public class InformationManager {
	private ForecastTree ft;

	public InformationManager() {
		ft = new ForecastTree();
	}
	
	public String getPrintablePartialForecast(int meteoID, String location, int dayID, String timeID){
		String partialForecast = "";
		Forecast tmp = ft.getDayForecast(meteoID, location, dayID);
		Map<String,String> pf = tmp.getPartialForecast(timeID);
		for(String key: pf.keySet()){
			partialForecast += key +": "+ pf.get(key) +"\n";
		}
		return partialForecast;
	}
	
	public String getPrintableForecastElement(int meteoID, String location, int dayID, String timeID, String element){
		Forecast forecast = ft.getDayForecast(meteoID, location, dayID);
		Map<String,String> partial = forecast.getPartialForecast(timeID);
		String strElement = partial.get(element);
		if(strElement == null || strElement == ""){ strElement = "Non Disponibile!"; }  //TODO #check: basta uno solo dei 2 controlli, verificare quale (probabilmente null)
		return strElement;
	}
	
		
	
}
