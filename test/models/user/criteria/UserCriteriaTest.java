package models.user.criteria;

import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import models.user.User;
import models.user.types.UserType;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class UserCriteriaTest extends UnitTest{

	@Before
	public void serup() {
		Fixtures.deleteDatabase();
		new User("hharitaAdmin", "p@ssw0rd", UserType.管理者).save();
		new User("hharita", "p@ssw0rd", UserType.利用者).save();
		new User("hatanakaAdmin", "p@ssw0rd", UserType.管理者).save();
		new User("hatanaka", "p@ssw0rd", UserType.利用者).save();
	}

	@Test
	public void filterByUserNameTest(){
		//名前 部分一致
		String inputName="hharitaAdmin";

		UserCriteria criteria = new UserCriteria();

		criteria.filterByName(inputName);

		if(!criteria.hasFilter()){
//			return Collections.EMPTY_LIST;
			throw new RuntimeException("filter処理がありません。");
		}

		List<User> list = criteria.exec();
		//名前がhharitaAdminのレコードが1件とれるはず
		assertThat(list.size(), is(1));

	}


	@Test
	public void filterByUserTypeTest(){
		//ユーザー種別
		UserType selectUserType = UserType.管理者;

		UserCriteria criteria = new UserCriteria();
		criteria.filterByUserType(selectUserType);

		if(!criteria.hasFilter()){
//			return Collections.EMPTY_LIST;
			throw new RuntimeException("filter処理がありません。");
		}

		List<User> list = criteria.exec();
		//管理者ユーザーが２件とれるはず
		assertThat(list.size(), is(2));
	}



	//----TODO-----

	//projectのカラムにprojectType(案件区分：不動産案件＆銀行システム案件＆病院システム案件)を追加
	//projectCriteriaクラスを作成する

	//projectの名前部分一致テスト
	//projectの名前完全一致テスト
	//projectが不動産案件のものテスト
	//projectが銀行システム案件のものテスト
	//projectが不動産案件と病院システム案件のもの（頭つかうかな）テスト

	//最後：projectを持っているUsersが管理者のもの（応用）



}
