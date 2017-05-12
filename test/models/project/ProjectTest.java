package models.project;

import java.util.List;

import models.project.types.ProjectType;
import models.user.User;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class ProjectTest extends UnitTest{

	private List<User> list;
	private User user;


	@Before
	public void setUp() {;
	 Fixtures.deleteDatabase();
	 user = new User("hharita", "test").save();
	 }


	@Test
	public void testConstructor(){
		 Project project1 = new Project(user,"projectName", ProjectType.不動産案件).save();



	}

}
