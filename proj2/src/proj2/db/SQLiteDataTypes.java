package proj2.db;

import java.util.Arrays;

public enum SQLiteDataTypes {
	
	NULL("NULL", void.class, Void.class), 
	INTEGER("INTEGER", int.class, Integer.class), 
	TINY_INT("TINYINT", byte.class, Byte.class),
	SMALL_INT("SMALLINT", short.class, Short.class),
	BIG_INT("BIGINT", long.class, Long.class),
	TEXT("TEXT", String.class, char.class), 
	FLOAT("REAL", float.class, Float.class), 
	DOUBLE("DOUBLE", double.class, Double.class),
	BLOB("BLOB", Object.class), 
	BIT("BIT", Boolean.class, boolean.class);
	
	private String name;
	private Class<?>[] types;
	
	private SQLiteDataTypes(String name, Class<?>... types) {
		this.name = name;
		this.types = types;
	}
	
	public String getName() {
		return name;
	}
	
	public Class<?>[] getTypes() {
		return types;
	}
	
	public static SQLiteDataTypes getFromClazz(Class<?> clazz) {
		SQLiteDataTypes type = Arrays.asList(SQLiteDataTypes.values()).stream().filter(data -> Arrays.asList(data.types).contains(clazz)).findFirst().get();
		if(type != null) {
			return type;
		}else {
			throw new IllegalArgumentException("No such class");
		}
	}

}
