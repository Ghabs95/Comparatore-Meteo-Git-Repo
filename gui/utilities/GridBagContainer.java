package gui.utilities;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

@SuppressWarnings("serial")
public abstract class GridBagContainer extends Container {
	protected ActiveComponentsManager activeComponents;
	protected MyConstraints lim;

	public GridBagContainer() {
		initContainer();
		addComponents();
	}

	private void initContainer() {
		this.setLayout(new GridBagLayout());
		lim = new MyConstraints();
		activeComponents = new ActiveComponentsManager();
	}

	public abstract void addComponents();

	public void getChildActiveComponents(ActiveComponentsManager childComponents) {
		activeComponents.updateAll(childComponents);
	}

	public ActiveComponentsManager getActiveComponents() {
		return activeComponents;
	}

	public void setupConstraints() {
		// imposto le limitazioni
		lim.setInsets(5, 0, 5, 0);
		lim.setFillAndAnchor(GridBagConstraints.NONE, GridBagConstraints.WEST);
		lim.setGridCellDimension(1, 1);
	}
	
	// for test only
	public MyConstraints getLim() {
		return lim;
	}
}
