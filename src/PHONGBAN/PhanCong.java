package PHONGBAN;

import java.util.Scanner;

import Center.DataCenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * PhanCong: Lưu thông tin phân công nhân sự vào đề án và tự tính tiền thưởng
 * - tiền thưởng tự tính dựa trên ngày hoàn thành so với deadline
 * - không cần nhập thủ công, sẽ tự cập nhật khi đủ dữ liệu
 */
public class PhanCong {
    private String maNS;
    private String maDA;
    private String ngayPhanCong;
    private String ngayHoanThanh;
    private String deadLineDeAn;
    private String tienThuong;
    // Hệ số thưởng (VNĐ/ngày), dùng kiểu long để dễ hiểu và tránh số thực
    private static final long HESO_THUONG = 50_000L;
    // Định dạng ngày sử dụng yyyy-MM-dd (MM là tháng)
    private static final SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
    
    public PhanCong(){
    this.maDA= "";
    this.maNS="";
    this.ngayHoanThanh="";
    this.ngayPhanCong="";
    this.deadLineDeAn="";
    this.tienThuong = null; // Mặc định chưa có tiền thưởng
    }

    
    public PhanCong(String maNS, String maDA, String tienThuong, String ngayHoanThanh, String ngayPhanCong, String deadLineDeAn){
        this.maDA=maDA;
        this.maNS=maNS;
        this.ngayHoanThanh= ngayHoanThanh;
        this.ngayPhanCong = ngayPhanCong;
        this.deadLineDeAn=deadLineDeAn;
        // Nếu có truyền sẵn tiền thưởng (ví dụ khi đọc từ file), giữ lại; nếu rỗng hoặc "null" thì tự tính
        boolean hasProvidedTienThuong = false;
        if (tienThuong != null) {
            String tt = tienThuong.trim();
            if (!tt.isEmpty() && !tt.equalsIgnoreCase("null")) {
                this.tienThuong = tt;
                hasProvidedTienThuong = true;
            }
        }
        if (!hasProvidedTienThuong) {
            this.tienThuong = null;
            capNhatTienThuongIfReady();
        }
    }
    public PhanCong(DeAn deAn, String maNS, String tienThuong, String ngayHoanThanh){
        // Không nhận tiền thưởng khi tạo từ Đề án; để null và sẽ tính khi đánh giá
        this(maNS, deAn.getMaDA(), null, ngayHoanThanh, deAn.getNgayBD(), deAn.getNgayKT());
    }
    private Date parseDate(String dateStr){
        try{
            return sdf.parse(dateStr);
        }
        catch (ParseException e){
            throw new RuntimeException("lỗi định dạng ngày: " +dateStr);
        }
    }
    public PhanCong(PhanCong pc){
        this.maDA=pc.maDA;
        this.maNS=pc.maNS;
        this.ngayHoanThanh=pc.ngayHoanThanh;
        this.deadLineDeAn=pc.deadLineDeAn;
        this.ngayPhanCong=pc.ngayPhanCong;
        this.tienThuong = pc.tienThuong; // Giữ nguyên tiền thưởng nếu đã được tính trước đó
    }

    public String getMaDA(){return maDA;}
    public String getMaNS(){return maNS;}
    /**
     * Lấy tiền thưởng (VNĐ, dạng chuỗi). Nếu chưa có, sẽ tự tính khi đủ dữ liệu.
     */
    public String getTienThuong(){
        if (this.tienThuong == null) {
            capNhatTienThuongIfReady();
        }
        return tienThuong;
    }
    public String getNgayPhanCong(){return ngayPhanCong;}
    public String getNgayHoanThanh(){return ngayHoanThanh;}
    public String getDeadLineDeAN(){return deadLineDeAn;}
    public void setMaDA(String maDA){this.maDA= maDA;}
    public void setMaNS(String maNS){this.maNS=maNS;}
    public void setNgayPhanCong(String ngayPhanCong){this.ngayPhanCong=ngayPhanCong;}
    public void setTienThuong(String tienThuong){this.tienThuong= tienThuong;}
    /**
     * Cập nhật ngày hoàn thành và thử tính lại tiền thưởng nếu đủ dữ liệu.
     */
    public void setNgayHoanThanh(String ngayHoanThanh){
        this.ngayHoanThanh=ngayHoanThanh;
        capNhatTienThuongIfReady();
    }
    /**
     * Cập nhật deadline và thử tính lại tiền thưởng nếu đủ dữ liệu.
     */
    public void setDeadLineDeAn(String deadLineDeAn){
        this.deadLineDeAn=deadLineDeAn;
        capNhatTienThuongIfReady();
    }

    // Tính số ngày chênh lệch giữa deadline và ngày hoàn thành
    private long tinhSoNgayLech() {
        Date ngayHoanThanhDate = parseDate(ngayHoanThanh);
        Date deadlineDate = parseDate(deadLineDeAn);
        return (deadlineDate.getTime() - ngayHoanThanhDate.getTime()) / (1000L * 60 * 60 * 24);
    }

