public class Admin {
    public String username = "Admin505";
    public String password = "Password505";

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}