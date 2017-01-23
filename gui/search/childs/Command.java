package gui.search.childs;

import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

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
		addOperation("customWeather", "Meteo",      1);
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
	
	//Bottone di servizio, per disattivare gli altri
	private void addCancelButton(){
		JRadioButton button = new JRadioButton();
		activeComponents.addRadioButton("cancel", button);
		group.add(button);
	}
	
}
