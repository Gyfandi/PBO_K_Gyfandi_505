package com.praktikum.users;

import com.praktikum.actions.MahasiswaActions;
import java.util.Scanner;

public class Mahasiswa extends User implements MahasiswaActions {

    public Mahasiswa(String nama, String nim) {
        super(nama, nim);
    }

    @Override
    public boolean login(String nama, String nim) {
        return getNama().equalsIgnoreCase(nama) && getNim().equals(nim);
    }

    @Override
    public void displayInfo() {
        System.out.println("Login Mahasiswa berhasil!");
        super.displayInfo();
    }

    @Override
    public void displayAppMenu() {
        Scanner input = new Scanner(System.in);

        while (true) {  // Menambahkan loop agar mahasiswa dapat memilih beberapa opsi
            System.out.println("\n1. Laporkan Barang Temuan/Hilang");
            System.out.println("2. Lihat Daftar Laporan");
            System.out.println("0. Logout");
            System.out.print("Masukkan pilihan: ");
            int pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    reportItem();
                    break;
                case 2:
                    viewReportedItems();
                    break;
                case 0:
                    logout();
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    @Override
    public void reportItem() {
        Scanner input = new Scanner(System.in);

        System.out.println("Masukkan Nama Barang: ");
        String namaBarang = input.nextLine();

        System.out.println("Masukkan Deskripsi Barang: ");
        String deskripsiBarang = input.nextLine();

        System.out.println("Masukkan Lokasi Terakhir/Dimenangkan: ");
        String lokasi = input.nextLine();

        // Menampilkan konfirmasi input
        System.out.println("\nLaporan Barang:");
        System.out.println("Nama Barang: " + namaBarang);
        System.out.println("Deskripsi Barang: " + deskripsiBarang);
        System.out.println("Lokasi Terakhir/Dimenangkan: " + lokasi);
    }

    @Override
    public void viewReportedItems() {
        // Menampilkan daftar barang yang dilaporkan, saat ini hanya placeholder
        System.out.println("Fitur Lihat Laporan Belum Tersedia");
    }

    public void logout() {
        System.out.println("Logout berhasil. Terima kasih telah menggunakan aplikasi.");
    }
}