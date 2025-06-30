package model;

public abstract class User {
    private String username;
    private String password;
    private boolean isAdmin;

    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() {
        return isAdmin; }

    public String getUsername() {
        return username; }

    public String getPassword() {
        return password; }

    public abstract String getRole();
}