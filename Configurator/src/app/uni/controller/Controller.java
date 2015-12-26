package app.uni.controller;

import app.uni.model.CommandList;
import app.uni.model.Settings;
import app.uni.model.Command;
import app.uni.view.CommandDialog;
import app.uni.view.SettingsDialog;

import java.awt.event.*;
import java.io.FileWriter;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Controller {
	public CommandList listData;
	public ActionListener exitAction;
	public ActionListener addListener;
	public ActionListener removeListener;
	public ActionListener editListener;
	public ActionListener settingsListener;
	public ActionListener exportListener;

	private int selectedItemIndex = -1;
	private Settings settings;

	public void setSelectedTableRow(int index) {
		selectedItemIndex = index;
	}
	public void moveSelectedTableRowUp() {
		if (selectedItemIndex > 0) {
			listData.getModel().moveRow(selectedItemIndex, selectedItemIndex, selectedItemIndex-1);
		}
	}
	public void moveSelectedTableRowDown() {
		if (selectedItemIndex < listData.getModel().getRowCount()-1) {
			listData.getModel().moveRow(selectedItemIndex, selectedItemIndex, selectedItemIndex+1);
		}
	}

	public ResourceBundle getResourceBundle() {
		ResourceBundle result = null;
		try {
			result = ResourceBundle.getBundle("app.uni.resources.labels", settings.getLocale());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void exportJSON() {
		try {
			String fullpath = settings.getPath() + "/" + settings.getFileName() + "." + settings.getAvailableFormats()[settings.getFormat()];
			System.out.println(fullpath);
			FileWriter file = new FileWriter(fullpath);
			file.write("[\n");
			for (int i=0; i<listData.getModel().getRowCount(); i++) {
				Command current = listData.getCommand(i);
				if (i>0) {
					file.write("\t,\n");
				}
				file.write("\t{\n");
				file.write("\t\t\"name\": \""+ current.name + "\",\n");
				file.write("\t\t\"alias\": \""+ current.alias + "\",\n");
				file.write("\t\t\"command\": \""+ current.command + "\",\n");
				file.write("\t\t\"args\": \""+ current.args + "\",\n");
				file.write("\t\t\"prompt\": \""+ current.prompt + "\"\n");
				file.write("\t}\n");
			}
			file.write("]");
			file.close();

			JOptionPane.showMessageDialog((JFrame)null, "Export successfully done on file: " + fullpath);
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog((JFrame)null, "Export failed! (" + ex.getMessage() + ")", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void exportTXT() {
		try {
			String fullpath = settings.getPath() + "/" + settings.getFileName() + "." + settings.getAvailableFormats()[settings.getFormat()];
			System.out.println(fullpath);
			FileWriter file = new FileWriter(fullpath);
			for (int i=0; i<listData.getModel().getRowCount(); i++) {
				if (i>0) {
					file.write("\n");
				}
				Command current = listData.getCommand(i);
				file.write("name: "+ current.name + "\n");
				file.write("alias: "+ current.alias + "\n");
				file.write("command: "+ current.command + "\n");
				file.write("args: "+ current.args + "\n");
				file.write("prompt: "+ current.prompt + "\n");
			}
			file.close();

			JOptionPane.showMessageDialog((JFrame)null, "Export successfully done on file: " + fullpath);
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog((JFrame)null, "Export failed! (" + ex.getMessage() + ")", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public Controller() {
		settings = new Settings();
		listData = new CommandList();
		listData.addCommand(new Command("lamp", "Toggle Lamp", "lamp.sh", "15", true, 1));
		listData.addCommand(new Command("weather", "Get weather", "weather.sh", "", false, 0));

		exitAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			};
		};
		addListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CommandDialog dialog = new CommandDialog("Command List entry", settings);
				Command res = dialog.Show();

				if (res != null) {
					listData.addCommand(res);
					listData.getModel().fireTableDataChanged();
				};
			};
		};
		removeListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listData.removeCommand(selectedItemIndex);
				listData.getModel().fireTableDataChanged();
			};
		};
		settingsListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SettingsDialog dialog = new SettingsDialog(settings);
				dialog.Show();
			}
		};
		editListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CommandDialog dialog = new CommandDialog("Modify Command", settings, listData.getCommand(selectedItemIndex));
				Command res = dialog.Show();

				if (res != null) {
					listData.changeCommand(selectedItemIndex, res);
					listData.getModel().fireTableDataChanged();
				};
			};
		};
		exportListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog((JFrame)null, "Are you sure you want to export current settings?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
					new Thread(new Runnable() {
						public void run() {
							if (settings.getFormat() == 0) {
								exportJSON();
							}
							if (settings.getFormat() == 2) {
								exportTXT();
							}
						}
					}).start();
				}
			}
		};
	};
};
