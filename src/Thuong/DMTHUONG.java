package Thuong;
import java.util.Scanner;

public class DMTHUONG {
    private String maThuong;
    private String tenThuong;
    private double soTienMacDinh;

    public DMTHUONG(){
        maThuong = "";
        tenThuong = "";
        soTienMacDinh = 0.0;
    }

    public DMTHUONG(String maThuong, String tenThuong, double soTienMacDinh){
        this.maThuong = maThuong;
        this.tenThuong = tenThuong;
        this.soTienMacDinh = soTienMacDinh;
    }

    public DMTHUONG(DMTHUONG DMT){
        maThuong = DMT.maThuong;
        tenThuong = DMT.tenThuong;
        soTienMacDinh = DMT.soTienMacDinh;
    }

    public String getMaThuong() { return maThuong; }
    public String getTenThuong() { return tenThuong; }
    public double getSoTienMacDinh() { return soTienMacDinh; }

    public void setMaThuong(String maThuong) { this.maThuong = maThuong; }
    public void setTenThuong(String tenThuong) { this.tenThuong = tenThuong; }
    public void setSoTienMacDinh(double soTienMacDinh) { this.soTienMacDinh = soTienMacDinh; }

    Scanner sc = new Scanner(System.in);
    public void nhap(){
        System.out.print("Nhap ma thuong: ");
        maThuong = sc.nextLine();
        System.out.print("Nhap ten thuong: ");
        tenThuong = sc.nextLine();
        System.out.print("Nhap so tien mac dinh: ");
        soTienMacDinh = sc.nextDouble();
    }

    public void xuat(){
        System.out.println("Ma thuong: " +maThuong+ ", ten thuong: " +tenThuong+ ", so tien mac dinh: " +soTienMacDinh);
    }
}
