package sample;

import static org.hamcrest.CoreMatchers.*;
import models.user.User;
import models.user.collection.UsersCollection;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class AnyMatchTest extends UnitTest {

	@Before
	public void setUp() {
		Fixtures.deleteDatabase();

	}

	// anyMatch
	@Test
	public void anyMatchTest() {
		// 1つでも条件に合致するものがある

		new User("Hatanaka", "p@ssw0rd").save();
		new User("hharita", "p@ssw0rd").save();
		new User("horimoto", "p@ssw0rd").save();
		new User("watanabe", "p@ssw0rd").save();

		final UsersCollection userCollection = new UsersCollection(
				User.findAll());
		final boolean anyMatch = userCollection.isAnyMatchByUserName();

		// 一つでも名前に"Hatanaka"があること
		assertThat(anyMatch, is(true));

	}

}
