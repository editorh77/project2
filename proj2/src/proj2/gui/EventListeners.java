package proj2.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import proj2.ConnectTo_Database;
import proj2.Flight;
import proj2.FinalProject;
import proj2.Organizer;

public class EventListeners implements ActionListener {
	
	private HashMap<Class<?>, Listener<?>> listeners = new HashMap<Class<?>, Listener<?>>();
	private static final EventListeners instance = new EventListeners();
	
	public static EventListeners getInstance() {
		return instance;
	}
	
	private EventListeners() {
		listeners.put(JButton.class, new Listener<JButton>() {
			
			private HashMap<String, Consumer<Container>> namedMap = new HashMap<String, Consumer<Container>>();
			
			private void init(){
				namedMap.put(FinalProject.LANG.filter("BEGIN"), (parent) -> {
					GUI.createSecondPanel(parent);
				});
				namedMap.put(FinalProject.LANG.filter("Add Flight"), (parent) -> {
					FinalProject.CENTRAL.produce(
									((JTextField) parent.getComponents()[0]).getText(),
									((JTextField) parent.getComponents()[1]).getText(),
									((JTextField) parent.getComponents()[2]).getText(),
									((JTextField) parent.getComponents()[3]).getText(),
									((JTextField) parent.getComponents()[4]).getText(),
									((JTextField) parent.getComponents()[5]).getText(),
									((JTextField) parent.getComponents()[6]).getText()
							);
					DataDisplayManager.loadFromList(FinalProject.CENTRAL.getFlights());
					FinalProject.CENTRAL.store(ConnectTo_Database.getInstance().getTable());
				});
				namedMap.put(FinalProject.LANG.filter("Set Flight"), parent -> {
					FinalProject.CENTRAL.getFlights().get(DataDisplayManager.getTable().getSelectedRow()).
						setFlightNumber(Integer.parseInt(((JTextField) parent.getComponents()[0]).getText())).
						setFlightName(((JTextField) parent.getComponents()[1]).getText()).
						setFlightOrigin(((JTextField) parent.getComponents()[2]).getText()).
						setFlightDestination(((JTextField) parent.getComponents()[3]).getText()).
						setFlightDuration(Integer.parseInt(((JTextField) parent.getComponents()[4]).getText())).
						setTotalSeats(Integer.parseInt(((JTextField) parent.getComponents()[5]).getText())).
						setFlightCost(Integer.parseInt(((JTextField) parent.getComponents()[6]).getText()));
					DataDisplayManager.loadFromList(FinalProject.CENTRAL.getFlights());
					FinalProject.CENTRAL.store(ConnectTo_Database.getInstance().getTable());
				});
				namedMap.put(FinalProject.LANG.filter("Remove Flight"), parent -> {
					FinalProject.CENTRAL.getFlights().remove(DataDisplayManager.getTable().getSelectedRow());
					DataDisplayManager.loadFromList(FinalProject.CENTRAL.getFlights());
					FinalProject.CENTRAL.store(ConnectTo_Database.getInstance().getTable());
				});
				Consumer<BiPredicate<Flight, Flight>> sorting = b -> {FinalProject.CENTRAL.sort(b); DataDisplayManager.loadFromList(FinalProject.CENTRAL.getFlights());};
				namedMap.put(FinalProject.LANG.filter("Sort Number"), parent -> {
					sorting.accept(Organizer.COMPARE_NUMBER);
				});
				namedMap.put(FinalProject.LANG.filter("Sort Name"), parent -> {
					sorting.accept(Organizer.COMPARE_NAME);
				});
				namedMap.put(FinalProject.LANG.filter("Eng/Fre"), parent -> {
					if(FinalProject.LANG.isOriginal())
						FinalProject.LANG.setSpecificLanguage(Locale.CANADA_FRENCH);
					else
						FinalProject.LANG.setSpecificLanguage(Locale.US);
					GUI.createSecondPanel(parent);
					DataDisplayManager.loadFromList(FinalProject.CENTRAL.getFlights());
				});
			}

			@Override
			public void invoke(JButton t) {
				init();
				Container parent = t.getParent();
				// TODO Auto-generated method stub
				if(namedMap.containsKey(t.getText()))
					namedMap.get(t.getText()).accept(parent);
				else throw new IllegalArgumentException("There is no action to be performed by the button " + t.getText());
			}
			
		});
	}

	
	/**
	 * Invokes a select listener based on the type of object that is passed through it.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(listeners.keySet().contains(e.getSource().getClass())) {
			((Listener<Object>) listeners.get(e.getSource().getClass())).invoke(e.getSource());
		}else {
			throw new IllegalArgumentException("There is no such listener.");
		}
	}

}
