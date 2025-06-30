package model;

public class RegularUser extends User {

    public RegularUser(String username, String password) {
        super(username, password, false);
    }

    @Override
    public String getRole() {
        return "User";
    }
}