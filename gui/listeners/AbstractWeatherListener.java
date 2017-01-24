package gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

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
	
	private void setupMeteo(ActiveComponentsManager acm){
		meteo = new LinkedList<>();
		meteo.add(acm.getCheckBox("checkLamma"));
		meteo.add(acm.getCheckBox("checkAM"));
		meteo.add(acm.getCheckBox("check3B"));
	}
	
	private void setupDays(ActiveComponentsManager acm){
		days = new LinkedList<>();
		days.add(acm.getCheckBox("checkOggi"));
		days.add(acm.getCheckBox("checkDomani"));
		days.add(acm.getCheckBox("checkDopodomani"));
	}
	
	private void setupTimes(ActiveComponentsManager acm){
		times = new LinkedList<>();
		times.add(acm.getCheckBox("checkMattina"));
		times.add(acm.getCheckBox("checkPomeriggio"));
		times.add(acm.getCheckBox("checkSera"));
	}
	
	//TODO #refactoring: rifattorizzare col metodo sotto!
	public LinkedList<Integer> getSelectedMeteo(){
		LinkedList<Integer> meteoIDs = new LinkedList<>();
		for(JCheckBox box:meteo){
			if(box.isSelected()){
				if(box.getText().contains("Lamma")){
					meteoIDs.add(ForecastConstants.LAMMA);
				}else if(box.getText().contains("AM")){
					meteoIDs.add(ForecastConstants.AM);
				}else{
					meteoIDs.add(ForecastConstants.THREEB);
				}
			}
		}
		
		return meteoIDs;
	}
		
	//TODO #refactoring: rifattorizzare col metodo sopra!
	public LinkedList<Integer> getSelectedDays(){
		LinkedList<Integer> dayIDs = new LinkedList<>();
		for(JCheckBox box:days){
			if(box.isSelected()){
				if(box.getText().contains("Oggi")){
					dayIDs.add(ForecastConstants.OGGI);
				}else if(box.getText().contains("Domani")){
					dayIDs.add(ForecastConstants.DOMANI);
				}else{
					dayIDs.add(ForecastConstants.DOPODOMANI);
				}
			}
		}
		if(dayIDs.size() == 0){dayIDs.add(getDefaultDay());} //se nessuno � selezionato, seleziono domani
		return dayIDs;
	}
	
	private int getDefaultDay(){
		days.get(1).setSelected(true);
		return ForecastConstants.DOMANI;
	}
	
	public LinkedList<String> getSelectedTimes(){
		LinkedList<String> timeIDs = new LinkedList<>();
		for(JCheckBox box:times){
			if(box.isSelected()){
				if(box.getText().contains("Mattina")){
					timeIDs.add(ForecastConstants.MATTINA);
				}else if(box.getText().contains("Pomeriggio")){
					timeIDs.add(ForecastConstants.POMERIGGIO);
				}else{
					timeIDs.add(ForecastConstants.SERA);
				}
			}
		}
		if(timeIDs.size() == 0){timeIDs.add(getDefaultTime());} //se nessuno � selezionato, seleziono mattina
		return timeIDs;
	}
	
	private String getDefaultTime(){
		return ForecastConstants.INFOGIORNO;
	}

	public String getMeteo(int meteoID){
		switch(meteoID){
		case ForecastConstants.LAMMA : return "LAMMA";
		case ForecastConstants.AM : return "METEO AM";
		case ForecastConstants.THREEB : return "3B METEO";
		default : return "";
		}
	}
	
	public String getDay(int dayID){
		switch(dayID){
		case ForecastConstants.OGGI : return "OGGI";
		case ForecastConstants.DOMANI : return "DOMANI";
		case ForecastConstants.DOPODOMANI : return "DOPODOMANI";
		default : return "";
		}
		
	}
	
	@Override
	public abstract void actionPerformed(ActionEvent arg0);

}
