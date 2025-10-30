package PHONGBAN_DEAN;
import java.util.Scanner;
import java.io.*;
public class Danhsachphancong {
    private PhanCong[] dsPhanCong;
    private int soPhanCong;
    public static Scanner sc = new Scanner(System.in);

    public Danhsachphancong(){
        dsPhanCong = new PhanCong[100];
        soPhanCong = 0;
        docTuFilePhanCong(); // Tự động đọc file khi khởi tạo
    }

    private PhanCong[] tangKichThuoc(PhanCong[] arr){
        PhanCong[] newArr= new PhanCong[arr.length*2];
        for(int i=0; i<arr.length; i++) newArr[i] = arr[i];
        return newArr;
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

    public void themPhanCong(){
        if(soPhanCong>= dsPhanCong.length) dsPhanCong = tangKichThuoc(dsPhanCong);
        PhanCong pc = new PhanCong();
        pc.nhapPhanCong();
        dsPhanCong[soPhanCong++] = pc;
        ghiPhanCongVaoFile(pc);
    }

    public void xuatPhanCong(){
        System.out.println("===DANH SÁCH PHÂN CÔNG===");
        for (int i=0; i<soPhanCong;i++){
            System.out.println((i+1) + ". " +dsPhanCong[i]);
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