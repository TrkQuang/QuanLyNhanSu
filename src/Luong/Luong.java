package Luong;

import java.util.Scanner;
import Center.DataCenter;
import HSNS.CHUCVU;
import NhanSu.Nhansu;

/**
 * Class Luong: Quản lý lương tháng của nhân sự
 * Tính tổng hợp từ 3 nguồn:
 * 1. Lương cơ bản (từ công việc - chấm công) × Hệ số lương chức vụ
 * 2. Thưởng (từ module Thưởng)
 * 3. Thưởng dự án (từ module Phân công)
 */
public class Luong {
    private String maNS;
    private int thang;
    private int nam;
    
    // Lương cơ bản từ công việc
    private double tongNgayLam;     // Tổng ngày làm trong tháng
    private double tongGioLam;      // Tổng giờ làm trong tháng
    private double luongCoBan;      // Lương cơ bản tính từ chấm công (chưa nhân hệ số)
    private double heSoLuong;       // Hệ số lương từ chức vụ
    private double luongSauHeSo;    // Lương cơ bản × hệ số lương
    
    // Phụ cấp và các khoản thưởng
    private double phuCap;          // Phụ cấp từ nhân sự (lương cứng hàng tháng)
    private double thuongModule;    // Thưởng từ module Thưởng
    private double thuongDuAn;      // Thưởng từ phân công dự án
    
    // Tổng lương
    private double tongLuong;       // = luongSauHeSo + phuCap + thuongModule + thuongDuAn
    
    Scanner sc = new Scanner(System.in);
    
    // Constructor
    public Luong() {
        this.maNS = "";
        this.thang = 0;
        this.nam = 0;
        this.tongNgayLam = 0;
        this.tongGioLam = 0;
        this.luongCoBan = 0;
        this.heSoLuong = 1.0;  // Mặc định = 1.0
        this.luongSauHeSo = 0;
        this.phuCap = 0;
        this.thuongModule = 0;
        this.thuongDuAn = 0;
        this.tongLuong = 0;
    }
    
    public Luong(String maNS, int thang, int nam, double tongNgayLam, double tongGioLam,
                 double luongCoBan, double heSoLuong, double phuCap, double thuongModule, double thuongDuAn) {
        this.maNS = maNS;
        this.thang = thang;
        this.nam = nam;
        this.tongNgayLam = tongNgayLam;
        this.tongGioLam = tongGioLam;
        this.luongCoBan = luongCoBan;
        this.heSoLuong = heSoLuong;
        this.luongSauHeSo = luongCoBan * heSoLuong;
        this.phuCap = phuCap;
        this.thuongModule = thuongModule;
        this.thuongDuAn = thuongDuAn;
        this.tongLuong = luongSauHeSo + phuCap + thuongModule + thuongDuAn;
    }
    
    // Getters
    public String getMaNS() { return maNS; }
    public int getThang() { return thang; }
    public int getNam() { return nam; }
    public double getTongNgayLam() { return tongNgayLam; }
    public double getTongGioLam() { return tongGioLam; }
    public double getLuongCoBan() { return luongCoBan; }
    public double getHeSoLuong() { return heSoLuong; }
    public double getLuongSauHeSo() { return luongSauHeSo; }
    public double getPhuCap() { return phuCap; }
    public double getThuongModule() { return thuongModule; }
    public double getThuongDuAn() { return thuongDuAn; }
    public double getTongLuong() { return tongLuong; }
    
    // Setters
    public void setMaNS(String maNS) { this.maNS = maNS; }
    public void setThang(int thang) { this.thang = thang; }
    public void setNam(int nam) { this.nam = nam; }
    public void setTongNgayLam(double tongNgayLam) { this.tongNgayLam = tongNgayLam; }
    public void setTongGioLam(double tongGioLam) { this.tongGioLam = tongGioLam; }
    public void setLuongCoBan(double luongCoBan) { this.luongCoBan = luongCoBan; }
    public void setHeSoLuong(double heSoLuong) { this.heSoLuong = heSoLuong; }
    public void setPhuCap(double phuCap) { this.phuCap = phuCap; }
    public void setThuongModule(double thuongModule) { this.thuongModule = thuongModule; }
    public void setThuongDuAn(double thuongDuAn) { this.thuongDuAn = thuongDuAn; }
    
