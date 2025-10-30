package Thuong;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import Center.DataCenter;

public class Danhsachchitietthuong {
    private CTTHUONG[] dsChiTietThuong = new CTTHUONG[100];
    private int soLuongCT = 0;
    private Scanner sc = new Scanner(System.in);

    // Constructor - tự động đọc file khi khởi tạo
    public Danhsachchitietthuong() {
        docFile();
    }

    // Kiểm tra chi tiết thưởng có tồn tại không
    public boolean tonTaiChiTietThuong(String maCTThuong) {
        for (int i = 0; i < soLuongCT; i++) {
            if (dsChiTietThuong[i].getMaCTThuong().equalsIgnoreCase(maCTThuong)) {
                return true;
            }
        }
        return false;
    }

    // Tìm chi tiết thưởng theo mã
    public CTTHUONG timChiTietThuong(String maCTThuong) {
        for (int i = 0; i < soLuongCT; i++) {
            if (dsChiTietThuong[i].getMaCTThuong().equalsIgnoreCase(maCTThuong)) {
                return dsChiTietThuong[i];
            }
        }
        return null;
    }

    // Thêm chi tiết thưởng
    public void themCT() {
        System.out.println("\n--- THEM CHI TIET THUONG ---");
        CTTHUONG ct = new CTTHUONG();
        ct.nhap();

        // Kiểm tra trùng mã
        for (int i = 0; i < soLuongCT; i++) {
            if (dsChiTietThuong[i].getMaCTThuong().equalsIgnoreCase(ct.getMaCTThuong())) {
                System.out.println("Ma chi tiet thuong da ton tai!");
                return;
            }
        }

        dsChiTietThuong[soLuongCT] = ct;
        soLuongCT++;
        System.out.println("Da them chi tiet thuong thanh cong!");
    }

    // Xuất danh sách chi tiết thưởng
    public void xuatCT() {
        System.out.println("\n=== DANH SACH CHI TIET THUONG ===");
        if (soLuongCT == 0) {
            System.out.println("Chua co chi tiet thuong nao!");
            return;
        }

        for (int i = 0; i < soLuongCT; i++) {
            System.out.println("\n[" + (i + 1) + "]");
            dsChiTietThuong[i].xuat();
        }
    }

