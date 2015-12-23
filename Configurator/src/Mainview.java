import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.*;
import javax.swing.border.Border;

public class Mainview {
	private JFrame frame;

	public Mainview() {
		frame = new JFrame();
		Container content = frame.getContentPane();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JList list = new JList();
		JButton add = new JButton("Add");
		JButton edit = new JButton("Modify");
		JButton remove = new JButton("Remove");
		JButton up = new JButton("Up");
		JButton down = new JButton("Down");
		JButton settings = new JButton("Settings");
		JButton export = new JButton("Export");
		JPanel left = new JPanel();
		JPanel right = new JPanel();
		JPanel bottom = new JPanel();
		JPanel center = new JPanel();

		//list.setSize(200, 200);
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		left.add(up, BorderLayout.NORTH);
		left.add(down, BorderLayout.SOUTH);
		center.add(list);
		bottom.add(settings);
		bottom.add(export);
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		right.add(add);
		right.add(edit);
		right.add(remove);
		content.add(left, BorderLayout.WEST);
		content.add(right, BorderLayout.EAST);
		content.add(center, BorderLayout.CENTER);
		content.add(bottom, BorderLayout.SOUTH);

		JMenuBar menubar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu about = new JMenu("About");
		JMenuItem fopen = new JMenuItem("Open");
		JMenuItem fexport = new JMenuItem("Export");
		JMenuItem fexit = new JMenuItem("Exit");
		JMenuItem fsettings = new JMenuItem("Settings");
		JMenuItem ahelp = new JMenuItem("Help");
		JMenuItem ainfo = new JMenuItem("Info");

		menubar.add(file);
		menubar.add(about);
		file.add(fopen);
		file.add(fexport);
		file.add(fsettings);
		file.add(fexit);
		about.add(ahelp);
		about.add(ainfo);
		frame.setJMenuBar(menubar);

		frame.setSize(400, 400);
		//frame.pack();
	}

	public void show() {
		frame.setVisible(true);
	}
}
