public class Main {
    public static void main(String[] args) {
        KarakterGame umum = new KarakterGame("Karakter Umum", 10000);
        Pahlawan pahlawan = new Pahlawan("Tang San", 150000);
        Musuh musuh = new Musuh("Wang Lin", 200000);

        System.out.println("Status awal:");
        System.out.println(pahlawan.getNama() + " memiliki kesehatan: " + pahlawan.getKesehatan());
        System.out.println(musuh.getNama() + " memiliki kesehatan: " + musuh.getKesehatan());

        pahlawan.serang(musuh);
        musuh.serang(pahlawan);
    }
}