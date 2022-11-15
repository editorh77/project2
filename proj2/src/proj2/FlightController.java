package proj2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import javax.swing.JTextField;

import proj2.db.Column;
import proj2.db.Table;

public class FlightController {
	
	private Flight flight;
	private ViewFlight view;
	
	public FlightController() {
		
	}
	
	/**
	 * 
	 * Production constructor for easier construction of objects
	 * 
	 * @param flightNumber
	 * @param flightName
	 * @param flightOrigin
	 * @param flightDestination
	 * @param flightDuration
	 * @param totalSeats
	 * @param flightCost
	 * @param availableSeats
	 */
	public FlightController(int flightNumber, String flightName, String flightOrigin, String flightDestination,
			int flightDuration, int totalSeats, int flightCost, int availableSeats) {
		flight = new Flight();
		view = new ViewFlight();
		flight.setFlightNumber(flightNumber);
		flight.setFlightName(flightName);
		flight.setFlightOrigin(flightOrigin);
		flight.setFlightDestination(flightDestination);
		flight.setFlightDuration(flightDuration);
		flight.setTotalSeats(totalSeats);
		flight.setFlightCost(flightCost);
		flight.setAvailableSeats(availableSeats);
		flight.setFull(availableSeats <= 0);
	}
	

	/**
	 * 
	 * Factory production method
	 * 
	 * @param table
	 */
	public FlightController(Table table) {
		int pos = table.position();
		load(table, pos);
		table.advance();
	}
	

	
	/**
	 * 
	 * OOP loading method
	 * 
	 * @param table
	 * @param row
	 */
	public Flight load(Table table, int row) {
		flight = new Flight();
		if(!table.isEmpty()) {
			flight.setFlightNumber((Integer) table.getByName("flightNumber", row)).
			setFlightName((String) table.getByName("flightName", row)).
			setFlightOrigin((String) table.getByName("flightOrigin", row)).
			setFlightDestination((String) table.getByName("flightDestination", row)).
			setFlightDuration((Integer) table.getByName("flightDuration", row)).
			setTotalSeats((Integer) table.getByName("totalSeats", row)).
			setFlightCost((Integer) table.getByName("flightCost", row)).
			setAvailableSeats((Integer) table.getByName("availableSeats", row)).
			setFull(flight.getAvailableSeats() <= 0);
		}
		return flight;
	}
	

	/**
	 * 
	 * static method in the event that a manual receiving transaction needs to be done
	 * 
	 * @param table
	 * @param row
	 * @return
	 */
	public static FlightController loadFromDB(Table table, int row) {
		FlightController fl = new FlightController(
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

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public ViewFlight getView() {
		return view;
	}

	public void setView(ViewFlight view) {
		this.view = view;
	}
	
	public long getFlightNumber() {
		return flight.getFlightNumber();
	}

	public String getFlightName() {
		return flight.getFlightName();
	}

	public String getFlightOrigin() {
		return flight.getFlightOrigin();
	}

	public String getFlightDestination() {
		return flight.getFlightDestination();
	}

	public int getFlightDuration() {
		return flight.getFlightDuration();
	}

	public int getTotalSeats() {
		return flight.getTotalSeats();
	}

	public int getFlightCost() {
		return flight.getFlightCost();
	}

	public FlightController setFlightNumber(int flightNumber) {
		flight.setFlightNumber(flightNumber);
		return this;
	}

	public FlightController setFlightName(String flightName) {
		flight.setFlightName(flightName);
		return this;
	}

	public FlightController setFlightOrigin(String flightOrigin) {
		flight.setFlightOrigin(flightOrigin);
		return this;
	}

	public FlightController setFlightDestination(String flightDestination) {
		flight.setFlightDestination(flightDestination);
		return this;
	}

	public FlightController setFlightDuration(int flightDuration) {
		flight.setFlightDuration(flightDuration);
		return this;
	}

	public FlightController setTotalSeats(int totalSeats) {
		flight.setTotalSeats(totalSeats);
		return this;
	}

	public FlightController setFlightCost(int flightCost) {
		flight.setFlightCost(flightCost);
		return this;
	}
	
	public boolean isFull() {
		return flight.isFull();
	}
	
	public FlightController setFull(boolean full) {
		flight.setFull(full);
		return this;
	}

	public int getAvailableSeats() {
		return flight.getAvailableSeats();
	}

	public FlightController setAvailableSeats(int availableSeats) {
		flight.setAvailableSeats(availableSeats);
		return this;
	}


}
