package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Pemesanan;

import java.util.List;

public class PemesananAdminView {
    private BorderPane root;

    public PemesananAdminView(Stage stage, List<Pemesanan> pemesananList) {

        root = new BorderPane();

        // ===== HEADER =====
        HBox header = new HBox();
        header.setStyle("-fx-background-color: #AEE2F8; -fx-padding: 20;");
        header.setAlignment(Pos.CENTER_LEFT);
        Label title = new Label("Kelola Pemesanan");
        title.setFont(Font.font("Monospaced", FontWeight.BOLD, 28));
        header.getChildren().add(title);

        // ===== TABEL PEMESANAN =====
        TableView<Pemesanan> table = new TableView<>();

        TableColumn<Pemesanan, String> colNama = new TableColumn<>("Nama Pemesan");
        colNama.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNama()));

        TableColumn<Pemesanan, String> colTelp = new TableColumn<>("No Telp");
        colTelp.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTelp()));

        TableColumn<Pemesanan, String> colJudul = new TableColumn<>("Judul Musik");
        colJudul.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getKonser().getJudul()));

        TableColumn<Pemesanan, String> colTanggal = new TableColumn<>("Tanggal");
        colTanggal.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getKonser().getTanggal()));

        TableColumn<Pemesanan, String> colJam = new TableColumn<>("Jam");
        colJam.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getJam()));

        TableColumn<Pemesanan, String> colKursi = new TableColumn<>("Kursi");
        colKursi.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getKursi()));

        table.getColumns().addAll(colNama, colTelp, colJudul, colTanggal, colJam, colKursi);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getItems().addAll(pemesananList);

        // ===== KONTEN UTAMA =====
        VBox content = new VBox(10);
        content.setPadding(new Insets(15));
        content.getChildren().addAll(new Label("Daftar Semua Pemesanan"), table);

        // ===== TOMBOL KEMBALI =====
        Button kembaliBtn = new Button("Kembali ke Menu Administrator");
        kembaliBtn.setOnAction(e -> {
            stage.setScene(new Scene(new AdminPanelView(stage).getView(), 900, 600));
        });

        HBox bottomBox = new HBox(kembaliBtn);
        bottomBox.setPadding(new Insets(10));
        bottomBox.setAlignment(Pos.BOTTOM_LEFT);

        root.setTop(header);
        root.setCenter(content);
        root.setBottom(bottomBox);
    }

    public BorderPane getView() {
        return root;
    }
}
