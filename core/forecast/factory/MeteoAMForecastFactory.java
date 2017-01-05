package core.forecast.factory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

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
		infoGiorno.put("ultimoAggiornamento", lastUpdateRoot.getElementsContainingOwnText("aggiornamento pagina").get(0).text());
		infoGiorno.put("giorno", giorno);
		infoGiorno.put("min", getDegree(dayElement, Integer::min) + "\u00B0");
		infoGiorno.put("max", getDegree(dayElement, Integer::max) + "\u00B0");
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
		for (int i = 1; i < alerts.size(); i = i + 2) {
			Element alert = alerts.get(i).select("td").get(0);
			if (alert.text().equals("-")) {
				allerte += "nessuno, ";
			} else {
				Elements img = alert.select("img[title]");
				for (int j = 0; j < img.size(); j++) {
					allerte += img.get(j).attr("title") + ", ";
				}
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
		if (takeHour(hourElements, orario) != null) {
			infoOra.put("ora", takeHour(hourElements, orario).select("th").text());
			infoOra.put("cielo", takeHour(hourElements, orario).select("td").get(1).select("img[title]").attr("title"));
			infoOra.put("probPrecipitazioni", takeHour(hourElements, orario).select("td").get(2).text());
			infoOra.put("tempPercepita", takeHour(hourElements, orario).select(".temperatura-percepita").text() + "\u00B0");
			infoOra.put("temp", takeHour(hourElements, orario).select("td").get(3).text() + "\u00B0");
		}
		return infoOra;
	}

	private Element takeHour(Elements hours, int orario) {
		Element hour = null;
		String notteH = "04:00";
		String mattinaH = "10:00";
		String pomeriggioH = "16:00";
		String seraH = "22:00";
		int nh = getHour(hours, notteH);
		int mh = getHour(hours, mattinaH);
		int ph = getHour(hours, pomeriggioH);
		int sh = getHour(hours, seraH);
		if (orario == NOTTE && nh != -1 && hours.get(nh).select("th").text().equals(notteH)) {
			hour = hours.get(nh);
		} else if (orario == MATTINA && mh != -1 && hours.get(mh).select("th").text().equals(mattinaH)) {
			hour = hours.get(mh);
		} else if (orario == POMERIGGIO && ph != -1 && hours.get(ph).select("th").text().equals(pomeriggioH)) {
			hour = hours.get(ph);
		} else if (orario == SERA && sh != -1 && hours.get(sh).select("th").text().equals(seraH)) {
			hour = hours.get(sh);
		}
		return hour;
	}

	private int getHour(Elements root, String hour) {
		return root.stream()
				   .map(p -> p.select("th").text())
				   .collect(Collectors.toList())
				   .indexOf(hour);
	}

}
