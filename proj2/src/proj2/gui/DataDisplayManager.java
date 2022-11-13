package proj2.gui;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import proj2.Flight;
import proj2.FinalProject;

public class DataDisplayManager {
	
	private static final JTable dataTable = new JTable();
	
	static {
		dataTable.setLayout(null);
	}
	
	public static void setData(Object[][] datas, String[] columns) {
		((DefaultTableModel) dataTable.getModel()).setDataVector(datas, columns);
	}
	
	public static JTable getTable() {
		return dataTable;
	}
	
	public static void update() {
		dataTable.update(dataTable.getGraphics());
		if(dataTable.getParent() != null) dataTable.getParent().update(dataTable.getParent().getGraphics());
	}
	
	public static void loadFromList(List<Flight> flights) {
		int i = 0;
		String[] columns = FinalProject.LANG.filter(new String[]{"Number", "Name", "Origin", "Destination", "Duration", "Total Seats", "Cost"});
		String [][] datas = new String[flights.size()][columns.length];
		for(Flight flight : flights) {
			String[] data = {flight.getFlightNumber()+"", flight.getFlightName(), flight.getFlightOrigin(), flight.getFlightDestination(), flight.getFlightDuration() +"", flight.getTotalSeats() +"", flight.getFlightCost()+""};
			datas[i] = data;
			i++;
		}
		setData(datas, columns);
	}

}
