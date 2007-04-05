/* Triangle.java 1.01 2007-03-16
 * Stefan Gustavson, ITN-LiTH 2007 (stegu@itn.liu.se)
 */

/** A triangle polygon class for TNM046 lab exercises.
  * @version 2007-03-16
  * @author Stefan Gustavson (stegu@itn.liu.se)
  * @see Mesh
  */

public class Triangle {

/**
  * The triangle corners, in counter-clockwise order viewed from the front side.
  */
  public int p1,p2,p3;

/**
  * Default constructor, yields an invalid triangle with p1=p2=p3= -1
  */
  public Triangle() {
    p1=-1; p2=-1; p3=-1;
  }

/**
  * Constructor to create a triangle from three vertex indices
  * @param p1 The first vertex index
  * @param p2 The second vertex index
  * @param p3 The third vertex index
  */
  public Triangle(int p1, int p2, int p3) {
    this.p1=p1; this.p2=p2; this.p3=p3;
  }

/**
  * Reverse the vertex order of the triangle
  */
  public void reverse() {
    int p=p1; p1=p2; p2=p;
  }
}
