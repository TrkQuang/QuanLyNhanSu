package Thuong;
import java.util.Scanner;
import Center.DataCenter;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        DataCenter.qlThuong.docFile();
        DataCenter.dsNhanSu.docFileVaoDs();
        while (true) {
            System.out.println("\n===== MENU QUAN LY THUONG =====");
            System.out.println("1. Nhap danh muc thuong");
            System.out.println("2. Nhap chi tiet thuong");
            System.out.println("3. Xuat danh sach danh muc thuong");
            System.out.println("4. Xuat danh sach chi tiet thuong");
            System.out.println("5. Them danh muc thuong");
            System.out.println("6. Them chi tiet thuong");
            System.out.println("7. Sua danh muc thuong");
            System.out.println("8. Sua chi tiet thuong");
            System.out.println("9. Xoa danh muc thuong");
            System.out.println("10. Xoa chi tiet thuong");
            System.out.println("11. Tinh tong thuong theo ma nhan su");
            System.out.println("12. Doc du lieu tu file");
            System.out.println("13. Ghi du lieu vao file");
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang: ");
            int chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    DataCenter.qlThuong.nhapDMThuong();
                    break;
                case 2:
                    DataCenter.qlThuong.nhapCTThuong();
                    break;
                case 3:
                    DataCenter.qlThuong.xuatDMThuong();
                    break;
                case 4:
                    DataCenter.qlThuong.xuatCTThuong();
                    break;
                case 5:
                    DataCenter.qlThuong.themDMThuong();
                    break;
                case 6:
                    DataCenter.qlThuong.themCTThuong();
                    break;
                case 7:
                    DataCenter.qlThuong.suaDMThuong();
                    break;
                case 8:
                    DataCenter.qlThuong.suaCTThuong();
                    break;
                case 9:
                    DataCenter.qlThuong.xoaDMThuong();
                    break;
                case 10:
                    DataCenter.qlThuong.xoaCTThuong();
                    break;
                case 11:
                    DataCenter.qlThuong.tongThuongTheoNhanSu();
                    break;
                case 12:
                    DataCenter.qlThuong.docFile();
                    System.out.println("Da doc du lieu tu file!");
                    System.out.println("So luong danh muc thuong: " + DataCenter.qlThuong.getSoLuongDM());
                    System.out.println("So luong chi tiet thuong: " + DataCenter.qlThuong.getSoLuongCT());
                    break;
                case 13:
                    DataCenter.qlThuong.ghiFile();
                    System.out.println("Da ghi du lieu vao file!");
                    break;
                case 0:
                    DataCenter.qlThuong.ghiFile();
                    System.out.println("Da tu dong luu du lieu!");
                    System.out.println("Kết thúc chương trình!");
                    return;
                default:
                    System.out.println("Chức năng không hợp lệ!");
            }
        }
    }
}