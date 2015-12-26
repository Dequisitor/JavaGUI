package app.uni.model;

import javax.swing.table.DefaultTableModel;

public class TableModel extends DefaultTableModel {
	public TableModel(String[] columns, int rows) {
		super(columns, rows);
	};

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	};
};
