package com.tugasbesarpbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.*;


public class Tokonugget implements interfaceJDBC {
    String namapelanggan ;
    String nomortelepon ;
    String alamat ;
    String item ;
    Integer jumlah ;
    Integer harga ;

    public Tokonugget(String namapelanggan, String nomortelepon, String alamat){
        this.namapelanggan=namapelanggan;
        this.nomortelepon=nomortelepon;
        this.alamat=alamat;
    }

    public Tokonugget(){
    }

    public void setNama(String nama){
        this.namapelanggan = nama;
    }

    public void setNomor(String nomor){
        this.nomortelepon = nomor;
    }

    public void setalamat(String nomor){
        this.alamat = alamat;
    }

    public void tampilkanMenu(){
        System.out.println("\nMenu:");
            System.out.println("1. Tambah data");
            System.out.println("2. Update data");
            System.out.println("3. Hapus data");
            System.out.println("4. Keluar");

            System.out.print("Pilih menu (1/2/3/4)!: ");
    }
    


    @Override
    public void masukkandatapembelian(){

        Scanner nuzet = new Scanner(System.in);
        System.out.println("Masukkan nama: ");
        String namapelanggan = nuzet.nextLine();
        System.out.println("Masukkan item: ");
        String item = nuzet.nextLine();
        System.out.println("Masukkan jumlah: " );
        Integer jumlah = nuzet.nextInt();
        System.out.println("Masukkan harga: " );
        Integer harga = nuzet.nextInt();
        Integer totalharga = jumlah * harga;

        System.out.println("\t nuzet berhasil ditambahkan");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("nama: " + namapelanggan);
        System.out.println("item: " + item);
        System.out.println("jumalah: " + jumlah);
        System.out.println("total harga: " + totalharga);
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");


    
        String URL = "jdbc:mysql://localhost:3306/tbpbo";
        String USER = "root";
        String PASSWORD = "";

        try {
            // Membuat koneksi ke database (ganti dengan informasi koneksi Anda)
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Query untuk menyimpan data pelanggan ke database
            String query = "INSERT INTO pelangga (namapelanggan,item,jumlah,totalharga) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Menyetel parameter query dengan nilai-nilai dari objek Pemesanan
            preparedStatement.setString(1, namapelanggan);
            preparedStatement.setString(2, item);
            preparedStatement.setInt(3 , jumlah);
            preparedStatement.setInt(4 , totalharga);

            
           

            // Eksekusi query
            preparedStatement.executeUpdate();

            // Menutup koneksi
            preparedStatement.close();
            connection.close();

            System.out.println("Data Berhasil Disimpan Ke Database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }    
        

}


 @Override
    public void updatedatapembelian(){

        String URL = "jdbc:mysql://localhost:3306/tbpbo";
        String USER = "root";
        String PASSWORD = "";
        Scanner kremes = new Scanner(System.in);
        System.out.println("Masukkan nama: ");
        String carinamapelanggan=kremes.nextLine();
        

        try {
            // Membuat koneksi ke database (ganti dengan informasi koneksi Anda)
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Query untuk mencari data pelanggan berdasarkan nama
            String queryCari = "SELECT * FROM pelangga WHERE namapelanggan = ?";
            PreparedStatement preparedStatementCari = connection.prepareStatement(queryCari);
            preparedStatementCari.setString(1, carinamapelanggan);

            // Eksekusi query pencarian
            ResultSet resultSet = preparedStatementCari.executeQuery();

            // Cek apakah data ditemukan
            if (resultSet.next()) {
                Scanner gemez = new Scanner(System.in);
                // Jika data ditemukan, tampilkan data lama dan minta input data baru
                String namapelangganb = resultSet.getString("namapelanggan");
                String itemb = resultSet.getString("item");
                int  jumlahb = resultSet.getInt("jumlah");
                int totalhargab = resultSet.getInt("totalharga");
                
                System.out.println("");
                System.out.println("namapelanggan \t\t:" + namapelangganb);
                System.out.println("item:" + itemb);
                System.out.println("jumlah \t\t:" + jumlahb);
                System.out.println("totalharga \t\t:" + totalhargab);
                System.out.println("");

                // Minta input data baru
                System.out.println("Masukkan Data Baru:");

                // Input untuk setiap atribut yang diizinkan diubah
                System.out.print("Masukkan nama pelanggan: ");
                String namapelanggan = gemez.next();

                System.out.println("Masukkan item baru: ");
                String itembaru = gemez.next();

                System.out.println("Masukan jumlah baru: ");
                int jumlahbaru = gemez.nextInt();

                int totalhargabaru = jumlahbaru * totalhargab;

                // Query UPDATE untuk mengubah tanggal_reservasi dan durasi
                String queryUpdate = "UPDATE pelangga SET item=?,jumlah=? WHERE namapelanggan = ?";
                PreparedStatement preparedStatementUpdate = connection.prepareStatement(queryUpdate);
                preparedStatementUpdate.setString(1, carinamapelanggan);
                preparedStatementUpdate.setInt(2, totalhargabaru);
               
           

                // Eksekusi query update
                preparedStatementUpdate.executeUpdate();

                System.out.println("Data Pelanggan Berhasil Diupdate.");

                // Menutup koneksi
                preparedStatementCari.close();
                preparedStatementUpdate.close();
                connection.close();
            } else {
                System.out.println("Data pelanggan dengan nama " + carinamapelanggan + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void hapusdatapembelian(){

        Scanner basreng = new Scanner(System.in);

        String URL = "jdbc:mysql://localhost:3306/tbpbo";
        String USER = "root";
        String PASSWORD = "";
        System.out.println("");
        System.out.print("Masukkan Nama Pelanggan: ");
        String carinamapelanggan = basreng.next();

        try {
            // Membuat koneksi ke database (ganti dengan informasi koneksi Anda)
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Query untuk mencari data pelanggan berdasarkan nama
            String queryCari = "SELECT * FROM pelangga WHERE namapelanggan = ?";
            PreparedStatement preparedStatementCari = connection.prepareStatement(queryCari);
            preparedStatementCari.setString(1, carinamapelanggan);

            // Eksekusi query pencarian
            ResultSet resultSet = preparedStatementCari.executeQuery();

            // Cek apakah data ditemukan
            if (resultSet.next()) {
                // Jika data ditemukan, tampilkan data lama dan minta input data baru
                String namapelangganb = resultSet.getString("Nama pelanggan ");
                String itemb = resultSet.getString("Item ");
                Integer jumlahb = resultSet.getInt("Jumlah ");
                int  totalhargab = resultSet.getInt("Total Harga ");
            
                System.out.println("");
                System.out.println("namapelanggan: " + namapelangganb);
                System.out.println("item: " + itemb);
                System.out.println("jumlah: " + jumlahb);
                System.out.println("totalharga: "  + totalhargab);
                System.out.println("");

                String queryHapus = "DELETE FROM buku WHERE judul = ?";
                PreparedStatement preparedStatementHapus = connection.prepareStatement(queryHapus);
                preparedStatementHapus.setString(1, carinamapelanggan);

                preparedStatementHapus.executeUpdate();

                System.out.println("Data berhasil dihapus");
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                System.out.println("namapelanggan: " + namapelangganb);
                System.out.println("item: " + itemb);
                System.out.println("jumlah: " + jumlahb);
                System.out.println("totalharga: " + totalhargab);
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                
                } else {
                    System.out.println("Data tidak ditemukan");
                }
            // Menutup koneksi
            resultSet.close();
        preparedStatementCari.close();
        connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}





