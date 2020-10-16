package com.example.aplikasisemar;

public class Konfigurasi {

    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
    public static final String URL_ADD="http://192.168.43.126/KKI2Coba/tambahPgw.php";
    public static final String URL_GET_ALL = "http://192.168.43.126/KKI2Coba/tampilSemuaPgw.php";
    public static final String URL_GET_EMP = "http://192.168.43.126/KKI2Coba/tampilPgw.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.43.126/KKI2Coba/updatePgw.php";
    public static final String URL_DELETE_EMP = "http://192.168.43.126/KKI2Coba/hapusPgw.php?id=";
    public static final String URL_GET_Tanggap = "http://192.168.43.126/KKI2Coba/tanggapan.php";
    public static final String URL_GET_TanggapEMP = "http://192.168.43.126/KKI2Coba/tampilTanggapan.php?id=";
    public static final String URL_GET_ASPIRASI = "http://192.168.43.126/KKI2Coba/aspirasi.php";
    public static final String URL_GET_AspirasiEMP = "http://192.168.43.126/KKI2Coba/tampilaspirasi.php?id=";
    public static final String URL_ADDAspirasi="http://192.168.43.126/KKI2Coba/tambahaspirasi.php";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAMA = "nama";
    public static final String KEY_EMP_KATEGORI = "kategori";
    public static final String KEY_EMP_JENIS = "jenis"; //desg itu variabel untuk posisi
    public static final String KEY_EMP_DESKRIPSI = "deskripsi"; //salary itu variabel untuk gajih
    public static final String KEY_EMP_TANGGAPAN = "tanggapan"; //salary itu variabel untuk gajih
    public static final String KEY_EMP_CREATED = "created"; //salary itu variabel untuk gajih
    public static final String KEY_EMP_UPDATED = "updated"; //salary itu variabel untuk gajih

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_KATEGORI = "kategori";
    public static final String TAG_JENIS = "jenis";
    public static final String TAG_DESKRIPSI = "deskripsi";
    public static final String TAG_TANGGAPAN = "tanggapan";
    public static final String TAG_CREATED = "created";
    public static final String TAG_UPDATED = "updated";



    //ID karyawan
    //emp itu singkatan dari Employee
    public static final String EMP_ID = "emp_id";
}



