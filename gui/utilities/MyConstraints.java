package gui.utilities;

import java.awt.GridBagConstraints;

@SuppressWarnings("serial")
public class MyConstraints extends GridBagConstraints {

	public void setPosition(int gridx, int gridy){
		this.gridx = gridx;
		this.gridy = gridy;
	}
	
	public void setGridCellDimension(int gridwidth, int gridheight){
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
	}
	
	public void setFillAndAnchor(int fill, int anchor){
		this.fill = fill;
		this.anchor = anchor;
	}
	
	public void setInsets(int top, int bottom, int left, int right){
		this.insets.top = top;
		this.insets.bottom = bottom;
		this.insets.left = left;
		this.insets.right = right;
	}
	
	//TODO #choose: inserire metodi per constraints di default? (tipo se le label hanno tutte le stesse constraints...)
}
