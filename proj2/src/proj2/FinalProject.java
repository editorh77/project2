package proj2;

import java.util.Locale;

import proj2.db.Column;
import proj2.db.Database;
import proj2.db.Table;
import proj2.gui.GUI;
import proj2.lang.LanguageFilter;

public class FinalProject {
	
	public static final FlightUsine CENTRAL = new FlightUsine();
	public static final LanguageFilter LANG = new LanguageFilter(new Locale("en", "US"));
	
	
	@SuppressWarnings("unchecked")
	public static void init() {
		CENTRAL.createDatabaseCapabilities();
	}
	
	public static void main(String[] args) {
		init();
		GUI.begin();
	}

}
