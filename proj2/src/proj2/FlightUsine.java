package proj2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

import proj2.db.Table;

public class FlightUsine {

	private List<FlightController> flights = new ArrayList<FlightController>();
	private Organizer organizer = new Organizer();
	
	public List<FlightController> getFlights(){
		return flights;
	}
	
	public void createDatabaseCapabilities() {
		ConnectTo_Database.getInstance().createDatabase().createTable();
		while(!ConnectTo_Database.getInstance().getTable().isEmpty() && !ConnectTo_Database.getInstance().getTable().doneReading()) {
			load(ConnectTo_Database.getInstance().getTable());
		}
	}
	
	public void load(Table table) {
		flights.add(create(FlightController::new, table));
	}
	
	public FlightController create(FlightFactory b, Table table) {
		return b.load(table);
	}
	
	public FlightController produce(int flightNumber, String flightName, String flightOrigin, String flightDestination,
			int flightDuration, int totalSeats, int flightCost, int availableSeats) {
		FlightController fl = new FlightController(flightNumber, flightName, flightOrigin, flightDestination, flightDuration, totalSeats, flightCost, availableSeats);
		flights.add(fl);
		return fl;
	}
	
	public FlightController produce(String flightNumber, String flightName, String flightOrigin, String flightDestination, String flightDuration, String totalSeats, String flightCost, String availableSeats) {
		return produce(Integer.parseInt(flightNumber), flightName, flightOrigin, flightDestination, Integer.parseInt(flightDuration), Integer.parseInt(totalSeats), Integer.parseInt(flightCost), Integer.parseInt(availableSeats));
	}
	
	public void store(Table table) {
		table.delete();
		for(FlightController fc : flights) {
			Flight flight = fc.getFlight();
			table.insert("", table.getColumns(), flight.getFlightNumber(), flight.getFlightName(), flight.getFlightOrigin(), flight.getFlightDestination(), flight.getFlightDuration(), flight.getTotalSeats(), flight.getFlightCost(), flight.getAvailableSeats());
		}
	}
	
	public void sort(BiPredicate<FlightController, FlightController> pred) {
		organizer.sort(flights, pred);
	}
	
}
