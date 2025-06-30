
package model;

public class Konser {
    private String judul;
    private String genre;
    private String durasi;
    private String imagePath;
    private String tanggal;
    private double harga;

    public Konser(String judul, String genre, String durasi, String imagePath,String tanggal,double harga) {
        this.judul = judul;
        this.genre = genre;
        this.durasi = durasi;
        this.imagePath = imagePath;
        this.tanggal = tanggal;
        this.harga = harga;
    }

    public String getJudul() { return judul; }
    public String getGenre() { return genre; }
    public String getDurasi() { return durasi; }
    public String getImagePath() { return imagePath; }
    public String getTanggal() {
        return tanggal;
    }

    public double getHarga() {
        return harga;
    }
}
