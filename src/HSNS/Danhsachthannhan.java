package HSNS;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import Center.DataCenter;

public class Danhsachthannhan {
    private THANNHAN[] dsThanNhan = new THANNHAN[100];
    private int soLuongTN = 0;
    private Scanner sc = new Scanner(System.in);

    // Constructor - tự động đọc file khi khởi tạo
    public Danhsachthannhan() {
        docFile();
    }

    // Thêm thân nhân
    public void themTN() {
        if (soLuongTN >= dsThanNhan.length) {
            System.out.println("Danh sach than nhan da day! Khong the them moi.");
            return;
        }
        System.out.println("\n--- Them than nhan moi ---");
        THANNHAN tn = new THANNHAN();
        while (true) {
            tn.nhap();
            boolean trung = false;
            for (int i = 0; i < soLuongTN; i++) {
                if (dsThanNhan[i].getmaTN().equalsIgnoreCase(tn.getmaTN())) {
                    System.out.println("Ma than nhan da ton tai! Vui long nhap lai.");
                    trung = true;
                    break;
                }
            }
            if (!trung) break;
        }
        dsThanNhan[soLuongTN++] = tn;
        System.out.println("Da them than nhan thanh cong!");
    }

    // Xuất danh sách thân nhân
    public void xuatTN() {
        System.out.println("\n=== DANH SACH THAN NHAN ===");
        if (soLuongTN == 0) {
            System.out.println("Chua co than nhan nao!");
            return;
        }
        
        System.out.println("STT | Ma TN  | Ma NS  | Ho TN        | Ten TN   | Gioi tinh | Ngay sinh  | Quan he");
        System.out.println("-------------------------------------------------------------------------------------------");
        for (int i = 0; i < soLuongTN; i++) {
            System.out.print((i + 1) + "   | ");
            dsThanNhan[i].xuat();
        }
    }

    // Xuất thân nhân theo mã nhân sự
    public void xuatTheoMaNS() {
        System.out.print("Nhap ma nhan su: ");
        String maNS = sc.nextLine();
        
        System.out.println("\n=== THAN NHAN CUA NHAN SU " + maNS + " ===");
        boolean found = false;
        for (int i = 0; i < soLuongTN; i++) {
            if (dsThanNhan[i].getMaNS().equalsIgnoreCase(maNS)) {
                dsThanNhan[i].xuat();
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("Nhan su nay chua co than nhan!");
        }
    }

    // Xóa thân nhân
    public void xoaTN() {
        System.out.print("Nhap ma than nhan can xoa: ");
        String ma = sc.nextLine();
        
        boolean found = false;
        for (int i = 0; i < soLuongTN; i++) {
            if (dsThanNhan[i].getmaTN().equalsIgnoreCase(ma)) {
                // Dịch các phần tử phía sau lên
                for (int j = i; j < soLuongTN - 1; j++) {
                    dsThanNhan[j] = dsThanNhan[j + 1];
                }
                dsThanNhan[soLuongTN - 1] = null;
                soLuongTN--;
                found = true;
                System.out.println("Da xoa than nhan thanh cong!");
                break;
            }
        }
        
        if (!found) {
            System.out.println("Khong tim thay than nhan can xoa!");
        }
    }

    // Sửa thân nhân
    public void suaTN() {
        System.out.print("Nhap ma than nhan can sua: ");
        String ma = sc.nextLine();
        
        boolean found = false;
        for (int i = 0; i < soLuongTN; i++) {
            if (dsThanNhan[i].getmaTN().equalsIgnoreCase(ma)) {
                System.out.println("Thong tin cu:");
                dsThanNhan[i].xuat();
                System.out.println("\nNhap thong tin moi:");
                dsThanNhan[i].nhap();
                found = true;
                System.out.println("Da cap nhat thanh cong!");
                break;
            }
        }
        
        if (!found) {
            System.out.println("Khong tim thay than nhan can sua!");
        }
    }

    // Tìm thân nhân theo mã
    public THANNHAN timTheoMa(String maTN) {
        for (int i = 0; i < soLuongTN; i++) {
            if (dsThanNhan[i].getmaTN().equalsIgnoreCase(maTN)) {
                return dsThanNhan[i];
            }
        }
        return null;
    }

    // Đếm số lượng thân nhân của một nhân sự
    public int demThanNhanTheoMaNS(String maNS) {
        int count = 0;
        for (int i = 0; i < soLuongTN; i++) {
            if (dsThanNhan[i].getMaNS().equalsIgnoreCase(maNS)) {
                count++;
            }
        }
        return count;
    }

    // Thống kê thân nhân theo mã nhân sự
    public void thongKeThanNhan() {
        System.out.print("Nhap ma nhan su can thong ke: ");
        String maNS = sc.nextLine();
        
        // Kiểm tra mã nhân sự có tồn tại không
        if (DataCenter.dsNhanSu.timtheomaNS(maNS) == null) {
            System.out.println("Ma nhan su khong ton tai trong he thong!");
            return;
        }
        
        int soLuong = demThanNhanTheoMaNS(maNS);
        
        System.out.println("\n=== THONG KE THAN NHAN ===");
        System.out.println("Ma nhan su: " + maNS);
        System.out.println("So luong than nhan: " + soLuong);
        
        if (soLuong > 0) {
            System.out.println("\nDanh sach than nhan:");
            for (int i = 0; i < soLuongTN; i++) {
                if (dsThanNhan[i].getMaNS().equalsIgnoreCase(maNS)) {
                    System.out.print("  - ");
                    dsThanNhan[i].xuat();
                }
            }
        } else {
            System.out.println("Nhan su nay chua co than nhan!");
        }
    }

    // Ghi file
    public void ghiFile() throws IOException {
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("data/thannhan.txt"));
        
        for (int i = 0; i < soLuongTN; i++) {
            outputStream.writeUTF(dsThanNhan[i].getmaTN());
            outputStream.writeUTF(dsThanNhan[i].getMaNS());
            outputStream.writeUTF(dsThanNhan[i].getHoTN());
            outputStream.writeUTF(dsThanNhan[i].gettenTN());
            outputStream.writeUTF(dsThanNhan[i].getGioiTinh());
            outputStream.writeUTF(dsThanNhan[i].getNgaySinh());
            outputStream.writeUTF(dsThanNhan[i].getQuanHe());
        }
        
        outputStream.close();
    }

    // Đọc file
    public void docFile() {
        soLuongTN = 0;
        try {
            DataInputStream inputStream = new DataInputStream(new FileInputStream("data/thannhan.txt"));
            try {
                while (true) {
                    THANNHAN tn = new THANNHAN();
                    tn.setmaTN(inputStream.readUTF());
                    tn.setMaNS(inputStream.readUTF());
                    tn.setHoTN(inputStream.readUTF());
                    tn.settenTN(inputStream.readUTF());
                    tn.setGioiTinh(inputStream.readUTF());
                    tn.setNgaySinh(inputStream.readUTF());
                    tn.setQuanHe(inputStream.readUTF());
                    
                    dsThanNhan[soLuongTN] = tn;
                    soLuongTN++;
                }
            } catch (EOFException e) {
                // Đọc hết file
            } finally {
                inputStream.close();
            }
        } catch (IOException e) {
            System.out.println("Loi doc file than nhan: " + e.getMessage());
        }
    }

    // Getter
    public int getSoLuongTN() {
        return soLuongTN;
    }
}