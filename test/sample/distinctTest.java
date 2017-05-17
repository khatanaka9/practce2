package sample;

import static org.hamcrest.CoreMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class distinctTest extends UnitTest {

	@Before
	public void setUp() {
		Fixtures.deleteDatabase();

	}

	// distinct
	@Test
	public void distinctTest() {

		// 重複しているものをまとめる

		final List<String> list = Arrays.asList("AAA", "AAA", "DDD", "AAA");

		final List<String> distinct = list.stream().distinct()
				.collect(Collectors.toList());

		// 4件の存在するはず
		assertThat(distinct.size(), is(2));

		// TODO hatanaka １件目の取得は.get(0)です。

		// 1件目は"AAA"のはず
		assertThat(distinct.get(0), is("AAA"));
		// 2件目は"DDD"のはず
		assertThat(distinct.get(1), is("DDD"));

	}

}
