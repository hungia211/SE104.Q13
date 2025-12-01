CREATE OR REPLACE PROCEDURE update_nhanvien (
    p_manv      IN NUMBER,
    p_tennv     IN NVARCHAR2,
    p_cccd      IN CHAR,
    p_ngaysinh  IN DATE,
    p_gioitinh  IN NVARCHAR2,
    p_diachi    IN NVARCHAR2,
    p_sdt       IN CHAR,
    p_loainv    IN NVARCHAR2,
    p_taikhoan  IN VARCHAR2,
    p_matkhau   IN VARCHAR2,
    p_luongcb   IN NUMBER,
    p_tinhtrang IN NVARCHAR2
)
AS
BEGIN
    -- Tắt chế độ tự động xác nhận giao dịch (nếu cần)
    SET AUTOCOMMIT OFF;

    -- Thiết lập mức độ cô lập của giao dịch
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

    -- Cập nhật bảng NHANVIEN
    UPDATE NHANVIEN
    SET TenNV = p_tennv,
        CCCD = p_cccd,
        NgaySinh = p_ngaysinh,
        GioiTinh = p_gioitinh,
        DiaChi = p_diachi,
        SDT = p_sdt,
        LoaiNV = p_loainv,
        TaiKhoan = p_taikhoan,
        MatKhau = p_matkhau,
        LuongCB = p_luongcb,
        TinhTrang = p_tinhtrang
    WHERE MaNV = p_manv;

    -- Chờ 10 giây
    DBMS_SESSION.SLEEP(10);

    -- Xác nhận giao dịch
    COMMIT;
END;
/




CREATE OR REPLACE FUNCTION LayDanhSachNhanVien
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

COMMIT;



CREATE OR REPLACE FUNCTION TimNhanVien(
    p_option IN VARCHAR2,
    p_textInput IN VARCHAR2
) RETURN SYS_REFCURSOR
IS
  cur SYS_REFCURSOR;
BEGIN

  SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;

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

  RETURN cur;
END;
/


BEGIN
  TimNhanVien('Loại NV', 'Lễ tân');
END;
/

SELECT * FROM NHANVIEN WHERE LOWER(MANV) LIKE LOWER('%' || 1 || '%') AND TINHTRANG = 'Đang làm';

SELECT * FROM NHANVIEN WHERE MANV = 15






