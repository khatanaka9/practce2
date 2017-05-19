package sample;

import org.junit.*;

import play.test.*;

//機能毎にファイル作成したのでDeprecated
@Deprecated
public class StreamApiTest extends UnitTest {

	@Before
	public void setUp() {
		Fixtures.deleteDatabase();

	}
}
