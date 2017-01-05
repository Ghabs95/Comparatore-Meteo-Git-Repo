package core.forecast.factory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MeteoAMForecastFactory extends ForecastAbstractFactory {
	private Element lastUpdateRoot;	

	@Override
	public Elements createRoot(Document doc) {
		this.lastUpdateRoot = doc.select("#block-system-main").first();		
		return doc.select("#giorno");
	}

	@Override
	public Map<String, String> getInfoGiorno(Elements root, int day) {
		if (day == OGGI) {
			return putInMap(root, "oggi");
		} else if (day == DOMANI) {
			return putInMap(root, "domani");
		} else {
			return putInMap(root, "tregiorni");
		}
	}

	private Map<String, String> putInMap(Elements root, String giorno) {
		Map<String, String> infoGiorno = new LinkedHashMap<>();
		Element dayElement = root.select("#" + giorno).select("tbody").first();
//		infoGiorno.put("ultimoAggiornamento", lastUpdateRoot.select("p").get(3).text());
		infoGiorno.put("giorno", giorno);
		infoGiorno.put("min", getDegree(dayElement, Integer::min) + "°");
		infoGiorno.put("max", getDegree(dayElement, Integer::max) + "°");
		infoGiorno.put("allerte", getAlerts(dayElement));
		return infoGiorno;
	}

	private Integer getDegree(Element root, BinaryOperator<Integer> function) {
		Elements degs = root.select("tr");
		return degs.stream()
				  .map(p -> p.select("td").get(3))
				  .map(Element::text)
				  .map(Integer::parseInt)
				  .reduce(function)
				  .get();
	}

	private String getAlerts(Element root) {
		Elements alerts = root.select("tr");
		String allerte = "";
		for(int i = 1; i < alerts.size(); i = i + 2) {
			Element alert = alerts.get(i).select("td").get(0);
			if(alert.text().equals("-")) {
				allerte += "nessuno, ";
			} else {
				allerte += alert.select("img[title]").attr("title");
			}
		}
		return allerte;
	}

	@Override
	public Map<String, String> getPrevisioniOrarie(Elements root, int day, int orario) {
		if (day == OGGI) {
			return putInMapHour(root, "oggi", orario);
		} else if (day == DOMANI) {
			return putInMapHour(root, "domani", orario);
		} else {
			return putInMapHour(root, "tregiorni", orario);
		}
	}

	private Map<String, String> putInMapHour(Elements root, String giorno, int orario) {
		Map<String, String> infoOra = new LinkedHashMap<>();
		Element dayElement = root.select("#" + giorno).select("tbody").first();
		Elements hourElements = dayElement.select("tr");
		infoOra.put("ora", takeHour(dayElement.select("th"), orario).text());
		infoOra.put("cielo", takeHour(hourElements, orario).select("img[title]").attr("title"));
		infoOra.put("probPrecipitazioni", takeHour(hourElements, orario).select("td").get(2).text());
		infoOra.put("tempPercepita", takeHour(hourElements, orario).select(".temperatura-percepita").text());
		infoOra.put("temp", takeHour(hourElements, orario).select("td").get(3).text());
		return infoOra;
	}

	private Element takeHour(Elements hours, int orario) {
		Element hour;
		if (orario == NOTTE && hours.size() >= 2) {
			hour = hours.get(1);
		} else if (orario == MATTINA && hours.size() >= 4) {
			hour = hours.get(3);
		} else if (orario == POMERIGGIO && hours.size() >= 6) {
			hour = hours.get(5);
		} else if (orario == SERA && hours.size() >= 8) {
			hour = hours.get(7);
		} else {
			hour = hours.get(0);
		}
		return hour;
	} 
}
