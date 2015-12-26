package app.uni.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Properties;

public class Settings {

	private String path;
	private String fileName;
	private int format;
	private int lang;

	public Settings() {
		//load from file
		Properties props = new Properties();
		try {
			InputStream file = new FileInputStream("./settings.xml");
			props.loadFromXML(file);
			path = props.getProperty("path");
			fileName = props.getProperty("fileName");
			format = Integer.valueOf(props.getProperty("format"));
			lang = Integer.valueOf(props.getProperty("lang"));
			file.close();
		} catch (Exception e) {
			path = "/path/to/file";
			fileName = "commands";
			format = 0;
			lang = 3;
		}
	}

	public void Save() {
		Properties props = new Properties();
		props.setProperty("path", path);
		props.setProperty("fileName", fileName);
		props.setProperty("format", Integer.toString(format));
		props.setProperty("lang", Integer.toString(lang));
		OutputStream file;
		try {
			file = new FileOutputStream("./settings.xml");
			props.storeToXML(file, "Yes, this is goood");
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getFormat() {
		return format;
	}

	public void setFormat(int format) {
		this.format = format;
	}

	public int getLang() {
		return lang;
	}

	public void setLang(int lang) {
		this.lang = lang;
	}

	public String[] getAvailableLanguages() {
		String res[] = { "English (US)", "English (GB)", "Hungarian", "German" };
		return res;
	}

	public String[] getAvailableFormats() {
		String res[] = { "json", "xml", "txt"};
		return res;
	}

	public Locale getLocale() {
		Locale locale;
		switch (lang) {
			case 1:
				locale = Locale.UK;
				break;
			case 2:
				locale = new Locale("hu", "HU");
				break;
			case 3:
				locale = Locale.GERMANY;
				break;
			case 0:
			default:
				locale = Locale.US;
		}
		return locale;
	}

	public String toString() {
		return path + "/" + fileName + "." + getAvailableFormats()[format] + " " + getAvailableLanguages()[lang];
	}
}
