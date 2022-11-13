package proj2.db;

import java.lang.reflect.ParameterizedType;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

public class Table extends Communicator {
	
	private Database base;
	private String name;
	private List<Column<?>> columns = new ArrayList<Column<?>>();
	private HashMap<Column<?>, List<Object>> values = new HashMap<Column<?>, List<Object>>();
	private int position = 0;
	private boolean empty = true;
	
	public Table(String name, Database base, Column<?>...columns) {
		String allCols = "";
		this.name = name;
		this.base = base;
		int ind = 0;
		for(Column<?> col : columns) {
			this.columns.add(col);
			values.put(col, new ArrayList<Object>());
			allCols += col.getCommandText() + (ind < columns.length - 1 ? "," : "");
			ind ++;
		}
		update("CREATE TABLE IF NOT EXISTS '" + name + "' (" + allCols + ")");
	}
	
	public void insert(String conditions, Column<?>[] columns, Object...values) {
		String names = "";
		String vals = "";
		int ind = 0;
		for(Column<?> col : columns) {
			String suffix = (ind < columns.length - 1 ? ", " : "");
			names += col.getName() + suffix;
			vals += Communicator.commandConversion(values[ind]) + suffix;
			this.values.get(columns[ind]).add(values[ind]);
			ind++;
		}
		update("INSERT INTO '" + name + "' (" + names + ")" + " VALUES (" + vals + ") " + conditions);
		checkEmpty();
	}
	
	public void clear() {
		
	}
	
	public int position() {
		return position;
	}
	
	public void position(int position) {
		this.position = position;
	}
	
	public boolean doneReading() {
		return !(position < values.get(columns.get(0)).size());
	}
	
	public void advance() {
		position ++;
	}
	
	public Object getByName(String column, int row) {
		AtomicReference<Object> rv = new AtomicReference<Object>();
		columns.forEach(k -> {
			if(k.getName().equals(column)) {
				rv.set(values.get(k).get(row));
			}
		});
		return rv.get();
	}
	
	public Column<?> getColumn(String name){
		return columns.stream().filter(c -> c.getName().equals(name)).findFirst().get();
	}
	
	public void delete(Column<?>[] columns, Object...matches) {
		String condition = "";
		for(int i = 0; i < columns.length; i++) {
			Object match = matches[i];
			System.out.println(i);
			if(!values.get(columns[i]).contains(match)) continue;
			Object obj = values.get(columns[i]).stream().filter(o -> o.equals(match)).findFirst().get();
			values.get(columns[i]).remove(obj);
			condition += columns[i].getName() + " = " + Communicator.commandConversion(match) + (i > columns.length - 1 ? " AND " : "");
		}
		if(condition.isEmpty()) {
			return;
		}
		update("DELETE FROM '" + name + "' WHERE " + condition);
		checkEmpty();
	}
	
	/**
	 * Wipe out delete
	 */
	public void delete() {
		values.values().forEach(l -> l.clear());
		update("DELETE FROM '" + name + "'");
	}
	
	public Column[] getColumns() {
		return columns.toArray(new Column[values.size()]);
	}
	
	public void syphon() {
		CachedRowSet res = query("SELECT * FROM '" + name + "'");
		try {
			ResultSetMetaData meta = res.getMetaData();
			int total = meta.getColumnCount();
			if(total > values.size()) {
				for(int i = 1; i <= total; i++) {
					String name = meta.getColumnName(i);
					if(!values.keySet().stream().anyMatch(c -> c.getName().equals(name))) {
						Class<?> clazz = SQLTypeConverter.toClass(meta.getColumnType(i));
						Column<?> col = new Column<Object>(name, "") {
							public void postConstruction(){
								type = SQLiteDataTypes.getFromClazz(clazz).getName();
							}
						};
						columns.add(col);
						values.put(col, new ArrayList<Object>());
					}
				}
			}else if(total < values.size()) {
				List<String> names = new ArrayList<String>();
				for(int i = 1; i <= total; i++) {
					names.add(meta.getColumnName(i));
				}
				columns.removeIf(c -> !names.contains(c.getName()));
				values.keySet().removeIf(c -> !names.contains(c.getName()));
			}
			while(res.next()) {
				for(Column col : values.keySet()) {
					values.get(col).add(res.getObject(col.getName()));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		checkEmpty();
	}

	@Override
	public void update(String command) {
		// TODO Auto-generated method stub
		System.out.println(command);
		Communicator.update(this, command);
		checkEmpty();
	}
	
	private void checkEmpty() {
		if(this.values.values().stream().allMatch(l -> l.isEmpty())) {
			empty = true;
		}else {
			empty = false;
		}
	}

	@Override
	public CachedRowSet query(String command) {
		// TODO Auto-generated method stub
		System.out.println(command);
		return Communicator.query(this, command);
	}

	@Override
	public DataSource getSource() {
		// TODO Auto-generated method stub
		return base.getSource();
	}
	
	public boolean isEmpty() {
		return empty;
	}
	
	public HashMap<Column<?>, List<Object>> getValues(){
		return values;
	}

}
