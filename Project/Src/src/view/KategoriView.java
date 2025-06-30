package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class KategoriView {
    private VBox root;
    private ArrayList<String> daftarKategori = new ArrayList<>();

    public KategoriView(Stage stage) {


        root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Manajemen Kategori Musik");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField inputKategori = new TextField();
        inputKategori.setPromptText("Contoh: Jazz");

        Button tambahBtn = new Button("Tambah Kategori");
        ListView<String> listView = new ListView<>();

        tambahBtn.setOnAction(e -> {
            String kategoriBaru = inputKategori.getText();
            if (!kategoriBaru.isEmpty() && !daftarKategori.contains(kategoriBaru)) {
                daftarKategori.add(kategoriBaru);
                listView.getItems().add(kategoriBaru);
                inputKategori.clear();
            } else {
                new Alert(Alert.AlertType.WARNING, "Kategori kosong atau sudah ada.").show();
            }
        });

        Button hapusBtn = new Button("Hapus Kategori Terpilih");
        hapusBtn.setOnAction(e -> {
            String selected = listView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                daftarKategori.remove(selected);
                listView.getItems().remove(selected);
            }
        });

        Button kembaliBtn = new Button("Kembali ke Menu Administrator");
        kembaliBtn.setOnAction(e -> {
            stage.setScene(new Scene(new AdminPanelView(stage).getView(), 900, 600));
        });

        root.getChildren().addAll(title, inputKategori, tambahBtn, listView, hapusBtn, kembaliBtn);
    }

    public VBox getView() {
        return root;
    }
}
