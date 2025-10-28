package PHONGBAN;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeAn {
    private String maDA;
    private String maPhong;
    private String tenDA;
    private String ngayBD;
    private String ngayKT;

    public DeAn(){
        this.maDA = "";
        this.maPhong = "";
        this.tenDA = "";
        this.ngayBD= "";
        this.ngayKT="";
    }

    public DeAn(String maDA, String maPhong, String tenDA, String ngayBD, String ngayKT){
        this.maDA=maDA;
        this.maPhong = maPhong;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.tenDA = tenDA;
    }
    public DeAn(DeAn da){
        this.maDA = da.maDA;
        this.maPhong = da.maPhong;
        this.tenDA = da.tenDA;
        this.ngayBD = da.ngayBD;
        this.ngayKT = da.ngayKT;
    }
    public String getMaDA(){
        return maDA;
    }
    public String getMaPhong(){
        return maPhong;
    }
    public String getTenDA(){
        return tenDA;
    }
    public String getNgayBD(){
        return ngayBD;
    }
    public String getNgayKT(){
        return ngayKT;
    }
    public LocalDate getNgayBDAsLocalDate(){
        if (ngayBD == null || ngayBD.trim().isEmpty()) return null;
        try{
            return LocalDate.parse(ngayBD);
        } catch (DateTimeParseException e){
            throw new RuntimeException("Lỗi định dạng ngayBD: " + ngayBD);
        }
    }

    public LocalDate getNgayKTAsLocalDate(){
        if (ngayKT == null || ngayKT.trim().isEmpty()) return null;
        try{
            return LocalDate.parse(ngayKT);
        } catch (DateTimeParseException e){
            throw new RuntimeException("Lỗi định dạng ngayKT: " + ngayKT);
        }
    }
    public void setMaDA(String maDA){
        this.maDA = maDA;
    }
    public void setMaPhong(String maPhong){
        this.maPhong = maPhong;
    }
    public void setTenDA(String tenDA){
        this.tenDA = tenDA;
    }
    public void setNgayBD(String ngayBD){
        this.ngayBD= ngayBD;
    }
    public void setNgayKT(String ngayKT){
        this.ngayKT = ngayKT;
    }
    Scanner sc= new Scanner(System.in);

    public void nhapDeAn(){
        System.out.print("Mã đề án: ");
        this.maDA = sc.nextLine().trim();
        
        // Nhập và kiểm tra mã phòng ban
        System.out.print("Mã phòng (hoặc '0' để hủy): ");
        this.maPhong = sc.nextLine().trim();
        if (this.maPhong.equals("0")) {
            System.out.println("Đã Hủy nhập đề án.");
            return;
        }
        
        while(!Center.DataCenter.qlPhongBan.tonTaiPhongBan(this.maPhong)) {
            System.out.println("Mã phòng ban không tồn tại trong hệ thống!");
            System.out.print("Nhập lại mã phòng (hoặc '0' để hủy): ");
            this.maPhong = sc.nextLine().trim();
            if (this.maPhong.equals("0")) {
                System.out.println("Đã Hủy nhập đề án.");
                return;
            }
        }
        System.out.println("Mã phòng ban hợp lệ!");
        
        System.out.print("Tên đề án: ");
        this.tenDA = sc.nextLine().trim();
        System.out.print("Ngày BD (yyyy-MM-dd): ");
        this.ngayBD = sc.nextLine().trim();
        System.out.print("Ngày KT (yyyy-MM-dd): ");
        this.ngayKT = sc.nextLine().trim();
    }

    public void xuatDeAn(){
        System.out.println("DeAn{maDA='" + maDA + "', maPhong='" + maPhong + "', tenDA='" + tenDA + "', ngayBD='" + ngayBD + "', ngayKT='" + ngayKT + "'}");
    }
    public String toString(){
        return "DeAn" +
                "maDA='" + maDA + '\'' +
                ", maPhong='" + maPhong + '\'' +
                ", tenDA='" + tenDA + '\'' +
                ",ngayBD='" + ngayBD + '\'' +
                ", ngayKT='" + ngayKT + '\'' +
                '}';
    }

}
