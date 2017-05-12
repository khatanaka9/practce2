package models.project.repo;

import java.util.List;

import models.project.Project;
import models.user.User;

public class ProjectRepo {

	//userで検索
	public static List<Project> findPass(User user){
return Project.find("users = ?", user).fetch();
	}



}
