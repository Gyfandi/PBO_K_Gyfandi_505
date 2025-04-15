public class Musuh extends KarakterGame {
    public Musuh(String nama, int kesehatan) {
        super(nama, kesehatan);
    }

    @Override
    public void serang(KarakterGame target) {
        System.out.println(getNama() + " menyerang " + target.getNama() + " menggunakan Undying Ancient Finger! " + target.getNama() + " menerima 15000 damage ");
        target.setKesehatan(target.getKesehatan() - 15000);
        System.out.println(target.getNama() + " sekarang memiliki kesehatan " + target.getKesehatan() + " Hp ");

        System.out.println(getNama() + " menyerang  " + target.getNama() + " lagi menggunakan Extreme Life Dao " + target.getNama() + " menerima 50000 damage ");
        target.setKesehatan(target.getKesehatan() - 50000);
        System.out.println(target.getNama() + " sekarang memiliki kesehatan " + target.getKesehatan() + " Hp ");

        System.out.println(target.getNama() + " Melarikan diri!");
    }
}