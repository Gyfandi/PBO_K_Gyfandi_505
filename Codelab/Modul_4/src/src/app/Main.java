package app;
import sewa.*;

public class Main {
    public static void main(String[] args) {
        Konsol ps4 = new PS4("PS4 Pro", "Sony", "FC25");
        Konsol ps5 = new PS5("PS5 Slim", "Sony", "5K Gaming");

        ps4.displayInfo();
        ps5.displayInfo();
        System.out.println();

        Pelanggan p1 = new Pelanggan("Gyfandi Mecca", "K505");
        Pelanggan p2 = new Pelanggan("Raihan Naja", "K504");

        p1.tampilkanInfo();
        p2.tampilkanInfo();
        System.out.println();

        p1.sewaKonsol("PS4 Pro");
        p2.sewaKonsol("PS5 Slim", 5);
        System.out.println();

        p1.kembalikanKonsol("PS4 Pro");
        p2.kembalikanKonsol("PS5 Slim");
    }
}