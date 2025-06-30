// DataStore.java
package model;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    private static DataStore instance;

    private List<Konser> konserList;
    private List<Pemesanan> pemesananList;

    private DataStore() {
        konserList = new ArrayList<>();
        pemesananList = new ArrayList<>();
    }

    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    public List<Konser> getKonserList() {
        return konserList;
    }

    public void setKonserList(List<Konser> konserList) {
        this.konserList = konserList;
    }

    public List<Pemesanan> getPemesananList() {
        return pemesananList;
    }

    public void addPemesanan(Pemesanan pemesanan) {
        pemesananList.add(pemesanan);
    }

    public void clearAll() {
        konserList.clear();
        pemesananList.clear();
    }
}
