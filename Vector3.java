/* Vector3.java 1.01 2007-03-16
 * Stefan Gustavson, ITN-LiTH 2007 (stegu@itn.liu.se)
 */

/** A three-component vector class for TNM046 lab exercises.
  * @version 2007-03-16
  * @author Stefan Gustavson (stegu@itn.liu.se)
  * @see Mesh
  */

public class Vector3 {

/**
  * The x, y and z components.
  */
  public double x,y,z;

/**
  * Default constructor, yields zero vector (x=y=z=0.0)
  */
  public Vector3() {
    x=0.0; y=0.0; z=0.0;
  }

/**
  * Constructor to create any vector from three component values.
  * @param x The x component
  * @param y The y component
  * @param z The z component
  */
  public Vector3(double x, double y, double z) {
    this.x=x; this.y=y; this.z=z;
  }

/**
  * Normalize the vector to unit length
  */
  public void normalize() {
	  double length = Math.sqrt(x*x+y*y+z*z);
	  x /= length;
	  y /= length;
	  z /= length;
    // Normalize x, y, z to set vector to unit length
  }

/**
  * Cross (vector) product of two vectors
  * @param v2 The second factor for the cross product
  * @return The cross product vector
  */
  public Vector3 cross(Vector3 v2) {
    return cross(this, v2);
  }

/**
  * Cross (vector) product of two vectors
  * @param v1 The first factor for the cross product
  * @param v2 The second factor for the cross product
  * @return The cross product vector
  */
  public static Vector3 cross(Vector3 v1, Vector3 v2) {
  	Vector3 v3 = new Vector3();
  	
    // Create the cross product here
    v3.x = v1.y*v2.z - v1.z*v2.y;
    v3.y = v1.z*v2.x - v1.x*v2.z;
    v3.z = v1.x*v2.y - v1.y*v2.x;
    
    return v3;
  }

/**
  * Dot (scalar) product of two vectors
  * @param v2 The second factor for the dot product
  * @return The dot product value
  */
  public double dot(Vector3 v2) {
    return dot(this, v2);
  }

/**
  * Dot (scalar) product of two vectors
  * @param v1 The first factor for the dot product
  * @param v2 The second factor for the dot product
  * @return The dot product value
  */
  public static double dot(Vector3 v1, Vector3 v2) {
	  
    // Create the dot product here
	double dotProduct = v1.x*v2.x+v1.y*v2.y+v1.z+v2.z;  
	  
    return dotProduct;
  }

}
