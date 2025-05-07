package com.praktikum.users;

import com.praktikum.actions.AdminActions;
import java.util.Scanner;

public class Admin extends User implements AdminActions {
    private String username;
    private String password;

    public Admin(String nama, String nim, String username, String password) {
        super(nama, nim);
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean login(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }

    @Override
    public void displayInfo() {
        System.out.println("Login Admin berhasil!");
        super.displayInfo();
        System.out.println("Username: " + username);
    }

    @Override
    public void displayAppMenu() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Kelola Laporan Barang");
            System.out.println("2. Kelola Data Mahasiswa");
            System.out.println("0. Logout");
            System.out.print("Masukkan pilihan: ");
            int pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    manageItems();
                    break;
                case 2:
                    manageUsers();
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
    public void manageItems() {
        System.out.println("\nMengelola Laporan Barang:");
        // Menampilkan laporan barang yang diajukan oleh mahasiswa
        Mahasiswa mahasiswa = new Mahasiswa("Gyfandi Mecca Firstson Cusy", "202410370110505");
        mahasiswa.viewReportedItems();  // Memanggil method viewReportedItems
    }

    @Override
    public void manageUsers() {
        System.out.println("Fitur Kelola Mahasiswa Belum Tersedia");
    }

    public void logout() {
        System.out.println("Logout berhasil. Terima kasih telah menggunakan aplikasi.");
    }
}