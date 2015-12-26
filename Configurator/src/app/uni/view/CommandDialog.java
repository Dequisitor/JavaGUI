package app.uni.view;

import app.uni.model.Command;
import app.uni.model.Settings;

import javax.swing.*;
import java.awt.Container;
import java.awt.event.*;
import java.util.ResourceBundle;

public class CommandDialog extends JDialog {
	private JTextField name;
	private JTextField alias;
	private JTextField command;
	private JTextField arguments;
	private JComboBox<String> result;
	private JCheckBox prompt;
	private Command res;
	private Settings settings;
	private JLabel lName;
	private JLabel lAlias;
	private JLabel lCommand;
	private JLabel lArguments;
	private JLabel lResult;
	private JLabel lPrompt;
	private JButton save;
	private JButton cancel;

	public void setLabels() {
		try {
			ResourceBundle labels = ResourceBundle.getBundle("app.uni.resources.labels", settings.getLocale());
			String tmp;
			tmp = labels.getString("name");
			lName.setText(new String(tmp.getBytes("ISO-8859-1"), "UTF-8"));
			tmp = labels.getString("alias");
			lAlias.setText(new String(tmp.getBytes("ISO-8859-1"), "UTF-8"));
			tmp = labels.getString("command");
			lCommand.setText(new String(tmp.getBytes("ISO-8859-1"), "UTF-8"));
			tmp = labels.getString("arguments");
			lArguments.setText(new String(tmp.getBytes("ISO-8859-1"), "UTF-8"));
			tmp = labels.getString("result");
			lResult.setText(new String(tmp.getBytes("ISO-8859-1"), "UTF-8"));
			tmp = labels.getString("prompt");
			lPrompt.setText(new String(tmp.getBytes("ISO-8859-1"), "UTF-8"));
			tmp = labels.getString("save");
			save.setText(new String(tmp.getBytes("ISO-8859-1"), "UTF-8"));
			tmp = labels.getString("cancel");
			cancel.setText(new String(tmp.getBytes("ISO-8859-1"), "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			lName.setText("Name");
			lAlias.setText("Alias");
			lCommand.setText("Command");
			lArguments.setText("Arguments");
			lResult.setText("Response type");
			lPrompt.setText("Confim needed");
			save.setText("Save");
			cancel.setText("Cancel");
		}
		pack();
	}

	public CommandDialog(String title, Settings set, Command com) {
		super((JFrame)null, title);
		setModal(true);

		String options[] = { "HTTP request only", "Execution succes", "Output values" };
		settings = set;

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Container content = getContentPane();
		GroupLayout layout = new GroupLayout(content);
		setLayout(layout);

		lName = new JLabel();
		lAlias = new JLabel();
		lCommand = new JLabel();
		lArguments = new JLabel();
		lResult = new JLabel();
		lPrompt = new JLabel();
		save = new JButton();
		cancel = new JButton();
		name = new JTextField(12);
		alias = new JTextField(12);
		command = new JTextField(12);
		arguments = new JTextField(12);
		result = new JComboBox<String>(options);
		prompt = new JCheckBox();

		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
					.addComponent(lName)
					.addComponent(lAlias)
					.addComponent(lCommand)
					.addComponent(lArguments)
					.addComponent(lResult)
					.addComponent(lPrompt)
					.addComponent(lAlias))
				.addGroup(layout.createParallelGroup()
					.addComponent(name)
					.addComponent(alias)
					.addComponent(command)
					.addComponent(arguments)
					.addComponent(result)
					.addComponent(prompt)
					.addGroup(layout.createSequentialGroup()
						.addComponent(save)
						.addComponent(cancel))));
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
					.addComponent(lName)
					.addComponent(name))
				.addGroup(layout.createParallelGroup()
					.addComponent(lAlias)
					.addComponent(alias))
				.addGroup(layout.createParallelGroup()
					.addComponent(lCommand)
					.addComponent(command))
				.addGroup(layout.createParallelGroup()
					.addComponent(lArguments)
					.addComponent(arguments))
				.addGroup(layout.createParallelGroup()
					.addComponent(lResult)
					.addComponent(result))
				.addGroup(layout.createParallelGroup()
					.addComponent(lPrompt)
					.addComponent(prompt))
				.addGroup(layout.createParallelGroup()
					.addComponent(save)
					.addComponent(cancel)));

		setLabels();

		if (com != null) {
			name.setText(com.name);
			alias.setText(com.alias);
			command.setText(com.command);
			arguments.setText(com.args);
			result.setSelectedIndex(com.result);
			prompt.setSelected(com.prompt);
		}

		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res = new Command(name.getText(), alias.getText(), command.getText(), arguments.getText(), prompt.isSelected(), 1);
				dispose();
			};
		});
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res = null;
				dispose();
			}
		});

	};
	
	public CommandDialog(String title, Settings settings) {
		this(title, settings, (Command)null);
	};


	public Command Show() {
		setVisible(true);
		return res;
	};
};
