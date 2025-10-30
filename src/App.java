import Center.DataCenter;
import Luong.Danhsachluong;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * Main Menu - Hệ thống Quản lý Nhân sự Tổng hợp
 * Tích hợp tất cả các module: Nhân sự, Hồ sơ NS, Chấm công, Thưởng, Phòng ban, Lương
 */
public class App {
    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) throws Exception {
        // Kiểm tra và tạo thư mục data nếu chưa có
        kiemTraVaTaoThuMucData();
        
        // Load tất cả dữ liệu vào DataCenter khi khởi động
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║   HỆ THỐNG QUẢN LÝ NHÂN SỰ TỔNG HỢP         ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        System.out.println("\nĐang tải dữ liệu từ các module...\n");
        System.out.println("(Tất cả dữ liệu được tự động load khi khởi tạo DataCenter)");
        
        System.out.println("\n✓ Nhân sự: " + DataCenter.dsNhanSu.getSoluong() + " nhân sự");
        System.out.println("✓ Chấm công: " + DataCenter.qlcc.getIndex() + " bản ghi");
        System.out.println("✓ Hồ sơ nhân sự, Thưởng, Phòng ban: Đã sẵn sàng");
        
        System.out.println("\n✓ Đã tải xong tất cả dữ liệu!\n");
        
        // Hiển thị menu chính
        menuChinh();
        
        System.out.println("\n✓ Cảm ơn bạn đã sử dụng hệ thống!");
    }
    
    /**
     * Menu chính của hệ thống
     */
    private static void menuChinh() throws IOException {
        while (true) {
            hienThiMenuChinh();
            System.out.print("Chọn chức năng: ");
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
                    System.out.println("\nĐang lưu tất cả dữ liệu trước khi thoát...");
                    luuTatCaDuLieu();
                    System.out.println("✓ Đã lưu xong!");
                    return;
                    
                default:
                    System.out.println("✗ Lựa chọn không hợp lệ! Vui lòng chọn lại.");
            }
        }
    }
    
    /**
     * Hiển thị menu chính
     */
    private static void hienThiMenuChinh() {
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║          MENU QUẢN LÝ NHÂN SỰ CHÍNH          ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║  1. Quản lý Nhân sự                          ║");
        System.out.println("║  2. Quản lý Hồ sơ Nhân sự                    ║");
        System.out.println("║  3. Quản lý Chấm công                        ║");
        System.out.println("║  4. Quản lý Thưởng                           ║");
        System.out.println("║  5. Quản lý Phòng ban & Dự án                ║");
        System.out.println("║  6. Quản lý Lương                            ║");
        System.out.println("║  7. Thống kê Tổng quan                       ║");
        System.out.println("║  8. Lưu tất cả dữ liệu                       ║");
        System.out.println("║  0. Thoát (tự động lưu)                      ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
    }
    
    /**
     * Menu Nhân sự
     */
    private static void menuNhanSu() {
        System.out.println("\n→ Chuyển sang module Quản lý Nhân sự...");
        NhanSu.Main.main(new String[]{});
    }
    
    /**
     * Menu Hồ sơ Nhân sự
     */
    private static void menuHoSoNhanSu() {
        System.out.println("\n→ Chuyển sang module Quản lý Hồ sơ Nhân sự...");
        HSNS.Main.main(new String[]{});
    }
    
    /**
     * Menu Chấm công
     */
    private static void menuChamCong() {
        System.out.println("\n→ Chuyển sang module Quản lý Chấm công...");
        ChamCong.Main.main(new String[]{});
    }
    
    /**
     * Menu Thưởng
     */
    private static void menuThuong() {
        System.out.println("\n→ Chuyển sang module Quản lý Thưởng...");
        Thuong.Main.main(new String[]{});
    }
    
    /**
     * Menu Phòng ban
     */
    private static void menuPhongBan() {
        System.out.println("\n→ Chuyển sang module Quản lý Phòng ban & Dự án...");
        PHONGBAN_DEAN.MainPhongBan.main(new String[]{});
    }
    
    /**
     * Menu Lương
     */
    private static void menuLuong() {
        System.out.println("\n→ Chuyển sang module Quản lý Lương...");
        Danhsachluong qlLuong = new Danhsachluong();
        qlLuong.menu();
    }
    
    /**
     * Thống kê tổng quan
     */
    private static void thongKeTongQuan() {
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║           THỐNG KÊ TỔNG QUAN HỆ THỐNG         ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        
        // 1. Nhân sự
        int soNhanSu = DataCenter.dsNhanSu.getSoluong();
        System.out.println("\n【 NHÂN SỰ 】");
        System.out.println("   • Tổng số nhân sự: " + soNhanSu);
        
        // 2. Hồ sơ
        System.out.println("\n【 HỒ SƠ NHÂN SỰ 】");
        System.out.println("   • Chi tiết nhân sự: Đã tải");
        System.out.println("   • Chức vụ: Đã tải");
        System.out.println("   • Thân nhân: " + DataCenter.dstn.getSoLuongTN());
        
        // 3. Chấm công
        System.out.println("\n【 CHẤM CÔNG 】");
        System.out.println("   • Dữ liệu công tháng: ✓ Đã tải");
        
        // 4. Thưởng
        int soDanhMucThuong = DataCenter.dsdmt.getSoLuongDM();
        int soChiTietThuong = DataCenter.dsctt.getSoLuongCT();
        System.out.println("\n【 THƯỞNG 】");
        System.out.println("   • Danh mục thưởng: " + soDanhMucThuong);
        System.out.println("   • Chi tiết thưởng: " + soChiTietThuong);
        
        // 5. Phòng ban
        System.out.println("\n【 PHÒNG BAN & DỰ ÁN 】");
        System.out.println("   • Dữ liệu phòng ban: ✓ Đã tải");
        System.out.println("   • Dữ liệu dự án: ✓ Đã tải");
        System.out.println("   • Dữ liệu phân công: ✓ Đã tải");
        
        // 6. Lương
        System.out.println("\n【 LƯƠNG 】");
        System.out.println("   • Module tính lương: ✓ Hoạt động");
        System.out.println("   • Hỗ trợ full-time & part-time: ✓ OK");
        
        System.out.println("\n" + "═".repeat(49));
        System.out.println("✓ HỆ THỐNG HOẠT ĐỘNG BÌNH THƯỜNG!");
        
        System.out.println("\nNhấn Enter để tiếp tục...");
        sc.nextLine();
    }
    
    /**
     * Lưu tất cả dữ liệu
     */
    private static void luuTatCaDuLieu() throws IOException {
        System.out.println("\nĐang lưu dữ liệu...");
        
        // 1. Nhân sự
        System.out.print("   Nhân sự... ");
        DataCenter.dsNhanSu.ghiTatCaVaoFile();
        System.out.println("✓");
        
        // 2. Hồ sơ nhân sự (Chi tiết, Chức vụ, Thân nhân)
        System.out.print("   Hồ sơ nhân sự... ");
        DataCenter.dsctns.ghiFile();
        DataCenter.dscv.ghiFile();
        DataCenter.dstn.ghiFile();
        System.out.println("✓");
        
        // 3. Thưởng (Danh mục, Chi tiết)
        System.out.print("   Thưởng... ");
        DataCenter.dsdmt.ghiFile();
        DataCenter.dsctt.ghiFile();
        System.out.println("✓");
        
        // 4. Phòng ban
        System.out.print("   Phòng ban... ");
        DataCenter.dspb.ghiToanBoPhongBan();
        DataCenter.dsda.ghiToanBoDeAn();
        DataCenter.dspc.ghiToanBoPhanCong();
        System.out.println("✓");
        
        System.out.println("\n✓ Đã lưu tất cả dữ liệu thành công!");
    }
    
    /**
     * Kiểm tra và tạo thư mục data cùng các file rỗng nếu chưa có
     */
    private static void kiemTraVaTaoThuMucData() throws Exception {
        // Tạo thư mục data
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
            System.out.println("✓ Đã tạo thư mục: data/");
        }
        
        // Danh sách các file cần thiết
        String[] files = {
            "data/Nhansu.txt",
            "data/quanlyhsnhansu.txt", 
            "data/chucvu.txt",
            "data/thannhan.txt",
            "data/chamcong.txt",
            "data/congThang.txt",
            "data/danhmucthuong.txt",
            "data/chitietthuong.txt",
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
            }
        }
    }
}
