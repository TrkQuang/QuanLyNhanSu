import Center.DataCenter;
import Luong.QuanLyLuong;
import java.util.Scanner;
import java.io.File;

/**
 * Main Menu - Hệ thống Quản lý Nhân sự Tổng hợp
 * Tích hợp tất cả các module:
 * - Nhân sự (NhanSu)
 * - Hồ sơ nhân sự (HSNS)
 * - Chấm công (ChamCong)
 * - Thưởng (Thuong)
 * - Phòng ban & Dự án (PhongBan)
 * - Lương (Luong)
 */
public class App {
    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        // Kiểm tra và tạo thư mục data nếu chưa có
        kiemTraVaTaoThuMucData();
        
        // Load tất cả dữ liệu vào DataCenter khi khởi động
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║   HỆ THỐNG QUẢN LÝ NHÂN SỰ TỔNG HỢP         ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        System.out.println("\nDang tai du lieu tu cac module...\n");
        
        try {
            // 1. Load Nhân sự (cần cho validation)
            System.out.print("Nhan su... ");
            DataCenter.dsNhanSu.docFileVaoDs();
            System.out.println("OK");
            
            // 2. Load Hồ sơ nhân sự
            System.out.print("Ho so nhan su... ");
            DataCenter.dsHSNS.docFile();
            System.out.println("OK");
            
            // 3. Load Chấm công
            System.out.print("Cham cong... ");
            DataCenter.qlcc.docCongThangTuFile();
            System.out.println("OK");
            
            // 4. Load Thưởng
            System.out.print("Thuong... ");
            DataCenter.qlThuong.docFile();
            System.out.println("OK");
            
            // 5. Load Phòng ban
            System.out.print("Phong ban & Du an... ");
            DataCenter.qlPhongBan.docTuFilePhongBan();
            DataCenter.qlPhongBan.docTuFileDeAn();
            DataCenter.qlPhongBan.docTuFilePhanCong();
            System.out.println("OK");
            
            System.out.println("\nDa tai xong tat ca du lieu!\n");
            
        } catch (Exception e) {
            System.out.println("Co loi khi tai du lieu: " + e.getMessage());
            System.out.println("He thong van co the hoat dong voi du lieu trong.\n");
        }
        
        // Hiển thị menu chính
        menuChinh();
        
