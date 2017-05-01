/**
 * This class defines a set of JUnit test cases built for the com.maths module.  
 * 
 * @author okerberg
 * @version 1.0
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.main.Visualizer;
import com.maths.*;

public class testComMaths {

	
	
	// Global variables.
	com.maths.Calculator c1;
	com.maths.Calculator c2;
	com.maths.ParseFunction p1;
	com.maths.MathTree mt1;
	
	
	/**
	 * setUpBeforeClass - Ran before tests in this class
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	
	
	
	/**
	 * tearDownAfterClass - Ran after all tests in this class.
	 * 
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	
	
	/**
	 * setUp - Ran before each test case in this class.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		c1 = new com.maths.Calculator(800, 500);
		c1.setY0(250);
		c1.setX0(250);
		c1.setRecalculate(true);
		c1.a = 0;
		c1.b = 6.29;
		c1.DISPLAYED_FUNCTION="sin(x)";
		c2 = new com.maths.Calculator(800, 500);
		p1 = new ParseFunction();
		mt1 = new MathTree();
	}

	
	
	
	/**
	 * tearDown - ran after each test case in this class.
	 * 
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		c1 = null;
		c2 = null;
		p1 = null;
	}



	
	/**
	 * Test the com.maths.Calculator.java getA() method.
	 * 
	 */
	@Test
	public final void testCalculatorGetA() {
		// c1 and c2 'a' value should be equal
		assertEquals("should be equal", c2.getA(), c1.getA(), 0.001);

	}
	
	
	
	
	/**
	 * Test the com.maths.Calculator.java getB() method.
	 * 
	 */
	@Test
	public final void testCalculatorGetB() {
		// c1 and c2 'b' value should be equal
		assertEquals("should be equal", c2.getB(), c1.getB(), 0.001);

	}
	
	
	
	
	/**
	 * Test the com.maths.Calculator.java getX0() method, from Renderer3D.
	 * 
	 */
	@Test
	public final void testCalculatorGetX0() {
		// c1 and c2 'X0' int value should be equal
		assertEquals("should be equal", c2.getX0(), c1.getX0());
	}
	
	
	
	
	/**
	 * Test the com.maths.Calculator.java getY0() method, from Renderer3D.
	 * 
	 */
	@Test
	public final void testCalculatorGetY0() {
		// c1 and c2 'Y0' int value should be equal
		assertEquals("should be equal", c2.getY0(), c1.getY0());
	}
	
	
	
	
	/**
	 * Test the com.maths.ParseFunction.java parseFunction method.
	 * 
	 */
	@Test
	public final void testParseFunction() {
		// -2*4+1 should parse to double "-7.0".  
		p1.setFunction("-2*4+1");
		assertEquals("parseFunction result should match", -7, p1.parseFunction(), 0.001);
	}
	
	
	
	
	/**
	 * Test the com.maths.MathTree.java Evaluate method.
	 * 
	 */
	@Test
	public final void testMathTreeEvaluate() {
		// -(2*6)^2 should be equal to double -144.0
		mt1.buildStringTree("-(2*6)^2");
		assertEquals("should be equal", -144.0, mt1.evaluate(2,1), 0.001);
	}

	
}
