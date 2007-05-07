/** A simple framework for drawing animated "fake" 3D graphics
 * with perspective transformation in a Swing component.
 *
 * @author Stefan Gustavson (stegu@itn.liu.se)
 *
 * @version 2007-05-07
 *
 * @see Graphics3DMain
 * @see Mesh
 * @see Titanic
 *
 */

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.*;
import java.lang.InterruptedException;
import javax.swing.JPanel;

class Graphics3DPanelPerspective extends JPanel implements Runnable, KeyListener {

	/* Class-wide variable to hold the start time for animation */
	private long starttime = 0;

	/* Class-wide variable for easy access to the scene data */
	private Mesh m;

	/* Constructor: Initialize important stuff */
	public Graphics3DPanelPerspective() {
  		super(); // Run the parent constructor for JPanel
    	// Create a Mesh to use in the paintComponent() method
    	m = new Titanic();
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

		double time = 0.01 * (System.currentTimeMillis()-starttime);

		int w = getSize().width;
		int h = getSize().height;
		g2.setColor(Color.white);
		g2.fillRect(0, 0, w, h); // Erase before drawing a new frame

    	Matrix4 M;  // M is the model transformation
    	Matrix4 V;  // V is the view transformation
	    Matrix4 P;  // P is the perspective transformation
    	Matrix4 W;  // W is the window transformation

    	// Scale the object to a more convenient size
	    M = Matrix4.getScaleInstance(0.15);
	    
	    // View from slightly above
	    // Rotate view
    	V = Matrix4.getRotateXInstance(-Math.PI*5.0/12.0); 
    	V = V.mult(Matrix4.getRotateZInstance(2.0*Math.PI*time/100.0)); 
    	
    	
    	// A unit matrix - you need to create your own P and use it
    	P = new Matrix4();
    	double distance = 1.0;
    	P.a33 = 0.0;
    	P.a43 = 1/distance;
    	    	
        // W centers and scales the plot in the window, and flips Y to match Java2D
    	W = Matrix4.getTranslateInstance(0.5*w,0.5*h,0.0).mult( Matrix4.getScaleInstance( 0.5*h,-0.5*h,0.5*h ) );
    	
    	// This gives a plot without perspective	
    	m.transformVertices( W.mult( P.mult( V.mult(M) ) ) ); 
    	
    	// normalizeW
    	m.normalizeTransformedVertices();

    	// Wire frame plot without culling
    	g2.setPaint(Color.black);
    	m.renderWire(g2);
	}

	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}




}
