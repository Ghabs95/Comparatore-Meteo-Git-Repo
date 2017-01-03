package core.forecast.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MeteoAMForecastFactory extends ForecastAbstractFactory {
	
	@Override
	public Elements createRoot(Document doc) {
//		System.out.println(doc.select("#giorno"));
		return doc.select("#giorno");
	}

	@Override
	public Map<String, String> getInfoGiorno(Elements root, int day) {
		if(day == OGGI) {
			return putInMap(root, "oggi");
		} else if(day == DOMANI) {
			return putInMap(root, "domani");
		} else {
			return putInMap(root, "tregiorni");
		}
	}
	
	private Map<String, String> putInMap(Elements root, String giorno) {
		Map<String, String> infoGiorno = new HashMap<>();
		Element dayElement = root.select("#" + giorno).first();
		infoGiorno.put("giorno", giorno);
		infoGiorno.put("min", getDegree(dayElement, "td", Integer::min) + "°");
		infoGiorno.put("max", getDegree(dayElement, "td", Integer::max) + "°");
		return infoGiorno;
	}
	
	private Integer getDegree(Element root, String tag, BinaryOperator<Integer> function) {
		Elements deg = root.select(tag);		
//		return deg.stream().map(Element::text).filter(p -> !p.equals("-") && !p.equals("")).map(Integer::parseInt).reduce(function).get();

		return null;
	}

	@Override
	public Map<String, String> getPrevisioniOrarie(Elements root, int day, int orario) {
		if(day == OGGI) {
			return putInMapHour(root, "oggi", orario);
		} else if(day == DOMANI) {
			return putInMapHour(root, "domani", orario);
		} else {
			return putInMapHour(root, "tregiorni", orario);
		}
	}
	
//	private int fixHour(int orario) {
//		if(orario == 0)
//		return orario;
//	}

	private Map<String, String> putInMapHour(Elements root, String giorno, int orario) {
		Map<String, String> infoOra = new HashMap<>();
		Element dayElement = root.select("#" + giorno).select("tbody").select("tr").get(orario);
		infoOra.put("ora", dayElement.select("th").text());
		infoOra.put("temp", dayElement.select("td").get(3).text());
		
		return infoOra;
	}

	
	/*
	private String findDate(Elements root,int day){
		// Elements listaTag = root.getElementsByTag("th");
		
		Elements el = root.select("th");

		Iterator<Element> it = el.iterator();
		int count = 0;
		while(it.hasNext()){
			Element tmp = it.next();
			count++;
			System.out.println("ELEMENTO "+count+": "+tmp.toString());
		}

		return "";
	}
	*/
	
}
