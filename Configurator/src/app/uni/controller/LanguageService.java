package app.uni.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageService {
	public interface Observer {
		public void update();
	};

	public static LanguageService _instance = null;
	private ResourceBundle labels;
	private List<Observer> observers = new ArrayList<Observer>();

	private LanguageService() {
		setLocale(Locale.US);
	};

	public static LanguageService getInstance() {
		if (_instance == null) {
			_instance = new LanguageService();
		}
		return _instance;
	};

	public String getLabel(String label) {
		String res;
		try {
			String tmp = labels.getString(label);
			res = new String(tmp.getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			res = label;
		}

		return res;
	};

	public void setLocale(Locale locale) {
		labels = ResourceBundle.getBundle("app.uni.resources.labels", locale);
		notifyObservers();
	};

	public void addListener(Observer obs) {
		observers.add(obs);
	};

	public void notifyObservers() {
		for (Observer observer : observers) {
			try {
				observer.update();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
};
