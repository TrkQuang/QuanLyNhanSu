package ChamCong;
import java.util.Scanner;

public class ChamCongThang {
    private long maCC;
    private String maNS;
    private String nam;
    private String thang;
    private double tongGioLam;
    private double tongNgayLam;
    Scanner sc = new Scanner(System.in);

    public ChamCongThang(String maNS, String nam, String thang, double tongGioLam, double tongNgayLam) {
        this.maCC = 10000 + new java.util.Random().nextLong(90000);
        this.maNS = maNS;
        this.nam = nam;
        this.thang = thang;
        this.tongGioLam = tongGioLam;
        this.tongNgayLam = tongNgayLam;
    }

    // Getters & Setters
    public long getMaCC() { return maCC; }
    public String getMaNS() { return maNS; }
    public String getNam() { return nam; }
    public String getThang() { return thang; }
    public double getTongGioLam() { return tongGioLam; }
    public double getTongNgayLam() { return tongNgayLam; }
    public void addTongGioLam(double gioLam) {
        this.tongGioLam += gioLam;
    }
    public void addTongNgayLam(int ngay) {
        this.tongNgayLam += ngay;
    }

    //Nhap, xuat
    public void nhap() {
        System.out.println("\n-----------------");
        System.out.print("Nhập mã nhân sự: ");
        maNS = sc.nextLine();
        System.out.print("Nhập năm: ");
        nam = sc.nextLine();
        System.out.print("Nhập tháng: ");
        thang = sc.nextLine();
        System.out.print("Nhập tổng số giờ làm: ");
        tongGioLam = Double.parseDouble(sc.nextLine());
        System.out.print("Nhập tổng số ngày làm: ");
        tongNgayLam = Double.parseDouble(sc.nextLine());
        System.out.println("---Đã nhập thành công---");
    }

    public void xuat() {
        System.out.println("\n-----------------");
        System.out.println("Mã chấm công: " + maCC);
        System.out.println("Mã nhân sự: " + maNS);
        System.out.println("Năm: " + nam);
        System.out.println("Tháng: " + thang);
        System.out.println("Tổng số giờ làm: " + tongGioLam);
        System.out.println("Tổng số ngày làm: " + tongNgayLam);
    }
}
