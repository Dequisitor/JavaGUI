package app.uni.view;

import app.uni.controller.Controller;
import app.uni.controller.LanguageService;
import app.uni.controller.LanguageService.Observer;

import java.awt.Container;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Mainview {
	private JFrame frame;
	private Controller ctrl;
	private JTable table;

	private JButton add;
	private JButton edit;
	private JButton remove;
	private JButton up;
	private JButton down;
	private JButton settings;
	private JButton export;
	private JMenuBar menubar;
	private JMenu file;
	private JMenu about;
	private JMenuItem fexit;
	private JMenuItem fsettings;
	private JMenuItem ainfo;
	
	public void setLabels() {
		LanguageService l = LanguageService.getInstance();
		add.setText(l.getLabel("add"));
		edit.setText(l.getLabel("edit"));
		remove.setText(l.getLabel("remove"));
		up.setText(l.getLabel("up"));
		down.setText(l.getLabel("down"));
		settings.setText(l.getLabel("settings"));
		export.setText(l.getLabel("export"));
		file.setText(l.getLabel("file"));
		about.setText(l.getLabel("help"));
		fexit.setText(l.getLabel("exit"));
		fsettings.setText(l.getLabel("settings"));
		ainfo.setText(l.getLabel("info"));
		frame.pack();
	};

	public Mainview() {
		//main layout setup
		frame = new JFrame();
		Container content = frame.getContentPane();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GroupLayout layout = new GroupLayout(content);
		frame.setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		ctrl = new Controller();
		table = new JTable(ctrl.listData.getModel());

		//components
		add = new JButton();
		edit = new JButton();
		remove = new JButton();
		up = new JButton();
		down = new JButton();
		settings = new JButton();
		export = new JButton();
		menubar = new JMenuBar();
		file = new JMenu();
		about = new JMenu();
		fexit = new JMenuItem();
		fsettings = new JMenuItem();
		ainfo = new JMenuItem();

		//component setup
		up.setEnabled(false);
		down.setEnabled(false);
		remove.setEnabled(false);
		edit.setEnabled(false);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//layout setup
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(up)
					.addComponent(down))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(table, 600, 600, 1000)
					.addGroup(layout.createSequentialGroup()
						.addComponent(export)
						.addComponent(settings)))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(add)
					.addComponent(edit)
					.addComponent(remove)));
		layout.linkSize(SwingConstants.HORIZONTAL, up, down);
		layout.linkSize(SwingConstants.HORIZONTAL, add, edit, remove);
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addComponent(up)
					.addComponent(down))
				.addGroup(layout.createSequentialGroup()
					.addComponent(table, 200, 200, 500)
					.addGroup(layout.createParallelGroup()
						.addComponent(export)
						.addComponent(settings)))
				.addGroup(layout.createSequentialGroup()
					.addComponent(add)
					.addComponent(edit)
					.addComponent(remove)));

		//menu setup
		menubar.add(file);
		menubar.add(about);
		file.add(fsettings);
		file.add(fexit);
		about.add(ainfo);
		frame.setJMenuBar(menubar);

		//listeners
		fexit.addActionListener(ctrl.exitAction);
		add.addActionListener(ctrl.addListener);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				ctrl.setSelectedTableRow(table.getSelectedRow());
				remove.setEnabled(true);
				edit.setEnabled(true);
				up.setEnabled(true);
				down.setEnabled(true);
			}
		});
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove.setEnabled(false);
				edit.setEnabled(false);
				up.setEnabled(false);
				down.setEnabled(false);
			};
		});
		remove.addActionListener(ctrl.removeListener);
		edit.addActionListener(ctrl.editListener);
		
		//add listeners to continue selection
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.moveSelectedTableRowUp();
				int index = table.getSelectedRow() -1;
				if (index >= 0) {
					ctrl.setSelectedTableRow(index);
					table.setRowSelectionInterval(index, index);
				}
			}
		});
		down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.moveSelectedTableRowDown();
				int index = table.getSelectedRow() +1;
				if (index < table.getRowCount()) {
					ctrl.setSelectedTableRow(index);
					table.setRowSelectionInterval(index, index);
				}
			}
		});
		export.addActionListener(ctrl.exportListener);

		//settings popup
		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setLabels();
			}
		});
		settings.addActionListener(ctrl.settingsListener);
		fsettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setLabels();
			}
		});
		fsettings.addActionListener(ctrl.settingsListener);

		//info dialog
		ainfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog((JFrame)null, "Source code and description can be found\nat http://github.com/Dequisitor/JavaGUI", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		LanguageService.getInstance().addListener(new Observer() {
			public void update() {
				setLabels();
			};
		});
		setLabels();
	}

	public void show() {
		frame.setVisible(true);
	}
}
