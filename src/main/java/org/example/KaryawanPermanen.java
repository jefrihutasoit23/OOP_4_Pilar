package org.example;

public class KaryawanPermanen extends Karyawan{
    @Override
    int hitungGaji(int hari) {
        int upahPerHari = 500000;
        return (hari*upahPerHari);
    }

    @Override
    String jenisKaryawan() {
        return "Permanen";
    }

    public KaryawanPermanen(String nama, int id){

        super(nama, id);
    }
}
