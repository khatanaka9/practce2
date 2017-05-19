package sample;

import org.junit.*;

import play.test.*;

public class sankou extends UnitTest {

	@Test
	public void Sankou() {

		final int x;
		int y;
		final char z;

		x = 0;
		y = 10;
		z = x < y ? '真' : '偽';
		// z=真になるはず
		System.out.println(z);
	}

}
