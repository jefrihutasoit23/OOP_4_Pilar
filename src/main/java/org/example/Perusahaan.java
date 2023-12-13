package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Perusahaan {

    static ArrayList<Karyawan> listKaryawan = new ArrayList<>();

    static Map<Integer, Integer> lamaBekerja = new HashMap<Integer, Integer>();
    public static void InsertData(){
        try{
            File myObj = new File("daftarKaryawan.csv");
            Scanner sc = new Scanner(myObj);
            int flag = 0;
            while(sc.hasNextLine()){
                String[] data = sc.nextLine().split(",");
                if (flag == 0){
                    flag = 1;
                    continue;
                }
                int id = Integer.parseInt(data[0]);
                String nama = data[1];
                String jenisKaryawan = data[2].toLowerCase();
                switch (jenisKaryawan){
                    case ("permanen"):
                        listKaryawan.add(new KaryawanPermanen(nama, id));
                        break;
                }
            }
        }catch(Exception e){
            e.getStackTrace();
        }
    }
    public static void main(String[] args) {

        InsertData();
        mainMenu();

    }

    public static void mainMenu(){
        System.out.println("===================================");
        System.out.println("APLIKASI PERHITUNGAN BONUS TAHUNAN");
        System.out.println("===================================");
        System.out.println("1. EDIT LAMA BEKERJA");
        System.out.println("2. DAFTAR BONUS TAHUNAN");
        System.out.println("3. EXIT");

        int input;
        // validate input
        while(true){
            input = input();
            if (input >= 1  && input <= 3){
                break;
            }else{
                System.out.println("option not available !!");
            }
        }
        switch(input) {
            case 1:
                edit();
                break;
            case 2:
                list();
                break;
        }

    }

    public static int input(){
        int inputChoice;
        while (true){
            try{
                System.out.print("=> ");
                Scanner input = new Scanner (System.in);
                inputChoice = input.nextInt();
                break;
            }
            catch(Exception e){
                System.out.println("Invalid choice");
            }
        }
        return inputChoice;
    }

    public static void edit(){
        allList();
        System.out.println("(Silahkan pilih id)");
        int id = input();

        System.out.println("silahkan masukan lama bekerja (tahun): ");
        int periode = input();

        int flag = 0;
        for (Karyawan s : listKaryawan){
            if (s.getId() == id){
                lamaBekerja.put(id, periode);
                System.out.println("Periode bekerja berhasil di tambahkan/diedit !!");
                flag = 1;
                break;
            }
        }
        if (flag == 0){
            System.out.println("karyawan tidak ditemukan / tahun invalid!!");
        }
        pressAnyKey();
        mainMenu();
    }

    public static void list(){
        System.out.println("==============================");
        System.out.printf("%-10s | %-3s | %-10s", "NAMA", "ID", "TOTAL BONUS");
        System.out.println();
        System.out.println("==============================");
        for(Karyawan s : listKaryawan){
            int tahun;
            if (lamaBekerja.get(s.getId()) == null){
                tahun = 0;
            }else {
                tahun = lamaBekerja.get(s.getId());
            }
            int bonus = tahun*1000000;
            System.out.printf("%-10s | %-3d | %-,8d",s.getNama(),s.getId(), bonus);
            System.out.println();
        }
        pressAnyKey();
        mainMenu();
    }
    public static void allList(){
        System.out.println("==============================");
        System.out.printf("%-10s | %-3s | %-10s", "NAMA", "ID", "LAMA BEKERJA");
        System.out.println();
        System.out.println("==============================");
        for(Karyawan s : listKaryawan){
            int tahun;
            if (lamaBekerja.get(s.getId()) == null){
                tahun = 0;
            }else {
                tahun = lamaBekerja.get(s.getId());
            }
            System.out.printf("%-10s | %-3d | %-2d Tahun",s.getNama(),s.getId(), tahun);
            System.out.println();
        }
    }

    public static void pressAnyKey(){
        System.out.println("Press any key to continue ...");
        Scanner enter = new Scanner(System.in);
        enter.nextLine();
    }
}
