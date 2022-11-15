package proj2;

import proj2.gui.GUI;
import proj2.gui.Window;

public class ViewFlight {
	
	public String toString(FlightController flight) {
		return flight.getFlightNumber() + " " + flight.getFlightName() + " " + flight.getFlightOrigin() + " " + flight.getFlightDestination() + " " + flight.getFlightDuration() + " " + flight.getTotalSeats() + " " + flight.getFlightCost() + " " + flight.getAvailableSeats() + " " + flight.isFull();
	}

}
