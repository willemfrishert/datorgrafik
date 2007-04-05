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
      // 2) Perform the cross product e1 x e2
      // 3) Normalize the result to yield the face normal vector
    }
  }

/**
  * Calculate face visibility assuming the viewer is in the positive z direction,
  * and store the result in <code>faceVisibility</code>
  */
  public void calculateFaceVisibility() {
    if (faceVisibility == null || faceVisibility.length != triangles.length)
      faceVisibility = new boolean[triangles.length];
    for (int i=0; i<triangles.length; i++) {
      // Determine face visibility from face normal direction
    }
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
  }

/**
  * Render the mesh as a flat shaded plot, using the graphics context
  * of <code>g2</code> for the face color, and
  * draw only the triangles flagged as visible in <code>faceVisibility[]</code>.
  * @param g2 The graphics object to render into
  */
  public void renderFaceCulled(Graphics2D g2) {
    // Very similar to renderWireCulled().
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
  }

/**
  * Render the mesh as a flat shaded plot, using <code>faceColors[]</code>
  * multiplied by <code>faceLighting[]</code> to set the
  * color of each face, and draw only the triangles flagged as
  * visible in <code>faceVisibility[]</code>.
  * @param g2 The graphics object to render into
  */
  public void renderFaceShadedCulled(Graphics2D g2) {
    // Similar to renderFaceColorCulled(), but the color for each triangle
    // is now dependent on both faceColors and faceLighting.
  }

}
