CREATE OR REPLACE PROCEDURE DATABASE_DOAN_NPNT.Them_khuyenmai(
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
    SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;

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