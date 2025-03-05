import java.util.Scanner;

public class Tugas1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("pilihan login: ");
        System.out.println("1. Admin");
        System.out.println("2. Mahasiswa");
        System.out.println("Masukkan pilihan: ");
        int pilihan = input.nextInt();
        input.nextLine();

        if (pilihan == 1) {
            System.out.println("Masukkan username: ");
            String username = input.nextLine();
            System.out.println("Masukkan password: ");
            String password = input.nextLine();

            String NIM = "505";
            String usernameBenar = "Admin" + NIM;
            String passwordBenar = "Password" + NIM;

            if (username.equals(usernameBenar) && password.equals(passwordBenar)) {
                System.out.println("Login Admin berhasil!");
            } else{
                System.out.println("Login gagal! Username atau password salah.");
            }
        } else if (pilihan == 2) {
            System.out.println("Masukkan nama: ");
            String nama = input.nextLine();
            System.out.println("Masukkan NIM: ");
            String nim = input.nextLine();

            String namaBenar = "Gyfandi Mecca Firstson Cusy";
            String nimBenar = "202410370110505";

            if (nama.equals(namaBenar) && nim.equals(nimBenar)) {
                System.out.println("Login Mahasiswa berhasil!");
                System.out.println("Nama: " + nama);
                System.out.println("NIM: " + nim);
            } else {
                System.out.println("Login gagal! Nama atau NIM salah.");
            }
        } else {
            System.out.println("Pilihan tidak valid.");
        }
        input.close();
    }
}