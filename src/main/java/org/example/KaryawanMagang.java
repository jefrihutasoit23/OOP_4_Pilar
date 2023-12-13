package org.example;

public class KaryawanMagang extends Karyawan{
    @Override
    int hitungGaji(int hari) {
        int upahPerHari = 200000;
        return (hari*upahPerHari);
    }

    @Override
    String jenisKaryawan() {
        return "Magang";
    }

    public KaryawanMagang(String nama, int id){
        super(nama, id);
    }
}
