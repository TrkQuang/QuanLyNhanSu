package HSNS;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

import Center.DataCenter;


public class CHITIETNS {
    private String maNS;
    private THANNHAN[] dsThanNhan; // Mảng thân nhân
    private int soLuongThanNhan;   // Số lượng thân nhân hiện tại
    private String sdt;
    private String diachi;
    private String email;
    private String trinhdo;
    Scanner sc = new Scanner(System.in);
    public CHITIETNS(){
        maNS=" ";
        sdt=" ";
        diachi=" ";
        email=" ";
        trinhdo=" ";
        dsThanNhan = new THANNHAN[5]; // Khởi tạo mảng tối đa 5 thân nhân
        soLuongThanNhan = 0;
    }
    public CHITIETNS(String maNS, String maTN, String sdt, String diachi, String email, String trinhdo, THANNHAN tn){
        this.maNS=maNS;
        this.sdt=sdt;
        this.diachi=diachi;
        this.email=email;
        this.trinhdo=trinhdo;
        this.dsThanNhan = new THANNHAN[5];
        this.soLuongThanNhan = 0;
        if (tn != null) {
            this.dsThanNhan[0] = tn;
            this.soLuongThanNhan = 1;
        }
    }
    public CHITIETNS(CHITIETNS ctns){
        this.maNS=ctns.maNS;
        this.sdt=ctns.sdt;
        this.diachi=ctns.diachi;
        this.email=ctns.email;
        this.trinhdo=ctns.trinhdo;
        this.dsThanNhan = new THANNHAN[5];
        this.soLuongThanNhan = ctns.soLuongThanNhan;
        // Copy danh sách thân nhân
        for (int i = 0; i < ctns.soLuongThanNhan; i++) {
            this.dsThanNhan[i] = ctns.dsThanNhan[i];
        }
    }
    public String getmaNS(){
        return maNS;
    }
    public void setmaNS(String maNS){
        this.maNS=maNS;
    }
    public String getSdt(){
        return sdt;
    }
    public void setSdt(String sdt){
        this.sdt=sdt;
    }
    public String getDiachi(){
        return diachi;
    }
    public void setDiachi(String diachi){
        this.diachi=diachi;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getTrinhdo(){
        return trinhdo;
    }
    public void setTrinhdo(String trinhdo){
        this.trinhdo=trinhdo;
    }
    public THANNHAN[] getdsThanNhan() {
        return dsThanNhan;
    }
    public void setdsThanNhan(THANNHAN[] dsThanNhan) {
        this.dsThanNhan = dsThanNhan;
    }
    public int getSoLuongThanNhan() {
        return soLuongThanNhan;
    }
    public void setSoLuongThanNhan(int soLuongThanNhan) {
        this.soLuongThanNhan = soLuongThanNhan;
    }
    // Thêm 1 thân nhân vào danh sách
    public void themThanNhan(THANNHAN tn) {
        if (soLuongThanNhan < dsThanNhan.length) {
            dsThanNhan[soLuongThanNhan] = tn;
            soLuongThanNhan++;
        } else {
            System.out.println("❌ Đã đủ số lượng thân nhân tối đa!");
        }
    }
    public boolean kiemTrasdt(){
        if(sdt==null) return false;
        //Bieu thuc : bat dau bang 0 va theo sau la 9 chu so
        String bieuThuc="^0\\d{9}$";
        return sdt.matches(bieuThuc);
    }
    public boolean kiemTraemail(){
        if(email==null) return false;
        //Biểu thức: địa chỉ email có dạng [tên]@[tên miền].[đuôi]
        String bieuThuc="^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,6}$";
        return Pattern.matches(bieuThuc,email);
    }
   
    public void nhap(){
        System.out.print("Ma NS (hoặc '0' để hủy): ");
        maNS=sc.nextLine();
        if (maNS.equals("0")) {
            System.out.println("Hủy nhập chi tiết nhân sự.");
            return;
        }
        while(!DataCenter.dsNhanSu.tonTaiNhanSu(maNS)){
            System.out.print("Nhan su ko ton tai! Vui long nhap lai MaNS (hoặc '0' để hủy): ");
            maNS=sc.nextLine();
            if (maNS.equals("0")) {
                System.out.println("Hủy nhập chi tiết nhân sự.");
                return;
            }
        }
        System.out.print("So dien thoai: ");
        sdt=sc.nextLine();
        System.out.print("Dia chi: ");
        diachi=sc.nextLine();
        System.out.print("Email: ");
        email=sc.nextLine();
        System.out.print("Trinh do: ");
        trinhdo=sc.nextLine();
        
        // Nhập nhiều thân nhân
        System.out.print("Nhập số lượng thân nhân: ");
        int n = Integer.parseInt(sc.nextLine());
        soLuongThanNhan = 0;
        for (int i = 0; i < n && i < dsThanNhan.length; i++) {
            System.out.println("\n--- Thân nhân thứ " + (i+1) + " ---");
            THANNHAN tn = new THANNHAN();
            tn.nhap();
            themThanNhan(tn);
        }
    }
    public void xuat(){
        System.out.println(maNS + "    " + sdt + "   " + diachi + "    " + email + "   " + trinhdo);
        System.out.println("  Danh sách thân nhân (" + soLuongThanNhan + " người):");
        for (int i = 0; i < soLuongThanNhan; i++) {
            if (dsThanNhan[i] != null) {
                System.out.print("    [" + (i+1) + "] ");
                dsThanNhan[i].xuat();
            }
        }
    }
    public void ghiFile() throws IOException{
        DataOutputStream outputStream=new DataOutputStream(new FileOutputStream("chitietns.txt"));
        outputStream.writeUTF(maNS); 
        outputStream.writeUTF(sdt);  
        outputStream.writeUTF(diachi); 
        outputStream.writeUTF(email); 
        outputStream.writeUTF(trinhdo);
        
        // Ghi số lượng thân nhân
        outputStream.writeInt(soLuongThanNhan);
        
        // Ghi từng thân nhân
        for(int i = 0; i < soLuongThanNhan; i++){
            outputStream.writeUTF(dsThanNhan[i].getmaTN());
            outputStream.writeUTF(dsThanNhan[i].getHoTN());
            outputStream.writeUTF(dsThanNhan[i].gettenTN());
            outputStream.writeUTF(dsThanNhan[i].getGioiTinh());
            outputStream.writeUTF(dsThanNhan[i].getNgaySinh());
            outputStream.writeUTF(dsThanNhan[i].getQuanHe());
        }
        outputStream.close();
    }
    public void docFile(){
        try{
            DataInputStream inputStream=new DataInputStream(new FileInputStream("chitietns.txt"));
            try{
                while(true){
                    String maNS = inputStream.readUTF();
                    String sdt = inputStream.readUTF();
                    String diachi = inputStream.readUTF();
                    String email = inputStream.readUTF();
                    String trinhdo = inputStream.readUTF();
                    
                    // Đọc số lượng thân nhân
                    int n = inputStream.readInt();
                    
                    System.out.println(maNS + " | " + sdt + " | " + diachi + " | " + email + " | " + trinhdo);
                    System.out.println("  Thân nhân (" + n + " người):");
                    
                    // Đọc từng thân nhân
                    for(int i = 0; i < n; i++) {
                        String maTN = inputStream.readUTF();
                        String hoTN = inputStream.readUTF();
                        String tenTN = inputStream.readUTF();
                        String gioiTinh = inputStream.readUTF();
                        String ngaySinh = inputStream.readUTF();
                        String quanHe = inputStream.readUTF();
                        System.out.println("    [" + (i+1) + "] " + maTN + " - " + hoTN + " " + tenTN + " - " + gioiTinh + " - " + ngaySinh + " - " + quanHe);
                    }
                    System.out.println();
                }
            }
            catch(EOFException e){}
            finally{
                inputStream.close();
            }
        }
        catch(IOException e){}
    }
}
