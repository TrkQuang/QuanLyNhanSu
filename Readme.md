# 📚 HƯỚNG DẪN SỬ DỤNG HỆ THỐNG QUẢN LÝ NHÂN SỰ

## 📋 Mục lục

- [Giới thiệu](#giới-thiệu)
- [Cài đặt và chạy](#cài-đặt-và-chạy)
- [Cấu trúc hệ thống](#cấu-trúc-hệ-thống)
- [Hướng dẫn sử dụng từng module](#hướng-dẫn-sử-dụng-từng-module)
- [Format dữ liệu](#format-dữ-liệu)
- [Lưu ý quan trọng](#lưu-ý-quan-trọng)

---

## 🎯 Giới thiệu

Hệ thống **Quản lý nhân sự** là ứng dụng Java console giúp quản lý toàn diện các hoạt động nhân sự bao gồm:

- ✅ Quản lý thông tin nhân sự (Full-time/Part-time)
- ✅ Quản lý hồ sơ nhân sự (Chi tiết, Chức vụ, Thân nhân)
- ✅ Chấm công (Ngày, Tháng)
- ✅ Quản lý phòng ban, đề án, phân công
- ✅ Quản lý thưởng (Danh mục, Chi tiết)
- ✅ Tính lương tự động

### ⚡ Tính năng nổi bật:

- **Tự động load dữ liệu** khi khởi động
- **DataCenter** cung cấp truy xuất nhanh giữa các module
- **Tính toán tự động**: Công tháng, Tiền thưởng phân công, Lương
- **Null-safe**: Xử lý dữ liệu thiếu/rỗng an toàn

---

## 🚀 Cài đặt và chạy

### Yêu cầu:

- Java JDK 8 trở lên
- Terminal hỗ trợ UTF-8 (để hiển thị tiếng Việt)

### Cách chạy:

**Windows (PowerShell):**

```powershell
cd c:\Users\shuut\Documents\java_coding\QuanLyNhanSu
javac -d bin -encoding UTF-8 src/**/*.java
java -cp bin App
```

**Linux/Mac:**

```bash
cd /path/to/QuanLyNhanSu
javac -d bin -encoding UTF-8 src/**/*.java
java -cp bin App
```

### Lần đầu chạy:

Hệ thống sẽ tự động:

1. Đọc tất cả file dữ liệu từ thư mục `data/`
2. Load vào bộ nhớ (DataCenter)
3. Hiển thị menu chính

---

## 🏗️ Cấu trúc hệ thống

```
QuanLyNhanSu/
├── src/
│   ├── App.java                    # Entry point chính
│   ├── Center/
│   │   └── DataCenter.java         # Singleton quản lý toàn bộ dữ liệu
│   ├── NhanSu/                     # Module nhân sự cơ bản
│   ├── ChiTietNS/                  # Module hồ sơ chi tiết
│   ├── ChamCong/                   # Module chấm công
│   ├── Thuong/                     # Module thưởng
│   ├── PHONGBAN_DEAN/              # Module phòng ban/đề án
│   └── Luong/                      # Module lương
├── data/                           # Thư mục chứa file dữ liệu
└── bin/                            # Thư mục output sau compile
```

### DataCenter - Trung tâm dữ liệu:

```java
DataCenter.dsNhanSu         // Danh sách nhân sự
DataCenter.dsChiTietNS      // Chi tiết nhân sự
DataCenter.dsChucVu         // Chức vụ
DataCenter.dsThanNhan       // Thân nhân
DataCenter.qlcc             // Chấm công ngày
DataCenter.qlct             // Công tháng
DataCenter.qlDMThuong       // Danh mục thưởng
DataCenter.qlCTThuong       // Chi tiết thưởng
DataCenter.dsPhongBan       // Phòng ban
DataCenter.dsda             // Đề án
DataCenter.dspc             // Phân công
DataCenter.dsLuong          // Lương
```

---

## 📖 Hướng dẫn sử dụng từng module

### 1️⃣ Quản lý Nhân sự

**Menu:** `App.java` → Chọn `1`

#### Chức năng:

- **Thêm nhân sự**: Nhập mã NS, họ tên, số điện thoại, loại (Full-time/Part-time)
- **Sửa nhân sự**: Tìm theo mã → Cập nhật thông tin
- **Xóa nhân sự**: Xóa theo mã NS
- **Tìm kiếm**: Tìm theo mã, tên, SĐT
- **Xuất danh sách**: Hiển thị toàn bộ hoặc lọc theo loại

#### Lưu ý:

- Mã NS **không trùng**
- Loại nhân sự: `1` = Full-time, `2` = Part-time
- Xóa nhân sự sẽ ảnh hưởng đến các module khác (Chấm công, Phân công...)

---

### 2️⃣ Quản lý Hồ sơ nhân sự (HSNS)

**Menu:** `App.java` → Chọn `2`

#### Bao gồm 3 phần:

##### a) Chi tiết nhân sự:

- CCCD, Email, Địa chỉ, Ngày sinh
- File: `data/chitietnhansu.txt`

##### b) Chức vụ:

- Mã chức vụ, Tên chức vụ, Hệ số lương
- File: `data/chucvu.txt`

##### c) Thân nhân:

- Họ tên, Quan hệ, SĐT, CCCD
- File: `data/thannhan.txt`

---

### 3️⃣ Chấm công

**Menu:** `App.java` → Chọn `3`

#### a) Chấm công ngày:

- File: `data/chamcong.txt`
- Format: `maNS,ngayChamCong,gioVao,gioRa`
- Ví dụ: `001,2024-01-15,08:00,17:00`

#### b) Tổng hợp công tháng:

- **Tự động tổng hợp** từ dữ liệu công ngày
- File: `data/congThang.txt`
- Format: `maNS,nam,thang,tongNgayLam,tongGioLam`

**Cách dùng:**

1. Nhập chấm công ngày liên tục
2. Chọn "Tổng hợp công tháng" → Nhập năm, tháng
3. Hệ thống tự tính từ DataCenter (không đọc file nhiều lần)

---

### 4️⃣ Quản lý Thưởng

**Menu:** `App.java` → Chọn `4`

#### a) Danh mục thưởng:

- Mã DM thưởng, Tên, Số tiền
- File: `data/danhmucthuong.txt`

#### b) Chi tiết thưởng:

- Mã NS, Mã DM thưởng, Tháng, Năm
- File: `data/chitietthuong.txt`

**Lưu ý:**

- Một nhân sự có thể nhận nhiều loại thưởng/tháng
- API `tinhTongThuongTheoNhanSuTrongThang()` sẽ tổng hợp tất cả

---

### 5️⃣ Quản lý Phòng ban & Đề án

**Menu:** `App.java` → Chọn `5`

#### a) Phòng ban:

- Mã phòng, Tên, Trưởng phòng (mã NS), Ngày nhận chức
- File: `data/phongban.txt`
- Format ngày: `yyyy-MM-dd`

#### b) Đề án:

- Mã ĐA, Tên, Ngày BD, Ngày KT, Kinh phí
- File: `data/dean.txt`

#### c) Phân công:

- Mã NS, Mã ĐA, Ngày phân công, Ngày hoàn thành, Deadline
- **Tự tính tiền thưởng** dựa trên độ sớm/trễ
- File: `data/phancong.txt`

**Công thức thưởng phân công:**

```
Hoàn thành sớm  → Thưởng = số_ngày_sớm × 50,000 VNĐ
Đúng hạn        → Thưởng = 50,000 VNĐ
Trễ hạn         → Không thưởng (0 VNĐ)
```

**Khi nhập phân công:**

- Để trống "Ngày phân công" → Tự lấy ngày BD đề án
- Để trống "Deadline" → Tự lấy ngày KT đề án

---

### 6️⃣ Quản lý Lương

**Menu:** `App.java` → Chọn `6`

#### Tính lương tự động:

1. Lấy công tháng từ `congThang.txt`
2. Lấy thưởng từ module Thưởng
3. Tính:
   - **Full-time**: `Lương cơ bản × Hệ số + Thưởng`
   - **Part-time**: `Số giờ × Tiền công/giờ + Thưởng`

**File:** `data/luong.txt`  
**Format:** `maNS,thang,nam,luongCoBan,phuCap,thuong,tong`

---

## 📁 Format dữ liệu

### Quy tắc chung:

- Phân cách: Dấu **phẩy** (`,`)
- Encoding: **UTF-8**
- Ngày tháng: **yyyy-MM-dd** (năm-tháng-ngày)
- Giờ: **HH:mm** (24h)

### Ví dụ các file:

#### `data/Nhansu.txt`:

```
001,Nguyen Van A,0901234567,1
002,Tran Thi B,0912345678,2
```

#### `data/chamcong.txt`:

```
001,2024-01-15,08:00,17:00
001,2024-01-16,08:15,17:30
```

#### `data/congThang.txt`:

```
001,2024,1,22,176
002,2024,1,15,120
```

#### `data/phancong.txt`:

```
001,DA01,,2024-02-15,2024-01-01,2024-02-20
002,DA02,100000,2024-03-10,2024-02-01,2024-03-15
```

_Cột 3 (tiền thưởng) để trống → Tự tính_

---

## ⚠️ Lưu ý quan trọng

### 1. Tự động load dữ liệu:

- **Không cần** gọi `docFile()` thủ công
- Tất cả module tự load khi khởi tạo DataCenter
- App.java chỉ hiển thị trạng thái

### 2. Xử lý dữ liệu thiếu:

- Ngày tháng rỗng → Trả về `null`, không crash
- Tiền thưởng chưa tính → Hiển thị rỗng
- Phân công chưa hoàn thành → "Chưa hoàn thành"

### 3. Thứ tự thao tác đúng:

```
1. Thêm Nhân sự
2. Thêm Chức vụ → Gán chức vụ cho nhân sự
3. Thêm Đề án
4. Phân công nhân sự vào đề án
5. Chấm công
6. Tổng hợp công tháng
7. Tính lương
```

### 4. Backup dữ liệu:

- Định kỳ sao lưu thư mục `data/`
- Khi test, làm việc trên bản copy

### 5. Xóa dữ liệu:

- Xóa nhân sự → Sẽ còn dữ liệu "rác" ở các module khác
- Nên kiểm tra trước khi xóa

---

## 🐛 Xử lý lỗi thường gặp

### Lỗi: "Exception in PhanCong.parseDate()"

**Nguyên nhân:** File có ngày rỗng  
**Giải pháp:** Đã sửa, hệ thống xử lý null-safe

### Lỗi: "Mã NS không tồn tại"

**Nguyên nhân:** Nhập phân công/chấm công cho NS chưa tạo  
**Giải pháp:** Thêm nhân sú trước

### Lỗi: "FileNotFoundException"

**Nguyên nhân:** Thiếu file trong `data/`  
**Giải pháp:** Tạo file rỗng hoặc có dữ liệu mẫu

---

## 🎓 Tips sử dụng hiệu quả

1. **Nhập liệu tuần tự:** Phòng Ban, Chức vụ -> Nhân sự → Hồ sơ, Thân nhân → Chấm công → Tính lương
2. **Sử dụng mã NS nhất quán:** Đặt quy tắc (VD: 001, 002, NS001...)
3. **Kiểm tra dữ liệu:** Dùng chức năng "Xuất" để xem trước khi tính lương
4. **Tổng hợp định kỳ:** Cuối tháng chạy "Tổng hợp công tháng" và "Tính lương"
5. **Kiểm tra thưởng phân công:** Xem đánh giá để biết nhân sự hoàn thành sớm/trễ

---

## 📞 Hỗ trợ

Nếu gặp lỗi hoặc cần thêm tính năng:

1. Kiểm tra format dữ liệu trong file
2. Đọc phần "Xử lý lỗi thường gặp"
3. Kiểm tra console có thông báo lỗi gì
4. Liên hệ developer để hỗ trợ

---

## 📝 Changelog

### Version 2.0 (Current):

- ✅ Tách module Chấm công (Ngày/Tháng)
- ✅ Tự động load dữ liệu qua constructor
- ✅ DataCenter quản lý 12 module
- ✅ Null-safe date handling
- ✅ Tự động tính tiền thưởng phân công
- ✅ Tối ưu: Đọc từ memory thay vì file

### Version 1.0:

- Các chức năng cơ bản: NS, HSNS, Chấm công, Thưởng

---

### COMING SOON ( ĐẦY ĐỦ THỐNG KÊ, TÌM KIẾM )
