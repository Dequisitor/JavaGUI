package app.uni;

import app.uni.view.Mainview;

import javax.swing.*;

public class Configurator implements Runnable {

	public void run() {
		Mainview view = new Mainview();
		view.show();
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Configurator main = new Configurator();
		SwingUtilities.invokeLater(main);
	}
}
