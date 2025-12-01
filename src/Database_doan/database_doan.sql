ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;
CREATE USER database_doan_NPNT IDENTIFIED BY password;
GRANT CONNECT, RESOURCE, DBA TO database_doan_NPNT;

ALTER SESSION SET CURRENT_SCHEMA = database_doan_NPNT;

-- Xóa các b?ng n?u dã t?n t?i
DROP TABLE HONGTRANGBI CASCADE CONSTRAINTS;
DROP TABLE TAOHOADON CASCADE CONSTRAINTS;
DROP TABLE CHAMCONG CASCADE CONSTRAINTS;
DROP TABLE HOADON CASCADE CONSTRAINTS;
DROP TABLE KHUYENMAI CASCADE CONSTRAINTS;
DROP TABLE TRANGBI CASCADE CONSTRAINTS;
DROP TABLE CHITIETDATPHONG CASCADE CONSTRAINTS;
DROP TABLE PHONG CASCADE CONSTRAINTS;
DROP TABLE HOPDONG CASCADE CONSTRAINTS;
DROP TABLE NHANVIEN CASCADE CONSTRAINTS;
DROP TABLE KHACHHANG CASCADE CONSTRAINTS;

-- T?o b?ng
-- T?o b?ng khách hàng
CREATE TABLE KHACHHANG (
  MaKH NUMBER PRIMARY KEY,
  TenKH NVARCHAR2(50),
  CCCD CHAR(12),
  NgaySinh DATE,
  GioiTinh NVARCHAR2(3),  -- 'Nam', 'N?'
  DiaChi NVARCHAR2(100),
  SDT CHAR(10),
  SoHopDong INT
);

-- T?o b?ng nhân viên
CREATE TABLE NHANVIEN (
  MaNV NUMBER PRIMARY KEY,
  TenNV NVARCHAR2(50),
  CCCD CHAR(12),
  NgaySinh DATE,
  GioiTinh NVARCHAR2(3),  -- 'Nam', 'N?'
  DiaChi NVARCHAR2(100),
  SDT CHAR(10),
  LoaiNV NVARCHAR2(20),    -- 'T?p V?', 'L? Tân', 'Qu?n lý'
  TaiKhoan VARCHAR2(20),
  MatKhau VARCHAR2(20),
  LuongCB NUMBER,
  TinhTrang NVARCHAR2(20)  -- 'Ðang làm', 'Ngh? làm'
);

-- T?o b?ng h?p d?ng d?t phòng t?m
CREATE TABLE HOPDONG(
  MaHopDong NUMBER PRIMARY KEY,
  MaKH NUMBER,
  NgayLapHopDong TIMESTAMP,
  TGNhanPhong TIMESTAMP,
  TGTraPhong TIMESTAMP,
  TinhTrangHD NVARCHAR2(20),  -- 'Ðã xác nh?n', 'Chua xác nh?n'
  SoNguoiLon NUMBER,
  SoTreEm NUMBER,
  TriGiaHD NUMBER,  -- Ti?n c?c c?a h?p d?ng
  HinhThucThue NVARCHAR2(10) -- có 2 hình th?c thuê: 'Ngày', 'Gi?'
);

-- T?o b?ng phòng
CREATE TABLE PHONG  (
  MaPhong NUMBER PRIMARY KEY,
  LoaiPhong VARCHAR2(20),
  KieuPhong VARCHAR2(20),
  Gia NUMBER
);

-- T?o b?ng chi ti?t d?t phòng 
CREATE TABLE CHITIETDATPHONG (
  MaHopDong NUMBER NOT NULL,
  MaPhong NUMBER NOT NULL,
  CONSTRAINT PK_CTDP PRIMARY KEY (MaHopDong, MaPhong)
);

-- T?o b?ng trang b?
CREATE TABLE TRANGBI (
  MaTB NUMBER PRIMARY KEY,
  TenTB NVARCHAR2(50),
  GiaTB NUMBER,
  SoLuong INT,
  SLHong INT
);

-- T?o b?ng khuy?n mãi
CREATE TABLE KHUYENMAI (
  MaKM NUMBER PRIMARY KEY,
  TenKM NVARCHAR2(50),
  MoTaKM NVARCHAR2(200),
  NgayBatDau DATE,
  NgayKetThuc DATE,
  PhanTramKM NUMERIC(3,2)
);


-- T?o b?ng hóa don 
CREATE TABLE HOADON (
  MaHD NUMBER PRIMARY KEY,
  MaKM NUMBER,   -- v?i m?i hóa don ch? áp d?ng du?c 1 lo?i khuy?n mãi
  MaHopDong NUMBER,  
  NgayLapHD DATE,
  TongTien NUMBER,
  TienHongTB NUMBER
);

-- T?o b?ng h?ng trang b? 
CREATE TABLE HONGTRANGBI (
  MaTB NUMBER,
  MaHD NUMBER,
  CONSTRAINT PK_HONGTRANGBI PRIMARY KEY (MaTB, MaHD)
);

-- T?o b?ng t?o hóa don
CREATE TABLE TAOHOADON (
  MaHD NUMBER,
  MaNV NUMBER,
  CONSTRAINT PK_TAOHOADON PRIMARY KEY(MaHD, MaNV)
);

-- T?o b?ng ch?m công
CREATE TABLE CHAMCONG (
  MaCC NUMBER PRIMARY KEY,
  MaNV NUMBER,
  SoGioLamThem INT,
  SoNgayDiLam INT
);

-- T?o UINIQUE cho các thu?c tính là duy nh?t trong oracle
-- B?ng Khách hàng
ALTER TABLE KHACHHANG
ADD CONSTRAINT KHACHHANG_UNIQUE_CCCD UNIQUE(CCCD);

ALTER TABLE KHACHHANG
ADD CONSTRAINT KHACHHANG_UNIQUE_SDT UNIQUE(SDT);

-- B?ng Nhân viên
ALTER TABLE NHANVIEN
ADD CONSTRAINT NHANVIEN_UNIQUE_CCCD UNIQUE(CCCD);

ALTER TABLE NHANVIEN
ADD CONSTRAINT NHANVIEN_UNIQUE_SDT UNIQUE(SDT);


-- Xóa sequence
DROP SEQUENCE KhachHang_Seq;
DROP SEQUENCE NhanVien_Seq;
DROP SEQUENCE HopDong_Seq;
DROP SEQUENCE Phong_Seq;
DROP SEQUENCE TrangBi_Seq;
DROP SEQUENCE KhuyenMai_Seq;
DROP SEQUENCE HoaDon_Seq;
DROP SEQUENCE ChamCong_Seq;


-- T?o sequence cho database
CREATE SEQUENCE KhachHang_Seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE NhanVien_Seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE HopDong_Seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE Phong_Seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE TrangBi_Seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE KhuyenMai_Seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE HoaDon_Seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE ChamCong_Seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;



-- T?o khóa ngo?i 
ALTER TABLE HOPDONG
ADD CONSTRAINT FK_HOPDONG_KHACHHANG FOREIGN KEY (MaKH) REFERENCES KHACHHANG(MaKH); 

ALTER TABLE CHITIETDATPHONG
ADD CONSTRAINT FK_CTHDong_HOPDONG FOREIGN KEY (MaHopDong) REFERENCES HOPDONG(MaHopDong);

ALTER TABLE CHITIETDATPHONG
ADD CONSTRAINT FK_CTHDong_PHONG FOREIGN KEY (MaPhong) REFERENCES PHONG(MaPhong);

ALTER TABLE HONGTRANGBI
ADD CONSTRAINT FK_HONGTB_TRANGBI FOREIGN KEY (MaTB) REFERENCES TRANGBI(MaTB);

