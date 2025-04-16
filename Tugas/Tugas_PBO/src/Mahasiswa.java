public class Mahasiswa {
    public String nama = "Gyfandi Mecca Firstson Cusy";
    public String nim = "202410370110505";

    public boolean login(String nama, String nim) {
        return this.nama.equalsIgnoreCase(nama) && this.nim.equals(nim);
    }

    public void displayInfo() {
        System.out.println("Nama: " + nama);
        System.out.println("NIM: " + nim);
    }
}