package gui.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import gui.display.childs.Display;
import gui.listeners.AbstractWeatherListener;
import gui.listeners.CompareElementListener;
import gui.utilities.ActiveComponentsManager;

public class AbstractWeatherListenerTest {
	AbstractWeatherListener listener;

	@Before
	public void init() {
		ActiveComponentsManager activeComponents = new Display().getActiveComponents();
		listener = new CompareElementListener(activeComponents);
	}

	@Test
	public void getDayTest() {
		assertEquals("OGGI", listener.getDay(0));
		assertEquals("DOMANI", listener.getDay(1));
		assertEquals("DOPODOMANI", listener.getDay(2));
		assertEquals("", listener.getDay(3));
	}
}
