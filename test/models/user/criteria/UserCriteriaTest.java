package models.user.criteria;

import static org.hamcrest.CoreMatchers.*;

import java.util.*;

import models.user.*;
import models.user.types.*;

import org.junit.*;

import play.test.*;

public class UserCriteriaTest extends UnitTest {
    
    @Before
    public void serup() {
        Fixtures.deleteDatabase();
        new User("hharitaAdmin", "p@ssw0rd", UserType.管理者).save();
        new User("hharita", "p@ssw0rd", UserType.利用者).save();
        new User("hatanakaAdmin", "p@ssw0rd", UserType.管理者).save();
        new User("hatanaka", "p@ssw0rd", UserType.利用者).save();
    }
    
    @Test
    public void filterByUserNameTest() {
        //名前でフィルタ 部分一致するもの
        final String inputName = "hharitaAdmin";
        
        final UserCriteria criteria = new UserCriteria();
        
        criteria.filterByName(inputName);
        
        //criteriaにフィルター処理が存在しない場合
        if (!criteria.hasFilter()) {
            //TODO hatanaka ゴミコードの削除1
            
//			return Collections.EMPTY_LIST;
            throw new RuntimeException("filter処理がありません。");
        }
        
        final List<User> list = criteria.exec();
        //名前がhharitaAdminのレコードが1件とれるはず
        assertThat(list.size(), is(1));
        
    }
    
    @Test
    public void filterByUserTypeTest() {
        //ユーザー種別でフィルタ
        final UserType selectUserType = UserType.管理者;
        
        final UserCriteria criteria = new UserCriteria();
        criteria.filterByUserType(selectUserType);
        
        //criteriaにフィルター処理が存在しない場合
        if (!criteria.hasFilter()) {
            //TODO hatanaka ゴミコードの削除2
//			return Collections.EMPTY_LIST;
            throw new RuntimeException("filter処理がありません。");
        }
        
        final List<User> list = criteria.exec();
        //管理者ユーザーが2件とれるはず
        assertThat(list.size(), is(2));
    }
    
}
