package model;

import java.time.LocalDateTime;

public class Pembayaran {

    private String idPembayaran;
    private String metodePembayaran;
    private double jumlah;
    private LocalDateTime tanggalPembayaran;
    private boolean statusLunas;
    private boolean diskonDiberikan; // ➕ Menyimpan apakah diskon diberikan

    // Constructor utama (tanpa informasi diskon)
    public Pembayaran(String idPembayaran, String metodePembayaran, double jumlah, LocalDateTime tanggalPembayaran) {
        this.idPembayaran = idPembayaran;
        this.metodePembayaran = metodePembayaran;
        this.jumlah = jumlah;
        this.tanggalPembayaran = tanggalPembayaran;
        this.statusLunas = false;
        this.diskonDiberikan = false; // default tidak ada diskon
    }

    // Constructor tambahan (jika ingin langsung set diskonDiberikan)
    public Pembayaran(String idPembayaran, String metodePembayaran, double jumlah, LocalDateTime tanggalPembayaran, boolean diskonDiberikan) {
        this.idPembayaran = idPembayaran;
        this.metodePembayaran = metodePembayaran;
        this.jumlah = jumlah;
        this.tanggalPembayaran = tanggalPembayaran;
        this.statusLunas = false;
        this.diskonDiberikan = diskonDiberikan;
    }

    // Getter dan Setter

    public String getIdPembayaran() {
        return idPembayaran;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public double getJumlah() {
        return jumlah;
    }

    public LocalDateTime getTanggalPembayaran() {
        return tanggalPembayaran;
    }

    public boolean isStatusLunas() {
        return statusLunas;
    }

    public void konfirmasiLunas() {
        this.statusLunas = true;
    }

    public boolean isDiskonDiberikan() {
        return diskonDiberikan;
    }

    public void setDiskonDiberikan(boolean diskonDiberikan) {
        this.diskonDiberikan = diskonDiberikan;
    }


}
