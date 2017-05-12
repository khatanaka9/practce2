package sample;

import static org.hamcrest.CoreMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import models.user.User;
import models.user.collection.UsersCollection;
import models.user.types.UserType;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class StreamApiTest extends UnitTest{

	@Before
	public void setUp() {
	  Fixtures.deleteDatabase();

	}

	//ユーザー名がhharitaのものだけ
	@Test
	public void filterTest(){
		new User("hharita", "p@ssw0rd").save();
	      new User("hoge", "p@ssw0rd").save();
		UsersCollection userCollection = new UsersCollection(User.findAll());


		UsersCollection collect = userCollection.filterByName("hharita");
//		userList.stream().map(user -> user.name).forEach(name -> System.out.println(name));
		assertThat(collect.size(), is(1));
		//Optional.empty
		assertThat(collect.get(0).map(user -> user.name.equals("hharita")).orElse(false) ,is(true));
	}

	//
	@Test
	public void mapTest(){
	      new User("hatanaka", "p@ssw0rd").save();
	      new User("hharita", "p@ssw0rd").save();
	      new User("horimoto", "p@ssw0rd").save();
	      new User("watanabe", "p@ssw0rd").save();



	      UsersCollection userCollection = new UsersCollection(User.findAll());
	      List<String> map = userCollection.mapToUserName();
	      assertThat(map.size(), is(4));
	      assertThat(map.get(0) ,is("hatanaka"));
	      assertThat(map.get(1) ,is("hharita"));


	}

	@Test
	public void filtermap(){



		new User("Hatanaka","p@ssw0rd").save();
		List<User> userList = User.findAll();

		userList.stream().map(user -> user).forEach(user -> System.out.println(userList));

		List<String> filtermap = userList.stream().filter(user -> ( user.name.equals("Hatanaka"))).map(user -> "Ken"+user.name).collect(Collectors.toList());

		assertThat(filtermap.size(), is(1));
		assertThat(filtermap.get(0) ,is("KenHatanaka"));



	}


//	@Test(expected = RuntimeException.class) エラーの場合にテストが通る
/*
	@Test
	public void flatMapTest(){
	      Users user1 = new Users("Hatanaka", "p@ssw0rd").save();
	      Users user2 = new Users("Harita", "p@ssw0rd").save();

	      Project nullProject = null;

	      Project project1  =  new Project(user1, "hoge1").save();
	      Project project2  =  new Project(user1, "hoge2").save();


	    //Listの場合
	      UsersCollection userCollection = new UsersCollection(Users.findAll());

	      List<Project> projectList = userCollection.list().stream().flatMap(user -> ProjectRepo.findPass(user).stream()).collect(Collectors.toList());

	      //optional<Optional<Object>>の場合
	      Optional<Project> optProject = Optional.ofNullable(nullProject);
	      Optional<String> map = optProject.flatMap(pr -> {
	    	 return Optional.ofNullable(pr.projectName);
	      });

	      /** optional覚えておくこと */

	      //nullじゃない場合のみ実行する
/*
	      Users userList = optProject.map(pr -> pr.users).orElse(null);

	}

	@Test(expected = RuntimeException.class)
	public void flatMapTest2(){
	      Users user1 = new Users("Hatanaka", "p@ssw0rd").save();
	      Users user2 = new Users("Harita", "p@ssw0rd").save();

	      Project nullProject = null;

	      Project project1  =  new Project(user1, "hoge1").save();
	      Project project2  =  new Project(user1, "hoge2").save();


	    //Listの場合
	      List<Users> usersList = Users.findAll();
	      List<Project> projectList = usersList.stream().flatMap(user -> ProjectRepo.findPass(user).stream()).collect(Collectors.toList());

	      //optional<Optional<Object>>の場合
	      Optional<Project> optProject = Optional.ofNullable(nullProject);
	      Optional<String> map = optProject.flatMap(pr -> {
	    	 return Optional.ofNullable(pr.projectName);
	      });

	      /** optional覚えておくこと */

	      //nullじゃない場合のみ実行する
	/*
	      Users userList = optProject.map(pr -> pr.users).orElse(null);
	      //nullの時はエラー吐き出し
	      Users userList1 = optProject.map(pr -> pr.users).orElseThrow(()->new RuntimeException("projectがnullです"));

	}*/


	@Test
	public void distinctTest(){
		new User("AAA", "p@ssw0rd").save();
		new User("AAA", "p@ssw0rd").save();
		new User("DDD", "p@ssw0rd").save();
		new User("AAA", "p@ssw0rd").save();
		List<Integer> list = Arrays.asList(100,200,100,300);

		list.stream().distinct().forEach(System.out::print);

		List<User> userList = User.findAll();
		List<User> distinct = userList.stream().distinct().collect(Collectors.toList());


		assertThat(distinct.get(4).name, is("DDD"));


	}

	@Test
	public void sortTest(){

		new User("HatanakaA", "p@ssw0rd").save();
		new User("HatanakaC", "p@ssw0rd").save();
		new User("HatanakaB", "p@ssw0rd").save();

		UsersCollection userCollection = new UsersCollection(User.findAll());
		//Comparater.comparingInt等もあるので確認を。。
		System.out.println(userCollection.get(0).get().name);

		UsersCollection sortedAscCollection = userCollection.sortedByUserNameAsc();
		assertThat(sortedAscCollection.get(0).map
				(user -> user.name.equals("HatanakaA")).orElse(false), is(true));

		UsersCollection sortedDescCollection = userCollection.sortedByUserNameDesc();
		assertThat(sortedDescCollection.get(0).map(user -> user.name.equals("HatanakaC")).orElse(false), is(true));

	}

	@Test
	public void anyMatchTest(){

		new User("Hatanaka", "p@ssw0rd").save();
		new User("nakamura", "p@ssw0rd").save();

		UsersCollection userCollection = new UsersCollection(User.findAll());
		boolean anyMatch =userCollection.isAnyMatchByUserName();

		assertThat(anyMatch, is(false));

	}

	@Test
	public void allMatchTest(){

		new User("Hatanaka", "p@ssw0rd").save();
		new User("nakamura", "p@ssw0rd").save();

		UsersCollection userCollection = new UsersCollection(User.findAll());
		boolean allMatch =userCollection.isAnyMatchByUserName();

		System.out.println(allMatch);

		assertThat(allMatch, is(false));

	}

	@Test
	public void forEachTest(){

		new User("HatanakaA", "p@ssw0rd").save();
		new User("HatanakaC", "p@ssw0rd").save();
		new User("HatanakaB", "p@ssw0rd").save();
		new User("Hatanaka3", "p@ssw0rd").save();
		new User("Hatanaka1", "p@ssw0rd").save();
		new User("Hatanaka8", "p@ssw0rd").save();

		List<User> userList = User.findAll();


		userList.stream().forEach((name) -> System.out.println(name));

	}

	@Test
	public void enumTest(){
		new User("hharitaAdmin", "p@ssw0rd", UserType.管理者).save();
		new User("hharita", "p@ssw0rd", UserType.利用者).save();

		UsersCollection userCollection = new UsersCollection(User.findAll());
		UsersCollection adminUserCollection = userCollection.filterByUserType(UserType.管理者);
		assertThat(adminUserCollection.get(0).map(user -> user.userType().ediTable.equals(true)).orElse(false), is(true));
		assertThat(adminUserCollection.size(), is(1));
	}

}
