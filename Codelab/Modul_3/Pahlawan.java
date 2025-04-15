public class Pahlawan extends KarakterGame {
    public Pahlawan(String name, int kesehatan){
        super(name, kesehatan);
    }

    @Override
    public void serang(KarakterGame target) {
        System.out.println(getNama() + " menyerang " + target.getNama() + " menggunakan Ashura Endless Shackless " + target.getNama() + " Menerima 2000 damage ");
        target.setKesehatan(target.getKesehatan() - 2000);
        System.out.println(target.getNama() + " sekarang memiliki kesehatan " + target.getKesehatan() + " Hp ");
    }
}