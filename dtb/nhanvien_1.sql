-- LOST- UPDATE :

CREATE OR REPLACE PROCEDURE update_trangbi (p_soluong IN NUMBER, p_tentb IN VARCHAR2)
AS
BEGIN
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
    --SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
    UPDATE TRANGBI SET soluong = P_SOLUONG WHERE tentb = P_TENTB;
    DBMS_SESSION.SLEEP(5);
    COMMIT;
END;

SELECT TENTB, SOLUONG FROM TRANGBI WHERE TENTB = 'Tivi';

BEGIN
    update_trangbi(30, 'Tivi');
END;




-- Phantom-read
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


SELECT * FROM NHANVIEN n;
------ Phantom -read :
set transaction isolation level read committed;
SET SERVEROUTPUT ON;

EXEC TIMKIEM_NV('loainv', 'L? tân');

COMMIT;

--- Phantom -read : su dung muc co lap serializable 
set transaction isolation level SERIALIZABLE;
SET SERVEROUTPUT ON;

EXEC TIMKIEM_NV('loainv', 'L? tân');

COMMIT;

SET AUTO COMMIT OFF;














------ non- repeatable reads: su dung muc co lap read committed
set transaction isolation level read committed;
SET SERVEROUTPUT ON;
EXEC TIMKIEM_HD('makh', 10);
------ non- repeatable reads: su dung muc co lap serializable 
set transaction isolation level serializable;
SET SERVEROUTPUT ON;
EXEC TIMKIEM_HD('makh', 10);
















