
-- Procedure
create PROCEDURE Capnhap_nvtaohoadon (
    P_MaHD NUMBER,
    p_MaNV NUMBER
)
AS
BEGIN
    UPDATE TAOHOADON
    SET MaNV = p_MaNV
    WHERE MaHD = P_MaHD;
    COMMIT;
END Capnhap_nvtaohoadon;
/

create PROCEDURE Capnhap_tienhongtb (
    P_MaHD NUMBER,
    p_Tienhtb NUMBER
)
AS
BEGIN
    UPDATE HOADON
    SET TIENHONGTB = p_Tienhtb
    WHERE MaHD = P_MaHD;
    COMMIT;
END Capnhap_tienhongtb;
/

create PROCEDURE Capnhat_sltrangbihong(
    P_MaTB NUMBER,
    p_SLHong INT)
IS
    v_CurrentHong INT;
    v_soluongtb INT;
BEGIN
    -- Lấy số lượng trang bị hiện tại của khách sạn
    SELECT SLHong INTO v_CurrentHong
    FROM TRANGBI
    WHERE MaTB = P_MaTB;
    SELECT soluong INTO v_soluongtb
    FROM TRANGBI
    WHERE MaTB = P_MaTB;

    -- Kiểm tra và cập nhập số lượng trang bị hỏng
    IF v_CurrentHong + p_SLHong <= v_soluongtb THEN
        UPDATE TRANGBI
        SET SLHong = v_CurrentHong + p_SLHong
        WHERE MaTB = P_MaTB;
    ELSE
        RAISE_APPLICATION_ERROR(-20003, 'Số lượng trang bị hỏng không thể lớn hơn số lượng trang bị.');
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'Trang bị không tồn tại.');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20002, 'Có lỗi xảy ra trong quá trình cập nhập');
END Capnhat_sltrangbihong;
/

create PROCEDURE Capnhat_trangbi(
    p_MaTB NUMBER,
    p_TenTB NVARCHAR2,
    p_GiaTB NUMBER,
    p_SoLuong INT,
    p_SoLuongHong INT)
IS
BEGIN
    UPDATE TRANGBI
    SET TenTB = p_TenTB,
        GiaTB = p_GiaTB,
        SoLuong = p_SoLuong,
        SLHong = p_SoLuongHong
    WHERE MaTB = p_MaTB;
    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Đã có lỗi xảy ra trong quá trình xóa trang bị.');
    END IF;
    COMMIT;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'Trang bị không tồn tại.');
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END Capnhat_trangbi;
/

create PROCEDURE GetDSKhuyenMai (
    p_cursor OUT SYS_REFCURSOR
) AS
BEGIN
    -- Thiết lập mức độ cô lập của giao dịch
    --SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
    SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;

    OPEN p_cursor FOR
    SELECT * FROM KHUYENMAI
    ORDER BY MaKM ASC;

    -- Chờ 5 giây
    --DBMS_SESSION.SLEEP(5);
END GetDSKhuyenMai;
/

create PROCEDURE InsertNhanVien (
    p_TENNV IN NHANVIEN.TENNV%TYPE,
    p_CCCD IN NHANVIEN.CCCD%TYPE,
    p_GIOITINH IN NHANVIEN.GIOITINH%TYPE,
    p_NGAYSINH IN NHANVIEN.NGAYSINH%TYPE,
    p_DIACHI IN NHANVIEN.DIACHI%TYPE,
    p_LOAINV IN NHANVIEN.LOAINV%TYPE,
    p_TAIKHOAN IN NHANVIEN.TAIKHOAN%TYPE,
    p_MATKHAU IN NHANVIEN.MATKHAU%TYPE,
    p_SDT IN NHANVIEN.SDT%TYPE,
    p_LUONGCB IN NHANVIEN.LUONGCB%TYPE,
    p_TINHTRANG IN NHANVIEN.TINHTRANG%TYPE
) IS
BEGIN

    INSERT INTO NHANVIEN (MANV, TENNV, CCCD, GIOITINH, NGAYSINH, DIACHI, LOAINV, TAIKHOAN, MATKHAU, SDT, LUONGCB, TINHTRANG)
    VALUES (NhanVien_Seq.NEXTVAL, p_TENNV, p_CCCD, p_GIOITINH, p_NGAYSINH, p_DIACHI, p_LOAINV, p_TAIKHOAN, p_MATKHAU, p_SDT, p_LUONGCB, p_TINHTRANG);

    COMMIT;