ALTER TABLE HONGTRANGBI
ADD CONSTRAINT FK_HONGTB_HOADON FOREIGN KEY (MaHD) REFERENCES HOADON(MaHD);

ALTER TABLE HOADON
ADD CONSTRAINT FK_HOADON_KHUYENMAI FOREIGN KEY (MaKM) REFERENCES KHUYENMAI(MaKM);

ALTER TABLE HOADON
ADD CONSTRAINT FK_HOADON_HOPDONG FOREIGN KEY (MaHopDong) REFERENCES HOPDONG(MaHopDong);

ALTER TABLE TAOHOADON
ADD CONSTRAINT FK_TAOHD_NHANVIEN FOREIGN KEY (MaNV) REFERENCES NHANVIEN(MaNV);

ALTER TABLE TAOHOADON
ADD CONSTRAINT FK_TAOHD_HOADON FOREIGN KEY (MaHD) REFERENCES HOADON(MaHD);

ALTER TABLE CHAMCONG
ADD CONSTRAINT FK_CHAMCONG_NHANVIEN FOREIGN KEY (MaNV) REFERENCES NHANVIEN(MaNV);


-- INSERT D? LI?U
-- insert d? li?u khách hàng
INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong) VALUES
(KhachHang_Seq.NEXTVAL, 'Nguy?n Van An', '012345678901', TO_DATE('01-01-1999', 'DD-MM-YYYY'), 'Nam', '123 Lê L?i, Hà N?i', '0912345678', 2);

INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong) VALUES
(KhachHang_Seq.NEXTVAL, 'Tr?n Th? Bình', '012345678902', TO_DATE('15-02-1995', 'DD-MM-YYYY'), 'N?', '456 Tr?n Hung Ð?o, Ðà N?ng', '0912345679', 1);

INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong) VALUES
(KhachHang_Seq.NEXTVAL, 'Lê M?nh Cu?ng', '012345678903', TO_DATE('20-03-1998', 'DD-MM-YYYY'), 'Nam', '789 Nguy?n Trãi, H? Chí Minh', '0912345680', 1);

INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong) VALUES
(KhachHang_Seq.NEXTVAL, 'Ph?m Th? Di?u', '012345678904', TO_DATE('25-04-1995', 'DD-MM-YYYY'), 'N?', '321 Phan Chu Trinh, C?n Tho', '0912345681', 1);

INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong) VALUES
(KhachHang_Seq.NEXTVAL, 'Hoàng Van Huy', '012345678905', TO_DATE('30-05-2000', 'DD-MM-YYYY'), 'Nam', '654 Hai Bà Trung, Hu?', '0912345682', 2);

INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong) VALUES
(KhachHang_Seq.NEXTVAL, 'Vu Th? Linh', '012345678906', TO_DATE('10-06-1997', 'DD-MM-YYYY'), 'N?', '987 Lê Thánh Tông, H? Long', '0912345683', 1);

INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong) VALUES
(KhachHang_Seq.NEXTVAL, 'Ð?ng Van Lâm', '012345678907', TO_DATE('15-07-1993', 'DD-MM-YYYY'), 'Nam', '123 Võ Nguyên Giáp, Nha Trang', '0912345684', 1);

INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong) VALUES
(KhachHang_Seq.NEXTVAL, 'Bùi Th? Hi?n', '012345678908', TO_DATE('20-08-1991', 'DD-MM-YYYY'), 'N?', '456 Lê H?ng Phong, Ðà L?t', '0912345685', 1);

INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong) VALUES
(KhachHang_Seq.NEXTVAL, 'Ngô Tr?n Van Trung', '012345678909', TO_DATE('25-09-2002', 'DD-MM-YYYY'), 'Nam', '789 Tr?n Phú, H?i Phòng', '0912345686', 3);

INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong) VALUES
(KhachHang_Seq.NEXTVAL, 'Ð? Nguy?n Ng?c N?', '012345678910', TO_DATE('30-10-1989', 'DD-MM-YYYY'), 'N?', '321 Nguy?n Hu?, Vung Tàu', '0912345687', 2);

INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong) VALUES
(KhachHang_Seq.NEXTVAL, 'Ph?m Ti?n Hung', '012347078910', TO_DATE('01-10-2006', 'DD-MM-YYYY'), 'Nam', 'Phú Yên', '0912115687', 1);




--insert d? li?u nhân viên
INSERT INTO NHANVIEN (MANV, TENNV, CCCD, GIOITINH, NGAYSINH, DIACHI, LOAINV, TAIKHOAN, MATKHAU, SDT, LUONGCB, TINHTRANG) 
	VALUES (NhanVien_Seq.NEXTVAL, 'Nguy?n Minh Thái', '098765432101', 'Nam', TO_DATE('1-1-1985', 'DD-MM-YYYY'), '123 Ðu?ng ABC, Hà N?i', 'Qu?n lý', 'quanly1', 'qlpass', '0901256567', 1200000, 'Ðang làm');

INSERT INTO DATABASE_DOAN_NPNT.NHANVIEN (MANV, TENNV, CCCD, GIOITINH, NGAYSINH, DIACHI, LOAINV, TAIKHOAN, MATKHAU, SDT, LUONGCB, TINHTRANG) 
	VALUES (NhanVien_Seq.NEXTVAL, 'Hoàng Hào Quang', '456789012345', 'Nam', TO_DATE('10-11-1992', 'DD-MM-YYYY'), '202 Ðu?ng JKL, C?n Tho', 'L? tân', 'letan2', 'ltpass', '0943378901', 700000, 'Ðang làm');

INSERT INTO DATABASE_DOAN_NPNT.NHANVIEN (MANV, TENNV, CCCD, GIOITINH, NGAYSINH, DIACHI, LOAINV, TAIKHOAN, MATKHAU, SDT, LUONGCB, TINHTRANG) 
	VALUES (NhanVien_Seq.NEXTVAL, 'Ngô Nguy?n T? Nhu', '567890123456', 'N?', TO_DATE('5-2-2000', 'DD-MM-YYYY'), '303 Ðu?ng MNO, Hu?', 'L? tân', 'letan3', 'ltpass', '0956788112', 650000, 'Ðang làm');

INSERT INTO DATABASE_DOAN_NPNT.NHANVIEN (MANV, TENNV, CCCD, GIOITINH, NGAYSINH, DIACHI, LOAINV, TAIKHOAN, MATKHAU, SDT, LUONGCB, TINHTRANG) 
	VALUES (NhanVien_Seq.NEXTVAL, 'Vu Th? Xuân', '098765432102', 'N?', TO_DATE('15-5-1990', 'DD-MM-YYYY'), '456 Ðu?ng XYZ, TP. H? Chí Minh', 'T?p V?', NULL, NULL, '0912444568', 300000, 'Ðang làm');

INSERT INTO DATABASE_DOAN_NPNT.NHANVIEN (MANV, TENNV, CCCD, GIOITINH, NGAYSINH, DIACHI, LOAINV, TAIKHOAN, MATKHAU, SDT, LUONGCB, TINHTRANG) 
	VALUES (NhanVien_Seq.NEXTVAL, 'Lê Van Hùng', '234567890123', 'Nam', TO_DATE('22-3-1988', 'DD-MM-YYYY'), '789 Ðu?ng DEF, Ðà N?ng', 'T?p V?', NULL, NULL, '0923456711', 350000, 'Ðang làm');

INSERT INTO DATABASE_DOAN_NPNT.NHANVIEN (MANV, TENNV, CCCD, GIOITINH, NGAYSINH, DIACHI, LOAINV, TAIKHOAN, MATKHAU, SDT, LUONGCB, TINHTRANG) 
	VALUES (NhanVien_Seq.NEXTVAL, 'Ph?m Ng?c Nhung', '345678901234', 'N?', TO_DATE('30-7-1999', 'DD-MM-YYYY'), '101 Ðu?ng GHI, H?i Phòng', 'L? tân', 'letan6', 'ltpass', '0930967890', 500000, 'Ðang làm');



