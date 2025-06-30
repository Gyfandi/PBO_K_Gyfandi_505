package view;

import controller.AppController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Konser;

import java.net.URL;

public class KonserListView {
    private Stage stage;

    public KonserListView(Stage stage) {
        this.stage = stage;
    }

    public BorderPane getView() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Daftar konser (kiri)
        VBox daftarKonser = new VBox(20);
        daftarKonser.setPadding(new Insets(10));
        daftarKonser.setStyle("-fx-background-color: deepskyblue;");
        daftarKonser.setPrefWidth(690);
        daftarKonser.getChildren().addAll(
                buatItemKonser(new Konser("Sister Gem", "Rock", "120 menit", "/assets/Kak_Gem.jpg", "25 Juni 2025", 250000)),
                buatItemKonser(new Konser("Angel Persik", "Dangdut", "120 menit", "/assets/Angel_Persik.jpg", "28 Juni 2025", 250000))
        );

        // Panel kanan: teaser video dengan navigasi
        VBox sidePanel = new VBox(20);
        sidePanel.setPadding(new Insets(10));
        sidePanel.setPrefWidth(300);
        sidePanel.getChildren().addAll(buatTeaserBox());

        root.setLeft(daftarKonser);
        root.setRight(sidePanel);
        return root;
    }

    private Node buatItemKonser(Konser konser) {
        VBox box = new VBox(5);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-background-color: white;");

        Label title = new Label(konser.getJudul());
        title.setFont(Font.font("Arial", 20));
        title.setStyle("-fx-font-weight: bold;");

        ImageView img;
        try {
            Image image = new Image(getClass().getResource(konser.getImagePath()).toExternalForm());
            img = new ImageView(image);
        } catch (Exception e) {
            img = new ImageView(new Image("https://via.placeholder.com/120"));
        }

        img.setFitWidth(120);
        img.setPreserveRatio(true);

        // Klik gambar → buka PemesananView
        img.setOnMouseClicked(e -> {
            AppController.getInstance().setSelectedKonser(konser);
            stage.setScene(new Scene(new PemesananView(stage, konser).getView(), 900, 600));
        });



        VBox info = new VBox(5);
        info.getChildren().addAll(
                new Label("Jenis Musik : " + konser.getGenre().toUpperCase()),
                new Label("Durasi : " + konser.getDurasi()),
                new Label("Tanggal : " + konser.getTanggal())
        );

        HBox item = new HBox(10, img, info);
        box.getChildren().addAll(title, item);
        return box;
    }

    private Node buatTeaserBox() {
        VBox teaserBox = new VBox(10);
        teaserBox.setPadding(new Insets(10));
        teaserBox.setStyle("-fx-background-color: lightgray;");

        Label title = new Label("New Musik Teaser");
        title.setStyle("-fx-font-weight: bold;");
        Label subtitle = new Label();
        subtitle.setTextFill(Color.RED);

        MediaView mediaView = new MediaView();
        mediaView.setFitWidth(280);
        mediaView.setPreserveRatio(true);

        // Data teaser
        class Teaser {
            String judul;
            String videoPath;

            Teaser(String judul, String videoPath) {
                this.judul = judul;
                this.videoPath = videoPath;
            }
        }

        Teaser[] teasers = new Teaser[]{
                new Teaser("Sister Gem", "/assets/Sister_Gem_Teaser.mp4"),
                new Teaser("Angel Persik", "/assets/Angel_Persik_Teaser.mp4")
        };

        final int[] currentIndex = {0};
        final MediaPlayer[] currentPlayer = {null};

        Runnable loadTeaser = () -> {
            if (currentPlayer[0] != null) {
                currentPlayer[0].stop();
            }

            Teaser current = teasers[currentIndex[0]];
            subtitle.setText(current.judul);

            URL videoUrl = getClass().getResource(current.videoPath);
            if (videoUrl != null) {
                try {
                    Media media = new Media(videoUrl.toExternalForm());
                    MediaPlayer player = new MediaPlayer(media);
                    mediaView.setMediaPlayer(player);
                    player.setAutoPlay(true);
                    currentPlayer[0] = player;
                } catch (Exception e) {
                    System.out.println("Gagal memuat video: " + e.getMessage());
                }
            } else {
                System.out.println("Video tidak ditemukan: " + current.videoPath);
            }
        };

        Button btnPrev = new Button("<<");
        Button btnNext = new Button(">>");

        btnPrev.setOnAction(e -> {
            currentIndex[0] = (currentIndex[0] - 1 + teasers.length) % teasers.length;
            loadTeaser.run();
        });

        btnNext.setOnAction(e -> {
            currentIndex[0] = (currentIndex[0] + 1) % teasers.length;
            loadTeaser.run();
        });

        HBox navBox = new HBox(10, btnPrev, btnNext);
        navBox.setAlignment(Pos.CENTER);

        teaserBox.getChildren().addAll(title, subtitle, mediaView, navBox);
        loadTeaser.run();

        return teaserBox;
    }
}