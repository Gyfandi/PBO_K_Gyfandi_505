package sewa;

public class PS4 extends Konsol {
    private String jenisGame;

    public PS4(String namaKonsol, String penyedia, String jenisGame) {
        super(namaKonsol, penyedia);
        this.jenisGame = jenisGame;
    }

    @Override
    public void displayInfo() {
        System.out.println("PS4: " + namaKonsol + " oleh " + penyedia + " (Game: " + jenisGame + ")");
    }
}