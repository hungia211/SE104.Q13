
-- Procedure 1
CREATE OR REPLACE PROCEDURE Them_khachhang(
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
-- Procedure 2
create or replace PROCEDURE Them_nhanvien(
    p_TenNV NVARCHAR2,
    p_CCCD CHAR,
    p_NgaySinh DATE,
    p_GioiTinh NVARCHAR2,
    p_DiaChi NVARCHAR2,
    p_SDT CHAR,
    p_LoaiNV NVARCHAR2,
    p_TaiKhoan VARCHAR2,
    p_MatKhau VARCHAR2,
    p_LuongCB NUMBER)
IS
    v_MaNV NUMBER;
BEGIN
    -- Thêm nhân viên mới
    INSERT INTO NHANVIEN (TenNV, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, LoaiNV, TaiKhoan, MatKhau, LuongCB, TinhTrang)
    VALUES (p_TenNV, p_CCCD, p_NgaySinh, p_GioiTinh, p_DiaChi, p_SDT, p_LoaiNV, p_TaiKhoan, p_MatKhau, p_LuongCB, 'Đang làm')
    RETURNING MaNV INTO v_MaNV;

    -- thêm bảng chấm công cho nhân viên mới 
    INSERT INTO CHAMCONG (MaCC, MaNV, SoGioLamThem, SoNgayDiLam)
    VALUES (ChamCong_Seq.NEXTVAL, v_MaNV, 0, 0);

EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20002, 'CCCD hoặc sdt đã tồn tại trong hệ thống');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20004, 'CÓ lỗi xảy ra trong quá trình thêm nhân viên');
END Them_nhanvien;
-- Procedure 3
create or replace PROCEDURE Themhopdong(
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
    INSERT INTO HOPDONG(MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, SoNguoiLon, SoTreEm, TinhTrangHD,HinhThucThue,TriGiaHD)
    VALUES(P_MaKH, SYSDATE, P_TGNhanPhong, P_TGTraPhong, P_SoNguoiLon, P_SoTreEm, P_TinhTrangHD,P_HinhThucThue,P_TriGiaHD)
    RETURNING MaHopDong INTO v_MaHopDong;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        -- Hoàn tác các thay đổi nếu có lỗi
        ROLLBACK;
        RAISE;
END Themhopdong;
-- Procedure 4
create or replace PROCEDURE Them_chitietdatphong(
    P_MaHopDong NUMBER,
    P_MaPhong NUMBER
)
IS
BEGIN
    -- Thêm mới chi tiết đặt phòng
    INSERT INTO CHITIETDATPHONG(MaHopDong, MaPhong)
    VALUES (P_MaHopDong, P_MaPhong);

    -- Xác nhận các thay đổi
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        -- Hoàn tác các thay đổi nếu có lỗi
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20001, 'Loi khi them chi tiet dat phong : ' || SQLERRM);
END Them_chitietdatphong;
-- Procedure 5
create or replace PROCEDURE Them_trangbi(
    p_TenTB NVARCHAR2,
    p_GiaTB NUMBER,
    p_SoLuong INT)
IS
BEGIN
    INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong)
    VALUES (TrangBi_Seq.NEXTVAL, p_TenTB, p_GiaTB, p_SoLuong, 0);

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20002, 'Có lỗi xảy ra trong quá trình thêm trang bị .');
END Them_trangbi;
-- Procedure 6
create or replace PROCEDURE Them_khuyenmai(
    p_TenKM NVARCHAR2,
    p_MoTaKM NVARCHAR2,
    p_NgayBatDau DATE,
    p_NgayKetThuc DATE,
    p_PhanTramKM NUMERIC)
