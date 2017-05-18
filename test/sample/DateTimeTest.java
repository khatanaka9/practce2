package sample;

import java.time.LocalDateTime;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class DateTimeTest extends UnitTest{

	@Before
	public void setUp() {
		Fixtures.deleteDatabase();

	}

	// DateTime
	@Test
	public void dateTimeTest() {


	LocalDateTime d1 = LocalDateTime.of(2017, 05, 17,19, 15);
	System.out.println(d1);

	DateTime dateTime = new DateTime().now();
	System.out.println(dateTime);


	//1日前
	DateTime dateTime2 = new DateTime().minusDays(1);
	//dateTime2がdateTimeより過去ならば
	if(dateTime2.isBefore(dateTime)){


	}
	DateTime datetTime3 = new DateTime(2017,05,17,19,15);
	System.out.println(datetTime3);






	System.out.println(dateTime.getYear());
	System.out.println(dateTime.getDayOfMonth());
	System.out.println(dateTime.getMillis());
	System.out.println(dateTime.minusDays(1));
	System.out.println(dateTime.getMinuteOfDay());
	System.out.println(dateTime.getDayOfYear());
	System.out.println(dateTime.getDayOfWeek());

	DateTime datetTime4 = new DateTime().now().plusMonths(1)
			.withDayOfMonth(1)
			.withHourOfDay(12);
	System.out.println(datetTime4);



	}
}
