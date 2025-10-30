package Luong;

import Center.DataCenter;
import ChamCong.ChamCongThang;
import java.io.*;
import java.util.Scanner;

/**
 * Danhsachluong: Quản lý bảng lương tháng của nhân sự
 * Tự động tính lương từ 3 nguồn:
 * 1. ChamCong (DanhSachChamCong) - Lương cơ bản từ công
 * 2. Thuong (QUANLYTHUONG) - Thưởng từ module thưởng
 * 3. PhongBan (QuanLyPhongBan/PhanCong) - Thưởng dự án
 */
public class Danhsachluong {
    private Luong[] dsLuong;
    private int soLuong;
    private Scanner sc = new Scanner(System.in);
    
    // Constructor - tự động đọc file khi khởi tạo
    public Danhsachluong() {
        this.dsLuong = new Luong[100];
        this.soLuong = 0;
        docFile(); // Tự động load dữ liệu lương
    }
    
    /**
     * Tính lương tự động cho 1 nhân sự trong tháng/năm
     * @param maNS Mã nhân sự
     * @param thang Tháng (1-12)
     * @param nam Năm
     * @return Luong object đã tính toán, hoặc null nếu không có dữ liệu
     */
    public Luong tinhLuongTuDong(String maNS, int thang, int nam) {
        Luong luong = new Luong();
        luong.setMaNS(maNS);
        luong.setThang(thang);
        luong.setNam(nam);
        
        // 1. Lấy công từ module ChamCong
        ChamCongThang congThang = DataCenter.qlct.timCongThang(maNS, thang, nam);
        if (congThang != null) {
            luong.setTongNgayLam(congThang.getTongNgayLam());
            luong.setTongGioLam(congThang.getTongGioLam());
        } else {
            System.out.println("⚠️ Không tìm thấy công tháng cho NS " + maNS + " (" + thang + "/" + nam + ")");
            luong.setTongNgayLam(0);
            luong.setTongGioLam(0);
        }
        
        // Tính lương cơ bản
        luong.tinhLuongCoBan();
        
        // 1.5. Lấy hệ số lương từ chức vụ
        luong.layHeSoLuongTuChucVu();
        
        // 1.6. Lấy phụ cấp từ nhân sự
        luong.layPhuCapTuNhanSu();
        
        // 1.7. Tính lương sau hệ số
        luong.tinhLuongSauHeSo();
        
        // 2. Lấy thưởng từ module Thuong
        double thuongModule = DataCenter.dsctt.tinhTongThuongTheoNhanSuTrongThang(maNS, thang, nam);
        luong.setThuongModule(thuongModule);
        
        // 3. Lấy thưởng dự án từ module PhongBan
        double thuongDuAn = DataCenter.dspc.tinhTongThuongPhanCongTheoThang(maNS, thang, nam);
        luong.setThuongDuAn(thuongDuAn);
        
        // Tính tổng lương
        luong.tinhTongLuong();
        
        return luong;
    }
    
    /**
     * Tính lương cho tất cả nhân sự có công trong tháng/năm
     */
    public void tinhLuongChoTatCaNhanSu(int thang, int nam) {
        System.out.println("\n🔄 Đang tính lương tháng " + thang + "/" + nam + "...");
        
        // Load dữ liệu công tháng từ file
        DataCenter.qlct.docCongThangTuFile();
        
        // Duyệt qua tất cả nhân sự có trong danh sách nhân sự
        for (int i = 0; i < DataCenter.dsNhanSu.getSoluong(); i++) {
            String maNS = DataCenter.dsNhanSu.getDs()[i].getMaNS();
            
            // Tính lương cho nhân sự này
            Luong luong = tinhLuongTuDong(maNS, thang, nam);
            
            // Chỉ thêm vào danh sách nếu có dữ liệu công hoặc thưởng
            if (luong.getTongNgayLam() > 0 || luong.getTongGioLam() > 0 || 
                luong.getThuongModule() > 0 || luong.getThuongDuAn() > 0) {
                
                // Kiểm tra xem nhân sự này đã có lương tháng này chưa
                int viTri = timViTriLuong(maNS, thang, nam);
                if (viTri >= 0) {
                    // Cập nhật lương cũ
                    dsLuong[viTri] = luong;
                } else {
                    // Thêm lương mới
                    if (soLuong >= dsLuong.length) {
                        tangKichThuoc();
                    }
                    dsLuong[soLuong++] = luong;
                }
            }
        }
        
        System.out.println("✅ Đã tính lương cho " + soLuong + " nhân sự!");
    }
    
