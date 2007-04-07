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
	
	private Light lightSources[] = new Light[2];
	private Vector3 lightDir0;
	private Vector3 lightDir1;

	/* Constructor: Initialize important stuff */
	public Graphics3DPanel() {
  		super(); // Run the parent constructor for JPanel
    	// Create a Mesh to use in the paintComponent() method
    	m = new Icosahedron();
    	m.calculateFaceColors();
    	
    	lightSources[0] = new Light();
    	lightSources[0].color = new ColorRGB(0,1,0);
    	lightSources[0].direction = new Vector3(0.0, 0.0, 0.0);
    	lightSources[1] = new Light();
    	lightSources[1].color = new ColorRGB(0,0,1);
    	lightSources[1].direction = new Vector3(-0.0, 0.0, 0.0);
    	
    	lightDir0 = new Vector3(1.0, 0.0, 0.0);
    	lightDir1 = new Vector3(-1.0, 0.0, 0.0);
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

		Matrix4 T = new Matrix4();
    	T = Matrix4.getTranslateInstance(250.0, 250.0, 0.0);
    	T = T.mult(Matrix4.getScaleInstance(50.0));
    	
    	T = T.mult(Matrix4.getRotateZInstance(-0.125 * Math.PI * time));
    	T = T.mult(Matrix4.getTranslateInstance(2.0, 0, 0));
    	T = T.mult(Matrix4.getRotateXInstance(0.25 * Math.PI * time));
		m.transformVertices(T);

    	g2.setPaint(Color.black);
    	
    	T = Matrix4.getRotateZInstance(0.125 * Math.PI * time);
    	lightSources[0].direction = T.mult(lightDir0);
    	lightSources[1].direction = T.mult(lightDir1);
    	
//    	m.renderWire(g2);
//    	m.renderWireCulled(g2);
//    	m.renderFaceCulled(g2);
//    	m.renderFaceColorCulled(g2);
    	m.renderFaceShadedCulled(g2, lightSources);
	}

}
