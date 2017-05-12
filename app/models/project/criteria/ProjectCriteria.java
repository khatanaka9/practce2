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

public class ProjectCriteria extends AbstractEntityCriteria<ProjectCriteria, Project> {

	public ProjectCriteria(){
		super(Project.class,Collections.EMPTY_LIST,100);
	}

	public ProjectCriteria filterByNamePartialMatch(String projectname){
		setLike("projectName", projectname);
		return this;
	}

	public ProjectCriteria filterByNamePerfectMatch(String projectname){
		setEqual("projectName", projectname);
		return this;
	}

	public ProjectCriteria filterByProjectType(ProjectType projectType){
		setEqual("projectType", projectType.intValue);
		return this;
	}

	public ProjectCriteria filterByProjectTypeList(List<ProjectType> projectTypeList){
		List<Integer> projectTypeIntValueList = projectTypeList.stream().map(projectType -> projectType.intValue).collect(Collectors.toList());
		setIn("projectType", projectTypeIntValueList);
		return this;

	}
	public ProjectCriteria filterByProjectUser(){

//		setIn("userType", Arrays.asList(UserType.values()), Arrays.asList("users"));

		final DetachedCriteria userCriteria = getJoinCriteria(Arrays.asList("users"));
		userCriteria.add(Restrictions.eq("userType", UserType.管理者.intValue));
//		userCriteria.add(Restrictions.or(Restrictions.in("apNum.value", values),
//                                              Restrictions.in("bukkenCode.value", values)));
		return this;
	}


}
