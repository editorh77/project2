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
	
	private List<Flight> flights = new ArrayList<Flight>();
	private Organizer organizer = new Organizer();
	
	public List<Flight> getFlights(){
		return flights;
	}
	
	public void createDatabaseCapabilities() {
		ConnectTo_Database.getInstance().createDatabase().createTable();
		while(!ConnectTo_Database.getInstance().getTable().isEmpty() && !ConnectTo_Database.getInstance().getTable().doneReading()) {
			load(ConnectTo_Database.getInstance().getTable());
		}
	}
	
	public void load(Table table) {
		flights.add(create(Flight::new, table));
	}
	
	public Flight create(FlightFactory b, Table table) {
		return b.load(table);
	}
	
	public Flight produce(int flightNumber, String flightName, String flightOrigin, String flightDestination,
			int flightDuration, int totalSeats, int flightCost) {
		Flight fl = new Flight(flightNumber, flightName, flightOrigin, flightDestination, flightDuration, totalSeats, flightCost);
		flights.add(fl);
		return fl;
	}
	
	public Flight produce(String flightNumber, String flightName, String flightOrigin, String flightDestination, String flightDuration, String totalSeats, String flightCost) {
		return produce(Integer.parseInt(flightNumber), flightName, flightOrigin, flightDestination, Integer.parseInt(flightDuration), Integer.parseInt(totalSeats), Integer.parseInt(flightCost));
	}
	
	public void store(Table table) {
		table.delete();
		for(Flight flight : flights) {
			table.insert("", table.getColumns(), flight.getFlightNumber(), flight.getFlightName(), flight.getFlightOrigin(), flight.getFlightDestination(), flight.getFlightDuration(), flight.getTotalSeats(), flight.getFlightCost());
		}
	}
	
	public void sort(BiPredicate<Flight, Flight> pred) {
		organizer.sort(flights, pred);
	}

}
