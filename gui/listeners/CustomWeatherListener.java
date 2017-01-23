package gui.listeners;

import java.awt.event.ActionEvent;
import java.util.LinkedList;

import javax.swing.JRadioButton;

import gui.utilities.ActiveComponentsManager;

public class CustomWeatherListener extends AbstractWeatherListener {
	private JRadioButton cancel;
		
	public CustomWeatherListener(ActiveComponentsManager acm) {
		super(acm);
		cancel = acm.getRadioButton("cancel");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String location = searchBox.getText();
		display.setText("");  //ripulisco il display
		LinkedList<Integer> meteoIDs = getSelectedMeteo();
		if(meteoNotSelected(meteoIDs)){ return; } //se non c'è un meteo selezionato interrompo
		LinkedList<Integer> daysIDs = getSelectedDays();
		LinkedList<String> timesIDs = getSelectedTimes();
		for(int mID:meteoIDs){
			display.append("\n+++"+getMeteo(mID)+"+++\n\n");
			for(int dID:daysIDs){
				display.append("--"+getDay(dID)+"--\n");
				for(String tID:timesIDs){
					display.append("*"+tID+"*\n");
					display.append(info.getPrintablePartialForecast(mID, location, dID, tID));
					display.append("\n");
				
				}
			}
		}
		cancel.setSelected(true);
	}
	
	private boolean meteoNotSelected(LinkedList<Integer> meteoIDs){
		if(meteoIDs.size() == 0){
			display.append("SELEZIONARE UN SERVIZIO METEO!\n");
			cancel.setSelected(true);
			return true;
		}
		return false;
	}
	

}
