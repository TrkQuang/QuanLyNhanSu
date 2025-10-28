# HỆ THỐNG QUẢN LÝ NHÂN SỰ TỔNG HỢP

## 🎯 Tổng quan

Hệ thống quản lý nhân sự đầy đủ với 6 module tích hợp:

- 👥 **Nhân sự** - Quản lý thông tin nhân viên
- 📋 **Hồ sơ Nhân sự** - Chi tiết nhân sự, chức vụ, thân nhân
- ⏰ **Chấm công** - Chấm công ngày, tổng hợp tháng
- 🎁 **Thưởng** - Quản lý danh mục thưởng và chi tiết thưởng
- 🏢 **Phòng ban & Dự án** - Phòng ban, đề án, phân công
- 💰 **Lương** - Tính lương tự động từ công + thưởng + dự án

---

## 🚀 Cách chạy

### 1. Chạy từ terminal:

```bash
cd src
javac -d ../bin -cp ../bin QuanLyTongHop.java
java -cp ../bin QuanLyTongHop
```

### 2. Chạy từ IDE:

- Mở file `QuanLyTongHop.java`
- Run as Java Application

---

## 📋 Menu chính

```
╔═══════════════════════════════════════════════╗
║          MENU QUẢN LÝ NHÂN SỰ CHÍNH          ║
╠═══════════════════════════════════════════════╣
║  1. 👥 Quản lý Nhân sự                       ║
║  2. 📋 Quản lý Hồ sơ Nhân sự                 ║
║  3. ⏰ Quản lý Chấm công                      ║
║  4. 🎁 Quản lý Thưởng                         ║
║  5. 🏢 Quản lý Phòng ban & Dự án             ║
║  6. 💰 Quản lý Lương                          ║
║  7. 📊 Thống kê Tổng quan                    ║
║  8. 💾 Lưu tất cả dữ liệu                    ║
║  0. 🚪 Thoát (tự động lưu)                   ║
╚═══════════════════════════════════════════════╝
```

---

## 🔄 Quy trình làm việc

### 1️⃣ Khởi đầu - Nhập dữ liệu cơ bản

**Bước 1: Nhập Chức vụ** (Menu 2 - Hồ sơ NS)

```
Ví dụ:
- Mã CV: GD01 | Tên: Giám đốc | Hệ số lương: 3.5
- Mã CV: TP01 | Tên: Trưởng phòng | Hệ số lương: 2.5
- Mã CV: NV01 | Tên: Nhân viên | Hệ số lương: 1.0
```

**Bước 2: Nhập Nhân sự** (Menu 1 - Nhân sự)

```
- Nhập thông tin nhân viên
- Gán mã chức vụ cho từng nhân viên
```

**Bước 3: Nhập Phòng ban** (Menu 5 - Phòng ban)

```
- Tạo các phòng ban
- Tạo đề án
```

### 2️⃣ Hoạt động hàng ngày

**Chấm công** (Menu 3)

```
- Chấm công từng ngày cho nhân sự
- Tổng hợp công tháng (tự động)
```

**Thưởng** (Menu 4)

```
- Tạo danh mục thưởng
- Gán thưởng cho nhân sú
```

**Phân công dự án** (Menu 5)

```
- Phân công nhân sự vào đề án
- Theo dõi tiến độ và thưởng dự án
```

### 3️⃣ Cuối tháng - Tính lương

**Tính lương** (Menu 6)

```
1. Chọn "Tính lương tất cả NS (tháng)"
2. Nhập tháng/năm cần tính
3. Hệ thống tự động:
   ✅ Lấy công từ chấm công
   ✅ Tính lương cơ bản × hệ số chức vụ
   ✅ Cộng thưởng từ module Thưởng
   ✅ Cộng thưởng từ dự án đã hoàn thành
   ✅ Tạo bảng lương chi tiết
4. Lưu dữ liệu lương
```

---

## 📊 Cách tính lương

```
┌────────────────────────────────────────────────┐
│ CÔNG THỨC TÍNH LƯƠNG                          │
├────────────────────────────────────────────────┤
│ 1. Lương cơ bản:                               │
│    - Full-time: Ngày làm × 500,000 VNĐ         │
│    - Part-time: Giờ làm × 50,000 VNĐ           │
│                                                │
│ 2. Hệ số lương: Từ chức vụ                    │
│                                                │
│ 3. Lương sau hệ số:                            │
│    = Lương cơ bản × Hệ số lương                │
│                                                │
│ 4. Thưởng module: Từ QUANLYTHUONG             │
│                                                │
│ 5. Thưởng dự án: Từ PhanCong                  │
│    - Hoàn thành sớm: Ngày sớm × 200k           │
│    - Đúng hạn: 200k                            │
│    - Trễ hạn: 0đ                               │
│                                                │
│ 💰 TỔNG = (3) + (4) + (5)                     │
└────────────────────────────────────────────────┘
```

---

## 🎨 Tính năng nổi bật

### ✅ Tích hợp đầy đủ

