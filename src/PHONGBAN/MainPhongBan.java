package PHONGBAN;

import Center.DataCenter;
import java.util.Scanner;

public class MainPhongBan {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Load dữ liệu từ file vào DataCenter
        System.out.println("Dang tai du lieu...");
        DataCenter.dsNhanSu.docFileVaoDs(); // Cần cho validation mã NS
        DataCenter.qlPhongBan.docTuFilePhongBan();
        DataCenter.qlPhongBan.docTuFileDeAn();
        DataCenter.qlPhongBan.docTuFilePhanCong();
        System.out.println("Tai du lieu thanh cong!\n");

        while (true) {
            System.out.println();
            System.out.println("--- Menu QuanLyPhongBan ---");
            System.out.println("1. Thêm Phòng ban");
            System.out.println("2. Thêm Đề án");
            System.out.println("3. Thêm Phân công");
            System.out.println("4. Hiển thị danh sách");
            System.out.println("5. Xóa phòng ban");
            System.out.println("6. Xóa đề án");
            System.out.println("7. Lưu dữ liệu");
            System.out.println("0. Thoát (tự động lưu)");
            System.out.print("Chọn (0-7): ");
            String c = sc.nextLine().trim();
            switch (c) {
                case "1": DataCenter.qlPhongBan.themPhongBan(); break;
                case "2":
                    DataCenter.qlPhongBan.themDeAn();
                    break;
                case "3": DataCenter.qlPhongBan.themPhanCong(); break;
                case "4":
                    DataCenter.qlPhongBan.xuatPhongBan();
                    DataCenter.qlPhongBan.xuatDeAn();
                    DataCenter.qlPhongBan.xuatPhanCong();
                    break;
                case "5": DataCenter.qlPhongBan.xoaPhongBan(); break;
                case "6": DataCenter.qlPhongBan.xoaDeAn(); break;
                case "7":
                    System.out.println("Dang luu du lieu...");
                    DataCenter.qlPhongBan.ghiToanBoPhongBan();
                    DataCenter.qlPhongBan.ghiToanBoDeAn();
                    DataCenter.qlPhongBan.ghiToanBoPhanCong();
                    System.out.println("Luu du lieu thanh cong!");
                    break;
                case "0":
                    System.out.println("Dang luu du lieu truoc khi thoat...");
                    DataCenter.qlPhongBan.ghiToanBoPhongBan();
                    DataCenter.qlPhongBan.ghiToanBoDeAn();
                    DataCenter.qlPhongBan.ghiToanBoPhanCong();
                    System.out.println("Da luu! Quay ve menu chinh.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
            