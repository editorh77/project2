package proj2.db;

import java.lang.reflect.ParameterizedType;

public abstract class Column<T> {
	
	protected String name;
	protected String type;
	protected String annotes;
	
	@SuppressWarnings("unchecked")
	public Column(String name, String annotes){
		this.name = name;
		this.annotes = annotes;
		this.type = SQLiteDataTypes.getFromClazz((Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getName();
		postConstruction();
	}
	
	public void postConstruction() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public String getAnnotes() {
		return annotes;
	}
	
	public String getCommandText() {
		return name + " " + type + " " + annotes + " ";
	}

}
