//Kelas heewan
public class Main{
    public static void main(String[]args){
        //Membuat objek hewan 1 dan hewan 2
        Hewan hewan1 = new Hewan();
        Hewan hewan2 = new Hewan();

        hewan1.nama = "Kucing";
        hewan2.nama = "Anjing";

        hewan1.jenis = "Mamalia";
        hewan2.jenis = "Mamalia";

        hewan1.suara = "Nyann~~";
        hewan2.suara = "Woof-Woof!!";

        //Memanggil metode tampilkanInfo()
        hewan1.tampilkanInfo();
        hewan2.tampilkanInfo();
    }
}