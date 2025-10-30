package PHONGBAN_DEAN;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Danhsachphongban {
    private PhongBan[] dsPhongBan;
    private int soPhongBan;
    public static Scanner sc = new Scanner(System.in);

    public Danhsachphongban(){
        dsPhongBan = new PhongBan[100];
        soPhongBan = 0;
        docTuFilePhongBan(); // Tự động đọc file khi khởi tạo
    }

    private PhongBan[] tangKichThuoc(PhongBan[] arr){
        PhongBan[] newArr = new PhongBan[arr.length*2];
        for(int i=0; i<arr.length; i++) newArr[i] = arr[i];
        return newArr;
    }

    public void themPhongBan(){
        if(soPhongBan>= dsPhongBan.length) dsPhongBan = tangKichThuoc(dsPhongBan);
        PhongBan pb =new PhongBan();
        pb.nhapPhongBan();
        boolean trung = false;
        for (int i = 0; i < soPhongBan; i++) {
            if (dsPhongBan[i].getMaPhong().equalsIgnoreCase(pb.getMaPhong())) {
                trung  = true;
                break;
            }
        }
        while (trung) {
            System.out.println("PHONG BAN VOI MA PHONG NAY DA TON TAI, NHAP LAI!");
            pb.nhapPhongBan();
            trung = false;
            for (int i = 0; i < soPhongBan; i++) {
                if (dsPhongBan[i].getMaPhong().equalsIgnoreCase(pb.getMaPhong())) {
                    trung  = true;
                    break;
                }
            }
        }
        dsPhongBan[soPhongBan++] = pb;
        ghiPhongBanVaoFile(pb);
    }

    public void xuatPhongBan(){
        System.out.println("===DANH SÁCH PHÒNG BAN===");
        for(int i=0;i<soPhongBan; i++){
            System.out.println((i+1) + ". " + dsPhongBan[i]);
        }
    }

    // Kiểm tra phòng ban có tồn tại không
    public boolean tonTaiPhongBan(String maPhong) {
        for (int i = 0; i < soPhongBan; i++) {
            if (dsPhongBan[i] != null && dsPhongBan[i].getMaPhong().equalsIgnoreCase(maPhong)) {
                return true;
            }
        }
        return false;
    }

    // Tìm phòng ban theo mã
    public PhongBan timPhongBan(String maPhong) {
        for (int i = 0; i < soPhongBan; i++) {
            if (dsPhongBan[i] != null && dsPhongBan[i].getMaPhong().equalsIgnoreCase(maPhong)) {
                return dsPhongBan[i];
            }
        }
        return null;
    }

    public void ghiPhongBanVaoFile(PhongBan pb){
        try(FileWriter fw = new FileWriter("data/phongban.txt", true)){
            fw.write(pb.getMaNS() + "," +
                    pb.getMaPhong() + ", " +
                    pb.getTenPhong() + "," +
                    pb.getNgayNC() + "\n");
        } catch(IOException e){
            System.out.println("lỗi ghi file: " +e.getMessage());
        }
    }

    public void docTuFilePhongBan(){
        try(BufferedReader br= new BufferedReader(new FileReader("data/phongban.txt"))){
            String line;
            while((line = br.readLine()) != null){
                if(line.trim().isEmpty()) continue;
                String[] arr = line.split(",", -1);
                for(int i=0;i<arr.length;i++) arr[i]=arr[i].trim();
                // định dạng mong đợi: maNS, maPhong, tenPhong, ngayNC
                String maNS =arr[0];
                String maPhong = arr[1];
                String tenPhong =arr[2];
                String ngayNC =arr[3];
                if(soPhongBan >= dsPhongBan.length) dsPhongBan = tangKichThuoc(dsPhongBan);
                dsPhongBan[soPhongBan++] = new PhongBan(maPhong, maNS, tenPhong, ngayNC);
            }
            System.out.println("Đã đọc " + soPhongBan + " phòng từ file data/phongban.txt");
        } catch(Exception e){
            System.out.println("Lỗi đọc file phongban: " + e.getMessage());
        }
    }

    public void suaPhongBan(){
        String maPhongBan=sc.nextLine();
        for(int i=0; i<soPhongBan; i++){
            if(dsPhongBan[i].getMaPhong().equalsIgnoreCase(maPhongBan)){
                System.out.println("nhập lại thông tin mới:");
                dsPhongBan[i].nhapPhongBan();
                System.out.println("sửa thành công");
                return;
            }
        }
        System.out.println("không tìm thấy phòng ban");
    }

    public void xoaPhongBan(){
        System.out.println("nhập phòng cần xóa");
        String maPhongBan = sc.nextLine();
        for(int i=0; i<soPhongBan; i++){
            if(dsPhongBan[i].getMaPhong().equalsIgnoreCase(maPhongBan)){
                for(int j=i; j< soPhongBan-1; j++){ 
                    dsPhongBan[j]=dsPhongBan[j+1];
                }
            soPhongBan--;
            System.out.println("đã xóa phòng mã: " +maPhongBan);
            return;
            }
        }
        System.out.println("khônng tìm thấy phòng ");
    }

    // Ghi toàn bộ danh sách phòng ban vào file
    public void ghiToanBoPhongBan(){
        try(FileWriter fw = new FileWriter("data/phongban.txt", false)){ // false = overwrite
            for(int i = 0; i < soPhongBan; i++){
                if(dsPhongBan[i] != null){
                    PhongBan pb = dsPhongBan[i];
                    fw.write(pb.getMaNS() + "," + 
                            pb.getMaPhong() + "," + 
                            pb.getTenPhong() + "," + 
                            pb.getNgayNC() + "\n");
                }
            }
            System.out.println("Da ghi " + soPhongBan + " phong ban vao file.");
        } catch(IOException e){
            System.out.println("Loi ghi file phong ban: " + e.getMessage());
        }
    }
}