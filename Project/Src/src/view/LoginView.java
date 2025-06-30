package view;

import controller.AppController;
import controller.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.User;

public class LoginView {

    private StackPane root;
    private Stage stage;

    public LoginView(Stage stage) {


        this.stage = stage;

        // Root dengan background gradasi sky blue ke putih
        root = new StackPane();
        BackgroundFill gradientFill = new BackgroundFill(
                new LinearGradient(
                        0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                        new Stop(0, Color.SKYBLUE),
                        new Stop(1, Color.WHITE)
                ),
                CornerRadii.EMPTY,
                Insets.EMPTY
        );
        root.setBackground(new Background(gradientFill));

        // Kotak login
        VBox loginBox = new VBox(15);
        loginBox.setAlignment(Pos.CENTER);
        loginBox.setPadding(new Insets(30));
        loginBox.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.8); " +
                        "-fx-border-color: #2196F3; -fx-border-width: 2; " +
                        "-fx-background-radius: 10; -fx-border-radius: 10;"
        );

        Label loginLabel = new Label("Login");
        loginLabel.setFont(Font.font("Courier New", 24));
        loginLabel.setTextFill(Color.DARKBLUE);

        Label instructionLabel = new Label("Please enter your credentials to proceed");
        instructionLabel.setFont(Font.font("Courier New", 12));
        instructionLabel.setTextFill(Color.DARKBLUE);

        TextField usernameField = new TextField();
        usernameField.setPromptText("username");
        usernameField.setFont(Font.font("Courier New"));
        usernameField.setStyle("-fx-background-color: white; -fx-text-fill: black;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");
        passwordField.setFont(Font.font("Courier New"));
        passwordField.setStyle("-fx-background-color: white; -fx-text-fill: black;");

        Button loginButton = new Button("Login");
        loginButton.setFont(Font.font("Courier New"));
        loginButton.setStyle(
                "-fx-background-color: #2196F3; -fx-text-fill: white; " +
                        "-fx-background-radius: 5; -fx-cursor: hand;"
        );
        loginButton.setDefaultButton(true);

        // Navigasi dengan Enter
        usernameField.setOnAction(e -> passwordField.requestFocus());
        passwordField.setOnAction(e -> loginButton.fire());

        // Aksi login
        loginButton.setOnAction(e -> {
            User user = LoginController.authenticate(usernameField.getText(), passwordField.getText());
            if (user != null) {
                AppController.getInstance().setCurrentUser(user); // <- Tambah ini

                if (user.isAdmin()) {
                    stage.setScene(new Scene(new AdminPanelView(stage).getView(), 900, 600));
                } else {
                    stage.setScene(new Scene(new KonserListView(stage).getView(), 900, 600));
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Login gagal").show();
            }
        });


        loginBox.getChildren().addAll(loginLabel, instructionLabel, usernameField, passwordField, loginButton);
        root.getChildren().add(loginBox);
    }

    public Parent getView() {
        return root;
    }
}