    // Tính tiền thưởng (VND, số nguyên) dựa trên số ngày lệch
    private long tinhTienThuongVND(long soNgayLech) {
        if (soNgayLech > 0) return soNgayLech * HESO_THUONG; // sớm: thưởng theo số ngày sớm
        if (soNgayLech == 0) return HESO_THUONG;              // đúng hạn: thưởng 1 lần hệ số
        return 0L;                                            // trễ: không thưởng
    }

    // Cập nhật thuộc tính tienThuong theo logic hiện tại
    /**
     * Tính và cập nhật thuộc tính tiền thưởng theo quy tắc hiện tại.
     */
    public void capNhatTienThuong() {
        long soNgayLech = tinhSoNgayLech();
        long tien = tinhTienThuongVND(soNgayLech);
        this.tienThuong = String.format("%d", tien);
    }

    // Chỉ cập nhật khi dữ liệu cần thiết đã sẵn sàng; nếu thiếu, đặt về null
    /**
     * Chỉ cập nhật tiền thưởng khi đã có cả ngày hoàn thành và deadline.
     * Nếu thiếu dữ liệu, đặt tienThuong = null.
     */
    private void capNhatTienThuongIfReady() {
        if (isNullOrEmpty(this.ngayHoanThanh) || isNullOrEmpty(this.deadLineDeAn)) {
            this.tienThuong = null;
            return;
        }
        capNhatTienThuong();
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public String getDanhGia() {
        long soNgayLech = tinhSoNgayLech();
        String danhGia;
        if (soNgayLech > 0) {
            danhGia = "Hoàn thành sớm " + soNgayLech + " ngày.";
        } else if (soNgayLech == 0) {
            danhGia = "Hoàn thành đúng hạn.";
        } else {
            danhGia = "Hoàn thành trễ " + Math.abs(soNgayLech) + " ngày.";
        }
        // Luôn cập nhật tiền thưởng khi đánh giá
        long tien = tinhTienThuongVND(soNgayLech);
        this.tienThuong = String.format("%d", tien);
        return danhGia + " (Thưởng: " + this.tienThuong + " VNĐ)";
    }

    Scanner sc= new Scanner(System.in);
    public void nhapPhanCong(){
        System.out.print("Nhap ma NS (hoặc '0' để hủy): ");
        maNS=sc.nextLine();
        if (maNS.equals("0")) {
            System.out.println("Hủy nhập mã nhân sự.");
            return;
        }
        while(!DataCenter.dsNhanSu.tonTaiNhanSu(maNS)){
            System.out.print("Nhan su ko ton tai! Vui long nhap lai MaNS (hoặc '0' để hủy): ");
            maNS=sc.nextLine();
            if (maNS.equals("0")) {
                System.out.println("Hủy nhập mã nhân sự.");
                return;
            }
        }
        
        // Nhập và kiểm tra mã đề án
        System.out.print("Mã đề án (hoặc '0' để hủy): ");
        this.maDA = sc.nextLine().trim();
        if (this.maDA.equals("0")) {
            System.out.println("Huy nhap phan cong.");
            return;
        }
        
        while(!DataCenter.qlPhongBan.tonTaiDeAn(this.maDA)) {
            System.out.println(" Mã đề án không tồn tại trong hệ thống!");
            System.out.print("Nhập lại mã đề án (hoặc '0' để hủy): ");
            this.maDA = sc.nextLine().trim();
            if (this.maDA.equals("0")) {
                System.out.println("Hủy nhập phân công.");
                return;
            }
        }
        
        System.out.print("Ngày hoàn thành (yyyy-MM-dd): ");
        this.ngayHoanThanh = sc.nextLine().trim();
        System.out.print("Ngày phân công (yyyy-MM-dd) (enter để dùng ngày BD của đề án): ");
        this.ngayPhanCong = sc.nextLine().trim();
        System.out.print("Deadline đề án (yyyy-MM-dd) (enter để dùng ngày KT của đề án): ");
        this.deadLineDeAn = sc.nextLine().trim();
        capNhatTienThuongIfReady();
    }

    public void xuatPhanCong(){
        String tt = getTienThuong(); // đảm bảo tự tính nếu cần
        System.out.println(
            "PhanCong{" +
            "maNS='" + maNS + '\'' +
            ", maDA='" + maDA + '\'' +
            ", tienThuong='" + (tt == null ? "" : tt) + '\'' +
            ", ngayHoanThanh='" + ngayHoanThanh + '\'' +
            ", ngayPhanCong='" + ngayPhanCong + '\'' +
            ", deadLine='" + deadLineDeAn + '\'' +
            "}"
        );
    }

    public String toString() {
    return "Phân công: [Mã NS=" + maNS + ", Mã DA=" + maDA +
        ", Ngày PC=" + ngayPhanCong + ", Hoàn thành=" + ngayHoanThanh +
        ", Deadline=" + deadLineDeAn + ", " + getDanhGia() + "]";
    }
}
