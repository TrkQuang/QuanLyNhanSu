package HSNS;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class CHUCVU {
    private String maCV;
    private String tenCV;
    private double heSoLuong;
    Scanner sc=new Scanner(System.in);
    public CHUCVU(){
        maCV=" ";
        tenCV=" ";
        heSoLuong=0.0;
    }
    public CHUCVU(String maCV, String tenCV, double heSoLuong){
        this.maCV=maCV;
        this.tenCV=tenCV;
        this.heSoLuong=heSoLuong;
    }
    public String getmaCV(){
        return maCV;
    }    
    public void setmaCV(String maCV){
        this.maCV=maCV;
    }
    public String getTenCV(){
        return tenCV;
    }
    public void setTenCV(String tenCV){
        this.tenCV=tenCV;
    }
    public double getHeSoLuong(){
        return heSoLuong;
    }
    public void setHeSoLuong(double heSoLuong){
        this.heSoLuong=heSoLuong;
    }
    public void nhap(){
        System.out.print("Ma CV: ");
        maCV=sc.nextLine();
        System.out.print("Ten CV: ");
        tenCV=sc.nextLine();
        System.out.print("He so luong: ");
        heSoLuong=sc.nextDouble();
    }
    public void xuat(){
        System.out.println(maCV + "     " + tenCV + "    " + heSoLuong);
    }
    void ghiFile() throws IOException { 
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("chucvu.txt"));
        for (int k = 0; k < 5; k++) {
            outputStream.writeUTF(maCV);
            outputStream.writeUTF(tenCV);
            outputStream.writeDouble(heSoLuong);
        }
        outputStream.close();
    }
    void docFile() {
        try {
            DataInputStream inputStream = new DataInputStream(new FileInputStream("chucvu.txt"));
            try {
                while (true) {
                    String maCV = inputStream.readUTF();
                    String tenCV = inputStream.readUTF();
                    double heSoLuong = inputStream.readDouble();
                    System.out.print(maCV + " " + tenCV + " " + heSoLuong + "\n");
                }
            } 
        catch (EOFException e) {}
        finally { inputStream.close(); }
        } 
    catch (IOException e) {}
    }
}
