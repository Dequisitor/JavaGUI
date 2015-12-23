import javax.swing.*;
import java.awt.Container;
import java.awt.event.*;

public class CommandDialog extends JDialog {
	private JTextField name;
	private JTextField alias;
	private JTextField command;
	private JTextField arguments;
	private JComboBox result;
	private JCheckBox prompt;
	private Command res;

	public CommandDialog(JFrame parent, String title) {
		super(parent, title);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Container content = getContentPane();
		setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

		JLabel lName = new JLabel("Name");
		JLabel lAlias = new JLabel("Alias");
		JLabel lCommand = new JLabel("Command");
		JLabel lArguments = new JLabel("Arguments");
		JLabel lResult = new JLabel("Result Type");
		JLabel lPrompt = new JLabel("Prompt");
		JButton save = new JButton("Save");
		JButton cancel = new JButton("Cancel");
		name = new JTextField(12);
		alias = new JTextField(12);
		command = new JTextField(12);
		arguments = new JTextField(12);
		result = new JComboBox();
		prompt = new JCheckBox();
		JPanel p0 = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();

		p0.add(lName);
		p0.add(name);
		p1.add(lAlias);
		p1.add(alias);
		p2.add(lCommand);
		p2.add(command);
		p3.add(lArguments);
		p3.add(arguments);
		p4.add(lResult);
		p4.add(result);
		p5.add(lPrompt);
		p5.add(prompt);
		p6.add(save);
		p6.add(cancel);

		content.add(p0);
		content.add(p1);
		content.add(p2);
		content.add(p3);
		content.add(p4);
		content.add(p5);
		content.add(p6);
		
		//setSize(300, 400);
		pack();

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

	public Command Show() {
		setVisible(true);
		return res;
	};
};
