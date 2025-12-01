-- LOST- UPDATE :
CREATE OR REPLACE PROCEDURE update_trangbi (p_soluong IN NUMBER, p_tentb IN VARCHAR2)
AS
BEGIN
    --SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
    SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
    UPDATE TRANGBI SET soluong = P_SOLUONG WHERE tentb = P_TENTB;
    DBMS_SESSION.SLEEP(5);
    COMMIT;
END;

SELECT TENTB, SOLUONG FROM TRANGBI WHERE TENTB = 'Tivi';

BEGIN
    update_trangbi(30, 'Tivi');
END;

COMMIT;


































------ non- repeatable reads: su dung muc co lap read committed
set transaction isolation level read committed;
SET SERVEROUTPUT ON;
EXEC TIMKIEM_HD('makh', 10);
COMMIT;
------ non- repeatable reads: su dung muc co lap serializable 
set transaction isolation level serializable;
SET SERVEROUTPUT ON;
EXEC TIMKIEM_HD('makh', 10);
















