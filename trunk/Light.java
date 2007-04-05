/* Light.java 1.01 2007-03-16
 * Stefan Gustavson, ITN-LiTH 2007 (stegu@itn.liu.se)
 */

/** A simple light source object for TNM046 lab exercises.
  * @version 2007-03-16
  * @author Stefan Gustavson
  * @see Mesh
  */

public class Light {

/**
  * The direction of the light source
  */
  public Vector3 direction;

/**
  * The color of the light source
  */
  public ColorRGB color;

/**
  * Default constructor, calls default constructors for the instance variables
  */
  public Light() {
    direction = new Vector3();
    color = new ColorRGB();
  }

/**
  * Transform the light source direction by a transformation matrix.
  * Any translational components of the transformation are ignored.
  * @param mtx The transformation matrix
  */
  public void transformDirection(Matrix4 mtx) {
    // Transform using upper 3x3 matrix only - translation is ignored
    direction = mtx.mult(direction);
  }
}
