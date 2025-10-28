package HSNS;

import java.util.Scanner;
import Center.DataCenter;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║   HỆ THỐNG QUẢN LÝ HỒ SƠ NHÂN SỰ          ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        System.out.println("\nDang tai du lieu he thong...");
        
        // Đọc danh sách Nhân sự (cần cho validation mã NS)
        try {
            DataCenter.dsNhanSu.docFileVaoDs();
            System.out.println("Da tai danh sach nhan su tu file!");
        } catch (Exception e) {
            System.out.println("Khong tim thay file nhan su. Validation ma NS se khong hoat dong.");
        }
        
        // Đọc Chi tiết NS và Chức vụ
        try {
            DataCenter.dsHSNS.docFile();
            System.out.println("Doc file hoan tat!");
            System.out.println("So luong Chi tiet Nhan su: " + DataCenter.dsHSNS.getSoLuongCTNS());
            System.out.println("So luong Chuc vu: " + DataCenter.dsHSNS.getSoLuongCV());
        } catch (Exception e) {
            System.out.println("Chua co du lieu hoac file khong ton tai. Bat dau voi du lieu rong.");
        }
        
        // Gọi menu chính
        DataCenter.dsHSNS.menu();
        
        // Lưu dữ liệu khi thoát
        System.out.println("\nDang luu du lieu...");
        try {
            DataCenter.dsHSNS.ghiFile();
            System.out.println("Du lieu da duoc luu vao file quanlyhsnhansu.txt");
        } catch (Exception e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }
        
        System.out.println("\nQuay ve menu chinh...");
        return;
    }
}

