?--================================== TRIGGER ===================================================
-- Ki?m tra tu?i khách hàng ph?i trên 18 tu?i
CREATE OR REPLACE TRIGGER Check_TuoiKhachHang
BEFORE INSERT ON KHACHHANG
FOR EACH ROW
DECLARE
    customer_age NUMBER;
BEGIN
    -- Tính tu?i c?a khách hàng t? ngày sinh
    customer_age := TRUNC(MONTHS_BETWEEN(SYSDATE, :NEW.NgaySinh) / 12);

    -- Ki?m tra tu?i c?a khách hàng
    IF customer_age < 18 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Khách hàng ph?i d? 18 tu?i tr? lên.');
    END IF;
END;
/

-- Ki?m tra gi?i tính khách hàng ch? nam ho?c n?
CREATE OR REPLACE TRIGGER Check_gioi_tinh_KH
BEFORE INSERT OR UPDATE ON KHACHHANG
FOR EACH ROW
BEGIN
    IF (:NEW.GioiTinh != 'Nam' AND :NEW.GioiTinh != 'N?') THEN
        RAISE_APPLICATION_ERROR(-20001, 'Gi?i tính ch? có th? là "Nam" ho?c "N?".');
    END IF;
END;
/


-- Ki?m tra th?i gian tr? phòng ph?i sau th?i gian nh?n phòng
CREATE OR REPLACE TRIGGER Check_ThoiGianLapHopDong
BEFORE INSERT OR UPDATE ON HOPDONG
FOR EACH ROW
BEGIN
  IF :NEW.TGTraPhong <= :NEW.TGNhanPhong THEN
    RAISE_APPLICATION_ERROR(-20001, 'Th?i gian tr? phòng ph?i sau th?i gian nh?n phòng.');
  END IF;
END;
/


-- Ki?m tra Lo?i phòng ch? du?c VIP ho?c Thu?ng
CREATE OR REPLACE TRIGGER Check_LoaiPhong
BEFORE INSERT OR UPDATE ON PHONG
FOR EACH ROW
BEGIN
    IF (:NEW.LoaiPhong != 'VIP' AND :NEW.LoaiPhong != 'Thu?ng') THEN
        RAISE_APPLICATION_ERROR(-20001, 'Lo?i phòng ch? có th? là "VIP" ho?c "Thu?ng".');
    END IF;
END;
/

-- Ki?m tra Ki?u phòng ch? du?c Ðôi ho?c Ðon
CREATE OR REPLACE TRIGGER Check_KieuPhong
BEFORE INSERT OR UPDATE ON PHONG
FOR EACH ROW
BEGIN
    IF (:NEW.KieuPhong != 'Ðôi' AND :NEW.KieuPhong != 'Ðon') THEN
        RAISE_APPLICATION_ERROR(-20001, 'Ki?u phòng ch? có th? là Ðôi ho?c Ðon.');
    END IF;
END;
/

-- Ki?m tra ngày l?p hóa don ph?i sau ngày nh?n phòng
CREATE OR REPLACE TRIGGER Check_NgayLapHD 
BEFORE INSERT OR UPDATE ON HOADON
FOR EACH ROW
BEGIN
    IF (:NEW.NgayLapHD <= (SELECT TGNhanPhong FROM HOPDONG WHERE MaHopDong = :NEW.MaHopDong)) THEN
        RAISE_APPLICATION_ERROR(-20001, 'Ngày l?p hóa don ph?i sau ngày nh?n phòng.');
    END IF;
END;
/

-- Ki?m tra ngày K?t thúc khuy?n mãi ph?i sau ngày l?p khuy?n mãi
CREATE OR REPLACE TRIGGER Check_ThoiGianKM 
BEFORE INSERT OR UPDATE ON KHUYENMAI
FOR EACH ROW
BEGIN
  IF :NEW.NGAYKETTHUC <= :NEW.NGAYBATDAU THEN
    RAISE_APPLICATION_ERROR(-20001, 'Th?i gian k?t thúc ph?i sau th?i gian b?t d?u khuy?n mãi.');
  END IF;
END;
/

