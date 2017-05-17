package sample;

import static org.hamcrest.CoreMatchers.*;

import java.util.List;
import java.util.stream.Collectors;

import models.user.User;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class filtermapTest extends UnitTest {

	@Before
	public void setUp() {
		Fixtures.deleteDatabase();

	}

	// filterとmap
	@Test
	public void filtermap() {

		// 絞り込んだ後、更新する
		new User("Hatanaka", "p@ssw0rd").save();
		new User("hharita", "p@ssw0rd").save();
		new User("horimoto", "p@ssw0rd").save();
		new User("watanabe", "p@ssw0rd").save();
		final List<User> userList = User.findAll();

		userList.stream().map(user -> user)
				.forEach(user -> System.out.println(userList));

		final List<String> filtermap = userList.stream()
				.filter(user -> (user.name.equals("Hatanaka")))
				.map(user -> "Ken" + user.name).collect(Collectors.toList());

		// 1件のみのはず
		// ユーザー名が"Hatanaka"のもののみ取り出し、"KenHatanaka"に変更されているはず
		assertThat(filtermap.size(), is(1));
		assertThat(filtermap.get(0), is("KenHatanaka"));

	}
}
