package models.project.criteria;

import static org.hamcrest.CoreMatchers.*;

import java.util.Arrays;
import java.util.List;

import models.project.Project;
import models.project.types.ProjectType;
import models.user.User;
import models.user.types.UserType;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class ProjectCriteriaTest extends UnitTest {

	// ----TODO-----

	// projectのカラムにprojectType(案件区分：不動産案件＆銀行システム案件＆病院システム案件)を追加
	// projectCriteriaクラスを作成する

	// projectの名前部分一致テスト
	// projectの名前完全一致テスト
	// projectが不動産案件のものテスト
	// projectが銀行システム案件のものテスト
	// projectが不動産案件と病院システム案件のもの（頭つかうかな）テスト

	// 最後：projectを持っているUsersが管理者のもの（応用）

	private User user1;
	private User user2;
	private User user3;
	private User user4;

	@Before
	public void setup() {
		Fixtures.deleteDatabase();

		user1 = new User("hharitaAdmin", "p@ssw0rd", UserType.管理者).save();
		user2 = new User("hharita", "p@ssw0rd", UserType.利用者).save();
		user3 = new User("hatanakaAdmin", "p@ssw0rd", UserType.管理者).save();
		user4 = new User("hatanaka", "p@ssw0rd", UserType.利用者).save();

		new Project(user1, "realEstate1", ProjectType.不動産案件).save();
		new Project(user2, "realEstate2", ProjectType.不動産案件).save();
		new Project(user3, "bank", ProjectType.銀行システム案件).save();
		new Project(user4, "hospital", ProjectType.病院システム案件).save();
	}

	@Test
	// 名前が部分一致しているものを絞り込む
	public void filterByProjectNamePartialMatch() {

		String projectname = "ban";

		ProjectCriteria criteria = new ProjectCriteria();

		criteria.filterByNamePartialMatch(projectname);

		// criteriaにフィルター処理が存在しない場合
		if (!criteria.hasFilter()) {
			throw new RuntimeException("filter処理がありません。");
		}

		List<Project> list = criteria.exec();
		// 名前にbanが含まれるレコードが1件取れるはず
		assertThat(list.size(), is(1));
	}

	@Test
	// 名前が完全一致しているものを絞り込む
	public void filterByProjectNamePerfectMatch() {

		String projectname = "hospital";

		ProjectCriteria criteria = new ProjectCriteria();

		criteria.filterByNamePerfectMatch(projectname);

		// criteriaにフィルター処理が存在しない場合
		if (!criteria.hasFilter()) {
			throw new RuntimeException("filter処理がありません。");
		}

		List<Project> list = criteria.exec();
		// 名前がhospitalのレコードが1件取れるはず
		assertThat(list.size(), is(1));
	}

	@Test
	// 案件種別が不動産案件のものを絞り込む
	public void filterByProjectTypeRealEstate() {

		ProjectType selectProjectType = ProjectType.不動産案件;

		ProjectCriteria criteria = new ProjectCriteria();
		criteria.filterByProjectType(selectProjectType);

		// criteriaにフィルター処理が存在しない場合
		if (!criteria.hasFilter()) {
			throw new RuntimeException("filter処理がありません。");
		}

		List<Project> list = criteria.exec();
		// 案件種別が不動産案件のレコードが2件取れるはず
		assertThat(list.size(), is(2));

	}

	@Test
	// 案件種別が銀行システム案件のものを絞り込む
	public void filterByProjectTypeBank() {

		ProjectType selectProjectType = ProjectType.銀行システム案件;

		ProjectCriteria criteria = new ProjectCriteria();
		criteria.filterByProjectType(selectProjectType);

		// criteriaにフィルター処理が存在しない場合
		if (!criteria.hasFilter()) {
			throw new RuntimeException("filter処理がありません。");
		}

		List<Project> list = criteria.exec();
		// 案件種別が銀行システム案件のレコードが1件取れるはず
		assertThat(list.size(), is(1));

	}

	@Test
	// 案件種別が不動産案件と病院システム案件のものを絞り込む
	public void filterByProjectTypeHospital() {

		ProjectType projectTypeRealEstate = ProjectType.不動産案件;
		ProjectType projectTypeHospital = ProjectType.病院システム案件;

		List projectTypeList = Arrays.asList(projectTypeRealEstate,
				projectTypeHospital);

		ProjectCriteria criteria = new ProjectCriteria();

		criteria.filterByProjectTypeList(projectTypeList);

		// criteriaにフィルター処理が存在しない場合
		if (!criteria.hasFilter()) {
			throw new RuntimeException("filter処理がありません。");
		}

		List<Project> list = criteria.exec();
		// 案件種別が不動産案件か病院システムのレコードが3件とれるはず
		assertThat(list.size(), is(3));

	}

	@Test
	// projectを持っているUserが管理者のものを絞り込む
	public void filterByProjectUser() {

		ProjectCriteria criteria = new ProjectCriteria();
		criteria.filterByProjectUser();

		// criteriaにフィルター処理が存在しない場合
		if (!criteria.hasFilter()) {
			throw new RuntimeException("filter処理がありません。");
		}

		List<Project> list = criteria.exec();

		// UserTypeが管理者のレコードが2件とれるはず
		assertThat(list.size(), is(2));
		// 取得したデータはユーザー種別が管理者のレコードなはず
//		list.forEach(project -> assertThat(
//				project.user().userType().equals(UserType.管理者), is(true)));

	}
}
