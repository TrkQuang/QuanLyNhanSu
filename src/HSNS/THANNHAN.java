package HSNS;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class THANNHAN {
    private String maTN;
    private String hoTN;
    private String tenTN;
    private String gioiTinh;
    private String ngaySinh;
    private String quanHe;
    Scanner sc=new Scanner(System.in);
    public THANNHAN(){
        maTN=" ";
        hoTN=" "; 
        tenTN=" ";
        gioiTinh=" ";
        ngaySinh=" ";
        quanHe=" ";

    };
    public THANNHAN(String maTN, String maNS, String hoTN, String tenTN, String gioiTinh, String ngaySinh, String quanHe){
        this.maTN=maTN;
        this.hoTN=hoTN;
        this.tenTN=tenTN;
        this.gioiTinh=gioiTinh;
        this.ngaySinh=ngaySinh;
        this.quanHe=quanHe;
    }
    public String getmaTN(){
        return maTN;
    }
    public void setmaTN(String maTN){
        this.maTN=maTN;
    }
    public String getHoTN(){
        return hoTN;
    }
    public void setHoTN(String hoTN){
        this.hoTN=hoTN;
    }
    public String gettenTN(){
        return tenTN;
    }
    public void settenTN(String tenTN){
        this.tenTN=tenTN;
    }
    public String getGioiTinh(){
        return gioiTinh;
    }
    public void setGioiTinh(String gioiTinh){
        this.gioiTinh=gioiTinh;
    }
    public String getNgaySinh(){
        return ngaySinh;
    }
    public void setNgaySinh(String ngaySinh){
        this.ngaySinh=ngaySinh;
    }
    public String getQuanHe(){
        return quanHe;
    }
    public void setQuanHe(String quanHe){
        this.quanHe=quanHe;
    }
    public void nhap(){
        System.out.print("Ma TN: ");
        maTN=sc.nextLine();
        System.out.print("Ho TN: ");
        hoTN=sc.nextLine();
        System.out.print("Ten TN: ");
        tenTN=sc.nextLine();
        System.out.print("Gioi tinh: ");
        gioiTinh=sc.nextLine();
        System.out.print("Ngay sinh: ");
        ngaySinh=sc.nextLine();
        System.out.print("Quan he: ");
        quanHe=sc.nextLine();
    }
    public void xuat(){
        System.out.println(maTN + "   " + hoTN + "    " + tenTN + "   " + gioiTinh + "   " + ngaySinh + "   " + quanHe + "   ");
    }
    public void ghiFile() throws IOException { 
        DataOutputStream outStream = new DataOutputStream(new FileOutputStream("thannhan.txt"));
        for (int k = 0; k < 5; k++) { // Output 5 data records
            outStream.writeUTF(maTN);
            outStream.writeUTF(hoTN);
            outStream.writeUTF(tenTN);
            outStream.writeUTF(gioiTinh);
            outStream.writeUTF(ngaySinh);
            outStream.writeUTF(quanHe);
        }
        outStream.close();
    }
    void docFile() {
        try {
            DataInputStream inputStream = new DataInputStream(new FileInputStream("thannhan.txt"));
            try {
                while (true) {
                    String maTN = inputStream.readUTF();
                    String hoTN = inputStream.readUTF();
                    String tenTN = inputStream.readUTF();
                    String gioiTinh = inputStream.readUTF();
                    String ngaySinh = inputStream.readUTF();
                    String quanHe = inputStream.readUTF();
                    System.out.print(maTN + " " + hoTN + " " + tenTN + " " + gioiTinh + " " + ngaySinh + " " + quanHe + "\n");
                } 
            } 
            catch (EOFException e) {}
            finally {
                inputStream.close();
            }
        } 
        catch (IOException e) {}
    }
}
