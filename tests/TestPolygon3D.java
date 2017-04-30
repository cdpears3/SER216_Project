package tests;

import static org.junit.Assert.*;
import java.awt.geom.Area;
import org.junit.Test;
import com.Polygon3D;
import com.Point3D;

public class TestPolygon3D {
	
	@Test
	public void test()
	{
		// Create required objects
		int points = 4;
		int[] pointsX = {2,4,6,8};
		int[] pointsY = {1,2,3,4};
		int[] pointsZ = {1,3,5,7};
		
		Point3D[] pointArray = new Point3D[points];
		for(int i = 0; i < points; i++)
		{
			pointArray[i] = new Point3D(pointsX[i],pointsY[i], pointsZ[i]);
		}
		
		Polygon3D p3d = new Polygon3D(points, pointsX, pointsY, pointsZ, pointArray);		

		// Test divideIntoTriangle(Polygon3D)
		Polygon3D[] polyArray = p3d.divideIntoTriangles(p3d); 
			
			// should create 2 triangles
			assert(polyArray.length == 2);
		
			// Create 2 x, y, and z int arrays based on arrays above
			int[] mX1 = {2,4,6}; int[] mX2 = {2,6,8};
			int[] mY1 = {1,2,3}; int[] mY2 = {1,3,4};
			int[] mZ1 = {1,3,5}; int[] mZ2 = {1,5,7};
				
			// Create 2 polygons to represent 2 triangles created
			Polygon3D t1 = new Polygon3D(3, mX1, mY1, mZ1);
			Polygon3D t2 = new Polygon3D(3, mX2, mY2, mZ2);
			
			// Compare polyArray triangles to new triangles
			assert(t1.toString().equals(polyArray[0].toString()));
			assert(t2.toString().equals(polyArray[1].toString()));
		
		// Test extractSubPolygon3D(Polygon3D, int, int)
		Polygon3D extracted = p3d.extractSubPolygon3D(p3d ,3 ,1);
			
			// create int arrays to be used for test polygon which 
			// use the last 3 elements of original int arrays
			int[] eX = {4,6,8}; int[] eY = {2,3,4}; int[] eZ = {3,5,7};
			Polygon3D testExtracted = new Polygon3D(3, eX, eY, eZ);
			
			// Compare extracted polygon to test polygon
			assert(extracted.toString().equals(testExtracted.toString()));
	}
}
