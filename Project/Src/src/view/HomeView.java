package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import java.util.Random;

public class HomeView {
    private final Stage stage;
    private final StackPane root;

    public HomeView(Stage stage) {
        this.stage = stage;
        this.root = new StackPane();
    }


    public Pane getView() {


        StackPane glitterLayer = new StackPane(); // khusus untuk glitter
        StackPane contentLayer = new StackPane(); // untuk konten utama

        Label titleLabel = new Label("Aplikasi Ticketing Konser");
        titleLabel.setFont(new Font("Garamond", 36));

        Button pesanNow = new Button("Order Now!");
        pesanNow.setFont(Font.font("Arial", 18));
        pesanNow.setStyle("-fx-background-color: transparent; -fx-border-color: skyblue; -fx-border-width: 2; -fx-text-fill: skyblue;");

        pesanNow.setOnAction(e -> {
            LoginView loginView = new LoginView(stage);
            Scene loginScene = new Scene(loginView.getView(), 1000, 700);
            stage.setScene(loginScene);
        });


        VBox contentBox = new VBox(10);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(20));
        contentBox.getChildren().addAll(titleLabel, pesanNow);

        // Tambahkan glitter dulu ke lapisan bawah
        addGlitterEffect(glitterLayer);

        contentLayer.getChildren().add(contentBox);

        // StackPane utama gabungkan keduanya
        root.getChildren().addAll(glitterLayer, contentLayer);

        Stop[] stops = new Stop[]{
                new Stop(0, Color.SKYBLUE),
                new Stop(1, Color.WHITE)
        };

        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
        root.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));

        return root;
    }

    private void addGlitterEffect(Pane root) {
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            Circle sparkle = new Circle(1 + rand.nextDouble() * 2);
            sparkle.setFill(Color.rgb(255, 255, 255, 0.6));
            sparkle.setTranslateX(rand.nextDouble() * 600);
            sparkle.setTranslateY(rand.nextDouble() * 400);

            FadeTransition fade = new FadeTransition(Duration.seconds(2 + rand.nextDouble() * 2), sparkle);
            fade.setFromValue(0.2);
            fade.setToValue(1);
            fade.setAutoReverse(true);
            fade.setCycleCount(FadeTransition.INDEFINITE);
            fade.play();

            root.getChildren().add(sparkle);
        }
    }
}