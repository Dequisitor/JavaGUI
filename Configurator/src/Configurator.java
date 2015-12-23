import javax.swing.*;

public class Configurator implements Runnable {

	public void run() {
		Mainview view = new Mainview();
		view.show();
	}

	public static void main(String[] args) {
		Configurator main = new Configurator();
		SwingUtilities.invokeLater(main);
	}

}
