package gui;

import java.awt.GridBagConstraints;

import javax.swing.event.CaretListener;

import gui.display.DisplayContainer;
import gui.listeners.LocationAvailability;
import gui.listeners.AbstractWeatherListener;
import gui.listeners.CompareElementListener;
import gui.listeners.CustomWeatherListener;
import gui.search.SearchContainer;
import gui.utilities.GridBagContainer;

@SuppressWarnings("serial")
public class ContentPane extends GridBagContainer {

	@Override
	public void addComponents() {
		addSearch();
		addDisplay();
		// LISTENERS:
		addLocationAvailability();
		addCustomWeatherListener();
		addCompareElementListener();
	}

	private void addSearch() {
		GridBagContainer searchC = new SearchContainer();
		lim.setPosition(0, 0);
		lim.setFillAndAnchor(GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);
		lim.setGridCellDimension(1, 1);
		getChildActiveComponents(searchC.getActiveComponents());
		this.add(searchC, lim);
	}

	private void addDisplay() {
		GridBagContainer displayC = new DisplayContainer();
		lim.setPosition(1, 0);
		lim.setInsets(10, 10, 10, 10);
		lim.setFillAndAnchor(GridBagConstraints.BOTH, GridBagConstraints.CENTER);
		lim.setGridCellDimension(1, 1);
		getChildActiveComponents(displayC.getActiveComponents());
		this.add(displayC, lim);
	}

	private void addLocationAvailability() {
		CaretListener la = new LocationAvailability(activeComponents);
		activeComponents.getTextField("searchBox").addCaretListener(la);
	}

	private void addCustomWeatherListener() {
		AbstractWeatherListener twl = new CustomWeatherListener(activeComponents);
		activeComponents.getRadioButton("customWeather").addActionListener(twl);
	}

	private void addCompareElementListener() {
		AbstractWeatherListener cel = new CompareElementListener(activeComponents);
		activeComponents.getRadioButton("forecastElement").addActionListener(cel);
	}

}
