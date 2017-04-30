package tests;

import java.awt.Color;

import org.junit.Test;
import com.ZBuffer;

public class TestZBuffer {
	
	@Test
	public void test()
	{
		ZBuffer z = new ZBuffer();
		Color color = new Color(1193046);
		assert(z.fromColorToHex(color).equals("123456"));
	}
}
