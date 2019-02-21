package gui.search.childs;

import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

import gui.ForecastComparatorMain;
import gui.utilities.GridBagContainer;

@SuppressWarnings("serial")
public class TimeChoice extends GridBagContainer {

	@Override
	public void addComponents() {
		addTitleLabel();
		setupConstraints();
		// aggiungo i giorni
		addBox("checkOggi", "Oggi", 0, 2);
		addBox("checkDomani", "Domani", 0, 3);
		addBox("checkDopodomani", "Dopodomani", 0, 4);
		// aggiungo gli orari
		addBox("checkMattina", "Mattina", 1, 2);
		addBox("checkPomeriggio", "Pomeriggio", 1, 3);
		addBox("checkSera", "Sera", 1, 4);

	}

	private void addTitleLabel() {
		JLabel sl = new JLabel("Quando");
		sl.setFont(new Font("TimesRoman", Font.BOLD, 14));
		lim.setPosition(0, 0);
		lim.setInsets(10, 0, 10, 10);
		lim.setFillAndAnchor(GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
		lim.setGridCellDimension(3, 1);
		this.add(sl, lim);
	}

	public void setupConstraints() {
		lim.setupDefaultLeftAlignmentConstraints();
	}

	private void addBox(String name, String description, int x, int y) {
		JCheckBox box = new JCheckBox(description);
		box.setBackground(ForecastComparatorMain.BACKGROUND);
		lim.setPosition(x, y);
		activeComponents.addCheckBox(name, box);
		this.add(box, lim);
	}

}
