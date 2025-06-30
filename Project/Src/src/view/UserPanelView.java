package view;

import controller.AppController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Konser;
import model.Pemesanan;

public class UserPanelView {
    private BorderPane root;

    public UserPanelView(Stage stage) {
        root = new BorderPane();

        AppController app = AppController.getInstance();
        Konser konser = app.getSelectedKonser();
        Pemesanan pemesanan = app.getCurrentPemesanan();

        // ===== HEADER =====
        BorderPane header = new BorderPane();
        header.setStyle("-fx-background-color: #AEE2F8;");
        header.setPadding(new Insets(15));

        Label title = new Label("Konser Musik Theater");
        title.setFont(Font.font("Monospaced", FontWeight.BOLD, 28));
        BorderPane.setAlignment(title, Pos.CENTER);
        header.setCenter(title);

        // ===== NAVBAR =====
        HBox navbar = new HBox();
        navbar.setStyle("-fx-background-color: #222;");
        navbar.setPadding(new Insets(10));
        navbar.setAlignment(Pos.CENTER_RIGHT); // posisi tombol logout ke kanan

        Button logoutBtn = new Button("Logout");
        logoutBtn.setStyle("-fx-background-color: white; -fx-text-fill: #222; -fx-font-weight: bold;");
        logoutBtn.setOnAction(e -> {
            app.logout();
            stage.setScene(new Scene(new LoginView(stage).getView(), 900, 600));
        });

        navbar.getChildren().add(logoutBtn);

        // ===== SIDEBAR =====
        VBox sidebar = new VBox(15);
        sidebar.setAlignment(Pos.CENTER);
        sidebar.setPadding(new Insets(15));
        sidebar.setStyle("-fx-background-color: #eee;");
        sidebar.setPrefWidth(220);

        Label menuUserLabel = new Label("Menu Pengguna");
        menuUserLabel.setStyle("-fx-font-weight: bold; -fx-background-color: black; -fx-text-fill: white;");
        menuUserLabel.setMaxWidth(Double.MAX_VALUE);
        menuUserLabel.setAlignment(Pos.CENTER);

        Button detailTiketBtn = new Button("DETAIL TIKET");
        Button pesanBtn = new Button("PESAN TIKET");
        Button cetakBtn = new Button("CETAK TIKET");
        Button bayarBtn = new Button("PEMBAYARAN TIKET");
        Button kembaliList = new Button("Kembali ke List Tiket");

        for (Button btn : new Button[]{detailTiketBtn, pesanBtn, cetakBtn, bayarBtn, kembaliList}) {
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setStyle("-fx-background-color: white; -fx-border-color: #2196F3; -fx-border-width: 2px; -fx-text-fill: #2196F3; -fx-font-weight: bold;");
        }

        // ===== Button Actions =====
        detailTiketBtn.setOnAction(e -> {
            if (pemesanan != null) {
                stage.setScene(new Scene(new TiketView(stage).getView(), 900, 600));
            } else {
                new Alert(Alert.AlertType.WARNING, "Belum ada pemesanan!").show();
            }
        });

        pesanBtn.setOnAction(e -> {
            Konser selectedKonser = app.getSelectedKonser();
            if (selectedKonser != null) {
                stage.setScene(new Scene(new PemesananView(stage, selectedKonser).getView(), 900, 600));
            } else {
                new Alert(Alert.AlertType.WARNING, "Konser belum dipilih!").show();
            }
        });

        cetakBtn.setOnAction(e -> {
            if (pemesanan != null) {
                stage.setScene(new Scene(new TiketCetakView(stage).getView(), 900, 600));
            } else {
                new Alert(Alert.AlertType.WARNING, "Belum ada pemesanan!").show();
            }
        });

        bayarBtn.setOnAction(e -> {
            if (pemesanan != null) {
                stage.setScene(new Scene(new PembayaranView(stage).getView(), 900, 600));
            } else {
                new Alert(Alert.AlertType.WARNING, "Belum ada pemesanan!").show();
            }
        });

        kembaliList.setOnAction(e -> stage.setScene(new Scene(new KonserListView(stage).getView(), 900, 600)));

        sidebar.getChildren().addAll(menuUserLabel, detailTiketBtn, pesanBtn, cetakBtn, bayarBtn, kembaliList);

        // ===== MAIN CONTENT =====
        VBox content = new VBox(10);
        content.setPadding(new Insets(15));
        content.setAlignment(Pos.CENTER);
        content.setStyle("-fx-background-color: #f4f4f4;");
        content.setPrefHeight(400);
        content.setPrefWidth(600);

        Label welcome = new Label("Selamat datang di halaman pengguna!");
        welcome.setFont(Font.font("Arial", 16));
        content.getChildren().add(welcome);

        // ===== Layout Composition =====
        VBox topContainer = new VBox(header, navbar);

        StackPane centerWrapper = new StackPane(content);
        centerWrapper.setAlignment(Pos.CENTER);

        root.setTop(topContainer);
        root.setLeft(sidebar);
        root.setCenter(centerWrapper);
    }

    public BorderPane getView() {
        return root;
    }
}