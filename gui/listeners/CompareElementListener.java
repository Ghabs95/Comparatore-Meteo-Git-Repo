package gui.listeners;

import java.util.List;

import javax.swing.JComboBox;

import gui.utilities.ActiveComponentsManager;

public class CompareElementListener extends AbstractWeatherListener {
	private JComboBox<String> chooseElement;

	public CompareElementListener(ActiveComponentsManager acm) {
		super(acm);
		chooseElement = acm.getComboBox("chooseForecastElement");
	}

	@Override
	public void print(String location, List<String> timesIDs, int mID, int dID) {
		display.append("\n--" + getDay(dID) + "--\n");
		timesIDs.forEach(tID -> displayAppend(location, mID, dID, tID));
		display.append("\n\n");
		cancel.setSelected(true);
	}

	private void displayAppend(String location, int mID, int dID, String tID) {
		display.append("*" + tID + "*\n");
		String ce = getChoosedElement();
		display.append(getChoosedElement() + ": " + info.getPrintableForecastElement(mID, location, dID, tID, ce));
		display.append("\n");
	}

	private String getChoosedElement() {
		return (String) chooseElement.getSelectedItem();
	}

}
