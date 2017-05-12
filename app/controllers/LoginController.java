package controllers;

import java.util.List;

import models.user.User;
import play.mvc.Controller;



public class LoginController extends Controller{

	 public static void login(String id,String pass) {




		 List<User> usersList = User.findAll();
		 System.out.println(usersList.size());
		 System.out.println(usersList.size());
		 System.out.println(usersList.size());
		 System.out.println(usersList.size());
		 System.out.println(usersList.size());



//		 List<Users> PassList = UserRepo.findPass(pass);
//		 System.out.println(PassList.size());
//		 if(PassList.isEmpty()){
//			 //	throw new Exception("error");
//			 System.out.println("空");
//		}
//	        render();
//	    }
	 render();
	 }
}
