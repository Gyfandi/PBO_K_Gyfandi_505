package com.praktikum.data;

import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;
import java.util.ArrayList;

public class DataStore {
    public static ArrayList<User> userList = new ArrayList<>();
    public static ArrayList<Item> reportedItems = new ArrayList<>();

    static {
        userList.add(new Admin("Admin505", "202410370110505", "Admin505", "Password505"));
        userList.add(new Mahasiswa("Gyfandi Mecca Firstson Cusy", "202410370110505"));
        userList.add(new Mahasiswa("Farel Bayuputra Irawan", "202410370110496"));

        reportedItems.add(new Item("HP", "Meja A-01", "Smartphone", userList.get(1)));
        reportedItems.add(new Item("Dompet", "Meja B-19", "Dompet Pria Kulit Hitam", userList.get(2)));
        reportedItems.get(1).setStatus("Claimed"); // Contoh status claimed untuk Dompet
    }

    public static ArrayList<Item> getReportedItems() {
        return reportedItems;
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }
}