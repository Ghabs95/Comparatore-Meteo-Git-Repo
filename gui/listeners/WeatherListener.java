package gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import core.InformationManager;
import gui.utilities.ActiveComponentsManager;

public class WeatherListener implements ActionListener {
	private JTextField searchBox;
	private JCheckBox lamma;
	private JCheckBox am;
	private JCheckBox threeB;
	private JTextArea display;
	private InformationManager info;
	private int day;
	
	
	public WeatherListener(ActiveComponentsManager acm, int dayID) {
		searchBox = acm.getTextField("searchBox");
		lamma = acm.getCheckBox("checkLamma");
		am = acm.getCheckBox("checkAM");
		threeB = acm.getCheckBox("check3B");
		display = acm.getTextArea("display");
		info = new InformationManager();
		day = dayID;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String location = searchBox.getText();
		display.setText("");    //ripulisco il display
		if(lamma.isSelected()){
			displayMeteo("LAMMA", InformationManager.LAMMA, location, day);
		}
		if(am.isSelected()){
			displayMeteo("METEO AM", InformationManager.AM, location, day);
		}
		if(threeB.isSelected()){
			displayMeteo("3b METEO", InformationManager.THREEB, location, day);
		}
								
	}
	
	private void displayMeteo(String meteo, int meteoID, String location, int dayID){
		info.gatherForecastLocation(meteoID, location);
		String forecast = info.getPrintableForecast(meteoID, location, dayID);
		display.append(meteo.toUpperCase()+"\n\n"+forecast);
	}

}
