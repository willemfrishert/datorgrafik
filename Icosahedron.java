/* Icosahedron.java 1.0 2001-04-17
 * Stefan Gustavson, ITN-LiTH 2001 (stegu@itn.liu.se)
 */

import java.lang.*;
import java.awt.*;
import java.awt.geom.*;

/** A simple test object class for TNM046 lab exercises:
  * a regular icosahedron.
  * @version 2001-04-17
  * @author Stefan Gustavson (stegu@itn.liu.se)
  * @see Mesh
  */
public class Icosahedron extends Mesh {

/**
  * Default constructor, yields a regular icosahedron in a Mesh object
  */
  public Icosahedron() {
    double p=(1.0+Math.sqrt(5.0))/2.0;
    vertices = new Vertex4[12];
    vertices[0]= new Vertex4(1.0, 0.0, p);
    vertices[1]= new Vertex4(-1.0, 0.0, p);
    vertices[2]= new Vertex4(1.0, 0.0, -p);
    vertices[3]= new Vertex4(-1.0, 0.0, -p);
    vertices[4]= new Vertex4(0.0, p, 1.0);
    vertices[5]= new Vertex4(0.0, -p, 1.0);
    vertices[6]= new Vertex4(0.0, p, -1.0);
    vertices[7]= new Vertex4(0.0, -p, -1.0);
    vertices[8]= new Vertex4(p, 1.0, 0.0);
    vertices[9]= new Vertex4(-p, 1.0, 0.0);
    vertices[10]= new Vertex4(p, -1.0, 0.0);
    vertices[11]= new Vertex4(-p, -1.0, 0.0);
    triangles = new Triangle[20];
    triangles[0]= new Triangle(0,4,1);
    triangles[1]= new Triangle(0,1,5);
    triangles[2]= new Triangle(0,5,10);
    triangles[3]= new Triangle(0,10,8);
    triangles[4]= new Triangle(0,8,4);
    triangles[5]= new Triangle(4,8,6);
    triangles[6]= new Triangle(4,6,9);
    triangles[7]= new Triangle(4,9,1);
    triangles[8]= new Triangle(1,9,11);
    triangles[9]= new Triangle(1,11,5);
    triangles[10]= new Triangle(2,7,3);
    triangles[11]= new Triangle(2,3,6);
    triangles[12]= new Triangle(2,6,8);
    triangles[13]= new Triangle(2,8,10);
    triangles[14]= new Triangle(2,10,7);
    triangles[15]= new Triangle(7,10,5);
    triangles[16]= new Triangle(7,5,11);
    triangles[17]= new Triangle(7,11,3);
    triangles[18]= new Triangle(3,11,9);
    triangles[19]= new Triangle(3,9,6);
  }
}
