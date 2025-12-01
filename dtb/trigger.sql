--================================== TRIGGER ===================================================
-- Kiểm tra tuổi khách hàng phải trên 18 tuổi
CREATE OR REPLACE TRIGGER Check_TuoiKhachHang
BEFORE INSERT ON KHACHHANG
FOR EACH ROW
DECLARE
    customer_age NUMBER;
BEGIN
    -- Tính tuổi của khách hàng từ ngày sinh
    customer_age := TRUNC(MONTHS_BETWEEN(SYSDATE, :NEW.NgaySinh) / 12);

    -- Kiểm tra tuổi của khách hàng
    IF customer_age < 18 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Khách hàng phải đủ 18 tuổi trở lên.');
    END IF;
END;
/

-- Kiểm tra giới tính khách hàng chỉ nam hoặc nữ
CREATE OR REPLACE TRIGGER Check_gioi_tinh_KH
BEFORE INSERT OR UPDATE ON KHACHHANG
FOR EACH ROW
BEGIN
    IF (:NEW.GioiTinh != 'Nam' AND :NEW.GioiTinh != 'Nữ') THEN
        RAISE_APPLICATION_ERROR(-20001, 'Giới tính chỉ có thể là "Nam" hoặc "Nữ".');
    END IF;
END;
/


-- Kiểm tra thời gian trả phòng phải sau thời gian nhận phòng
CREATE OR REPLACE TRIGGER Check_ThoiGianLapHopDong
BEFORE INSERT OR UPDATE ON HOPDONG
FOR EACH ROW
BEGIN
  IF :NEW.TGTraPhong <= :NEW.TGNhanPhong THEN
    RAISE_APPLICATION_ERROR(-20001, 'Thời gian trả phòng phải sau thời gian nhận phòng.');
  END IF;
END;
/


-- Kiểm tra Loại phòng chỉ được VIP hoặc Thường
CREATE OR REPLACE TRIGGER Check_LoaiPhong
BEFORE INSERT OR UPDATE ON PHONG
FOR EACH ROW
BEGIN
    IF (:NEW.LoaiPhong != 'VIP' AND :NEW.LoaiPhong != 'Thường') THEN
        RAISE_APPLICATION_ERROR(-20001, 'Loại phòng chỉ có thể là "VIP" hoặc "Thường".');
    END IF;
END;
/

-- Kiểm tra Kiểu phòng chỉ được Đôi hoặc Đơn
CREATE OR REPLACE TRIGGER Check_KieuPhong
BEFORE INSERT OR UPDATE ON PHONG
FOR EACH ROW
BEGIN
    IF (:NEW.KieuPhong != 'Đôi' AND :NEW.KieuPhong != 'Đơn') THEN
        RAISE_APPLICATION_ERROR(-20001, 'Kiểu phòng chỉ có thể là Đôi hoặc Đơn.');
    END IF;
END;
/

-- Kiểm tra ngày lập hóa đơn phải sau ngày nhận phòng
CREATE OR REPLACE TRIGGER Check_NgayLapHD 
BEFORE INSERT OR UPDATE ON HOADON
FOR EACH ROW
BEGIN
    IF (:NEW.NgayLapHD <= (SELECT TGNhanPhong FROM HOPDONG WHERE MaHopDong = :NEW.MaHopDong)) THEN
        RAISE_APPLICATION_ERROR(-20001, 'Ngày lập hóa đơn phải sau ngày nhận phòng.');
    END IF;
END;
/

-- Kiểm tra ngày Kết thúc khuyến mãi phải sau ngày lập khuyến mãi
CREATE OR REPLACE TRIGGER Check_ThoiGianKM 
BEFORE INSERT OR UPDATE ON KHUYENMAI
FOR EACH ROW
BEGIN
  IF :NEW.NGAYKETTHUC <= :NEW.NGAYBATDAU THEN
    RAISE_APPLICATION_ERROR(-20001, 'Thời gian kết thúc phải sau thời gian bắt đầu khuyến mãi.');
  END IF;
END;
/

-- Kiểm tra nhân viên chỉ có 3 loại NV 
CREATE OR REPLACE TRIGGER Check_LoaiNhanVien 
BEFORE INSERT OR UPDATE ON NHANVIEN
FOR EACH ROW
BEGIN
    IF (:NEW.LOAINV != 'Lễ tân' AND :NEW.LOAINV != 'Tạp Vụ' AND :NEW.LOAINV != 'Quản lý' ) THEN
        RAISE_APPLICATION_ERROR(-20001, 'Loại phòng chỉ có thể là "Lễ tân" hoặc "Tạp Vụ" hoặc "Quản lý".');
    END IF;
END;
/

-- Kiểm tra số lượng hỏng phải nhỏ hơn hoặc bằng số lượng trang bị
CREATE OR REPLACE TRIGGER Check_SoLuongHong 
BEFORE INSERT OR UPDATE ON TRANGBI
FOR EACH ROW
BEGIN
    IF :NEW.SoLuongHong > :NEW.SoLuong THEN
        RAISE_APPLICATION_ERROR(-20001, 'Số lượng hỏng của trang bị phải nhỏ hơn hoặc bằng số lượng tổng trang bị.');
    END IF;
END;
/


-- Thuê phòng qua app sẽ có hình thức thuê là "Ngày" và tình trạng mặc định là "Chưa xác nhận"
CREATE OR REPLACE TRIGGER Check_taohopdongonline 
AFTER INSERT ON HOPDONG
FOR EACH ROW
BEGIN
    IF (:NEW.HinhThucThue = 'Ngày') THEN
        UPDATE HOPDONG
        SET TinhTrangHD = 'Chưa xác nhận'
        WHERE MaHopDong = :NEW.MaHopDong;
    END IF;
END;
/

-- Kiểm tra thông tin khách hàng nhập qua app phải hợp lệ
CREATE OR REPLACE TRIGGER Check_thongtinkhonline 
BEFORE INSERT ON KHACHHANG
FOR EACH ROW
BEGIN
    IF LENGTH(:NEW.CCCD) != 12 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Số CCCD phải có đúng 12 ký tự.');
    END IF;
    IF NOT REGEXP_LIKE(:NEW.CCCD, '^[0-9]{12}$') THEN
        RAISE_APPLICATION_ERROR(-20003, 'Số CCCD phải là chuỗi số.');
    END IF;
    IF LENGTH(:NEW.SDT) != 10 THEN
        RAISE_APPLICATION_ERROR(-20007, 'Số điện thoại phải có đúng 10 ký tự.');
    END IF;
    IF NOT REGEXP_LIKE(:NEW.SDT, '^[0-9]{10}$') THEN
        RAISE_APPLICATION_ERROR(-20008, 'Số điện thoại phải là chuỗi số.');
    END IF;
END;
/

-- kiểm tra tại cùng 1 thời điểm chỉ thuê được 1 phòng
CREATE OR REPLACE TRIGGER Check_PhongDatDuocMotHopDong
BEFORE INSERT ON HOPDONG
FOR EACH ROW
DECLARE
    v_count NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_count
    FROM CHITIETDATPHONG
    WHERE MaPhong = :NEW.MaPhong
    AND (TGNhanPhong BETWEEN :NEW.TGNhanPhong AND :NEW.TGTraPhong
        OR TGTraPhong BETWEEN :NEW.TGNhanPhong AND :NEW.TGTraPhong);

    IF v_count > 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Phòng đã được đặt trong một hợp đồng khác trong khoảng thời gian này.');
    END IF;
END;
/

