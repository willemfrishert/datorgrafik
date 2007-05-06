/** A main class to create, display and run a Graphics3DPanel.
 *
 * @author Stefan Gustavson (stegu@itn.liu.se)
 *
 * @version 2007-03-16
 *
 * @see Graphics3DPanel
 *
 */

import java.awt.Dimension;
import javax.swing.JFrame;

public class Graphics3DMain {

	public static void main(String args[]) {

		JFrame mainFrame = new JFrame("3D graphics example");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add the content pane, we use a single JPanel
		Graphics3DPanel panel = new Graphics3DPanel();
		panel.setPreferredSize(new Dimension(500,500));
		mainFrame.add(panel);
		mainFrame.pack();
		panel.addKeyListener( panel );

		// Create and start the animation thread
		Thread animationThread = new Thread(panel);
		animationThread.start();

		// Display the window
		mainFrame.setVisible(true);
	}
	
}
