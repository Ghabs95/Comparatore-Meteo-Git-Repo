package gui.listeners;

import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import core.site.Lamma;
import core.site.MeteoAM;
import core.site.Site;
import core.site.ThreeB;
import gui.utilities.ActiveComponentsManager;

public class LocationAvailability implements CaretListener {
	private Lamma lamma;
	private MeteoAM am;
	private ThreeB threeB;
	private JTextField searchBox;
	private JCheckBox lammaCheck;
	private JCheckBox amCheck;
	private JCheckBox threeBCheck;
	
	public LocationAvailability(ActiveComponentsManager acm) {
		super();
		lamma = new Lamma(""); //url fake, non mi serve
		am = new MeteoAM("");
		threeB = new ThreeB("");
		searchBox = acm.getTextField("searchBox");
		lammaCheck = acm.getCheckBox("checkLamma");
		amCheck = acm.getCheckBox("checkAM");
		threeBCheck = acm.getCheckBox("check3B");
	}

	
	@Override
	public void caretUpdate(CaretEvent ce) {
		String loc = searchBox.getText();
		displayAvailability(lamma, lammaCheck, loc);
		displayAvailability(am, amCheck, loc);
		displayAvailability(threeB, threeBCheck, loc);
	}
	
	private void displayAvailability(Site meteo,JCheckBox meteoCheck, String location){
		if(meteo.isAvailable(location)){
			meteoCheck.setBackground(Color.GREEN);
			meteoCheck.setEnabled(true);
		}else{
			meteoCheck.setEnabled(false);
			meteoCheck.setSelected(false);
			meteoCheck.setBackground(Color.RED);
		}
	}

}
