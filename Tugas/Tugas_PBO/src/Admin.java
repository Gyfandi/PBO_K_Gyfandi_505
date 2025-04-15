public class Admin {
    public String username;
    public String password;

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}