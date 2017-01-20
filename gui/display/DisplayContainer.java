package gui.display;

import java.awt.GridBagConstraints;

import gui.display.childs.Display;
import gui.utilities.GridBagContainer;

@SuppressWarnings("serial")
public class DisplayContainer extends GridBagContainer {
		
	@Override
	public void addComponents() {
		setupConstraints();
		addChild(new Display(),0);
	}
	
	private void setupConstraints(){
		lim.setInsets(0,0,0,0);
		lim.setFillAndAnchor(GridBagConstraints.NONE, GridBagConstraints.EAST);
		lim.setGridCellDimension(1,1);
	}
	
	private void addChild(GridBagContainer child, int y){
		lim.setPosition(0, y);
		getChildActiveComponents(child.getActiveComponents());
		this.add(child,lim);
	}
	
	

	
}
