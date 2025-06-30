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
import javafx.stage.Stage;
import model.Pemesanan;

public class TiketView {

    private StackPane root;

    public TiketView(Stage stage) {
        root = new StackPane();
        AppController app = AppController.getInstance();
        Pemesanan pemesanan = app.getCurrentPemesanan();

        if (pemesanan == null) {
            new Alert(Alert.AlertType.ERROR, "Data tiket tidak tersedia!").show();
            stage.setScene(new Scene(new UserPanelView(stage).getView(), 900, 600));
            return;
        }

        // Background biru langit
        root.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        // Kontainer isi
        VBox content = new VBox(10);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.CENTER);

        Label title = new Label("DETAIL TIKET ANDA");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setStyle("-fx-underline: true;");

        VBox info = new VBox(10);
        info.setAlignment(Pos.CENTER);
        info.getChildren().addAll(
                buatBaris("Nama Pemesan", pemesanan.getNama()),
                buatBaris("No Telp", pemesanan.getTelp()),
                buatBaris("Judul Musik", pemesanan.getKonser().getJudul()),
                buatBaris("Tanggal & Jam Konser", pemesanan.getKonser().getTanggal() + ", " + pemesanan.getJam() + ":00"),
                buatBaris("Harga Tiket", "Rp " + pemesanan.getKonser().getHarga() + " / Tiket"),
                buatBaris("Kursi", pemesanan.getKursi())
        );

        Label footer = new Label("Selamat menikmati konser musik kesayangan Anda.");
        footer.setFont(Font.font("Arial", 12));

        // Tombol kembali
        Button kembaliButton = new Button("Kembali");
        kembaliButton.setOnAction(e -> stage.setScene(new Scene(new UserPanelView(stage).getView(), 900, 600)));

        content.getChildren().addAll(title, new Separator(), info, new Separator(), footer, kembaliButton);
        root.getChildren().add(content);
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