END;
/

create PROCEDURE thanhtoanHD (
    P_MaHopDong NUMBER
)
AS
BEGIN
    UPDATE HopDong
    SET TinhTrangHD = 'Đã thanh toán'
    WHERE MaHopDong = P_MaHopDong;

    COMMIT;
END thanhtoanHD;
/

create PROCEDURE Them_chitietdatphong(
    P_MaHopDong NUMBER,
    P_MaPhong NUMBER
)
IS
BEGIN
    -- Thêm mới chi tiết đặt phòng
    INSERT INTO CHITIETDATPHONG(MaHopDong, MaPhong)
    VALUES (P_MaHopDong, P_MaPhong);

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20001, 'Loi khi them chi tiet dat phong: ' || SQLERRM);
END Them_chitietdatphong;
/

create PROCEDURE Them_hoadon(
    P_MaKM NUMBER,
    P_MaHopDong NUMBER,
    P_NgayLapHD DATE,
    P_TongTien NUMBER,
    P_TienHongTB NUMBER
)
IS
BEGIN
    -- Thực hiện thêm hóa đơn mới vào bảng HOADON
    INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB)
    VALUES (HoaDon_Seq.NEXTVAL, P_MaKM, P_MaHopDong, P_NgayLapHD, P_TongTien, P_TienHongTB);
    COMMIT;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20005, 'Lỗi khi thêm hóa đơn: ' || SQLERRM);
END Them_hoadon;
/

create PROCEDURE Them_hongtrangbi (
    P_MaTB NUMBER,
    P_MaHD NUMBER
)
IS
BEGIN
    -- Thêm dữ liệu vào bảng HONGTRANGBI
    INSERT INTO HONGTRANGBI (MaTB, MaHD)
    VALUES (P_MaTB, P_MaHD);

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Xảy ra lỗi trong quá trình thêm: ' || SQLERRM);
END Them_hongtrangbi;
/

create PROCEDURE Them_khachhang(
    p_TenKH NVARCHAR2,
    p_CCCD CHAR,
    p_NgaySinh DATE,
    p_GioiTinh NVARCHAR2,
    p_DiaChi NVARCHAR2,
    p_SDT CHAR
)
IS
BEGIN
    INSERT INTO KHACHHANG (MaKH, TenKH, CCCd, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong)
    VALUES (KhachHang_Seq.NEXTVAL, p_TenKH, p_CCCD, p_NgaySinh, p_GioiTinh, p_DiaChi, p_SDT, 0);
    COMMIT;

EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20002, 'CCCD hoặc sdt đã tồn tại trong hệ thống');
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20004, 'CÓ lỗi xảy ra trong quá trình thêm khách hàng');
END Them_khachhang;
/

create PROCEDURE                    Them_khuyenmai(
    p_TenKM NVARCHAR2,
    p_MoTaKM NVARCHAR2,
    p_NgayBatDau DATE,
    p_NgayKetThuc DATE,
    p_PhanTramKM NUMERIC
)
IS
BEGIN
    -- Thiết lập mức độ cô lập của giao dịch
    --SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
    -- SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;

    -- Kiểm tra ngày bắt đầu và ngày kết thúc
    IF p_NgayKetThuc < p_NgayBatDau THEN
        RAISE_APPLICATION_ERROR(-20003, 'Ngày kết thúc không thể trước ngày bắt đầu.');
    END IF;

    INSERT INTO KHUYENMAI (MaKM, TenKM, MoTaKM, NgayBatDau, NgayKetThuc, PhanTramKM)
    VALUES (KhuyenMai_Seq.NEXTVAL, p_TenKM, p_MoTaKM, p_NgayBatDau, p_NgayKetThuc, p_PhanTramKM);

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20004, 'Có lỗi xảy ra trong quá trình thêm khuyến mãi.');
END Them_khuyenmai;
/

