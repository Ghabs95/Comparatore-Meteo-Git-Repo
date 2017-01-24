package core.forecast;

import java.util.LinkedList;
import java.util.Map;

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
	
	//TODO #java8: rifattorizzare il metodo
	@Override
	public String toString(){
		String str = "";
		for (Map.Entry<String, Map<String, String>> entry : forecast.entrySet()) {
			str += entry.getKey() + ":\n";
			for (Map.Entry<String, String> sub_entry : entry.getValue().entrySet()) {
				str += "\t" + sub_entry.getKey() + ": " + sub_entry.getValue() + "\n";
			}
		}
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
