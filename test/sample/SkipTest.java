package sample;

import static org.hamcrest.CoreMatchers.*;

import java.util.*;
import java.util.stream.*;

import org.junit.*;

import play.test.*;

public class SkipTest extends UnitTest {

	@Before
	public void setUp() {
		Fixtures.deleteDatabase();
	}

	// skip
	@Test

	public void SkipTest() {

		final List<String> list = Arrays.asList("A", "BB", "CCC", "DDDD", "EEEEE", "FFFFFF");

		final List<String> skip = list.stream().limit(3).collect(Collectors.toList());

		// 先頭から3件飛んでいるはずと
		System.out.println(skip);
		// 3件存在しているはず
		assertThat(skip.size(), is(3));
	}
}
