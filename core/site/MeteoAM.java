package core.site;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.util.stream.Collectors.*;

import core.forecast.factory.ForecastAbstractFactory;
import core.forecast.factory.MeteoAMForecastFactory;

public class MeteoAM extends Site {

	public MeteoAM(String url) {
		super(url);
	}

	@Override
	public String getLocationUrl(String location, int day) {
		String locUrl = getUrl();
		try {
			locUrl += getCodeLocation(location);
		} catch (IOException e) {
			System.out.println("FNF");
		}
		return locUrl;
	}

	@Override
	public ForecastAbstractFactory getForecastConstructor() {
		return new MeteoAMForecastFactory();
	}

	@Override
	public String cleanSiteContent(String siteContent) {
		HTMLCleaner cleaner = new HTMLCleaner(); 
		String cleanContent = cleaner.cutTag(siteContent, "([^\"])(<script.)", "</script>"); //toglie i tag script e il loro contenuto
		cleanContent = cleaner.removeBlocks(cleanContent, "<!-- /.block -->");
		return cleanContent;
	}
	
	private String getCodeLocation(String location) throws IOException {
		BufferedReader locList = new BufferedReader(new FileReader("Comparatore-Meteo-Git-Repo/core/site/listaLocalita.txt"));
		String codeLoc = locList.lines()
				.filter(s -> s.contains(location.toUpperCase()))
				.map(s -> s.substring(0, s.indexOf('|')))
				.collect(joining());

		locList.close();
		return codeLoc;
	}

}
