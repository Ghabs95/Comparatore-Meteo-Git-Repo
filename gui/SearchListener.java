package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import core.InformationManager;

public class SearchListener implements ActionListener {
	private JButton button;
	private JTextField box;
	private InformationManager info;
	private JTextArea display;
	
	public SearchListener(JButton listened, JTextField searchBox, JTextArea display){
		button = listened;
		box = searchBox;
		this.display = display;
		info = new InformationManager();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		button.setEnabled(false);
		box.setEditable(false);
		String location = box.getText();
		info.gatherForecastLocation(InformationManager.LAMMA, location);
		display.setText(info.getPrintableForecast(InformationManager.LAMMA, location, InformationManager.DOMANI));
		box.setEditable(true);
		button.setEnabled(true);
	}

}
