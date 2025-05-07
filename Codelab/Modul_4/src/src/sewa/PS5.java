package sewa;

public class PS5 extends Konsol {
    private String fitur;

    public PS5(String namaKonsol, String penyedia, String fitur) {
        super(namaKonsol, penyedia);
        this.fitur = fitur;
    }

    @Override
    public void displayInfo() {
        System.out.println("PS5: " + namaKonsol + " oleh " + penyedia + " (Fitur: " + fitur + ")");
    }
}