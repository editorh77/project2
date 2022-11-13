package proj2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class Organizer {
	
	public static final BiPredicate<Flight, Flight> COMPARE_NUMBER = (a, b) -> a.getFlightNumber() < b.getFlightNumber();
	public static final BiPredicate<Flight, Flight> COMPARE_NAME = (a, b) -> a.getFlightName().compareTo(b.getFlightName()) < 0;
	
	public void sort(List<Flight> flights, BiPredicate<Flight, Flight> pred) {
		Flight[] flightArr = quickSort(flights.toArray(new Flight[flights.size()]), 0, flights.size() - 1, pred);
		flights.clear();
		Collections.addAll(flights, flightArr);
	}
	
	private void swap(Flight[] flights, int f0, int f1) {
		Flight tmp = flights[f0];
		flights[f0] = flights[f1];
		flights[f1] = tmp;
	}
	
	private int sort(Flight[] flights, int low, int high, BiPredicate<Flight, Flight> pred) {
		Flight highest = flights[high];
		int i = low - 1;
		for(int j = low; j < high; j++) {
			if(pred.test(flights[j], highest)) {
				i++;
				swap(flights, i, j);
			}
		}
		swap(flights, i + 1, high);
		return i + 1;
	}
	
	private Flight[] quickSort(Flight[] flights, int low, int high, BiPredicate<Flight, Flight> pred) {
		if(low < high) {
			int ind = sort(flights, low, high, pred);
			quickSort(flights, low, ind - 1, pred);
			quickSort(flights, ind + 1, high, pred);
		}
		return flights;
	}

}
