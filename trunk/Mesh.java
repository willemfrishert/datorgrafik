/* Mesh.java 1.01 2007-03-16
 * Stefan Gustavson, ITN-LiTH 2007 (stegu@itn.liu.se)
 */

import java.lang.*;
import java.awt.*;
import java.awt.geom.*;

/** The main 3D graphics object class for TNM046 lab exercises.
 * <p>
 * The <code>Mesh</code> class is the hub of the object hierarchy
 * for the lab exercises. This class holds the geometry, color and lighting
 * data for a 3D object, and is responsible for the culling, shading and
 * rendering to a 2D graphics window. Classes referred to by <code>Mesh</code>
 * contain data wrappers plus some related matrix and vector math that is
 * better kept separate, but this class is where most of the 3D stuff happens.
 * <p>
 * The other classes in this class structure are:
 * <ul>
 * <li><code>ColorRGB</code>: A simple color definition
 * <li><code>Vector3</code>: A 3-element vector
 * <li><code>Vertex4</code>: A 4-element homgeneous coordinate vector for a vertex
 * <li><code>Matrix4</code>: A 4x4 transformation matrix
 * <li><code>Triangle</code>: A triangle referring to a vertex list
 * <li><code>Light</code>: A simple light source object
 * <li><code>Icosahedron</code>: Example object, a subclass of Mesh that defines an icosahedron
 * <li><code>Graphics3DPanel</code>: A Swing component to demonstrate an example use of the classes
 * <li><code>Graphics3DMain</code>: A main window to hold a Graphics3DPanel
 * </ul>
 * @version 2007-03-16
 * @author Stefan Gustavson (stegu@itn.liu.se)
 * @see ColorRGB
 * @see Vector3
 * @see Vertex4
 * @see Matrix4
 * @see Triangle
 * @see Light
 * @see Icosahedron
 * @see Graphics3DPanel
 * @see Graphics3DMain
 */
public class Mesh {

/** The original untransformed vertex coordinates for the object */
  public Vertex4[] vertices;

/** Transformed vertex coordinates for the object */
  public Vertex4[] tVertices;

/** The triangle list for the object */
  public Triangle[] triangles;

/** Face normal vectors, one for each triangle */
  public Vector3[] faceNormals;

/** Face colors, one for each triangle */
  public ColorRGB[] faceColors;

/** Face visibility flags, one for each triangle */
  public boolean[] faceVisibility;

/** Face lighting information, one for each triangle */
  public ColorRGB[] faceLighting;

/** Default constructor - does nothing, could be implicit */
  public Mesh() {
	  
  }

/**
  * Constructor to load geometry data from a file - not yet implemented
  * @param filename The name of the file containing geometry data
  */
  public Mesh(String filename) {
    // Not yet implemented - suggested extra assignment
  }
  
