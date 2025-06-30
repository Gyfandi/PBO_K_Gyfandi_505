package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.DataStore;
import model.Pemesanan;

import java.util.List;

public class AdminPanelView {
    private BorderPane root;

    public AdminPanelView(Stage stage) {
        root = new BorderPane();

        // ===== HEADER =====
        HBox header = new HBox();
        header.setStyle("-fx-background-color: #AEE2F8; -fx-padding: 20;");
        header.setAlignment(Pos.CENTER_LEFT);
        Label title = new Label("Konser Musik Theater");
        title.setFont(Font.font("Monospaced", FontWeight.BOLD, 28));
        header.getChildren().add(title);

        // ===== NAVBAR =====
        HBox navbar = new HBox(20);
        navbar.setStyle("-fx-background-color: #222; -fx-padding: 10;");
        navbar.setAlignment(Pos.CENTER_LEFT);
        Button logoutBtn = new Button("Logout");
        logoutBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        logoutBtn.setOnAction(e -> {
            controller.AppController.getInstance().logout(); // Reset user & state
            stage.setScene(new Scene(new LoginView(stage).getView(), 900, 600));
        });
        navbar.getChildren().add(logoutBtn);

        // ===== SIDEBAR (Hanya LAPORAN PENJUALAN) =====
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(15));
        sidebar.setStyle("-fx-background-color: #eee;");
        Label menuAdminLabel = new Label("Menu Administrator");
        menuAdminLabel.setStyle("-fx-font-weight: bold; -fx-background-color: black; -fx-text-fill: white;");
        menuAdminLabel.setMaxWidth(Double.MAX_VALUE);
        menuAdminLabel.setAlignment(Pos.CENTER);

        Button laporanPenjualanBtn = new Button("LAPORAN PENJUALAN");
        laporanPenjualanBtn.setMaxWidth(Double.MAX_VALUE);
        laporanPenjualanBtn.setStyle(
                "-fx-background-color: white; " +
                        "-fx-border-color: red; -fx-border-width: 2px; " +
                        "-fx-text-fill: red; -fx-font-weight: bold;"
        );
        laporanPenjualanBtn.setOnAction(e -> {
                stage.setScene(new Scene(new LaporanPenjualanView(stage).getView(), 900, 600));
            });


        sidebar.getChildren().addAll(menuAdminLabel, laporanPenjualanBtn);

        VBox topContainer = new VBox(header, navbar);
        root.setTop(topContainer);
        root.setLeft(sidebar);
        root.setCenter(getLaporanPenjualanView()); // default view
    }

    public BorderPane getView() {
        return root;
    }

    private VBox getLaporanPenjualanView() {
        VBox content = new VBox(10);
        content.setPadding(new Insets(15));
        content.setStyle("-fx-background-color: #f4f4f4;");

        Label label = new Label("Laporan Penjualan Tiket");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        TableView<Pemesanan> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Pemesanan, String> colJudul = new TableColumn<>("Judul Musik");
        colJudul.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getKonser().getJudul()));

        TableColumn<Pemesanan, String> colJadwal = new TableColumn<>("Jadwal Konser");
        colJadwal.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getKonser().getTanggal() + ", " + data.getValue().getJam()
        ));

        TableColumn<Pemesanan, String> colNama = new TableColumn<>("Nama Pembeli");
        colNama.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNama()));

        TableColumn<Pemesanan, String> colHarga = new TableColumn<>("Harga");
        colHarga.setCellValueFactory(data -> new SimpleStringProperty(
                String.format("Rp %, .0f", data.getValue().getKonser().getHarga())
        ));

        table.getColumns().addAll(colJudul, colJadwal, colNama, colHarga);

        List<Pemesanan> semuaPemesanan = DataStore.getInstance().getPemesananList();
        table.setItems(FXCollections.observableArrayList(semuaPemesanan));

        double total = semuaPemesanan.stream()
                .mapToDouble(p -> p.getKonser().getHarga())
                .sum();

        Label totalLabel = new Label("Total Pendapatan: Rp " + String.format("%,.0f", total));
        totalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        totalLabel.setTextFill(Color.GREEN);

        content.getChildren().addAll(label, table, totalLabel);
        return content;
    }
}
