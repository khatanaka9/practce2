package sample;

import java.util.List;

import models.user.User;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class forEachTest extends UnitTest {

	@Before
	public void setUp() {
		Fixtures.deleteDatabase();

	}

	// forEach
	@Test
	public void forEachTest() {

		new User("HatanakaA", "p@ssw0rd").save();
		new User("HatanakaC", "p@ssw0rd").save();
		new User("HatanakaB", "p@ssw0rd").save();
		new User("Hatanaka3", "p@ssw0rd").save();
		new User("Hatanaka1", "p@ssw0rd").save();
		new User("Hatanaka8", "p@ssw0rd").save();

		final List<User> userList = User.findAll();

		//6件が表示される
		userList.stream().forEach((name) -> System.out.println(name));

	}

}
