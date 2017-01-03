package core.forecast.factory;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LammaForecastFactory extends ForecastAbstractFactory {

	@Override
	public Elements createRoot(Document doc) {
		return doc.select("dati");
	}

	@Override
	public Map<String, String> getInfoGiorno(Elements root, int day) {
		int dayIndex = getDayIndex(day);
		Element dayTag = getDayTag(root, dayIndex);
		Map<String, String> infoGiorno = new HashMap<>();
		infoGiorno.put("giorno", dayTag.attr("datadescr"));
		infoGiorno.put("min", getContent(dayTag, "temp", 0));
		infoGiorno.put("max", getContent(dayTag, "temp", 1));
		infoGiorno.put("allerte", getAlerts(dayTag));
		return infoGiorno;
	}

	@Override
	public Map<String, String> getPrevisioniOrarie(Elements root, int day, int orario) {
		Map<String, String> hourForecast = new HashMap<>();
		Element forecastTag = getForecastTag(root, day, orario);
		if (tagNotFound(forecastTag)) {
			return addEmptyMap(hourForecast);
		} // se il tag non c'e' ritorna la stringa vuota
		hourForecast.put("cielo", getNodeAttribute(getNthNode(forecastTag, "simbolo", 0), "descr"));
		hourForecast.put("temp", getContent(forecastTag, "temp", 0));
		hourForecast.put("temp_perc", getContent(forecastTag, "temp", 1));
		hourForecast.put("rain_prob", getRainProb(forecastTag));
		return hourForecast;
	}

	private Element getForecastTag(Elements root, int day, int orario) {
		Elements forecastList = root.select("previsione");
		int hTagIndex = getHourIndex(day, orario);
		return (Element) forecastList.get(hTagIndex);
	}

	private boolean tagNotFound(Element forecastTag) {
		String hour = forecastTag.attr("ora");
		return hour.equals("giorno") ? true : false;
	}

	private int getDayIndex(int day) {
		if (day == 1) {
			day = 4;
		} else if (day == 2) {
			day = 9;
		}
		return day;
	}

	private Element getDayTag(Elements root, int dayIndex) {
		return (Element)(root.select("previsione").get(dayIndex));
	}

	private Map<String, String> addEmptyMap(Map<String, String> map) {
		map.put("", "");
		return map;
	}

	private String getAlerts(Element dayTag) {
		String value = dayTag.attr("allerta");
		String alerts = "";
		if (value != "") {
			if (!(value.equals("nessuno"))) {
				alerts = "Allerta: " + value + "\n";
				alerts += getRisks(dayTag);
			}
		}
		return alerts;
	}

	private String getRisks(Element dayTag) {
		Elements risks = dayTag.getElementsByTag("rischio");
		String allRisks = "";
		for (int i = 0; i < risks.size(); i++) {
			allRisks += getRisk(risks.get(i));
		}
		return allRisks;
	}

	private String getRisk(Element element) {
		String value = getNodeAttribute(element, "value");
		if (value.equals("nessuno")) {
			return "";
		}
		String str = getNodeAttribute(element, "descr");
		str += ": " + value + "\n";
		return str;
	}

	private int getHourIndex(int day, int orario) {
		int increment = 0;
		switch (orario) {
		case NOTTE:
			increment = 1;
			break;
		case MATTINA:
			increment = 2;
			break;
		case POMERIGGIO:
			increment = 3;
			break;
		case SERA:
			increment = 4;
			break;
		default:
			increment = 1;
		}
		if (day == OGGI) {
			increment--;
		}
		return (getDayIndex(day) + increment);
	}

	private String getRainProb(Element forecastTag) {
		String rainProb = getContent(forecastTag, "prob_rain", 0);
		if (rainProb.equals("")) {
			rainProb = "Non Disponibile";
		}
		return rainProb;
	}


}
