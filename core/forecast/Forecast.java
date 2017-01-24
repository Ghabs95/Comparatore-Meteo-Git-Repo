package core.forecast;

import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

public class Forecast {	
	private Map<String,Map<String,String>> forecast;
	
	
	public Forecast(Map<String,Map<String,String>> root){
		forecast = root;
	}
	
	
	// Visualizza da console (debug only)
	public void show() {
		// stampa key - value
		forecast.forEach((day, info) -> printMap(day, info));
	}

	private void printMap(String day, Map<String, String> info) {
		System.out.println(day + ": ");
		info.entrySet().forEach(entry -> System.out.println("\t" + entry.getKey() + ": " + entry.getValue()));
	}
	
	@Override
	 public String toString(){
	  String str = "";
	  str = forecast.entrySet().stream().map(entry -> getTostring(entry)).collect(Collectors.joining());
	  
	  return str;
	 }


	 private String getTostring(Map.Entry<String, Map<String, String>> entry) {
	  String str = entry.getKey() + ":\n";
	  str += entry.getValue().entrySet().stream()
	            .map(sub_entry -> "\t" + sub_entry.getKey() + ": " + sub_entry.getValue() + "\n")
	            .collect(Collectors.joining());
	  return str;
	 }
	
	public static LinkedList<String> getAvailableElements(){
		LinkedList<String> elements = new LinkedList<>();
		elements.add(ForecastConstants.AGGIORNAMENTO);
		elements.add(ForecastConstants.MAX);
		elements.add(ForecastConstants.MIN);
		elements.add(ForecastConstants.ALLERTA);
		
		elements.add(ForecastConstants.CIELO);
		elements.add(ForecastConstants.PROB_PIOGGIA);
		elements.add(ForecastConstants.TEMPERATURA);
		elements.add(ForecastConstants.TEMP_PERCEPITA);
		return elements;
	}
	
	/* Metodi di ricerca */
	public Map<String,String> getPartialForecast(String timeID){
		return forecast.get(timeID);
	}
	
}
