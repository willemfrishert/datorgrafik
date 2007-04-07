/* Matrix4.java 1.01 2007-03-16
 * Stefan Gustavson, ITN-LiTH 2007 (stegu@itn.liu.se)
 */

/** A 4x4-element matrix class for TNM046 lab exercises.
  * @version 2007-03-16
  * @author Stefan Gustavson (stegu@itn.liu.se)
  * @see Mesh
  */
  
import java.math.*;

public class Matrix4
{

/**
  * The matrix coefficients. Element <code>aij</code> is at row i, column j.
  */
  public double a11,a12,a13,a14,
                a21,a22,a23,a24,
                a31,a32,a33,a34,
                a41,a42,a43,a44;

/**
  * Default constructor, yields indentity matrix
  */
  public Matrix4()
  {
    a11=1.0; a12=0.0; a13=0.0; a14=0.0;
    a21=0.0; a22=1.0; a23=0.0; a24=0.0;
    a31=0.0; a32=0.0; a33=1.0; a34=0.0;
    a41=0.0; a42=0.0; a43=0.0; a44=1.0;
  }

/**
  * Matrix copy constructor, copies an existing matrix
  */
  public Matrix4(Matrix4 m)
  {
    a11=m.a11; a12=m.a12; a13=m.a13; a14=m.a14;
    a21=m.a21; a22=m.a22; a23=m.a23; a24=m.a24;
    a31=m.a31; a32=m.a32; a33=m.a33; a34=m.a34;
    a41=m.a41; a42=m.a42; a43=m.a43; a44=m.a44;
  }

/**
  * Factory method to create a transformation matrix for rotation around X
  * @param theta The rotation angle
  * @return A transformation matrix
  */
  public static Matrix4 getRotateXInstance(double theta)
  {
    Matrix4 m = new Matrix4();
    
    // Set appropriate elements here
    m.a22= Math.cos(theta);  m.a23=Math.sin(theta);
    m.a32=-Math.sin(theta);  m.a33=Math.cos(theta);
    
    return m;
  }

/**
  * Factory method to create a transformation matrix for rotation around Y
  * @param theta The rotation angle
  * @return A transformation matrix
  */
  public static Matrix4 getRotateYInstance(double theta)
  {
    Matrix4 m = new Matrix4();
    
    // Set appropriate elements here
    m.a11= Math.cos(theta);  m.a13=-Math.sin(theta);
    m.a31= Math.sin(theta);  m.a33= Math.cos(theta);
    
    return m;
  }

/**
  * Factory method to create a transformation matrix for rotation around Z
  * @param theta The rotation angle
  * @return A transformation matrix
  */
  public static Matrix4 getRotateZInstance(double theta) {
    Matrix4 m = new Matrix4();
    
    // Set appropriate elements here
    m.a11= Math.cos(theta);  m.a12=Math.sin(theta);
    m.a21=-Math.sin(theta);  m.a22=Math.cos(theta);
    
    return m;
  }

/**
  * Factory method to create a transformation matrix for translation
  * @param tx The translation along the x dimension
  * @param ty The translation along the y dimension
  * @param tz The translation along the z dimension
  * @return A transformation matrix
  */
  public static Matrix4 getTranslateInstance(double tx, double ty, double tz) {
    Matrix4 m = new Matrix4();
    m.a14=tx; m.a24=ty; m.a34=tz;    
    return m;
  }

/**
  * Factory method to create a transformation matrix for uniform scaling
  * @param s The scaling factor
  * @return A transformation matrix
  */
  public static Matrix4 getScaleInstance(double s) {
    Matrix4 m = new Matrix4();
    
    // Set appropriate elements here
    m.a11 = s;
    m.a22 = s;
    m.a33 = s;
    
    return m;
  }

/**
  * Factory method to create a transformation matrix for non-uniform scaling
  * @param sx The scaling factor along the x dimension
  * @param sy The scaling factor along the y dimension
  * @param sz The scaling factor along the z dimension
  * @return A transformation matrix
  */
  public static Matrix4 getScaleInstance(double sx, double sy, double sz) {
    Matrix4 m = new Matrix4();
    
    // Set appropriate elements here
    m.a11 = sx;
    m.a22 = sy;
    m.a33 = sz;
    
    return m;
  }

/**
  * Copy the coefficient vaules from another matrix
  * @param m The matrix to copy
  */
  public void set(Matrix4 m) {
    a11=m.a11; a12=m.a12; a13=m.a13; a14=m.a14;
    a21=m.a21; a22=m.a22; a23=m.a23; a24=m.a24;
    a31=m.a31; a32=m.a32; a33=m.a33; a34=m.a34;
    a41=m.a41; a42=m.a42; a43=m.a43; a44=m.a44;
  }

/**
  * Concatenate (right-multiply) with another matrix
  * @param m The matrix to concatenate from the right with this matrix
  */
  public void concat(Matrix4 m) {
    this.set(this.mult(m));
  }

/**
  * Concatenate (left-multiply) with another matrix
  * @param m The matrix to concatenate from the left with this matrix
  */
  public void leftConcat(Matrix4 m) {
    this.set(m.mult(this));
  }

/**
  * Matrix multiply with another matrix
  * @param m2 The second (right) matrix for the multiplication
  * @return The resulting matrix product
  */
  public Matrix4 mult(Matrix4 m2) {
    return Matrix4.mult(this, m2);
  }

/**
  * Multiply matrix with a vertex to transform it.
  * @param v The vertex to transform
  * @return The transformed vertex
  */
  public Vertex4 mult(Vertex4 v) {
    Vertex4 vout = new Vertex4();
    
    // Calculate x,y,z,w values of vout here
    vout.x = (a11*v.x) + (a12*v.y) + (a13*v.z) + (a14*v.w);
    vout.y = (a21*v.x) + (a22*v.y) + (a23*v.z) + (a24*v.w);
    vout.z = (a31*v.x) + (a32*v.y) + (a33*v.z) + (a34*v.w);
    vout.w = (a41*v.x) + (a42*v.y) + (a43*v.z) + (a44*v.w);
    
    return vout;
  }

/**
  * Multiply matrix with a 3-element vector to transform it.
  * Only the upper left 3x3 portion of the matrix is used.
  * (Translations and homogeneous coordinate changes are ignored)
  * @param v The vector to transform
  * @return The transformed vertex
  */
  // Multiply a 3-element vector with the upper left 3x3 portion of the matrix
  public Vector3 mult(Vector3 v) {
    Vector3 vout = new Vector3();
    // Calculate x,y,z values of vout here
    
    vout.x = (a11*v.x) + (a12*v.y) + (a13*v.z);
    vout.y = (a21*v.x) + (a22*v.y) + (a23*v.z);
    vout.z = (a31*v.x) + (a32*v.y) + (a33*v.z);
    return vout;
  }

/**
  * Matrix multiply two matrices
  * @param m1 The first (left) matrix for the multiplication
  * @param m2 The second (right) matrix for the multiplication
  * @return The resulting matrix product
  */
  public static Matrix4 mult(Matrix4 m1, Matrix4 m2) {
    Matrix4 m3 = new Matrix4();
    // An array for a[i,j] and loops would make this code more compact, but slower.
    m3.a11 = m1.a11*m2.a11+m1.a12*m2.a21+m1.a13*m2.a31+m1.a14*m2.a41;
    m3.a12 = m1.a11*m2.a12+m1.a12*m2.a22+m1.a13*m2.a32+m1.a14*m2.a42;
    m3.a13 = m1.a11*m2.a13+m1.a12*m2.a23+m1.a13*m2.a33+m1.a14*m2.a43;
    m3.a14 = m1.a11*m2.a14+m1.a12*m2.a24+m1.a13*m2.a34+m1.a14*m2.a44;

    m3.a21 = m1.a21*m2.a11+m1.a22*m2.a21+m1.a23*m2.a31+m1.a24*m2.a41;
    m3.a22 = m1.a21*m2.a12+m1.a22*m2.a22+m1.a23*m2.a32+m1.a24*m2.a42;
    m3.a23 = m1.a21*m2.a13+m1.a22*m2.a23+m1.a23*m2.a33+m1.a24*m2.a43;
    m3.a24 = m1.a21*m2.a14+m1.a22*m2.a24+m1.a23*m2.a34+m1.a24*m2.a44;

    m3.a31 = m1.a31*m2.a11+m1.a32*m2.a21+m1.a33*m2.a31+m1.a34*m2.a41;
    m3.a32 = m1.a31*m2.a12+m1.a32*m2.a22+m1.a33*m2.a32+m1.a34*m2.a42;
    m3.a33 = m1.a31*m2.a13+m1.a32*m2.a23+m1.a33*m2.a33+m1.a34*m2.a43;
    m3.a34 = m1.a31*m2.a14+m1.a32*m2.a24+m1.a33*m2.a34+m1.a34*m2.a44;

    m3.a41 = m1.a41*m2.a11+m1.a42*m2.a21+m1.a43*m2.a31+m1.a44*m2.a41;
    m3.a42 = m1.a41*m2.a12+m1.a42*m2.a22+m1.a43*m2.a32+m1.a44*m2.a42;
    m3.a43 = m1.a41*m2.a13+m1.a42*m2.a23+m1.a43*m2.a33+m1.a44*m2.a43;
    m3.a44 = m1.a41*m2.a14+m1.a42*m2.a24+m1.a43*m2.a34+m1.a44*m2.a44;

    return m3;
  }
}