-- Ki?m tra nhân viên ch? có 3 lo?i NV 
CREATE OR REPLACE TRIGGER Check_LoaiNhanVien 
BEFORE INSERT OR UPDATE ON NHANVIEN
FOR EACH ROW
BEGIN
    IF (:NEW.LOAINV != 'L? tân' AND :NEW.LOAINV != 'T?p V?' AND :NEW.LOAINV != 'Qu?n lý' ) THEN
        RAISE_APPLICATION_ERROR(-20001, 'Lo?i phòng ch? có th? là "L? tân" ho?c "T?p V?" ho?c "Qu?n lý".');
    END IF;
END;
/

-- Ki?m tra s? lu?ng h?ng ph?i nh? hon ho?c b?ng s? lu?ng trang b?
CREATE OR REPLACE TRIGGER Check_SoLuongHong 
BEFORE INSERT OR UPDATE ON TRANGBI
FOR EACH ROW
BEGIN
    IF :NEW.SLHong > :NEW.SoLuong THEN
        RAISE_APPLICATION_ERROR(-20001, 'S? lu?ng h?ng c?a trang b? ph?i nh? hon ho?c b?ng s? lu?ng t?ng trang b?.');
    END IF;
END;
/


-- Thuê phòng qua app s? có hình th?c thuê là "Ngày" và tình tr?ng m?c d?nh là "Chua xác nh?n"
CREATE OR REPLACE TRIGGER Check_taohopdongonline 
AFTER INSERT ON HOPDONG
FOR EACH ROW
BEGIN
    IF (:NEW.HinhThucThue = 'Ngày') THEN
        UPDATE HOPDONG
        SET TinhTrangHD = 'Chua xác nh?n'
        WHERE MaHopDong = :NEW.MaHopDong;
    END IF;
END;
/

-- Ki?m tra thông tin khách hàng nh?p qua app ph?i h?p l?
CREATE OR REPLACE TRIGGER Check_thongtinkhonline 
BEFORE INSERT ON KHACHHANG
FOR EACH ROW
BEGIN
    IF LENGTH(:NEW.CCCD) != 12 THEN
        RAISE_APPLICATION_ERROR(-20002, 'S? CCCD ph?i có dúng 12 ký t?.');
    END IF;
    IF NOT REGEXP_LIKE(:NEW.CCCD, '^[0-9]{12}$') THEN
        RAISE_APPLICATION_ERROR(-20003, 'S? CCCD ph?i là chu?i s?.');
    END IF;
    IF LENGTH(:NEW.SDT) != 10 THEN
        RAISE_APPLICATION_ERROR(-20007, 'S? di?n tho?i ph?i có dúng 10 ký t?.');
    END IF;
    IF NOT REGEXP_LIKE(:NEW.SDT, '^[0-9]{10}$') THEN
        RAISE_APPLICATION_ERROR(-20008, 'S? di?n tho?i ph?i là chu?i s?.');
    END IF;
END;
/


-- ki?m tra phòng tr?ng 
CREATE OR REPLACE TRIGGER trg_check_room_availability
BEFORE INSERT ON CHITIETDATPHONG
FOR EACH ROW
DECLARE
  v_count INTEGER;
BEGIN
  SELECT COUNT(*)
  INTO v_count
  FROM CHITIETDATPHONG ctdp
  JOIN HOPDONG hd ON ctdp.MaHopDong = hd.MaHopDong
  WHERE ctdp.MaPhong = :NEW.MaPhong
    AND hd.TGNhanPhong < (SELECT TGTraPhong FROM HOPDONG WHERE MaHopDong = :NEW.MaHopDong)
    AND hd.TGTraPhong > (SELECT TGNhanPhong FROM HOPDONG WHERE MaHopDong = :NEW.MaHopDong);

  IF v_count > 0 THEN
    RAISE_APPLICATION_ERROR(-20001, 'Phòng dã du?c d?t.');
  END IF;
END;
/


-- không cho c?p nh?t l?i thông tin h?p d?ng có tình tr?ng là "Ðã thanh toán"
CREATE OR REPLACE TRIGGER Checked_capnhat_TinhTrangHD
BEFORE UPDATE ON HOPDONG
FOR EACH ROW
BEGIN
    IF :OLD.TinhTrangHD = 'Ðã thanh toán' THEN
        RAISE_APPLICATION_ERROR(-20001, 'Không th? c?p nh?t tình tr?ng h?p d?ng dã thanh toán.');
    END IF;
END;
/

