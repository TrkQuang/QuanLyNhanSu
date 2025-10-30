package HSNS;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Danhsachchucvu {
    private static int indexCV=0;
    private CHUCVU CV[] = new CHUCVU[100];
    private Scanner sc = new Scanner(System.in);

    // Constructor - tự động đọc file khi khởi tạo
    public Danhsachchucvu() {
        docFile();
    }

    // Lấy số lượng chi tiết nhân sự
    public int getSoLuongChucvu() {
        return indexCV;
    }

    // Kiểm tra chức vụ có tồn tại không
    public boolean tonTaiChucVu(String maCV) {
        for (int i = 0; i < indexCV; i++) {
            if (CV[i].getmaCV().equalsIgnoreCase(maCV)) {
                return true;
            }
        }
        return false;
    }

    // Tìm chức vụ theo mã
    public CHUCVU timChucVu(String maCV) {
        for (int i = 0; i < indexCV; i++) {
            if (CV[i].getmaCV().equalsIgnoreCase(maCV)) {
                return CV[i];
            }
        }
        return null;
    }

    //Nhập chức vụ
    public void nhapCV() {
        System.out.print("Nhap so luong chuc vu: ");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            CV[indexCV] = new CHUCVU();
            System.out.println("\n--- Chuc vu " + (indexCV + 1) + " ---");
            
            // Lặp lại nhập nếu trùng mã
            while (true) {
                CV[indexCV].nhap();
                boolean trung = false;

                for (int j = 0; j < indexCV; j++) {
                    if (CV[j].getmaCV().equalsIgnoreCase(CV[indexCV].getmaCV())) {
                        System.out.println("Ma chuc vu nay da ton tai! Vui long nhap lai.");
                        trung = true;
                        break;
                    }
                }

                if (!trung) break; // nếu không trùng thì thoát vòng lặp nhập lại
            }

            indexCV++;
        }
    }

    //Xuất chức vụ
    public void xuatCV(){
        System.out.println("\n=== DANH SACH CHUC VU ===");
        for(int i=0;i<indexCV;i++){
            CV[i].xuat();
        }
    }

    //Thêm chức vụ
    public void themChucVu(){
        System.out.println("Nhap thong tin chuc vu can them:");
        CHUCVU cv = new CHUCVU();
        cv.nhap();
        for(int i=0;i<indexCV;i++){
            if(CV[i].getmaCV().equals(cv.getmaCV())){
                System.out.println("Ma chuc vu da ton tai!");
                return;
            }
        }
        CV[indexCV++]=cv;
        System.out.println("Da them chuc vu thanh cong!");
    }

    // Xóa chức vụ
    public void xoaChucVu(){
        System.out.print("Nhap ma chuc vu can xoa: ");
        String ma=sc.nextLine();
        boolean found=false;
        for(int i=0;i<indexCV;i++){
            if(CV[i].getmaCV().equalsIgnoreCase(ma)){
                for(int j=0;j<indexCV-1;j++){
                    CV[j]=CV[j+1];
                }
                indexCV--;
                found=true;
                break;
            }
        }
        if(found){
            System.out.println("Da xoa chuc vu thanh cong!");
        }
        else{
            System.out.println("Khong tim thay chuc vu can xoa!");
        }
    }

   //Hàm ghi file
    public void ghiFile() throws IOException { 
        // Ghi file Chức vụ
        DataOutputStream outputStreamCV = new DataOutputStream(new FileOutputStream("data/chucvu.txt"));
        for (int i = 0; i < indexCV; i++) {
            outputStreamCV.writeUTF(CV[i].getmaCV());
            outputStreamCV.writeUTF(CV[i].getTenCV());
            outputStreamCV.writeDouble(CV[i].getHeSoLuong());
        }
        outputStreamCV.close();
    }


    //Hàm đọc file
    public void docFile() {
        // Đọc file Chức vụ
        int j = 0;
        try {
            DataInputStream inputStreamCV = new DataInputStream(new FileInputStream("data/chucvu.txt"));
            try {
                while (true) {
                    CV[j] = new CHUCVU();
                    CV[j].setmaCV(inputStreamCV.readUTF());
                    CV[j].setTenCV(inputStreamCV.readUTF());
                    CV[j].setHeSoLuong(inputStreamCV.readDouble());
                    j++;
                }
            }
            catch (EOFException e) {}
            finally {
                indexCV = j;
                inputStreamCV.close();
            }
        }
        catch (IOException e) {
            System.out.println("Loi doc file Chuc vu: " + e.getMessage());
        }
    }
}
