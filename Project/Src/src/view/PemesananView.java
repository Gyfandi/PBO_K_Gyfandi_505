package view;

import controller.AppController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import model.Konser;
import model.Pemesanan;

import java.util.HashSet;
import java.util.Set;

public class PemesananView {
    private StackPane root;
    private Stage stage;

    private static Set<String> kursiTerisi = new HashSet<>();
    private final Set<String> kursiDipilihSet = new HashSet<>();

    public PemesananView(Stage stage, Konser konser) {
        this.stage = stage;

        root = new StackPane();
        BackgroundFill gradientFill = new BackgroundFill(
                new LinearGradient(
                        0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                        new Stop(0, Color.SKYBLUE),
                        new Stop(1, Color.web("#F9F6EE"))
                ),
                CornerRadii.EMPTY,
                Insets.EMPTY
        );
        root.setBackground(new Background(gradientFill));

        VBox content = new VBox(10);
        content.setPadding(new Insets(30));
        content.setMaxWidth(550);
        content.setAlignment(Pos.CENTER_LEFT);
        content.setStyle("""
            -fx-background-color: rgba(255,255,255,0.85);
            -fx-background-radius: 10;
            -fx-text-fill: black;
            -fx-font-size: 14px;
            -fx-font-family: Arial;
        """);

        TextField nama = new TextField();
        TextField email = new TextField();
        TextField telp = new TextField();
        TextArea alamat = new TextArea();
        alamat.setPrefRowCount(3);
        ComboBox<String> jam = new ComboBox<>();
        jam.getItems().addAll("19:00", "20:00", "21:00");

        Label kursiLabel = new Label("Pilih Kursi (maks. 5):");
        kursiLabel.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");

        GridPane kursiGrid = new GridPane();
        kursiGrid.setHgap(5);
        kursiGrid.setVgap(5);
        kursiGrid.setPadding(new Insets(10));
        kursiGrid.setAlignment(Pos.CENTER_LEFT);

        int cols = 10;
        for (int i = 0; i < 100; i++) {
            String nomor = String.valueOf(i + 1);
            Button kursiBtn = new Button(nomor);
            kursiBtn.setMinWidth(45);

            if (kursiTerisi.contains(nomor)) {
                kursiBtn.setDisable(true);
                kursiBtn.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            } else {
                kursiBtn.setStyle("-fx-background-color: lightgreen; -fx-text-fill: black;");

                kursiBtn.setOnAction(e -> {
                    if (kursiDipilihSet.contains(nomor)) {
                        kursiDipilihSet.remove(nomor);
                        kursiBtn.setStyle("-fx-background-color: lightgreen; -fx-text-fill: black;");
                    } else {
                        if (kursiDipilihSet.size() >= 5) {
                            new Alert(Alert.AlertType.WARNING, "Maksimal hanya bisa memilih 5 kursi!").show();
                            return;
                        }
                        kursiDipilihSet.add(nomor);
                        kursiBtn.setStyle("-fx-background-color: deepskyblue; -fx-text-fill: white;");
                    }
                });
            }

            kursiGrid.add(kursiBtn, i % cols, i / cols);
        }

        Button submit = new Button("Selesai Pesan Tiket");
        submit.setStyle("-fx-text-fill: black;");
        submit.setOnAction(e -> {
            if (nama.getText().isEmpty() || telp.getText().isEmpty() || jam.getValue() == null || kursiDipilihSet.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Harap lengkapi semua isian dan pilih minimal 1 kursi!").show();
                return;
            }

            for (String k : kursiDipilihSet) {
                if (kursiTerisi.contains(k)) {
                    new Alert(Alert.AlertType.ERROR, "Kursi nomor " + k + " sudah ditempati.").show();
                    return;
                }
            }

            kursiTerisi.addAll(kursiDipilihSet);

            Pemesanan p = new Pemesanan(
                    nama.getText(),
                    email.getText(),
                    telp.getText(),
                    alamat.getText(),
                    konser,
                    jam.getValue(),
                    String.join(", ", kursiDipilihSet)
            );
            AppController.getInstance().setCurrentPemesanan(p);

            stage.setScene(new Scene(new UserPanelView(stage).getView(), 900, 600));
        });

        Button back = new Button("Kembali");
        back.setStyle("-fx-text-fill: black;");
        back.setOnAction(e -> {
            stage.setScene(new Scene(new KonserListView(stage).getView(), 900, 600));
        });

        HBox tombolBox = new HBox(10, submit, back);
        tombolBox.setAlignment(Pos.CENTER_RIGHT);

        content.getChildren().addAll(
                labelHitam("Pemesanan Tiket Untuk: " + konser.getJudul()),
                labelHitam("Nama:"), nama,
                labelHitam("Email:"), email,
                labelHitam("Telp:"), telp,
                labelHitam("Alamat:"), alamat,
                labelHitam("Jam:"), jam,
                kursiLabel, kursiGrid,
                tombolBox
        );

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(20));
        scrollPane.setStyle("-fx-background: transparent;");

        root.getChildren().add(scrollPane);
    }

    private Label labelHitam(String teks) {
        Label label = new Label(teks);
        label.setStyle("-fx-text-fill: black;");
        return label;
    }

    public StackPane getView() {
        return root;
    }
}