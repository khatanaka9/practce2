package models.user.repo;

import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import models.user.User;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class UserRepoTest extends UnitTest{

	@Before
	public void setUp() {
	  Fixtures.deleteDatabase();
      new User("hharita", "p@ssw0rd").save();
      new User("hoge", "p@ssw0rd").save();
	}

	@Test
	public void test() {

		final List<User> user = UserRepo.findPass("p@ssw0rd");

		// 2件存在するはず
		assertThat(user.size(), is(2));
	}


}
