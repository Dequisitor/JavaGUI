import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Mainview {
	private JFrame frame;

	public Mainview() {
		frame = new JFrame();
		Container content = frame.getContentPane();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		Controller ctrl = new Controller();
		JTable table = new JTable(ctrl.listData.getModel());

		JButton add = new JButton("Add");
		JButton edit = new JButton("Modify");
		final JButton remove = new JButton("Remove");
		JButton up = new JButton("Up");
		JButton down = new JButton("Down");
		JButton settings = new JButton("Settings");
		JButton export = new JButton("Export");
		JPanel left = new JPanel();
		JPanel right = new JPanel();
		JPanel bottom = new JPanel();
		JPanel center = new JPanel();

		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		left.add(up, BorderLayout.NORTH);
		left.add(down, BorderLayout.SOUTH);
		center.add(new JScrollPane(table));
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

		frame.pack();
		
		remove.setEnabled(false);
		table.setShowVerticalLines(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		fexit.addActionListener(ctrl.exitAction);
		add.addActionListener(ctrl.addListener);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				remove.setEnabled(true);
				System.out.println("remove enabled");
			};
		});
		table.getSelectionModel().addListSelectionListener(ctrl.selectionListener);
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove.setEnabled(false);
				System.out.println("remove disabled");
			};
		});
		remove.addActionListener(ctrl.removeListener);
	}

	public void show() {
		frame.setVisible(true);
	}
}
