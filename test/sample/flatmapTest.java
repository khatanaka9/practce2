package sample;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import models.project.Project;
import models.project.repo.ProjectRepo;
import models.user.User;
import models.user.collection.UsersCollection;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class flatmapTest extends UnitTest {

	@Before
	public void setUp() {
		Fixtures.deleteDatabase();

	}

	// flatmap
	// @Test(expected = RuntimeException.class) エラーの場合にテストが通る

	@Test
	public void flatMapTest() {

		Project nullProject = null;
		User user1 = new User("Hatanaka", "p@ssw0rd").save();
		User user2 = new User("Harita", "p@ssw0rd").save();
		final UsersCollection userCollection = new UsersCollection(
				User.findAll());
		System.out.println();

		// Listの場合 UsersCollection userCollection = new
		// UsersCollection(Users.findAll());

		List<Project> projectList = userCollection.list().stream()
				.flatMap(user -> ProjectRepo.findPass(user).stream())
				.collect(Collectors.toList());


//		// optional<Optional<Object>>の場合
		Optional<Project> optProject = Optional.ofNullable(nullProject);
		Optional<String> map = optProject.flatMap(pr -> {
			return Optional.ofNullable(pr.projectName); });

//		//optional覚えておくこと

		//nullじゃない場合のみ実行する
		User userList = (User) optProject.map(pr -> pr.users).orElse(null);


		System.out.println(userList);


	}
}