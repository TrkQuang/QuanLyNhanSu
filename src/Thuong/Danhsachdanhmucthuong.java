package Thuong;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Danhsachdanhmucthuong {
    private DMTHUONG[] dsDanhMucThuong = new DMTHUONG[100];
    private int soLuongDM = 0;
    private Scanner sc = new Scanner(System.in);

    // Constructor - tự động đọc file khi khởi tạo
    public Danhsachdanhmucthuong() {
        docFile();
    }

    // Kiểm tra danh mục thưởng có tồn tại không
    public boolean tonTaiDanhMucThuong(String maThuong) {
        for (int i = 0; i < soLuongDM; i++) {
            if (dsDanhMucThuong[i].getMaThuong().equalsIgnoreCase(maThuong)) {
                return true;
            }
        }
        return false;
    }

    // Tìm danh mục thưởng theo mã
    public DMTHUONG timDanhMucThuong(String maThuong) {
        for (int i = 0; i < soLuongDM; i++) {
            if (dsDanhMucThuong[i].getMaThuong().equalsIgnoreCase(maThuong)) {
                return dsDanhMucThuong[i];
            }
        }
        return null;
    }

    // Thêm danh mục thưởng
    public void themDM() {
        System.out.println("\n--- THEM DANH MUC THUONG ---");
        DMTHUONG dm = new DMTHUONG();
        dm.nhap();
        sc.nextLine(); // Đọc bỏ dòng thừa sau nextDouble()

        // Kiểm tra trùng mã
        for (int i = 0; i < soLuongDM; i++) {
            if (dsDanhMucThuong[i].getMaThuong().equalsIgnoreCase(dm.getMaThuong())) {
                System.out.println("Ma thuong da ton tai!");
                return;
            }
        }

        dsDanhMucThuong[soLuongDM] = dm;
        soLuongDM++;
        System.out.println("Da them danh muc thuong thanh cong!");
    }

    // Xuất danh sách danh mục thưởng
    public void xuatDM() {
        System.out.println("\n=== DANH SACH DANH MUC THUONG ===");
        if (soLuongDM == 0) {
            System.out.println("Chua co danh muc thuong nao!");
            return;
        }

        System.out.println("STT | Ma Thuong | Ten Thuong                    | So tien mac dinh");
        System.out.println("-----------------------------------------------------------------------------");
        for (int i = 0; i < soLuongDM; i++) {
            System.out.printf("%-4d| %-10s| %-30s| %.2f\n", 
                (i + 1),
                dsDanhMucThuong[i].getMaThuong(),
                dsDanhMucThuong[i].getTenThuong(),
                dsDanhMucThuong[i].getSoTienMacDinh());
        }
    }

    // Xóa danh mục thưởng
    public void xoaDM() {
        System.out.print("Nhap ma thuong can xoa: ");
        String ma = sc.nextLine();

        boolean found = false;
        for (int i = 0; i < soLuongDM; i++) {
            if (dsDanhMucThuong[i].getMaThuong().equalsIgnoreCase(ma)) {
                // Dịch các phần tử phía sau lên
                for (int j = i; j < soLuongDM - 1; j++) {
                    dsDanhMucThuong[j] = dsDanhMucThuong[j + 1];
                }
                dsDanhMucThuong[soLuongDM - 1] = null;
                soLuongDM--;
                found = true;
                System.out.println("Da xoa danh muc thuong thanh cong!");
                break;
            }
        }

        if (!found) {
            System.out.println("Khong tim thay ma thuong can xoa!");
        }
    }

    // Sửa danh mục thưởng
    public void suaDM() {
        System.out.print("Nhap ma thuong can sua: ");
        String ma = sc.nextLine();

        boolean found = false;
        for (int i = 0; i < soLuongDM; i++) {
            if (dsDanhMucThuong[i].getMaThuong().equalsIgnoreCase(ma)) {
                System.out.println("Thong tin cu:");
                dsDanhMucThuong[i].xuat();
                System.out.println("\nNhap thong tin moi:");
                dsDanhMucThuong[i].nhap();
                sc.nextLine(); // Đọc bỏ dòng thừa
                found = true;
                System.out.println("Da cap nhat thanh cong!");
                break;
            }
        }

        if (!found) {
            System.out.println("Khong tim thay ma thuong can sua!");
        }
    }

    // Tìm kiếm danh mục thưởng theo tên
    public void timKiemTheoTen() {
        System.out.print("Nhap ten thuong can tim: ");
        String ten = sc.nextLine();

        System.out.println("\n=== KET QUA TIM KIEM ===");
        boolean found = false;
        for (int i = 0; i < soLuongDM; i++) {
            if (dsDanhMucThuong[i].getTenThuong().toLowerCase().contains(ten.toLowerCase())) {
                dsDanhMucThuong[i].xuat();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Khong tim thay danh muc thuong co ten: " + ten);
        }
    }

    // Ghi file
    public void ghiFile() throws IOException {
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("data/danhmucthuong.txt"));

        for (int i = 0; i < soLuongDM; i++) {
            outputStream.writeUTF(dsDanhMucThuong[i].getMaThuong());
            outputStream.writeUTF(dsDanhMucThuong[i].getTenThuong());
            outputStream.writeDouble(dsDanhMucThuong[i].getSoTienMacDinh());
        }

        outputStream.close();
    }

    // Đọc file
    public void docFile() {
        soLuongDM = 0;
        try {
            DataInputStream inputStream = new DataInputStream(new FileInputStream("data/danhmucthuong.txt"));
            try {
                while (true) {
                    DMTHUONG dm = new DMTHUONG();
                    dm.setMaThuong(inputStream.readUTF());
                    dm.setTenThuong(inputStream.readUTF());
                    dm.setSoTienMacDinh(inputStream.readDouble());

                    dsDanhMucThuong[soLuongDM] = dm;
                    soLuongDM++;
                }
            } catch (EOFException e) {
                // Đọc hết file
            } finally {
                inputStream.close();
            }
        } catch (IOException e) {
            System.out.println("Loi doc file danh muc thuong: " + e.getMessage());
        }
    }

    // Getter
    public int getSoLuongDM() {
        return soLuongDM;
    }

    public DMTHUONG[] getDsDanhMucThuong() {
        return dsDanhMucThuong;
    }
}
