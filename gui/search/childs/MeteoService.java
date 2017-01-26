package gui.search.childs;

import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

import gui.ForecastComparatorMain;
import gui.utilities.GridBagContainer;

@SuppressWarnings("serial")
public class MeteoService extends GridBagContainer {

	@Override
	public void addComponents() {
		addTitleLabel();
		addCheckBoxes();
	}
	
	private void addTitleLabel() {
		JLabel sl = new JLabel("Servizio Meteo");
		sl.setFont(new Font("TimesRoman",Font.BOLD,14));
		lim.setPosition(0,0);
		lim.setInsets(10,0,10,10);
		lim.setFillAndAnchor(GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
		lim.setGridCellDimension(2,1);
		this.add(sl, lim);
	}
	
	private void addCheckBoxes(){ // TODO #refactoring: astrarre il metodo o in una classe apposta (vedi TimeChoice)
		//imposto le limitazioni
		lim.setInsets(5,0,5,0);
		lim.setFillAndAnchor(GridBagConstraints.NONE, GridBagConstraints.WEST);
		lim.setGridCellDimension(1,1);
		//aggiungo i box
		addBox("checkLamma","Lamma", 2);
		addBox("checkAM","Meteo AM", 3);
		addBox("check3B","3B Meteo", 4);
	}
	
	private void addBox(String name, String description, int y){
		JCheckBox box = new JCheckBox(description);
		box.setBackground(ForecastComparatorMain.BACKGROUND);
		lim.setPosition(0, y);
		activeComponents.addCheckBox(name, box);
		this.add(box,lim);
	}
	
}
