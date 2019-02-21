package core.forecast.factory;

import java.util.LinkedHashMap;
import java.util.Map;
import static java.util.stream.Collectors.*;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import core.forecast.ForecastConstants;

public class LammaForecastFactory extends ForecastAbstractFactory {

	@Override
	public Elements createRoot(Document doc) {
		return doc.select("dati");
	}

	@Override
	public Map<String, String> getInfoGiorno(Elements root, int day) {
		int dayIndex = getDayIndex(day);
		Element dayTag = root.select("previsione").get(dayIndex);
		Map<String, String> infoGiorno = new LinkedHashMap<>();
		infoGiorno.put(ForecastConstants.AGGIORNAMENTO, root.select("aggiornamento").text());
		infoGiorno.put(ForecastConstants.GIORNO, dayTag.attr("datadescr"));
		infoGiorno.put(ForecastConstants.MIN, dayTag.select("temp").get(0).text());
		infoGiorno.put(ForecastConstants.MAX, dayTag.select("temp").get(1).text());
		infoGiorno.put(ForecastConstants.ALLERTA, getAlerts(dayTag));
		return infoGiorno;
	}

	@Override
	public Map<String, String> getPrevisioniOrarie(Elements root, int day, int orario) {
		Map<String, String> hourForecast = new LinkedHashMap<>();
		Element forecastTag = root.select("previsione").get(getHourIndex(day, orario));
		if (!tagNotFound(forecastTag)) {
			hourForecast.put(ForecastConstants.CIELO, forecastTag.select("simbolo").get(0).attr("descr"));
			hourForecast.put(ForecastConstants.TEMPERATURA, forecastTag.select("temp").get(0).text());
			hourForecast.put(ForecastConstants.TEMP_PERCEPITA, forecastTag.select("temp").get(1).text());
			hourForecast.put(ForecastConstants.PROB_PIOGGIA, forecastTag.select("prob_rain").text());
		}
		return hourForecast;
	}

	private boolean tagNotFound(Element forecastTag) {
		String hour = forecastTag.attr("ora");
		return hour.equals("giorno");
	}

	private int getDayIndex(int day) {
		if (day == 1) {
			day = 4;
		} else if (day == 2) {
			day = 9;
		}
		return day;
	}

	private String getAlerts(Element dayTag) {
		String value = dayTag.select("allerta").attr("value");
		String alerts = "";
		if (value != "" && !value.equals("nessuno") && !value.contains("NA")) {
			alerts = "Allerta: " + value + "\n";
			alerts += getRisks(dayTag);
		} else {
			alerts = "nessuno";
		}
		return alerts;
	}

	private String getRisks(Element dayTag) {
		Elements risks = dayTag.select("rischio");
		return risks.stream().map(this::getRisk).collect(joining());
	}

	private String getRisk(Element element) {
		String value = element.attr("value");
		if (value.equals("nessuno")) {
			return "";
		}
		String str = element.attr("descr");
		str += ": " + value + "\n";
		return str;
	}

	private int getHourIndex(int day, int orario) {
		int increment = 0;
		switch (orario) {
		case FactoryConstants.NOTTE:
			increment = 1;
			break;
		case FactoryConstants.MATTINA:
			increment = 2;
			break;
		case FactoryConstants.POMERIGGIO:
			increment = 3;
			break;
		case FactoryConstants.SERA:
			increment = 4;
			break;
		default:
			increment = 1;
		}
		if (day == FactoryConstants.OGGI) {
			increment--;
		}
		return (getDayIndex(day) + increment);
	}

}
