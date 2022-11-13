package proj2.lang;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageFilter {
	
	private static final Locale ORIGIN_LOCA = Locale.US;
	
	private Locale specificLanguage;
	
	public LanguageFilter(Locale locale) {
		specificLanguage = locale;
	}
	
	public String filter(String text) {
		if(!isOriginal()) {
			ResourceBundle bundle = ResourceBundle.getBundle("proj2/lang/text", specificLanguage);
			return bundle.getString(text);
		}
		return text;
	}
	
	public String[] filter(String[] txt) {
		if(!isOriginal()) {
			ResourceBundle bundle = ResourceBundle.getBundle("proj2/lang/text", specificLanguage);
			for(int i = 0; i < txt.length; i++) {
				txt[i] = bundle.getString(txt[i]);
			}
		}
		return txt;
	}
	
	public LanguageFilter setSpecificLanguage(Locale locale) {
		specificLanguage = locale;
		return this;
	}
	
	public boolean isOriginal() {
		return specificLanguage.equals(ORIGIN_LOCA);
	}

}
