package gui.utilities;

import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ActiveComponentsManager {
	private HashMap<String,JButton> button;
	private HashMap<String,JRadioButton> radioButton;
	private HashMap<String,JCheckBox> checkBox;
	private HashMap<String,JTextField> textField;
	private HashMap<String,JTextArea> textArea;
	private HashMap<String,JLabel> label;
	
	public ActiveComponentsManager(){
		button = new HashMap<>();
		radioButton = new HashMap<>();
		checkBox = new HashMap<>();
		textField = new HashMap<>();
		textArea = new HashMap<>();
		label = new HashMap<>();
	}
	
	/* ADDERS */
	public void addButton(String name, JButton component){
		button.put(name, component);
	}
	
	public void addRadioButton(String name, JRadioButton component){
		radioButton.put(name, component);
	}
	
	public void addCheckBox(String name, JCheckBox component){
		checkBox.put(name, component);
	}
	
	public void addTextField(String name, JTextField component){
		textField.put(name, component);
	}
	
	public void addTextArea(String name, JTextArea component){
		textArea.put(name, component);
	}
	
	public void addLabel(String name, JLabel component){
		label.put(name, component);
	}
	
	/* UPDATER */
	public void updateAll(ActiveComponentsManager acm){
		button.putAll(acm.button);
		radioButton.putAll(acm.radioButton);
		checkBox.putAll(acm.checkBox);
		textField.putAll(acm.textField);
		textArea.putAll(acm.textArea);
		label.putAll(acm.label);
	}
	
	
	/* GETTERS per ricerca */
	public JButton getButton(String name){
		return button.get(name);
	}
	
	public JCheckBox getCheckBox(String name){
		return checkBox.get(name);
	}
	
	public JRadioButton getRadioButton(String name){
		return radioButton.get(name);
	}
	
	public JTextField getTextField(String name){
		return textField.get(name);
	}
	
	public JTextArea getTextArea(String name){
		return textArea.get(name);
	}
	
	public JLabel getLabel(String name){
		return label.get(name);
	}
	
	 

}
