package org.example;

public class KaryawanKontrak extends Karyawan{
    @Override
    int hitungGaji(int hari) {
        int upahPerHari = 300000;
        return (hari*upahPerHari);
    }

    @Override
    String jenisKaryawan() {
        return "Kontrak";
    }

    public KaryawanKontrak(String nama, int id){
        super(nama, id);
    }
}