    /**
     * Tính lương cơ bản dựa trên loại nhân sự và công
     * - Nhansufull: tính theo ngày (tongNgayLam * luongcb từ Nhansufull)
     * - Nhansupart: tính theo giờ (tongGioLam * tienconggio từ Nhansupart)
     */
    public void tinhLuongCoBan() {
        // Tìm nhân sự để kiểm tra loại
        Nhansu ns = DataCenter.dsNhanSu.timtheomaNS(this.maNS);

        if (ns == null) {
            System.out.println("Không tìm thấy nhân sự " + this.maNS);
            this.luongCoBan = 0;
            return;
        }
        
        // Kiểm tra loại nhân sự bằng instanceof
        if (ns instanceof NhanSu.Nhansufull) {
            // Full-time: tính theo ngày với lương cơ bản từ Nhansufull
            NhanSu.Nhansufull nsFull = (NhanSu.Nhansufull) ns;
            double luongCBNgay = nsFull.getluongcb();  // Lương cơ bản/ngày
            this.luongCoBan = tongNgayLam * luongCBNgay;
        } 
        else if (ns instanceof NhanSu.Nhansupart) {
            // Part-time: tính theo giờ với tiền công giờ từ Nhansupart
            NhanSu.Nhansupart nsPart = (NhanSu.Nhansupart) ns;
            double tienGio = nsPart.gettienconggio();  // Tiền công/giờ
            this.luongCoBan = tongGioLam * tienGio;
        }
    }
    
    /**
     * Lấy hệ số lương từ chức vụ của nhân sự
     */
    public void layHeSoLuongTuChucVu() {
        // Tìm nhân sự
        Nhansu ns = DataCenter.dsNhanSu.timtheomaNS(this.maNS);
        if (ns == null) {
            System.out.println("Không tìm thấy nhân sự " + this.maNS);
            this.heSoLuong = 1.0; // Mặc định
            return;
        }
        
        // Lấy mã chức vụ
        String maCV = ns.getMachucvu();
        if (maCV == null || maCV.trim().isEmpty()) {
            System.out.println("Nhân sự " + this.maNS + " chưa có chức vụ");
            this.heSoLuong = 1.0; // Mặc định
            return;
        }
        
        // Tìm chức vụ
        CHUCVU cv = DataCenter.dscv.timChucVu(maCV);
        if (cv == null) {
            System.out.println("Không tìm thấy chức vụ " + maCV);
            this.heSoLuong = 1.0; // Mặc định
            return;
        }
        
        // Lấy hệ số lương
        this.heSoLuong = cv.getHeSoLuong();
    }
    
    /**
     * Lấy phụ cấp từ nhân sự
     */
    public void layPhuCapTuNhanSu() {
        // Tìm nhân sự
        Nhansu ns = DataCenter.dsNhanSu.timtheomaNS(this.maNS);
        if (ns == null) {
            System.out.println("Không tìm thấy nhân sự " + this.maNS);
            this.phuCap = 0;
            return;
        }
        
        // Lấy phụ cấp (float -> double)
        this.phuCap = ns.getPhucap();
    }
    
    /**
     * Tính lương sau khi nhân hệ số chức vụ
     */
    public void tinhLuongSauHeSo() {
        this.luongSauHeSo = this.luongCoBan * this.heSoLuong;
    }
    
    /**
     * Tính tổng lương = (lương cơ bản × hệ số) + phụ cấp + thưởng module + thưởng dự án
     */
    public void tinhTongLuong() {
        this.tongLuong = this.luongSauHeSo + this.phuCap + this.thuongModule + this.thuongDuAn;
    }
    
