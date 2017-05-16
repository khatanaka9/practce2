package sample;

import static org.hamcrest.CoreMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import models.user.User;

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
		new User("AAA", "p@ssw0rd").save();
		new User("AAA", "p@ssw0rd").save();
		new User("DDD", "p@ssw0rd").save();
		new User("AAA", "p@ssw0rd").save();
		final List<Integer> list = Arrays.asList(100, 200, 100, 300);

		list.stream().distinct().forEach(System.out::print);

		final List<User> userList = User.findAll();
		final List<User> distinct = userList.stream().distinct()
				.collect(Collectors.toList());

		// 4件の存在するはず
		assertThat(distinct.size(), is(4));
		// 1件目は"AAA"のはず
		assertThat(distinct.get(1).name, is("AAA"));
		// 2件目は"DDD"のはず
		assertThat(distinct.get(2).name, is("DDD"));

	}

}
