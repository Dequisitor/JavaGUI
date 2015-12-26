package app.uni.view;

import app.uni.controller.LanguageService;
import app.uni.controller.LanguageService.Observer;
import app.uni.model.Settings;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class SettingsDialog extends JDialog {
	private Settings settings;
	private NumberFormatter decimalFormat;
	private NumberFormatter currencyFormat;
	private DefaultFormatterFactory decimalFactory;
	private DefaultFormatterFactory currencyFactory;
	private DateFormat dateFormat;
	private JLabel loutput;
	private JLabel lpath;
	private JLabel lfilename;
	private JLabel llocale;
	private JButton save;
	private JButton cancel;
	private JButton output;

	public void setLabels() {
		LanguageService l = LanguageService.getInstance();
		loutput.setText(l.getLabel("output"));
		lpath.setText(l.getLabel("path"));
		lfilename.setText(l.getLabel("filename"));
		llocale.setText(l.getLabel("locale"));
		save.setText(l.getLabel("save"));
		cancel.setText(l.getLabel("cancel"));
		output.setText(l.getLabel("dir"));
		pack();
	}

	public SettingsDialog(Settings param) {
		super((JFrame)null, "Settings");
		settings = param;
		
		//create a copy
		final Settings oldSettings = new Settings();
		oldSettings.setFileName(param.getFileName());
		oldSettings.setPath(param.getPath());
		oldSettings.setFormat(param.getFormat());
		oldSettings.setLang(param.getLang());

		Container content = getContentPane();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		GroupLayout layout = new GroupLayout(content);
		setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		loutput = new JLabel();
		lpath = new JLabel();
		lfilename = new JLabel();
		llocale = new JLabel();
		save = new JButton();
		cancel = new JButton();
		output = new JButton();

		final JFormattedTextField num = new JFormattedTextField();
		final JFormattedTextField currency = new JFormattedTextField();
		final JTextField date = new JTextField();
		final JLabel lselectedpath = new JLabel(settings.getPath());
		final JTextField filename = new JTextField(settings.getFileName());
		final JComboBox<String> format = new JComboBox<String>(settings.getAvailableFormats());
		final JComboBox<String> language = new JComboBox<String>(settings.getAvailableLanguages());

		format.setSelectedIndex(settings.getFormat());
		language.setSelectedIndex(settings.getLang());
		num.setEnabled(false);
		currency.setEnabled(false);
		date.setEnabled(false);

		decimalFormat = new NumberFormatter(NumberFormat.getNumberInstance(settings.getLocale()));
		currencyFormat = new NumberFormatter(NumberFormat.getCurrencyInstance(settings.getLocale()));
		decimalFactory = new DefaultFormatterFactory(decimalFormat);
		currencyFactory = new DefaultFormatterFactory(currencyFormat);
		num.setFormatterFactory(decimalFactory);
		currency.setFormatterFactory(currencyFactory);
		num.setValue(123456.789);
		currency.setValue(123456.789);
		dateFormat = DateFormat.getDateInstance(DateFormat.FULL, settings.getLocale());
		date.setText(dateFormat.format(new Date()));

		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
					.addComponent(lpath)
					.addComponent(lfilename)
					.addComponent(loutput)
					.addComponent(llocale))
				.addGroup(layout.createParallelGroup()
					.addComponent(output)
					.addComponent(lselectedpath)
					.addComponent(filename)
					.addComponent(format)
					.addComponent(language)
					.addComponent(num)
					.addComponent(currency)
					.addComponent(date)
					.addGroup(layout.createSequentialGroup()
						.addComponent(save)
						.addComponent(cancel))));

		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(lpath)
					.addComponent(output))
				.addComponent(lselectedpath)
				.addGroup(layout.createParallelGroup()
					.addComponent(lfilename)
					.addComponent(filename))
				.addGroup(layout.createParallelGroup()
					.addComponent(loutput)
					.addComponent(format))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(llocale)
					.addComponent(language))
				.addComponent(num)
				.addComponent(currency)
				.addComponent(date)
				.addGroup(layout.createParallelGroup()
					.addComponent(save)
					.addComponent(cancel)));

		setModal(true);
		setLabels();

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settings.setFileName(oldSettings.getFileName());
				settings.setPath(oldSettings.getPath());
				settings.setFormat(oldSettings.getFormat());
				settings.setLang(oldSettings.getLang());
				dispose();
			}
		});
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settings.setFileName(filename.getText());
				settings.setFormat(format.getSelectedIndex());
				settings.setLang(language.getSelectedIndex());
				settings.setPath(lselectedpath.getText());
				settings.Save();
				dispose();
			}
		});
		output.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser dialog = new JFileChooser();
				dialog.setCurrentDirectory(new java.io.File("."));
				dialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				dialog.setAcceptAllFileFilterUsed(false);

				if (dialog.showOpenDialog((JFrame)null) == JFileChooser.APPROVE_OPTION) {
					lselectedpath.setText(dialog.getCurrentDirectory().toString());
				}
			}
		});
		language.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settings.setLang(language.getSelectedIndex());
				Locale locale = settings.getLocale();

				decimalFormat = new NumberFormatter(NumberFormat.getInstance(locale));
				decimalFactory = new DefaultFormatterFactory(decimalFormat);
				num.setFormatterFactory(decimalFactory);
				num.setValue(123456.789);

				currencyFormat = new NumberFormatter(NumberFormat.getCurrencyInstance(locale));
				currencyFactory = new DefaultFormatterFactory(currencyFormat);
				currency.setFormatterFactory(currencyFactory);
				currency.setValue(123456.789);

				dateFormat = DateFormat.getDateInstance(DateFormat.FULL, locale);
				date.setText(dateFormat.format(new Date()));
			}
		});

		LanguageService.getInstance().addListener(new Observer() {
			public void update() {
				setLabels();
			};
		});
		setLabels();
	}

	public void Show() {
		setVisible(true);
	}
}
