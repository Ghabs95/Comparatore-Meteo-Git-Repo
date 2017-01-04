package core.forecast.factory;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ThreeBForecastFactory extends ForecastAbstractFactory {
	@Override
	public Elements createRoot(Document doc) {
		return doc.select("div.box.noMarg");
	}

	@Override
	public Map<String, String> getInfoGiorno(Elements root, int day) {
		Element dayElement = root.select("div.navDays").get(day);
		if (day == OGGI) {
			return putInMap(root, dayElement, "span.switchcelsius.switch-te.active");
		} else {
			return putInMap(root, dayElement, "p.big.switchcelsius.switch-te.active");
		}
	}

	private Map<String, String> putInMap(Elements root, Element giorno, String stringTag ) {
		Map<String, String> infoGiorno = new LinkedHashMap<>();
		infoGiorno.put("giorno", giorno.text());
		infoGiorno.put("min", getDegree(root, stringTag, Double::min) + "°");
		infoGiorno.put("max", getDegree(root, stringTag, Double::max) + "°");
		return infoGiorno;
	}

	@Override
	public Map<String, String> getPrevisioniOrarie(Elements root, int day, int orario) {
		Map<String, String> hourForecast = new LinkedHashMap<>();
		if(day == OGGI) {
			List<String> dayTime = getDayTime(root, "div.col-xs-1-4.big, div.col-xs-2-4");
			dayTime = getDayTimeList(dayTime);
			hourForecast.put(dayTime.get(0), dayTime.get(1));
		}
		return hourForecast;
	}

	private Double getDegree(Elements root, String tag, BinaryOperator<Double> function) {
		return root.select(tag).stream()
							   .map(Element::text)
							   .map(text -> text.replaceAll("°|C", ""))
							   .map(Double::parseDouble)
							   .reduce(function)
							   .get();
	}
	
	private List<String> getDayTime(Elements root, String tag) {
		return root.select(tag).stream()
							   .map(Element::text)
							   .collect(toList());
	}
	
	private List<String> getDayTimeList(List<String> list) {
		List<String> rst = new ArrayList<>();
		IntStream.iterate(0, i -> i + 2)
				 .limit(list.size()/2)
				 .forEach(i -> rst.add(list.get(i) + " " + list.get(i + 1)));
		return rst;
	}
}
