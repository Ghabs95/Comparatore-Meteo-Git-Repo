package gui.utilities;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ActiveComponentsManager {
	private Map<String, JComboBox<String>> comboBox;
	private Map<String, JRadioButton> radioButton;
	private Map<String, JCheckBox> checkBox;
	private Map<String, JTextField> textField;
	private Map<String, JTextArea> textArea;
	private Map<String, JLabel> label;

	public ActiveComponentsManager() {
		comboBox = new HashMap<>();
		radioButton = new HashMap<>();
		checkBox = new HashMap<>();
		textField = new HashMap<>();
		textArea = new HashMap<>();
		label = new HashMap<>();
	}

	/* ADDERS */
	public void addComboBox(String name, JComboBox<String> component) {
		comboBox.put(name, component);
	}

	public void addRadioButton(String name, JRadioButton component) {
		radioButton.put(name, component);
	}

	public void addCheckBox(String name, JCheckBox component) {
		checkBox.put(name, component);
	}

	public void addTextField(String name, JTextField component) {
		textField.put(name, component);
	}

	public void addTextArea(String name, JTextArea component) {
		textArea.put(name, component);
	}

	public void addLabel(String name, JLabel component) {
		label.put(name, component);
	}

	/* UPDATER */
	public void updateAll(ActiveComponentsManager acm) {
		comboBox.putAll(acm.comboBox);
		radioButton.putAll(acm.radioButton);
		checkBox.putAll(acm.checkBox);
		textField.putAll(acm.textField);
		textArea.putAll(acm.textArea);
		label.putAll(acm.label);
	}

	/* GETTERS per ricerca */
	public JComboBox<String> getComboBox(String name) {
		return comboBox.get(name);
	}

	public JCheckBox getCheckBox(String name) {
		return checkBox.get(name);
	}

	public JRadioButton getRadioButton(String name) {
		return radioButton.get(name);
	}

	public JTextField getTextField(String name) {
		return textField.get(name);
	}

	public JTextArea getTextArea(String name) {
		return textArea.get(name);
	}

	public JLabel getLabel(String name) {
		return label.get(name);
	}

}
