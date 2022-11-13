package proj2.db;

public enum JDBCSyntax {
	
	URL_PREFIX("jdbc:sqlite:"),
	URL_SUFFIX(".db");
	
	private String value;
	
	private JDBCSyntax(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

}
