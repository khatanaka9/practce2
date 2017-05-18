package sample;

import static org.hamcrest.CoreMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import models.user.User;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class StreamLimitTest extends UnitTest{

	@Before
	public void setYp(){
		Fixtures.deleteDatabase();
	}

	//Limit
	@Test

	public void limitTest(){

		new User("Hatanaka", "p@ssw0rd").save();
		final List<String> list = Arrays.asList("A","BB","CCC","DDDD","EEEEE","FFFFFF");

		List<String> limit = list.stream().limit(4).collect(Collectors.toList());


		//4件存在していること
		System.out.println(limit);
		assertThat(limit.size(), is(4));

	}
}
