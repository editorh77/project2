package proj2;

import proj2.db.Table;

public interface FlightFactory {
	
	public FlightController load(Table table);

}
