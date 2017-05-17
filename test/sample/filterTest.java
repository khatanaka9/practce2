package sample;

import static org.hamcrest.CoreMatchers.*;
import models.user.*;
import models.user.collection.*;

import org.junit.*;

import play.test.*;

public class filterTest extends UnitTest {
    
    @Before
    public void setUp() {
        Fixtures.deleteDatabase();
        
    }
    
    // filter
    @Test
    public void filterTest() {
        // 絞り込む
        new User("hharita", "p@ssw0rd").save();
        new User("hoge", "p@ssw0rd").save();
        final UsersCollection userCollection = new UsersCollection(User.findAll());
        
        final UsersCollection collect = userCollection.filterByName("hharita");
        
        // 1件のみのはず
        assertThat(collect.size(), is(1));
        // ユーザー名がhharitaのものだけのはず
        assertThat(collect.get(0).map(user -> user.name.equals("hharita")).orElse(false), is(true));
        
    }
}
