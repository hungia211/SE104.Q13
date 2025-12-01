CREATE OR REPLACE PROCEDURE TimKiem_NV
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
            WHERE TO_CHAR(manv) = v_giatri;  -- Chuy?n d?i manv sang varchar2 d? so sánh
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
        DBMS_OUTPUT.PUT_LINE('Not exist!');
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
END;
/
