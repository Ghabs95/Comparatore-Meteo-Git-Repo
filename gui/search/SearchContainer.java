package gui.search;

import java.awt.GridBagConstraints;

import gui.search.childs.Command;
import gui.search.childs.LocationChoice;
import gui.search.childs.MeteoService;
import gui.search.childs.Preface;
import gui.search.childs.TimeChoice;
import gui.utilities.GridBagContainer;

@SuppressWarnings("serial")
public class SearchContainer extends GridBagContainer {

	@Override
	public void addComponents() {
		setupConstraints();
		addChild(new Preface(), 		0);
		addChild(new LocationChoice(), 	1);
		addChild(new MeteoService(),	2);
		addChild(new TimeChoice(),		3);
		addChild(new Command(), 		4);
	}
	
	private void setupConstraints(){
		lim.setInsets(0,0,0,0);
		lim.setFillAndAnchor(GridBagConstraints.NONE, GridBagConstraints.WEST);
		lim.setGridCellDimension(1,1);
	}
	
	private void addChild(GridBagContainer child, int y){
		lim.setPosition(0, y);
		getChildActiveComponents(child.getActiveComponents());
		this.add(child,lim);
	}
		
}
