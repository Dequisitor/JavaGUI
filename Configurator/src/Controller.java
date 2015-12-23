import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Controller {
	public CommandList listData;
	public ActionListener exitAction;
	public ActionListener addListener;
	public ActionListener removeListener;
	public ListSelectionListener selectionListener;

	private int selectedItemIndex = -1;

	public Controller() {
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
				CommandDialog dialog = new CommandDialog(null, "Command List entry");
				dialog.setModal(true);
				Command res = dialog.Show();
				System.out.println(res);

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
		selectionListener = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selectedItemIndex = e.getFirstIndex();
				System.out.println(selectedItemIndex);
			};
		};

	};

};
