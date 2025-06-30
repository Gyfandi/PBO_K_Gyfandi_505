// Tambahan: Admin class pakai inheritance & polimorphisme
package model;

public class Admin extends User {

    public Admin(String username, String password) {
        super(username, password, true);
    }

    @Override
    public String getRole() {
        return "Admin";
    }
}