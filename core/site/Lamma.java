package core.site;

import core.forecast.factory.ForecastAbstractFactory;
import core.forecast.factory.LammaForecastFactory;

public class Lamma extends Site {

	public Lamma() {
		super("http://www.lamma.rete.toscana.it/previ/ita/xml/comuni_web/dati/", "lamma_loc.txt");
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

}
