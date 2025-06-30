package view;

import controller.AppController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.DataStore;
import model.Pemesanan;

public class TiketCetakView {

    private StackPane root;

    public TiketCetakView(Stage stage) {
        root = new StackPane();
        AppController app = AppController.getInstance();
        Pemesanan pemesanan = app.getCurrentPemesanan();

        if (pemesanan == null) {
            new Alert(Alert.AlertType.ERROR, "Data tiket tidak tersedia!").show();
            stage.setScene(new Scene(new UserPanelView(stage).getView(), 900, 600));
            return;
        }

        root.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        VBox content = new VBox(10);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.CENTER);

        Label title = new Label("TIKET KONSER MUSIK");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setStyle("-fx-underline: true;");
        title.setTextAlignment(TextAlignment.CENTER);

        // Hitung jumlah tiket dan total pembayaran
        int jumlahTiket = pemesanan.getKursi().split(",").length;
        int hargaTiket = (int) pemesanan.getKonser().getHarga();
        int total = jumlahTiket * hargaTiket;
        boolean isFirstCustomer = DataStore.getInstance().getPemesananList().isEmpty(); // <- perlu disesuaikan jika sudah disimpan saat pembayaran
        int diskon = isFirstCustomer ? (int) (total * 0.1) : 0;
        int totalBayar = total - diskon;

        VBox info = new VBox(10);
        info.setAlignment(Pos.CENTER);
        info.getChildren().addAll(
                buatBaris("Nama Pemesan", pemesanan.getNama()),
                buatBaris("No Telp", pemesanan.getTelp()),
                buatBaris("Judul Musik", pemesanan.getKonser().getJudul()),
                buatBaris("Tanggal & Jam Konser", pemesanan.getKonser().getTanggal() + ", " + pemesanan.getJam() + ":00"),
                buatBaris("Jumlah Tiket", jumlahTiket + " kursi"),
                buatBaris("Harga Tiket", "Rp " + hargaTiket + " / Tiket"),
                buatBaris("Diskon", "Rp " + diskon),
                buatBaris("Total Pembayaran", "Rp " + totalBayar),
                buatBaris("Kursi", pemesanan.getKursi())
        );

        Label footer = new Label("Selamat Menikmati Konser Musik Kesayangan Anda.\nTiket ini adalah bukti resmi pemesanan.");
        footer.setFont(Font.font("Arial", 12));
        footer.setTextAlignment(TextAlignment.CENTER);

        // Tombol kembali
        Button kembaliButton = new Button("Kembali");
        kembaliButton.setOnAction(e -> stage.setScene(new Scene(new UserPanelView(stage).getView(), 900, 600)));

        // Tombol cetak
        Button cetakButton = new Button("CETAK TIKET");
        cetakButton.setStyle("-fx-background-color: white; -fx-border-color: black;");
        cetakButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cetak Tiket");
            alert.setHeaderText(null);
            alert.setContentText("Tiket berhasil dicetak!");
            alert.showAndWait();
        });

        BorderPane bottomPane = new BorderPane();
        bottomPane.setLeft(kembaliButton);
        bottomPane.setRight(cetakButton);
        bottomPane.setPadding(new Insets(10));

        VBox isiTengah = new VBox(10, new Label("E - Tiket"), title, new Separator(), info, new Separator(), footer);
        isiTengah.setAlignment(Pos.CENTER);

        BorderPane layout = new BorderPane();
        layout.setCenter(isiTengah);
        layout.setBottom(bottomPane);

        root.getChildren().add(layout);
    }

    private VBox buatBaris(String label, String isi) {
        Label l = new Label(label);
        l.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Label v = new Label(isi);
        v.setFont(Font.font("Arial", 14));
        VBox baris = new VBox(2, l, v);
        baris.setAlignment(Pos.CENTER);
        return baris;
    }

    public StackPane getView() {
        return root;
    }
}