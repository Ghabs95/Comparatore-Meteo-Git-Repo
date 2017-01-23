package gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import core.InformationManager;
import gui.utilities.ActiveComponentsManager;

public abstract class AbstractWeatherListener implements ActionListener {
	private LinkedList<JCheckBox> meteo;
	private LinkedList<JCheckBox> days;
	private LinkedList<JCheckBox> times;
	protected JTextField searchBox;
	protected JTextArea display;	
	protected InformationManager info;
	

	public AbstractWeatherListener(ActiveComponentsManager acm) {
		setupMeteo(acm);
		setupDays(acm);
		setupTimes(acm);
		searchBox = acm.getTextField("searchBox");
		display = acm.getTextArea("display");
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
	
	public LinkedList<Integer> getSelectedMeteo(){
		LinkedList<Integer> meteoIDs = new LinkedList<>();
		for(JCheckBox box:meteo){
			if(box.isSelected()){
				if(box.getText().contains("Lamma")){
					meteoIDs.add(InformationManager.LAMMA);
				}else if(box.getText().contains("AM")){
					meteoIDs.add(InformationManager.AM);
				}else{
					meteoIDs.add(InformationManager.THREEB);
				}
			}
		}
		
		return meteoIDs;
	}
		
	public LinkedList<Integer> getSelectedDays(){
		LinkedList<Integer> dayIDs = new LinkedList<>();
		for(JCheckBox box:days){
			if(box.isSelected()){
				if(box.getText().contains("Oggi")){
					dayIDs.add(InformationManager.OGGI);
				}else if(box.getText().contains("Domani")){
					dayIDs.add(InformationManager.DOMANI);
				}else{
					dayIDs.add(InformationManager.DOPODOMANI);
				}
			}
		}
		if(dayIDs.size() == 0){dayIDs.add(getDefaultDay());} //se nessuno è selezionato, seleziono domani
		return dayIDs;
	}
	
	private int getDefaultDay(){
		days.get(1).setSelected(true);
		return InformationManager.DOMANI;
	}
	
	public LinkedList<String> getSelectedTimes(){
		LinkedList<String> timeIDs = new LinkedList<>();
		for(JCheckBox box:times){
			if(box.isSelected()){
				if(box.getText().contains("Mattina")){
					timeIDs.add(InformationManager.MATTINA);
				}else if(box.getText().contains("Pomeriggio")){
					timeIDs.add(InformationManager.POMERIGGIO);
				}else{
					timeIDs.add(InformationManager.SERA);
				}
			}
		}
		if(timeIDs.size() == 0){timeIDs.add(getDefaultTime());} //se nessuno è selezionato, seleziono mattina
		return timeIDs;
	}
	
	private String getDefaultTime(){
		times.get(0).setSelected(true);
		return InformationManager.MATTINA;
	}

	public String getMeteo(int meteoID){
		switch(meteoID){
		case InformationManager.LAMMA : return "LAMMA";
		case InformationManager.AM : return "METEO AM";
		case InformationManager.THREEB : return "3B METEO";
		default : return "";
		}
	}
	
	public String getDay(int dayID){
		switch(dayID){
		case InformationManager.OGGI : return "OGGI";
		case InformationManager.DOMANI : return "DOMANI";
		case InformationManager.DOPODOMANI : return "DOPODOMANI";
		default : return "";
		}
		
	}
	
	@Override
	public abstract void actionPerformed(ActionEvent arg0);

}
