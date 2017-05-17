package sample;

import static org.hamcrest.CoreMatchers.*;
import models.user.User;
import models.user.collection.UsersCollection;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class AllMatchTest extends UnitTest {
	// allMatch

	@Before
	public void setUp() {
		Fixtures.deleteDatabase();

	}
	@Test
	public void allMatchTest() {

		// すべての条件に合致する
		new User("Hatanaka", "p@ssw0rd").save();
		new User("horimoto", "p@ssw0rd").save();
		new User("watanabe", "p@ssw0rd").save();

		final UsersCollection userCollection = new UsersCollection(
				User.findAll());
		final boolean allMatch = userCollection.isAllMatchByUserName();

		// 名前の文字数がすべて8文字であること
		assertThat(allMatch, is(true));

	}

}
