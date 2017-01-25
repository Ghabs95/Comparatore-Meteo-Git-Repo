package core.forecast.factory;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import core.forecast.ForecastConstants;

public class ThreeBForecastFactory extends ForecastAbstractFactory {
	private Element lastUpdateRoot;
	
	@Override
	public Elements createRoot(Document doc) {
		this.lastUpdateRoot = doc.select(ThreeBConstants.LAST_UPDATE_TAG).first();
		return doc.select(ThreeBConstants.ROOT_TAG);
	}

	@Override
	public Map<String, String> getInfoGiorno(Elements root, int day) {
		Element dayElement = root.select(ThreeBConstants.DAY_LIST_TAG).get(day);
		if (day == FactoryConstants.OGGI) {
			return putInMapInfoDay(root, dayElement, ThreeBConstants.TODAY_DEG_TAG, slicingList(getDayTime(root, ThreeBConstants.TODAY_DEG_TAG), 3));
		} else {
			return putInMapInfoDay(root, dayElement, ThreeBConstants.DEG_TAG, getDayTime(root, ThreeBConstants.DEG_TAG));
		}
	}

	@Override
	public Map<String, String> getPrevisioniOrarie(Elements root, int day, int orario) {
		Map<String, String> hourForecast = new LinkedHashMap<>();
		if(day == FactoryConstants.OGGI) {
			hourForecast = putInMapTodayForecast(root, orario);
		}
		else {
			orario = orario == FactoryConstants.NOTTE ? 3 : orario - 1;
			hourForecast = putInMapForecast(root, orario);
		}
		return hourForecast;
	}
	
	private Map<String, String> putInMapTodayForecast(Elements root, int orario) {
		Map<String, String> hourForecast = new LinkedHashMap<>();
		List<String> dayTime = getDayTime(root, ThreeBConstants.TIME_FORECAST_TAG);
		List<Integer> dayTimeFormatted = formatTimeToInteger(dayTime);
		
		if(dayTimeFormatted.contains(orario * 5 + 5)) {
			int index = dayTimeFormatted.indexOf(orario * 5 + 5);
			String dayTimeList = dayTime.get(index * 2 + 1);
			List<String> allTemp = getDayTime(root, ThreeBConstants.TODAY_DEG_TAG);
			hourForecast.put(ForecastConstants.CIELO, dayTimeList);
			hourForecast.put(ForecastConstants.TEMPERATURA, slicingList(allTemp, 3).get(index));
			hourForecast.put(ForecastConstants.TEMP_PERCEPITA, slicingList(allTemp.subList(1, allTemp.size()), 3).get(index));
			hourForecast.put(ForecastConstants.PROB_PIOGGIA, getDayTime(root, ThreeBConstants.RAIN_TAG).get(index));
		}
		return hourForecast;
	}
	
	
	private Map<String, String> putInMapInfoDay(Elements root, Element giorno, String stringTag, List<String> degree) {
		Map<String, String> infoGiorno = new LinkedHashMap<>();
		infoGiorno.put(ForecastConstants.AGGIORNAMENTO, lastUpdateRoot.text());
		infoGiorno.put(ForecastConstants.GIORNO, giorno.text());
		infoGiorno.put(ForecastConstants.MIN, getDegree(Double::min, degree) + ThreeBConstants.DEGREE_SIMBOL);
		infoGiorno.put(ForecastConstants.MAX, getDegree(Double::max, degree) + ThreeBConstants.DEGREE_SIMBOL);
		infoGiorno.put(ForecastConstants.ALLERTA, "non disponibile");
		return infoGiorno;
	}
	

	private Map<String, String> putInMapForecast(Elements root, int orario) {
		Map<String, String> forecast = new LinkedHashMap<>();
		List<String> strings = getStringValue(root, orario);
		forecast.put(ForecastConstants.CIELO, strings.get(0));
		forecast.put(ForecastConstants.TEMPERATURA,  strings.get(1));
		forecast.put(ForecastConstants.TEMP_PERCEPITA,  strings.get(2));
		forecast.put(ForecastConstants.PROB_PIOGGIA,  strings.get(3));
		return forecast;
	}

	private List<String> getStringValue(Elements root, int orario) {
		String strCielo, strTemp, strTempPerc, strProbPioggia;
		try {
			strCielo = getDayTime(root, ThreeBConstants.FORECAST_TAG).get(orario);
			strTemp = getDayTime(root, ThreeBConstants.DEG_TAG).subList(0, 4).get(orario);
			strTempPerc = slicingList(getDayTime(root, ThreeBConstants.TODAY_DEG_TAG), 2).get(orario);
			strProbPioggia = getDayTime(root, ThreeBConstants.DATA_TAG).subList(12, 16).get(orario);
		} catch (Exception e) {
			strCielo = strTemp = strTempPerc = strProbPioggia = "Non disponibile offline";
		}
		return new ArrayList<>(Arrays.asList(strCielo,strTemp,strTempPerc,strProbPioggia));
	}

	
	
	private Double getDegree(BinaryOperator<Double> function, List<String> degree) {
		return degree.stream()
					.map(text -> text.replaceAll(ThreeBConstants.DELETE_DEG_SIMBOL_REGEX, ""))
					.map(Double::parseDouble)
					.reduce(function)
					.orElse(0.0);
	}
	
	private List<String> getDayTime(Elements root, String tag) {
		return root.select(tag).stream()
							   .map(Element::text)
							   .collect(toList());
	}
	
	private List<Integer> formatTimeToInteger(List<String> dayTime) {
		return dayTime.stream()
					  .map(hour -> hour.replaceAll(ThreeBConstants.ONLY_NUMBER_HOUR_REGEX, ""))
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
}