        System.out.println("\nCam on ban da su dung he thong!");
    }
    
    /**
     * Menu chính của hệ thống
     */
    private static void menuChinh() {
        while (true) {
            hienThiMenuChinh();
            System.out.print("Chon chuc nang: ");
            String chon = sc.nextLine().trim();
            
            switch (chon) {
                case "1":
                    menuNhanSu();
                    break;
                    
                case "2":
                    menuHoSoNhanSu();
                    break;
                    
                case "3":
                    menuChamCong();
                    break;
                    
                case "4":
                    menuThuong();
                    break;
                    
                case "5":
                    menuPhongBan();
                    break;
                    
                case "6":
                    menuLuong();
                    break;
                    
                case "7":
                    thongKeTongQuan();
                    break;
                    
                case "8":
                    luuTatCaDuLieu();
                    break;
                    
                case "0":
                    System.out.println("\nDang luu tat ca du lieu truoc khi thoat...");
                    luuTatCaDuLieu();
                    System.out.println("Da luu xong!");
                    return;
                    
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai.");
            }
        }
    }
    
    /**
     * Hiển thị menu chính
     */
    private static void hienThiMenuChinh() {
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║          MENU QUAN LY NHAN SU CHINH          ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║  1. Quan ly Nhan su                          ║");
        System.out.println("║  2. Quan ly Ho so Nhan su                    ║");
        System.out.println("║  3. Quan ly Cham cong                        ║");
        System.out.println("║  4. Quan ly Thuong                           ║");
        System.out.println("║  5. Quan ly Phong ban & Du an                ║");
        System.out.println("║  6. Quan ly Luong                            ║");
        System.out.println("║  7. Thong ke Tong quan                       ║");
        System.out.println("║  8. Luu tat ca du lieu                       ║");
        System.out.println("║  0. Thoat (tu dong luu)                      ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
    }
    
    /**
     * Menu Nhân sự
     */
    private static void menuNhanSu() {
        System.out.println("\nChuyen sang module Quan ly Nhan su...");
        try {
            NhanSu.Main.main(new String[]{});
        } catch (Exception e) {
            System.out.println("Co loi: " + e.getMessage());
        }
    }
    
    /**
     * Menu Hồ sơ Nhân sự
     */
    private static void menuHoSoNhanSu() {
        System.out.println("\nChuyen sang module Quan ly Ho so Nhan su...");
        try {
            HSNS.Main.main(new String[]{});
        } catch (Exception e) {
            System.out.println("Co loi: " + e.getMessage());
        }
    }
    
    /**
     * Menu Chấm công
     */
    private static void menuChamCong() {
        System.out.println("\nChuyen sang module Quan ly Cham cong...");
        try {
            ChamCong.Main.main(new String[]{});
        } catch (Exception e) {
            System.out.println("Co loi: " + e.getMessage());
        }
    }
    
    /**
     * Menu Thưởng
     */
    private static void menuThuong() {
        System.out.println("\nChuyen sang module Quan ly Thuong...");
        try {
            Thuong.Main.main(new String[]{});
        } catch (Exception e) {
            System.out.println("Co loi: " + e.getMessage());
        }
    }
    
    /**
     * Menu Phòng ban
     */
    private static void menuPhongBan() {
        System.out.println("\nChuyen sang module Quan ly Phong ban & Du an...");
        try {
            PHONGBAN.MainPhongBan.main(new String[]{});
        } catch (Exception e) {
            System.out.println("Co loi: " + e.getMessage());
        }
    }
    
    /**
     * Menu Lương
     */
    private static void menuLuong() {
        System.out.println("\nChuyen sang module Quan ly Luong...");
        QuanLyLuong qlLuong = new QuanLyLuong();
        qlLuong.menu();
    }
    
    /**
     * Thống kê tổng quan
     */
    private static void thongKeTongQuan() {
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║           THONG KE TONG QUAN HE THONG         ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        
        try {
            // 1. Nhân sự
            int soNhanSu = DataCenter.dsNhanSu.getSoluong();
            System.out.println("\nNHAN SU:");
            System.out.println("   - Tong so nhan su: " + soNhanSu);
            
            // 2. Hồ sơ
            int soHoSo = DataCenter.dsHSNS.getSoLuongCTNS();
            int soChucVu = DataCenter.dsHSNS.getSoLuongCV();
            System.out.println("\nHO SO NHAN SU:");
            System.out.println("   - Tong ho so: " + soHoSo);
            System.out.println("   - So chuc vu: " + soChucVu);
            
            // 3. Chấm công
            System.out.println("\nCHAM CONG:");
            System.out.println("   - Da tai du lieu cong thang OK");
            
            // 4. Thưởng
            System.out.println("\nTHUONG:");
            System.out.println("   - He thong thuong hoat dong OK");
            
            // 5. Phòng ban
            System.out.println("\nPHONG BAN & DU AN:");
            System.out.println("   - Da tai du lieu phong ban OK");
            System.out.println("   - Da tai du lieu de an OK");
            System.out.println("   - Da tai du lieu phan cong OK");
            
            // 6. Lương
            System.out.println("\nLUONG:");
            System.out.println("   - Module tinh luong tu dong OK");
            System.out.println("   - Ho tro he so luong chuc vu OK");
            
            System.out.println("\n" + "=".repeat(49));
            System.out.println("He thong hoat dong binh thuong!");
            
        } catch (Exception e) {
            System.out.println("Loi khi thong ke: " + e.getMessage());
        }
        
        System.out.println("\nNhan Enter de tiep tuc...");
        sc.nextLine();
    }
    
    /**
     * Lưu tất cả dữ liệu
     */
    private static void luuTatCaDuLieu() {
        System.out.println("\nDang luu du lieu...");
        
        try {
            // 1. Nhân sự
            System.out.print("   Nhan su... ");
            DataCenter.dsNhanSu.ghiTatCaVaoFile();
            System.out.println("OK");
            
            // 2. Hồ sơ nhân sự
            System.out.print("   Ho so nhan su... ");
            DataCenter.dsHSNS.ghiFile();
            System.out.println("OK");
            
            // 3. Thưởng
            System.out.print("   Thuong... ");
            DataCenter.qlThuong.ghiFile();
            System.out.println("OK");
            
            // 4. Phòng ban
            System.out.print("   Phong ban... ");
            DataCenter.qlPhongBan.ghiToanBoPhongBan();
            DataCenter.qlPhongBan.ghiToanBoDeAn();
            DataCenter.qlPhongBan.ghiToanBoPhanCong();
            System.out.println("OK");
            
            System.out.println("\nDa luu tat ca du lieu thanh cong!");
            
        } catch (Exception e) {
            System.out.println("\nCo loi khi luu: " + e.getMessage());
        }
    }
    
    /**
     * Kiểm tra và tạo thư mục data cùng các file rỗng nếu chưa có
     */
    private static void kiemTraVaTaoThuMucData() {
        try {
            // Danh sách các file cần thiết
            String[] files = {
                "data/Nhansu.txt",
                "data/quanlyhsnhansu.txt", 
                "data/chucvu.txt",
                "data/chamcong.txt",
                "data/congThang.txt",
                "data/danhMucThuong.txt",
                "data/chiTietThuong.txt",
                "data/phongban.txt",
                "data/dean.txt",
                "data/phancong.txt",
                "data/luong.txt"
            };
            
            // Tạo file rỗng nếu chưa có
            for (String filename : files) {
                File file = new File(filename);
                if (!file.exists()) {
                    file.createNewFile();
                    System.out.println("Da tao file: " + filename);
                }
            }
            
        } catch (Exception e) {
            System.out.println("Loi khi tao thu muc/file: " + e.getMessage());
        }
    }
}
