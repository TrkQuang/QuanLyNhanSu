package Thuong;
import java.util.Scanner;
import java.util.Date;
import Center.DataCenter;

public class CTTHUONG {
    private String maCTThuong;
    private String maNS;
    private Date ngayThuong;
    private DMTHUONG[] dsThuong;      // Mảng danh mục thưởng
    private int soLuongThuong;        // Số lượng loại thưởng

    public CTTHUONG(){
        maCTThuong = "";
        maNS = "";
        ngayThuong = null;
        dsThuong = new DMTHUONG[10];  // Tối đa 10 loại thưởng
        soLuongThuong = 0;
    }

    public CTTHUONG(String maCTThuong, String maNS, Date ngayThuong){
        this.maCTThuong = maCTThuong;
        this.maNS = maNS;
        this.ngayThuong = ngayThuong;
        this.dsThuong = new DMTHUONG[10];
        this.soLuongThuong = 0;
    }

    public CTTHUONG(CTTHUONG CTT){
        maCTThuong = CTT.maCTThuong;
        maNS = CTT.maNS;
        ngayThuong = CTT.ngayThuong;
        dsThuong = new DMTHUONG[10];
        soLuongThuong = CTT.soLuongThuong;
        // Copy danh sách thưởng
        for (int i = 0; i < CTT.soLuongThuong; i++) {
            this.dsThuong[i] = CTT.dsThuong[i];
        }
    }

    public String getMaCTThuong() { return maCTThuong; }
    public String getMaNS() { return maNS; }
    public Date getNgayThuong() { return ngayThuong; }
    public DMTHUONG[] getDsThuong() { return dsThuong; }
    public int getSoLuongThuong() { return soLuongThuong; }
    
    // Tính tổng số tiền thưởng
    public double getTongSoTien() {
        double tong = 0;
        for (int i = 0; i < soLuongThuong; i++) {
            if (dsThuong[i] != null) {
                tong += dsThuong[i].getSoTienMacDinh();
            }
        }
        return tong;
    }

    public void setMaCTThuong(String maCTThuong) { this.maCTThuong = maCTThuong; }
    public void setMaNS(String maNS) { this.maNS = maNS; }
    public void setNgayThuong(Date ngayThuong) { this.ngayThuong = ngayThuong; }
    public void setDsThuong(DMTHUONG[] dsThuong) { this.dsThuong = dsThuong; }
    public void setSoLuongThuong(int soLuongThuong) { this.soLuongThuong = soLuongThuong; }

    // Thêm 1 danh mục thưởng vào chi tiết
    public void themDanhMucThuong(DMTHUONG dmt) {
        if (soLuongThuong < dsThuong.length) {
            dsThuong[soLuongThuong] = dmt;
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
        
        for (int i = 0; i < n && i < dsThuong.length; i++) {
            System.out.println("\n--- Danh muc thuong thu " + (i+1) + " ---");
            System.out.print("Nhap ma danh muc thuong (hoặc '0' để hủy): ");
            String maThuong = sc.nextLine();

            if (maThuong.equals("0")) {
                System.out.println("Hủy nhập danh mục thưởng.");
                return;
            }

            // Kiểm tra danh mục thưởng có tồn tại không
            while(!DataCenter.qlThuong.tonTaiDanhMucThuong(maThuong)){
                System.out.print("Danh muc thuong ko ton tai! Vui long nhap lai Ma thuong (hoặc '0' để hủy): ");
                maThuong = sc.nextLine();
                if (maThuong.equals("0")) {
                    System.out.println("Hủy nhập danh mục thưởng.");
                    return;
                }
            }
            System.out.println("Ma danh muc thuong hop le!");
            
            // Lấy danh mục thưởng từ DataCenter
            DMTHUONG dmt = DataCenter.qlThuong.timDanhMucThuong(maThuong);
            if (dmt != null) {
                themDanhMucThuong(dmt);
                System.out.println("→ Ten thuong: " + dmt.getTenThuong() + ", So tien: " + dmt.getSoTienMacDinh());
            }
        }
        
        ngayThuong = new Date(); // Tự động lấy ngày hiện tại
    }

    public void xuat(){
        System.out.println("Ma CT Thuong: " + maCTThuong + " | Ma NS: " + maNS + " | Ngay: " + ngayThuong);
        System.out.println("  Danh sach thuong (" + soLuongThuong + " loai):");
        for (int i = 0; i < soLuongThuong; i++) {
            if (dsThuong[i] != null) {
                System.out.print("    [" + (i+1) + "] ");
                dsThuong[i].xuat();
            }
        }
        System.out.println("Tong tien thuong: " + getTongSoTien());
    }
}
