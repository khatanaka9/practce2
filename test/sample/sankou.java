package sample;

import org.junit.*;

import play.test.*;

public class sankou extends UnitTest {
	// TODO hatanaka クラス名は大文字

	@Test
	public void Sankou() {

		// TODO hatanaka プリミティブ型は使わない(boolean以外)
		// TODO hatanaka intフィールド片方だけfinalがついててもう片方がfinalがついてない理由はなぜでしょう
		// -> finalの意味がわからなければ調査すること
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
