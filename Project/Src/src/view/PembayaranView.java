package view;

import controller.AppController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.DataStore;
import model.Pemesanan;

public class PembayaranView {

    private StackPane root;

    public PembayaranView(Stage stage) {
        root = new StackPane();

        AppController app = AppController.getInstance();
        Pemesanan pemesanan = app.getCurrentPemesanan();

        if (pemesanan == null) {
            new Alert(Alert.AlertType.ERROR, "Data pemesanan tidak ditemukan!").show();
            stage.setScene(new Scene(new UserPanelView(stage).getView(), 900, 600));
            return;
        }

        root.setPadding(new Insets(20));

        VBox content = new VBox(15);
        content.setMaxWidth(500);
        content.setAlignment(Pos.CENTER);
        content.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-padding: 20;");

        Label title = new Label("PEMBAYARAN TIKET");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Hitung jumlah kursi yang dipilih
        int jumlahKursi = pemesanan.getKursi().split(",").length;
        int hargaPerTiket = (int) pemesanan.getKonser().getHarga();
        int total = jumlahKursi * hargaPerTiket;

        // Cek apakah ini adalah pembelian pertama (dari DataStore)
        boolean isFirstCustomer = DataStore.getInstance().getPemesananList().isEmpty();
        int diskon = isFirstCustomer ? (int) (total * 0.1) : 0;
        int totalBayar = total - diskon;

        Label detailHarga = new Label("Harga per tiket: Rp " + hargaPerTiket);
        Label jumlah = new Label("Jumlah kursi: " + jumlahKursi);
        Label totalLabel = new Label("Total: Rp " + total);
        Label diskonLabel = new Label(isFirstCustomer ? "Diskon Pembeli Pertama: -Rp " + diskon : "Diskon: Rp 0");
        Label finalLabel = new Label("Total Pembayaran: Rp " + totalBayar);
        finalLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        TextField noRek = new TextField();
        noRek.setPromptText("Masukkan No. Rekening Anda");

        Button bayarButton = new Button("Bayar Sekarang");
        bayarButton.setStyle("-fx-background-color: lightgreen;");
        bayarButton.setOnAction(e -> {
            if (noRek.getText().trim().isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Harap masukkan nomor rekening terlebih dahulu!").show();
                return;
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pembayaran Berhasil");
            alert.setHeaderText(null);
            alert.setContentText("Pembayaran berhasil diproses.\nTiket Anda akan dicetak.");
            alert.showAndWait();

            stage.setScene(new Scene(new TiketCetakView(stage).getView(), 900, 600));
        });

        Button kembaliButton = new Button("Kembali");
        kembaliButton.setOnAction(e -> stage.setScene(new Scene(new UserPanelView(stage).getView(), 900, 600)));

        HBox tombolBox = new HBox(10, bayarButton, kembaliButton);
        tombolBox.setAlignment(Pos.CENTER);

        content.getChildren().addAll(
                title,
                detailHarga,
                jumlah,
                totalLabel,
                diskonLabel,
                finalLabel,
                noRek,
                tombolBox
        );

        root.getChildren().add(content);
    }

    public StackPane getView() {
        return root;
    }
}