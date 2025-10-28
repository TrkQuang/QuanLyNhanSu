# TÍNH LƯƠNG VỚI HỆ SỐ CHỨC VỤ

## ✅ Đã cập nhật

### 1. Class `Luong.java`

**Thêm thuộc tính:**

- `heSoLuong`: Hệ số lương từ chức vụ (mặc định = 1.0)
- `luongSauHeSo`: Lương cơ bản × hệ số lương

**Thêm methods:**

- `layHeSoLuongTuChucVu()`: Tự động lấy hệ số từ chức vụ của nhân sự
- `tinhLuongSauHeSo()`: Tính lương = lương cơ bản × hệ số
- `getHeSoLuong()`, `getLuongSauHeSo()`: Getters

**Cập nhật:**

- `tinhTongLuong()`: Giờ tính = luongSauHeSo + thưởng (thay vì luongCoBan + thưởng)
- `xuat()`: Hiển thị thêm hệ số lương và lương sau hệ số
- `toFileString()`: Lưu thêm hệ số lương
- `fromFileString()`: Đọc thêm hệ số lương

### 2. Class `QLHSNS.java`

**Thêm method:**

- `timChucVu(String maCV)`: Tìm và trả về CHUCVU object theo mã

### 3. Class `QuanLyLuong.java`

**Cập nhật `tinhLuongTuDong()`:**

```java
// 1. Tính lương cơ bản từ công
luong.tinhLuongCoBan();

// 2. Lấy hệ số lương từ chức vụ
luong.layHeSoLuongTuChucVu();

// 3. Tính lương sau hệ số (cơ bản × hệ số)
luong.tinhLuongSauHeSo();

// 4. Lấy thưởng module
// 5. Lấy thưởng dự án
// 6. Tính tổng = lương sau hệ số + thưởng
```

---

## 💡 Cách hoạt động

### Công thức mới:

```
Lương cơ bản = (Số ngày làm × 500k) HOẶC (Số giờ làm × 50k)
Hệ số lương = Lấy từ chức vụ của nhân sự
Lương sau hệ số = Lương cơ bản × Hệ số lương

TỔNG LƯƠNG = Lương sau hệ số + Thưởng module + Thưởng dự án
```

### Ví dụ:

**Nhân viên A:**

- Chức vụ: Trưởng phòng (hệ số = 2.5)
- Ngày làm: 22 ngày
- Lương cơ bản: 22 × 500,000 = 11,000,000 VNĐ
- **Lương sau hệ số: 11,000,000 × 2.5 = 27,500,000 VNĐ** ✨
- Thưởng module: 500,000 VNĐ
- Thưởng dự án: 1,000,000 VNĐ
- **TỔNG: 29,000,000 VNĐ**

**Nhân viên B:**

- Chức vụ: Nhân viên (hệ số = 1.0)
- Ngày làm: 22 ngày
- Lương cơ bản: 22 × 500,000 = 11,000,000 VNĐ
- **Lương sau hệ số: 11,000,000 × 1.0 = 11,000,000 VNĐ**
- Thưởng module: 200,000 VNĐ
- Thưởng dự án: 0 VNĐ
- **TỔNG: 11,200,000 VNĐ**

---

## 📊 Bảng lương mới

```
========== BẢNG LƯƠNG ==========
Mã nhân sự: 001
Tháng/Năm: 10/2025
--------------------------------
Công việc:
  - Tổng ngày làm: 22.0 ngày
  - Tổng giờ làm: 0.0 giờ
  - Lương cơ bản: 11,000,000 VNĐ
  - Hệ số lương: x2.50           ← MỚI
  - Lương sau hệ số: 27,500,000 VNĐ  ← MỚI
--------------------------------
Thưởng:
  - Thưởng module: 500,000 VNĐ
  - Thưởng dự án: 1,000,000 VNĐ
================================
💰 TỔNG LƯƠNG: 29,000,000 VNĐ
================================
```

---

## 🔧 Xử lý đặc biệt

### 1. Nhân sự chưa có chức vụ

- Hệ số lương = 1.0 (mặc định)
- In warning: "⚠️ Nhân sự XXX chưa có chức vụ"

### 2. Chức vụ không tồn tại

- Hệ số lương = 1.0 (mặc định)
- In warning: "⚠️ Không tìm thấy chức vụ XXX"

### 3. Không tìm thấy nhân sự

- Hệ số lương = 1.0 (mặc định)
- In warning: "⚠️ Không tìm thấy nhân sự XXX"

---

## 📁 Format file mới

`data/luong.txt`:

```
maNS,thang,nam,tongNgayLam,tongGioLam,luongCoBan,heSoLuong,thuongModule,thuongDuAn,tongLuong
001,10,2025,22.0,0.0,11000000.0,2.5,500000.0,1000000.0,29000000.0
002,10,2025,20.0,0.0,10000000.0,1.0,200000.0,0.0,10200000.0
```

**Cột thêm:** `heSoLuong` (vị trí thứ 7)

---

## 🎯 Lợi ích

✅ **Công bằng theo cấp bậc**: Lương phản ánh đúng vị trí chức vụ
✅ **Tự động**: Không cần nhập hệ số, lấy từ chức vụ
✅ **Linh hoạt**: Dễ điều chỉnh hệ số trong module HSNS
✅ **Minh bạch**: Hiển thị chi tiết hệ số và lương sau hệ số

---

## ⚙️ Các hệ số mẫu

Có thể cấu hình trong module **Quản lý Hồ sơ nhân sự (HSNS)**:

| Chức vụ      | Hệ số lương |
| ------------ | ----------- |
| Giám đốc     | 3.5         |
| Phó Giám đốc | 3.0         |
| Trưởng phòng | 2.5         |
| Phó phòng    | 2.0         |
| Trưởng nhóm  | 1.5         |
| Nhân viên    | 1.0         |

---

## 🚀 Cách sử dụng

**Không thay đổi!** Module tự động:

1. Tính lương cơ bản từ công
2. Lấy hệ số từ chức vụ
3. Nhân hệ số vào lương cơ bản
4. Cộng thưởng
5. Hiển thị chi tiết

Chỉ cần đảm bảo:

- Nhân sự có mã chức vụ (trong `Nhansu.java`)
- Chức vụ có hệ số lương (trong `CHUCVU.java`)

---

**Updated**: 2025-10-29  
**Version**: 2.0 - Thêm hệ số lương chức vụ
