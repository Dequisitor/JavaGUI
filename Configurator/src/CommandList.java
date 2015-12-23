import java.util.List;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class CommandList {
	private class TableModel extends DefaultTableModel {
		public TableModel(String[] columns, int rows) {
			super(columns, rows);
		};

		@Override
		public boolean isCellEditable(int row, int col) {
			return false;
		};
	};
	private TableModel commandList; 
	
	public CommandList() {
		commandList = new TableModel(getColumns(), 0);
	};

	public void addCommand(Command param) {
		commandList.addRow(param.toArray());
	};

	public void changeCommand(Command param) {
	};

	public void removeCommand(int index) {
		commandList.removeRow(index);
	};

	public String[] getColumns() {
		String columns[] = {"Name", "Alias", "Command", "Arguments", "Prompt", "Return Type"};
		return columns;
	};

	public DefaultTableModel getModel() {
		return commandList;
	};
};
