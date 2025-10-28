package HSNS;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class QLHSNS {
    private static int indexCTNS=0;
    private static int indexCV=0;
    private CHITIETNS CTNS[] = new CHITIETNS[100];
    private CHUCVU CV[] = new CHUCVU[100];
    private Scanner sc = new Scanner(System.in);

    // Lấy số lượng chi tiết nhân sự
    public int getSoLuongCTNS() {
        return indexCTNS;
    }

    // Lấy số lượng chức vụ
    public int getSoLuongCV() {
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

    //Nhập chức vụ
    public void nhapCV() {
        System.out.print("Nhap so luong chuc vu: ");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            CV[indexCV] = new CHUCVU();
            System.out.println("\n--- Chuc vu " + (indexCV + 1) + " ---");
            CV[indexCV].nhap();
            indexCV++;
        }
    }
    //Xuất nhân sú
    public void xuatNS() {
        System.out.println("\n=== DANH SACH CHI TIET NHAN SU ===");
        for(int i=0;i<indexCTNS;i++){
            CTNS[i].xuat();
        }
    }
    
    //Xuất chức vụ
    public void xuatCV(){
        System.out.println("\n=== DANH SACH CHUC VU ===");
        for(int i=0;i<indexCV;i++){
            CV[i].xuat();
        }
    }

    //Xuất thân nhân
    public void xuatTN(){
        System.out.println("\n=== DANH SACH THAN NHAN ===");
        for(int i=0;i<indexCTNS;i++){
            System.out.println("Chi tiet nhan su: " + CTNS[i].getmaNS());
            THANNHAN[] dsTN = CTNS[i].getdsThanNhan();
            int soLuong = CTNS[i].getSoLuongThanNhan();
            if (soLuong > 0) {
                for(int j = 0; j < soLuong; j++){
                    System.out.print("  [" + (j+1) + "] ");
                    dsTN[j].xuat();
                }
            } else {
                System.out.println("  Chua co thong tin than nhan!");
            }
            System.out.println();
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

    //Thêm thân nhân cho nhân sự
    public void themTN(){
        System.out.print("Nhap ma nhan su can them than nhan: ");
        String ma=sc.nextLine();
        int n=-1;
        for(int i=0;i<indexCTNS;i++){
            if(CTNS[i].getmaNS().equalsIgnoreCase(ma)){
                n=i;
                break;
            }
        }
        if(n==-1){
            System.out.println("Khong tim thay chi tiet nhan su co ma: " + ma);
            return;
        }
        System.out.print("Nhap so luong than nhan can them: ");
        int soLuong = Integer.parseInt(sc.nextLine());
        for(int i = 0; i < soLuong; i++){
            System.out.println("\n--- Than nhan thu " + (i+1) + " ---");
            THANNHAN tn=new THANNHAN();
            tn.nhap();
            CTNS[n].themThanNhan(tn);
        }
        System.out.println("Da them " + soLuong + " than nhan cho chi tiet nhan su " + ma);
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

    //Xoá thân nhân
    public void xoaThanNhan(){
        System.out.print("Nhap ma than nhan can xoa: ");
        String ma=sc.nextLine();
        boolean found=false;
        for(int i=0;i<indexCTNS;i++){
            THANNHAN[] dsTN = CTNS[i].getdsThanNhan();
            int soLuong = CTNS[i].getSoLuongThanNhan();
            for(int j = 0; j < soLuong; j++){
                if(dsTN[j]!=null && dsTN[j].getmaTN().equalsIgnoreCase(ma)){
                    // Dịch các phần tử phía sau lên
                    for(int k = j; k < soLuong - 1; k++){
                        dsTN[k] = dsTN[k+1];
                    }
                    dsTN[soLuong-1] = null;
                    CTNS[i].setSoLuongThanNhan(soLuong - 1);
                    found=true;
                    break;
                }
            }
            if(found) break;
        }
        if(found){
            System.out.println("Da xoa than nhan thanh cong!");
        }
        else{
            System.out.println("Khong tim thay than nhan can xoa!");
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

    //Sửa chức vụ
    public void suaChucVu() {
        System.out.print("Nhap ma chuc vu can sua: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < indexCV; i++) {
            if (CV[i].getmaCV().equalsIgnoreCase(ma)) {
                System.out.println("Thong tin cu:");
                CV[i].xuat();
                System.out.println("Nhap thong tin moi: ");
                CV[i].nhap();
                found = true;
                break;
            }
        }
        if (found){
            System.out.println("Da cap nhat thanh cong!");
        } 
        else{
            System.out.println("Khong tim thay chuc vu can sua!");
        }
    }

    //Sửa thân nhân
    public void suaThanNhan() {
        System.out.print("Nhap ma than nhan can sua: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < indexCTNS; i++) {
            THANNHAN[] dsTN = CTNS[i].getdsThanNhan();
            int soLuong = CTNS[i].getSoLuongThanNhan();
            for(int j = 0; j < soLuong; j++){
                if (dsTN[j]!=null && dsTN[j].getmaTN().equalsIgnoreCase(ma)) {
                    System.out.println("Thong tin cu:");
                    dsTN[j].xuat();
                    System.out.println("Nhap thong tin moi: ");
                    dsTN[j].nhap();
                    found = true;
                    break;
                }
            }
            if(found) break;
        }
        if (found){
            System.out.println("Da cap nhat thanh cong!");
        }
        else{
            System.out.println("Khong tim thay than nhan can sua!");
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
            
            // Ghi danh sách thân nhân
            THANNHAN[] dsTN = CTNS[i].getdsThanNhan();
            int soLuong = CTNS[i].getSoLuongThanNhan();
            outputStream.writeInt(soLuong);
            
            for(int j = 0; j < soLuong; j++){
                outputStream.writeUTF(dsTN[j].getmaTN());
                outputStream.writeUTF(dsTN[j].getHoTN());
                outputStream.writeUTF(dsTN[j].gettenTN());
                outputStream.writeUTF(dsTN[j].getGioiTinh());
                outputStream.writeUTF(dsTN[j].getNgaySinh());
                outputStream.writeUTF(dsTN[j].getQuanHe());
            }
        }
        outputStream.close();
        
        // Ghi file Chức vụ riêng
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
                    
                    // Đọc danh sách thân nhân
                    int soLuong = inputStream.readInt();
                    
                    for(int j = 0; j < soLuong; j++){
                        THANNHAN tn = new THANNHAN();
                        tn.setmaTN(inputStream.readUTF());
                        tn.setHoTN(inputStream.readUTF());
                        tn.settenTN(inputStream.readUTF());
                        tn.setGioiTinh(inputStream.readUTF());
                        tn.setNgaySinh(inputStream.readUTF());
                        tn.setQuanHe(inputStream.readUTF());
                        CTNS[i].themThanNhan(tn);
                    }
                    
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
        
        // Đọc file Chức vụ riêng
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

    // Menu chính quản lý hồ sơ nhân sự
    public void menu() {
        int choice;
        do {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║    QUẢN LÝ HỒ SƠ NHÂN SỰ            ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ 1. Quản lý Chi tiết Nhân sự           ║");
            System.out.println("║ 2. Quản lý Chức vụ                    ║");
            System.out.println("║ 3. Quản lý Thân nhân                  ║");
            System.out.println("║ 4. Tìm kiếm Chi tiết Nhân sự          ║");
            System.out.println("║ 5. Lưu/Đọc File                       ║");
            System.out.println("║ 0. Quay lại                           ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print("➤ Chọn chức năng: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    menuChiTietNS();
                    break;
                case 2:
                    menuChucVu();
                    break;
                case 3:
                    menuThanNhan();
                    break;
                case 4:
                    timTheoTen();
                    break;
                case 5:
                    menuFile();
                    break;
                case 0:
                    System.out.println("Quay lai menu chinh...");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 0);
    }

    // Menu quản lý chi tiết nhân sự
    private void menuChiTietNS() {
        int choice;
        do {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║    QUẢN LÝ CHI TIẾT NHÂN SỰ         ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ 1. Nhập danh sách Chi tiết NS         ║");
            System.out.println("║ 2. Thêm Chi tiết Nhân sự              ║");
            System.out.println("║ 3. Xuất danh sách Chi tiết NS         ║");
            System.out.println("║ 4. Sửa Chi tiết Nhân sự               ║");
            System.out.println("║ 5. Xóa Chi tiết Nhân sự               ║");
            System.out.println("║ 0. Quay lại                           ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print("➤ Chọn chức năng: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    nhapCTNS();
                    break;
                case 2:
                    themNS();
                    break;
                case 3:
                    xuatNS();
                    break;
                case 4:
                    suaNhanSu();
                    break;
                case 5:
                    xoaNhanSu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    // Menu quản lý chức vụ
    private void menuChucVu() {
        int choice;
        do {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║    QUẢN LÝ CHỨC VỤ                  ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ 1. Nhập danh sách Chức vụ             ║");
            System.out.println("║ 2. Thêm Chức vụ                       ║");
            System.out.println("║ 3. Xuất danh sách Chức vụ             ║");
            System.out.println("║ 4. Sửa Chức vụ                        ║");
            System.out.println("║ 5. Xóa Chức vụ                        ║");
            System.out.println("║ 0. Quay lại                           ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print("➤ Chọn chức năng: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    nhapCV();
                    break;
                case 2:
                    themChucVu();
                    break;
                case 3:
                    xuatCV();
                    break;
                case 4:
                    suaChucVu();
                    break;
                case 5:
                    xoaChucVu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    // Menu quản lý thân nhân
    private void menuThanNhan() {
        int choice;
        do {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║    QUẢN LÝ THÂN NHÂN                ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ 1. Thêm Thân nhân cho Chi tiết NS     ║");
            System.out.println("║ 2. Xuất danh sách Thân nhân           ║");
            System.out.println("║ 3. Sửa Thân nhân                      ║");
            System.out.println("║ 4. Xóa Thân nhân                      ║");
            System.out.println("║ 0. Quay lại                           ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print("➤ Chọn chức năng: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    themTN();
                    break;
                case 2:
                    xuatTN();
                    break;
                case 3:
                    suaThanNhan();
                    break;
                case 4:
                    xoaThanNhan();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    // Menu quản lý file
    private void menuFile() {
        int choice;
        do {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║    QUẢN LÝ FILE                     ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ 1. Lưu dữ liệu vào File               ║");
            System.out.println("║ 2. Đọc dữ liệu từ File                ║");
            System.out.println("║ 0. Quay lại                           ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print("➤ Chọn chức năng: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    try {
                        ghiFile();
                        System.out.println("Da luu du lieu thanh cong!");
                    } catch (IOException e) {
                        System.out.println("Loi khi ghi file: " + e.getMessage());
                    }
                    break;
                case 2:
                    docFile();
                    System.out.println("Da doc du lieu thanh cong!");
                    System.out.println("So luong Chi tiet NS: " + getSoLuongCTNS());
                    System.out.println("So luong Chuc vu: " + getSoLuongCV());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }
}




   

    
        