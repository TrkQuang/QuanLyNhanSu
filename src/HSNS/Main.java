package HSNS;

import java.util.Scanner;
import Center.DataCenter;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║   HE THONG QUAN LY HO SO NHAN SU            ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        
        // Gọi menu chính (dữ liệu đã được load bởi App.java)
        menu();
        
        System.out.println("\nQuay ve menu chinh...");
    }
    
    private static void menu() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║        MENU QUAN LY HO SO NHAN SU        ║");
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║  1. Quan ly Chi tiet Nhan su              ║");
            System.out.println("║  2. Quan ly Than nhan                     ║");
            System.out.println("║  3. Quan ly Chuc vu                       ║");
            System.out.println("║  4. Tim kiem Nhan su theo ten             ║");
            System.out.println("║  0. Quay lai menu chinh                   ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.print("Chon chuc nang: ");
            
            String choice = sc.nextLine();
            
            switch (choice) {
                case "1":
                    menuChiTietNhanSu();
                    break;
                case "2":
                    menuThanNhan();
                    break;
                case "3":
                    menuChucVu();
                    break;
                case "4":
                    DataCenter.dsctns.timTheoTen();
                    break;
                case "0":
                    System.out.println("Quay ve menu chinh...");
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }
    
    private static void menuChiTietNhanSu() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║       QUAN LY CHI TIET NHAN SU           ║");
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║  1. Xuat danh sach Nhan su                ║");
            System.out.println("║  2. Them Nhan su moi                      ║");
            System.out.println("║  3. Sua thong tin Nhan su                 ║");
            System.out.println("║  4. Xoa Nhan su                           ║");
            System.out.println("║  0. Quay lai                              ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.print("Chon chuc nang: ");
            
            String choice = sc.nextLine();
            
            switch (choice) {
                case "1":
                    DataCenter.dsctns.xuatNS();
                    break;
                case "2":
                    DataCenter.dsctns.themNS();
                    break;
                case "3":
                    DataCenter.dsctns.suaNhanSu();
                    break;
                case "4":
                    DataCenter.dsctns.xoaNhanSu();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }
    
    private static void menuThanNhan() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║          QUAN LY THAN NHAN               ║");
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║  1. Xuat tat ca Than nhan                 ║");
            System.out.println("║  2. Xuat Than nhan theo Ma NS             ║");
            System.out.println("║  3. Them Than nhan moi                    ║");
            System.out.println("║  4. Sua thong tin Than nhan               ║");
            System.out.println("║  5. Xoa Than nhan                         ║");
            System.out.println("║  0. Quay lai                              ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.print("Chon chuc nang: ");
            
            String choice = sc.nextLine();
            
            switch (choice) {
                case "1":
                    DataCenter.dstn.xuatTN();
                    break;
                case "2":
                    DataCenter.dstn.xuatTheoMaNS();
                    break;
                case "3":
                    DataCenter.dstn.themTN();
                    break;
                case "4":
                    DataCenter.dstn.suaTN();
                    break;
                case "5":
                    DataCenter.dstn.xoaTN();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }
    
    private static void menuChucVu() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║           QUAN LY CHUC VU                ║");
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║  1. Xuat danh sach Chuc vu                ║");
            System.out.println("║  2. Them Chuc vu moi                      ║");
            System.out.println("║  3. Xoa Chuc vu                           ║");
            System.out.println("║  0. Quay lai                              ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.print("Chon chuc nang: ");
            
            String choice = sc.nextLine();
            
            switch (choice) {
                case "1":
                    DataCenter.dscv.xuatCV();
                    break;
                case "2":
                    DataCenter.dscv.themChucVu();
                    break;
                case "3":
                    DataCenter.dscv.xoaChucVu();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }
}

