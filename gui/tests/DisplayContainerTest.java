package gui.tests;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.util.stream.Stream;

import org.junit.Test;

import gui.display.DisplayContainer;
import gui.display.childs.Display;
import gui.utilities.GridBagContainer;

public class DisplayContainerTest {
	GridBagContainer displayContainer = new DisplayContainer();

	@Test
	public void addComponentsTest() {
		Component[] components = displayContainer.getComponents();
		Stream.of(components).forEach(component -> assertTrue(component instanceof Display));
	}

	@Test
	public void setupConstraintsTest() {
		GridBagConstraints lim = displayContainer.getLim();
		assertEquals(5, lim.insets.top);
		assertEquals(0, lim.insets.bottom);
		assertEquals(5, lim.insets.left);
		assertEquals(0, lim.insets.right);
		assertEquals(GridBagConstraints.NONE, lim.fill);
		assertEquals(GridBagConstraints.EAST, lim.anchor);
		assertEquals(1, lim.gridwidth);
		assertEquals(1, lim.gridheight);
	}
}
