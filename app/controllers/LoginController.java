package controllers;

import java.util.List;

import models.user.User;
import play.mvc.Controller;

public class LoginController extends Controller {

    public static void login(final String id, final String pass) {

        final List<User> usersList = User.findAll();
        System.out.println(usersList.size());
        System.out.println(usersList.size());
        System.out.println(usersList.size());
        System.out.println(usersList.size());
        System.out.println(usersList.size());

        //TODO hatanaka testファイル以外も綺麗に

        render();
    }
}
