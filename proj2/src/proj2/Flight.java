package proj2;

import proj2.db.Table;

public class Flight {
	
	private int flightNumber;
	private String flightName;
	private String flightOrigin;
	private String flightDestination;
	private int flightDuration;
	private int totalSeats;
	private int flightCost;
	private int availableSeats;
	private boolean full;
	
	public Flight(int flightNumber, String flightName, String flightOrigin, String flightDestination,
			int flightDuration, int totalSeats, int flightCost, int availableSeats) {
		super();
		this.flightNumber = flightNumber;
		this.flightName = flightName;
		this.flightOrigin = flightOrigin;
		this.flightDestination = flightDestination;
		this.flightDuration = flightDuration;
		this.totalSeats = totalSeats;
		this.flightCost = flightCost;
		this.setAvailableSeats(availableSeats);
		this.full = availableSeats <= 0;
	}
	
	/**
	 * 
	 * Factory production method
	 * 
	 * @param table
	 */
	public Flight(Table table) {
		int pos = table.position();
		load(table, pos);
		table.advance();
	}

	public long getFlightNumber() {
		return flightNumber;
	}

	public String getFlightName() {
		return flightName;
	}

	public String getFlightOrigin() {
		return flightOrigin;
	}

	public String getFlightDestination() {
		return flightDestination;
	}

	public int getFlightDuration() {
		return flightDuration;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public int getFlightCost() {
		return flightCost;
	}

	public Flight setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
		return this;
	}

	public Flight setFlightName(String flightName) {
		this.flightName = flightName;
		return this;
	}

	public Flight setFlightOrigin(String flightOrigin) {
		this.flightOrigin = flightOrigin;
		return this;
	}

	public Flight setFlightDestination(String flightDestination) {
		this.flightDestination = flightDestination;
		return this;
	}

	public Flight setFlightDuration(int flightDuration) {
		this.flightDuration = flightDuration;
		return this;
	}

	public Flight setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
		return this;
	}

	public Flight setFlightCost(int flightCost) {
		this.flightCost = flightCost;
		return this;
	}
	
	public boolean isFull() {
		return full;
	}
	
	public Flight setFull(boolean full) {
		this.full = full;
		return this;
	}
	
	/**
	 * 
	 * OOP loading method
	 * 
	 * @param table
	 * @param row
	 */
	public Flight load(Table table, int row) {
		if(!table.isEmpty()) {
			setFlightNumber((Integer) table.getByName("flightNumber", row));
			setFlightName((String) table.getByName("flightName", row));
			setFlightOrigin((String) table.getByName("flightOrigin", row));
			setFlightDestination((String) table.getByName("flightDestination", row));
			setFlightDuration((Integer) table.getByName("flightDuration", row));
			setTotalSeats((Integer) table.getByName("totalSeats", row));
			setFlightCost((Integer) table.getByName("flightCost", row));
			setAvailableSeats((Integer) table.getByName("availableSeats", row));
			setFull(availableSeats <= 0);
		}
		return this;
	}
	
	/**
	 * 
	 * static method in the event that a manual receiving transaction needs to be done
	 * 
	 * @param table
	 * @param row
	 * @return
	 */
	public static Flight loadFromDB(Table table, int row) {
		Flight fl = new Flight(
				(Integer) table.getByName("flightNumber", row), 
				(String) table.getByName("flightName", row),
				(String) table.getByName("flightOrigin", row),
				(String) table.getByName("flightDestination", row),
				(Integer) table.getByName("flightDuration", row),
				(Integer) table.getByName("totalSeats", row),
				(Integer) table.getByName("flightCost", row),
				(Integer) table.getByName("availableSeats", row)
				);
		return fl;
	}
	
	/**
	 * debug purposes
	 */
	public String toString() {
		return flightNumber + " " + flightName + " " + flightOrigin + " " + flightDestination + " " + flightDuration + " " + totalSeats + " " + flightCost + " " + availableSeats + " " + isFull();
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
		if(availableSeats <= 0) setFull(true);
	}

}
