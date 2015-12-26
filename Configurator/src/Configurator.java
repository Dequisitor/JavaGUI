package app.uni.configurator;
import javax.swing.*;

public class Configurator implements Runnable {

	public void run() {
		Mainview view = new Mainview();
		view.show();
	}

	public static void main(String[] args) {
		System.setProperty("file.encoding", "UTF-8");
		try {
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Configurator main = new Configurator();
		SwingUtilities.invokeLater(main);
	}
}
