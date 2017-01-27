package core.site;

import static java.util.stream.Collectors.joining;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import core.forecast.Forecast;
import core.forecast.factory.ForecastAbstractFactory;
import core.internet.WebHandler;

public abstract class Site {
	private String urlSite;
	private ArrayList<LinkedList<String>> availableLocations;

	public Site(String url, String locFileName) {
		urlSite = url;
		availableLocations = new ArrayList<>();
		setupAvailableLocations(locFileName);
	}

	public Forecast getForecast(String location, int day) {
		String url = getLocationUrl(location, day);
		WebHandler web = WebHandler.getInstance();
		String siteContent = web.getSite(url);
		Document document = Jsoup.parse(siteContent);
		ForecastAbstractFactory constructor = getForecastConstructor();
		return constructor.createForecast(document, day);
	}

	public abstract String getLocationUrl(String location, int day);

	public abstract ForecastAbstractFactory getForecastConstructor();

	public String getUrl() {
		return urlSite;
	}

	public String getFormattedLocation(String location) {
		return Stream.of(location.split(" "))
					 .map(String::toLowerCase)
					 .collect(joining());
	}

	private void setupAvailableLocations(String fileName) {
		String path = getPath(fileName);
		String locations = loadFromFile(path);
		String[] tmp = locations.split("\n");
		// Supponendo che le localita' siano gia' formattate nel file (no space + lowercase)
		// Cerco la dimensione della parola piu' lunga:
		int max = Stream.of(tmp).mapToInt(String::length).max().getAsInt();

		// inizializzo la lista:
		for (int i = 0; i <= max; i++) {
			availableLocations.add(new LinkedList<>());
		}

		// Inserisco le parole divise per lunghezza:
		Stream.of(tmp).forEach(loc -> availableLocations.get(loc.length()).add(loc));
	}

	private String getPath(String fileName) {
		URL urlP = getClass().getResource(fileName);
		String path = "";
		try {
			path = URLDecoder.decode(urlP.getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Location file NOT FOUND!!");
		}
		return path;
	}

	private String loadFromFile(String path) {
		BufferedReader br = null;
		String locations = "";
		try {
			br = new BufferedReader(new FileReader(path));
			locations = br.lines().collect(joining("\n"));
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return locations;
	}

	public boolean isAvailable(String location) {
		location = getFormattedLocation(location);
		if (location.length() >= availableLocations.size()) {
			return false;
		}
		LinkedList<String> shapedLocation = availableLocations.get(location.length());
		if (shapedLocation.isEmpty()) {
			return false;
		} else {
			return shapedLocation.contains(location);
		}
	}

	// Debug Only
	public void showAvailableLocations() {
		for (int i = 0; i < availableLocations.size(); i++) {
			System.out.println("\nLen: " + i);
			availableLocations.get(i).forEach(System.out::println);
		}
	}
}