- Tất cả module kết nối qua **DataCenter**
- Dữ liệu đồng bộ giữa các module
- Validation cross-module

### ✅ Tự động hóa

- Tính lương tự động từ 3 nguồn
- Tổng hợp công tháng tự động
- Tính thưởng dự án tự động

### ✅ Validation mạnh mẽ

- Kiểm tra mã nhân sự tồn tại
- Kiểm tra mã chức vụ hợp lệ
- Kiểm tra phòng ban tồn tại
- Kiểm tra đề án tồn tại

### ✅ Escape-friendly

- Nhập '0' để hủy tại bất kỳ bước nào
- Không bị stuck trong validation loop

### ✅ File I/O hoàn chỉnh

- Auto-load khi khởi động
- Auto-save khi thoát
- Manual save bất kỳ lúc nào

### ✅ Hệ số lương linh hoạt

- Hệ số theo chức vụ
- Dễ dàng điều chỉnh
- Tự động áp dụng khi tính lương

---

## 📁 Cấu trúc dữ liệu

```
data/
├── Nhansu.txt              # Danh sách nhân sự
├── quanlyhsnhansu.txt      # Chi tiết nhân sự & thân nhân
├── chucvu.txt              # Danh sách chức vụ & hệ số lương
├── chamcong.txt            # Chấm công ngày
├── congThang.txt           # Tổng hợp công tháng
├── danhMucThuong.txt       # Danh mục thưởng
├── chiTietThuong.txt       # Chi tiết thưởng của NS
├── phongban.txt            # Danh sách phòng ban
├── dean.txt                # Danh sách đề án
├── phancong.txt            # Phân công NS vào dự án
└── luong.txt               # Bảng lương tháng
```

---

## 🔧 Troubleshooting

### Lỗi: "Không tìm thấy file"

✅ **Giải pháp**: Tạo thư mục `data/` trong root project

### Lỗi: "Không tính được lương"

✅ **Kiểm tra**:

1. Đã tổng hợp công tháng chưa? (Menu 3)
2. Nhân sự có mã chức vụ chưa? (Menu 1)
3. Chức vụ có hệ số lương chưa? (Menu 2)

### Lỗi: "Validation thất bại"

✅ **Kiểm tra**:

1. Đã tạo dữ liệu master chưa? (chức vụ, phòng ban, đề án)
2. Đã load dữ liệu khi khởi động chưa?
3. Mã nhập vào có đúng format không?

---

## 📚 Module chi tiết

### 1. Module Nhân sự

- Thêm/sửa/xóa nhân sự
- Quản lý full-time và part-time
- Gán chức vụ cho nhân viên

### 2. Module Hồ sơ

- Chi tiết nhân sự với nhiều thân nhân
- Quản lý chức vụ và hệ số lương
- Xuất báo cáo hồ sơ

### 3. Module Chấm công

- Chấm công theo ngày
- Tự động tổng hợp tháng
- Hỗ trợ nhiều trạng thái (đi làm, nghỉ phép, nghỉ không lương...)

### 4. Module Thưởng

- Danh mục thưởng
- Chi tiết thưởng cho từng nhân sự
- 1 chi tiết thưởng có nhiều danh mục thưởng

### 5. Module Phòng ban

- Quản lý phòng ban
- Quản lý đề án
- Phân công nhân sự
- Tự động tính thưởng dự án theo tiến độ

### 6. Module Lương

- Tính lương tự động
- Hỗ trợ hệ số lương chức vụ
- Tích hợp 3 nguồn: Công + Thưởng + Dự án
- Xuất bảng lương chi tiết

---

## 🎯 Best Practices

### 1. Workflow chuẩn

```
1. Nhập chức vụ (có hệ số lương)
2. Nhập nhân sự (gán chức vụ)
3. Nhập phòng ban & đề án
4. Chấm công hàng ngày
5. Thưởng theo tháng
6. Phân công dự án
7. Cuối tháng: Tổng hợp công + Tính lương
```

### 2. Data integrity

- Load dữ liệu khi khởi động
- Save thường xuyên
- Kiểm tra validation
- Backup định kỳ

### 3. Performance

- Module độc lập
- DataCenter làm cache
- File I/O tối ưu

---

## 👥 Phát triển bởi

**QuanLyNhanSu Team**

- Version: 2.0
- Last updated: 2025-10-29

---

## 📝 Changelog

### v2.0 (2025-10-29)

- ➕ Module Lương với hệ số chức vụ
- ➕ Menu tổng hợp QuanLyTongHop
- ✨ Tích hợp 6 module hoàn chỉnh
- ✨ Auto-load/save dữ liệu
- ✨ Thống kê tổng quan

### v1.0

- ✅ Module Nhân sự
- ✅ Module Hồ sơ
- ✅ Module Chấm công
- ✅ Module Thưởng
- ✅ Module Phòng ban
- ✅ DataCenter pattern

---

**🎉 Hệ thống sẵn sàng sử dụng!**
