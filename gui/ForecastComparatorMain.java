package gui;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;
import static java.lang.Math.toIntExact;

import java.awt.Color;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ForecastComparatorMain extends JFrame {
	// Costanti Grafiche
	private final static double GOLDEN_RATIO = (1 + sqrt(5)) / 2;
	public final static int WIDTH = 790;
	public final static int HEIGHT = getGoldenRatio(WIDTH);
	private final static Color BACKGROUND = new Color(240, 220, 130);

	public ForecastComparatorMain() {
		initFrame();
		setContentPane(new ContentPane());
		setBackground(BACKGROUND);
	}

	private void initFrame() {
		setTitle("Comparatore Meteo - v1.0");
		setLocation(280, 100);
		setSize(WIDTH, HEIGHT);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private static int getGoldenRatio(int longerSide) {
		return toIntExact(round(longerSide / GOLDEN_RATIO));
	}

	public static void main(String[] args) {
		ForecastComparatorMain cm = new ForecastComparatorMain();
		// cm.pack();
		cm.setVisible(true);
	}

}
