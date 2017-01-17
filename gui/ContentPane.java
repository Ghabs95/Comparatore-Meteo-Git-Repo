package gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ContentPane extends Container {
	private final static int CENTER = GridBagConstraints.CENTER;
	private final static int HORIZONTAL = GridBagConstraints.HORIZONTAL;
	private final static int BOTH = GridBagConstraints.BOTH;
	private final static int NORTH = GridBagConstraints.NORTH;
	//Variabili parte grafica
	private MyConstraints lim;
	private SearchContainer searchC;
	private DisplayContainer displayC;
	//Ascoltatori:
	private SearchListener sl;
	
	
	public ContentPane() {
		initContentPane();
		addTitle("Comparatore Meteo");
		addSearch();
		addDisplay();
		addSearchListener();
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
	
	private void addSearch(){
		searchC = new SearchContainer();
		lim.setPosition(0,1);
		lim.setFillAndAnchor(HORIZONTAL,NORTH);
		lim.setGridCellDimension(1,1);
		this.add(searchC,lim);
	}
	
	private void addDisplay(){
		displayC = new DisplayContainer();
		lim.setPosition(2,1);
		lim.setInsets(10,10,10,10);
		lim.setFillAndAnchor(BOTH, CENTER);
		lim.setGridCellDimension(1,1);
		this.add(displayC, lim);
	}
	
	private void addSearchListener(){
		sl = new SearchListener(searchC.activeComponents.getButton("search"),
								searchC.activeComponents.getTextField("searchBox"),
								displayC.activeComponents.getTextArea("display"));
		
		searchC.activeComponents.getButton("search").addActionListener(sl);
	}
	
}
