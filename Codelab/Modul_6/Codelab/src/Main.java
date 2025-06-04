import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    private int targetNumber;
    private int attempts;
    private final Random random = new Random();

    private Label feedbackLabel;
    private Label attemptsLabel;
    private TextField inputField;
    private Button actionButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Generate angka pertama
        generateNumber();

        // Title
        Label titleLabel = new Label("🔮 Tebak Angka 1–100");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        titleLabel.setTextFill(Color.DARKBLUE);

        // Feedback
        feedbackLabel = new Label("Masukkan tebakanmu!");
        feedbackLabel.setFont(Font.font("Arial", 16));
        feedbackLabel.setTextFill(Color.BLACK);

        // Input & Button
        inputField = new TextField();
        inputField.setPromptText("Masukkan angka di sini");
        inputField.setMaxWidth(140);

        actionButton = new Button("🎲 Coba Tebak!");
        actionButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        actionButton.setOnAction(e -> handleGuess());

        HBox inputBox = new HBox(10, inputField, actionButton);
        inputBox.setAlignment(Pos.CENTER);

        // Attempts
        attemptsLabel = new Label("Jumlah percobaan: 0");
        attemptsLabel.setFont(Font.font("Arial", 14));
        attemptsLabel.setTextFill(Color.BLACK);

        // Root layout
        VBox root = new VBox(15, titleLabel, feedbackLabel, inputBox, attemptsLabel);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color: #4d918f;");

        // Scene
        Scene scene = new Scene(root, 400, 280);
        primaryStage.setTitle("Tebak Angka Advance");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void generateNumber() {
        targetNumber = random.nextInt(100) + 1;
        attempts = 0;
        if (attemptsLabel != null) {
            attemptsLabel.setText("Jumlah percobaan: 0");
        }
    }

    private void handleGuess() {
        if (actionButton.getText().equals("🔁 Main Lagi")) {
            generateNumber();
            feedbackLabel.setText("Masukkan tebakanmu!");
            feedbackLabel.setTextFill(Color.BLACK);
            inputField.setDisable(false);
            inputField.setText("");
            actionButton.setText("🎲 Coba Tebak!");
            actionButton.setStyle("-fx-background-color: #8ec3b3; -fx-text-fill: white;");
            return;
        }

        String guessText = inputField.getText();
        if (!guessText.matches("\\d+")) {
            feedbackLabel.setText("⚠ Masukkan angka valid!");
            feedbackLabel.setTextFill(Color.DARKRED);
            return;
        }

        int guess = Integer.parseInt(guessText);
        attempts++;
        attemptsLabel.setText("Jumlah percobaan: " + attempts);

        if (guess < targetNumber) {
            feedbackLabel.setText("🔽 Terlalu kecil!");
            feedbackLabel.setTextFill(Color.LIGHTYELLOW);
        } else if (guess > targetNumber) {
            feedbackLabel.setText("🔼 Terlalu besar!");
            feedbackLabel.setTextFill(Color.LIGHTGOLDENRODYELLOW);
        } else {
            feedbackLabel.setText("✅ Tebakan benar!");
            feedbackLabel.setTextFill(Color.FORESTGREEN);
            inputField.setDisable(true);
            actionButton.setText("🔁 Main Lagi");
            actionButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        }
    }
}