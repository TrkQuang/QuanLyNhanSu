package ChamCong;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Quản lý danh sách chấm công theo ngày
 * - Thêm chấm công mới
 * - Ghi/đọc file chamcong.txt
 * - Xuất danh sách chấm công ngày
 * - Lưu trữ dữ liệu trong bộ nhớ để truy cập nhanh
 */
public class DanhSachChamCong {
    private ChamCong Dscc[] = new ChamCong[1000]; // Mảng chứa chấm công
    private int index = 0; // Số lượng chấm công hiện tại

    // Constructor - tự động đọc file khi khởi tạo
    public DanhSachChamCong() {
        docChamCongTuFile();
    }

    // Getter để các module khác truy cập dữ liệu
    public ChamCong[] getDscc() {
        return Dscc;
    }

    public int getIndex() {
        return index;
    }

    // Mở rộng mảng nếu đầy
    private void moRongMang() {
        ChamCong[] moi = new ChamCong[Dscc.length * 2];
        for (int i = 0; i < Dscc.length; i++) {
            moi[i] = Dscc[i];
        }
        Dscc = moi;
    }

    // Ghi chấm công vào file
    public void ghiChamCongVaoFile(ChamCong cc) {
        try (FileWriter fw = new FileWriter("data/chamcong.txt", true)) {
            fw.write(cc.getStt() + "," +
                    cc.getMaNS() + "," +
                    cc.getNgayCC() + "," +
                    cc.getTrangThai() + "," +
                    cc.getSoGioLam() + "\n");
        } catch (IOException e) {
            System.out.println("Lỗi ghi file: " + e.getMessage());
        }
    }

    // Thêm chấm công mới
    public void ThemCC() {
        if (index == Dscc.length) {
            moRongMang();
        }
        ChamCong cc = new ChamCong();
        cc.nhap();
        Dscc[index++] = cc;
        ghiChamCongVaoFile(cc);
    }

    // Xuất danh sách chấm công từng ngày (từ bộ nhớ)
    public void xuatDanhSachNgay() {
        System.out.println("\n--- Danh sách chấm công ngày ---");
        if (index == 0) {
            System.out.println("Chưa có dữ liệu chấm công!");
            return;
        }
        for (int i = 0; i < index; i++) {
            System.out.printf("STT: %d | Mã NS: %s | Ngày: %s | Trạng thái: %s | Giờ làm: %.1f\n",
                    Dscc[i].getStt(), 
                    Dscc[i].getMaNS(), 
                    Dscc[i].getNgayCC(), 
                    Dscc[i].getTrangThai(), 
                    Dscc[i].getSoGioLam());
        }
    }

    // Xóa dữ liệu chấm công ngày
    public void xoaDuLieuChamCong() {
        try (FileWriter fw = new FileWriter("data/chamcong.txt")) {
            fw.write("");
        } catch (Exception e) {
            System.out.println("Lỗi xóa file: " + e.getMessage());
        }
        System.out.println("Đã xóa dữ liệu chấm công ngày!");
    }

    /**
     * API: Đọc tất cả chấm công ngày từ file vào mảng Dscc[]
     * Load dữ liệu 1 lần để các module khác dùng chung
     */
    public void docChamCongTuFile() {
        index = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("data/chamcong.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr.length < 5) continue;

                String maNS = arr[1];
                String ngayCC = arr[2];
                DanhSachTrangThai trangThai = DanhSachTrangThai.valueOf(arr[3]);
                double soGioLam = Double.parseDouble(arr[4]);
                ChamCong cc = new ChamCong(maNS, ngayCC, trangThai, soGioLam);
                Dscc[index++] = cc;
            }
            System.out.println("Đã đọc " + index + " bản ghi chấm công từ file.");
        } catch (Exception e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
    }
}