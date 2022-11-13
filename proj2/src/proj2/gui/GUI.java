package proj2.gui;

import java.awt.Container;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import proj2.FinalProject;

public class GUI {
	
	private static final int WIDTH = 1080;
	private static final int HEIGHT = 720;
	
	private static final EventListeners el = EventListeners.getInstance();
	
	public static void begin() {
		Window window = new Window("FlightManager", WIDTH, HEIGHT);
		JLabel beginLabel = new JLabel("Begin management process - Manager");
		JButton begin = new JButton("BEGIN");
		begin.addActionListener(el);
		begin.setBounds(WIDTH / 2 - 100 / 2, HEIGHT / 2 - 50, 100, 50);
		beginLabel.setBounds(WIDTH / 2 - 100, HEIGHT / 2 - 100, 400, 50);
		window.getFrame().setLayout(null);
		window.getFrame().getContentPane().add(beginLabel);
		window.getFrame().getContentPane().add(begin);
		window.getFrame().update(window.getFrame().getGraphics());
	}
	
	public static void createSecondPanel(Container cont) {
		cont.removeAll();
		String[] names = {"Flight Number", "Flight Name", "Flight Origin", "Flight Destination", "Flight Duration", "Total Seats", "Flight Cost"};
		int x = 100; int y = 50; int z = 0;
		for(String name : names) {
			JTextField tf = new JTextField(FinalProject.LANG.filter(name));
			tf.setBounds(z, 0, x, y);
			cont.add(tf);
			z+=x;
		}
		String[] buttons = {"Add Flight", "Set Flight", "Remove Flight", "Sort Number", "Sort Name", "Eng/Fre"};
		int[][] bounds = {{0, 50}, {100, 50}, {200, 50}, {0, 300}, {0, 350}, {0, 400}};
		for(int i = 0; i < buttons.length; i++) {
			JButton button = new JButton(FinalProject.LANG.filter(buttons[i]));
			button.setBounds(bounds[i][0], bounds[i][1], x, y);
			button.addActionListener(EventListeners.getInstance());
			cont.add(button);
		}
		DataDisplayManager.loadFromList(FinalProject.CENTRAL.getFlights());
		cont.add(new JScrollPane(DataDisplayManager.getTable(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) {{setBounds(0, 100, 700, 200);}});
		cont.update(cont.getGraphics());//Update to refresh the graphics
	}

}
