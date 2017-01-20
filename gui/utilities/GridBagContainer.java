package gui.utilities;

import java.awt.Container;
import java.awt.GridBagLayout;

@SuppressWarnings("serial")
public abstract class GridBagContainer extends Container {
	protected ActiveComponentsManager activeComponents;
	protected MyConstraints lim;
	
	public GridBagContainer(){
		initContainer();
		addComponents();
	}
	
	private void initContainer() {
		this.setLayout(new GridBagLayout());
		lim = new MyConstraints();
		activeComponents = new ActiveComponentsManager();
	}
	
	public abstract void addComponents();
	
	public void getChildActiveComponents(ActiveComponentsManager childComponents){
		activeComponents.updateAll(childComponents);
	}
	
	public ActiveComponentsManager getActiveComponents(){
		return activeComponents;
	}
}
