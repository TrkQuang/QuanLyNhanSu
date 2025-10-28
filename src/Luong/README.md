# MODULE LƯƠNG - TÍNH LƯƠNG TỔNG HỢP

## 📋 Tổng quan

Module Lương tự động tính tổng hợp lương tháng cho nhân sự từ 3 nguồn dữ liệu:

### 1. 💼 Lương Cơ Bản (từ ChamCong)

- **Full-time**: `tongNgayLam × 500,000 VNĐ/ngày`
- **Part-time**: `tongGioLam × 50,000 VNĐ/giờ`
- Nguồn dữ liệu: `data/congThang.txt`

### 2. 🎁 Thưởng Module (từ QUANLYTHUONG)

- Thưởng từ các danh mục thưởng (DMTHUONG)
- Chi tiết thưởng (CTTHUONG) theo tháng
- Nguồn dữ liệu: `data/danhMucThuong.txt`, `data/chiTietThuong.txt`

### 3. 🏆 Thưởng Dự Án (từ QuanLyPhongBan/PhanCong)

- Thưởng từ phân công dự án đã hoàn thành
- Tính theo ngày hoàn thành: sớm/đúng hạn/trễ
- Nguồn dữ liệu: `data/phancong.txt`

---

## 🏗️ Cấu trúc

### Class `Luong.java`

**Thuộc tính:**

- `maNS`: Mã nhân sự
- `thang, nam`: Tháng/năm tính lương
- `tongNgayLam, tongGioLam`: Công việc
- `luongCoBan`: Lương từ công
- `thuongModule`: Thưởng từ module Thưởng
- `thuongDuAn`: Thưởng từ dự án
- `tongLuong`: Tổng lương = cơ bản + 2 loại thưởng

**Methods chính:**

- `tinhLuongCoBan()`: Tính lương từ công (ngày hoặc giờ)
- `tinhTongLuong()`: Tổng hợp 3 nguồn
- `xuat()`: Hiển thị bảng lương chi tiết
- `toFileString()`, `fromFileString()`: Ghi/đọc file

### Class `QuanLyLuong.java`

**Chức năng:**

- `tinhLuongTuDong(maNS, thang, nam)`: Tính lương tự động cho 1 NS
- `tinhLuongChoTatCaNhanSu(thang, nam)`: Tính cho tất cả NS
- `hienThiTatCaLuong()`: Hiển thị danh sách
- `hienThiLuongTheoNhanSu()`: Tra cứu lương
- `ghiFile()`, `docFile()`: Lưu/đọc `data/luong.txt`
- `menu()`: Giao diện quản lý

---

## 🔌 API đã thêm

### 1. `DanhSachChamCong.java`

```java
// Tìm công tháng của nhân sự
public ChamCongThang timCongThang(String maNS, int thang, int nam)

// Load công tháng từ file
public void docCongThangTuFile()
```

### 2. `QuanLyPhongBan.java`

```java
// Tính tổng thưởng phân công theo tháng
public double tinhTongThuongPhanCongTheoThang(String maNS, int month, int year)
```

### 3. `QUANLYTHUONG.java` (đã có sẵn)

```java
// Tính tổng thưởng từ module Thưởng
public double tinhTongThuongTheoNhanSuTrongThang(String maNS, int month, int year)
```

---

## 🚀 Cách sử dụng

### 1. Chạy module Lương độc lập:

```bash
cd src
javac Luong/*.java
java Luong.Main
```

### 2. Tích hợp vào hệ thống chính:

```java
// Trong menu chính, thêm:
QuanLyLuong qlLuong = new QuanLyLuong();
qlLuong.menu();
```

### 3. Menu chức năng:

1. **Tính lương tất cả NS (tháng)**: Nhập tháng/năm → tự động tính cho tất cả NS có công
2. **Tính lương 1 nhân sự**: Nhập mã NS + tháng/năm → hiển thị bảng lương chi tiết
3. **Hiển thị tất cả bảng lương**: Xem danh sách đã tính
4. **Tra cứu lương nhân sự**: Tìm lương của 1 NS cụ thể
5. **Lưu dữ liệu lương**: Ghi vào `data/luong.txt`
6. **Đọc dữ liệu từ file**: Load lại lương đã lưu
7. **Quay lại (tự động lưu)**: Tự động lưu trước khi thoát

