package ChamCong;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import Center.DataCenter;

/**
 * Quản lý tổng hợp công tháng
 * - Tổng hợp từ chấm công ngày thành công tháng
 * - Ghi/đọc file congThang.txt
 * - Tìm kiếm và thống kê công tháng
 */
public class DanhSachCongThang {
    private ChamCongThang bangChamCongThang[] = new ChamCongThang[50];
    private int soBangThang = 0;
    
    // Constructor - tự động đọc file khi khởi tạo
    public DanhSachCongThang() {
        docCongThangTuFile();
    }
    
    /**
     * Tổng hợp chấm công từ DataCenter (lấy từ bộ nhớ) thành bảng công tháng
     * Ghi kết quả vào file congThang.txt
     */
    public void tongHopTuDataCenter() {
        // Bước 1: Lấy dữ liệu chấm công ngày từ DataCenter (đã load sẵn trong bộ nhớ)
        ChamCong[] dscc = DataCenter.qlcc.getDscc();
        int soLuong = DataCenter.qlcc.getIndex();

        System.out.println("Đang tổng hợp " + soLuong + " bản ghi chấm công...");

        // Bước 2: Tổng hợp theo tháng/năm/nhân sự
        soBangThang = 0;
        for (int i = 0; i < soLuong; i++) {
            ChamCong cc = dscc[i];
            String[] dateArr = cc.getNgayCC().split("-");
            if (dateArr.length < 2) continue;
            String nam = dateArr[0];
            String thang = dateArr[1];

            // Bước 3: Tìm bảng công tháng đã tồn tại cho nhân sự này
            int viTri = -1;
            for (int j = 0; j < soBangThang; j++) {
                if (bangChamCongThang[j].getMaNS().equals(cc.getMaNS())
                        && bangChamCongThang[j].getNam().equals(nam)
                        && bangChamCongThang[j].getThang().equals(thang)) {
                    viTri = j;
                    break;
                }
            }

            // Bước 4: Nếu chưa có thì tạo mới
            if (viTri == -1) {
                bangChamCongThang[soBangThang] = new ChamCongThang(
                        cc.getMaNS(), nam, thang, 0, 0
                );
                viTri = soBangThang;
                soBangThang++;
            }

            // Bước 5: Cộng dồn giờ làm và ngày làm
            bangChamCongThang[viTri].addTongGioLam(cc.getSoGioLam());
            if (cc.getTrangThai() == DanhSachTrangThai.DILAM || 
                cc.getTrangThai() == DanhSachTrangThai.NGHICOLUONG) {
                bangChamCongThang[viTri].addTongNgayLam(1);
            }
        }

        // Bước 6: Ghi kết quả vào file congThang.txt
        ghiTatCaCongThangVaoFile();

        // Bước 7: In bảng tổng hợp
        System.out.println("\n========== Bảng tổng hợp chấm công tháng ==========");
        for (int i = 0; i < soBangThang; i++) {
            bangChamCongThang[i].xuat();
        }
    }

    /**
     * Ghi tất cả công tháng vào file congThang.txt
     */
    private void ghiTatCaCongThangVaoFile() {
        try (FileWriter fw = new FileWriter("data/congThang.txt")) {
            for (int i = 0; i < soBangThang; i++) {
                ChamCongThang ct = bangChamCongThang[i];
                fw.write(ct.getMaCC() + "," +
                        ct.getMaNS() + "," +
                        ct.getNam() + "," +
                        ct.getThang() + "," +
                        ct.getTongGioLam() + "," +
                        ct.getTongNgayLam() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Lỗi ghi file: " + e.getMessage());
        }
    }

    /**
     * Xuất bảng tổng hợp chấm công tháng từ file
     */
    public void xuatBangTongHopTuFile() {
        System.out.println("\n========== Bảng tổng hợp chấm công tháng ==========");
        try (BufferedReader br = new BufferedReader(new FileReader("data/congThang.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr.length < 6) continue;
                System.out.printf("Mã CC: %s | Mã NS: %s | Năm: %s | Tháng: %s | Tổng giờ làm: %s | Tổng ngày làm: %s\n",
                        arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
            }
        } catch (Exception e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
    }

    /**
     * Xóa dữ liệu công tháng
     */
    public void xoaDuLieuCongThang() {
        try (FileWriter fw = new FileWriter("data/congThang.txt")) {
            fw.write("");
        } catch (Exception e) {
            System.out.println("Lỗi xóa file: " + e.getMessage());
        }
        System.out.println("Đã xóa dữ liệu bảng tổng hợp tháng!");
    }

    /**
     * API: Tìm công tháng của nhân sự theo mã NS, tháng, năm
     * @param maNS Mã nhân sự
     * @param thang Tháng (1-12)
     * @param nam Năm
     * @return ChamCongThang object hoặc null nếu không tìm thấy
     */
    public ChamCongThang timCongThang(String maNS, int thang, int nam) {
        String thangStr = String.valueOf(thang);
        String namStr = String.valueOf(nam);

        for (int i = 0; i < soBangThang; i++) {
            if (bangChamCongThang[i] != null &&
                    bangChamCongThang[i].getMaNS().equalsIgnoreCase(maNS) &&
                    bangChamCongThang[i].getThang().equals(thangStr) &&
                    bangChamCongThang[i].getNam().equals(namStr)) {
                return bangChamCongThang[i];
            }
        }
        return null;
    }

    /**
     * API: Đọc công tháng từ file và load vào bộ nhớ
     */
    public void docCongThangTuFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("data/congThang.txt"))) {
            soBangThang = 0;
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] arr = line.split(",");
                if (arr.length < 6) continue;

                String maNS = arr[1].trim();
                String nam = arr[2].trim();
                String thang = arr[3].trim();
                double tongGioLam = Double.parseDouble(arr[4].trim());
                double tongNgayLam = Double.parseDouble(arr[5].trim());

                bangChamCongThang[soBangThang++] = new ChamCongThang(
                        maNS, nam, thang, tongGioLam, tongNgayLam
                );
            }
            System.out.println("Đã đọc " + soBangThang + " bảng công tháng từ file.");
        } catch (Exception e) {
            System.out.println("Lỗi đọc file congThang.txt: " + e.getMessage());
        }
    }

    /**
     * Thống kê bảng chấm công theo từng mã nhân sự
     */
    public void thongKeBangChamCongTheoMaNS() {
        Map<String, ChamCongThang[]> mapBangCong = new HashMap<>();
        Map<String, Integer> mapSoLuong = new HashMap<>();
        int maxThang = 12;

        // Khởi tạo mảng cho từng mã nhân sự
        for (int i = 0; i < soBangThang; i++) {
            ChamCongThang ct = bangChamCongThang[i];
            String maNS = ct.getMaNS();
            if (!mapBangCong.containsKey(maNS)) {
                mapBangCong.put(maNS, new ChamCongThang[maxThang]);
                mapSoLuong.put(maNS, 0);
            }
            int idx = mapSoLuong.get(maNS);
            if (idx < maxThang) {
                mapBangCong.get(maNS)[idx] = ct;
                mapSoLuong.put(maNS, idx + 1);
            }
        }

        // In kết quả thống kê
        for (String maNS : mapBangCong.keySet()) {
            System.out.println("\n========== Mã nhân sự: " + maNS + " ==========");
            ChamCongThang[] arr = mapBangCong.get(maNS);
            int soLuong = mapSoLuong.get(maNS);
            for (int i = 0; i < soLuong; i++) {
                arr[i].xuat();
            }
        }
    }
}
