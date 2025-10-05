package NhanSu;

import java.util.Scanner;

public class DieuChinh {
	private Scanner sc = new Scanner(System.in);

	// Giao diện để chỉnh sửa nhân sự trong danh sách
	public void chinhSua(DanhSachNhanSu dsn) {
		System.out.print("Nhap maNS can chinh sua: ");
		String ma = sc.nextLine();
		Nhansu nv = dsn.timtheomaNS(ma);
		if (nv == null) {
			System.out.println("Khong tim thay nhan su voi ma: " + ma);
			return;
		}

		while (true) {
			System.out.println("\n--- Menu Chinh Sua (maNS=" + nv.getMaNS() + ") ---");
			System.out.println("1. Ho");
			System.out.println("2. Ten");
			System.out.println("3. Ngay sinh");
			System.out.println("4. Gioi tinh");
			System.out.println("5. Phu cap");
			System.out.println("6. Ma phong");
			System.out.println("7. Ma chuc vu");
			// subclass
			if (nv instanceof Nhansufull) {
				System.out.println("8. Luong co ban");
				System.out.println("9. Phu cap tham nien");
				System.out.println("0. Luu & Thoat");
			} else if (nv instanceof Nhansupart) {
				System.out.println("8. Tien cong gio");
				System.out.println("0. Luu & Thoat");
			} else {
				System.out.println("0. Luu & Thoat");
			}

			System.out.print("Chon: ");
			String line = sc.nextLine();
			int chon;
			try {
				chon = Integer.parseInt(line);
			} catch (NumberFormatException e) {
				System.out.println("Nhap khong hop le, vui long nhap so.");
				continue;
			}

			if (chon == 0) {
				System.out.println("Da luu thay doi.");
				break;
			}

			switch (chon) {
				case 1:
					System.out.print("Nhap ho moi: ");
					nv.setHo(sc.nextLine());
					break;
				case 2:
					System.out.print("Nhap ten moi: ");
					nv.setTen(sc.nextLine());
					break;
				case 3:
					System.out.print("Nhap ngay sinh moi (dd/mm/yyyy): ");
					nv.setNgaySinh(sc.nextLine());
					break;
				case 4:
					System.out.print("Nhap gioi tinh moi: ");
					nv.setGioitinh(sc.nextLine());
					break;
				case 5:
					System.out.print("Nhap phu cap moi: ");
					try { nv.setPhucap(Float.parseFloat(sc.nextLine())); } catch (NumberFormatException ex) { System.out.println("Gia tri phu cap khong hop le."); }
					break;
				case 6:
					System.out.print("Nhap ma phong moi: ");
					nv.setMaPhong(sc.nextLine());
					break;
				case 7:
					System.out.print("Nhap ma chuc vu moi: ");
					nv.setMachucvu(sc.nextLine());
					break;
				case 8:
					if (nv instanceof Nhansufull) {
						Nhansufull nf = (Nhansufull) nv;
						System.out.print("Nhap luong co ban moi: ");
						try { nf.setluongcb(Float.parseFloat(sc.nextLine())); } catch (NumberFormatException ex) { System.out.println("Gia tri khong hop le."); }
					} else if (nv instanceof Nhansupart) {
						Nhansupart np = (Nhansupart) nv;
						System.out.print("Nhap tien cong gio moi: ");
						try { np.settienconggio(Float.parseFloat(sc.nextLine())); } catch (NumberFormatException ex) { System.out.println("Gia tri khong hop le."); }
					} else {
						System.out.println("Lua chon khong hop le.");
					}
					break;
				case 9:
					if (nv instanceof Nhansufull) {
						Nhansufull nf2 = (Nhansufull) nv;
						System.out.print("Nhap phu cap tham nien moi: ");
						try { nf2.setphucapthamnien(Float.parseFloat(sc.nextLine())); } catch (NumberFormatException ex) { System.out.println("Gia tri khong hop le."); }
					} else {
						System.out.println("Lua chon khong hop le.");
					}
					break;
				default:
					System.out.println("Lua chon khong hop le.");
			}
		}
	}
}
