package Thuong;
import java.util.Scanner;
import java.util.Date;
import Center.DataCenter;

public class CTTHUONG {
    private String maCTThuong;
    private String maNS;
    private Date ngayThuong;
    private String[] maDanhMucThuong;  // Mảng mã danh mục thưởng
    private int soLuongThuong;         // Số lượng loại thưởng

    public CTTHUONG(){
        maCTThuong = "";
        maNS = "";
        ngayThuong = null;
        maDanhMucThuong = new String[10];  // Tối đa 10 loại thưởng
        soLuongThuong = 0;
    }

    public CTTHUONG(String maCTThuong, String maNS, Date ngayThuong){
        this.maCTThuong = maCTThuong;
        this.maNS = maNS;
        this.ngayThuong = ngayThuong;
        this.maDanhMucThuong = new String[10];
        this.soLuongThuong = 0;
    }

    public CTTHUONG(CTTHUONG CTT){
        maCTThuong = CTT.maCTThuong;
        maNS = CTT.maNS;
        ngayThuong = CTT.ngayThuong;
        maDanhMucThuong = new String[10];
        soLuongThuong = CTT.soLuongThuong;
        // Copy danh sách mã thưởng
        for (int i = 0; i < CTT.soLuongThuong; i++) {
            this.maDanhMucThuong[i] = CTT.maDanhMucThuong[i];
        }
    }

    public String getMaCTThuong() { return maCTThuong; }
    public String getMaNS() { return maNS; }
    public Date getNgayThuong() { return ngayThuong; }
    public String[] getMaDanhMucThuong() { return maDanhMucThuong; }
    public int getSoLuongThuong() { return soLuongThuong; }
    
    // Tính tổng số tiền thưởng
    public double getTongSoTien() {
        double tong = 0;
        for (int i = 0; i < soLuongThuong; i++) {
            if (maDanhMucThuong[i] != null) {
                DMTHUONG dmt = DataCenter.dsdmt.timDanhMucThuong(maDanhMucThuong[i]);
                if (dmt != null) {
                    tong += dmt.getSoTienMacDinh();
                }
            }
        }
        return tong;
    }

    public void setMaCTThuong(String maCTThuong) { this.maCTThuong = maCTThuong; }
    public void setMaNS(String maNS) { this.maNS = maNS; }
    public void setNgayThuong(Date ngayThuong) { this.ngayThuong = ngayThuong; }
    public void setMaDanhMucThuong(String[] maDanhMucThuong) { this.maDanhMucThuong = maDanhMucThuong; }
    public void setSoLuongThuong(int soLuongThuong) { this.soLuongThuong = soLuongThuong; }

    // Thêm 1 mã danh mục thưởng vào chi tiết
    public void themMaDanhMucThuong(String maThuong) {
        if (soLuongThuong < maDanhMucThuong.length) {
            maDanhMucThuong[soLuongThuong] = maThuong;
            soLuongThuong++;
        } else {
            System.out.println("Đã đủ số lượng thưởng tối đa!");
        }
    }

    Scanner sc = new Scanner(System.in);
    public void nhap(){
        System.out.print("Nhap ma chi tiet thuong: ");
        maCTThuong = sc.nextLine();
        
        System.out.print("Nhap ma NS (hoặc '0' để hủy): ");
        maNS=sc.nextLine();
        if (maNS.equals("0")) {
            System.out.println("Hủy nhập chi tiết nhân sự.");
            return;
        }
        while(!DataCenter.dsNhanSu.tonTaiNhanSu(maNS)){
            System.out.print("Nhan su ko ton tai! Vui long nhap lai MaNS (hoặc '0' để hủy): ");
            maNS=sc.nextLine();
            if (maNS.equals("0")) {
                System.out.println("Hủy nhập chi tiết nhân sự.");
                return;
            }
        }
        
        // Nhập nhiều danh mục thưởng
        System.out.print("Nhap so luong danh muc thuong: ");
        int n = Integer.parseInt(sc.nextLine());
        soLuongThuong = 0;
        
        for (int i = 0; i < n && i < maDanhMucThuong.length; i++) {
            System.out.println("\n--- Danh muc thuong thu " + (i+1) + " ---");
            System.out.print("Nhap ma danh muc thuong (hoặc '0' để hủy): ");
            String maThuong = sc.nextLine();

            if (maThuong.equals("0")) {
                System.out.println("Hủy nhập danh mục thưởng.");
                return;
            }

            // Kiểm tra danh mục thưởng có tồn tại không
            while(!DataCenter.dsdmt.tonTaiDanhMucThuong(maThuong)){
                System.out.print("Danh muc thuong ko ton tai! Vui long nhap lai Ma thuong (hoặc '0' để hủy): ");
                maThuong = sc.nextLine();
                if (maThuong.equals("0")) {
                    System.out.println("Hủy nhập danh mục thưởng.");
                    return;
                }
            }
            System.out.println("Ma danh muc thuong hop le!");
            
            // Lưu mã danh mục thưởng
            themMaDanhMucThuong(maThuong);
            
            // Hiển thị thông tin
            DMTHUONG dmt = DataCenter.dsdmt.timDanhMucThuong(maThuong);
            if (dmt != null) {
                System.out.println("→ Ten thuong: " + dmt.getTenThuong() + ", So tien: " + dmt.getSoTienMacDinh());
            }
        }
        
        ngayThuong = new Date(); // Tự động lấy ngày hiện tại
    }

    public void xuat(){
        System.out.println("Ma CT Thuong: " + maCTThuong + " | Ma NS: " + maNS + " | Ngay: " + ngayThuong);
        System.out.println("  Danh sach thuong (" + soLuongThuong + " loai):");
        for (int i = 0; i < soLuongThuong; i++) {
            if (maDanhMucThuong[i] != null) {
                DMTHUONG dmt = DataCenter.dsdmt.timDanhMucThuong(maDanhMucThuong[i]);
                if (dmt != null) {
                    System.out.print("    [" + (i+1) + "] ");
                    dmt.xuat();
                }
            }
        }
        System.out.println("  → Tong tien thuong: " + getTongSoTien());
    }
}
