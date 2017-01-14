package gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SearchContainer extends Container {
	private MyConstraints lim;
	//Componenti attive:
	public JTextField searchBox;
	public JButton startButton;

	public SearchContainer(){
		initContainer();
		addSearchLabel();
		addSearchBox();
		addStartButton();
	}
	
	private void initContainer() {
		this.setLayout(new GridBagLayout());
		lim = new MyConstraints();
	}

	private void addSearchLabel() {
		JLabel sl = new JLabel("Localita: ");
		sl.setFont(new Font("TimesRoman",Font.PLAIN,14));
		lim.setPosition(0,0);
		lim.setInsets(10,0,10,10);
		lim.setFillAndAnchor(GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTHEAST);
		lim.setGridCellDimension(1,1);
		this.add(sl, lim);
	}
	
	private void addSearchBox() {
		searchBox = new JTextField(10);
		lim.setPosition(1,0);
		lim.setInsets(10,10,10,10);
		lim.setFillAndAnchor(GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTH);
		lim.setGridCellDimension(1,1);
		this.add(searchBox, lim);
	}
	
	private void addStartButton(){
		startButton = new JButton("Search!");
		lim.setPosition(1, 1);
		this.add(startButton, lim);
	}
	
}
