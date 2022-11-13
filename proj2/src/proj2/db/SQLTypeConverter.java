package proj2.db;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Types;
import java.util.HashMap;

public class SQLTypeConverter {
	
	public void put() throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		HashMap<String, Integer> temp = new HashMap<String, Integer>();
		Field[] fields = Types.class.getFields();
		for(Field field : fields) {
			field.setAccessible(true);
			if(Modifier.isFinal(field.getModifiers())) {
				temp.put(field.getName(), field.getInt(Types.class.newInstance()));
			}
			field.setAccessible(false);
		}
	}
	
	 public static Class<?> toClass(int type) {
	        Class<?> result = Object.class;

	        switch (type) {
	            case Types.CHAR:
	            case Types.VARCHAR:
	            case Types.LONGVARCHAR:
	                result = String.class;
	                break;

	            case Types.NUMERIC:
	            case Types.DECIMAL:
	                result = java.math.BigDecimal.class;
	                break;

	            case Types.BIT:
	                result = boolean.class;
	                break;

	            case Types.TINYINT:
	                result = byte.class;
	                break;

	            case Types.SMALLINT:
	                result = short.class;
	                break;

	            case Types.INTEGER:
	                result = int.class;
	                break;

	            case Types.BIGINT:
	                result = long.class;
	                break;

	            case Types.REAL:
	            case Types.FLOAT:
	                result = float.class;
	                break;

	            case Types.DOUBLE:
	                result = double.class;
	                break;

	            case Types.BINARY:
	            case Types.VARBINARY:
	            case Types.LONGVARBINARY:
	                result = Byte[].class;
	                break;

	            case Types.DATE:
	                result = java.sql.Date.class;
	                break;

	            case Types.TIME:
	                result = java.sql.Time.class;
	                break;

	            case Types.TIMESTAMP:
	                result = java.sql.Timestamp.class;
	                break;
	        }

	        return result;
	    }
 
}
