package models.project;

import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import models.project.types.ProjectType;
import models.user.User;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class ProjectTest extends UnitTest {

	private List<User> list;
	private User user;

	@Before
	public void setUp() {
		;
		Fixtures.deleteDatabase();
		user = new User("hharita", "test").save();
	}

	@Test
	public void testConstructor() {
		final Project project = new Project(user, "案件1", ProjectType.不動産案件)
				.save();

		final List<Project> project1 = project.findAll();

		// 1件のみのはず
		assertThat(project1.size(), is(1));
		// 案件名が案件1のはず
		assertThat(project1.get(0).projectName, is("案件1"));

	}

}
