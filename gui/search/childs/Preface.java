package gui.search.childs;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import core.internet.WebHandler;
import gui.utilities.GridBagContainer;

@SuppressWarnings("serial")
public class Preface extends GridBagContainer {

	@Override
	public void addComponents() {
		addTitleLabel();
		addImageLabel();
	}

	private void addTitleLabel() {
		JLabel titleL = new JLabel("Comparatore Meteo");
		titleL.setFont(new Font("TimesRoman", Font.BOLD, 20));
		lim.setPosition(0, 0);
		lim.setInsets(0, 0, 0, 0);
		lim.setFillAndAnchor(GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
		lim.setGridCellDimension(1, 1);
		this.add(titleL, lim);
	}

	private void addImageLabel() {
		JLabel image = new JLabel(new ImageIcon(getMeteoSatImage()));
		lim.setPosition(0, 1);
		lim.setInsets(5, 5, 5, 5);
		lim.setFillAndAnchor(GridBagConstraints.BOTH, GridBagConstraints.CENTER);
		lim.setGridCellDimension(1, 1);
		activeComponents.addLabel("image", image);
		this.add(image, lim);
	}
	
	private BufferedImage getMeteoSatImage(){
		String path = "http://img2.meteo.it/forecastimg/realtime/europa/eu_sat_07.jpg";
		BufferedImage image = WebHandler.getInstance().getPhoto(path); 
		return resize(image,250,150);
	}

	// Trovato su internet!
	private BufferedImage resize(BufferedImage image, int width, int height) {
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D) bi.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(image, 0, 0, width, height, null);
		g2d.dispose();
		return bi;
	}
}
