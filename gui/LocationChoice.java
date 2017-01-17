package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LocationChoice extends GridBagContainer {

	@Override
	public void addComponents() {
		addSearchLabel();
		addSearchBox();
		addStartButton();
	}
	
	private void addSearchLabel() {
		JLabel sl = new JLabel("Localita'");
		sl.setFont(new Font("TimesRoman",Font.BOLD,14));
		lim.setPosition(0,0);
		lim.setInsets(10,0,0,10);
		lim.setFillAndAnchor(GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
		lim.setGridCellDimension(1,1);
		this.add(sl, lim);
	}
	
	private void addSearchBox() {
		JTextField searchBox = new JTextField(10);
		lim.setPosition(0,1);
		lim.setInsets(0,0,0,10);
		lim.setFillAndAnchor(GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
		lim.setGridCellDimension(1,1);
		activeComponents.addTextField("searchBox", searchBox);
		this.add(searchBox, lim);
	}	
	
	private void addStartButton(){
		JButton searchButton = new JButton("Search!");
		lim.setPosition(1, 1);
		activeComponents.addButton("search",searchButton);
		this.add(searchButton, lim);
	}
	
	
	

}
