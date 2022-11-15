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
	 * debug purposes only
	 * do not use
	 * 
	 * @deprecated
	 */
	public String toString() {
		return flightNumber + " " + flightName + " " + flightOrigin + " " + flightDestination + " " + flightDuration + " " + totalSeats + " " + flightCost + " " + availableSeats + " " + isFull();
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public Flight setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
		if(availableSeats <= 0) setFull(true);
		else setFull(true);
		return this;
	}

}
