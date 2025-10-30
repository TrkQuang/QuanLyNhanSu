package Center;
import ChamCong.DanhSachChamCong;
import ChamCong.DanhSachCongThang;
import HSNS.Danhsachchitietnhansu;
import HSNS.Danhsachchucvu;
import HSNS.Danhsachthannhan;
import Luong.Danhsachluong;
import NhanSu.DanhSachNhanSu;
import PHONGBAN_DEAN.Danhsachdean;
import PHONGBAN_DEAN.Danhsachphancong;
import PHONGBAN_DEAN.Danhsachphongban;
import Thuong.Danhsachchitietthuong;
import Thuong.Danhsachdanhmucthuong;

/**
 * DataCenter - Trung tâm dữ liệu chung cho toàn hệ thống
 * Cung cấp truy cập global tới các danh sách quản lý chính
 */

public class DataCenter {
    // Danh sách nhân sự (dùng chung cho validation cross-module)
    public static DanhSachNhanSu dsNhanSu = new DanhSachNhanSu();
    
    // Quản lý hồ sơ nhân sự (bao gồm chi tiết NS và chức vụ)
    public static Danhsachchitietnhansu dsctns = new Danhsachchitietnhansu();
    public static Danhsachchucvu dscv = new Danhsachchucvu();
    public static Danhsachthannhan dstn = new Danhsachthannhan();

    // Quản lý chấm công (tách làm 2: chấm công ngày và công tháng)
    public static DanhSachChamCong qlcc = new DanhSachChamCong();
    public static DanhSachCongThang qlct = new DanhSachCongThang();
    
    // Quản lý thưởng (bao gồm chi tiết thưởng và danh mục thưởng)
    public static Danhsachdanhmucthuong dsdmt = new Danhsachdanhmucthuong();
    public static Danhsachchitietthuong dsctt = new Danhsachchitietthuong();
    
    // Quản lý phòng ban (bao gồm phòng ban, đề án, phân công)
    public static Danhsachphancong dspc = new Danhsachphancong();
    public static Danhsachdean dsda = new Danhsachdean();
    public static Danhsachphongban dspb = new Danhsachphongban();
    
    // Quản lý lương
    public static Danhsachluong dsLuong = new Danhsachluong();
    
    // Private constructor để ngăn khởi tạo instance
    private DataCenter() {
        throw new AssertionError("DataCenter không thể khởi tạo!");
    }
}
