package gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ContentPane extends Container {
	private final static int CENTER = GridBagConstraints.CENTER;
	private final static int HORIZONTAL = GridBagConstraints.HORIZONTAL;
	private final static int BOTH = GridBagConstraints.BOTH;
	private final static int EAST = GridBagConstraints.EAST;
	//Variabili parte grafica
	private MyConstraints lim;
	
	
	public ContentPane() {
		initContentPane();
		addTitle("Comparatore Meteo");
		addSearchPanel();
		addDisplayPanel();
	}
	
	private void initContentPane(){
		lim = new MyConstraints(); 		
		this.setLayout(new GridBagLayout());
	}
			
	private void addTitle(String title){
		JLabel titleP = new JLabel(title);
		lim.setPosition(0,0);
		lim.setInsets(10,10,10,10);
		lim.setFillAndAnchor(HORIZONTAL, CENTER);
		lim.setGridCellDimension(3,1);
		this.add(titleP, lim);
	}
	
	private void addSearchPanel(){
		JLabel sl = new JLabel("Localita: ");
		sl.setFont(new Font("TimesRoman",Font.PLAIN,14));
		lim.setPosition(0,1);
		lim.setInsets(10,0,10,10);
		lim.setFillAndAnchor(HORIZONTAL, GridBagConstraints.NORTHEAST);
		lim.setGridCellDimension(1,1);
		this.add(sl, lim);
		
		JTextField searchBox = new JTextField(10);
		lim.setPosition(1,1);
		lim.setInsets(10,10,10,10);
		lim.setFillAndAnchor(HORIZONTAL, GridBagConstraints.NORTH);
		lim.setGridCellDimension(1,1);
		this.add(searchBox, lim);
	}
	
	private void addDisplayPanel(){
		JTextArea displayBox = new JTextArea(20,20);
		lim.setPosition(2,1);
		lim.setInsets(10,10,10,10);
		lim.setFillAndAnchor(BOTH, CENTER);
		lim.setGridCellDimension(1,1);
		this.add(displayBox, lim);
	}
	
}
