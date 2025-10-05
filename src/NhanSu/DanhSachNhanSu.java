package NhanSu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DanhSachNhanSu {
    //thuoc tinh
    private Nhansu ds[];
    private int n;
    Scanner sc = new Scanner(System.in);
    //nhap
    public void nhap() {
        System.out.print("So luong sinh vien muon nhap: ");
        n = sc.nextInt();
        ds = new Nhansu[n];
        for (int i = 0; i<n; i++) {
            int chon=0;
            System.out.print("Chon loai nhan su(1: Fulltime, 2: Parttime): ");
            chon = sc.nextInt();
            sc.nextLine();
            while (chon != 1 && chon != 2) {
                System.out.print("Vui long nhap lai(1: Fulltime, 2: Parttime): ");
                chon = sc.nextInt();
            }
            if(chon == 1) {
                ds[i] = new Nhansufull();
                ds[i].nhap();
            }
            if(chon == 2) {
                ds[i] = new Nhansupart();
                ds[i].nhap();
            }
            ghiVaoFile(ds[i]);
        }
        System.out.println("Da them thanh cong!");
    }
    //ghi lên text
    public void ghiVaoFile(Nhansu a) {
        try (FileWriter fw = new FileWriter("data/Nhansu.txt", true)) { // true: ghi tiếp vào cuối file
            fw.write(a.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Lỗi ghi file: " + e.getMessage());
        }
    }
    //mở rộng mảng 1 phần tử
    public void morong() {
        Nhansu temp[] = new Nhansu[n+1];
        for(int i = 0; i<n; i++) {
            temp[i] = ds[i];
        }
        ds = temp;
        n++;
    }
    //thêm một nhân sự mới vào cuối list
    public void themNS() {
        morong();
        int chon=0;
        System.out.print("Chon loai nhan su(1: Fulltime, 2: Parttime): ");
        chon = sc.nextInt();
        sc.nextLine();
        while (chon != 1 && chon != 2) {
            System.out.print("Vui long nhap lai(1: Fulltime, 2: Parttime): ");
            chon = sc.nextInt();
        }
        if(chon == 1) {
            ds[n-1] = new Nhansufull();
            }
        if(chon == 2) {
            ds[n-1] = new Nhansupart();
        }
        ds[n-1].nhap();
        ghiVaoFile(ds[n-1]);
        System.out.println("Da them!");
    }
    //xuat ds nhan su
    public void xuat() {
        System.out.println("---Danh Sach Nhan Su---");
        for(Nhansu a : ds) {
            a.xuat();
        } 
    }
    //xoa nhan su theo mans
    public void xoaNS() {
        System.out.print("Nhap vao maNS cua nhan su can xoa: ");
        String maNS = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (ds[i] != null && ds[i].getMaNS().equals(maNS)) {
                Nhansu[] temp = new Nhansu[n - 1];
                int index = 0;
                for (int j = 0; j < n; j++) {
                    if (j == i) continue; //bỏ qua
                    temp[index++] = ds[j];
                }
                ds = temp;
                n--;
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println("Da xoa nhan su co ma: " + maNS);
        } else {
            System.out.println("Khong tim thay nhan su co ma: " + maNS);
        }
    }
    //tim kiem nhan su theo mans
    public Nhansu timtheomaNS() {
        System.out.print("Nhap vao maNS cua nhan su can tim: ");
        String maNS = sc.nextLine();
        for(Nhansu a : ds) {
            if(a.getMaNS().equals(maNS)) {
                System.out.println("Da tim thay!");
                return a;
            }
        }
        System.out.println("Khong co nhan su nao ton tai voi maNS: "+ maNS);
        return null;
    }

    // tim kiem theo maNS (khong yeu cau nhap tu ham) - tra ve doi tuong hoac null
    public Nhansu timtheomaNS(String maNS) {
        if (maNS == null) return null;
        for (int i = 0; i < n; i++) {
            if (ds[i] != null && ds[i].getMaNS().equals(maNS)) {
                return ds[i];
            }
        }
        return null;
    }
}
