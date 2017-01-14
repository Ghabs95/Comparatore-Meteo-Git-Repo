package gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class DisplayContainer extends Container {
	private MyConstraints lim;
	//Componenti attive:
	public JTextArea displayBox;
	
	
	public DisplayContainer(){
		initContainer();
		addDisplay();
	}

	private void initContainer() {
		this.setLayout(new GridBagLayout());
		lim = new MyConstraints();
	}
	
	private void addDisplay() {
		displayBox = new JTextArea(20,30);
		displayBox.setEditable(false);
		JScrollPane sp = new JScrollPane(displayBox);
		lim.setPosition(0,0);
		lim.setInsets(10,10,10,10);
		lim.setFillAndAnchor(GridBagConstraints.BOTH,GridBagConstraints.CENTER);
		lim.setGridCellDimension(1,1);
		this.add(sp, lim);
	}
}
