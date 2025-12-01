CREATE OR REPLACE PROCEDURE update_trangbi (
    p_tentb   IN VARCHAR2,
    p_giatb   IN NUMBER,
    p_soluong IN NUMBER,
    p_slhong  IN NUMBER,
    p_matb    IN NUMBER
)
AS
BEGIN
    -- Thiết lập mức độ cô lập của giao dịch
    SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
    -- Cập nhật bảng TRANGBI
    UPDATE TRANGBI
    SET TenTB = p_tentb,
        GiaTB = p_giatb,
        SoLuong = p_soluong,
        SLHong = p_slhong
    WHERE MaTB = p_matb;

    -- Chờ 10 giây
    DBMS_SESSION.SLEEP(5);

    -- Xác nhận giao dịch
    COMMIT;
END;

BEGIN
    update_trangbi('Tivi', 5000000, 25, 2, 1);
END;

COMMIT;
SET AUTOCOMMIT OFF;