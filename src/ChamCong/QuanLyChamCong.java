package ChamCong;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class QuanLyChamCong {
    private static int index = 0;
    private ChamCong Dscc[] = new ChamCong[10];
    private ChamCongThang bangChamCongThang[] = new ChamCongThang[50];
    private int soBangThang = 0;

    // Mở rộng mảng nếu đầy (giữ nguyên như cũ)
    private void moRongMang() {
        ChamCong[] moi = new ChamCong[Dscc.length * 2];
        for (int i = 0; i < Dscc.length; i++) {
            moi[i] = Dscc[i];
        }
        Dscc = moi;
    }
    // Ghi chấm công vào file
    public void ghiChamCongVaoFile(ChamCong cc) {
        try (FileWriter fw = new FileWriter("data/chamcong.txt", true)) { // true: ghi tiếp vào cuối file
            fw.write(cc.getStt() + "," +
                    cc.getMaNS() + "," +
                    cc.getNgayCC() + "," +
                    cc.getTrangThai() + "," +
                    cc.getSoGioLam() + "\n");
        } catch (IOException e) {
            System.out.println("Lỗi ghi file: " + e.getMessage());
        }
    }
    public void ThemCC() {
        if (index == Dscc.length) {
            moRongMang();
        }
        ChamCong cc = new ChamCong();  
        cc.nhap();                    
        Dscc[index++] = cc;
        ghiChamCongVaoFile(cc); //tich hop vao file lun de ko mat du lieu khi close chuong trinh
    }

    // Tổng hợp chấm công tháng cho tất cả nhân viên
public void tongHopTuFile() {
    ChamCong[] dsccFile = new ChamCong[50];
    int soLuong = 0;
    //doc tu file va ghi vao mang
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
            dsccFile[soLuong++] = cc;
        }
    } catch (Exception e) {
        System.out.println("Lỗi đọc file: " + e.getMessage());
        return;
    }

    soBangThang = 0;
    for (int i = 0; i < soLuong; i++) {
        ChamCong cc = dsccFile[i];
        String[] dateArr = cc.getNgayCC().split("-");
        if (dateArr.length < 2) continue;
        String nam = dateArr[0];
        String thang = dateArr[1];

        int j;
        for (j = 0; j < soBangThang; j++) {
            if (bangChamCongThang[j].getMaNS().equals(cc.getMaNS())
                && bangChamCongThang[j].getNam().equals(nam)
                && bangChamCongThang[j].getThang().equals(thang)) {
                break;
            }
        }
        if (j == soBangThang) {
            bangChamCongThang[soBangThang++] = new ChamCongThang(
                cc.getMaNS(), nam, thang, 0, 0
            );
        }
        bangChamCongThang[j].addTongGioLam(cc.getSoGioLam());
        if (cc.getTrangThai() == DanhSachTrangThai.DILAM || cc.getTrangThai() == DanhSachTrangThai.NGHICOLUONG) {
            bangChamCongThang[j].addTongNgayLam(1);
        }
    }
    ghiChamCongThangVaoFile(bangChamCongThang, soBangThang);
    // In bảng tổng hợp
    System.out.println("\n--- Bảng tổng hợp chấm công tháng (đọc từ file) ---");
    for (int i = 0; i < soBangThang; i++) {
        bangChamCongThang[i].xuat();
    }
}
    // Xuất danh sách chấm công từng ngày
    public void xuatDanhSachNgayTuFile() {
        System.out.println("\n--- Danh sách chấm công ngày (từ file) ---");
        try (BufferedReader br = new BufferedReader(new FileReader("data/chamcong.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr.length < 5) continue;
                System.out.printf("STT: %s | Mã NS: %s | Ngày: %s | Trạng thái: %s | Giờ làm: %s\n",
                        arr[0], arr[1], arr[2], arr[3], arr[4]);
            }
        } catch (Exception e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
    }

    // Xuất bảng tổng hợp chấm công tháng
    public void xuatBangTongHopTuFile() {
        System.out.println("\n--- Bảng tổng hợp chấm công tháng (từ file) ---");
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
    public void ghiChamCongThangVaoFile(ChamCongThang[] bangChamCongThang, int soBangThang) {
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
}