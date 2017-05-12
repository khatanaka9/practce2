package models.user.repo;

import models.user.User;

import org.junit.Before;

import play.test.Fixtures;
import play.test.UnitTest;

public class UserRepoTest extends UnitTest{

	@Before
	public void setUp() {
	  Fixtures.deleteDatabase();
      new User("hharita", "p@ssw0rd").save();
      new User("hoge", "p@ssw0rd").save();
	}
}
