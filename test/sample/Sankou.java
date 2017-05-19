package sample;

import org.junit.*;

import play.test.*;

public class Sankou extends UnitTest {

	@Test
	public void Sankou() {

		final Integer x;
		final Integer y;
		final Character z;

		x = 0;
		y = 10;
		z = x < y ? '真' : '偽';
		// z=真になるはず
		System.out.println(z);
	}

}
