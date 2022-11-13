package proj2.gui;

import javax.swing.JFrame;

public class Window {
	
	public static final int SCREEN_WIDTH = 1080;
	public static final int SCREEN_HEIGHT = 720;
	
	private JFrame frame;
	
	public Window(String title, int width, int height) {
		frame = new JFrame(title);
		frame.setBounds((SCREEN_WIDTH - width / 2)/ 2, (SCREEN_HEIGHT - height / 2) / 2, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public JFrame getFrame() {
		return frame;
	}

}
