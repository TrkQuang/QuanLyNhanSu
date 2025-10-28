package PHONGBAN;

import java.util.Scanner;

import Center.DataCenter;

public class PhongBan {
    private String maPhong;
    private String tenPhong;
    private String maNS;
    private String ngayNC;

    public PhongBan(){
        this.maNS="";
        this.maPhong="";
        this.ngayNC="";
        this.tenPhong="";
    }
    public PhongBan(String maPhong, String maNS, String tenPhong, String ngayNC){
        this.maNS=maNS;
        this.maPhong=maPhong;
        this.ngayNC=ngayNC;
        this.tenPhong=tenPhong;
    }
    public PhongBan(PhongBan pb){
        this.maNS=pb.maNS;
        this.maPhong=pb.maPhong;
        this.ngayNC=pb.ngayNC;
        this.tenPhong=pb.tenPhong;
    }
    public String getMaPhong(){return maPhong;}
    public String getMaNS(){return maNS;}
    public String getTenPhong(){return tenPhong;}
    public String getNgayNC(){return ngayNC;}
    public void setMaPhong(String maPhong){this.maPhong=maPhong;}
    public void setTenPhong(String tenPhong){this.tenPhong=tenPhong;}
    public void setMaNS(String maNS){this.maNS=maNS;}
    public void setNgayNC(String ngayNC){this.ngayNC=ngayNC;}

    Scanner sc= new Scanner(System.in);
    public void nhapPhongBan(){
        System.out.print("nhập mã phòng:");
        maPhong=sc.nextLine();
        System.out.print("nhập tên phòng:");
        tenPhong=sc.nextLine();
        System.out.print("Nhap ma NS (hoặc '0' để hủy): ");
        maNS=sc.nextLine();
        if (maNS.equals("0")) {
            System.out.println("Hủy nhập chi tiết nhân sự.");
            return;
        }
        while(!DataCenter.dsNhanSu.tonTaiNhanSu(maNS)){
            System.out.print("Nhan su ko ton tai! Vui long nhap lai MaNS (hoặc '0' để hủy): ");
            maNS=sc.nextLine();
            if (maNS.equals("0")) {
                System.out.println("Hủy nhập chi tiết nhân sự.");
                return;
            }
        }
        System.out.print("nhập ngày nhận chức ( Trưởng phòng ): ");
        ngayNC=sc.nextLine();
    }

    public void xuatPhongBan(){
        System.out.println("PhongBan{maPhong='" + maPhong + "', tenPhong='" + tenPhong + "', maNS='" + maNS + "', ngayNC='" + ngayNC + "'}");
    }

    public String toString() {
        return "PhongBan{" +
                "maPhong='" + maPhong + '\'' +
                ", tenPhong='" + tenPhong + '\'' +
                ", truongPhong='" + maNS + '\'' +
                ", ngayNhanChuc='" + ngayNC + '\'' +
                '}';
    }
    
}
