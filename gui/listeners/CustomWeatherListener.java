package gui.listeners;

import java.awt.event.ActionEvent;
import java.util.List;

import gui.utilities.ActiveComponentsManager;

public class CustomWeatherListener extends AbstractWeatherListener {
	
		
	public CustomWeatherListener(ActiveComponentsManager acm) {
		super(acm);
	}
	
	//TODO #refactoring: rifattorizzare con i metodi in comune al CompareElementListener
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String location = searchBox.getText();
		display.setText("");  //ripulisco il display
		List<Integer> meteoIDs = getSelectedMeteo();
		if(meteoNotSelected(meteoIDs)){ return; } //se non c'è un meteo selezionato interrompo
		List<Integer> daysIDs = getSelectedDays();
		List<String> timesIDs = getSelectedTimes();
		for(int mID:meteoIDs){
			display.append("+++"+getMeteo(mID)+"+++\n\n");
			for(int dID:daysIDs){
				display.append("--"+getDay(dID)+"--\n");
				for(String tID:timesIDs){
					display.append("*"+tID+"*\n");
					display.append(info.getPrintablePartialForecast(mID, location, dID, tID));
					display.append("\n");
				
				}
			}
			display.append("\n\n");
		}
		cancel.setSelected(true);
	}
	
	private boolean meteoNotSelected(List<Integer> meteoIDs){
		if(meteoIDs.size() == 0){
			display.append("SELEZIONARE UN SERVIZIO METEO!\n");
			cancel.setSelected(true);
			return true;
		}
		return false;
	}
	

}
