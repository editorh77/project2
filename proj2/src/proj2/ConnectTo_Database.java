package proj2;

import proj2.db.Column;
import proj2.db.Database;
import proj2.db.Table;

public class ConnectTo_Database {
	
	private static ConnectTo_Database instance = new ConnectTo_Database();
	public Database database;
	public Table table;
	
	public static ConnectTo_Database getInstance() {
		return instance;
	}
	
	public ConnectTo_Database createDatabase() {
		database = new Database(System.getProperty("user.dir") +"/src/proj2/databases", "FLIGHTCONTROL");
		return this;
	}
	
	private Column<?>[] createColumns() {
		Column<?>[] column = new Column<?>[] {
			new Column<Integer>("flightNumber", ""){},
			new Column<String>("flightName", "") {},
			new Column<String>("flightOrigin", "") {},
			new Column<String>("flightDestination", "") {},
			new Column<Integer>("flightDuration", "") {},
			new Column<Integer>("totalSeats", "") {},
			new Column<Integer>("flightCost", "") {},
			new Column<Integer>("availableSeats", "") {}
			};
		return column;
	}
	
	public ConnectTo_Database createTable() {
		table = new Table("table", database, createColumns());
		table.syphon();
		return this;
	}
	
	public ConnectTo_Database clearTable() {
		table.delete();
		return this;
	}
	
	public Database getDatabase() {
		return database;
	}
	
	public Table getTable() {
		return table;
	}

}
