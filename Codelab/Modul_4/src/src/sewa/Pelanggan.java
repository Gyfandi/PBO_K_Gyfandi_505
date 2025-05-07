package sewa;

public class Pelanggan implements Penyewaan {
    private String nama;
    private String idPelanggan;

    public Pelanggan(String nama, String idPelanggan) {
        this.nama = nama;
        this.idPelanggan = idPelanggan;
    }

    public void sewaKonsol(String namaKonsol) {
        System.out.println(nama + " menyewa konsol " + namaKonsol);
    }

    public void sewaKonsol(String namaKonsol, int durasi) {
        System.out.println(nama + " menyewa \"" + namaKonsol + "\" selama " + durasi + " jam.");
    }

    public void kembalikanKonsol(String namaKonsol) {
        System.out.println(nama + " mengembalikan konsol  " + namaKonsol);
    }

    public void tampilkanInfo() {
        System.out.println("Pelanggan: " + nama + " (ID: " + idPelanggan + ")");
    }
}