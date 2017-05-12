package models.user.repo;

import java.util.List;

import models.user.User;

public class UserRepo {

	//passで検索
	public static List<User> findPass(String pass){
return User.find("pass = ?", pass).fetch();
	}



}