create PROCEDURE Them_nhanvien(
    p_TenNV NVARCHAR2,
    p_CCCD CHAR,
    p_NgaySinh DATE,
    p_GioiTinh NVARCHAR2,
    p_DiaChi NVARCHAR2,
    p_SDT CHAR,
    p_LoaiNV NVARCHAR2,
    p_TaiKhoan VARCHAR2,
    p_MatKhau VARCHAR2,
    p_LuongCB NUMBER
)
IS
    v_MaNV NUMBER;
BEGIN
    -- Thêm nhân viên mới
    INSERT INTO NHANVIEN (TenNV, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, LoaiNV, TaiKhoan, MatKhau, LuongCB, TinhTrang)
    VALUES (p_TenNV, p_CCCD, p_NgaySinh, p_GioiTinh, p_DiaChi, p_SDT, p_LoaiNV, p_TaiKhoan, p_MatKhau, p_LuongCB, 'Đang làm')
    RETURNING MaNV INTO v_MaNV;

    -- Thêm bảng chấm công cho nhân viên mới
    INSERT INTO CHAMCONG (MaCC, MaNV, SoGioLamThem, SoNgayDiLam)
    VALUES (ChamCong_Seq.NEXTVAL, v_MaNV, 0, 0);

EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20002, 'CCCD hoặc sdt đã tồn tại trong hệ thống');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20004, 'CÓ lỗi xảy ra trong quá trình thêm nhân viên');
END Them_nhanvien;
/

create PROCEDURE Them_trangbi(
    p_TenTB NVARCHAR2,
    p_GiaTB NUMBER,
    p_SoLuong INT
)
IS
BEGIN
    INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong)
    VALUES (TrangBi_Seq.NEXTVAL, p_TenTB, p_GiaTB, p_SoLuong, 0);

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20002, 'Có lỗi xảy ra trong quá trình thêm trang bị.');
END Them_trangbi;
/

create PROCEDURE Themhopdong(
    P_MaKH NUMBER,
    P_TGNhanPhong TIMESTAMP,
    P_TGTraPhong TIMESTAMP,
    P_SoNguoiLon NUMBER,
    P_SoTreEm NUMBER,
    P_TinhTrangHD NVARCHAR2,
    P_HinhThucThue NVARCHAR2,
    P_TriGiaHD NUMBER
)
IS
    v_MaHopDong NUMBER;
BEGIN
    -- Thêm mới hợp đồng
    INSERT INTO HOPDONG(MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, SoNguoiLon, SoTreEm, TinhTrangHD, HinhThucThue, TriGiaHD)
    VALUES(P_MaKH, SYSDATE, P_TGNhanPhong, P_TGTraPhong, P_SoNguoiLon, P_SoTreEm, P_TinhTrangHD, P_HinhThucThue, P_TriGiaHD)
    RETURNING MaHopDong INTO v_MaHopDong;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END Themhopdong;
/

create PROCEDURE                    ThemNhanVien(
    p_TENNV IN NVARCHAR2,
    p_CCCD IN CHAR,
    p_GIOITINH IN NVARCHAR2,
    p_NGAYSINH IN DATE,
    p_DIACHI IN NVARCHAR2,
    p_SDT IN CHAR,
    p_LOAINV IN NVARCHAR2,
    p_TAIKHOAN IN NVARCHAR2,
    p_MATKHAU IN NVARCHAR2,
    p_LUONGCB IN NUMBER
)
IS
BEGIN
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
    -- SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
    -- Chèn dữ liệu vào bảng NHANVIEN
    INSERT INTO NHANVIEN (MANV, TENNV, CCCD, GIOITINH, NGAYSINH, DIACHI, LOAINV, TAIKHOAN, MATKHAU, SDT, LUONGCB, TINHTRANG)
    VALUES (NhanVien_Seq.NEXTVAL, p_TENNV, p_CCCD, p_GIOITINH, p_NGAYSINH, p_DIACHI, p_LOAINV, p_TAIKHOAN, p_MATKHAU, p_SDT, p_LUONGCB, 'Đang làm');
