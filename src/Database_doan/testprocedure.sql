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
        DELETE FROM HONGTRANGBI WHERE MaHD = v_MaHD;
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
/

CREATE OR REPLACE PROCEDURE Xoa_trangbi(
    P_MaTB NUMBER
)
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

CREATE OR REPLACE PROCEDURE Xoa_hoadon(
    P_MaHD NUMBER
)
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

CREATE OR REPLACE PROCEDURE Capnhap_nvtaohoadon (
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

CREATE OR REPLACE PROCEDURE Capnhap_tienhongtb (
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
/

CREATE OR REPLACE PROCEDURE thanhtoanHD (
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

CREATE OR REPLACE PROCEDURE Xoa_KhachHang(
    P_MaKH NUMBER
)
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

