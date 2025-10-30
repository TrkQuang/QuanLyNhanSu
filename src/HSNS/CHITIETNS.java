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
    }
    
    public CHITIETNS(String maNS, String sdt, String diachi, String email, String trinhdo){
        this.maNS=maNS;
        this.sdt=sdt;
        this.diachi=diachi;
        this.email=email;
        this.trinhdo=trinhdo;
    }
    
    public CHITIETNS(CHITIETNS ctns){
        this.maNS=ctns.maNS;
        this.sdt=ctns.sdt;
        this.diachi=ctns.diachi;
        this.email=ctns.email;
        this.trinhdo=ctns.trinhdo;
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
    }
    
    public void xuat(){
        System.out.println(maNS + "    " + sdt + "   " + diachi + "    " + email + "   " + trinhdo);
    }
    
    public void ghiFile() throws IOException{
        DataOutputStream outputStream=new DataOutputStream(new FileOutputStream("chitietns.txt"));
        outputStream.writeUTF(maNS); 
        outputStream.writeUTF(sdt);  
        outputStream.writeUTF(diachi); 
        outputStream.writeUTF(email); 
        outputStream.writeUTF(trinhdo);
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
                    
                    System.out.println(maNS + " | " + sdt + " | " + diachi + " | " + email + " | " + trinhdo);
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
