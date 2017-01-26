package gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import core.InformationManager;
import core.forecast.ForecastConstants;
import gui.utilities.ActiveComponentsManager;

public abstract class AbstractWeatherListener implements ActionListener {
	private LinkedList<JCheckBox> meteo;
	private LinkedList<JCheckBox> days;
	private LinkedList<JCheckBox> times;
	protected JTextField searchBox;
	protected JTextArea display;
	protected JRadioButton cancel;
	protected InformationManager info;

	public AbstractWeatherListener(ActiveComponentsManager acm) {
		setupMeteo(acm);
		setupDays(acm);
		setupTimes(acm);
		searchBox = acm.getTextField("searchBox");
		display = acm.getTextArea("display");
		cancel = acm.getRadioButton("cancel");
		info = new InformationManager();
	}

	private void setupMeteo(ActiveComponentsManager acm) {
		meteo = new LinkedList<>();
		meteo.add(acm.getCheckBox("checkLamma"));
		meteo.add(acm.getCheckBox("checkAM"));
		meteo.add(acm.getCheckBox("check3B"));
	}

	private void setupDays(ActiveComponentsManager acm) {
		days = new LinkedList<>();
		days.add(acm.getCheckBox("checkOggi"));
		days.add(acm.getCheckBox("checkDomani"));
		days.add(acm.getCheckBox("checkDopodomani"));
	}

	private void setupTimes(ActiveComponentsManager acm) {
		times = new LinkedList<>();
		times.add(acm.getCheckBox("checkMattina"));
		times.add(acm.getCheckBox("checkPomeriggio"));
		times.add(acm.getCheckBox("checkSera"));
	}

	public List<Integer> getSelectedMeteo() {
		List<Integer> meteoIDs = new LinkedList<>();
		getFiltered(meteo, "Lamma").forEach(box -> meteoIDs.add(ForecastConstants.LAMMA));
		getFiltered(meteo, "AM").forEach(box -> meteoIDs.add(ForecastConstants.AM));
		getFiltered(meteo, "3B").forEach(box -> meteoIDs.add(ForecastConstants.THREEB));

		return meteoIDs;
	}

	private Stream<JCheckBox> getFiltered(List<JCheckBox> meteo, String meteoName) {
		return meteo.stream()
					.filter(box -> box.isSelected())
					.filter(box -> box.getText().contains(meteoName));
	}

	public List<Integer> getSelectedDays() {
		LinkedList<Integer> dayIDs = new LinkedList<>();
		getFiltered(days, "Oggi").forEach(box -> dayIDs.add(ForecastConstants.OGGI));
		getFiltered(days, "Domani").forEach(box -> dayIDs.add(ForecastConstants.DOMANI));
		getFiltered(days, "Dopodomani").forEach(box -> dayIDs.add(ForecastConstants.DOPODOMANI));

		return dayIDs.size() == 0 ? getDefaultDay() : dayIDs;
	}

	private List<Integer> getDefaultDay() {
		days.get(1).setSelected(true);
		return new LinkedList<>(Arrays.asList(ForecastConstants.DOMANI));
	}

	public List<String> getSelectedTimes() {
		LinkedList<String> timeIDs = new LinkedList<>();
		getFiltered(times, "Mattina").forEach(box -> timeIDs.add(ForecastConstants.MATTINA));
		getFiltered(times, "Pomeriggio").forEach(box -> timeIDs.add(ForecastConstants.POMERIGGIO));
		getFiltered(times, "Sera").forEach(box -> timeIDs.add(ForecastConstants.SERA));
		return timeIDs.size() == 0 ? getDefaultTime() : timeIDs;
	}

	private List<String> getDefaultTime() {
		return new LinkedList<>(Arrays.asList(ForecastConstants.INFOGIORNO));
	}

	public String getMeteo(int meteoID) {
		switch (meteoID) {
		case ForecastConstants.LAMMA:
			return "LAMMA";
		case ForecastConstants.AM:
			return "METEO AM";
		case ForecastConstants.THREEB:
			return "3B METEO";
		default:
			return "";
		}
	}

	public String getDay(int dayID) {
		switch (dayID) {
		case ForecastConstants.OGGI:
			return "OGGI";
		case ForecastConstants.DOMANI:
			return "DOMANI";
		case ForecastConstants.DOPODOMANI:
			return "DOPODOMANI";
		default:
			return "";
		}

	}

	@Override
	public abstract void actionPerformed(ActionEvent arg0);

}
