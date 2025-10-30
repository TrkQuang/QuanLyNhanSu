package Luong;

import Center.DataCenter;

/**
 * Main class để test module Lương
 */
public class Main {
    public static void main(String[] args) {
        // Load dữ liệu từ các module khác vào DataCenter
        System.out.println("Dang tai du lieu tu cac module...\n");
        
        // 1. Load nhân sự (cần cho tính lương)
        DataCenter.dsNhanSu.docFileVaoDs();
        
        // 2. Load chấm công (lương cơ bản)
        DataCenter.qlct.docCongThangTuFile();
        
        // 3. Load thưởng (thưởng module)
        DataCenter.dsctt.docFile();
        DataCenter.dsdmt.docFile();
        
        // 4. Load phòng ban và phân công (thưởng dự án)
        DataCenter.dspb.docTuFilePhongBan();
        DataCenter.dsda.docTuFileDeAn();
        DataCenter.dspc.docTuFilePhanCong();
        
        System.out.println("Da tai xong du lieu!\n");
        
        // Khởi động module quản lý lương
        Danhsachluong qlLuong = new Danhsachluong();
        qlLuong.menu();
        
        System.out.println("\nKet thuc chuong trinh quan ly luong.");
    }
}