    // Xuất chi tiết thưởng theo mã nhân sự
    public void xuatTheoMaNS() {
        System.out.print("Nhap ma nhan su: ");
        String maNS = sc.nextLine();

        // Kiểm tra nhân sự có tồn tại không
        if (DataCenter.dsNhanSu.timtheomaNS(maNS) == null) {
            System.out.println("Ma nhan su khong ton tai trong he thong!");
            return;
        }

        System.out.println("\n=== CHI TIET THUONG CUA NHAN SU " + maNS + " ===");
        boolean found = false;
        for (int i = 0; i < soLuongCT; i++) {
            if (dsChiTietThuong[i].getMaNS().equalsIgnoreCase(maNS)) {
                dsChiTietThuong[i].xuat();
                System.out.println();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Nhan su nay chua co chi tiet thuong!");
        }
    }

    // Xóa chi tiết thưởng
    public void xoaCT() {
        System.out.print("Nhap ma chi tiet thuong can xoa: ");
        String ma = sc.nextLine();

        boolean found = false;
        for (int i = 0; i < soLuongCT; i++) {
            if (dsChiTietThuong[i].getMaCTThuong().equalsIgnoreCase(ma)) {
                // Dịch các phần tử phía sau lên
                for (int j = i; j < soLuongCT - 1; j++) {
                    dsChiTietThuong[j] = dsChiTietThuong[j + 1];
                }
                dsChiTietThuong[soLuongCT - 1] = null;
                soLuongCT--;
                found = true;
                System.out.println("Da xoa chi tiet thuong thanh cong!");
                break;
            }
        }

        if (!found) {
            System.out.println("Khong tim thay ma chi tiet thuong can xoa!");
        }
    }

    // Sửa chi tiết thưởng
    public void suaCT() {
        System.out.print("Nhap ma chi tiet thuong can sua: ");
        String ma = sc.nextLine();

        boolean found = false;
        for (int i = 0; i < soLuongCT; i++) {
            if (dsChiTietThuong[i].getMaCTThuong().equalsIgnoreCase(ma)) {
                System.out.println("Thong tin cu:");
                dsChiTietThuong[i].xuat();
                System.out.println("\nNhap thong tin moi:");
                dsChiTietThuong[i].nhap();
                found = true;
                System.out.println("Da cap nhat thanh cong!");
                break;
            }
        }

        if (!found) {
            System.out.println("Khong tim thay ma chi tiet thuong can sua!");
        }
    }

    // Tính tổng thưởng theo nhân sú trong tháng/năm
    public double tinhTongThuongTheoNhanSuTrongThang(String maNS, int thang, int nam) {
        double tongThuong = 0;
        
        for (int i = 0; i < soLuongCT; i++) {
            if (dsChiTietThuong[i].getMaNS().equalsIgnoreCase(maNS)) {
                tongThuong += dsChiTietThuong[i].getTongSoTien();
            }
        }
        
        return tongThuong;
    }

    // Thống kê thưởng theo nhân sự
    public void thongKeThuongTheoNhanSu() {
        System.out.print("Nhap ma nhan su: ");
        String maNS = sc.nextLine();

        // Kiểm tra nhân sự có tồn tại không
        if (DataCenter.dsNhanSu.timtheomaNS(maNS) == null) {
            System.out.println("Ma nhan su khong ton tai trong he thong!");
            return;
        }

        double tongThuong = tinhTongThuongTheoNhanSuTrongThang(maNS, 0, 0);

        System.out.println("\n=== THONG KE THUONG ===");
        System.out.println("Ma nhan su: " + maNS);
        System.out.printf("Tong tien thuong: %.2f VND\n", tongThuong);
    }

    // Thống kê tổng thưởng tất cả nhân sự
    public void thongKeTongThuong() {
        System.out.println("\n=== THONG KE TONG THUONG ===");
        double tongTatCa = 0;
        int soNhanSu = 0;

        // Duyệt qua tất cả chi tiết thưởng
        for (int i = 0; i < soLuongCT; i++) {
            String maNS = dsChiTietThuong[i].getMaNS();
            double tienThuong = dsChiTietThuong[i].getTongSoTien();
            
            System.out.printf("- Ma NS: %s | Tien thuong: %.2f VND\n", maNS, tienThuong);
            tongTatCa += tienThuong;
            soNhanSu++;
        }

        System.out.println("----------------------------------------");
        System.out.printf("Tong so chi tiet thuong: %d\n", soNhanSu);
        System.out.printf("TONG TIEN THUONG: %.2f VND\n", tongTatCa);
    }

    // Ghi file
    public void ghiFile() throws IOException {
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("data/chitietthuong.txt"));

        for (int i = 0; i < soLuongCT; i++) {
            CTTHUONG ct = dsChiTietThuong[i];
            outputStream.writeUTF(ct.getMaCTThuong());
            outputStream.writeUTF(ct.getMaNS());
            outputStream.writeLong(ct.getNgayThuong().getTime()); // Lưu timestamp
            
            // Lưu số lượng và mảng mã thưởng
            outputStream.writeInt(ct.getSoLuongThuong());
            for (int j = 0; j < ct.getSoLuongThuong(); j++) {
                outputStream.writeUTF(ct.getMaDanhMucThuong()[j]);
            }
        }

        outputStream.close();
    }

    // Đọc file
    public void docFile() {
        soLuongCT = 0;
        try {
            DataInputStream inputStream = new DataInputStream(new FileInputStream("data/chitietthuong.txt"));
            try {
                while (true) {
                    CTTHUONG ct = new CTTHUONG();
                    ct.setMaCTThuong(inputStream.readUTF());
                    ct.setMaNS(inputStream.readUTF());
                    ct.setNgayThuong(new Date(inputStream.readLong())); // Đọc timestamp

                    // Đọc số lượng và mảng mã thưởng
                    int soLuong = inputStream.readInt();
                    ct.setSoLuongThuong(soLuong);
                    String[] maDanhMucThuong = new String[10];
                    for (int j = 0; j < soLuong; j++) {
                        maDanhMucThuong[j] = inputStream.readUTF();
                    }
                    ct.setMaDanhMucThuong(maDanhMucThuong);

                    dsChiTietThuong[soLuongCT] = ct;
                    soLuongCT++;
                }
            } catch (EOFException e) {
                // Đọc hết file
            } finally {
                inputStream.close();
            }
        } catch (IOException e) {
            System.out.println("Loi doc file chi tiet thuong: " + e.getMessage());
        }
    }

    // Getter
    public int getSoLuongCT() {
        return soLuongCT;
    }

    public CTTHUONG[] getDsChiTietThuong() {
        return dsChiTietThuong;
    }
}
