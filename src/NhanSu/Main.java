package NhanSu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DanhSachNhanSu dsn = new DanhSachNhanSu();
        DieuChinh dc = new DieuChinh();

        while (true) {
            System.out.println("\n===== QUAN LY NHAN SU - MENU CHINH =====");
            System.out.println("1. Nhap danh sach nhan su (nhieu)");
            System.out.println("2. Them 1 nhan su");
            System.out.println("3. Xuat danh sach nhan su");
            System.out.println("4. Xoa nhan su theo maNS");
            System.out.println("5. Tim nhan su theo maNS");
            System.out.println("6. Chinh sua thong tin nhan su (chuyen toi menu chinh sua)");
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang: ");

            String line = sc.nextLine();
            int chon = -1;
            try { chon = Integer.parseInt(line); } catch (NumberFormatException e) { System.out.println("Nhap khong hop le, vui long nhap so."); continue; }

            switch (chon) {
                case 1:
                    dsn.nhap();
                    break;
                case 2:
                    dsn.themNS();
                    break;
                case 3:
                    try {
                        dsn.xuat();
                    } catch (NullPointerException e) {
                        System.out.println("Danh sach rong. Chon 1 de nhap hoac 2 de them nhan su.");
                    }
                    break;
                case 4:
                    try {
                        dsn.xoaNS();
                    } catch (NullPointerException e) {
                        System.out.println("Danh sach rong. Khong co gi de xoa.");
                    }
                    break;
                case 5:
                    try {
                        Nhansu found = dsn.timtheomaNS();
                        if (found != null) found.xuat();
                    } catch (NullPointerException e) {
                        System.out.println("Danh sach rong. Khong tim duoc.");
                    }
                    break;
                case 6:
                    try {
                        dc.chinhSua(dsn);
                    } catch (NullPointerException e) {
                        System.out.println("Danh sach rong. Khong co nhan su de chinh sua.");
                    }
                    break;
                case 0:
                    System.out.println("Ket thuc chuong trinh.");
                    sc.close();
                    return;
                default:
                    System.out.println("Chuc nang khong hop le.");
            }
        }
    }
}
