package models.project;

import java.util.*;

import models.project.types.*;
import models.user.*;

import org.junit.*;

import play.test.*;

public class ProjectTest extends UnitTest {
    
    private List<User> list;
    private User user;
    
    @Before
    public void setUp() {
        ;
        Fixtures.deleteDatabase();
        user = new User("hharita", "test").save();
    }
    
    //TODO hatanaka Entityのテストケースも追加
    @Test
    //
    public void testConstructor() {
        final Project project = new Project(user, "projectName", ProjectType.不動産案件).save();
        
    }
    
}
