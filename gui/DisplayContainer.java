package gui;

import java.awt.GridBagConstraints;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class DisplayContainer extends GridBagContainer {
		
	@Override
	public void addComponents() {
		setupConstraints();
		addDisplay();
	}
	
	private void setupConstraints(){
		lim.setInsets(0,0,0,0);
		lim.setFillAndAnchor(GridBagConstraints.NONE, GridBagConstraints.EAST);
		lim.setGridCellDimension(1,1);
	}
	
	private void addDisplay() {
		JTextArea displayBox = new JTextArea(20,30);
		displayBox.setEditable(false);
		JScrollPane sp = new JScrollPane(displayBox);
		lim.setPosition(0,0);
		lim.setInsets(10,10,10,10);
		lim.setFillAndAnchor(GridBagConstraints.BOTH,GridBagConstraints.CENTER);
		lim.setGridCellDimension(1,1);
		activeComponents.addTextArea("display", displayBox);
		this.add(sp, lim);
	}

	
}
