package model;

import actions.Pembayaran;

public class Pemesanan implements Pembayaran {
    private String nama;
    private String email;
    private String telp;
    private String alamat;
    private Konser konser;
    private String jam;
    private String kursi;
    private boolean pembelianPertama = true; // ➕ Default: true

    public Pemesanan(String nama, String email, String telp, String alamat, Konser konser, String jam, String kursi) {
        this.nama = nama;
        this.email = email;
        this.telp = telp;
        this.alamat = alamat;
        this.konser = konser;
        this.jam = jam;
        this.kursi = kursi;
    }

    // Getter
    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getTelp() {
        return telp;
    }

    public String getAlamat() {
        return alamat;
    }

    public Konser getKonser() {
        return konser;
    }

    public String getJam() {
        return jam;
    }

    public String getKursi() {
        return kursi;
    }

    // ➕ Getter dan Setter untuk pembelianPertama
    public boolean isPembelianPertama() {
        return pembelianPertama;
    }

    public void setPembelianPertama(boolean pembelianPertama) {
        this.pembelianPertama = pembelianPertama;
    }

    @Override
        public void prosesPembayaran() {
            System.out.println("Pembayaran untuk konser " + konser.getJudul() + " diproses.");
        }
}
