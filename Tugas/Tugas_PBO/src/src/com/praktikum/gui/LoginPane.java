package com.praktikum.gui;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import com.praktikum.main.LoginSystem; // Tetap import LoginSystem untuk authenticateUser
import com.praktikum.data.DataStore; // Tambahkan import untuk DataStore
import com.praktikum.users.Admin; //
import com.praktikum.users.Mahasiswa; //
import com.praktikum.users.User; //

public class LoginPane {

    private VBox root;
    private ComboBox<String> userTypeComboBox;
    private TextField idField;
    private PasswordField passwordField;
    private Label messageLabel;

    public LoginPane() {
        initializeUI();
    }

    private void initializeUI() {
        // --- Komponen UI ---
        Label titleLabel = new Label("Login Sistem Lost & Found");
        titleLabel.setFont(new Font("System Bold", 18));

        userTypeComboBox = new ComboBox<>();
        userTypeComboBox.setItems(FXCollections.observableArrayList("Admin", "Mahasiswa"));
        userTypeComboBox.setValue("Mahasiswa"); // Set default

        idField = new TextField();
        idField.setPromptText("Username / Nama");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password / NIM");

        Button loginButton = new Button("Login");
        loginButton.setPrefWidth(80);

        messageLabel = new Label();
        messageLabel.setTextFill(Color.RED);

        // --- Event Handling untuk Login Button ---
        loginButton.setOnAction(event -> handleLogin());

        // --- Tata Letak (Layout) ---
        root = new VBox(10); // Spasi 10px antar elemen
        root.setAlignment(Pos.CENTER); // Posisi tengah
        root.setPadding(new Insets(20, 20, 20, 20)); // Padding
        root.getChildren().addAll(titleLabel, userTypeComboBox, idField, passwordField, loginButton, messageLabel);
    }

    private void handleLogin() {
        String userType = userTypeComboBox.getValue();
        String id = idField.getText();
        String passwordOrNim = passwordField.getText();

        if (userType == null || id.isEmpty() || passwordOrNim.isEmpty()) {
            messageLabel.setText("Semua field harus diisi!");
            return;
        }

        User authenticatedUser = LoginSystem.authenticateUser(userType, id, passwordOrNim); // Menggunakan LoginSystem untuk autentikasi

        if (authenticatedUser != null) {
            messageLabel.setText("Login Berhasil!");
            // Pindah ke dashboard yang sesuai
            if (authenticatedUser instanceof Admin) { //
                MainApp.loadAdminDashboard();
            } else if (authenticatedUser instanceof Mahasiswa) { //
                MainApp.loadMahasiswaDashboard((Mahasiswa) authenticatedUser);
            }
        } else {
            messageLabel.setText("Login gagal, periksa kredensial.");
        }
    }

    public Parent getRoot() {
        return root;
    }
}