--======================================== FUNCTION ======================================
--FUNCTION1
--Tính thời gian thuê phòng theo giờ
CREATE OR REPLACE FUNCTION Tinh_TGthuephongtheogio (
  f_MaHopDong NUMBER,
  f_TGNhanPhong TIMESTAMP,
  f_TGTraPhong TIMESTAMP
) RETURN NUMBER IS
  v_hours NUMBER;
BEGIN

  v_hours := EXTRACT(HOUR FROM (f_TGTraPhong - f_TGNhanPhong)) 
             + EXTRACT(DAY FROM (f_TGTraPhong - f_TGNhanPhong)) * 24 
             + EXTRACT(MINUTE FROM (f_TGTraPhong - f_TGNhanPhong)) / 60
             + EXTRACT(SECOND FROM (f_TGTraPhong - f_TGNhanPhong)) / 3600;
  
  RETURN v_hours;
END;
/

--FUNCTION2
--Tính thời gian thuê phòng theo ngày
CREATE OR REPLACE FUNCTION Tinh_TGthuephongtheongay (
  f_MaHopDong NUMBER,
  f_TGNhanPhong TIMESTAMP,
  f_TGTraPhong TIMESTAMP
) RETURN NUMBER IS
  v_days NUMBER;
BEGIN
  
  v_days := TRUNC(f_TGTraPhong) - TRUNC(f_TGNhanPhong);
  
  IF f_TGTraPhong > f_TGNhanPhong + v_days THEN
    v_days := v_days + 1;
  END IF;
  
  RETURN v_days;
END;
/


--FUNCTION3
--Tính trị giá hợp đồng (số tiền khách hàng phải cọc để xác nhận hợp đồng đặt phòng)
CREATE OR REPLACE FUNCTION Tinh_TriGiaHopDong (
  f_MaHopDong NUMBER
) RETURN NUMBER IS
  v_TriGia NUMBER := 0;  -- Khởi tạo trị giá hợp đồng
BEGIN
  -- Tính tổng tiền phòng của hợp đồng
  SELECT SUM(P.Gia * Tinh_TGthuephongtheongay(HD.MaHopDong, HD.TGNhanPhong, HD.TGTraPhong))
  INTO v_TriGia
  FROM HOPDONG HD
  INNER JOIN CHITIETDATPHONG CDP ON HD.MaHopDong = CDP.MaHopDong
  INNER JOIN PHONG P ON CDP.MaPhong = P.MaPhong
  WHERE HD.MaHopDong = f_MaHopDong;
  
  -- Tính trị giá hợp đồng (30% tổng tiền phòng)
  v_TriGia := v_TriGia * 0.3;
  
  RETURN v_TriGia;
END;
/
DECLARE
  v_MaHopDong NUMBER := 1;
  v_TriGiaHopDong NUMBER;
BEGIN
  v_TriGiaHopDong := Tinh_TriGiaHopDong(v_MaHopDong);
  DBMS_OUTPUT.PUT_LINE('Tri Gia Hop Dong: ' || v_TriGiaHopDong);
END;
/


--FUNCTION4
--Tính tiền khách bồi thường do làm hỏng trang bị
CREATE OR REPLACE FUNCTION Tinh_TienBoiThuong (
  f_MaTB NUMBER, 
  f_SoLuongBiHong NUMBER
) RETURN NUMBER IS
  v_TienBoiThuong NUMBER := 0;  -- Khởi tạo tổng tiền bồi thường
BEGIN
  -- Lấy giá của trang bị từ bảng TRANGBI
  SELECT GiaTB * f_SoLuongBiHong
  INTO v_TienBoiThuong
  FROM TRANGBI
  WHERE MaTB = f_MaTB;

  RETURN v_TienBoiThuong;
END;
/
--Tính tiền khách phải bồi thường nếu làm hỏng 2 cái Tivi (MaTB=1, SoLuongBiHong=2)
DECLARE
  v_TienBoiThuong NUMBER :=0;
BEGIN
  -- Gọi function để tính tiền bồi thường
  v_TienBoiThuong := Tinh_TienBoiThuong(1, 2);
  
  -- In ra tổng tiền bồi thường
  DBMS_OUTPUT.PUT_LINE('Tổng tiền bồi thường: ' || v_TienBoiThuong);
END;
/


--FUNCTION5
--Tính tiền khuyến mãi
CREATE OR REPLACE FUNCTION Tinh_TienKhuyenMai (
  f_MaHopDong NUMBER
) RETURN NUMBER IS
  v_TienKhuyenMai NUMBER := 0;  -- Khởi tạo tổng số tiền được khuyến mãi
  v_PhanTramKM NUMBER; -- Phần trăm khuyến mãi áp dụng
  v_SHD NUMBER;
