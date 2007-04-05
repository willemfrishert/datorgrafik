/* Vertex4.java 1.01 2007-03-16
 * Stefan Gustavson, ITN-LiTH 2007 (stegu@itn.liu.se)
 */

/** A four-component homogeneous coordinate vertex class for TNM046 lab exercises.
  * @version 2007-03-16
  * @author Stefan Gustavson (stegu@itn.liu.se)
  * @see Mesh
  */

public class Vertex4 {
/**
  * The x, y, z and w components.
  */
  public double x,y,z,w;

/**
  * Default constructor, yields zero vector (x=y=z=0.0, w=1.0)
  */
  public Vertex4() {
    x=0.0; y=0.0; z=0.0; w=1.0;
  }

/**
  * Constructor to create any vertex from four component values
  * @param x The x component
  * @param y The y component
  * @param z The z component
  * @param w The w component
  */
  public Vertex4(double x, double y, double z,  double w) {
    this.x=x; this.y=y; this.z=z; this.w=w;
  }

/**
  * Constructor to create a vertex from x, y, z component values.
  * The w component is set to 1.
  * @param x The x component
  * @param y The y component
  * @param z The z component
  */
  public Vertex4(double x, double y, double z) {
    this.x=x; this.y=y; this.z=z; this.w=1.0;
  }

/**
  * Constructor to create a vertex from x and y component values.
  * The z component is set to 0, and the w component is set to 1.
  * @param x The x component
  * @param y The y component
  */
  public Vertex4(double x, double y) {
    this.x=x; this.y=y; this.z=0.0; this.w=1.0;
  }

/**
  * Normalize the homogeneous coordinates so that w=1
  */
  public void normalizeW() {
    // Normalize the homogeneous coordinates
  }
}