  public void calculateFaceColors(){
	  faceColors = new ColorRGB[triangles.length];
	  
	  for (int i=0; i < faceColors.length; i++)
	  {
		  faceColors[i] = new ColorRGB(Math.random()*0.25,Math.random()*0.25,Math.random()*0.25);
	  }
  }

/**
  * Transform <code>vertices</code>, store transformed result in <code>tVertices</code>
  * @param mtx The transformation matrix
  */
  public void transformVertices(Matrix4 mtx) {
    if((tVertices == null) || (tVertices.length != vertices.length)) {
      tVertices = new Vertex4[vertices.length];
    }
    for(int i=0; i<vertices.length; i++) {
      if(vertices[i] != null) {
        tVertices[i] = mtx.mult(vertices[i]);
      }
    }
  }

/**
  * Transform <code>tVertices</code>, store result back to <code>tVertices</code> again
  * @param mtx The transformation matrix
  */
  public void transformTransformedVertices(Matrix4 mtx) {
    for(int i=0; i<tVertices.length; i++) {
      if(tVertices[i] != null) {
        tVertices[i] = mtx.mult(tVertices[i]);
      }
    }
  }

/**
  * Calculate face normals by a cross product of two edges and store the result
  * in <code>faceNormals</code>
  */
  public void calculateFaceNormals() {
    if (faceNormals == null || faceNormals.length != triangles.length)
      faceNormals = new Vector3[triangles.length];
    for (int i=0; i<triangles.length; i++) {
      // 1) Construct vectors e1 from p1 to p2 and e2 from p1 to p3
    	Vertex4 p1 = tVertices[triangles[i].p1];
    	Vertex4 p2 = tVertices[triangles[i].p2];
    	Vertex4 p3 = tVertices[triangles[i].p3];
    	Vector3 e1 = new Vector3( p2.x - p1.x, p2.y-p1.y, p2.z - p1.z ) ;
    	Vector3 e2 = new Vector3( p3.x - p1.x, p3.y-p1.y, p3.z - p1.z ) ;
    	
      // 2) Perform the cross product e1 x e2
    	Vector3 normal = Vector3.cross(e1, e2);
    	
      // 3) Normalize the result to yield the face normal vector
    	normal.normalize();
    	
    	faceNormals[i] = normal;
    }
  }

/**
  * Calculate face visibility assuming the viewer is in the positive z direction,
  * and store the result in <code>faceVisibility</code>
  */
  public void calculateFaceVisibility() {
	  
    Vector3 viewDirection = new Vector3(0,0,-1);
    calculateFaceVisibility(viewDirection);
    
//    if (faceVisibility == null || faceVisibility.length != triangles.length)
//      faceVisibility = new boolean[triangles.length];
//    for (int i=0; i<triangles.length; i++) {
//      // Determine face visibility from face normal direction
//    	double angle = Vector3.dot(viewDirection, faceNormals[i]);
//    	
//    	if (Math.abs(angle) < 1)
//    	{
//    		faceVisibility[i] = true;
//    	}
//    	else
//    	{
//    		faceVisibility[i] = false;
//    	}
//    }
  }

/**
  * Calculate face visibility from an arbitrary viewer direction, and
  * store the result in <code>faceVisibility</code>.
  */
  public void calculateFaceVisibility(Vector3 viewer) {
    if (faceVisibility == null || faceVisibility.length != triangles.length)
      faceVisibility = new boolean[triangles.length];
    for (int i=0; i<triangles.length; i++) {
      // Determine face visibility from face normal and viewer direction
	  double angle = Vector3.dot(viewer, faceNormals[i]);
	
	  if (Math.abs(angle) < 1)
	  {
	  	faceVisibility[i] = true;
	  }
	  else
	  {
		faceVisibility[i] = false;
	  }
	}
  }

/**
  * Calculate diffuse lighting from one directional light source
  * @param lightsource The light source for which to calculate diffuse lighting
  * @return An array of <code>ColorRGB</code> objects, one for each triangle
  */
  public ColorRGB[] calculateFaceLighting(Light lightsource) {
    ColorRGB[] lighting = new ColorRGB[triangles.length];
    for (int i=0; i<triangles.length; i++) {
      // Calculate diffuse reflection of a parallel light source.
      // Make sure no negative light values are returned!
    	
    	Vector3 normal = faceNormals[i];
    	
    	double angle = Vector3.dot(normal, lightsource.direction);

    	double r = faceColors[i].r + lightsource.color.r*angle;
    	double g = faceColors[i].g + lightsource.color.g*angle;
    	double b = faceColors[i].b + lightsource.color.b*angle;
    	
    	lighting[i] = new ColorRGB( r, g, b);
    }
    return lighting;
  }

/**
  * Normalize the homogeneous coordinate for the vertices
  * (i.e. perform a perspective division)
  */
  public void normalizeTransformedVertices() {
    for (int i=0; i<tVertices.length; i++) {
      tVertices[i].normalizeW();
    }
  }

/**
  * Render the mesh as a wireframe plot, using the graphics context
  * of <code>g2</code> for the color, line width and line style.
  * @param g2 The graphics object to render into
  */
  public void renderWire(Graphics2D g2) {
    GeneralPath p = new GeneralPath();
    for(int i=0; i<triangles.length; i++) {
	if(triangles[i] != null) { // Don't try to draw nonexistent triangles
        p.moveTo((float)tVertices[triangles[i].p1].x,(float)tVertices[triangles[i].p1].y);
        p.lineTo((float)tVertices[triangles[i].p2].x,(float)tVertices[triangles[i].p2].y);
        p.lineTo((float)tVertices[triangles[i].p3].x,(float)tVertices[triangles[i].p3].y);
        p.closePath();
      }
    }
    g2.draw(p); // Render all triangles in one blow since they are all the same color
  }

/**
  * Render the mesh as a wireframe plot, using the graphics context
  * of <code>g2</code> for the color, line width and line style, and
  * draw only the triangles flagged as visible in <code>faceVisibility[]</code>.
  * @param g2 The graphics object to render into
  */
  public void renderWireCulled(Graphics2D g2) {
    // Render visible triangles as a wireframe - very similar to renderWire().
	calculateFaceNormals();
	calculateFaceVisibility(); 
	  
    GeneralPath p = new GeneralPath();
    for(int i=0; i<triangles.length; i++) {
	if(triangles[i] != null) { // Don't try to draw nonexistent triangles
		if (faceVisibility[i])
		{
	        p.moveTo((float)tVertices[triangles[i].p1].x,(float)tVertices[triangles[i].p1].y);
	        p.lineTo((float)tVertices[triangles[i].p2].x,(float)tVertices[triangles[i].p2].y);
	        p.lineTo((float)tVertices[triangles[i].p3].x,(float)tVertices[triangles[i].p3].y);
	        p.closePath();
		}
      }
    }
    g2.draw(p); // Render all triangles in one blow since they are all the same color
	  
  }

/**
  * Render the mesh as a flat shaded plot, using the graphics context
  * of <code>g2</code> for the face color, and
  * draw only the triangles flagged as visible in <code>faceVisibility[]</code>.
  * @param g2 The graphics object to render into
  */
  public void renderFaceCulled(Graphics2D g2) {
    // Very similar to renderWireCulled().
	calculateFaceNormals();
	calculateFaceVisibility(); 

	GeneralPath p = new GeneralPath();
	for(int i=0; i<triangles.length; i++) {
	if(triangles[i] != null) { // Don't try to draw nonexistent triangles
		if (faceVisibility[i])
		{
	        p.moveTo((float)tVertices[triangles[i].p1].x,(float)tVertices[triangles[i].p1].y);
	        p.lineTo((float)tVertices[triangles[i].p2].x,(float)tVertices[triangles[i].p2].y);
	        p.lineTo((float)tVertices[triangles[i].p3].x,(float)tVertices[triangles[i].p3].y);
	        p.closePath();
		}
	  }
	}
	
	Paint paint = g2.getPaint();
	g2.setPaint(Color.blue);
	g2.fill(p);
	
	g2.setPaint(paint);
	g2.draw(p); // Render all triangles in one blow since they are all the same color
  }

/**
  * Render the mesh as a flat shaded plot, using <code>faceColors[]</code>
  * to set the color of each face, and draw only the triangles flagged as
  * visible in <code>faceVisibility[]</code>.
  * @param g2 The graphics object to render into
  */
  public void renderFaceColorCulled(Graphics2D g2) {
    // Very similar to the other rendering functions, but now we can have
    // different colors, so we need to render each triangle separately.
    // Very similar to renderWireCulled().
	calculateFaceNormals();
	calculateFaceVisibility(); 
	Paint paint = g2.getPaint();
	
	for(int i=0; i<triangles.length; i++) {
	if(triangles[i] != null) { // Don't try to draw nonexistent triangles
		if (faceVisibility[i])
		{
			GeneralPath p = new GeneralPath();
	        p.moveTo((float)tVertices[triangles[i].p1].x,(float)tVertices[triangles[i].p1].y);
	        p.lineTo((float)tVertices[triangles[i].p2].x,(float)tVertices[triangles[i].p2].y);
	        p.lineTo((float)tVertices[triangles[i].p3].x,(float)tVertices[triangles[i].p3].y);
	        p.closePath();
	        
	    	g2.setPaint(new Color((float)faceColors[i].r, (float)faceColors[i].g, (float)faceColors[i].b) );
	    	g2.fill(p);
	    	
	    	g2.setPaint(paint);
	    	g2.draw(p); // Render all triangles in one blow since they are all the same color  
		}
	  }
	}
  }

/**
  * Render the mesh as a flat shaded plot, using <code>faceColors[]</code>
  * multiplied by <code>faceLighting[]</code> to set the
  * color of each face, and draw only the triangles flagged as
  * visible in <code>faceVisibility[]</code>.
  * @param g2 The graphics object to render into
  */
  public void renderFaceShadedCulled(Graphics2D g2, Light[] lightSources) {
    // Similar to renderFaceColorCulled(), but the color for each triangle
    // is now dependent on both faceColors and faceLighting.
	calculateFaceNormals();
	calculateFaceVisibility();

	// reserve space for final shades
	ColorRGB totalLightColors[] = new ColorRGB[triangles.length];
	for(int i=0; i< totalLightColors.length; i++)
	{	
		totalLightColors[i] = new ColorRGB();
	}
	
	// add shades together
	for(int i=0; i< lightSources.length; i++)
	{
		ColorRGB lightColors[] = calculateFaceLighting(lightSources[i]);
		
		for(int j=0; j < lightColors.length; j++)
		{
			totalLightColors[j] = totalLightColors[j].add(lightColors[j]);
		}
	}
	
	// make sure we don't go overboard with the colors
	for(int i=0; i< totalLightColors.length; i++)
	{	
		totalLightColors[i].r = (totalLightColors[i].r < 1.0) ? totalLightColors[i].r : 1.0;
		totalLightColors[i].g = (totalLightColors[i].g < 1.0) ? totalLightColors[i].g : 1.0;
		totalLightColors[i].b = (totalLightColors[i].b < 1.0) ? totalLightColors[i].b : 1.0;
    	
		totalLightColors[i].r = (totalLightColors[i].r > 0.0) ? totalLightColors[i].r : 0.0;
		totalLightColors[i].g = (totalLightColors[i].g > 0.0) ? totalLightColors[i].g : 0.0;
		totalLightColors[i].b = (totalLightColors[i].b > 0.0) ? totalLightColors[i].b : 0.0;
	}
	
	Paint paint = g2.getPaint();
	
	for(int i=0; i<triangles.length; i++) {
	if(triangles[i] != null) { // Don't try to draw nonexistent triangles
		if (faceVisibility[i])
		{
			GeneralPath p = new GeneralPath();
	        p.moveTo((float)tVertices[triangles[i].p1].x,(float)tVertices[triangles[i].p1].y);
	        p.lineTo((float)tVertices[triangles[i].p2].x,(float)tVertices[triangles[i].p2].y);
	        p.lineTo((float)tVertices[triangles[i].p3].x,(float)tVertices[triangles[i].p3].y);
	        p.closePath();
	        
	    	g2.setPaint(new Color((float)totalLightColors[i].r, (float)totalLightColors[i].g, (float)totalLightColors[i].b) );
	    	g2.fill(p);
	    	
	    	g2.setPaint(paint);
	    	g2.draw(p); // Render all triangles in one blow since they are all the same color  
		}
	  }
	}
  }
}
