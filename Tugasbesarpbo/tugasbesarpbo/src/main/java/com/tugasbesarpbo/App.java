package com.tugasbesarpbo;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args ){

       Tokonugget darel = new Tokonugget();
       
        boolean uwau = false;
        Scanner scanner = new Scanner(System.in);
        
        while (!uwau) {
            darel.tampilkanMenu();
            int pilihan = scanner.nextInt();
            scanner.nextLine();  // Membersihkan buffer

            switch (pilihan) {
                case 1: 
                    darel.masukkandatapembelian();
                    break;
                case 2:
                    darel.updatedatapembelian();
                    break;
                case 3:
                    darel.hapusdatapembelian();
                    break;
                case 4:
                    System.out.println("Terima kasih! Keluar dari program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih 1, 2, 3, atau 4");
            }
        }
    }
}    

