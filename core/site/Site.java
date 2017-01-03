package core.site;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import core.forecast.Forecast;
import core.forecast.factory.ForecastAbstractFactory;
import core.internet.WebHandler;

public abstract class Site {
	private String urlSite;

	public Site(String url) {
		urlSite = url;
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
}
