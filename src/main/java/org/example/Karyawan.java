package org.example;

abstract class Karyawan {
    protected String nama;
    protected int id;

    abstract int hitungGaji(int gaji);

    abstract String jenisKaryawan();

    public Karyawan(String nama, int id){
        this.nama = nama;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }
}
