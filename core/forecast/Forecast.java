package core.forecast;

import java.util.Map;

public class Forecast {
	private Map<String,Map<String,String>> forecast;
	
	public Forecast(Map<String,Map<String,String>> root){
		forecast = root;
	}
	
	// Metodi per la lettura delle informazioni 
	public void showOld() {
		System.out.println("------------------------------------");
		for (Map.Entry<String, Map<String, String>> entry : forecast.entrySet()) {
			System.out.println(entry.getKey() + ":");
			for (Map.Entry<String, String> sub_entry : entry.getValue().entrySet()) {
				System.out.println("\t" + sub_entry.getKey() + ": " + sub_entry.getValue());
			}
		}
	}

	//java 8 print
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
		for (Map.Entry<String, Map<String, String>> entry : forecast.entrySet()) {
			str += entry.getKey() + ":\n";
			for (Map.Entry<String, String> sub_entry : entry.getValue().entrySet()) {
				str += "\t" + sub_entry.getKey() + ": " + sub_entry.getValue() + "\n";
			}
		}
		return str;
	}
	
}
