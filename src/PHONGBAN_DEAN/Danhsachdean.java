package PHONGBAN_DEAN;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Danhsachdean{
    private DeAn[] dsDeAn;
    private int soDeAn;
    private Scanner sc = new Scanner(System.in);

    public Danhsachdean(){
        dsDeAn = new DeAn[100];
        soDeAn = 0;
    }

    private DeAn[] tangKichThuoc(DeAn[] arr){
        DeAn[] newArr= new DeAn[arr.length*2];
        for (int i=0; i<arr.length; i++) newArr[i] = arr[i];
        return newArr;
    }

    public void themDeAn(){
        if(soDeAn >= dsDeAn.length) dsDeAn = tangKichThuoc(dsDeAn);
        DeAn da = new DeAn();
        da.nhapDeAn();
        
        // Kiểm tra trùng mã đề án
        boolean trung = false;
        for (int i = 0; i < soDeAn; i++) {
            if (dsDeAn[i] != null && dsDeAn[i].getMaDA().equalsIgnoreCase(da.getMaDA())) {
                trung = true;
                break;
            }
        }
        
        while(trung) {
            System.out.println("Ma de an da ton tai! Vui long nhap lai.");
            da.nhapDeAn();
            
            // Kiểm tra lại
            trung = false;
            for (int i = 0; i < soDeAn; i++) {
                if (dsDeAn[i] != null && dsDeAn[i].getMaDA().equalsIgnoreCase(da.getMaDA())) {
                    trung = true;
                    break;
                }
            }
        }
        
        dsDeAn[soDeAn++] = da;
        ghiDeAnVaoFile(da);
        System.out.println("Da them de an thanh cong!");
    }

    public void xuatDeAn(){
        System.out.println("===DANH SÁCH ĐỀ ÁN===");
        for(int i=0; i<soDeAn; i++){
            System.out.println((i+1) + ". " +dsDeAn[i]);
        }
    }

    public void xoaDeAn(){
        System.out.println("nhập đề án cần xóa");
        String maDA = sc.nextLine();
        for(int i=0; i<soDeAn; i++){
            if(dsDeAn[i].getMaDA().equalsIgnoreCase(maDA)){
                for(int j=i; j<soDeAn-1; j++){
                    dsDeAn[j]=dsDeAn[j+1];
                }
                soDeAn--;
                System.out.println("đã xóa đề án: " +maDA);
                return;
            }
        }
        System.out.println("không tìm thấy đề án!");
    }

    // Kiểm tra đề án có tồn tại không
    public boolean tonTaiDeAn(String maDA) {
        for (int i = 0; i < soDeAn; i++) {
            if (dsDeAn[i] != null && dsDeAn[i].getMaDA().equalsIgnoreCase(maDA)) {
                return true;
            }
        }
        return false;
    }

    // Tìm đề án theo mã
    public DeAn timDeAn(String maDA) {
        for (int i = 0; i < soDeAn; i++) {
            if (dsDeAn[i] != null && dsDeAn[i].getMaDA().equalsIgnoreCase(maDA)) {
                return dsDeAn[i];
            }
        }
        return null;
    }

    public void ghiDeAnVaoFile(DeAn da){
        try(FileWriter fw = new FileWriter("data/dean.txt", true)){
            fw.write(da.getMaDA() + "," + 
                    da.getMaPhong() + "," + 
                    da.getTenDA() + "," +
                    da.getNgayBD() + "," +
                    da.getNgayKT() + "\n");
        } catch(IOException e){
            System.out.println("lỗi ghi file: " +e.getMessage());
        }
    }

    public void docTuFileDeAn(){
        try(BufferedReader br = new BufferedReader(new FileReader("data/dean.txt"))){
            String line;
            while((line = br.readLine()) !=null){
                if(line.trim().isEmpty()) continue;
                String[] arr=line.split(",", -1);
                String maDA=arr[0];
                String maPhong = arr[1];
                String tenDA=arr[2];
                String ngayBD=arr[3];
                String ngayKT=arr[4];
                if(soDeAn>=dsDeAn.length) dsDeAn = tangKichThuoc(dsDeAn);
                dsDeAn[soDeAn++]= new DeAn(maDA, maPhong, tenDA, ngayBD, ngayKT);
            }
            System.out.println("đã đọc" + soDeAn + "đề án từ file data/dean.txt");
        } catch(Exception e){
            System.out.println("lỗi đọc file dean: " +e.getMessage());
        }
    }

    // Ghi toàn bộ danh sách đề án vào file
    public void ghiToanBoDeAn(){
        try(FileWriter fw = new FileWriter("data/dean.txt", false)){ // false = overwrite
            for(int i = 0; i < soDeAn; i++){
                if(dsDeAn[i] != null){
                    DeAn da = dsDeAn[i];
                    fw.write(da.getMaDA() + "," + 
                            da.getMaPhong() + "," + 
                            da.getTenDA() + "," +
                            da.getNgayBD() + "," +
                            da.getNgayKT() + "\n");
                }
            }
            System.out.println("Da ghi " + soDeAn + " de an vao file.");
        } catch(IOException e){
            System.out.println("Loi ghi file de an: " + e.getMessage());
        }
    }
}