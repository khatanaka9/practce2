package controllers;

import java.util.*;

import models.user.*;
import play.mvc.*;

public class LoginController extends Controller {
    
    public static void login(final String id, final String pass) {
        
        final List<User> usersList = User.findAll();
        System.out.println(usersList.size());
        System.out.println(usersList.size());
        System.out.println(usersList.size());
        System.out.println(usersList.size());
        System.out.println(usersList.size());
        
        //TODO hatanaka testファイル以外も綺麗に
        
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
