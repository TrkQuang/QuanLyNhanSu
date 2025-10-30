package NhanSu;

import java.util.Scanner;

public class Nhansufull extends Nhansu {
    //thuộc tính
    private float luongcb;
    private float phucapthamnien;
    Scanner sc = new Scanner(System.in);
    //constructor
    public Nhansufull() {
        super();
        luongcb = 0;
        phucapthamnien = 0;
    }
    public Nhansufull(String maNS, String ho, String ten, String ngaySinh, String gioitinh, 
    float phucap, String maPhong, String machucvu, float luongcb, float phucapthamnien) {
        super();
        this.luongcb = luongcb;
        this.phucapthamnien = phucapthamnien;
    }
    public Nhansufull(Nhansufull a) {
        super();
        this.luongcb = a.luongcb;
        this.phucapthamnien = a.phucapthamnien;        
    }
    //get, set
    public float getluongcb() {return luongcb;}
    public float phucapthamnien() {return phucapthamnien;}
    public void setluongcb(float luongcb) {this.luongcb = luongcb;}
    public void setphucapthamnien(float phucapthamnien) {this.phucapthamnien = phucapthamnien;}
    //nhap
    @Override
    public void nhap() {
        super.nhap();
        System.out.print("Nhập tiền lương cơ bản: ");
        luongcb = Float.parseFloat(sc.nextLine());
        System.out.print("Nhập phụ cấp thâm niên: ");
        phucapthamnien = Float.parseFloat(sc.nextLine());
    }
    //xuat
    @Override
    public void xuat() {
        super.xuat();
        System.out.println("Luong co ban: " + luongcb);
        System.out.println("Phu cap tham nien: " + phucapthamnien);
    }
    @Override
    public String toString() {
        return super.toString() + "," + luongcb + "," + phucapthamnien;
    }
}
