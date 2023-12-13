package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ManajerKaryawan {

    static ArrayList<Karyawan> listKaryawan = new ArrayList<>();
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
                    case ("magang"):
                        listKaryawan.add(new KaryawanMagang(nama, id));
                        break;
                    case ("kontrak"):
                        listKaryawan.add(new KaryawanKontrak(nama, id));
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
        System.out.println("============================");
        System.out.println("APLIKASI MANAJEMEN KARYAWAN");
        System.out.println("============================");
        System.out.println("1. ADD");
        System.out.println("2. DELETE");
        System.out.println("3. FIND");
        System.out.println("4. EXIT");

        int input;
        // validate input
        while(true){
            input = input();
            if (input >= 1  && input <= 4){
                break;
            }else{
                System.out.println("option not available !!");
            }
        }
        switch(input) {
            case 1:
                add();
                break;
            case 2:
                delete();
                break;
            case 3:
                find();
                break;
        }
        writeFile();

    }

    public static void writeFile(){
        try{
            PrintWriter printWriter = new PrintWriter("daftarKaryawan.csv");
            printWriter.println("id, nama, jenis");
            for (Karyawan s : listKaryawan){
                printWriter.printf("%d,%s,%s", s.getId(), s.getNama(), s.jenisKaryawan());
                printWriter.println();
            }
            printWriter.close();
        }catch(Exception e){
            e.getStackTrace();
        }

    }

    public static void add(){
        System.out.print("Masukan Nama : ");
        Scanner userInput = new Scanner(System.in);
        String nama = userInput.nextLine();

        System.out.print("Masukan Jenis Karyawan (permanen/magang/kontrak): ");
        Scanner userInput2 = new Scanner(System.in);
        String jenisKaryawan = userInput2.nextLine().toLowerCase();

        int id = listKaryawan.get(listKaryawan.size() - 1).getId()+1;
        switch (jenisKaryawan){
            case ("permanen"):
                listKaryawan.add(new KaryawanPermanen(nama, id));
                System.out.println("Karyawan berhasil diinput !!");
                break;
            case ("magang"):
                listKaryawan.add(new KaryawanMagang(nama, id));
                System.out.println("Karyawan berhasil diinput !!");
                break;
            case ("kontrak"):
                listKaryawan.add(new KaryawanKontrak(nama, id));
                System.out.println("Karyawan berhasil diinput !!");
                break;
            default:
                System.out.println("Invalid jenis karyawan !!");
        }
        pressAnyKey();
        mainMenu();
    }

    public static void delete(){
        allList();
        System.out.println("(Silahkan pilih id)");
        int id = input();
        boolean result = listKaryawan.removeIf(Karyawan -> Karyawan.getId() == id);
        if (result){
            System.out.println("karyawan berhasil dihapus");
        }else{
            System.out.println("karyawan gagal dihapus");
        }
        pressAnyKey();
        mainMenu();
    }

    public static void find(){
        allList();
        System.out.println("(Silahkan pilih id)");
        int id = input();
        Karyawan targetedKaryawan;
        int flag = 0;
        for (Karyawan s : listKaryawan){
            if (s.getId() == id){
                targetedKaryawan = s;
                System.out.println("nama : "+targetedKaryawan.getNama());
                System.out.println("id : "+targetedKaryawan.getId());
                System.out.printf("gaji : %,d",targetedKaryawan.hitungGaji(30));
                System.out.println();
                System.out.println("jenis karyawan : "+targetedKaryawan.jenisKaryawan());
                flag = 1;
                break;
            }
        }
        if (flag == 0){
            System.out.println("Karayawan tidak ditemukan !!");
        }
        pressAnyKey();
        mainMenu();
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

    public static void allList(){
        System.out.println("===============");
        System.out.printf("%-10s | %-3s", "NAMA", "ID");
        System.out.println();
        System.out.println("===============");
        for(Karyawan s : listKaryawan){
            System.out.printf("%-10s | %-3d",s.getNama(),s.getId());
            System.out.println();
        }
    }

    public static void pressAnyKey(){
        System.out.println("Press any key to continue ...");
        Scanner enter = new Scanner(System.in);
        enter.nextLine();
    }
}