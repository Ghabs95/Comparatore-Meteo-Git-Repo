package gui.listeners;

import java.util.List;

import gui.utilities.ActiveComponentsManager;

public class CustomWeatherListener extends AbstractWeatherListener {

	public CustomWeatherListener(ActiveComponentsManager acm) {
		super(acm);
	}

	@Override
	public void print(String location, List<String> timesIDs, int mID, int dID) {
		display.append("--" + getDay(dID) + "--\n");
		timesIDs.forEach(tID -> displayAppend(location, mID, dID, tID));
		display.append("\n\n");
		cancel.setSelected(true);
	}

	private void displayAppend(String location, int mID, int dID, String tID) {
		display.append("*" + tID + "*\n");
		display.append(info.getPrintablePartialForecast(mID, location, dID, tID));
		display.append("\n");
	}
}
