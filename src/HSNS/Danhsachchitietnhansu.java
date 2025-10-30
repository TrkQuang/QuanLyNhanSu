package HSNS;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Danhsachchitietnhansu {
    private static int indexCTNS = 0;
    private CHITIETNS CTNS[] = new CHITIETNS[100];
    private Scanner sc = new Scanner(System.in);
    
    // Constructor - tự động đọc file khi khởi tạo
    public Danhsachchitietnhansu() {
        docFile();
    }
    
    //Nhập chi tiết nhân sự
    public void nhapCTNS() {
        System.out.print("Nhap so luong chi tiet nhan su: ");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            CTNS[indexCTNS] = new CHITIETNS();
            System.out.println("\n--- Chi tiet nhan su thu " + (indexCTNS + 1) + " ---");
            CTNS[indexCTNS].nhap();
            indexCTNS++;
        }
    }
    //Xuất nhân sú
    public void xuatNS() {
        System.out.println("\n=== DANH SACH CHI TIET NHAN SU ===");
        for(int i=0;i<indexCTNS;i++){
            CTNS[i].xuat();
        }
    }

    //Thêm nhân sự
    public void themNS(){
        System.out.println("Nhap thong tin chi tiet nhan su can them:");
        CHITIETNS ctns = new CHITIETNS();
        ctns.nhap(); // Đã validate maNS tồn tại trong DanhSachNhanSu
        
        // Kiểm tra trùng lặp trong CTNS[]
        for(int i=0;i<indexCTNS;i++){
            if(CTNS[i].getmaNS().equals(ctns.getmaNS())){
                System.out.println("Ma nhan su nay da co chi tiet roi!");
                return;
            }
        }
        CTNS[indexCTNS++]=ctns;
        System.out.println("Da them chi tiet nhan su thanh cong!");
    }

     // Xóa nhân sự
    public void xoaNhanSu() {
        System.out.print("Nhap ma nhan su can xoa: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < indexCTNS; i++) {
            if (CTNS[i].getmaNS().equalsIgnoreCase(ma)) {
                for (int j = i; j < indexCTNS - 1; j++) {
                    CTNS[j] = CTNS[j + 1];
                }
                indexCTNS--;
                found = true;
                break;
            }
        }
        if (found){
            System.out.println("Da xoa chi tiet nhan su thanh cong!");
        } 
        else {
            System.out.println("Khong tim thay ma chi tiet nhan su can xoa!");
        }
    }

    // Tìm nhân sự theo tên
    public void timTheoTen() {
        System.out.print("Nhap ten hoac ma chi tiet nhan su can tim: ");
        String ten = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < indexCTNS; i++) {
            if (CTNS[i].getTrinhdo().equalsIgnoreCase(ten) || 
                CTNS[i].getmaNS().equalsIgnoreCase(ten)) {
                CTNS[i].xuat();
                found = true;
            }
        }
        if (!found){
            System.out.println("Khong tim thay chi tiet nhan su: " + ten);
        }
    }

    //Sửa nhân sự
    public void suaNhanSu() {
        System.out.print("Nhap ma chi tiet nhan su can sua: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < indexCTNS; i++) {
            if (CTNS[i].getmaNS().equalsIgnoreCase(ma)) {
                System.out.println("Thong tin cu:");
                CTNS[i].xuat();
                System.out.println("Nhap thong tin moi: ");
                CTNS[i].nhap();
                found = true;
                break;
            }
        }
        if (found){
            System.out.println("Da cap nhat thanh cong!");
        }
        else{
            System.out.println("Khong tim thay chi tiet nhan su can sua!");
        }
    }

    //Hàm ghi file
    public void ghiFile() throws IOException { 
        // Ghi file Chi tiết Nhân sự
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("data/quanlyhsnhansu.txt"));
        for (int i = 0; i < indexCTNS; i++) { 
            outputStream.writeUTF(CTNS[i].getmaNS());
            outputStream.writeUTF(CTNS[i].getSdt());
            outputStream.writeUTF(CTNS[i].getDiachi());
            outputStream.writeUTF(CTNS[i].getEmail());
            outputStream.writeUTF(CTNS[i].getTrinhdo());
        }
        outputStream.close();
    }

    //Hàm đọc file
    public void docFile() {
        // Đọc file Chi tiết Nhân sự
        int i = 0;
        try {
            DataInputStream inputStream = new DataInputStream(new FileInputStream("data/quanlyhsnhansu.txt"));
            try {
                while (true) { 
                    CTNS[i] = new CHITIETNS();
                    // Đọc dữ liệu nhân sự
                    CTNS[i].setmaNS(inputStream.readUTF());
                    CTNS[i].setSdt(inputStream.readUTF());
                    CTNS[i].setDiachi(inputStream.readUTF());
                    CTNS[i].setEmail(inputStream.readUTF());
                    CTNS[i].setTrinhdo(inputStream.readUTF());
                    i++;
                } 
            } 
            catch (EOFException e) {}
            finally {
                indexCTNS = i;
                inputStream.close();
            }
        }       
        catch (IOException e) {
            System.out.println("Loi doc file Chi tiet NS: " + e.getMessage());
        }
    }
}
