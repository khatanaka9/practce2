package sample;

import static org.hamcrest.CoreMatchers.*;
import models.user.User;
import models.user.collection.UsersCollection;
import models.user.types.UserType;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class enumTest extends UnitTest {

	@Before
	public void setUp() {
		Fixtures.deleteDatabase();

	}

	@Test
	public void enumTest() {

		new User("hharitaAdmin", "p@ssw0rd", UserType.管理者).save();
		new User("hharita", "p@ssw0rd", UserType.利用者).save();

		final UsersCollection userCollection = new UsersCollection(
				User.findAll());
		final UsersCollection adminUserCollection = userCollection
				.filterByUserType(UserType.管理者);
		assertThat(
				adminUserCollection.get(0)
						.map(user -> user.userType().ediTable.equals(true))
						.orElse(false), is(true));
		// 1件のみのはず
		assertThat(adminUserCollection.size(), is(1));
	}
}
