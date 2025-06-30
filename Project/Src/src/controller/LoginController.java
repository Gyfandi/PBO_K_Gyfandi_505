package controller;

import model.Admin;
import model.RegularUser;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class LoginController {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new Admin("adminkece", "admin123"));
        users.add(new RegularUser("user", "user123"));
    }

    public static User authenticate(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
}