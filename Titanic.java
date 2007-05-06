/**
 * 
 */

/**
 * @author wfrishert
 *
 */
public class Titanic extends Mesh
{
	public Titanic()
	{
		vertices = new Vertex4[9];
				
		vertices[0] = new Vertex4(-4,2,2);
		vertices[1] = new Vertex4(-3,1,0);
		vertices[2] = new Vertex4(-3,-1,0);
		vertices[3] = new Vertex4(-4,-2,2);
		vertices[4] = new Vertex4(3,2,2);
		vertices[5] = new Vertex4(3,1,0);
		vertices[6] = new Vertex4(3,-1,0);
		vertices[7] = new Vertex4(3,-2,2);
		vertices[8] = new Vertex4(5,0,2);

		triangles = new Triangle[14];
		
		triangles[0] = new Triangle(1,2,0);
		triangles[1] = new Triangle(2,3,0);
		triangles[2] = new Triangle(4,1,0);
		triangles[3] = new Triangle(4,5,1);
		triangles[4] = new Triangle(5,6,1);
		triangles[5] = new Triangle(6,2,1);
		triangles[6] = new Triangle(6,3,2);
		triangles[7] = new Triangle(6,7,3);
		triangles[8] = new Triangle(8,5,4);
		triangles[9] = new Triangle(8,6,5);
		triangles[10] = new Triangle(8,7,6);
		triangles[11] = new Triangle(3,4,0);
		triangles[12] = new Triangle(7,4,3);
		triangles[13] = new Triangle(7,8,4);
	}
}
