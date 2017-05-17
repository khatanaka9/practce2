package models.project.criteria;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import models.base.criteria.AbstractEntityCriteria;
import models.project.Project;
import models.project.types.ProjectType;
import models.user.types.UserType;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class ProjectCriteria extends
		AbstractEntityCriteria<ProjectCriteria, Project> {


	public ProjectCriteria() {
		super(Project.class, Collections.EMPTY_LIST, 100);
	}

	// 名前を部分一致させる
	public ProjectCriteria filterByNamePartialMatch(String projectname) {
		setLike("projectName", projectname);
		return this;
	}

	// 名前を完全一致させる
	public ProjectCriteria filterByNamePerfectMatch(String projectname) {
		setEqual("projectName", projectname);
		return this;
	}

	// 案件種別でフィルタ
	public ProjectCriteria filterByProjectType(ProjectType projectType) {
		setEqual("projectType", projectType.intValue);
		return this;
	}

	// 案件種別2件でフィルタ
	public ProjectCriteria filterByProjectTypeList(
			List<ProjectType> projectTypeList) {
		List<Integer> projectTypeIntValueList = projectTypeList.stream()
				.map(projectType -> projectType.intValue)
				.collect(Collectors.toList());
		setIn("projectType", projectTypeIntValueList);
		return this;

	}

	// ユーザー種別でフィルタ
	public ProjectCriteria filterByProjectUser() {

		final DetachedCriteria userCriteria = getJoinCriteria(Arrays
				.asList("users"));
		userCriteria.add(Restrictions.eq("userType", UserType.管理者.intValue));
		return this;
	}

}
