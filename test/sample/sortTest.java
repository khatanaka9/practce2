package sample;

import static org.hamcrest.CoreMatchers.*;
import models.user.User;
import models.user.collection.UsersCollection;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class sortTest extends UnitTest {

	@Before
	public void setUp() {
		Fixtures.deleteDatabase();

	}

	// sort
	@Test
	public void sortTest() {
		// 昇順、降順にソートする
		new User("HatanakaA", "p@ssw0rd").save();
		new User("HatanakaC", "p@ssw0rd").save();
		new User("HatanakaB", "p@ssw0rd").save();

		final UsersCollection userCollection = new UsersCollection(
				User.findAll());
		// Comparater.comparingInt等もあるので確認を。。


		// ユーザーを基準に昇順にソートする
		final UsersCollection sortedAscCollection = userCollection
				.sortedByUserNameAsc();
		assertThat(
				sortedAscCollection.get(0)
						.map(user -> user.name.equals("HatanakaA"))
						.orElse(false), is(true));

		// ユーザーを基準に降順にソートする
		final UsersCollection sortedDescCollection = userCollection
				.sortedByUserNameDesc();
		assertThat(
				sortedDescCollection.get(0)
						.map(user -> user.name.equals("HatanakaC"))
						.orElse(false), is(true));

	}

}
