package proj2;

import proj2.db.Table;

public interface FlightFactory {
	
	public Flight load(Table table);

}
