package core.forecast.factory;

import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import core.forecast.Forecast;
import core.forecast.ForecastConstants;

public abstract class ForecastAbstractFactory {
	
	public Forecast createForecast(Document doc, int day) {
		Elements root = createRoot(doc);
		Map<String, Map<String, String>> forecast = new LinkedHashMap<>();
		forecast.put(ForecastConstants.INFOGIORNO, getInfoGiorno(root, day));
		forecast.put(ForecastConstants.NOTTE, getPrevisioniOrarie(root, day, FactoryConstants.NOTTE));
		forecast.put(ForecastConstants.MATTINA, getPrevisioniOrarie(root, day, FactoryConstants.MATTINA));
		forecast.put(ForecastConstants.POMERIGGIO, getPrevisioniOrarie(root, day, FactoryConstants.POMERIGGIO));
		forecast.put(ForecastConstants.SERA, getPrevisioniOrarie(root, day, FactoryConstants.SERA));
		return new Forecast(forecast);
	}

	public abstract Elements createRoot(Document doc);

	public abstract Map<String, String> getInfoGiorno(Elements root, int day);

	public abstract Map<String, String> getPrevisioniOrarie(Elements root, int day, int orario);

	/* Funzioni per prendere informazioni nell'albero */

	// Prende il contenuto tra il tag aperto e quello chiuso.
	// Tag position va indicata in base all'ordine in cui i tag vengono trovati
	// (es. 0 se e' il primo)
	public String getContent(Element rootElement, String tagName, int tagPosition) {
		Element node = getNthNode(rootElement, tagName, tagPosition);
		if (node == null) { return ""; } // se il tag non esiste
		return node.text();
	}

	// Restituisce l'N-esimo nodo, dato un certo tag
	public Element getNthNode(Element root, String name, int tagPosition) {
		Elements tmp = root.select(name);
		if (tmp.size() == 0) { return null;	} // se il tag non e' presente
		return tmp.get(tagPosition);
	}

	// Dato un nodo, ritorna il valore di un suo attributo
	public String getNodeAttribute(Node node, String attrName) {
		Element tmp = (Element) node;
		return tmp.attr(attrName);
	}

}
