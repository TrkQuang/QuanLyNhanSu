package Thuong;
import java.util.Scanner;
import Center.DataCenter;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║     HE THONG QUAN LY THUONG NHAN SU          ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        
        // Gọi menu chính (dữ liệu đã được load bởi App.java)
        menu();
        
        System.out.println("\nQuay ve menu chinh!");
    }
    
    private static void menu() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║        MENU QUAN LY THUONG               ║");
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║  1. Quan ly Danh muc thuong              ║");
            System.out.println("║  2. Quan ly Chi tiet thuong              ║");
            System.out.println("║  3. Thong ke thuong                      ║");
            System.out.println("║  0. Thoat va luu du lieu                 ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.print("Chon chuc nang: ");
            
            String choice = sc.nextLine();
            
            switch (choice) {
                case "1":
                    menuDanhMucThuong();
                    break;
                case "2":
                    menuChiTietThuong();
                    break;
                case "3":
                    menuThongKe();
                    break;
                case "0":
                    System.out.println("Dang thoat...");
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }
    
    private static void menuDanhMucThuong() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║       QUAN LY DANH MUC THUONG            ║");
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║  1. Xuat danh sach Danh muc thuong       ║");
            System.out.println("║  2. Them Danh muc thuong moi             ║");
            System.out.println("║  3. Sua Danh muc thuong                  ║");
            System.out.println("║  4. Xoa Danh muc thuong                  ║");
            System.out.println("║  5. Tim kiem Danh muc thuong theo ten    ║");
            System.out.println("║  0. Quay lai                             ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.print("Chon chuc nang: ");
            
            String choice = sc.nextLine();
            
            switch (choice) {
                case "1":
                    DataCenter.dsdmt.xuatDM();
                    break;
                case "2":
                    DataCenter.dsdmt.themDM();
                    break;
                case "3":
                    DataCenter.dsdmt.suaDM();
                    break;
                case "4":
                    DataCenter.dsdmt.xoaDM();
                    break;
                case "5":
                    DataCenter.dsdmt.timKiemTheoTen();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }
    
    private static void menuChiTietThuong() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║        QUAN LY CHI TIET THUONG           ║");
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║  1. Xuat tat ca Chi tiet thuong          ║");
            System.out.println("║  2. Xuat Chi tiet thuong theo Ma NS      ║");
            System.out.println("║  3. Them Chi tiet thuong moi             ║");
            System.out.println("║  4. Sua Chi tiet thuong                  ║");
            System.out.println("║  5. Xoa Chi tiet thuong                  ║");
            System.out.println("║  0. Quay lai                             ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.print("Chon chuc nang: ");
            
            String choice = sc.nextLine();
            
            switch (choice) {
                case "1":
                    DataCenter.dsctt.xuatCT();
                    break;
                case "2":
                    DataCenter.dsctt.xuatTheoMaNS();
                    break;
                case "3":
                    DataCenter.dsctt.themCT();
                    break;
                case "4":
                    DataCenter.dsctt.suaCT();
                    break;
                case "5":
                    DataCenter.dsctt.xoaCT();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }
    
    private static void menuThongKe() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║           THONG KE THUONG                ║");
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║  1. Thong ke thuong theo Ma NS           ║");
            System.out.println("║  2. Thong ke tong thuong tat ca          ║");
            System.out.println("║  0. Quay lai                             ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.print("Chon chuc nang: ");
            
            String choice = sc.nextLine();
            
            switch (choice) {
                case "1":
                    DataCenter.dsctt.thongKeThuongTheoNhanSu();
                    break;
                case "2":
                    DataCenter.dsctt.thongKeTongThuong();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }
}