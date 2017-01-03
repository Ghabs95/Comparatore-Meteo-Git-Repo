package core.forecast.factory;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import core.forecast.Forecast;

public abstract class ForecastAbstractFactory {
	public final static int OGGI = 0;
	public final static int DOMANI = 1;
	public final static int DOPODOMANI = 2;
	public final static int NOTTE = 0;
	public final static int MATTINA = 1;
	public final static int POMERIGGIO = 2;
	public final static int SERA = 3;
	public final static int MIN = 0;
	public final static int MAX = 1;
	
	public Forecast createForecast(Document doc, int day) {
		Elements root = createRoot(doc);
		Map<String, Map<String, String>> forecast = new HashMap<>();
		forecast.put("infoGiorno", getInfoGiorno(root, day));
		forecast.put("notte", getPrevisioniOrarie(root, day, NOTTE));
		forecast.put("mattina", getPrevisioniOrarie(root, day, MATTINA));
		forecast.put("pomeriggio", getPrevisioniOrarie(root, day, POMERIGGIO));
		forecast.put("sera", getPrevisioniOrarie(root, day, SERA));
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
		if (node == null) {
			return "";
		} // se il tag non esiste
		return node.text();
	}

	// Restituisce l'N-esimo nodo, dato un certo tag
	public Element getNthNode(Element root, String name, int tagPosition) {
		Elements tmp = root.select(name);
		if (tmp.size() == 0) {
			return null;
		} // se il tag non e' presente
		else {
			return tmp.get(tagPosition);
		}
	}

	// Dato un Node, lo trasforma in un Element (solo se e' lecito)
//	@SuppressWarnings("static-access")
//	public Element nodeToElement(Node node) {
//		if (node.getNodeType() == node.ELEMENT_NODE) {
//			return (Element) node;
//		} else {
//			return null;
//		}
//	}

	// Dato un nodo, ritorna il valore di un suo attributo
	public String getNodeAttribute(Node node, String attrName) {
		Element tmp = (Element) node;
		return tmp.attr(attrName);
	}

}
