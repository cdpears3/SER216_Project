package tests;

import java.awt.geom.Line2D;

import org.junit.Test;
import com.Renderer3D;
import com.Point3D;

public class TestRenderer3D {
	
	@Test
	public void test()
	{
		// Create required objects
		Renderer3D r3d = new Renderer3D();
		Point3D p1 = new Point3D(1,6,3);
		Point3D p2 = new Point3D(9,3,12);
		
		// Test interpolatePhongNormal(Point3D, Point3D, double)
		System.out.println("interpolatePhongNormal Values:");
		System.out.println(r3d.interpolatePhongNormal(p1, p2, 10).toString());
		System.out.println();
		
		// Test newLine(Point3D, Point3D)
		Line2D.Double l2dd = r3d.newLine(p1,p2);
		System.out.println("newLine Values:");
		System.out.println("X1: " + l2dd.getX1());
		System.out.println("X2: " + l2dd.getX2());
		System.out.println("Y1: " + l2dd.getY1());
		System.out.println("Y2: " + l2dd.getY2());
		System.out.println();
		
		// Test getRotationMatrix(Point3D, double)
		double[][] rMatrix = r3d.getRotationMatrix(p1, 1);
		String s = "";
		int count;
		for(int i = 0; i < rMatrix.length; i++)
		{
			count = 0;
			for(int j = 0; j < rMatrix[0].length; j++)
			{
				s += rMatrix[i][j] + " ";
				count++;
				if(count == 2)
					s += "\n";
			}
		}
		System.out.println("getRotationMatrix Values:");
		System.out.println(s);
		System.out.println();
		
		// Test calcAssX(Point3D)
		double d = r3d.calcAssX(p1);
		System.out.println("calcAssX Value:");
		System.out.println(d);
	}
}