END;
/

create PROCEDURE TimKiem_HD
(
    v_tenthuoctinh IN VARCHAR2,
    v_giatri IN VARCHAR2
)
AS
    v_ds_hd SYS_REFCURSOR;
    v_mahopdong number;
    v_makh  number;
    v_ngaylaphopdong timestamp;
    v_tgnhanphong timestamp;
    v_tgtraphong timestamp;
    v_tinhtranghd nvarchar2(20);
    v_songuoilon number;
    v_sotreem number;
    v_trigiahd number;
    v_hinhthucthue nvarchar2(10);
BEGIN
    IF v_tenthuoctinh = 'mahopdong' THEN
        OPEN v_ds_hd FOR
            SELECT *
            FROM HOPDONG
            WHERE mahopdong = v_giatri;
    ELSIF v_tenthuoctinh = 'makh' THEN
         OPEN v_ds_hd FOR
            SELECT *
            FROM HOPDONG
            WHERE makh = v_giatri;
    ELSIF v_tenthuoctinh = 'tinhtranghd' THEN
        OPEN v_ds_hd FOR
            SELECT *
            FROM HOPDONG
            WHERE tinhtranghd = v_giatri;
    ELSE
        DBMS_OUTPUT.PUT_LINE('Not exist!');
        RETURN;
    END IF;

   LOOP
        FETCH v_ds_hd INTO v_mahopdong, v_makh, v_ngaylaphopdong, v_tgnhanphong, v_tgtraphong, v_tinhtranghd, v_songuoilon, v_sotreem, v_trigiahd, v_hinhthucthue ;
        EXIT WHEN v_ds_hd%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE('-----------------------');
        DBMS_OUTPUT.PUT_LINE('MAHD:' ||v_mahopdong );
        DBMS_OUTPUT.PUT_LINE('MAKH:' || v_makh);
        DBMS_OUTPUT.PUT_LINE('NGAYLAPHD:' || v_ngaylaphopdong);
        DBMS_OUTPUT.PUT_LINE('TGNHANPHONG:' || v_tgnhanphong);
        DBMS_OUTPUT.PUT_LINE('TGTRAPHONG:' || v_tgtraphong);
        DBMS_OUTPUT.PUT_LINE('TINHTRANGHD:' ||  v_tinhtranghd);
        DBMS_OUTPUT.PUT_LINE('SOLUONGNGUOILON:' || v_songuoilon);
        DBMS_OUTPUT.PUT_LINE('SOLUONGTREEM:' || v_sotreem);
        DBMS_OUTPUT.PUT_LINE('TRIGIAHD:' || v_trigiahd);
        DBMS_OUTPUT.PUT_LINE('HINHTHUCTHUE:' || v_hinhthucthue);
    END LOOP;

    CLOSE v_ds_hd;
END;
/

create PROCEDURE TimKiem_NV
(
    v_tenthuoctinh IN VARCHAR2,
    v_giatri IN VARCHAR2
)
AS
    v_ds_nv SYS_REFCURSOR;
    v_manv number;
    v_tennv  nvarchar2(50);
    v_cccd char(12);
    v_ngaysinh date;
    v_gioitinh nvarchar2(3);
    v_diachi nvarchar2(100);
    v_sdt char(10);
    v_loainv nvarchar2(20);
    v_taikhoan varchar2(20);
    v_matkhau varchar2(20);
    v_luongcb number;
    v_tinhtrang varchar2(20);
