package gui.tests;

import static org.junit.Assert.*;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;

import gui.utilities.ActiveComponentsManager;

public class ActiveComponentsManagerTest {
	ActiveComponentsManager activeComponents = new ActiveComponentsManager();

	@Before
	public void init() {
		activeComponents.addCheckBox("check", new JCheckBox());
		activeComponents.addComboBox("combo", new JComboBox<>());
		activeComponents.addLabel("label", new JLabel("label"));
		activeComponents.addRadioButton("radio", new JRadioButton("radio"));
		activeComponents.addTextArea("textArea", new JTextArea());
		activeComponents.addTextField("textField", new JTextField());
	}
	
	@Test
	public void adderTest() {
		assertTrue(activeComponents.getCheckBox("check") instanceof JCheckBox);
		assertTrue(activeComponents.getComboBox("combo") instanceof JComboBox);
		assertTrue(activeComponents.getLabel("label") instanceof JLabel);
		assertTrue(activeComponents.getRadioButton("radio") instanceof JRadioButton);
		assertTrue(activeComponents.getTextArea("textArea") instanceof JTextArea);
		assertTrue(activeComponents.getTextField("textField") instanceof JTextField);
	}
	
	@Test
	public void updateAllTest() {
		ActiveComponentsManager acm = new ActiveComponentsManager();
		acm.addCheckBox("check2", new JCheckBox());
		acm.addComboBox("combo2", new JComboBox<>());
		activeComponents.updateAll(acm);
		assertTrue(activeComponents.getCheckBox("check2") instanceof JCheckBox);
		assertTrue(activeComponents.getComboBox("combo2") instanceof JComboBox);
	}

}
