/* ColorRGB.java 1.01 2007-03-16
 * Stefan Gustavson, ITN-LiTH 2007 (stegu@itn.liu.se)
 */

/** A very simple color description class for TNM046 lab exercises.
  * @version 2007-03-16
  * @author Stefan Gustavson (stegu@itn.liu.se)
  * @see Mesh
  */

public class ColorRGB {

/**
  * The color R, G, B component values, expressed as floating
  * point values in the range 0.0 to 1.0.
  */
  public double r,g,b;

/**
  * Default constructor, yields black (r=g=b=0.0)
  */
  public ColorRGB() {
    r=0; g=0; b=0;
  }

/**
  * Constructor for an arbitrary color from three component values.
  * @param r The red intensity, 0.0 <= r <= 1.0
  * @param g The green intensity, 0.0 <= g <= 1.0
  * @param b The blue intensity, 0.0 <= b <= 1.0
  */
  public ColorRGB(double r, double g, double b) {
    this.r=r; this.g=g; this.b=b;
  }

/**
  * Multiply each of the color components with a scalar value
  * @param a Scalar value to multiply with the color
  * @return The resulting color
  */
  public ColorRGB mult(double a) {
    return new ColorRGB(r*a, g*a, b*a);
  }

/**
  * Multiply two colors component by component ("subtractive mixing")
  * @param c The second color for the multiplication
  * @return The resulting color
  */
  public ColorRGB mult(ColorRGB c) {
    return new ColorRGB(r*c.r, g*c.g, b*c.b);
  }

/**
  * Multiply two colors component by component ("subtractive mixing")
  * @param c1 The first color for the multiplication
  * @param c2 The second color for the multiplication
  * @return The resulting color
  */
  public static ColorRGB mult(ColorRGB c1, ColorRGB c2) {
    return new ColorRGB(c1.r*c2.r, c1.g*c2.g, c1.b*c2.b);
  }

/**
  * Add two colors component by component ("additive mixing")
  * @param c The second color for the addition
  * @return The resulting color
  */
  public ColorRGB add(ColorRGB c) {
    return new ColorRGB(r+c.r, g+c.g, b+c.b);
  }

/**
  * Add two colors component by component ("additive mixing")
  * @param c1 The first color for the addition
  * @param c2 The second color for the addition
  * @return The resulting color
  */
  public static ColorRGB add(ColorRGB c1, ColorRGB c2) {
  // Add two RGB colors component-wise
    return new ColorRGB(c1.r+c2.r, c1.g+c2.g, c1.b+c2.b);
  }

}
