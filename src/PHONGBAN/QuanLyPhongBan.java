package PHONGBAN;

import java.util.Scanner;
import java.io.*;

public class QuanLyPhongBan {
    private PhongBan[] dsPhongBan;
    private DeAn[] dsDeAn;
    private PhanCong[] dsPhanCong;
    private int soDeAn, soPhanCong, soPhongBan;
    private Scanner sc = new Scanner(System.in);

    public QuanLyPhongBan(){
        dsPhongBan = new PhongBan[100];
        dsDeAn = new DeAn[100];
        dsPhanCong = new PhanCong[100];
        soDeAn = 0;
        soPhanCong = 0;
        soPhongBan = 0;
    }

    private PhongBan[] tangKichThuoc(PhongBan[] arr){
        PhongBan[] newArr = new PhongBan[arr.length*2];
        for(int i=0; i<arr.length; i++) newArr[i] = arr[i];
        return newArr;
    }

    private DeAn[] tangKichThuoc(DeAn[] arr){
        DeAn[] newArr= new DeAn[arr.length*2];
        for (int i=0; i<arr.length; i++) newArr[i] = arr[i];
        return newArr;
    }

    private PhanCong[] tangKichThuoc(PhanCong[] arr){
        PhanCong[] newArr= new PhanCong[arr.length*2];
        for(int i=0; i<arr.length; i++) newArr[i] = arr[i];
        return newArr;
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

    /**
     * API: Tính tổng thưởng phân công của nhân sự trong tháng/năm
     * @param maNS Mã nhân sự
     * @param month Tháng (1-12)
     * @param year Năm
     * @return Tổng tiền thưởng từ các dự án hoàn thành trong tháng đó
     */
    public double tinhTongThuongPhanCongTheoThang(String maNS, int month, int year) {
        double tongThuong = 0.0;
        
        for (int i = 0; i < soPhanCong; i++) {
            if (dsPhanCong[i] == null) continue;
            
            // Kiểm tra mã nhân sự
            if (!dsPhanCong[i].getMaNS().equalsIgnoreCase(maNS)) continue;
            
            // Kiểm tra ngày hoàn thành có thuộc tháng/năm này không
            String ngayHoanThanh = dsPhanCong[i].getNgayHoanThanh();
            if (ngayHoanThanh == null || ngayHoanThanh.trim().isEmpty()) continue;
            
            try {
                String[] parts = ngayHoanThanh.split("-");
                if (parts.length < 3) continue;
                
                int yearHT = Integer.parseInt(parts[0]);
                int monthHT = Integer.parseInt(parts[1]);
                
                // Nếu trùng tháng/năm thì cộng thưởng
                if (yearHT == year && monthHT == month) {
                    String tienThuongStr = dsPhanCong[i].getTienThuong();
                    if (tienThuongStr != null && !tienThuongStr.trim().isEmpty()) {
                        try {
                            double tienThuong = Double.parseDouble(tienThuongStr);
                            tongThuong += tienThuong;
                        } catch (NumberFormatException e) {
                            // Bỏ qua nếu không parse được
                        }
                    }
                }
            } catch (Exception e) {
                // Bỏ qua nếu có lỗi parse date
            }
        }
        
        return tongThuong;
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
    public void ghiPhanCongVaoFile(PhanCong pc){
        try(FileWriter fw = new FileWriter("data/phancong.txt", true)){
            fw.write(pc.getMaNS() + "," +
                    pc.getMaDA() + "," +
                    pc.getNgayPhanCong() + "," +
                    pc.getNgayHoanThanh() + "," +
                    pc.getDeadLineDeAN() + "\n");
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

    public void docTuFilePhanCong(){
        try(BufferedReader br = new BufferedReader(new FileReader("data/phancong.txt"))){
            String line;
            while((line = br.readLine()) != null){
                if(line.trim().isEmpty()) continue;
                String[] arr=line.split(",", -1);
                String maNS= arr[0];
                String maDA=arr[1];
                String tienThuong= arr[2];
                String ngayHoanThanh = arr[4];
                String ngayPhanCong = arr[3];
                String deadLineDeAn = arr[5];
                if(soPhanCong>=dsPhanCong.length) dsPhanCong = tangKichThuoc(dsPhanCong);
                dsPhanCong[soPhanCong++] = new PhanCong(maNS, maDA, tienThuong, ngayHoanThanh, ngayPhanCong, deadLineDeAn);
            }
            System.out.println("đã đọc" + soPhanCong + "phân công từ file data/phancong.txt");
        } catch(Exception e){
            System.out.println("lỗi đọc file phancong: " + e.getMessage());
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

    public void themPhongBan(){
        if(soPhongBan>= dsPhongBan.length) dsPhongBan = tangKichThuoc(dsPhongBan);
        PhongBan pb =new PhongBan();
        pb.nhapPhongBan();
        dsPhongBan[soPhongBan++] = pb;
        ghiPhongBanVaoFile(pb);
    }

public void themDeAn(){
    if(soDeAn >= dsDeAn.length) dsDeAn = tangKichThuoc(dsDeAn);
    DeAn da = new DeAn();
    da.nhapDeAn();
    // Kiểm tra mã phòng ban có tồn tại không, nếu không thì yêu cầu nhập lại
    while (!tonTaiMaPhong(da.getMaPhong())) {
        System.out.println("Phòng ban không tồn tại! Hiện tại có các phòng ban sau:");
        xuatPhongBan();
        System.out.print("Vui lòng nhập lại mã phòng: ");
        String maPhong = sc.nextLine();
        da.setMaPhong(maPhong);
    }
    dsDeAn[soDeAn++] = da;
    ghiDeAnVaoFile(da);
}

// Thêm hàm kiểm tra tồn tại mã phòng ban
private boolean tonTaiMaPhong(String maPhong) {
    if (maPhong == null) return false;
    for (int i = 0; i < soPhongBan; i++) {
        if (maPhong.equalsIgnoreCase(dsPhongBan[i].getMaPhong())) {
            return true;
        }
    }
    return false;
}

    public void themPhanCong(){
        if(soPhanCong>= dsPhanCong.length) dsPhanCong = tangKichThuoc(dsPhanCong);
        PhanCong pc = new PhanCong();
        pc.nhapPhanCong();
        dsPhanCong[soPhanCong++] = pc;
        ghiPhanCongVaoFile(pc);
    }

    public void xuatPhongBan(){
        System.out.println("===DANH SÁCH PHÒNG BAN===");
        for(int i=0;i<soPhongBan; i++){
            System.out.println((i+1) + ". " + dsPhongBan[i]);
        }
    }

    public void xuatDeAn(){
        System.out.println("===DANH SÁCH ĐỀ ÁN===");
        for(int i=0; i<soDeAn; i++){
            System.out.println((i+1) + ". " +dsDeAn[i]);
        }
    }

    public void xuatPhanCong(){
        System.out.println("===DANH SÁCH PHÂN CÔNG===");
        for (int i=0; i<soPhanCong;i++){
            System.out.println((i+1) + ". " +dsPhanCong[i]);
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

    // Ghi toàn bộ danh sách phân công vào file
    public void ghiToanBoPhanCong(){
        try(FileWriter fw = new FileWriter("data/phancong.txt", false)){ // false = overwrite
            for(int i = 0; i < soPhanCong; i++){
                if(dsPhanCong[i] != null){
                    PhanCong pc = dsPhanCong[i];
                    String tt = pc.getTienThuong();
                    fw.write(pc.getMaNS() + "," + 
                            pc.getMaDA() + "," + 
                            (tt == null ? "" : tt) + "," +
                            pc.getNgayPhanCong() + "," +
                            pc.getNgayHoanThanh() + "," +
                            pc.getDeadLineDeAN() + "\n");
                }
            }
            System.out.println("Da ghi " + soPhanCong + " phan cong vao file.");
        } catch(IOException e){
            System.out.println("Loi ghi file phan cong: " + e.getMessage());
        }
    }
}
