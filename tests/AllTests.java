package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ testComMaths.class, TestLineData.class, TestPoint3D.class,
		TestPolygon3D.class, TestRenderer3D.class, TestZBuffer.class })
public class AllTests {

}
