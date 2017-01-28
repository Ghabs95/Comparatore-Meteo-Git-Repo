package gui.tests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Point;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

import gui.ContentPane;
import gui.ForecastComparatorMain;

public class ForecastComparatorMainTest {
	JFrame cm = new ForecastComparatorMain();
	Point pointLocation;

	@Before
	public void init() {
		pointLocation = new Point(280, 10);
	}

	@Test
	public void initFrameTest() {
		String title = cm.getTitle();
		Point location = cm.getLocation();
		boolean resizable = cm.isResizable();
		assertEquals(title, "Comparatore Meteo - v2.0");
		assertEquals(location, location);
		assertFalse(resizable);
	}

	@Test
	public void constructorForecastComparatorMainTest() {
		Color background = cm.getBackground();
		Container contentPane = cm.getContentPane();
		assertEquals(background, new Color(240, 220, 130));
		assertTrue(contentPane instanceof ContentPane);
	}
}
