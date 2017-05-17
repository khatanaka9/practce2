package controllers;

import java.util.List;

import models.user.User;
import play.mvc.Controller;

public class LoginController extends Controller {

    public static void login(final String id, final String pass) {

        final List<User> usersList = User.findAll();

        render();
    }
}
