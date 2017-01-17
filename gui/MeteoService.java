package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class MeteoService extends GridBagContainer {

	@Override
	public void addComponents() {
		addTitleLabel();
		addCheckBoxes();
		addMeteoLabels();
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
	
	private void addCheckBoxes(){
		//imposto le limitazioni
		lim.setInsets(5,0,5,0);
		lim.setFillAndAnchor(GridBagConstraints.NONE, GridBagConstraints.WEST);
		lim.setGridCellDimension(1,1);
		//aggiungo i box
		addBox("checkLamma", 2);
		addBox("checkAM",    3);
		addBox("check3B",    4);
	}
	
	private void addBox(String name,int y){
		JCheckBox box = new JCheckBox();
		lim.setPosition(0, y);
		activeComponents.addCheckBox(name, box);
		this.add(box,lim);
	}
	
	private void addMeteoLabels() {	
		lim.setFillAndAnchor(GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
		lim.setGridCellDimension(1,1);
		addMLabel("Lamma",    2);
		addMLabel("Meteo AM", 3);
		addMLabel("3B Meteo", 4);
	}
	
	private void addMLabel(String name, int y){
		JLabel ml = new JLabel(name);
		ml.setFont(new Font("TimesRoman",Font.PLAIN,14));
		lim.setPosition(1,y);
		this.add(ml,lim);
	}

}
