package gui;

import java.awt.Color;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ForecastComparatorMain extends JFrame {
	public final static Color BACKGROUND = new Color(240, 220, 130);

	public ForecastComparatorMain() {
		initFrame();
		setContentPane(new ContentPane());
		setBackground(BACKGROUND);
	}

	private void initFrame() {
		setTitle("Comparatore Meteo - v2.0");
		setLocation(280, 10);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		ForecastComparatorMain cm = new ForecastComparatorMain();
		cm.pack();
		cm.setVisible(true);
	}

}
