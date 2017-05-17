package sample;

import static org.hamcrest.CoreMatchers.*;

import java.util.*;

import models.user.*;
import models.user.collection.*;

import org.junit.*;

import play.test.*;

public class mapTest extends UnitTest {
    
    @Before
    public void setUp() {
        Fixtures.deleteDatabase();
        
    }
    
    // map
    @Test
    public void mapTest() {
        // 更新する
        new User("hatanaka", "p@ssw0rd").save();
        new User("hharita", "p@ssw0rd").save();
        new User("horimoto", "p@ssw0rd").save();
        new User("watanabe", "p@ssw0rd").save();
        
        final UsersCollection userCollection = new UsersCollection(User.findAll());
        
        //TODO hatanaka これなんかおかしいです。
        final List<String> map = userCollection.mapToUserName("hatanaka");
        
        // 4件存在するはず
        assertThat(map.size(), is(4));
        // ユーザー名が全件"hatanaka"に変更されているはず
        assertThat(map.get(0), is("hatanaka"));
        assertThat(map.get(1), is("hatanaka"));
        assertThat(map.get(2), is("hatanaka"));
        assertThat(map.get(3), is("hatanaka"));
        
    }
}
