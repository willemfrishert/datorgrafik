/** A simple framework for drawing animated "fake" 3D graphics
 * in a Swing component.
 *
 * @author Stefan Gustavson (stegu@itn.liu.se)
 *
 * @version 2007-03-16
 *
 * @see Graphics3DMain
 * @see Mesh
 *
 */

import java.awt.*;
import java.awt.geom.*;
import java.lang.InterruptedException;
import javax.swing.JPanel;

class Graphics3DPanel extends JPanel implements Runnable {

	/* Class-wide variable to hold the start time for animation */
	private long starttime = 0;

	/* Class-wide variable for easy access to the scene data */
	private Mesh m;

	/* Constructor: Initialize important stuff */
	public Graphics3DPanel() {
  		super(); // Run the parent constructor for JPanel
    	// Create a Mesh to use in the paintComponent() method
    	m = new Icosahedron();
	}

	/* The run() method, required to create a Thread from this class.
	 * (This is precisely what "implements Runnable" means.)
	 */
	public void run() {
		starttime = System.currentTimeMillis();
		while(true) {
			try {
				Thread.sleep(40); // Sleep for about 1/25 second
			} catch(InterruptedException e) {}
			repaint();
		}
	}

	/* Render the 3D graphics to the window using Java2D for drawing */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g; // Required to use Java2D

		double time = 0.001 * (System.currentTimeMillis()-starttime);

		int w = getSize().width;
		int h = getSize().height;
		g2.setColor(Color.white);
		g2.fillRect(0, 0, w, h); // Erase before drawing a new frame

		Matrix4 T;
    	T = Matrix4.getTranslateInstance(250.0, 250.0, 0.0);
    	T = T.mult(Matrix4.getScaleInstance(50.0));
		m.transformVertices(T.mult(Matrix4.getRotateXInstance(2.0 * Math.PI * time)));

    	g2.setPaint(Color.black);
    	m.renderWire(g2);
	}

}