IS
BEGIN
    -- kiểm tra ngày bắt đầu và ngày kết thúc  
    IF p_NgayKetThuc < p_NgayBatDau THEN
        RAISE_APPLICATION_ERROR(-20003, 'Ngày kết thúc không thể trước ngày bắt đầu ');
    END IF;

    INSERT INTO KHUYENMAI (MaKM, TenKM, MoTaKM, NgayBatDau, NgayKetThuc, PhanTramKM)
    VALUES (KhuyenMai_Seq.NEXTVAL, p_TenKM, p_MoTaKM, p_NgayBatDau, p_NgayKetThuc, p_PhanTramKM);

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20004, 'Có lỗi xảy ra trong quá trình thêm khuyến mãi.');
END Them_khuyenmai;
-- Procedure 7
CREATE OR REPLACE PROCEDURE Them_hoadon(
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
        ROLLBACK; -- Lùi lại tất cả các thay đổi nếu có lỗi
        RAISE_APPLICATION_ERROR(-20005, 'Lỗi khi thêm hóa đơn: ' || SQLERRM);
END Them_hoadon;
-- Procedure 8
create or replace PROCEDURE Them_hongtrangbi (
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
        -- Xử lý lỗi khi không thể thêm vào bảng
        RAISE_APPLICATION_ERROR(-20001, 'Xảy ra lỗi trong quá trình thêm  ' || SQLERRM);
END Them_hongtrangbi;
-- Procedure 9
CREATE OR REPLACE PROCEDURE Xoa_hopdong(p_MaHopDong NUMBER)
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
         DELETE FROM HONGTRANGBI WHERE MaHD= v_MaHD;
        DELETE FROM HOADON WHERE MaHopDong = p_MaHopDong;
    END IF;

    -- Xóa hợp đồng
    DELETE FROM HOPDONG
    WHERE MaHopDong = p_MaHopDong;

    -- Kiểm tra xem có bản ghi nào được xóa không và kiểm tra điều kiện để cập nhật số hợp đồng
    IF SQL%ROWCOUNT > 0 THEN
        IF v_TinhTrangHD = 'Đã thanh toán' AND v_HinhThucThue = 'Ngày' THEN
            -- Cập nhật số hợp đồng của khách hàng
            UPDATE KHACHHANG
            SET SoHopDong = SoHopDong - 1
            WHERE MaKH = v_MaKH;
             DELETE FROM KHACHHANG
        WHERE MaKH = v_MaKH AND SoHopDong = 0;

        END IF;
        COMMIT;
    ELSE
        RAISE_APPLICATION_ERROR(-20002, 'Không thể xóa hợp đồng.');
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        -- Cung cấp thông tin chi tiết về lỗi
        RAISE_APPLICATION_ERROR(-20001, 'Lỗi xảy ra: ' || SQLERRM);
END Xoa_hopdong;
-- Procedure 10
CREATE OR REPLACE PROCEDURE Xoa_KhachHang(
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
-- Procedure 11
create or replace PROCEDURE Xoa_nhanvien(
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
-- Procedure 12
create or replace PROCEDURE Xoa_trangbi(
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
-- Procedure 13
CREATE OR REPLACE PROCEDURE Xoa_Km(
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
-- Procedure 14
create or replace PROCEDURE Xoa_hoadon(
    P_MaHD NUMBER)
IS
v_MaHopDong NUMBER;
BEGIN
select MaHopDong into v_MaHopDong From HOADON WHERE MaHD=P_MaHD;
    -- Xóa dữ liệu hỏng trang bị liên quan
   Xoa_HopDong(v_MaHopDong);

    -- Kiểm tra xóa thành công không 

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END Xoa_hoadon;
-- Procedure 15
create or replace PROCEDURE Capnhap_nvtaohoadon (
    P_MaHD NUMBER,
    p_MaNV NUMBER
)
AS
BEGIN
    UPDATE TAOHOADON
    SET MaNV = p_MaNV
    WHERE MaHD = P_MaHD;
    COMMIT;
END;
-- Procedure 16
create or replace PROCEDURE Capnhap_tienhongtb (
    P_MaHD NUMBER,
    p_Tienhtb NUMBER
)
AS
BEGIN
    UPDATE HOADON
    SET TIENHONGTB=p_Tienhtb
    WHERE MaHD = P_MaHD;
    COMMIT;
END Capnhap_tienhongtb;
-- Procedure 17
create or replace PROCEDURE thanhtoanHD (
    P_MaHopDong NUMBER
)
AS
BEGIN
    UPDATE HopDong
    SET TinhTrangHD = 'Đã thanh toán'
    WHERE MaHopDong = P_MaHopDong;

    COMMIT;
END;
-- Procedure 18
create or replace PROCEDURE Capnhat_trangbi(
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
-- Procedure 19
create or replace PROCEDURE Capnhat_sltrangbihong(
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
    SELECT soluong into v_soluongtb
    FROM TRANGBI
    WHERE MaTB=P_MaTB;
    -- Kiểm tra và cập nhập số lượng trang bị hỏng 
    IF v_CurrentHong + p_SLHong <= v_soluongtb THEN
        UPDATE TRANGBI
        SET SLHong = v_CurrentHong + p_SLHong
        WHERE MaTB = P_MaTB;
    ELSE
        RAISE_APPLICATION_ERROR(-20003, 'Số lượng trang bị hỏng không thể lớn hơn số lượng trang bị  ');
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'Trang bị không tồn tại .');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20002, 'Có lỗi xảy ra trong quá trình cập nhập');
END Capnhat_sltrangbihong;
