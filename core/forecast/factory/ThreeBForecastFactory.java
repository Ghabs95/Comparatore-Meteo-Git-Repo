package core.forecast.factory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import static java.util.stream.Collectors.*;

public class ThreeBForecastFactory extends ForecastAbstractFactory {
	private final String LAST_UPDATE_TAG = "div.page-header.text-align-sm > div";
	private final String ROOT_TAG = "div.box.noMarg";
	private final String DAY_LIST_TAG = "div.navDays";
	private final String TODAY_DEG_TAG = "span.switchcelsius.switch-te.active";
	private final String DEG_TAG = "p.big.switchcelsius.switch-te.active";
	private final String TIME_FORECAST_TAG = "div.col-xs-1-4.big, div.col-xs-2-4";
	private final String RAIN_TAG = "span.gray";
	private final String FORECAST_TAG = "small.hidden-xs";
	private final String DATA_TAG = "div.col-xs-2-3.col-sm-1-5.text-center.altriDati.altriDatiD-active";
	private final String ONLY_NUMBER_HOUR_REGEX = ":00|[^0-9]+";
	private final String DELETE_DEG_SIMBOL_REGEX = "°|C";
	private Element lastUpdateRoot;
	
	@Override
	public Elements createRoot(Document doc) {
		this.lastUpdateRoot = doc.select(LAST_UPDATE_TAG).first();
		return doc.select(ROOT_TAG);
	}

	@Override
	public Map<String, String> getInfoGiorno(Elements root, int day) {
		Element dayElement = root.select(DAY_LIST_TAG).get(day);
		if (day == OGGI) {
			return putInMapInfoDay(root, dayElement, TODAY_DEG_TAG, slicingList(getDayTime(root, TODAY_DEG_TAG), 3));
		} else {
			return putInMapInfoDay(root, dayElement, DEG_TAG, getDayTime(root, DEG_TAG));
		}
	}

	@Override
	public Map<String, String> getPrevisioniOrarie(Elements root, int day, int orario) {
		Map<String, String> hourForecast = new LinkedHashMap<>();
		if(day == OGGI) {
			hourForecast = putInMapTodayForecast(root, orario);
		}
		else {
			orario = orario == NOTTE ? 3 : orario - 1;
			hourForecast = putInMapForecats(root, orario);
		}
		return hourForecast;
	}
	
	private Map<String, String> putInMapTodayForecast(Elements root, int orario) {
		Map<String, String> hourForecast = new HashMap<>();
		List<String> dayTime = getDayTime(root, TIME_FORECAST_TAG);
		List<Integer> dayTimeFormatted = formatTimeToInteger(dayTime);
		
		if(dayTimeFormatted.contains(orario * 5 + 5)) {
			int index = dayTimeFormatted.indexOf(orario * 5 + 5);
			String dayTimeList = dayTime.get(index * 2 + 1);
			List<String> allTemp = getDayTime(root, TODAY_DEG_TAG);
			
			hourForecast.put("cielo", dayTimeList);
			hourForecast.put("temp", slicingList(allTemp, 3).get(index));
			hourForecast.put("rain_prob", getDayTime(root, RAIN_TAG).get(index));
			hourForecast.put("temp_perc", slicingList(allTemp.subList(1, allTemp.size()), 3).get(index));
		}
		return hourForecast;
	}

	private Map<String, String> putInMapInfoDay(Elements root, Element giorno, String stringTag, List<String> degree) {
		Map<String, String> infoGiorno = new HashMap<>();
		infoGiorno.put("ultimoAggiornamento", lastUpdateRoot.text());
		infoGiorno.put("giorno", giorno.text());
		infoGiorno.put("min", getDegree(root, stringTag, Double::min, degree) + "°");
		infoGiorno.put("max", getDegree(root, stringTag, Double::max, degree) + "°");
		return infoGiorno;
	}
	
	private Map<String, String> putInMapForecats(Elements root, int orario) {
		Map<String, String> forecast = new HashMap<>();
		forecast.put("cielo", getDayTime(root, FORECAST_TAG).get(orario));
		forecast.put("temp", getDayTime(root, DEG_TAG).subList(0, 4).get(orario));
		forecast.put("rain_prob", getDayTime(root, DATA_TAG).subList(12, 16).get(orario));
		forecast.put("temp_perc", slicingList(getDayTime(root, TODAY_DEG_TAG), 2).get(orario));
		return forecast;
	}
	
	private Double getDegree(Elements root, String tag, BinaryOperator<Double> function, List<String> degree) {
		return degree.stream()
					.map(text -> text.replaceAll(DELETE_DEG_SIMBOL_REGEX, ""))
					.map(Double::parseDouble)
					.reduce(function)
					.get();
	}
	
	private List<String> getDayTime(Elements root, String tag) {
		return root.select(tag).stream()
							   .map(Element::text)
							   .collect(toList());
	}
	
	private List<Integer> formatTimeToInteger(List<String> dayTime) {
		return dayTime.stream()
					  .map(hour -> hour.replaceAll(ONLY_NUMBER_HOUR_REGEX, ""))
					  .filter(hour -> !hour.isEmpty())
					  .map(Integer::parseInt)
					  .collect(toList());
	}
	
	private List<String> slicingList(List<String> list, int slice) {
		return IntStream.range(0, list.size())
						.filter(i -> i % slice == 0)
						.mapToObj(i -> list.get(i))
						.collect(toList());
	}

	public Element getLastUpdateRoot() {
		return lastUpdateRoot;
	}
}