    /**
     * Nhập thông tin lương (chủ yếu để test thủ công)
     */
    public void nhap() {
        System.out.print("Mã nhân sự: ");
        this.maNS = sc.nextLine().trim();
        System.out.print("Tháng: ");
        this.thang = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Năm: ");
        this.nam = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Tổng ngày làm: ");
        this.tongNgayLam = Double.parseDouble(sc.nextLine().trim());
        System.out.print("Tổng giờ làm: ");
        this.tongGioLam = Double.parseDouble(sc.nextLine().trim());
        
        tinhLuongCoBan();
        
        System.out.print("Thưởng từ module Thưởng: ");
        this.thuongModule = Double.parseDouble(sc.nextLine().trim());
        System.out.print("Thưởng từ dự án: ");
        this.thuongDuAn = Double.parseDouble(sc.nextLine().trim());
        
        tinhTongLuong();
    }
    
    /**
     * Xuất thông tin lương
     */
    public void xuat() {
        System.out.println("\n========== BẢNG LƯƠNG ==========");
        System.out.println("Mã nhân sự: " + maNS);
        System.out.println("Tháng/Năm: " + thang + "/" + nam);
        System.out.println("--------------------------------");
        System.out.println("Công việc:");
        System.out.println("  - Tổng ngày làm: " + tongNgayLam + " ngày");
        System.out.println("  - Tổng giờ làm: " + tongGioLam + " giờ");
        System.out.println("  - Lương cơ bản: " + String.format("%,.0f", luongCoBan) + " VNĐ");
        System.out.println("  - Hệ số lương: x" + String.format("%.2f", heSoLuong));
        System.out.println("  - Lương sau hệ số: " + String.format("%,.0f", luongSauHeSo) + " VNĐ");
        System.out.println("--------------------------------");
        System.out.println("Phụ cấp:");
        System.out.println("  - Phụ cấp: " + String.format("%,.0f", phuCap) + " VNĐ");
        System.out.println("--------------------------------");
        System.out.println("Thưởng:");
        System.out.println("  - Thưởng module: " + String.format("%,.0f", thuongModule) + " VNĐ");
        System.out.println("  - Thưởng dự án: " + String.format("%,.0f", thuongDuAn) + " VNĐ");
        System.out.println("================================");
        System.out.println("TỔNG LƯƠNG: " + String.format("%,.0f", tongLuong) + " VNĐ");
        System.out.println("================================\n");
    }
    
    /**
     * Chuyển thành chuỗi để ghi file
     * Format: maNS,thang,nam,tongNgayLam,tongGioLam,luongCoBan,heSoLuong,phuCap,thuongModule,thuongDuAn,tongLuong
     */
    public String toFileString() {
        return maNS + "," + thang + "," + nam + "," + 
               tongNgayLam + "," + tongGioLam + "," + 
               luongCoBan + "," + heSoLuong + "," + phuCap + "," + 
               thuongModule + "," + thuongDuAn + "," + tongLuong;
    }
    
    /**
     * Đọc từ chuỗi file
     */
    public static Luong fromFileString(String line) {
        String[] arr = line.split(",");
        if (arr.length < 11) return null;
        
        String maNS = arr[0].trim();
        int thang = Integer.parseInt(arr[1].trim());
        int nam = Integer.parseInt(arr[2].trim());
        double tongNgayLam = Double.parseDouble(arr[3].trim());
        double tongGioLam = Double.parseDouble(arr[4].trim());
        double luongCoBan = Double.parseDouble(arr[5].trim());
        double heSoLuong = Double.parseDouble(arr[6].trim());
        double phuCap = Double.parseDouble(arr[7].trim());
        double thuongModule = Double.parseDouble(arr[8].trim());
        double thuongDuAn = Double.parseDouble(arr[9].trim());
        
        return new Luong(maNS, thang, nam, tongNgayLam, tongGioLam, 
                        luongCoBan, heSoLuong, phuCap, thuongModule, thuongDuAn);
    }
    
    @Override
    public String toString() {
        return "Luong{maNS='" + maNS + "', thang=" + thang + ", nam=" + nam + 
               ", heSoLuong=x" + String.format("%.2f", heSoLuong) +
               ", tongLuong=" + String.format("%,.0f", tongLuong) + " VNĐ}";
    }
}
