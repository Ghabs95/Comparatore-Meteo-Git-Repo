package core.forecast.factory;

public class ThreeBConstants {
	public final static String LAST_UPDATE_TAG = "div.page-header.text-align-sm > div";
	public final static String ROOT_TAG = "div.box.noMarg";
	public final static String DAY_LIST_TAG = "div.navDays";
	public final static String TODAY_DEG_TAG = "span.switchcelsius.switch-te.active";
	public final static String DEG_TAG = "p.big.switchcelsius.switch-te.active";
	public final static String TIME_FORECAST_TAG = "div.col-xs-1-4.big, div.col-xs-2-4";
	public final static String RAIN_TAG = "span.gray";
	public final static String FORECAST_TAG = "small.hidden-xs";
	public final static String DATA_TAG = "div.col-xs-2-3.col-sm-1-5.text-center.altriDati.altriDatiD-active";
	public final static String ONLY_NUMBER_HOUR_REGEX = ":00|[^0-9]+";
	public final static String DEGREE_SIMBOL = "\u00B0";
	public final static String DELETE_DEG_SIMBOL_REGEX = DEGREE_SIMBOL + "|C";
}
