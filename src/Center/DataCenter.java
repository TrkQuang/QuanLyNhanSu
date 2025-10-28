package Center;
import ChamCong.DanhSachChamCong;
import HSNS.QLHSNS;
import NhanSu.DanhSachNhanSu;
import Thuong.QUANLYTHUONG;
import PHONGBAN.QuanLyPhongBan;

/**
 * DataCenter - Trung tâm dữ liệu chung cho toàn hệ thống
 * Cung cấp truy cập global tới các danh sách quản lý chính
 */
public class DataCenter {
    // Danh sách nhân sự (dùng chung cho validation cross-module)
    public static DanhSachNhanSu dsNhanSu = new DanhSachNhanSu();
    
    // Quản lý hồ sơ nhân sự (bao gồm chi tiết NS và chức vụ)
    public static QLHSNS dsHSNS = new QLHSNS();

    // Quản lý chấm công
    public static DanhSachChamCong qlcc = new DanhSachChamCong();
    
    // Quản lý thưởng (bao gồm chi tiết thưởng và danh mục thưởng)
    public static QUANLYTHUONG qlThuong = new QUANLYTHUONG();
    
    // Quản lý phòng ban (bao gồm phòng ban, đề án, phân công)
    public static QuanLyPhongBan qlPhongBan = new QuanLyPhongBan();
    
    // Private constructor để ngăn khởi tạo instance
    private DataCenter() {
        throw new AssertionError("DataCenter không thể khởi tạo!");
    }
}
