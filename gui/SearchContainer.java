package gui;

import java.awt.GridBagConstraints;

@SuppressWarnings("serial")
public class SearchContainer extends GridBagContainer {

	@Override
	public void addComponents() {
		setupConstraints();
		addChild(new LocationChoice(), 0);
		//addMeteoService();
		addChild(new MeteoService(), 1);
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