BEGIN
  -- Tính tổng số hóa đơn của khách hàng từ bảng HOPDONG
  SELECT COUNT(SoHopDong) INTO v_SHD FROM KHACHHANG k WHERE MaKH = (SELECT MaKH FROM HOPDONG WHERE MaHopDong = f_MaHopDong);
  -- Tính tổng tiền phòng của hoá đơn
  SELECT SUM(P.Gia * Tinh_TGthuephongtheongay(HD.MaHopDong, HD.TGNhanPhong, HD.TGTraPhong))
  INTO v_TienKhuyenMai
  FROM HOPDONG HD
  INNER JOIN CHITIETDATPHONG c ON HD.MAHOPDONG = c.MAHOPDONG
  INNER JOIN PHONG P ON C.MAPHONG = P.MAPHONG
  WHERE HD.MAHOPDONG = f_MaHopDong;
  -- Nếu khách hàng là VIP (số hóa đơn >= 10)
  IF v_SHD >= 10 THEN
  -- Tìm chương trình khuyến mãi có PhanTramKM cao nhất và thỏa mãn điều kiện thời gian áp dụng
  SELECT MAX(KM.PhanTramKM)
  INTO v_PhanTramKM
  FROM KHUYENMAI KM
  WHERE KM.NgayBatDau <= (SELECT H.NGAYLAPHOPDONG FROM HOPDONG h WHERE H.MAHOPDONG = f_MaHopDong)
    AND KM.NgayKetThuc >= (SELECT H.NGAYLAPHOPDONG FROM HOPDONG h WHERE H.MAHOPDONG = f_MaHopDong);

  -- So sánh phần trăm khuyến mãi với 15%
  IF v_PhanTramKM > 0.15 THEN
    v_PhanTramKM := v_PhanTramKM;
  ELSE
    v_PhanTramKM := 0.15;
  END IF;
  ELSE
  -- Nếu không phải là VIP, không cần so sánh với 15%
  SELECT MAX(KM.PhanTramKM)
  INTO v_PhanTramKM
  FROM KHUYENMAI KM
  WHERE KM.NgayBatDau <= (SELECT H.NGAYLAPHOPDONG FROM HOPDONG h WHERE H.MAHOPDONG = f_MaHopDong)
    AND KM.NgayKetThuc >= (SELECT H.NGAYLAPHOPDONG FROM HOPDONG h WHERE H.MAHOPDONG = f_MaHopDong);
  END IF;

  -- Tính số tiền được khuyến mãi
  v_TienKhuyenMai := v_TienKhuyenMai * v_PhanTramKM;
  
  RETURN v_TienKhuyenMai;
END;
/
--Tính tiền khuyến mãi áp dụng cho hợp đồng có MaHopDong = 13
DECLARE
  v_TienKhuyenMai NUMBER :=0;
BEGIN
  -- Gọi function để tính tiền bồi thường
  v_TienKhuyenMai := TINH_TIENKHUYENMAI(13);
  
  -- In ra tổng tiền bồi thường
  DBMS_OUTPUT.PUT_LINE('Tổng tiền khuyến mãi: ' || v_TienKhuyenMai);
END;
/


--FUNCTION6
--Tính tổng tiền phải thanh toán của hoá đơn
CREATE OR REPLACE FUNCTION Tinh_TongTienThanhToan (
  f_MaHopDong NUMBER,
  f_MaTB NUMBER,
  f_SoLuongBiHong NUMBER
) RETURN NUMBER IS
  v_TongTienPhong NUMBER := 0;
  v_TriGiaHopDong NUMBER := 0;
  v_TienKhuyenMai NUMBER := 0;
  v_TienBoiThuong NUMBER := 0;
  v_TongTienThanhToan NUMBER := 0;
BEGIN
  -- Tính tổng tiền phòng của hợp đồng
  SELECT SUM(P.Gia * Tinh_TGthuephongtheongay(HD.MaHopDong, HD.TGNhanPhong, HD.TGTraPhong))
  INTO v_TongTienPhong
  FROM HOPDONG HD
  INNER JOIN CHITIETDATPHONG C ON HD.MaHopDong = C.MaHopDong
  INNER JOIN PHONG P ON C.MaPhong = P.MaPhong
  WHERE HD.MaHopDong = f_MaHopDong;

  -- Tính trị giá hợp đồng (tiền cọc)
  v_TriGiaHopDong := Tinh_TriGiaHopDong(f_MaHopDong);

  -- Tính tiền khuyến mãi
  v_TienKhuyenMai := Tinh_TienKhuyenMai(f_MaHopDong);

  -- Tính tiền bồi thường
  v_TienBoiThuong := Tinh_TienBoiThuong(f_MaTB, f_SoLuongBiHong);

  -- Tính tổng tiền phải thanh toán
  v_TongTienThanhToan := v_TongTienPhong - v_TriGiaHopDong - v_TienKhuyenMai + v_TienBoiThuong;

  RETURN v_TongTienThanhToan;
END;
/

--FUNCTION7
--Tính tổng lương một tháng của nhân viên
CREATE OR REPLACE FUNCTION Tinh_TongLuong (
  f_MaNV NUMBER
) RETURN NUMBER IS
  v_LuongCB NUMBER;     -- Lương cơ bản
  v_SoNgayDiLam NUMBER; -- Số ngày đi làm
  v_SoGioLamThem NUMBER;-- Số giờ làm thêm
  v_TongLuong NUMBER;   -- Tổng lương
BEGIN
  -- Lấy thông tin lương cơ bản từ bảng NHANVIEN
  SELECT LuongCB
  INTO v_LuongCB
  FROM NHANVIEN
  WHERE MaNV = f_MaNV;
  
  -- Lấy thông tin số ngày đi làm và số giờ làm thêm từ bảng CHAMCONG
  SELECT SoNgayDiLam, SoGioLamThem
  INTO v_SoNgayDiLam, v_SoGioLamThem
  FROM CHAMCONG
  WHERE MaNV = f_MaNV;

  -- Tính tổng lương
  v_TongLuong := v_LuongCB * v_SoNgayDiLam + (v_LuongCB / 8) * v_SoGioLamThem;

  RETURN v_TongLuong;
END;
/
--Tính tổng lương của nhân viên có MaNV = 1
DECLARE
v_TongLuong NUMBER;
BEGIN
V_TONGLUONG := TINH_TONGLUONG(1);
DBMS_OUTPUT.PUT_LINE('Tổng lương: ' || V_TONGLUONG);
END;
/

