Create or replace PROCEDURE TimKiem_HD
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
