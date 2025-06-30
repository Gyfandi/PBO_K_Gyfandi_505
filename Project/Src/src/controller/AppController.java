package controller;

import model.Konser;
import model.Pemesanan;
import model.User;

public class AppController {
    private static AppController instance;

    private User currentUser;
    private Konser selectedKonser;
    private Pemesanan currentPemesanan;

    private AppController() {
    }

    // Singleton Pattern supaya controller bisa dipakai lintas scene
    public static AppController getInstance() {
        if (instance == null) {
            instance = new AppController();
        }
        return instance;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isAdmin() {
        return currentUser != null && currentUser.isAdmin();
    }

    public void setSelectedKonser(Konser konser) {
        this.selectedKonser = konser;
    }

    public Konser getSelectedKonser() {
        return selectedKonser;
    }

    public void setCurrentPemesanan(Pemesanan pemesanan) {
        this.currentPemesanan = pemesanan;
    }

    public Pemesanan getCurrentPemesanan() {
        return currentPemesanan;
    }

    public void logout() {
        currentUser = null;
        selectedKonser = null;
        currentPemesanan = null;
    }
}
