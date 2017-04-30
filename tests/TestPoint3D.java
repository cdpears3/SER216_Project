package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.Point3D;

public class TestPoint3D {

	@Test
	public void test() {
		// Create required objects
		Point3D p = new Point3D();
		Point3D p1 = new Point3D(1,1,1);
		Point3D p2 = new Point3D(3,3,3);
		Point3D p3 = new Point3D(1,3,5);
		Point3D p4 = new Point3D(2,4,6);
		
		// Test calculateCosin(Point3D, Point3D)
		double cosin = p.calculateCosin(p1, p2);
		assert(cosin == 1);
		
		// Test distance(double, double, double, double, double, double)
		double dist = p.distance(1, 1, 1, 2, 1, 1);
		assert (dist == 1);
		
		// Test calculateCrossProduct(Point3D, Point3D)
		Point3D tcp = new Point3D(-2.0,4.0,-2.0);
		Point3D cp = p.calculateCrossProduct(p3, p4);
		assert(tcp.p_x == cp.p_x);
		assert(tcp.p_y == cp.p_y);
		assert(tcp.p_z == cp.p_z);
	}

}
