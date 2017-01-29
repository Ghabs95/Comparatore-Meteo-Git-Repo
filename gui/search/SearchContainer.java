package gui.search;

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
		addChild(new Preface(), 0);
		addChild(new LocationChoice(), 1);
		addChild(new MeteoService(), 2);
		addChild(new TimeChoice(), 3);
		addChild(new Command(), 4);
	}

	public void setupConstraints() {
		lim.setupDefaultCheckBoxConstraints();
		lim.setInsets(0, 0, 0, 0);
	}

	private void addChild(GridBagContainer child, int y) {
		lim.setPosition(0, y);
		getChildActiveComponents(child.getActiveComponents());
		this.add(child, lim);
	}

}