--Insert d? li?u h?p d?ng thuê phòng
-- H?p d?ng cho Nguy?n Van An (2 h?p d?ng)
INSERT INTO HOPDONG (MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, TinhTrangHD, SoNguoiLon, SoTreEm, TriGiaHD, HinhThucThue) VALUES
(HopDong_Seq.NEXTVAL, 1, TO_TIMESTAMP('10-01-2023 09:15:23', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('12-01-2023 14:00:00', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('15-01-2023 12:00:00', 'DD-MM-YYYY HH24:MI:SS'), 'Ðã thanh toán', 2, 1, 1000000, 'Ngày');

INSERT INTO HOPDONG (MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, TinhTrangHD, SoNguoiLon, SoTreEm, TriGiaHD, HinhThucThue) VALUES
(HopDong_Seq.NEXTVAL, 1, TO_TIMESTAMP('15-03-2023 14:23:45', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('17-03-2023 14:00:00', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('20-03-2023 12:00:00', 'DD-MM-YYYY HH24:MI:SS'), 'Ðã thanh toán', 2, 2, 2000000, 'Ngày');

-- H?p d?ng cho Tr?n Th? Bình (1 h?p d?ng)
INSERT INTO HOPDONG (MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, TinhTrangHD, SoNguoiLon, SoTreEm, TriGiaHD, HinhThucThue) VALUES
(HopDong_Seq.NEXTVAL, 2, TO_TIMESTAMP('01-02-2023 08:45:12', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('03-02-2023 14:00:00', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('06-02-2023 12:00:00', 'DD-MM-YYYY HH24:MI:SS'), 'Ðã thanh toán', 1, 0, 1500000, 'Ngày');

-- H?p d?ng cho Lê M?nh Cu?ng (1 h?p d?ng)
INSERT INTO HOPDONG (MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, TinhTrangHD, SoNguoiLon, SoTreEm, TriGiaHD, HinhThucThue) VALUES
(HopDong_Seq.NEXTVAL, 3, TO_TIMESTAMP('05-04-2023 10:32:55', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('07-04-2023 14:00:00', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('10-04-2023 12:00:00', 'DD-MM-YYYY HH24:MI:SS'), 'Ðã thanh toán', 2, 1, 1800000, 'Ngày');

-- H?p d?ng cho Ph?m Th? Di?u (1 h?p d?ng)
INSERT INTO HOPDONG (MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, TinhTrangHD, SoNguoiLon, SoTreEm, TriGiaHD, HinhThucThue) VALUES
(HopDong_Seq.NEXTVAL, 4, TO_TIMESTAMP('15-05-2023 13:05:44', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('17-05-2023 14:00:00', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('20-05-2023 12:00:00', 'DD-MM-YYYY HH24:MI:SS'), 'Ðã thanh toán', 1, 2, 1600000, 'Ngày');

-- H?p d?ng cho Hoàng Van Huy (2 h?p d?ng)
INSERT INTO HOPDONG (MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, TinhTrangHD, SoNguoiLon, SoTreEm, TriGiaHD, HinhThucThue) VALUES
(HopDong_Seq.NEXTVAL, 5, TO_TIMESTAMP('01-06-2023 11:25:30', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('03-06-2023 14:00:00', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('06-06-2023 12:00:00', 'DD-MM-YYYY HH24:MI:SS'), 'Ðã thanh toán', 3, 0, 2200000, 'Ngày');

INSERT INTO HOPDONG (MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, TinhTrangHD, SoNguoiLon, SoTreEm, TriGiaHD, HinhThucThue) VALUES
(HopDong_Seq.NEXTVAL, 5, TO_TIMESTAMP('10-07-2023 09:45:50', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('12-07-2023 14:00:00', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('15-07-2023 12:00:00', 'DD-MM-YYYY HH24:MI:SS'), 'Ðã thanh toán', 2, 2, 2300000, 'Ngày');

-- H?p d?ng cho Vu Th? Linh (1 h?p d?ng)
INSERT INTO HOPDONG (MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, TinhTrangHD, SoNguoiLon, SoTreEm, TriGiaHD, HinhThucThue) VALUES
(HopDong_Seq.NEXTVAL, 6, TO_TIMESTAMP('20-08-2023 14:55:22', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('22-08-2023 14:00:00', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('25-08-2023 12:00:00', 'DD-MM-YYYY HH24:MI:SS'), 'Ðã thanh toán', 1, 0, 1300000, 'Ngày');

-- H?p d?ng cho Ð?ng Van Lâm (1 h?p d?ng)
INSERT INTO HOPDONG (MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, TinhTrangHD, SoNguoiLon, SoTreEm, TriGiaHD, HinhThucThue) VALUES
(HopDong_Seq.NEXTVAL, 7, TO_TIMESTAMP('05-09-2023 08:40:10', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('07-09-2023 14:00:00', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('10-09-2023 12:00:00', 'DD-MM-YYYY HH24:MI:SS'), 'Ðã thanh toán', 3, 1, 2500000, 'Ngày');

-- H?p d?ng cho Bùi Th? Hi?n (1 h?p d?ng)
INSERT INTO HOPDONG (MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, TinhTrangHD, SoNguoiLon, SoTreEm, TriGiaHD, HinhThucThue) VALUES
(HopDong_Seq.NEXTVAL, 8, TO_TIMESTAMP('12-10-2023 12:22:33', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('14-10-2023 14:00:00', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('17-10-2023 12:00:00', 'DD-MM-YYYY HH24:MI:SS'), 'Ðã thanh toán', 2, 2, 2400000, 'Ngày');

-- H?p d?ng cho Ngô Tr?n Van Trung (3 h?p d?ng)
INSERT INTO HOPDONG (MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, TinhTrangHD, SoNguoiLon, SoTreEm, TriGiaHD, HinhThucThue) VALUES
(HopDong_Seq.NEXTVAL, 9, TO_TIMESTAMP('01-11-2023 09:12:01', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('03-11-2023 14:00:00', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('06-11-2023 12:00:00', 'DD-MM-YYYY HH24:MI:SS'), 'Ðã thanh toán', 2, 1, 1700000, 'Ngày');

INSERT INTO HOPDONG (MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, TinhTrangHD, SoNguoiLon, SoTreEm, TriGiaHD, HinhThucThue) VALUES
(HopDong_Seq.NEXTVAL, 9, TO_TIMESTAMP('10-12-2023 13:35:12', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('12-12-2023 14:00:00', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('15-12-2023 12:00:00', 'DD-MM-YYYY HH24:MI:SS'), 'Ðã thanh toán', 1, 1, 1600000, 'Ngày');

INSERT INTO HOPDONG (MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, TinhTrangHD, SoNguoiLon, SoTreEm, TriGiaHD, HinhThucThue) VALUES
(HopDong_Seq.NEXTVAL, 9, TO_TIMESTAMP('20-12-2023 10:50:30', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('22-12-2023 14:00:00', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('25-12-2023 12:00:00', 'DD-MM-YYYY HH24:MI:SS'), 'Ðã thanh toán', 3, 2, 3000000, 'Ngày');

-- H?p d?ng cho Ð? Nguy?n Ng?c N? (2 h?p d?ng)
INSERT INTO HOPDONG (MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, TinhTrangHD, SoNguoiLon, SoTreEm, TriGiaHD, HinhThucThue) VALUES
(HopDong_Seq.NEXTVAL, 10, TO_TIMESTAMP('12-01-2024 12:22:33', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('14-01-2024 14:00:00', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('17-01-2024 12:00:00', 'DD-MM-YYYY HH24:MI:SS'), 'Ðã thanh toán', 1, 1, 1900000, 'Ngày');

INSERT INTO HOPDONG (MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, TinhTrangHD, SoNguoiLon, SoTreEm, TriGiaHD, HinhThucThue) VALUES
(HopDong_Seq.NEXTVAL, 10, TO_TIMESTAMP('12-03-2024 19:05:54', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('16-03-2024 14:00:00', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('20-03-2024 12:00:00', 'DD-MM-YYYY HH24:MI:SS'), 'Ðã thanh toán', 3, 0, 2900000, 'Ngày');

-- H?p d?ng cho Nguy?n Ti?n Hung (1 h?p d?ng)
INSERT INTO HOPDONG (MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, TinhTrangHD, SoNguoiLon, SoTreEm, TriGiaHD, HinhThucThue) VALUES
(HopDong_Seq.NEXTVAL, 18, TO_TIMESTAMP('23-03-2023 19:05:54', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('27-03-2023 14:00:00', 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP('29-03-2023 12:00:00', 'DD-MM-YYYY HH24:MI:SS'), 'Ðã thanh toán', 3, 0, 2900000, 'Ngày');


-- insert d? li?u phòng
-- T?ng 1
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (101, 'VIP', 'Ðon', 1000000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (102, 'VIP', 'Ðôi', 1800000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (103, 'Thu?ng', 'Ðon', 700000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (104, 'Thu?ng', 'Ðôi', 1200000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (105, 'VIP', 'Ðon', 1000000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (106, 'VIP', 'Ðôi', 1800000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (107, 'Thu?ng', 'Ðon', 700000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (108, 'Thu?ng', 'Ðôi', 1200000);

-- T?ng 2
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (201, 'VIP', 'Ðon', 1000000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (202, 'VIP', 'Ðôi', 1800000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (203, 'Thu?ng', 'Ðon', 700000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (204, 'Thu?ng', 'Ðôi', 1200000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (205, 'VIP', 'Ðon', 1000000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (206, 'VIP', 'Ðôi', 1800000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (207, 'Thu?ng', 'Ðon', 700000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (208, 'Thu?ng', 'Ðôi', 1200000);

-- T?ng 3
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (301, 'VIP', 'Ðon', 1200000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (302, 'VIP', 'Ðôi', 2000000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (303, 'Thu?ng', 'Ðon', 900000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (304, 'Thu?ng', 'Ðôi', 1600000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (305, 'VIP', 'Ðon', 1200000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (306, 'VIP', 'Ðôi', 2000000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (307, 'Thu?ng', 'Ðon', 900000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (308, 'Thu?ng', 'Ðôi', 1600000);

-- T?ng 4
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (401, 'VIP', 'Ðon', 1200000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (402, 'VIP', 'Ðôi', 2000000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (403, 'Thu?ng', 'Ðon', 900000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (404, 'Thu?ng', 'Ðôi', 1600000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (405, 'VIP', 'Ðon', 1200000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (406, 'VIP', 'Ðôi', 2000000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (407, 'Thu?ng', 'Ðon', 900000);
INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia) VALUES (408, 'Thu?ng', 'Ðôi', 1600000);


-- inset chi ti?t d?t phòng 
-- H?p d?ng cho Nguy?n Van An
INSERT INTO CHITIETDATPHONG (MaHopDong, MaPhong) VALUES (1, 101);
INSERT INTO CHITIETDATPHONG (MaHopDong, MaPhong) VALUES (1, 202);

INSERT INTO CHITIETDATPHONG (MaHopDong, MaPhong) VALUES (2, 103);
INSERT INTO CHITIETDATPHONG (MaHopDong, MaPhong) VALUES (2, 204);

-- H?p d?ng cho Tr?n Th? Bình
INSERT INTO CHITIETDATPHONG (MaHopDong, MaPhong) VALUES (3, 305);

-- H?p d?ng cho Lê M?nh Cu?ng
INSERT INTO CHITIETDATPHONG (MaHopDong, MaPhong) VALUES (4, 206);

-- H?p d?ng cho Ph?m Th? Di?u
INSERT INTO CHITIETDATPHONG (MaHopDong, MaPhong) VALUES (5, 207);

-- H?p d?ng cho Hoàng Van Huy
INSERT INTO CHITIETDATPHONG (MaHopDong, MaPhong) VALUES (6, 208);
INSERT INTO CHITIETDATPHONG (MaHopDong, MaPhong) VALUES (6, 101);

INSERT INTO CHITIETDATPHONG (MaHopDong, MaPhong) VALUES (7, 302);
INSERT INTO CHITIETDATPHONG (MaHopDong, MaPhong) VALUES (7, 303);

-- H?p d?ng cho Vu Th? Linh
INSERT INTO CHITIETDATPHONG (MaHopDong, MaPhong) VALUES (8, 304);

-- H?p d?ng cho Ð?ng Van Lâm
INSERT INTO CHITIETDATPHONG (MaHopDong, MaPhong) VALUES (9, 305);

-- H?p d?ng cho Bùi Th? Hi?n
INSERT INTO CHITIETDATPHONG (MaHopDong, MaPhong) VALUES (10, 306);

-- H?p d?ng cho Ngô Tr?n Van Trung
INSERT INTO CHITIETDATPHONG (MaHopDong, MaPhong) VALUES (11, 307);

INSERT INTO CHITIETDATPHONG (MaHopDong, MaPhong) VALUES (12, 308);
INSERT INTO CHITIETDATPHONG (MaHopDong, MaPhong) VALUES (12, 401);

INSERT INTO CHITIETDATPHONG (MaHopDong, MaPhong) VALUES (13, 402);

-- H?p d?ng cho Ð? Nguy?n Ng?c N?
INSERT INTO CHITIETDATPHONG (MaHopDong, MaPhong) VALUES (14, 103);
INSERT INTO CHITIETDATPHONG (MaHopDong, MaPhong) VALUES (14, 204);

INSERT INTO CHITIETDATPHONG (MaHopDong, MaPhong) VALUES (15, 305);
INSERT INTO CHITIETDATPHONG (MaHopDong, MaPhong) VALUES (15, 206);



-- Thêm d? li?u vào b?ng TRANGBI s? d?ng sequence
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Tivi', 5000000, 31, 1);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'T? l?nh', 7000000, 32, 0);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Ði?u hòa', 10000000, 30, 2);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Máy gi?t', 6000000, 15, 1);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Lò vi sóng', 3000000, 15, 1);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Máy s?y tóc', 200000, 45, 3);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Bàn ?i', 400000, 39, 1);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Bình nu?c nóng', 2000000, 36, 0);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Máy pha cà phê', 1500000, 16, 0);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Qu?t', 500000, 32, 2);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Khan t?m', 60000, 59, 5);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Dép', 20000, 70, 2);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Bình bông', 400000, 15, 2);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Ghê sofa', 4000000, 15, 0);


-- Thêm d? li?u vào b?ng KHUYENMAI s? d?ng sequence
INSERT INTO KHUYENMAI (MaKM, TenKM, MoTaKM, NgayBatDau, NgayKetThuc, PhanTramKM) VALUES 
(KhuyenMai_Seq.NEXTVAL, 'Khuy?n mãi mùa hè', 'Gi?m giá 10% cho t?t c? các phòng trong tháng 6 và 7', TO_DATE('01-06-2024', 'DD-MM-YYYY'), TO_DATE('31-07-2024', 'DD-MM-YYYY'), 0.1);

INSERT INTO KHUYENMAI (MaKM, TenKM, MoTaKM, NgayBatDau, NgayKetThuc, PhanTramKM) VALUES 
(KhuyenMai_Seq.NEXTVAL, 'Khuy?n mãi cu?i tu?n', 'Gi?m giá 5% cho các ngày cu?i tu?n t? th? 6 d?n ch? nh?t', TO_DATE('01-01-2024', 'DD-MM-YYYY'), TO_DATE('31-12-2024', 'DD-MM-YYYY'), 0.05);

INSERT INTO KHUYENMAI (MaKM, TenKM, MoTaKM, NgayBatDau, NgayKetThuc, PhanTramKM) VALUES 
(KhuyenMai_Seq.NEXTVAL, 'Khuy?n mãi sinh nh?t', 'Gi?m giá 15% trong ngày sinh nh?t c?a khách hàng', TO_DATE('01-01-2024', 'DD-MM-YYYY'), TO_DATE('31-12-2024', 'DD-MM-YYYY'), 0.15);

INSERT INTO KHUYENMAI (MaKM, TenKM, MoTaKM, NgayBatDau, NgayKetThuc, PhanTramKM) VALUES 
(KhuyenMai_Seq.NEXTVAL, 'Khuy?n mãi mùa dông', 'Gi?m giá 10% cho t?t c? các phòng trong tháng 12 và 1', TO_DATE('01-12-2024', 'DD-MM-YYYY'), TO_DATE('31-01-2025', 'DD-MM-YYYY'), 0.1);

-- Khuy?n mãi cho Ngày Qu?c t? Ph? n? (8/3)
INSERT INTO KHUYENMAI (MaKM, TenKM, MoTaKM, NgayBatDau, NgayKetThuc, PhanTramKM) VALUES 
(KhuyenMai_Seq.NEXTVAL, 'Khuy?n mãi Ngày Ph? n? 8/3', 'Chúc m?ng Ngày Qu?c t? Ph? n?! Chuong trình khuy?n mãi dành riêng cho phái d?p v?i uu dãi gi?m giá 20% cho t?t c? các ph? n? luu trú trong ngày 8/3.', 
TO_DATE('08-03-2024', 'DD-MM-YYYY'), TO_DATE('08-03-2024', 'DD-MM-YYYY'), 0.2);

-- Khuy?n mãi cho Ngày Qu?c t? thi?u nhi (1/6)
INSERT INTO KHUYENMAI (MaKM, TenKM, MoTaKM, NgayBatDau, NgayKetThuc, PhanTramKM) VALUES 
(KhuyenMai_Seq.NEXTVAL, 'Khuy?n mãi Ngày Lao d?ng 1/6', 'Chúc m?ng Ngày Qu?c t? Thi?u nhi! Chuong trình khuy?n mãi d?c bi?t dành cho t?t c? các khách hàng v?i uu dãi gi?m giá 15% trong c? tu?n t? 1/6 d?n 7/6.', 
TO_DATE('01-06-2024', 'DD-MM-YYYY'), TO_DATE('07-06-2024', 'DD-MM-YYYY'), 0.15);



-- Hóa don cho h?p d?ng c?a Nguy?n Van An (H?p d?ng s? 1)
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES 
(HoaDon_Seq.NEXTVAL, NULL, 1, TO_DATE('15-01-2023', 'DD-MM-YYYY'), 3000000, 0);

-- Hóa don cho h?p d?ng c?a Nguy?n Van An (H?p d?ng s? 2)
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES 
(HoaDon_Seq.NEXTVAL, NULL, 2, TO_DATE('20-03-2023', 'DD-MM-YYYY'), 6000000, 0);

-- Hóa don cho h?p d?ng c?a Tr?n Th? Bình
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES 
(HoaDon_Seq.NEXTVAL, NULL, 3, TO_DATE('06-02-2023', 'DD-MM-YYYY'), 4500000, 0);

-- Hóa don cho h?p d?ng c?a Lê M?nh Cu?ng
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES 
(HoaDon_Seq.NEXTVAL, NULL, 4, TO_DATE('10-04-2023', 'DD-MM-YYYY'), 5800000, 200000);

-- Hóa don cho h?p d?ng c?a Ph?m Th? Di?u
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES 
(HoaDon_Seq.NEXTVAL, NULL, 5, TO_DATE('20-05-2023', 'DD-MM-YYYY'), 3600000, 0);

-- Hóa don cho h?p d?ng c?a Hoàng Van Huy (H?p d?ng s? 1)
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES 
(HoaDon_Seq.NEXTVAL, NULL, 6, TO_DATE('06-06-2023', 'DD-MM-YYYY'), 6200000, 0);

-- Hóa don cho h?p d?ng c?a Hoàng Van Huy (H?p d?ng s? 2)
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES 
(HoaDon_Seq.NEXTVAL, NULL, 7, TO_DATE('15-07-2023', 'DD-MM-YYYY'), 6300000, 50000);

-- Hóa don cho h?p d?ng c?a Vu Th? Linh
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES 
(HoaDon_Seq.NEXTVAL, NULL, 8, TO_DATE('25-08-2023', 'DD-MM-YYYY'), 3300000, 0);

-- Hóa don cho h?p d?ng c?a Ð?ng Van Lâm
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES 
(HoaDon_Seq.NEXTVAL, NULL, 9, TO_DATE('10-09-2023', 'DD-MM-YYYY'), 6500000, 0);

-- Hóa don cho h?p d?ng c?a Bùi Th? Hi?n
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES 
(HoaDon_Seq.NEXTVAL, NULL, 10, TO_DATE('17-10-2023', 'DD-MM-YYYY'), 6400000, 0);

-- Hóa don cho h?p d?ng c?a Ngô Tr?n Van Trung (H?p d?ng s? 1)
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES 
(HoaDon_Seq.NEXTVAL, NULL, 11, TO_DATE('06-11-2023', 'DD-MM-YYYY'), 4700000, 0);

-- Hóa don cho h?p d?ng c?a Ngô Tr?n Van Trung (H?p d?ng s? 2)
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES 
(HoaDon_Seq.NEXTVAL, NULL, 12, TO_DATE('15-12-2023', 'DD-MM-YYYY'), 4600000, 0);

-- Hóa don cho h?p d?ng c?a Ngô Tr?n Van Trung (H?p d?ng s? 3)
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES 
(HoaDon_Seq.NEXTVAL, NULL, 13, TO_DATE('25-12-2023', 'DD-MM-YYYY'), 9000000, 0);

-- Hóa don cho h?p d?ng c?a Ð? Nguy?n Ng?c N? (H?p d?ng s? 1)
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES 
(HoaDon_Seq.NEXTVAL, NULL, 14, TO_DATE('17-01-2024', 'DD-MM-YYYY'), 5900000, 0);

-- Hóa don cho h?p d?ng c?a Ð? Nguy?n Ng?c N? (H?p d?ng s? 2)
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES 
(HoaDon_Seq.NEXTVAL, NULL, 15, TO_DATE('20-03-2024', 'DD-MM-YYYY'), 4300000, 0);

-- Hóa don cho h?p d?ng c?a Ph?m Ti?n Hung (H?p d?ng s? 1)
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES 
(HoaDon_Seq.NEXTVAL, NULL, 23, TO_DATE('30-03-2023', 'DD-MM-YYYY'), 4300000, 0);


-- insert d? li?u b?ng h?ng trang b?
INSERT INTO HONGTRANGBI (MAHD, MATB) VALUES (4, 6);
INSERT INTO HONGTRANGBI (MAHD, MATB) VALUES (7, 10);


-- insert d? li?u t?o hóa don
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (1, 2);
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (2, 3);
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (3, 2);
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (4, 3);
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (5, 3);
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (6, 2);
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (7, 2);
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (8, 2);
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (9, 2);



-- Insert d? li?u ch?m công cho 6 nhân viên
INSERT INTO CHAMCONG (MaCC, MaNV, SoGioLamThem, SoNgayDiLam) VALUES (ChamCong_Seq.NEXTVAL, 1, 5, 18);
INSERT INTO CHAMCONG (MaCC, MaNV, SoGioLamThem, SoNgayDiLam) VALUES (ChamCong_Seq.NEXTVAL, 2, 2, 4);
INSERT INTO CHAMCONG (MaCC, MaNV, SoGioLamThem, SoNgayDiLam) VALUES (ChamCong_Seq.NEXTVAL, 3, 0, 0);
INSERT INTO CHAMCONG (MaCC, MaNV, SoGioLamThem, SoNgayDiLam) VALUES (ChamCong_Seq.NEXTVAL, 4, 3, 8);
INSERT INTO CHAMCONG (MaCC, MaNV, SoGioLamThem, SoNgayDiLam) VALUES (ChamCong_Seq.NEXTVAL, 5, 0, 0);
INSERT INTO CHAMCONG (MaCC, MaNV, SoGioLamThem, SoNgayDiLam) VALUES (ChamCong_Seq.NEXTVAL, 6, 0, 0);






SELECT * FROM HOADON h;


SELECT P.LOAIPHONG, P.KIEUPHONG, P.GIA , C.MAHOPDONG
FROM HOPDONG h
  JOIN CHITIETDATPHONG c ON C.MAHOPDONG = H.MAHOPDONG
  JOIN PHONG p ON C.MAPHONG = P.MAPHONG
  JOIN HOADON hd ON H.MAHOPDONG = HD.MAHOPDONG; 

-- Xu?t ra s? khách hàng trong tháng
SELECT SUM(SONGUOILON) AS tong_so_nguoi_lon, 
       EXTRACT(MONTH FROM TGNHANPHONG) AS thang, 
       EXTRACT(YEAR FROM TGNHANPHONG) AS nam
FROM HOPDONG
GROUP BY EXTRACT(MONTH FROM TGNHANPHONG), EXTRACT(YEAR FROM TGNHANPHONG)
ORDER BY EXTRACT(YEAR FROM TGNHANPHONG), EXTRACT(MONTH FROM TGNHANPHONG);

-- Xu?t ra d?t onl hay off
SELECT HINHTHUCTHUE, COUNT(H.MAHOPDONG) so_Hop_dong, EXTRACT(MONTH FROM H.NGAYLAPHOPDONG) AS thang, EXTRACT(YEAR FROM H.NGAYLAPHOPDONG) AS nam
FROM HOPDONG h
GROUP BY H.HINHTHUCTHUE, EXTRACT(MONTH FROM H.NGAYLAPHOPDONG), EXTRACT(YEAR FROM H.NGAYLAPHOPDONG)
ORDER BY EXTRACT(YEAR FROM H.NGAYLAPHOPDONG), EXTRACT(MONTH FROM H.NGAYLAPHOPDONG);

-- Xu?t doanh thu theo nam
SELECT SUM(H.TONGTIEN) AS doanh_thu, EXTRACT(YEAR FROM H.NGAYLAPHD) AS nam
FROM HOADON h
GROUP BY EXTRACT(YEAR FROM H.NGAYLAPHD);








COMMIT;
--================================== TRIGGER ===================================================

-- Ki?m tra tu?i khách hàng ph?i trên 18 tu?i
CREATE OR REPLACE TRIGGER Check_TuoiKhachHang
BEFORE INSERT ON KHACHHANG
FOR EACH ROW
DECLARE
    customer_age NUMBER;
BEGIN
    -- Tính tu?i c?a khách hàng t? ngày sinh
    customer_age := TRUNC(MONTHS_BETWEEN(SYSDATE, :NEW.NgaySinh) / 12);

    -- Ki?m tra tu?i c?a khách hàng
    IF customer_age < 18 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Khách hàng ph?i d? 18 tu?i tr? lên.');
    END IF;
END;
/

-- Ki?m tra gi?i tính khách hàng ch? nam ho?c n?
CREATE OR REPLACE TRIGGER Check_gioi_tinh_KH
BEFORE INSERT OR UPDATE ON KHACHHANG
FOR EACH ROW
BEGIN
    IF (:NEW.GioiTinh != 'Nam' AND :NEW.GioiTinh != 'N?') THEN
        RAISE_APPLICATION_ERROR(-20001, 'Gi?i tính ch? có th? là "Nam" ho?c "N?".');
    END IF;
END;
/


-- Ki?m tra th?i gian tr? phòng ph?i sau th?i gian nh?n phòng
CREATE OR REPLACE TRIGGER Check_ThoiGianLapHopDong
BEFORE INSERT OR UPDATE ON HOPDONG
FOR EACH ROW
BEGIN
  IF :NEW.TGTraPhong <= :NEW.TGNhanPhong THEN
    RAISE_APPLICATION_ERROR(-20001, 'Th?i gian tr? phòng ph?i sau th?i gian nh?n phòng.');
  END IF;
END;
/


-- Ki?m tra Lo?i phòng ch? du?c VIP ho?c Thu?ng
CREATE OR REPLACE TRIGGER Check_LoaiPhong
BEFORE INSERT OR UPDATE ON PHONG
FOR EACH ROW
BEGIN
    IF (:NEW.LoaiPhong != 'VIP' AND :NEW.LoaiPhong != 'Thu?ng') THEN
        RAISE_APPLICATION_ERROR(-20001, 'Lo?i phòng ch? có th? là "VIP" ho?c "Thu?ng".');
    END IF;
END;
/

-- Ki?m tra Ki?u phòng ch? du?c Ðôi ho?c Ðon
CREATE OR REPLACE TRIGGER Check_KieuPhong
BEFORE INSERT OR UPDATE ON PHONG
FOR EACH ROW
BEGIN
    IF (:NEW.KieuPhong != 'Ðôi' AND :NEW.KieuPhong != 'Ðon') THEN
        RAISE_APPLICATION_ERROR(-20001, 'Ki?u phòng ch? có th? là Ðôi ho?c Ðon.');
    END IF;
END;
/

-- Ki?m tra ngày l?p hóa don ph?i sau ngày nh?n phòng
CREATE OR REPLACE TRIGGER Check_NgayLapHD 
BEFORE INSERT OR UPDATE ON HOADON
FOR EACH ROW
BEGIN
    IF (:NEW.NgayLapHD <= (SELECT TGNhanPhong FROM HOPDONG WHERE MaHopDong = :NEW.MaHopDong)) THEN
        RAISE_APPLICATION_ERROR(-20001, 'Ngày l?p hóa don ph?i sau ngày nh?n phòng.');
    END IF;
END;
/

-- Ki?m tra ngày K?t thúc khuy?n mãi ph?i sau ngày l?p khuy?n mãi
CREATE OR REPLACE TRIGGER Check_ThoiGianKM 
BEFORE INSERT OR UPDATE ON KHUYENMAI
FOR EACH ROW
BEGIN
  IF :NEW.NGAYKETTHUC <= :NEW.NGAYBATDAU THEN
    RAISE_APPLICATION_ERROR(-20001, 'Th?i gian k?t thúc ph?i sau th?i gian b?t d?u khuy?n mãi.');
  END IF;
END;
/

-- Ki?m tra nhân viên ch? có 3 lo?i NV 
CREATE OR REPLACE TRIGGER Check_LoaiNhanVien 
BEFORE INSERT OR UPDATE ON NHANVIEN
FOR EACH ROW
BEGIN
    IF (:NEW.LOAINV != 'L? tân' AND :NEW.LOAINV != 'T?p V?' AND :NEW.LOAINV != 'Qu?n lý' ) THEN
        RAISE_APPLICATION_ERROR(-20001, 'Lo?i phòng ch? có th? là "L? tân" ho?c "T?p V?" ho?c "Qu?n lý".');
    END IF;
END;
/

-- Ki?m tra s? lu?ng h?ng ph?i nh? hon ho?c b?ng s? lu?ng trang b?
CREATE OR REPLACE TRIGGER Check_SoLuongHong 
BEFORE INSERT OR UPDATE ON TRANGBI
FOR EACH ROW
BEGIN
    IF :NEW.SoLuongHong > :NEW.SoLuong THEN
        RAISE_APPLICATION_ERROR(-20001, 'S? lu?ng h?ng c?a trang b? ph?i nh? hon ho?c b?ng s? lu?ng t?ng trang b?.');
    END IF;
END;
/


-- Thuê phòng qua app s? có hình th?c thuê là "Ngày" và tình tr?ng m?c d?nh là "Chua xác nh?n"
CREATE OR REPLACE TRIGGER Check_taohopdongonline 
AFTER INSERT ON HOPDONG
FOR EACH ROW
BEGIN
    IF (:NEW.HinhThucThue = 'Ngày') THEN
        UPDATE HOPDONG
        SET TinhTrangHD = 'Chua xác nh?n'
        WHERE MaHopDong = :NEW.MaHopDong;
    END IF;
END;
/

-- Ki?m tra thông tin khách hàng nh?p qua app ph?i h?p l?
CREATE OR REPLACE TRIGGER Check_thongtinkhonline 
BEFORE INSERT ON KHACHHANG
FOR EACH ROW
BEGIN
    IF LENGTH(:NEW.CCCD) != 12 THEN
        RAISE_APPLICATION_ERROR(-20002, 'S? CCCD ph?i có dúng 12 ký t?.');
    END IF;
    IF NOT REGEXP_LIKE(:NEW.CCCD, '^[0-9]{12}$') THEN
        RAISE_APPLICATION_ERROR(-20003, 'S? CCCD ph?i là chu?i s?.');
    END IF;
    IF LENGTH(:NEW.SDT) != 10 THEN
        RAISE_APPLICATION_ERROR(-20007, 'S? di?n tho?i ph?i có dúng 10 ký t?.');
    END IF;
    IF NOT REGEXP_LIKE(:NEW.SDT, '^[0-9]{10}$') THEN
        RAISE_APPLICATION_ERROR(-20008, 'S? di?n tho?i ph?i là chu?i s?.');
    END IF;
END;
/

-- ki?m tra t?i cùng 1 th?i di?m ch? thuê du?c 1 phòng
CREATE OR REPLACE TRIGGER Check_PhongDatDuocMotHopDong
BEFORE INSERT ON HOPDONG
FOR EACH ROW
DECLARE
    v_count NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_count
    FROM CHITIETDATPHONG
    WHERE MaPhong = :NEW.MaPhong
    AND (TGNhanPhong BETWEEN :NEW.TGNhanPhong AND :NEW.TGTraPhong
        OR TGTraPhong BETWEEN :NEW.TGNhanPhong AND :NEW.TGTraPhong);

    IF v_count > 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Phòng dã du?c d?t trong m?t h?p d?ng khác trong kho?ng th?i gian này.');
    END IF;
END;
/








--========================================= PROCEDURE =======================================

-- Procedure: Them_khachhang
CREATE OR REPLACE PROCEDURE Them_khachhang(
    p_TenKH NVARCHAR2,
    p_CCCD CHAR,
    p_NgaySinh DATE,
    p_GioiTinh NVARCHAR2,
    p_DiaChi NVARCHAR2,
    p_SDT CHAR
) IS
BEGIN
    INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong)
    VALUES (KhachHang_Seq.NEXTVAL, p_TenKH, p_CCCD, p_NgaySinh, p_GioiTinh, p_DiaChi, p_SDT, 0);
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20004, 'Có l?i x?y ra trong quá trình thêm khách hàng.');
END Them_khachhang;
/


-- Procedure: Them_nhanvien
CREATE OR REPLACE PROCEDURE Them_nhanvien(
    p_TenNV NVARCHAR2,
    p_CCCD CHAR,
    p_NgaySinh DATE,
    p_GioiTinh NVARCHAR2,
    p_DiaChi NVARCHAR2,
    p_SDT CHAR,
    p_LoaiNV NVARCHAR2,
    p_LuongCB NUMBER
) IS
BEGIN
    INSERT INTO NHANVIEN (MaNV, TenNV, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, LoaiNV, LuongCB)
    VALUES (NhanVien_Seq.NEXTVAL, p_TenNV, p_CCCD, p_NgaySinh, p_GioiTinh, p_DiaChi, p_SDT, p_LoaiNV, p_LuongCB);
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20005, 'Có l?i x?y ra trong quá trình thêm nhân viên.');
END Them_nhanvien;
/


-- Procedure: Them_phong
CREATE OR REPLACE PROCEDURE Them_phong(
    p_LoaiPhong VARCHAR2,
    p_KieuPhong VARCHAR2,
    p_Gia NUMBER
) IS
BEGIN
    INSERT INTO PHONG (MaPhong, LoaiPhong, KieuPhong, Gia)
    VALUES (Phong_Seq.NEXTVAL, p_LoaiPhong, p_KieuPhong, p_Gia);
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20006, 'Có l?i x?y ra trong quá trình thêm phòng.');
END Them_phong;
/


-- Procedure: Them_khuyenmai
CREATE OR REPLACE PROCEDURE Them_khuyenmai(
    p_TenKM NVARCHAR2,
    p_MoTaKM NVARCHAR2,
    p_NgayBatDau DATE,
    p_NgayKetThuc DATE,
    p_PhanTramKM NUMERIC
)
IS
BEGIN
    -- Ki?m tra ngày b?t d?u và ngày k?t thúc
    IF p_NgayKetThuc < p_NgayBatDau THEN
        RAISE_APPLICATION_ERROR(-20003, 'Ngày k?t thúc không th? tru?c ngày b?t d?u');
    END IF;

    -- Thêm khuy?n mãi
    INSERT INTO KHUYENMAI (MaKM, TenKM, MoTaKM, NgayBatDau, NgayKetThuc, PhanTramKM)
    VALUES (KhuyenMai_Seq.NEXTVAL, p_TenKM, p_MoTaKM, p_NgayBatDau, p_NgayKetThuc, p_PhanTramKM);

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20004, 'Có l?i x?y ra trong quá trình thêm khuy?n mãi.');
END Them_khuyenmai;
/


-- Procedure: Them_trangbi
CREATE OR REPLACE PROCEDURE Them_trangbi(
    p_TenTB NVARCHAR2,
    p_GiaTB NUMBER,
    p_SoLuong INT
)
IS
BEGIN
    -- Thêm trang b?
    INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong)
    VALUES (TrangBi_Seq.NEXTVAL, p_TenTB, p_GiaTB, p_SoLuong, 0);
  
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20002, 'Có l?i x?y ra trong quá trình thêm trang b?.');
END Them_trangbi;
/


-- Procedure: Capnhat_sltrangbihong
CREATE OR REPLACE PROCEDURE Capnhat_sltrangbihong(
    P_MaTB NUMBER,
    p_SLHong INT
)
IS
    v_CurrentHong INT;
    v_soluongtb INT;
BEGIN
    -- L?y s? lu?ng trang b? hi?n t?i c?a khách s?n
    SELECT SLHong INTO v_CurrentHong
    FROM TRANGBI
    WHERE MaTB = P_MaTB;

    SELECT soluong INTO v_soluongtb
    FROM TRANGBI
    WHERE MaTB = P_MaTB;

    -- Ki?m tra và c?p nh?t s? lu?ng trang b? h?ng
    IF v_CurrentHong + p_SLHong <= v_soluongtb THEN
        UPDATE TRANGBI
        SET SLHong = v_CurrentHong + p_SLHong
        WHERE MaTB = P_MaTB;
    ELSE
        RAISE_APPLICATION_ERROR(-20003, 'S? lu?ng trang b? h?ng không th? l?n hon s? lu?ng trang b?.');
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'Trang b? không t?n t?i.');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20002, 'Có l?i x?y ra trong quá trình c?p nh?t.');
END Capnhat_sltrangbihong;
/


-- Procedure: Xoa_nhanvien
CREATE OR REPLACE PROCEDURE Xoa_nhanvien(
    P_MaNV NUMBER
)
IS
BEGIN
    -- C?p nh?t tình tr?ng nhân viên
    UPDATE NHANVIEN
    SET TinhTrang = 'Ngh? làm'
    WHERE MaNV = P_MaNV;

    -- Ki?m tra xem có c?p nh?t thành công
    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Nhân viên không t?n t?i.');
    ELSE
        -- Xóa d? li?u ch?m công c?a nhân viên
        DELETE FROM CHAMCONG
        WHERE MaNV = P_MaNV;
    END IF;
END Xoa_nhanvien;
/


-- Procedure: Xoa_trangbi
CREATE OR REPLACE PROCEDURE Xoa_trangbi(
    P_MaTB NUMBER
)
IS
BEGIN
    -- Xóa d? li?u h?ng trang b? liên quan
    DELETE FROM HONGTRANGBI
    WHERE MaTB = P_MaTB;

    -- Xóa trang b?
    DELETE FROM TRANGBI
    WHERE MaTB = P_MaTB;

    -- Ki?m tra xóa thành công không
    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Xóa không thành công.');
    END IF;

    -- Xác nh?n thay d?i
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        -- ROLLBACK n?u có l?i x?y ra
        ROLLBACK;
        RAISE;
END Xoa_trangbi;
/


-- Procedure: Xoa_hopdong
CREATE OR REPLACE PROCEDURE Xoa_hopdong(
    p_MaHopDong NUMBER
)
IS
    v_MaKH NUMBER;
    v_countHopDong NUMBER;
BEGIN
    -- L?y mã khách hàng t? trong h?p d?ng
    SELECT MaKH INTO v_MaKH
    FROM HOPDONG
    WHERE MaHopDong = p_MaHopDong;

    -- Xóa chi ti?t d?t phòng liên quan
    DELETE FROM CHITIETDATPHONG
    WHERE MaHopDong = p_MaHopDong;

    -- Xóa h?p d?ng
    DELETE FROM HOPDONG
    WHERE MaHopDong = p_MaHopDong;

    -- Ki?m tra xóa thành công không
    v_countHopDong := SQL%ROWCOUNT;

    -- Ki?m tra n?u h?p d?ng dã xóa thành công
    IF v_countHopDong > 0 THEN
        UPDATE KHACHHANG
        SET SoHopDong = SoHopDong - 1
        WHERE MaKH = v_MaKH;

        -- Xóa khách hàng n?u không còn h?p d?ng
        DELETE FROM KHACHHANG
        WHERE MaKH = v_MaKH AND SoHopDong = 0;
    ELSE
        RAISE_APPLICATION_ERROR(-20002, 'Không th? xóa h?p d?ng.');
    END IF;

    -- Xác nh?n thay d?i
    COMMIT;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'H?p d?ng không t?n t?i');
    WHEN OTHERS THEN
        -- ROLLBACK n?u có l?i x?y ra
        ROLLBACK;
        RAISE;
END Xoa_hopdong;
/


-- Procedure: Capnhat_trangbi
CREATE OR REPLACE PROCEDURE Capnhat_trangbi(
    p_MaTB NUMBER,
    p_TenTB NVARCHAR2,
    p_GiaTB NUMBER,
    p_SoLuong INT,
    p_SoLuongHong INT
)
IS
BEGIN
    -- C?p nh?t thông tin trang b?
    UPDATE TRANGBI
    SET TenTB = p_TenTB,
        GiaTB = p_GiaTB,
        SoLuong = p_SoLuong,
        SLHong = p_SoLuongHong
    WHERE MaTB = p_MaTB;

    -- Ki?m tra c?p nh?t thành công không
    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Ðã có l?i x?y ra trong quá trình c?p nh?t trang b?.');
    END IF;

    -- Xác nh?n thay d?i
    COMMIT;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'Trang b? không t?n t?i.');
    WHEN OTHERS THEN
        -- ROLLBACK n?u có l?i x?y ra
        ROLLBACK;
        RAISE;
END Capnhat_trangbi;
/


-- Procedure: Kiemtra_cccd
CREATE OR REPLACE PROCEDURE Kiemtra_cccd(
    P_cccd CHAR,
    P_MaKH OUT NUMBER
)
IS
BEGIN
    -- Ki?m tra CCCD và l?y mã khách hàng
    SELECT MaKH INTO P_MaKH
    FROM KHACHHANG
    WHERE CCCD = P_cccd;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        P_MaKH := NULL;
END Kiemtra_cccd;
/


-- Procedure: Themhopdong
CREATE OR REPLACE PROCEDURE Themhopdong(
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
    -- Thêm m?i h?p d?ng
    INSERT INTO HOPDONG (MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, SoNguoiLon, SoTreEm, TinhTrangHD, HinhThucThue, TriGiaHD)
    VALUES (P_MaKH, SYSDATE, P_TGNhanPhong, P_TGTraPhong, P_SoNguoiLon, P_SoTreEm, P_TinhTrangHD, P_HinhThucThue, P_TriGiaHD)
    RETURNING MaHopDong INTO v_MaHopDong;

    -- Xác nh?n thay d?i
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        -- Hoàn tác các thay d?i n?u có l?i
        ROLLBACK;
        RAISE;
END Themhopdong;
/


-- Procedure: Them_chitietdatphong
CREATE OR REPLACE PROCEDURE Them_chitietdatphong(
    P_MaHopDong NUMBER,
    P_MaPhong NUMBER
)
IS
BEGIN
    -- Thêm m?i chi ti?t d?t phòng
    INSERT INTO CHITIETDATPHONG (MaHopDong, MaPhong)
    VALUES (P_MaHopDong, P_MaPhong);

    -- Xác nh?n các thay d?i
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        -- Hoàn tác các thay d?i n?u có l?i
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20001, 'L?i khi thêm chi ti?t d?t phòng: ' || SQLERRM);
END Them_chitietdatphong;
/


-- Procedure: Capnhat_tthopdong
CREATE OR REPLACE PROCEDURE Capnhat_tthopdong (
    P_MaHopDong NUMBER,
    P_TinhTrang NVARCHAR2
)
AS
BEGIN
    -- C?p nh?t tình tr?ng h?p d?ng
    UPDATE HopDong
    SET TinhTrangHD = P_TinhTrang
    WHERE MaHopDong = P_MaHopDong;
    
    -- Xác nh?n thay d?i
    COMMIT;
END Capnhat_tthopdong;
/

-- Procedure: Them_hongtrangbi
CREATE OR REPLACE PROCEDURE Them_hongtrangbi (
    P_MaTB NUMBER,
    P_MaHD NUMBER
)
IS
BEGIN
    -- Thêm d? li?u vào b?ng HONGTRANGBI
    INSERT INTO HONGTRANGBI (MaTB, MaHD)
    VALUES (P_MaTB, P_MaHD);

    -- Xác nh?n thay d?i
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        -- X? lý l?i khi không th? thêm vào b?ng
        RAISE_APPLICATION_ERROR(-20001, 'L?i khi thêm vào b?ng HONGTRANGBI: ' || SQLERRM);
END Them_hongtrangbi;
/

-- Truy xu?t d?ng th?i
