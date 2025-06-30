package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import model.Pemesanan;
import model.DataStore;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LaporanPenjualanView {
    private VBox root;

    public LaporanPenjualanView(Stage stage) {
        root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Laporan Penjualan Tiket");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        // Ambil data pemesanan
        List<Pemesanan> semuaPemesanan = DataStore.getInstance().getPemesananList();

        // Simpan otomatis ke file lokal saat laporan dibuka
        File fileOtomatis = new File("laporan_penjualan.csv");
        simpanKeFile(fileOtomatis, semuaPemesanan);

        // Hitung total penjualan berdasarkan konser
        Map<String, Long> penjualanPerKonser = semuaPemesanan.stream()
                .collect(Collectors.groupingBy(p -> p.getKonser().getJudul(), Collectors.counting()));

        TableView<Map.Entry<String, Long>> table = new TableView<>();

        TableColumn<Map.Entry<String, Long>, String> colJudul = new TableColumn<>("Judul Musik");
        colJudul.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getKey()));

        TableColumn<Map.Entry<String, Long>, String> colJumlah = new TableColumn<>("Jumlah Terjual");
        colJumlah.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getValue().toString()));

        table.getColumns().addAll(colJudul, colJumlah);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setItems(FXCollections.observableArrayList(penjualanPerKonser.entrySet()));

        Button simpanManualBtn = new Button("Simpan Laporan Manual");
        simpanManualBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Simpan Laporan Penjualan");
            fileChooser.setInitialFileName("laporan_penjualan.csv");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                simpanKeFile(file, semuaPemesanan);
            }
        });

        Button kembaliBtn = new Button("Kembali ke Menu Administrator");
        kembaliBtn.setOnAction(e -> stage.setScene(new Scene(new AdminPanelView(stage).getView(), 900, 600)));

        root.getChildren().addAll(title, table, simpanManualBtn, kembaliBtn);
    }

    private void simpanKeFile(File file, List<Pemesanan> daftarPemesanan) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Nama,Email,Telepon,Alamat,Judul Musik,Tanggal,Jam,Kursi,Harga\n");
            for (Pemesanan p : daftarPemesanan) {
                writer.write(String.join(",",
                        escapeCSV(p.getNama()),
                        escapeCSV(p.getEmail()),
                        escapeCSV(p.getTelp()),
                        escapeCSV(p.getAlamat()),
                        escapeCSV(p.getKonser().getJudul()),
                        escapeCSV(p.getKonser().getTanggal()),
                        escapeCSV(p.getJam()),
                        escapeCSV(p.getKursi()),
                        String.valueOf(p.getKonser().getHarga())
                ));
                writer.write("\n");
            }
            showAlert("Sukses", "Laporan berhasil disimpan di:\n" + file.getAbsolutePath());
        } catch (IOException e) {
            showAlert("Gagal", "Terjadi kesalahan saat menyimpan file.");
            e.printStackTrace();
        }
    }

    private String escapeCSV(String value) {
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public VBox getView() {
        return root;
    }
}
