package NhanSu;

import java.util.Scanner;

import Center.DataCenter;

public class Nhansu {
    //thuộc tính
    protected String maNS;
    protected String ho;
    protected String ten;
    protected String ngaySinh;
    protected String gioitinh;
    protected float phucap;
    protected String maPhong;
    protected String machucvu;
    //constructor
    public Nhansu() {
        maNS = "";
        ho = "";
        ten = "";
        ngaySinh = "";
        gioitinh = "";
        phucap = 0;
        maPhong = "";
        machucvu = "";
    }
    public Nhansu(String maNS, String ho, String ten, String ngaySinh, String gioitinh, float phucap, String maPhong, String machucvu) {
        this.maNS = maNS;
        this.ho = ho;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.gioitinh = gioitinh;
        this.phucap = phucap;
        this.maPhong = maPhong;
        this.machucvu = machucvu;
    }
    public Nhansu(Nhansu a) {
        this.maNS = a.maNS;
        this.ho = a.ho;
        this.ten = a.ten;
        this.ngaySinh = a.ngaySinh;
        this.gioitinh = a.gioitinh;
        this.phucap = a.phucap;
        this.maPhong = a.maPhong;
        this.machucvu = a.machucvu;
    }
    // Getter và Setter
    public String getMaNS() { return maNS; }
    public void setMaNS(String maNS) { this.maNS = maNS; }

    public String getHo() { return ho; }
    public void setHo(String ho) { this.ho = ho; }

    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }

    public String getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(String ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getGioitinh() { return gioitinh; }
    public void setGioitinh(String gioitinh) { this.gioitinh = gioitinh; }

    public float getPhucap() { return phucap; }
    public void setPhucap(float phucap) { this.phucap = phucap; }

    public String getMaPhong() { return maPhong; }
    public void setMaPhong(String maPhong) { this.maPhong = maPhong; }

    public String getMachucvu() { return machucvu; }
    public void setMachucvu(String machucvu) { this.machucvu = machucvu; }
    //Nhập
    Scanner sc = new Scanner(System.in);
    public void nhap() {
    System.out.print("Nhập mã nhân sự: ");
    maNS = sc.nextLine();
    System.out.print("Nhập họ: ");
    ho = sc.nextLine();
    System.out.print("Nhập tên: ");
    ten = sc.nextLine();
    System.out.print("Nhập ngày sinh (dd/mm/yyyy): ");
    ngaySinh = sc.nextLine();
    System.out.print("Nhập giới tính: ");
    gioitinh = sc.nextLine();
    System.out.print("Nhập phụ cấp: ");
    phucap = Float.parseFloat(sc.nextLine());
    System.out.print("Nhập mã phòng (hoặc '0' để bỏ qua): ");
    maPhong = sc.nextLine();
    if (maPhong.equals("0")) {
        maPhong = "";
        System.out.println("Bo qua nhap ma phong.");
    } else {
        while(!DataCenter.dspb.tonTaiPhongBan(maPhong)){
            System.out.print("Phong ko ton tai! Vui long nhap lai ma phong (hoặc '0' để bỏ qua): ");
            maPhong=sc.nextLine();
            if (maPhong.equals("0")) {
                maPhong = "";
                System.out.println("!! Đã bỏ qua nhập mã phòng !!.");
                break;
            }
        }
    }
    System.out.print("Nhập mã chức vụ (hoặc '0' để bỏ qua): ");
    machucvu = sc.nextLine();
    if (machucvu.equals("0")) {
        machucvu = "";
        System.out.println("Bo qua nhap ma chuc vu.");
    } else {
        while(!DataCenter.dscv.tonTaiChucVu(machucvu)){
            System.out.print("Chuc vu ko ton tai! Vui long nhap lai Ma chuc vu (hoặc '0' để bỏ qua): ");
            machucvu=sc.nextLine();
            if (machucvu.equals("0")) {
                machucvu = "";
                System.out.println("!! Đã bỏ qua nhập mã chức vụ !!.");
                break;
            }
        }
    }
}
    //xuat
    public void xuat() {
        System.out.println("---------");
        System.out.println("Ma nhan su: " +maNS);
        System.out.println("Ho: " +ho);
        System.out.println("Ten: " +ten);
        System.out.println("Ngay sinh: " +ngaySinh);
        System.out.println("Gioi tinh: " +gioitinh);
        System.out.println("Phu cap: " +phucap);
        System.out.println("Ma phong: " +maPhong);
        System.out.println("Ma chuc vu: " +machucvu);
    }
    //toString
    @Override
    public String toString() {
        return maNS + "," + ho + "," + ten + "," + ngaySinh + "," + gioitinh + "," + phucap + "," + maPhong + "," + machucvu;
    }
}
