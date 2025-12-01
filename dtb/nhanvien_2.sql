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
    update_trangbi(35, 'Tivi');
END;



------ Phantom -read : su dung muc co lap read committed
set transaction isolation level read committed;
INSERT INTO NHANVIEN (MANV, TENNV, CCCD, GIOITINH, NGAYSINH, DIACHI, LOAINV, TAIKHOAN, MATKHAU, SDT, LUONGCB, TINHTRANG) 
	VALUES (NhanVien_Seq.NEXTVAL, 'Hà Th? Hi?u', '098762472131', 'N?', TO_DATE('5-7-1987', 'DD-MM-YYYY'), '123 Ðu?ng Lý Thu?ng Ki?t, Qu?ng Nam', 'L? tân', 'letan7', 'ltpass', '0903276867', 800000, 'Ðang làm');
commit;


--- Phantom-read: su dung muc co lap serializable
set transaction isolation level serializable;
INSERT INTO NHANVIEN (MANV, TENNV, CCCD, GIOITINH, NGAYSINH, DIACHI, LOAINV, TAIKHOAN, MATKHAU, SDT, LUONGCB, TINHTRANG) 
	VALUES (NhanVien_Seq.NEXTVAL, 'Võ Hông H?nh', '098762762775', 'N?', TO_DATE('5-7-1987', 'DD-MM-YYYY'), '123 Ðu?ng Võ Nguyên Giáp, Khánh Hòa', 'L? tân', 'letan8', 'ltpass', '0903276301', 800000, 'Ðang làm');
commit;

INSERT INTO NHANVIEN (MANV, TENNV, CCCD, GIOITINH, NGAYSINH, DIACHI, LOAINV, TAIKHOAN, MATKHAU, SDT, LUONGCB, TINHTRANG) 
	VALUES (NhanVien_Seq.NEXTVAL, 'Võ Hông H?nh', '098762762775', 'N?', TO_DATE('5-7-1987', 'DD-MM-YYYY'), '123 Ðu?ng Võ Nguyên Giáp, Khánh Hòa', 'L? tân', 'letan8', 'ltpass', '0903276301', 800000, 'Ðang làm');
commit;

















------ non- repeatable reads: su dung muc co lap read committed
set transaction isolation level read committed;
SET SERVEROUTPUT ON;
EXEC TIMKIEM_HD('makh', 11);
update HOPDONG
set TinhTrangHD = 'Da xac nhan'
where mahopdong = 53;
EXEC TIMKIEM_HD('makh', 11);
commit;

------ non- repeatable reads: su dung muc co lap serializable 
set transaction isolation level serializable;
EXEC TIMKIEM_HD('makh', 11);
update HOPDONG
set TinhTrangHD = 'Da xac nhan'
where mahopdong = 53;
EXEC TIMKIEM_HD('makh', 11);
commit;




