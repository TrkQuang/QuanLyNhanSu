package ChamCong;
public enum DanhSachTrangThai {
    DILAM(false),
    NGHIKOLUONG(true),
    NGHICOLUONG(true);

    private boolean laNghi; //check trang thai nghi

    DanhSachTrangThai(boolean laNghi) {
        this.laNghi = laNghi;
    }

    public boolean isNghi() {
        return laNghi;
    }
}