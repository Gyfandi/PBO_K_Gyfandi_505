public class Keuangan {
    private String nama;
    private int saldo;

    public Keuangan(String nama, int saldo) {
        this.nama = nama;
        this.saldo = saldo;
    }

    public String getNama() {

        return nama;
    }

    public int getSaldo() {

        return saldo;
    }

    public void setSaldo(int saldo) {

        this.saldo = saldo;
    }
}