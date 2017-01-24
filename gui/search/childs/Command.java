package gui.search.childs;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import core.forecast.Forecast;
import gui.ForecastComparatorMain;
import gui.utilities.GridBagContainer;

@SuppressWarnings("serial")
public class Command extends GridBagContainer {
	private ButtonGroup group;

	@Override
	public void addComponents() {
		addLabel();
		operationConstraintsSetup();
		addCancelButton();
		//Operazioni:
		addOperation("customWeather", "Meteo", 1);
		addOperation("forecastElement","Confronta ", 2);
		addComboBox("chooseForecastElement",Forecast.getAvailableElements(), 2);
	}
	
	private void addLabel() {
		JLabel sl = new JLabel("Operazione");
		sl.setFont(new Font("TimesRoman",Font.BOLD,14));
		lim.setPosition(0,0);
		lim.setInsets(10,0,10,0);
		lim.setFillAndAnchor(GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
		lim.setGridCellDimension(1,1);
		this.add(sl, lim);
	}
	
	private void operationConstraintsSetup(){
		group = new ButtonGroup();
		lim.setInsets(5, 0, 5, 0);
		lim.setFillAndAnchor(GridBagConstraints.NONE, GridBagConstraints.WEST);
		lim.setGridCellDimension(1,1);
	}
	
	private void addOperation(String name, String description, int y){
		JRadioButton button = new JRadioButton(description);
		button.setBackground(ForecastComparatorMain.BACKGROUND);
		lim.setPosition(0, y);
		activeComponents.addRadioButton(name, button);
		group.add(button);
		this.add(button,lim);
	}
	
	private void addComboBox(String name, LinkedList<String> description, int y){
		JComboBox<String> box = new JComboBox<>();
		description.forEach((element) -> box.addItem(element));
		box.setSelectedItem("prob. pioggia");
		lim.setPosition(1, y);
		activeComponents.addComboBox(name, box);
		this.add(box,lim);
	}
	
	
	//Bottone di servizio, per disattivare gli altri
	private void addCancelButton(){
		JRadioButton button = new JRadioButton();
		activeComponents.addRadioButton("cancel", button);
		group.add(button);
	}
	
}
