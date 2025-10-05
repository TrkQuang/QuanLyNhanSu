package ChamCong;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QuanLyChamCong qlcc = new QuanLyChamCong();

        while (true) {
            System.out.println("\n===== MENU CHẤM CÔNG =====");
            System.out.println("1. Thêm bản ghi chấm công ngày");
            System.out.println("2. Xem danh sách chấm công ngày");
            System.out.println("3. Tổng hợp chấm công tháng từ file");
            System.out.println("4. Xem bảng tổng hợp chấm công tháng");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    qlcc.ThemCC();
                    break;
                case 2:
                    qlcc.xuatDanhSachNgayTuFile();
                    break;
                case 3:
                    qlcc.tongHopTuFile(); // tổng hợp từ file, ghi bảng tổng hợp ra file
                    break;
                case 4:
                    qlcc.xuatBangTongHopTuFile();
                    break;
                case 0:
                    System.out.println("Kết thúc chương trình!");
                    sc.close();
                    return;
                default:
                    System.out.println("Chức năng không hợp lệ!");
            }
        }
    }
}