---

## 📊 Format file `data/luong.txt`

```
maNS,thang,nam,tongNgayLam,tongGioLam,luongCoBan,thuongModule,thuongDuAn,tongLuong
001,10,2025,22.0,0.0,11000000.0,500000.0,1000000.0,12500000.0
002,10,2025,0.0,160.0,8000000.0,0.0,0.0,8000000.0
```

---

## 💡 Ví dụ bảng lương

```
========== BẢNG LƯƠNG ==========
Mã nhân sự: 001
Tháng/Năm: 10/2025
--------------------------------
Công việc:
  - Tổng ngày làm: 22.0 ngày
  - Tổng giờ làm: 0.0 giờ
  - Lương cơ bản: 11,000,000 VNĐ
--------------------------------
Thưởng:
  - Thưởng module: 500,000 VNĐ
  - Thưởng dự án: 1,000,000 VNĐ
================================
💰 TỔNG LƯƠNG: 12,500,000 VNĐ
================================
```

---

## ⚙️ Hằng số cấu hình

Có thể điều chỉnh trong `Luong.java`:

```java
private static final double LUONG_NGAY = 500_000.0;  // 500k/ngày
private static final double LUONG_GIO = 50_000.0;    // 50k/giờ
```

---

## ✅ Tính năng nổi bật

1. ✅ **Tự động tổng hợp** từ 3 module độc lập
2. ✅ **Hỗ trợ full-time và part-time** (tính theo ngày hoặc giờ)
3. ✅ **Validation tự động** - chỉ tính nếu có dữ liệu
4. ✅ **Lưu/đọc file** - persistent storage
5. ✅ **Format tiền tệ đẹp** - dấu phẩy ngăn cách hàng nghìn
6. ✅ **Menu thân thiện** - giao diện box đẹp
7. ✅ **Auto-save** - tự động lưu khi thoát

---

## 🔗 Dependencies

Module này phụ thuộc vào:

- `Center.DataCenter` - Truy cập global data
- `ChamCong.DanhSachChamCong` - Dữ liệu công
- `Thuong.QUANLYTHUONG` - Dữ liệu thưởng module
- `PHONGBAN.QuanLyPhongBan` - Dữ liệu thưởng dự án
- `NhanSu.DanhSachNhanSu` - Danh sách nhân sự

---

## 🎯 Lưu ý quan trọng

1. **Phải load dữ liệu trước**: Gọi `docFile()` của các module trước khi tính lương
2. **Công tháng phải có sẵn**: Chạy tổng hợp công (`DanhSachChamCong.tongHopTuFile()`) trước
3. **File format**: Đảm bảo `congThang.txt` đúng format: `maCC,maNS,nam,thang,tongGioLam,tongNgayLam`
4. **Thưởng dự án**: Chỉ tính thưởng từ dự án đã hoàn thành (có `ngayHoanThanh`)

---

## 🐛 Troubleshooting

**Lỗi: "Không tìm thấy công tháng"**

- ✅ Kiểm tra `data/congThang.txt` có dữ liệu không
- ✅ Chạy tổng hợp công trước (module ChamCong)

**Lương = 0**

- ✅ Nhân sự chưa có công trong tháng đó
- ✅ Kiểm tra format ngày tháng trong file

**Không load được thưởng**

- ✅ Kiểm tra `DataCenter` đã load `qlThuong` và `qlPhongBan` chưa
- ✅ Gọi `docFile()` của các module trước

---

## 📝 TODO (tương lai)

- [ ] Thêm thuế TNCN
- [ ] Bảo hiểm xã hội
- [ ] Phụ cấp (ăn trưa, xăng xe, điện thoại)
- [ ] Tạm ứng lương
- [ ] Export Excel
- [ ] Gửi email thông báo lương

---

**Phát triển bởi**: QuanLyNhanSu Team  
**Version**: 1.0  
**Last updated**: 2025
