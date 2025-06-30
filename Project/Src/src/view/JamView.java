package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class JamView {

    private VBox root;
    private ArrayList<String> daftarJam = new ArrayList<>();

    public JamView(Stage stage) {


        root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Manajemen Jam Konser");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField inputJam = new TextField();
        inputJam.setPromptText("Contoh: 19:00");

        Button tambahBtn = new Button("Tambah Jam");
        ListView<String> listView = new ListView<>();

        tambahBtn.setOnAction(e -> {
            String jamBaru = inputJam.getText();
            if (!jamBaru.isEmpty() && !daftarJam.contains(jamBaru)) {
                daftarJam.add(jamBaru);
                listView.getItems().add(jamBaru);
                inputJam.clear();
            } else {
                new Alert(Alert.AlertType.WARNING, "Jam kosong atau sudah ada.").show();
            }
        });

        Button hapusBtn = new Button("Hapus Jam Terpilih");
        hapusBtn.setOnAction(e -> {
            String selected = listView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                daftarJam.remove(selected);
                listView.getItems().remove(selected);
            }
        });

        Button kembaliBtn = new Button("Kembali ke Menu Administrator");
        kembaliBtn.setOnAction(e -> {
            stage.setScene(new Scene(new AdminPanelView(stage).getView(), 900, 600));
        });

        root.getChildren().addAll(title, inputJam, tambahBtn, listView, hapusBtn, kembaliBtn);
    }

    public VBox getView() {
        return root;
    }
}
