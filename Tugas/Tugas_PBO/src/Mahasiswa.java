public class Mahasiswa {
    public String nama;
    public String nim;

    public boolean login(String nama, String nim) {
        return this.nama.equalsIgnoreCase(nama) && this.nim.equals(nim);
    }
}