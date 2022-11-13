package proj2.db;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

import org.sqlite.SQLiteDataSource;

public class Database extends Communicator {
	
	private String path;
	private String name;
	public SQLiteDataSource source;
	public List<Table> tables = new ArrayList<Table>();
	
	public Database(String path, String name) {
		try {
			this.source = new SQLiteDataSource();
			this.source.setUrl(JDBCSyntax.URL_PREFIX.getValue() + path + "/" + name + JDBCSyntax.URL_SUFFIX.getValue());
		}catch(Exception e) {
			e.printStackTrace();
		}
		this.setPath(path);
		this.setName(name);
	}
	
	public void syphon() {
		for(Table table : tables) {
			table.syphon();
		}
	}
	
	public void addTable(Table table) {
		tables.add(table);
	}

	@Override
	public void update(String command) {
		// TODO Auto-generated method stub
		Communicator.update(this, command);
	}

	@Override
	public CachedRowSet query(String command) {
		// TODO Auto-generated method stub
		return Communicator.query(this, command);
	}

	@Override
	public DataSource getSource() {
		// TODO Auto-generated method stub
		return source;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
