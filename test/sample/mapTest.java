package sample;

import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import models.user.User;
import models.user.collection.UsersCollection;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class mapTest extends UnitTest {

	@Before
	public void setUp() {
		Fixtures.deleteDatabase();

	}

	// map
	@Test
	public void mapTest() {
		// 更新する
		new User("hatanaka", "p@ssw0rd").save();
		new User("hharita", "p@ssw0rd").save();
		new User("horimoto", "p@ssw0rd").save();
		new User("watanabe", "p@ssw0rd").save();

		final UsersCollection userCollection = new UsersCollection(
				User.findAll());

		// TODO hatanaka これなんかおかしいです。

		final List<String> map = userCollection.mapToUserName();

		// 4件存在するはず
		assertThat(map.size(), is(4));
		// ユーザー名全件に[]がついているはず
		map.stream().forEach((name) -> System.out.println(name));

		assertThat(map.get(0), is("[hatanaka]"));
		assertThat(map.get(1), is("[hharita]"));
		assertThat(map.get(2), is("[horimoto]"));
		assertThat(map.get(3), is("[watanabe]"));


	}
}