BEGIN
    IF v_tenthuoctinh = 'manv' THEN
        OPEN v_ds_nv FOR
            SELECT *
            FROM NHANVIEN
            WHERE TO_CHAR(manv) = v_giatri;
    ELSIF v_tenthuoctinh = 'tennv' THEN
        OPEN v_ds_nv FOR
            SELECT *
            FROM NHANVIEN
            WHERE tennv = v_giatri;
    ELSIF v_tenthuoctinh = 'cccd' THEN
        OPEN v_ds_nv FOR
            SELECT *
            FROM NHANVIEN
            WHERE cccd = v_giatri;
    ELSIF v_tenthuoctinh = 'sdt' THEN
        OPEN v_ds_nv FOR
            SELECT *
            FROM NHANVIEN
            WHERE sdt = v_giatri;
    ELSIF v_tenthuoctinh = 'loainv' THEN
        OPEN v_ds_nv FOR
            SELECT *
            FROM NHANVIEN
            WHERE loainv = v_giatri;
    ELSE
        DBMS_OUTPUT.PUT_LINE('Thuộc tính không tồn tại!');
        RETURN;
    END IF;

    LOOP
        FETCH v_ds_nv INTO v_manv, v_tennv, v_cccd, v_ngaysinh, v_gioitinh, v_diachi, v_sdt, v_loainv, v_taikhoan, v_matkhau, v_luongcb, v_tinhtrang;
        EXIT WHEN v_ds_nv%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('-----------------------');
        DBMS_OUTPUT.PUT_LINE('MANV: ' || v_manv);
        DBMS_OUTPUT.PUT_LINE('TENNV: ' || v_tennv);
        DBMS_OUTPUT.PUT_LINE('CCCD: ' || v_cccd);
        DBMS_OUTPUT.PUT_LINE('NGAYSINH: ' || v_ngaysinh);
        DBMS_OUTPUT.PUT_LINE('GIOITINH: ' || v_gioitinh);
        DBMS_OUTPUT.PUT_LINE('DIACHI: ' || v_diachi);
        DBMS_OUTPUT.PUT_LINE('SDT: ' || v_sdt);
        DBMS_OUTPUT.PUT_LINE('LOAINV: ' || v_loainv);
        DBMS_OUTPUT.PUT_LINE('TAIKHOAN: ' || v_taikhoan);
        DBMS_OUTPUT.PUT_LINE('MATKHAU: ' || v_matkhau);
        DBMS_OUTPUT.PUT_LINE('LUONGCB: ' || v_luongcb);
        DBMS_OUTPUT.PUT_LINE('TINHTRANG: ' || v_tinhtrang);
    END LOOP;

    CLOSE v_ds_nv;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Đã xảy ra lỗi: ' || SQLERRM);
        IF v_ds_nv%ISOPEN THEN
            CLOSE v_ds_nv;
        END IF;
END;
/

create FUNCTION                    TimNhanVien(
    p_option IN VARCHAR2,
    p_textInput IN VARCHAR2
) RETURN SYS_REFCURSOR
IS
  cur SYS_REFCURSOR;
BEGIN

  SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
  --SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;

  IF p_option = 'Mã NV' THEN
    OPEN cur FOR
      SELECT * FROM NHANVIEN WHERE LOWER(MANV) LIKE LOWER('%' || p_textInput || '%') AND TINHTRANG = 'Đang làm';
  ELSIF p_option = 'Họ tên' THEN
    OPEN cur FOR
      SELECT * FROM NHANVIEN WHERE LOWER(TENNV) LIKE LOWER('%' || p_textInput || '%') AND TINHTRANG = 'Đang làm';
  ELSIF p_option = 'CCCD' THEN
    OPEN cur FOR
      SELECT * FROM NHANVIEN WHERE CCCD = p_textInput;
  ELSIF p_option = 'SĐT' THEN
    OPEN cur FOR
      SELECT * FROM NHANVIEN WHERE SDT = p_textInput;
  ELSIF p_option = 'Loại NV' THEN
    OPEN cur FOR
      SELECT * FROM NHANVIEN WHERE LOWER(LOAINV) LIKE LOWER('%' || p_textInput || '%') AND TINHTRANG = 'Đang làm';
  END IF;

  -- Chờ 5 giây
  DBMS_SESSION.SLEEP(5);

  RETURN cur;
END;
/



-- Function
create FUNCTION                    LayDanhSachNhanVien
RETURN SYS_REFCURSOR
IS
  cur SYS_REFCURSOR;
BEGIN

  SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;

  OPEN cur FOR
    SELECT * FROM NhanVien ORDER BY MANV DESC;


  RETURN cur;
