package core.forecast;

import java.util.LinkedList;
import java.util.Map;

public class Forecast {
	public final static String GIORNO = "infoGiorno";
	public final static String NOTTE = "notte";
	public final static String MATTINA = "mattina";
	public final static String POMERIGGIO = "pomeriggio";
	public final static String SERA = "sera";
	
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
	
	//TODO #choose: visto che le key delle mappe sono stringhe, adrebbero fissate con delle costanti in Forecast (ma sono tante, forse viene brutto...)
	public static LinkedList<String> getAvailableElements(){
		LinkedList<String> elements = new LinkedList<>();
		elements.add("aggiornamento");
		elements.add("T_max");
		elements.add("T_min");
		elements.add("allerta");
		
		elements.add("cielo");
		elements.add("prob. pioggia");
		elements.add("temperatura");
		elements.add("temp. percepita");
		return elements;
	}
	
	/* Metodi di ricerca */
	public Map<String,String> getPartialForecast(String timeID){
		return forecast.get(timeID);
	}
	
}
