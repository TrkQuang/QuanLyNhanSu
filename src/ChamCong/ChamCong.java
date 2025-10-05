package ChamCong;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class ChamCong {
    private static long sttTuTang = docSoDongFile("data/chamcong.txt") +1;
    private long stt;                  // STT của từng đối tượng
    private String maNS;      
    private String ngayCC;       
    private DanhSachTrangThai trangThai;
    private double soGioLam;
    Scanner sc = new Scanner(System.in);
    // Constructor mặc định
    public ChamCong() {
        this.stt = sttTuTang++;
        this.maNS = "";
        this.ngayCC = "";
        this.trangThai = null;
        this.soGioLam = 0;
    }
    // Constructor đầy đủ
    public ChamCong(String maNS, String ngayCC, DanhSachTrangThai trangThai, double soGioLam) {
        this.stt = sttTuTang++; // Tăng mỗi lần tạo mới
        this.maNS = maNS;
        this.ngayCC = ngayCC;
        this.trangThai = trangThai;
        this.soGioLam = soGioLam;
    }
    // Constructor sao chép
    public ChamCong(ChamCong a){
        this.stt = a.stt;
        this.maNS = a.maNS;
        this.ngayCC = a.ngayCC;
        this.trangThai = a.trangThai;
        this.soGioLam = a.soGioLam;
    }
    //get,set
    public long getStt() { return stt; }
    public void setStt(long stt) { this.stt = stt; }
    public String getMaNS() { return maNS; }
    public void setMaNS(String maNS) { this.maNS = maNS; }
    public String getNgayCC() { return ngayCC; }
    public void setNgayCC(String ngayCC) { this.ngayCC = ngayCC; }
    public DanhSachTrangThai getTrangThai() { return trangThai; }
    public void setTrangThai(DanhSachTrangThai trangThai) { this.trangThai = trangThai; }
    public double getSoGioLam() { return soGioLam; }
    public void setSoGioLam(double soGioLam) { this.soGioLam = soGioLam; }
    //doc stt
    public static long docSoDongFile(String path) {
        long count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while (br.readLine() != null) {
                count++;
            }
        } catch (Exception e) {
            // neu ko doc duoc gi het trả về 0
        }
        return count;
    }
    //nhap
    public void nhap() {
        System.out.println("\n-----------------");
        System.out.print("Nhap vao ma nhan su: ");
        maNS = sc.nextLine();
        while (true) {
            System.out.print("Ấn Enter để lấy ngày hiện tại, hoặc nhập ngày (YYYY-MM-DD): ");
            ngayCC = sc.nextLine();
            if (ngayCC.isEmpty()) {
                ngayCC = java.time.LocalDate.now().toString();
                break;
            }
            if (ngayCC.matches("\\d{4}-\\d{2}-\\d{2}")) { //hàm kiểm tra xem đúng định dạng yyyy-mm-dd ko
                                                                // \\d là số (digit, [0-9])
                                                                // \\w là chữ cái hoặc số (word, [A-Za-z0-9_])
                                                                // [A-Za-z] là chữ cái (hoa hoặc thường)
                break;
            }
            System.out.println("Ngày không đúng định dạng. Vui lòng nhập lại (YYYY-MM-DD)!");
        }
    System.out.print("Trang thai(0: Di Lam, 1: Nghi khong luong, 2: Nghi co luong): ");
    int n = Integer.parseInt(sc.nextLine());
    trangThai = DanhSachTrangThai.values()[n];
    if (trangThai.isNghi()) {
        soGioLam = 0;
        System.out.println("So gio lam: 0");
    } else {
        System.out.print("So gio lam: ");
        soGioLam = Double.parseDouble(sc.nextLine());
    }
        System.out.println("---Da them thanh cong---");
    }
    public void xuat() {
        System.out.println("\n-----------------");
        System.out.println("STT: "+stt);
        System.out.println("Ma nhan su: " +maNS);
        System.out.println("Ngay cham cong: " +ngayCC);
        System.out.println("Trang thai: " +trangThai);
        System.out.println("So gio lam: "+soGioLam);
    }
}
