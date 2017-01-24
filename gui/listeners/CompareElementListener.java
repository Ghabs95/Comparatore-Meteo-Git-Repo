package gui.listeners;

import java.awt.event.ActionEvent;
import java.util.LinkedList;

import javax.swing.JComboBox;

import gui.utilities.ActiveComponentsManager;

public class CompareElementListener extends AbstractWeatherListener {
	private JComboBox<String> chooseElement;

	public CompareElementListener(ActiveComponentsManager acm) {
		super(acm);
		chooseElement = acm.getComboBox("chooseForecastElement");
	}

	//TODO #refactoring: rifattorizzare gli elementi in comune con CustomWeatherListener
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String location = searchBox.getText();
		display.setText("");  //ripulisco il display
		LinkedList<Integer> meteoIDs = getSelectedMeteo();
		if(meteoNotSelected(meteoIDs)){ return; } //se non c'è un meteo selezionato interrompo
		LinkedList<Integer> daysIDs = getSelectedDays();
		LinkedList<String> timesIDs = getSelectedTimes();
		for(int mID:meteoIDs){
			display.append("+++"+getMeteo(mID)+"+++\n\n");
			for(int dID:daysIDs){
				display.append("\n--"+getDay(dID)+"--\n");
				for(String tID:timesIDs){
					display.append("*"+tID+"*\n");
					String ce = getChoosedElement();
					display.append(ce +": "+info.getPrintableForecastElement(mID, location, dID, tID, ce));
					display.append("\n");
				}
			}
			display.append("\n\n");
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
	
	private String getChoosedElement(){
		return (String)chooseElement.getSelectedItem();
	}

}
