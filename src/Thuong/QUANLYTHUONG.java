package Thuong;
import java.util.Date;

public class QUANLYTHUONG {
    private int indexCT = 0;
    private int indexDM = 0;
    private CTTHUONG CTT[] = new CTTHUONG[50];
    private DMTHUONG DMT[] = new DMTHUONG[50];
    private static final java.util.Scanner sc = new java.util.Scanner(System.in);

    // Getter cho số lượng
    public int getSoLuongCT() { return indexCT; }
    public int getSoLuongDM() { return indexDM; }

    // Kiểm tra danh mục thưởng có tồn tại không
    public boolean tonTaiDanhMucThuong(String maThuong) {
        for (int i = 0; i < indexDM; i++) {
            if (DMT[i] != null && DMT[i].getMaThuong().equalsIgnoreCase(maThuong)) {
                return true;
            }
        }
        return false;
    }

    // Tìm danh mục thưởng theo mã
    public DMTHUONG timDanhMucThuong(String maThuong) {
        for (int i = 0; i < indexDM; i++) {
            if (DMT[i] != null && DMT[i].getMaThuong().equalsIgnoreCase(maThuong)) {
                return DMT[i];
            }
        }
        return null;
    }

    //nhập danh mục thưởng
    public void nhapDMThuong() {
        System.out.print("Nhap so luong danh muc thuong: ");
        int n;
        try {
            n = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println("Gia tri nhap khong hop le.");
            return;
        }
        if (n <= 0) {
            System.out.println("So luong phai la so duong.");
            return;
        }
        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Danh muc thuong thu " + (i + 1) + " ---");
            DMT[indexDM] = new DMTHUONG();
            DMT[indexDM].nhap();
            indexDM++;
        }
    }

    //Nhập chi tiết thưởng (đã có nhiều danh mục thưởng trong 1 chi tiết)
    public void nhapCTThuong() {
        System.out.print("Nhap so luong chi tiet thuong: ");
        int m;
        try {
            m = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println("Gia tri nhap khong hop le.");
            return;
        }
        for (int i = 0; i < m; i++) {
            System.out.println("\n--- Chi tiet thuong thu " + (i + 1) + " ---");
            CTTHUONG ct = new CTTHUONG();
            ct.nhap(); // Đã nhập nhiều danh mục thưởng bên trong
            CTT[indexCT++] = ct;
        }
    }

    //Xuất danh mục thưởng
    public void xuatDMThuong() {
        System.out.println("\n=== DANH SACH DANH MUC THUONG ===");
        if (indexDM == 0) {
            System.out.println("Danh muc rong.");
            return;
        }
        for (int i = 0; i < indexDM; i++) {
            if (DMT[i] != null) {
                System.out.print("[" + (i+1) + "] ");
                DMT[i].xuat();
            }
        }
    }

    //Xuất chi tiết thưởng
    public void xuatCTThuong() {
        System.out.println("\n=== DANH SACH CHI TIET THUONG ===");
        if (indexCT == 0) {
            System.out.println("Danh sach chi tiet thuong rong.");
            return;
        }
        for (int i = 0; i < indexCT; i++) {
            if (CTT[i] != null) {
                System.out.println("\n[" + (i+1) + "]");
                CTT[i].xuat();
            }
        }
    }

    //Thêm danh mục thưởng
    public void themDMThuong() {
        System.out.println("\n--- THEM DANH MUC THUONG ---");
        DMTHUONG dm = new DMTHUONG();
        dm.nhap();

        for (int i = 0; i < indexDM; i++) {
            if (dm.getMaThuong().equals(DMT[i].getMaThuong())) {
                System.out.println("Ma thuong da ton tai!");
                return;
            }
        }
        if (indexDM >= DMT.length) {
            System.out.println("Khong the them: danh muc da day.");
            return;
        }
        DMT[indexDM++] = dm;
        System.out.println("Them danh muc thuong thanh cong!");
    }

    //thêm chi tiết thưởng
    public void themCTThuong() {
        System.out.println("\n--- THEM CHI TIET THUONG ---");
        CTTHUONG ct = new CTTHUONG();
        ct.nhap(); // Đã validate maNS và nhập nhiều danh mục thưởng

        for (int i = 0; i < indexCT; i++) {
            if (ct.getMaCTThuong().equals(CTT[i].getMaCTThuong())) {
                System.out.println("Ma chi tiet thuong da ton tai!");
                return;
            }
        }
        if (indexCT >= CTT.length) {
            System.out.println("Khong the them: danh sach chi tiet thuong da day.");
            return;
        }
        CTT[indexCT++] = ct;
        System.out.println("Them chi tiet thuong thanh cong!");
    }

    //xóa danh mục thưởng
    public void xoaDMThuong() {
        System.out.print("Nhap ma thuong can xoa: ");
        String ma = sc.nextLine();

        for (int i = 0; i < indexDM; i++) {
            if (DMT[i].getMaThuong().equals(ma)) {
                for (int j = i; j < indexDM - 1; j++) DMT[j] = DMT[j + 1];
                indexDM--;
                // clear last slot to avoid stale reference
                DMT[indexDM] = null;
                System.out.println("Xoa danh muc thuong thanh cong!");
                return;
            }
        }
        System.out.println("Khong tim thay ma thuong can xoa!");
    }

    //xóa chi tiết thưởng
    public void xoaCTThuong() {
        System.out.print("Nhap ma chi tiet thuong can xoa: ");
        String ma = sc.nextLine();

        for (int i = 0; i < indexCT; i++) {
            if (CTT[i].getMaCTThuong().equals(ma)) {
                for (int j = i; j < indexCT - 1; j++) CTT[j] = CTT[j + 1];
                indexCT--;
                // clear last slot
                CTT[indexCT] = null;
                System.out.println("Xoa chi tiet thuong thanh cong!");
                return;
            }
        }
        System.out.println("Khong tim thay ma chi tiet thuong can xoa!");
    }

    //sửa danh sach danh mục thưởng
    public void suaDMThuong() {
        System.out.print("Nhap ma thuong can sua: ");
        String ma = sc.nextLine();

        for (int i = 0; i < indexDM; i++) {
            if (DMT[i].getMaThuong().equals(ma)) {
                System.out.println("Nhap thong tin moi:");
                DMT[i].nhap();
                System.out.println("Sua danh muc thuong thanh cong!");
                return;
            }
        }
        System.out.println("Khong tim thay ma thuong de sua!");
    }


    //sửa danh sach chi tiết thưởng
    public void suaCTThuong() {
        System.out.print("Nhap ma chi tiet thuong can sua: ");
        String ma = sc.nextLine();

        for (int i = 0; i < indexCT; i++) {
            if (CTT[i].getMaCTThuong().equals(ma)) {
                System.out.println("Nhap thong tin moi:");
                CTT[i].nhap(); // Đã nhập lại nhiều danh mục thưởng
                System.out.println("Sua chi tiet thuong thanh cong!");
                return;
            }
        }
        System.out.println("Khong tim thay ma chi tiet thuong de sua!");
    }

    //Tính tổng thưởng của 1 nhân sự
    public void tongThuongTheoNhanSu() {
        System.out.print("Nhap ma nhan su: ");
        String ma = sc.nextLine();
        double tong = 0;
        int count = 0;
        for (int i = 0; i < indexCT; i++) {
            if (CTT[i].getMaNS().equals(ma)) {
                tong += CTT[i].getTongSoTien(); // Dùng getTongSoTien() mới
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Nhan su " + ma + " chua co thuong nao!");
        } else {
            System.out.println("Tong tien thuong cua nhan su " + ma + " la: " + tong + " (" + count + " chi tiet)");
        }
    }

    // Trả về tổng thưởng của 1 nhân sự trong một tháng/năm
    public double tinhTongThuongTheoNhanSuTrongThang(String maNS, int month, int year) {
        double tong = 0;
        for (int i = 0; i < indexCT; i++) {
            CTTHUONG ct = CTT[i];
            if (ct == null) continue;
            if (!maNS.equals(ct.getMaNS())) continue;
            Date d = ct.getNgayThuong();
            if (d == null) continue;
            @SuppressWarnings("deprecation")
            int y = d.getYear() + 1900;
            @SuppressWarnings("deprecation")
            int m = d.getMonth() + 1;
            if (y == year && m == month) {
                tong += ct.getTongSoTien(); // Dùng getTongSoTien() mới
            }
        }
        return tong;
    }

    // Ghi file
    public void ghiFile() {
        try {
            // Ghi Danh mục thưởng
            java.io.DataOutputStream outDM = new java.io.DataOutputStream(
                new java.io.FileOutputStream("data/danhMucThuong.txt"));
            
            for (int i = 0; i < indexDM; i++) {
                if (DMT[i] != null) {
                    outDM.writeUTF(DMT[i].getMaThuong());
                    outDM.writeUTF(DMT[i].getTenThuong());
                    outDM.writeDouble(DMT[i].getSoTienMacDinh());
                }
            }
            outDM.close();
            
            // Ghi Chi tiết thưởng
            java.io.DataOutputStream outCT = new java.io.DataOutputStream(
                new java.io.FileOutputStream("data/chiTietThuong.txt"));
            
            for (int i = 0; i < indexCT; i++) {
                if (CTT[i] != null) {
                    outCT.writeUTF(CTT[i].getMaCTThuong());
                    outCT.writeUTF(CTT[i].getMaNS());
                    outCT.writeLong(CTT[i].getNgayThuong().getTime());
                    
                    // Ghi số lượng danh mục thưởng
                    int soLuong = CTT[i].getSoLuongThuong();
                    outCT.writeInt(soLuong);
                    
                    // Ghi từng danh mục thưởng
                    DMTHUONG[] dsThuong = CTT[i].getDsThuong();
                    for (int j = 0; j < soLuong; j++) {
                        if (dsThuong[j] != null) {
                            outCT.writeUTF(dsThuong[j].getMaThuong());
                            outCT.writeUTF(dsThuong[j].getTenThuong());
                            outCT.writeDouble(dsThuong[j].getSoTienMacDinh());
                        }
                    }
                }
            }
            outCT.close();
            
        } catch (java.io.IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    // Đọc file
    public void docFile() {
        // Đọc Danh mục thưởng
        try {
            java.io.DataInputStream inDM = new java.io.DataInputStream(
                new java.io.FileInputStream("data/danhMucThuong.txt"));
            
            indexDM = 0;
            try {
                while (true) {
                    DMT[indexDM] = new DMTHUONG();
                    DMT[indexDM].setMaThuong(inDM.readUTF());
                    DMT[indexDM].setTenThuong(inDM.readUTF());
                    DMT[indexDM].setSoTienMacDinh(inDM.readDouble());
                    indexDM++;
                }
            } catch (java.io.EOFException e) {
                // Đọc hết file
            }
            inDM.close();
        } catch (java.io.IOException e) {
            System.out.println("Khong tim thay file danh muc thuong: " + e.getMessage());
        }
        
        // Đọc Chi tiết thưởng
        try {
            java.io.DataInputStream inCT = new java.io.DataInputStream(
                new java.io.FileInputStream("data/chiTietThuong.txt"));
            
            indexCT = 0;
            try {
                while (true) {
                    CTT[indexCT] = new CTTHUONG();
                    CTT[indexCT].setMaCTThuong(inCT.readUTF());
                    CTT[indexCT].setMaNS(inCT.readUTF());
                    CTT[indexCT].setNgayThuong(new Date(inCT.readLong()));
                    
                    // Đọc số lượng danh mục thưởng
                    int soLuong = inCT.readInt();
                    
                    // Đọc từng danh mục thưởng
                    for (int j = 0; j < soLuong; j++) {
                        DMTHUONG dmt = new DMTHUONG();
                        dmt.setMaThuong(inCT.readUTF());
                        dmt.setTenThuong(inCT.readUTF());
                        dmt.setSoTienMacDinh(inCT.readDouble());
                        CTT[indexCT].themDanhMucThuong(dmt);
                    }
                    
                    indexCT++;
                }
            } catch (java.io.EOFException e) {
                // Đọc hết file
            }
            inCT.close();
        } catch (java.io.IOException e) {
            System.out.println("Khong tim thay file chi tiet thuong: " + e.getMessage());
        }
    }
}

