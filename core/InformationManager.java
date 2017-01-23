package core;

import java.util.Map;

import core.forecast.Forecast;
import core.forecast.ForecastTree;

public class InformationManager {
	public final static int LAMMA = ForecastTree.LAMMA;
	public final static int AM = ForecastTree.AM;
	public final static int THREEB = ForecastTree.THREEB;
	public final static int OGGI = ForecastTree.OGGI;
	public final static int DOMANI = ForecastTree.DOMANI;
	public final static int DOPODOMANI = ForecastTree.DOPODOMANI;
	public final static String NOTTE = ForecastTree.NOTTE;
	public final static String MATTINA = ForecastTree.MATTINA;
	public final static String POMERIGGIO = ForecastTree.POMERIGGIO;
	public final static String SERA = ForecastTree.SERA;

	private ForecastTree ft;

	public InformationManager() {
		ft = new ForecastTree();
	}
	
	public String getPrintablePartialForecast(int meteoID, String location, int dayID, String timeID){
		String partialForecast = "";
		Forecast tmp = ft.getDayForecast(meteoID, location, dayID);
		Map<String,String> pf = tmp.getPartialForecast(timeID);
		for(String key: pf.keySet()){
			partialForecast += key +": "+ pf.get(key) +"\n";
		}
		return partialForecast;
	}
	
	
}
