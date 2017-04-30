package tests;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import com.LineData;
import com.Point3D;
import com.Polygon3D;

public class TestLineData {
	
	@Test
	public void test()
	{
		// Create required objects
		LineData linedata = new LineData(4,3,2,1);
		Point3D p1 = new Point3D(1,2,3);
		Point3D p2 = new Point3D(4,5,6);
		Point3D p3 = new Point3D(7,8,9);
		Point3D p4 = new Point3D(0,0,0);
		Point3D p5 = new Point3D(10,20,30);
		Point3D p6 = new Point3D(5,10,15);
		Point3D p7 = new Point3D(2,4,6);
		Vector<Point3D> vector = new Vector();
		vector.add(p1); vector.add(p2); vector.add(p3); vector.add(p4);	vector.add(p5); vector.add(p6); vector.add(p7);
		
		// Test buildPolygon
		Polygon3D p3d = linedata.buildPolygon(linedata, vector); // Construct Polygon3D object using buildPolygon
		
		// Set mock arrays for testing Polygon3D point arrays
		int[] mockX = {10, 0, 7, 4};
		int[] mockY = {20, 0, 8, 5};
		int[] mockZ = {30, 0, 9, 6};
		
		// ensures buildPolygon is setting values correctly
		assertArrayEquals(p3d.xpoints, mockX); 
		assertArrayEquals(p3d.ypoints, mockY); 
		assertArrayEquals(p3d.zpoints, mockZ); 
	}
}
