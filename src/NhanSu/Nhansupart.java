package NhanSu;

import java.util.Scanner;

public class Nhansupart extends Nhansu {
    //thuoc tinh
    private float tienconggio;
    Scanner sc = new Scanner(System.in);
    //constructor
    public Nhansupart() {
        super();
        tienconggio = 0;
    }
    public Nhansupart(String maNS, String ho, String ten, String ngaySinh, String gioitinh, 
    float phucap, String maPhong, String machucvu, float tienconggio) {
        super();
        this.tienconggio = tienconggio;
    }
    public Nhansupart(Nhansupart a) {
        super();
        this.tienconggio = a.tienconggio;    
    }
    //get,set
    public float gettienconggio() {return tienconggio;}
    public void settienconggio(float tienconggio) {this.tienconggio=tienconggio;}
    //nhap
    @Override
    public void nhap() {
        super.nhap();
        System.out.print("Nhập tiền công giờ: ");
        tienconggio = Float.parseFloat(sc.nextLine());
    }

    @Override
    public String toString() {
        return super.toString() + "," + tienconggio;
    }
}
