package core.site;

import core.forecast.factory.ForecastAbstractFactory;
import core.forecast.factory.LammaForecastFactory;

public class Lamma extends Site {
	

	public Lamma(String url) {
		super(url,"lamma_loc.txt");
	}

	@Override
	public String getLocationUrl(String location, int day) {
		String locUrl = getUrl();
		locUrl += getFormattedLocation(location) + ".xml";
		return locUrl;
	}

	@Override
	public ForecastAbstractFactory getForecastConstructor() {
		return new LammaForecastFactory();
	}

	@Override
	public String cleanSiteContent(String siteContent) {
		return siteContent;
	}

}