END;
/

create FUNCTION Tinh_TGthuephongtheogio (
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

create FUNCTION Tinh_TGthuephongtheongay (
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

create FUNCTION Tinh_TienBoiThuong (
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

create FUNCTION Tinh_TienKhuyenMai (
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

create FUNCTION Tinh_TongLuong (
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

create FUNCTION Tinh_TongTienThanhToan (
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

create PROCEDURE Xoa_hoadon(
    P_MaHD NUMBER)
IS
    v_MaHopDong NUMBER;
BEGIN
    SELECT MaHopDong INTO v_MaHopDong FROM HOADON WHERE MaHD = P_MaHD;
    -- Xóa dữ liệu hỏng trang bị liên quan
    Xoa_HopDong(v_MaHopDong);

    -- Kiểm tra xóa thành công không
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END Xoa_hoadon;
/

create PROCEDURE Xoa_hopdong(p_MaHopDong NUMBER)
IS
    v_count NUMBER;
    v_MaHD NUMBER;
    v_MaKH NUMBER;
    v_TinhTrangHD VARCHAR2(50);
    v_HinhThucThue VARCHAR2(10);
BEGIN
    -- Lấy thông tin khách hàng và trạng thái hợp đồng từ hợp đồng
    SELECT MaKH, TinhTrangHD, HinhThucThue INTO v_MaKH, v_TinhTrangHD, v_HinhThucThue
    FROM HOPDONG
    WHERE MaHopDong = p_MaHopDong;

    -- Xóa chi tiết đặt phòng liên quan
    DELETE FROM CHITIETDATPHONG
    WHERE MaHopDong = p_MaHopDong;

    -- Xóa các bản ghi hóa đơn liên quan
    SELECT COUNT(*) INTO v_count FROM HOADON WHERE MaHopDong = p_MaHopDong;
    IF v_count > 0 THEN
        SELECT MaHD INTO v_MaHD FROM HOADON WHERE MaHopDong = p_MaHopDong;
        DELETE FROM TAOHOADON WHERE MaHD = v_MaHD;
        DELETE FROM HONGTRANGBI WHERE MaHD = v_MaHD;
        DELETE FROM HOADON WHERE MaHopDong = p_MaHopDong;
    END IF;

    -- Xóa hợp đồng
    DELETE FROM HOPDONG
    WHERE MaHopDong = p_MaHopDong;

    -- Kiểm tra số hợp đồng của khách hàng và giảm đi 1 đơn vị
    UPDATE KHACHHANG
    SET SoHopDong = SoHopDong - 1
    WHERE MaKH = v_MaKH;

    -- Nếu khách hàng không có hợp đồng nào khác, xóa khách hàng này luôn
    SELECT COUNT(*) INTO v_count FROM HOPDONG WHERE MaKH = v_MaKH;

    IF v_count = 0 THEN
        DELETE FROM KHACHHANG WHERE MaKH = v_MaKH;
    END IF;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20004, 'Lỗi khi xóa hợp đồng');
END Xoa_hopdong;
/

create PROCEDURE Xoa_KhachHang(
    P_MaKH NUMBER)
IS
    v_count NUMBER;
BEGIN
    -- Kiểm tra xem khách hàng có tồn tại không
    SELECT COUNT(*) INTO v_count FROM KHACHHANG WHERE MaKH = P_MaKH;
    IF v_count = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Không tìm thấy khách hàng với mã: ' || P_MaKH);
    END IF;

    -- Vòng lặp qua tất cả các hợp đồng của khách hàng và xóa từng hợp đồng
    FOR hopdong_rec IN (SELECT MaHopDong FROM HOPDONG WHERE MaKH = P_MaKH)
    LOOP
        -- Gọi procedure Xoa_HopDong để xóa mỗi hợp đồng
        Xoa_HopDong(hopdong_rec.MaHopDong);
    END LOOP;

    -- Nếu không có hợp đồng, vẫn tiếp tục xóa khách hàng
    DELETE FROM KHACHHANG WHERE MaKH = P_MaKH;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END Xoa_KhachHang;
/

create PROCEDURE Xoa_Km(
    P_MaKM NUMBER
)
IS
    v_count NUMBER;
BEGIN
    -- Đếm số hóa đơn sử dụng mã khuyến mãi
    SELECT COUNT(*) INTO v_count FROM HOADON WHERE MaKM = P_MaKM;

    -- Kiểm tra xem mã KM có được sử dụng trong hóa đơn nào không
    IF v_count > 0 THEN
        -- Nếu mã khuyến mãi đã được sử dụng, phát ra lỗi
        RAISE_APPLICATION_ERROR(-20002, 'Không thể xóa mã khuyến mãi ' || P_MaKM || ' vì nó đã được áp dụng trong hóa đơn.');
    ELSE
        -- Nếu không, xóa mã khuyến mãi
        DELETE FROM KHUYENMAI WHERE MaKM = P_MaKM;
        COMMIT;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END Xoa_Km;
/

create PROCEDURE Xoa_nhanvien(
    P_MaNV NUMBER)
IS
BEGIN
    -- Cập nhập tình trạng nhân viên
    UPDATE NHANVIEN
    SET TinhTrang = 'Nghỉ làm'
    WHERE MaNV = P_MaNV;

    -- kiểm tra xem có cập nhập thành công
    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Nhân viên không tồn tại.');
    ELSE
        -- Xóa dữ liệu chấm công của nhân viên
        DELETE FROM CHAMCONG
        WHERE MaNV = P_MaNV;
    END IF;
END Xoa_nhanvien;
/

create PROCEDURE Xoa_trangbi(
    P_MaTB NUMBER)
IS
BEGIN
    -- Xóa dữ liệu hỏng trang bị liên quan
    DELETE FROM HONGTRANGBI
    WHERE MaTB = P_MaTB;

    -- Xóa trang bị
    DELETE FROM TRANGBI
    WHERE MaTB = P_MaTB;

    -- Kiểm tra xóa thành công không
    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Xóa không thành công.');
    END IF;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END Xoa_trangbi;
/





-- Build THONGKE from existing HOADON data.
-- Allocates invoice revenue to room types proportional to room price.
MERGE INTO THONGKE t
USING (
    SELECT EXTRACT(MONTH FROM h.NgayLapHD) AS Thang,
           EXTRACT(YEAR FROM h.NgayLapHD) AS Nam,
           p.MaLoai,
           SUM(CASE
                   WHEN tg.TongGia = 0 THEN 0
                   ELSE h.TongTien * (lp.Gia / tg.TongGia)
               END) AS DoanhThu
    FROM HOADON h
    JOIN HOPDONG hd ON h.MaHopDong = hd.MaHopDong
    JOIN CHITIETDATPHONG c ON hd.MaHopDong = c.MaHopDong
    JOIN PHONG p ON c.MaPhong = p.MaPhong
    JOIN LOAIPHONG lp ON p.MaLoai = lp.MaLoai
    JOIN (
        SELECT hd2.MaHopDong, SUM(lp2.Gia) AS TongGia
        FROM HOPDONG hd2
        JOIN CHITIETDATPHONG c2 ON hd2.MaHopDong = c2.MaHopDong
        JOIN PHONG p2 ON c2.MaPhong = p2.MaPhong
        JOIN LOAIPHONG lp2 ON p2.MaLoai = lp2.MaLoai
        GROUP BY hd2.MaHopDong
    ) tg ON tg.MaHopDong = h.MaHopDong
    GROUP BY EXTRACT(MONTH FROM h.NgayLapHD), EXTRACT(YEAR FROM h.NgayLapHD), p.MaLoai
) src
ON (t.Thang = src.Thang AND t.Nam = src.Nam AND t.MaLoai = src.MaLoai)
WHEN MATCHED THEN
    UPDATE SET t.DoanhThu = src.DoanhThu
WHEN NOT MATCHED THEN
    INSERT (MaTK, Thang, Nam, MaLoai, DoanhThu)
    VALUES (ThongKe_Seq.NEXTVAL, src.Thang, src.Nam, src.MaLoai, src.DoanhThu);

COMMIT;
