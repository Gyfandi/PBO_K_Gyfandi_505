package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.HomeView;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        HomeView homeView = new HomeView(primaryStage);
        Scene scene = new Scene(homeView.getView(), 800, 600);
        primaryStage.setTitle("TixAja");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}