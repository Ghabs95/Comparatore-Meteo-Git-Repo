package core.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import core.internet.WebHandler;

public class WebHandlerTest {
	WebHandler instance;
	WebHandler instanceTest;
	
	@Before 
	public void getInstanceSingleton() {
		instance = WebHandler.getInstance();
		instanceTest = WebHandler.getInstance();
	}
	
	@Test
	public void getInstanceTest() {
		assertTrue(instance instanceof WebHandler);
		assertTrue(instanceTest instanceof WebHandler);
		assertTrue(instance == instanceTest);
	}
	
	@Test
	public void getSiteTest() {
		String site = instance.getSite("http://www.lamma.rete.toscana.it/previ/ita/xml/comuni_web/dati/firenze.xml");
		assertTrue(site.contains("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
	}

}
