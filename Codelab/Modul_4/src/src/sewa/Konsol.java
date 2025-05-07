package sewa;

public abstract class Konsol {
    protected String namaKonsol;
    protected String penyedia;

    public Konsol(String namaKonsol, String penyedia) {
        this.namaKonsol = namaKonsol;
        this.penyedia = penyedia;
    }

    public abstract void displayInfo();
}