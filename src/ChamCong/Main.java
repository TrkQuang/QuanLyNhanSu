package ChamCong;
import java.util.Scanner;
import Center.DataCenter;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== MENU CHẤM CÔNG =====");
            System.out.println("1. Thêm bản ghi chấm công ngày (lưu vào file)");
            System.out.println("2. Xem danh sách chấm công ngày (đọc từ file)");
            System.out.println("3. Tổng hợp chấm công tháng từ file và lưu ra file");
            System.out.println("4. Xem bảng tổng hợp chấm công tháng (đọc từ file)");
            System.out.println("5. Xóa dữ liệu chấm công ngày");
            System.out.println("6. Xóa dữ liệu bảng tổng hợp tháng");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    DataCenter.qlcc.ThemCC();
                    break;
                case 2:
                    DataCenter.qlcc.xuatDanhSachNgayTuFile();
                    break;
                case 3:
                    DataCenter.qlcc.tongHopTuFile();
                    break;
                case 4:
                    DataCenter.qlcc.xuatBangTongHopTuFile();
                    break;
                case 5:
                    DataCenter.qlcc.xoaDuLieuChamCong();
                    break;
                case 6:
                    DataCenter.qlcc.xoaDuLieuCongThang();
                    break;
                case 0:
                    System.out.println("Kết thúc chương trình!");
                    return;
                default:
                    System.out.println("Chức năng không hợp lệ!");
            }
        }
    }
}