    /**
     * Tìm vị trí lương trong mảng
     */
    private int timViTriLuong(String maNS, int thang, int nam) {
        for (int i = 0; i < soLuong; i++) {
            if (dsLuong[i] != null && 
                dsLuong[i].getMaNS().equalsIgnoreCase(maNS) &&
                dsLuong[i].getThang() == thang &&
                dsLuong[i].getNam() == nam) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Tăng kích thước mảng
     */
    private void tangKichThuoc() {
        Luong[] moi = new Luong[dsLuong.length * 2];
        for (int i = 0; i < dsLuong.length; i++) {
            moi[i] = dsLuong[i];
        }
        dsLuong = moi;
    }
    
    /**
     * Hiển thị bảng lương tất cả nhân sự
     */
    public void hienThiTatCaLuong() {
        if (soLuong == 0) {
            System.out.println("⚠️ Chưa có dữ liệu lương!");
            return;
        }
        
        System.out.println("\n========================================");
        System.out.println("        DANH SÁCH BẢNG LƯƠNG");
        System.out.println("========================================");
        for (int i = 0; i < soLuong; i++) {
            if (dsLuong[i] != null) {
                dsLuong[i].xuat();
            }
        }
    }
    
    /**
     * Hiển thị lương của 1 nhân sự
     */
    public void hienThiLuongTheoNhanSu(String maNS, int thang, int nam) {
        int viTri = timViTriLuong(maNS, thang, nam);
        if (viTri >= 0) {
            dsLuong[viTri].xuat();
        } else {
            System.out.println("❌ Không tìm thấy lương của NS " + maNS + " tháng " + thang + "/" + nam);
        }
    }
    
    /**
     * Ghi toàn bộ danh sách lương vào file
     */
    public void ghiFile() {
        try (FileWriter fw = new FileWriter("data/luong.txt", false)) {
            for (int i = 0; i < soLuong; i++) {
                if (dsLuong[i] != null) {
                    fw.write(dsLuong[i].toFileString() + "\n");
                }
            }
            System.out.println("✅ Đã ghi " + soLuong + " bảng lương vào file data/luong.txt");
        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi file: " + e.getMessage());
        }
    }
    
    /**
     * Đọc danh sách lương từ file
     */
    public void docFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("data/luong.txt"))) {
            soLuong = 0;
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                
                Luong luong = Luong.fromFileString(line);
                if (luong != null) {
                    if (soLuong >= dsLuong.length) {
                        tangKichThuoc();
                    }
                    dsLuong[soLuong++] = luong;
                }
            }
            System.out.println("✅ Đã đọc " + soLuong + " bảng lương từ file.");
        } catch (FileNotFoundException e) {
            System.out.println("⚠️ File luong.txt chưa tồn tại. Sẽ tạo mới khi ghi dữ liệu.");
        } catch (Exception e) {
            System.out.println("❌ Lỗi đọc file: " + e.getMessage());
        }
    }
    
    /**
     * Menu quản lý lương
     */
    public void menu() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║     QUẢN LÝ LƯƠNG NHÂN SỰ         ║");
            System.out.println("╠════════════════════════════════════╣");
            System.out.println("║ 1. Tính lương tất cả NS (tháng)   ║");
            System.out.println("║ 2. Tính lương 1 nhân sự           ║");
            System.out.println("║ 3. Hiển thị tất cả bảng lương     ║");
            System.out.println("║ 4. Tra cứu lương nhân sự          ║");
            System.out.println("║ 5. Lưu dữ liệu lương              ║");
            System.out.println("║ 6. Đọc dữ liệu lương từ file      ║");
            System.out.println("║ 0. Quay lại (tự động lưu)         ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.print("Chọn chức năng: ");
            
            String chon = sc.nextLine();
            
            switch (chon) {
                case "1":
                    System.out.print("Nhập tháng: ");
                    int thang = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Nhập năm: ");
                    int nam = Integer.parseInt(sc.nextLine().trim());
                    tinhLuongChoTatCaNhanSu(thang, nam);
                    break;
                    
                case "2":
                    System.out.print("Nhập mã nhân sự: ");
                    String maNS = sc.nextLine().trim();
                    System.out.print("Nhập tháng: ");
                    int thang2 = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Nhập năm: ");
                    int nam2 = Integer.parseInt(sc.nextLine().trim());
                    
                    Luong luong = tinhLuongTuDong(maNS, thang2, nam2);
                    luong.xuat();
                    
                    System.out.print("Lưu vào danh sách? (y/n): ");
                    if (sc.nextLine().trim().equalsIgnoreCase("y")) {
                        int viTri = timViTriLuong(maNS, thang2, nam2);
                        if (viTri >= 0) {
                            dsLuong[viTri] = luong;
                            System.out.println("✅ Đã cập nhật lương!");
                        } else {
                            if (soLuong >= dsLuong.length) tangKichThuoc();
                            dsLuong[soLuong++] = luong;
                            System.out.println("✅ Đã thêm lương mới!");
                        }
                    }
                    break;
                    
                case "3":
                    hienThiTatCaLuong();
                    break;
                    
                case "4":
                    System.out.print("Nhập mã nhân sự: ");
                    String maNS3 = sc.nextLine().trim();
                    System.out.print("Nhập tháng: ");
                    int thang3 = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Nhập năm: ");
                    int nam3 = Integer.parseInt(sc.nextLine().trim());
                    hienThiLuongTheoNhanSu(maNS3, thang3, nam3);
                    break;
                    
                case "5":
                    ghiFile();
                    break;
                    
                case "6":
                    docFile();
                    break;
                    
                case "0":
                    System.out.println("💾 Đang lưu dữ liệu trước khi thoát...");
                    ghiFile();
                    System.out.println("✅ Đã lưu! Quay lại menu chính.");
                    return;
                    
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        }
    }
}
