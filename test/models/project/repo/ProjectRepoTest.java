package models.project.repo;

import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import models.project.Project;
import models.project.types.ProjectType;
import models.user.User;
import models.user.types.UserType;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class ProjectRepoTest extends UnitTest {

	private User user;

	@Before
	public void setup() {
		Fixtures.deleteDatabase();

		user = new User("hatanakaadmin", "p@ssw0rd", UserType.管理者).save();
		new Project(user, "realEstate1", ProjectType.不動産案件).save();
	}

	@Test
	public void test() {

		final List<Project> project = ProjectRepo.findPass(user);

		// 1件のみのはず
		assertThat(project.size(), is(1));
	}


}
