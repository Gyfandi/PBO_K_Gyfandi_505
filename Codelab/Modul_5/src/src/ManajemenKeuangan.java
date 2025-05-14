import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ManajemenKeuangan {
    public static void main(String[] args) {
        ArrayList<Keuangan> daftarRekening = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean lanjut = true;

        while (lanjut) {
            System.out.println("\n===== Menu Manajemen Keuangan =====");
            System.out.println("1. Tambah Rekening Baru");
            System.out.println("2. Tampilkan Semua Rekening");
            System.out.println("3. Kurangi Saldo Rekening");
            System.out.println("0. Keluar");
            System.out.print("Pilih opsi: ");

            int pilihan = -1;
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka!");
                scanner.nextLine();
                continue;
            }

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama rekening: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan saldo awal: ");
                    try {
                        int saldo = scanner.nextInt();
                        scanner.nextLine();
                        daftarRekening.add(new Keuangan(nama, saldo));
                        System.out.println("Rekening '" + nama + "' berhasil ditambahkan.");
                    } catch (InputMismatchException e) {
                        System.out.println("Input saldo harus berupa angka!");
                        scanner.nextLine();
                    }
                    break;

                case 2:
                    if (daftarRekening.isEmpty()) {
                        System.out.println("Tidak ada data rekening.");
                    } else {
                        System.out.println("\n--- Daftar Rekening ---");
                        for (int i = 0; i < daftarRekening.size(); i++) {
                            Keuangan k = daftarRekening.get(i);
                            System.out.println(i + ". Nama: " + k.getNama() + ", Saldo: " + k.getSaldo());
                        }
                        System.out.println("-----------------------");
                    }
                    break;

                case 3:
                    if (daftarRekening.isEmpty()) {
                        System.out.println("Tidak ada rekening untuk dikurangi saldonya.");
                        break;
                    }

                    System.out.println("\n--- Daftar Rekening (Pilih untuk Kurangi Saldo) ---");
                    for (int i = 0; i < daftarRekening.size(); i++) {
                        Keuangan k = daftarRekening.get(i);
                        System.out.println(i + ". " + k.getNama() + " (Saldo: " + k.getSaldo() + ")");
                    }

                    try {
                        System.out.print("Masukkan nomor indeks rekening: ");
                        int indeks = scanner.nextInt();
                        System.out.print("Masukkan jumlah saldo yang akan diambil: ");
                        int jumlah = scanner.nextInt();
                        scanner.nextLine();

                        Keuangan rekening = daftarRekening.get(indeks);
                        if (jumlah > rekening.getSaldo()) {
                            throw new SaldoTidakCukupException("Saldo untuk " + rekening.getNama() + " hanya tersisa " + rekening.getSaldo());
                        }

                        rekening.setSaldo(rekening.getSaldo() - jumlah);
                        System.out.println("Saldo rekening '" + rekening.getNama() + "' berhasil dikurangi. Sisa saldo: " + rekening.getSaldo());

                    } catch (InputMismatchException e) {
                        System.out.println("Input harus berupa angka!");
                        scanner.nextLine();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Indeks tidak valid!");
                    } catch (SaldoTidakCukupException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Terima kasih! Program berakhir.");
                    lanjut = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }

        scanner.close();
    }
}