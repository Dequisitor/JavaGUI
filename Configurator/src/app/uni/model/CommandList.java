package app.uni.model;

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

	public Command getCommand(int index) {
		Command res = new Command();
		res.name = (String)commandList.getValueAt(index, 0);
		res.alias = (String)commandList.getValueAt(index, 1);
		res.command = (String)commandList.getValueAt(index, 2);
		res.args = (String)commandList.getValueAt(index, 3);
		res.result = (int)commandList.getValueAt(index, 4);
		res.prompt = (boolean)commandList.getValueAt(index, 5);

		return res;
	};

	public void changeCommand(int index, Command param) {
		commandList.setValueAt(param.name, index, 0);
		commandList.setValueAt(param.alias, index, 1);
		commandList.setValueAt(param.command, index, 2);
		commandList.setValueAt(param.args, index, 3);
		commandList.setValueAt(param.result, index, 4);
		commandList.setValueAt(param.prompt, index, 5);
	};

	public void removeCommand(int index) {
		commandList.removeRow(index);
	};

	public String[] getColumns() {
		String columns[] = {"Name", "Alias", "Command", "Arguments", "Return Type", "Prompt"};
		return columns;
	};

	public DefaultTableModel getModel() {
		return commandList;
	};
};
