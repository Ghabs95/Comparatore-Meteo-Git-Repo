package core.site;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import core.forecast.Forecast;
import core.forecast.factory.ForecastAbstractFactory;
import core.internet.WebHandler;

public abstract class Site {
	private String urlSite;
	private ArrayList<LinkedList<String>> availableLocations;

	public Site(String url,String locFileName) {
		urlSite = url;
		availableLocations = new ArrayList<>();
		setupAvailableLocations(locFileName);
	}

	public Forecast getForecast(String location, int day) {
		String url = getLocationUrl(location, day);
		WebHandler web = WebHandler.getInstance();
		String siteContent = web.getSite(url);
		String cleanContent = cleanSiteContent(siteContent);
		Document document = Jsoup.parse(cleanContent);
		ForecastAbstractFactory constructor = getForecastConstructor();
		return constructor.createForecast(document, day);
	}

	public String getUrl() {
		return urlSite;
	}

	public abstract String getLocationUrl(String location, int day);

	public abstract ForecastAbstractFactory getForecastConstructor();
	
	public abstract String cleanSiteContent(String siteContent);
	
	public String getFormattedLocation(String location) {
		String fLoc = "";
		String[] locPieces = location.split(" ");
		for (String s : locPieces) {
			s = s.toLowerCase();
			fLoc += s;
		}
		return fLoc;
	}
	
	private void setupAvailableLocations(String fileName){
		String path = getPath(fileName);
		String locations = loadFromFile(path);
		String[] tmp = locations.split("\n");
		//Supponendo che le località siano già formattate nel file (no space + lowercase)
		//Cerco la dimensione della parola più lunga:
		int max = 0;
		for(String loc:tmp){
			if(loc.length()>max){
				max = loc.length();
			}
		}
		//inizializzo la lista:
		for(int i=0; i<=max; i++){
			availableLocations.add(new LinkedList<>());
		}
		//Inserisco le parole divise per lunghezza:
		for(String loc:tmp){
			availableLocations.get(loc.length()).add(loc);
		}
	}
	
	private String getPath(String fileName){
		URL urlP = getClass().getResource(fileName);
		String path = "";
		try {
			path = URLDecoder.decode(urlP.getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Location file NOT FOUND!!");
		}
		return path;
	}
	
	private String loadFromFile(String path){
		BufferedReader br = null;
		String locations = "";
		try{
			br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine())!= null){
				locations += line +"\n";
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return locations;
	}
	
	public boolean isAvailable(String location){
		location = getFormattedLocation(location);
		if(location.length() >= availableLocations.size()){ return false; }
		LinkedList<String> shapedLocation = availableLocations.get(location.length());
		if(shapedLocation.isEmpty()){
			return false;
		}else{
			return shapedLocation.contains(location);
		}
	}
	
	//Debug Only
	public void showAvailableLocations(){
		for(int i=0; i<availableLocations.size(); i++){
			System.out.println("\nLen: "+i);
			availableLocations.get(i).forEach((String s) -> System.out.println(s)); 
		}
	}
}
