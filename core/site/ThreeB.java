package core.site;

import java.util.stream.Stream;
import static java.util.stream.Collectors.*;

import core.forecast.factory.ForecastAbstractFactory;
import core.forecast.factory.ThreeBForecastFactory;

public class ThreeB extends Site {

	public ThreeB() {
		super("http://www.3bmeteo.com/meteo/", "threeB_loc.txt");
	}

	@Override
	public String getLocationUrl(String location, int day) {
		String locUrl = getUrl();
		locUrl += getFormattedLocation(location);
		return locUrl + "/" + day;
	}

	@Override
	public String getFormattedLocation(String location) {
		return Stream.of(location.split(" ")).map(String::toLowerCase).collect(joining("+"));
	}

	@Override
	public ForecastAbstractFactory getForecastConstructor() {
		return new ThreeBForecastFactory();
	}

}
