ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;
CREATE USER database_doan_NPNT IDENTIFIED BY password;
GRANT CONNECT, RESOURCE, DBA TO database_doan_NPNT;

ALTER SESSION SET CURRENT_SCHEMA = database_doan_NPNT;

-- Xóa các bảng nếu đã tồn tại
DROP TABLE THAMSO CASCADE CONSTRAINTS;
DROP TABLE THONGKE CASCADE CONSTRAINTS;
DROP TABLE HONGTRANGBI CASCADE CONSTRAINTS;
DROP TABLE TAOHOADON CASCADE CONSTRAINTS;
DROP TABLE CHAMCONG CASCADE CONSTRAINTS;
DROP TABLE HOADON CASCADE CONSTRAINTS;
DROP TABLE KHUYENMAI CASCADE CONSTRAINTS;
DROP TABLE TRANGBI CASCADE CONSTRAINTS;
DROP TABLE CHITIETDATPHONG CASCADE CONSTRAINTS;
DROP TABLE PHONG CASCADE CONSTRAINTS;
DROP TABLE LOAIPHONG CASCADE CONSTRAINTS;
DROP TABLE HOPDONG CASCADE CONSTRAINTS;
DROP TABLE NHANVIEN CASCADE CONSTRAINTS;
DROP TABLE KHACHHANG CASCADE CONSTRAINTS;

-- Tạo bảng
-- Tạo bảng khách hàng
CREATE TABLE KHACHHANG (
  MaKH NUMBER PRIMARY KEY,
  TenKH NVARCHAR2(50),
  CCCD CHAR(12),
  NgaySinh DATE,
  GioiTinh NVARCHAR2(3),  -- 'Nam', 'Nữ'
  DiaChi NVARCHAR2(100),
  SDT CHAR(10),
  SoHopDong INT
);

-- Tạo bảng nhân viên
CREATE TABLE NHANVIEN (
  MaNV NUMBER PRIMARY KEY,
  TenNV NVARCHAR2(50),
  CCCD CHAR(12),
  NgaySinh DATE,
  GioiTinh NVARCHAR2(3),  -- 'Nam', 'Nữ'
  DiaChi NVARCHAR2(100),
  SDT CHAR(10),
  LoaiNV NVARCHAR2(20),    -- 'Tạp Vụ', 'Lễ Tân', 'Quản lý'
  TaiKhoan VARCHAR2(20),
  MatKhau VARCHAR2(20),
  LuongCB NUMBER,
  TinhTrang NVARCHAR2(20)  -- 'Đang làm', 'Nghỉ làm'
);

-- Tạo bảng hợp đồng đặt phòng tạm
CREATE TABLE HOPDONG(
  MaHopDong NUMBER PRIMARY KEY,
  MaKH NUMBER,
  NgayLapHopDong TIMESTAMP,
  TGNhanPhong TIMESTAMP,
  TGTraPhong TIMESTAMP,
  TinhTrangHD NVARCHAR2(20),  -- 'Đã xác nhận', 'Chưa xác nhận', 'Đã nhận phòng', 'Đã thanh toán'
  LoaiKH NVARCHAR2(20), -- "Nội địa", "Nước ngoài"
  TriGiaHD NUMBER,  -- Tiền cọc của hợp đồng
  HinhThucThue NVARCHAR2(10) -- có 2 hình thức thuê: 'Ngày', 'Giờ'
);

-- Tạo bảng loại phòng
CREATE TABLE LOAIPHONG (
  MaLoai NUMBER PRIMARY KEY,
  TenLoai VARCHAR(10), -- 'A', 'B', 'C'
  Gia NUMBER -- '150000', '170000', '200000'
);

-- Tạo bảng phòng
CREATE TABLE PHONG  (
  MaPhong NUMBER PRIMARY KEY,
  MaLoai NUMBER,
  TinhTrang NVARCHAR2(10) --  'Bình thường', 'Bảo trì',
);

-- Tạo bảng chi tiết đặt phòng
CREATE TABLE CHITIETDATPHONG (
  MaHopDong NUMBER NOT NULL,
  MaPhong NUMBER NOT NULL,
  SoKhach NUMBER,   -- Tối đa 3 khách
  CONSTRAINT PK_CTDP PRIMARY KEY (MaHopDong, MaPhong)
);

-- Tạo bảng trang bị
CREATE TABLE TRANGBI (
  MaTB NUMBER PRIMARY KEY,
  TenTB NVARCHAR2(50),
  GiaTB NUMBER,
  SoLuong INT,
  SLHong INT
);

-- Tạo bảng khuyến mãi
CREATE TABLE KHUYENMAI (
  MaKM NUMBER PRIMARY KEY,
  TenKM NVARCHAR2(50),
  MoTaKM NVARCHAR2(200),
  NgayBatDau DATE,
  NgayKetThuc DATE,
  PhanTramKM NUMERIC(3,2)
);


-- Tạo bảng hóa đơn
CREATE TABLE HOADON (
  MaHD NUMBER PRIMARY KEY,
  MaKM NUMBER,   -- với mỗi hóa đơn chỉ áp dụng được 1 loại khuyến mãi
  MaHopDong NUMBER,
  NgayLapHD DATE,
  TongTien NUMBER,
  TienHongTB NUMBER,
  SoNgayThue NUMBER
);

-- Tạo bảng hỏng trang bị
CREATE TABLE HONGTRANGBI (
  MaTB NUMBER,
  MaHD NUMBER,
  CONSTRAINT PK_HONGTRANGBI PRIMARY KEY (MaTB, MaHD)
);

-- Tạo bảng tạo hóa đơn
CREATE TABLE TAOHOADON (
  MaHD NUMBER,
  MaNV NUMBER,
  CONSTRAINT PK_TAOHOADON PRIMARY KEY(MaHD, MaNV)
);

-- Tạo bảng chấm công
CREATE TABLE CHAMCONG (
  MaCC NUMBER PRIMARY KEY,
  MaNV NUMBER,
  SoGioLamThem INT,
  SoNgayDiLam INT
);

-- Tạo bảng Thống kê doanh thu
CREATE TABLE THONGKE (
  MaTK NUMBER PRIMARY KEY ,
  Thang NUMBER,
  Nam NUMBER,
  MaLoai NUMBER,
  DoanhThu NUMBER
);

-- Tạo bảng tham số (để lưu những giá trị qui định trong khách sạn)
CREATE TABLE THAMSO (
    TenThamSo NVARCHAR2(50) PRIMARY KEY ,
    GiaTri FLOAT
);

-- Tạo UINIQUE cho các thuộc tính là duy nhất trong oracle
-- Bảng Khách hàng
ALTER TABLE KHACHHANG
ADD CONSTRAINT KHACHHANG_UNIQUE_CCCD UNIQUE(CCCD);

ALTER TABLE KHACHHANG
ADD CONSTRAINT KHACHHANG_UNIQUE_SDT UNIQUE(SDT);

-- Bảng Nhân viên
ALTER TABLE NHANVIEN
ADD CONSTRAINT NHANVIEN_UNIQUE_CCCD UNIQUE(CCCD);

ALTER TABLE NHANVIEN
ADD CONSTRAINT NHANVIEN_UNIQUE_SDT UNIQUE(SDT);


-- Xóa sequence
DROP SEQUENCE KhachHang_Seq;
DROP SEQUENCE NhanVien_Seq;
DROP SEQUENCE HopDong_Seq;
DROP SEQUENCE LoaiPhong_Seq;
DROP SEQUENCE Phong_Seq;
DROP SEQUENCE TrangBi_Seq;
DROP SEQUENCE KhuyenMai_Seq;
DROP SEQUENCE HoaDon_Seq;
DROP SEQUENCE ChamCong_Seq;
DROP SEQUENCE ThongKe_Seq;



-- Tạo sequence cho database
CREATE SEQUENCE KhachHang_Seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE NhanVien_Seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE HopDong_Seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE LoaiPhong_Seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE Phong_Seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE TrangBi_Seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE KhuyenMai_Seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE HoaDon_Seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE ChamCong_Seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE ThongKe_Seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;


-- Tạo khóa ngoại
ALTER TABLE HOPDONG
ADD CONSTRAINT FK_HOPDONG_KHACHHANG FOREIGN KEY (MaKH) REFERENCES KHACHHANG(MaKH);

ALTER TABLE CHITIETDATPHONG
ADD CONSTRAINT FK_CTHDong_HOPDONG FOREIGN KEY (MaHopDong) REFERENCES HOPDONG(MaHopDong);

ALTER TABLE CHITIETDATPHONG
ADD CONSTRAINT FK_CTHDong_PHONG FOREIGN KEY (MaPhong) REFERENCES PHONG(MaPhong);

ALTER TABLE PHONG
ADD CONSTRAINT FK_PHONG_LOAIPHONG FOREIGN KEY (MaLoai) REFERENCES LOAIPHONG(MaLoai);

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

ALTER TABLE THONGKE
ADD CONSTRAINT FK_THONGKE_LOAPHONG FOREIGN KEY (MaLoai) REFERENCES LOAIPHONG(MaLoai);


-- INSERT DỮ LIỆU
-- insert dữ liệu khách hàng
INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong) VALUES
(KhachHang_Seq.NEXTVAL, 'Nguyễn Văn An', '012345678901', TO_DATE('01-01-1999', 'DD-MM-YYYY'), 'Nam', '123 Lê Lợi, Hà Nội', '0912345678', 2);

INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong) VALUES
(KhachHang_Seq.NEXTVAL, 'Trần Thị Bình', '012345678902', TO_DATE('15-02-1995', 'DD-MM-YYYY'), 'Nữ', '456 Trần Hưng Đạo, Đà Nẵng', '0912345679', 1);

INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong) VALUES
(KhachHang_Seq.NEXTVAL, 'Lê Mạnh Cường', '012345678903', TO_DATE('20-03-1998', 'DD-MM-YYYY'), 'Nam', '789 Nguyễn Trãi, Hồ Chí Minh', '0912345680', 1);

INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong) VALUES
(KhachHang_Seq.NEXTVAL, 'Phạm Thị Diệu', '012345678904', TO_DATE('25-04-1995', 'DD-MM-YYYY'), 'Nữ', '321 Phan Chu Trinh, Cần Thơ', '0912345681', 1);

INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong) VALUES
(KhachHang_Seq.NEXTVAL, 'Hoàng Văn Huy', '012345678905', TO_DATE('30-05-2000', 'DD-MM-YYYY'), 'Nam', '654 Hai Bà Trưng, Huế', '0912345682', 2);

INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong) VALUES
(KhachHang_Seq.NEXTVAL, 'Vũ Thị Linh', '012345678906', TO_DATE('10-06-1997', 'DD-MM-YYYY'), 'Nữ', '987 Lê Thánh Tông, Hạ Long', '0912345683', 1);

INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong) VALUES
(KhachHang_Seq.NEXTVAL, 'Đặng Văn Lâm', '012345678907', TO_DATE('15-07-1993', 'DD-MM-YYYY'), 'Nam', '123 Võ Nguyên Giáp, Nha Trang', '0912345684', 1);

INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong) VALUES
(KhachHang_Seq.NEXTVAL, 'Bùi Thị Hiền', '012345678908', TO_DATE('20-08-1991', 'DD-MM-YYYY'), 'Nữ', '456 Lê Hồng Phong, Đà Lạt', '0912345685', 1);

INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong) VALUES
(KhachHang_Seq.NEXTVAL, 'Ngô Trần Văn Trung', '012345678909', TO_DATE('25-09-2002', 'DD-MM-YYYY'), 'Nam', '789 Trần Phú, Hải Phòng', '0912345686', 3);

INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong) VALUES
(KhachHang_Seq.NEXTVAL, 'Đỗ Nguyễn Ngọc Nữ', '012345678910', TO_DATE('30-10-1989', 'DD-MM-YYYY'), 'Nữ', '321 Nguyễn Huệ, Vũng Tàu', '0912345687', 2);

INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong) VALUES
(KhachHang_Seq.NEXTVAL, 'Nguyễn Thị Thu', '012345675411', TO_DATE('15-05-1995', 'DD-MM-YYYY'), 'Nữ', '123 Nguyễn Văn Linh, Đà Nẵng', '0912345688', 1);

INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong) VALUES
(KhachHang_Seq.NEXTVAL, 'Trần Thị Hương', '012345118912', TO_DATE('20-06-1990', 'DD-MM-YYYY'), 'Nữ', '456 Lý Thường Kiệt, Hà Nội', '0912345689', 1);

INSERT INTO KHACHHANG (MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong) VALUES
(KhachHang_Seq.NEXTVAL, 'Phạm Thị Tâm', '012345028913', TO_DATE('10-07-1987', 'DD-MM-YYYY'), 'Nữ', '789 Nguyễn Thị Minh Khai, TP.HCM', '0912345690', 1);


--insert dữ liệu nhân viên
INSERT INTO NHANVIEN (MANV, TENNV, CCCD, GIOITINH, NGAYSINH, DIACHI, LOAINV, TAIKHOAN, MATKHAU, SDT, LUONGCB, TINHTRANG)
	VALUES (NhanVien_Seq.NEXTVAL, 'Nguyễn Minh Thái', '098765432101', 'Nam', TO_DATE('1-1-1985', 'DD-MM-YYYY'), '123 Đường ABC, Hà Nội', 'Quản lý', 'quanly1', 'qlpass', '0901256567', 1200000, 'Đang làm');

INSERT INTO DATABASE_DOAN_NPNT.NHANVIEN (MANV, TENNV, CCCD, GIOITINH, NGAYSINH, DIACHI, LOAINV, TAIKHOAN, MATKHAU, SDT, LUONGCB, TINHTRANG)
	VALUES (NhanVien_Seq.NEXTVAL, 'Hoàng Hào Quang', '456789012345', 'Nam', TO_DATE('10-11-1992', 'DD-MM-YYYY'), '202 Đường JKL, Cần Thơ', 'Lễ tân', 'letan2', 'ltpass', '0943378901', 700000, 'Đang làm');

INSERT INTO DATABASE_DOAN_NPNT.NHANVIEN (MANV, TENNV, CCCD, GIOITINH, NGAYSINH, DIACHI, LOAINV, TAIKHOAN, MATKHAU, SDT, LUONGCB, TINHTRANG)
	VALUES (NhanVien_Seq.NEXTVAL, 'Ngô Nguyễn Tố Như', '567890123456', 'Nữ', TO_DATE('5-2-2000', 'DD-MM-YYYY'), '303 Đường MNO, Huế', 'Lễ tân', 'letan3', 'ltpass', '0956788112', 650000, 'Đang làm');

INSERT INTO DATABASE_DOAN_NPNT.NHANVIEN (MANV, TENNV, CCCD, GIOITINH, NGAYSINH, DIACHI, LOAINV, TAIKHOAN, MATKHAU, SDT, LUONGCB, TINHTRANG)
	VALUES (NhanVien_Seq.NEXTVAL, 'Vũ Thị Xuân', '098765432102', 'Nữ', TO_DATE('15-5-1990', 'DD-MM-YYYY'), '456 Đường XYZ, TP. Hồ Chí Minh', 'Tạp Vụ', NULL, NULL, '0912444568', 300000, 'Đang làm');

INSERT INTO DATABASE_DOAN_NPNT.NHANVIEN (MANV, TENNV, CCCD, GIOITINH, NGAYSINH, DIACHI, LOAINV, TAIKHOAN, MATKHAU, SDT, LUONGCB, TINHTRANG)
	VALUES (NhanVien_Seq.NEXTVAL, 'Lê Văn Hùng', '234567890123', 'Nam', TO_DATE('22-3-1988', 'DD-MM-YYYY'), '789 Đường DEF, Đà Nẵng', 'Tạp Vụ', NULL, NULL, '0923456711', 350000, 'Đang làm');

INSERT INTO DATABASE_DOAN_NPNT.NHANVIEN (MANV, TENNV, CCCD, GIOITINH, NGAYSINH, DIACHI, LOAINV, TAIKHOAN, MATKHAU, SDT, LUONGCB, TINHTRANG)
	VALUES (NhanVien_Seq.NEXTVAL, 'Phạm Ngọc Nhung', '345678901234', 'Nữ', TO_DATE('30-7-1999', 'DD-MM-YYYY'), '101 Đường GHI, Hải Phòng', 'Lễ tân', 'letan6', 'ltpass', '0930967890', 500000, 'Đang làm');

INSERT INTO NHANVIEN (MANV, TENNV, CCCD, GIOITINH, NGAYSINH, DIACHI, LOAINV, TAIKHOAN, MATKHAU, SDT, LUONGCB, TINHTRANG)
VALUES (NhanVien_Seq.NEXTVAL, 'Lê Minh Khang', '123456789012', 'Nam', TO_DATE('15-6-1988', 'DD-MM-YYYY'), '789 Đường PQR, Đà Nẵng', 'Quản lý', 'quanly3', 'qlpass', '0901122334', 1250000, 'Đang làm');

INSERT INTO NHANVIEN (MANV, TENNV, CCCD, GIOITINH, NGAYSINH, DIACHI, LOAINV, TAIKHOAN, MATKHAU, SDT, LUONGCB, TINHTRANG)
VALUES (NhanVien_Seq.NEXTVAL, 'Trần Thị Ngọc', '234567490123', 'Nữ', TO_DATE('22-3-1995', 'DD-MM-YYYY'), '987 Đường STU, Nha Trang', 'Lễ tân', 'letan4', 'ltpass', '0933456789', 720000, 'Đang làm');

INSERT INTO NHANVIEN (MANV, TENNV, CCCD, GIOITINH, NGAYSINH, DIACHI, LOAINV, TAIKHOAN, MATKHAU, SDT, LUONGCB, TINHTRANG)
VALUES (NhanVien_Seq.NEXTVAL, 'Phạm Văn Dũng', '345478901234', 'Nam', TO_DATE('8-8-1987', 'DD-MM-YYYY'), '654 Đường VWX, Hải Phòng', 'Quản lý', 'quanly4', 'qlpass', '0987654321', 1300000, 'Đang làm');

INSERT INTO NHANVIEN (MANV, TENNV, CCCD, GIOITINH, NGAYSINH, DIACHI, LOAINV, TAIKHOAN, MATKHAU, SDT, LUONGCB, TINHTRANG)
VALUES (NhanVien_Seq.NEXTVAL, 'Nguyễn Văn Anh', '456489012345', 'Nam', TO_DATE('30-10-1991', 'DD-MM-YYYY'), '321 Đường YZA, Vũng Tàu', 'Tạp Vụ', NULL, NULL, '0911223344', 350000, 'Đang làm');

INSERT INTO NHANVIEN (MANV, TENNV, CCCD, GIOITINH, NGAYSINH, DIACHI, LOAINV, TAIKHOAN, MATKHAU, SDT, LUONGCB, TINHTRANG)
VALUES (NhanVien_Seq.NEXTVAL, 'Bùi Thị Lan', '567840123456', 'Nữ', TO_DATE('12-12-1992', 'DD-MM-YYYY'), '111 Đường BCD, Hà Nội', 'Lễ tân', 'letan5', 'ltpass', '0967890123', 690000, 'Đang làm');



--Insert dữ liệu hợp đồng thuê phòng
-- Hợp đồng cho Nguyễn Văn An (2 hợp đồng)
INSERT INTO HOPDONG VALUES
(HopDong_Seq.NEXTVAL, 1,
TO_TIMESTAMP('10-01-2023 09:15:23','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('12-01-2023 14:00:00','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('15-01-2023 12:00:00','DD-MM-YYYY HH24:MI:SS'),
'Đã thanh toán', 'Nội địa', 1000000, 'Ngày');

INSERT INTO HOPDONG VALUES
(HopDong_Seq.NEXTVAL, 1,
TO_TIMESTAMP('15-03-2023 14:23:45','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('17-03-2023 14:00:00','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('20-03-2023 12:00:00','DD-MM-YYYY HH24:MI:SS'),
'Đã thanh toán', 'Nội địa', 2000000, 'Ngày');


-- Hợp đồng cho Trần Thị Bình (1 hợp đồng)
INSERT INTO HOPDONG
(MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong,
 TinhTrangHD, LoaiKH, TriGiaHD, HinhThucThue)
VALUES
(HopDong_Seq.NEXTVAL, 2,
TO_TIMESTAMP('01-02-2023 08:45:12','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('03-02-2023 14:00:00','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('06-02-2023 12:00:00','DD-MM-YYYY HH24:MI:SS'),
'Đã thanh toán', 'Nội địa', 1500000, 'Ngày');

-- Hợp đồng cho Lê Mạnh Cường (1 hợp đồng)
INSERT INTO HOPDONG VALUES
(HopDong_Seq.NEXTVAL, 3,
TO_TIMESTAMP('05-04-2023 10:32:55','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('07-04-2023 14:00:00','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('10-04-2023 12:00:00','DD-MM-YYYY HH24:MI:SS'),
'Đã thanh toán', 'Nội địa', 1800000, 'Ngày');

-- Hợp đồng cho Phạm Thị Diệu (1 hợp đồng)
INSERT INTO HOPDONG VALUES
(HopDong_Seq.NEXTVAL, 4,
TO_TIMESTAMP('15-05-2023 13:05:44','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('17-05-2023 14:00:00','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('20-05-2023 12:00:00','DD-MM-YYYY HH24:MI:SS'),
'Đã thanh toán', 'Nội địa', 1600000, 'Ngày');

-- Hợp đồng cho Hoàng Văn Huy (2 hợp đồng)
INSERT INTO HOPDONG VALUES
(HopDong_Seq.NEXTVAL, 5,
TO_TIMESTAMP('01-06-2023 11:25:30','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('03-06-2023 14:00:00','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('06-06-2023 12:00:00','DD-MM-YYYY HH24:MI:SS'),
'Đã thanh toán', 'Nội địa', 2200000, 'Ngày');

INSERT INTO HOPDONG VALUES
(HopDong_Seq.NEXTVAL, 5,
TO_TIMESTAMP('10-07-2023 09:45:50','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('12-07-2023 14:00:00','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('15-07-2023 12:00:00','DD-MM-YYYY HH24:MI:SS'),
'Đã thanh toán', 'Nội địa', 2300000, 'Ngày');

-- Hợp đồng cho Vũ Thị Linh (1 hợp đồng)
INSERT INTO HOPDONG VALUES
(HopDong_Seq.NEXTVAL, 6,
TO_TIMESTAMP('20-08-2023 14:55:22','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('22-08-2023 14:00:00','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('25-08-2023 12:00:00','DD-MM-YYYY HH24:MI:SS'),
'Đã thanh toán', 'Nội địa', 1300000, 'Ngày');

-- Hợp đồng cho Đặng Văn Lâm (1 hợp đồng)
INSERT INTO HOPDONG VALUES
(HopDong_Seq.NEXTVAL, 7,
TO_TIMESTAMP('05-09-2023 08:40:10','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('07-09-2023 14:00:00','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('10-09-2023 12:00:00','DD-MM-YYYY HH24:MI:SS'),
'Đã thanh toán', 'Nội địa', 2500000, 'Ngày');

-- Hợp đồng cho Bùi Thị Hiền (1 hợp đồng)
INSERT INTO HOPDONG VALUES
(HopDong_Seq.NEXTVAL, 8,
TO_TIMESTAMP('12-10-2023 12:22:33','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('14-10-2023 14:00:00','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('17-10-2023 12:00:00','DD-MM-YYYY HH24:MI:SS'),
'Đã thanh toán', 'Nội địa', 2400000, 'Ngày');

-- Hợp đồng cho Ngô Trần Văn Trung (3 hợp đồng)
INSERT INTO HOPDONG VALUES
(HopDong_Seq.NEXTVAL, 9,
TO_TIMESTAMP('01-11-2023 09:12:01','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('03-11-2023 14:00:00','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('06-11-2023 12:00:00','DD-MM-YYYY HH24:MI:SS'),
'Đã thanh toán', 'Nội địa', 1700000, 'Ngày');

INSERT INTO HOPDONG VALUES
(HopDong_Seq.NEXTVAL, 9,
TO_TIMESTAMP('10-12-2023 13:35:12','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('12-12-2023 14:00:00','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('15-12-2023 12:00:00','DD-MM-YYYY HH24:MI:SS'),
'Đã thanh toán', 'Nội địa', 1600000, 'Ngày');

INSERT INTO HOPDONG VALUES
(HopDong_Seq.NEXTVAL, 9,
TO_TIMESTAMP('20-12-2023 10:50:30','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('22-12-2023 14:00:00','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('25-12-2023 12:00:00','DD-MM-YYYY HH24:MI:SS'),
'Đã thanh toán', 'Nội địa', 3000000, 'Ngày');

-- Hợp đồng cho Đỗ Nguyễn Ngọc Nữ (2 hợp đồng)
INSERT INTO HOPDONG VALUES
(HopDong_Seq.NEXTVAL, 10,
TO_TIMESTAMP('12-01-2024 12:22:33','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('14-01-2024 14:00:00','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('17-01-2024 12:00:00','DD-MM-YYYY HH24:MI:SS'),
'Đã thanh toán', 'Nội địa', 1900000, 'Ngày');

INSERT INTO HOPDONG VALUES
(HopDong_Seq.NEXTVAL, 10,
TO_TIMESTAMP('12-03-2024 19:05:54','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('16-03-2024 14:00:00','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('20-03-2024 12:00:00','DD-MM-YYYY HH24:MI:SS'),
'Đã thanh toán', 'Nội địa', 2900000, 'Ngày');

-- Hợp đồng cho Nguyễn Thị Thu (1 hợp đồng)
INSERT INTO HOPDONG VALUES
(HopDong_Seq.NEXTVAL, 11,
TO_TIMESTAMP('28-01-2024 12:22:33','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('04-02-2024 14:00:00','DD-MM-YYYY HH24:MI:SS'),
TO_TIMESTAMP('07-02-2024 12:00:00','DD-MM-YYYY HH24:MI:SS'),
'Đã thanh toán', 'Nội địa', 1900000, 'Ngày');


-- insert dữ liệu loại phòng
INSERT INTO LOAIPHONG VALUES (1, 'A', 150000);
INSERT INTO LOAIPHONG VALUES (2, 'B', 170000);
INSERT INTO LOAIPHONG VALUES (3, 'C', 200000);

-- insert dữ liệu phòng
-- Tầng 1
INSERT INTO PHONG VALUES (101, 1, 'Bình thường');
INSERT INTO PHONG VALUES (102, 1, 'Bình thường');
INSERT INTO PHONG VALUES (103, 1, 'Bình thường');

INSERT INTO PHONG VALUES (104, 2, 'Bình thường');
INSERT INTO PHONG VALUES (105, 2, 'Bình thường');
INSERT INTO PHONG VALUES (106, 2, 'Bình thường');

INSERT INTO PHONG VALUES (107, 3, 'Bình thường');
INSERT INTO PHONG VALUES (108, 3, 'Bình thường');

INSERT INTO PHONG VALUES (109, 1, 'Bình thường');
INSERT INTO PHONG VALUES (110, 1, 'Bình thường');
INSERT INTO PHONG VALUES (111, 1, 'Bình thường');

INSERT INTO PHONG VALUES (112, 2, 'Bình thường');
INSERT INTO PHONG VALUES (113, 2, 'Bình thường');

INSERT INTO PHONG VALUES (114, 3, 'Bình thường');
INSERT INTO PHONG VALUES (115, 3, 'Bình thường');

-- Tầng 2
INSERT INTO PHONG VALUES (201, 1, 'Bình thường');
INSERT INTO PHONG VALUES (202, 1, 'Bình thường');
INSERT INTO PHONG VALUES (203, 1, 'Bình thường');

INSERT INTO PHONG VALUES (204, 2, 'Bình thường');
INSERT INTO PHONG VALUES (205, 2, 'Bình thường');
INSERT INTO PHONG VALUES (206, 2, 'Bình thường');

INSERT INTO PHONG VALUES (207, 3, 'Bình thường');
INSERT INTO PHONG VALUES (208, 3, 'Bình thường');

INSERT INTO PHONG VALUES (209, 1, 'Bình thường');
INSERT INTO PHONG VALUES (210, 1, 'Bình thường');
INSERT INTO PHONG VALUES (211, 1, 'Bình thường');

INSERT INTO PHONG VALUES (212, 2, 'Bình thường');
INSERT INTO PHONG VALUES (213, 2, 'Bình thường');

INSERT INTO PHONG VALUES (214, 3, 'Bình thường');
INSERT INTO PHONG VALUES (215, 3, 'Bình thường');

-- Tầng 3
INSERT INTO PHONG VALUES (301, 1, 'Bình thường');
INSERT INTO PHONG VALUES (302, 1, 'Bình thường');
INSERT INTO PHONG VALUES (303, 1, 'Bình thường');

INSERT INTO PHONG VALUES (304, 2, 'Bình thường');
INSERT INTO PHONG VALUES (305, 2, 'Bình thường');
INSERT INTO PHONG VALUES (306, 2, 'Bình thường');

INSERT INTO PHONG VALUES (307, 3, 'Bình thường');
INSERT INTO PHONG VALUES (308, 3, 'Bình thường');

INSERT INTO PHONG VALUES (309, 1, 'Bình thường');
INSERT INTO PHONG VALUES (310, 1, 'Bình thường');
INSERT INTO PHONG VALUES (311, 1, 'Bình thường');

INSERT INTO PHONG VALUES (312, 2, 'Bình thường');
INSERT INTO PHONG VALUES (313, 2, 'Bình thường');

INSERT INTO PHONG VALUES (314, 3, 'Bình thường');
INSERT INTO PHONG VALUES (315, 3, 'Bình thường');

-- Tầng 4
INSERT INTO PHONG VALUES (401, 1, 'Bình thường');
INSERT INTO PHONG VALUES (402, 1, 'Bình thường');
INSERT INTO PHONG VALUES (403, 1, 'Bình thường');

INSERT INTO PHONG VALUES (404, 2, 'Bình thường');
INSERT INTO PHONG VALUES (405, 2, 'Bình thường');
INSERT INTO PHONG VALUES (406, 2, 'Bình thường');

INSERT INTO PHONG VALUES (407, 3, 'Bình thường');
INSERT INTO PHONG VALUES (408, 3, 'Bình thường');

INSERT INTO PHONG VALUES (409, 1, 'Bình thường');
INSERT INTO PHONG VALUES (410, 1, 'Bình thường');
INSERT INTO PHONG VALUES (411, 1, 'Bình thường');

INSERT INTO PHONG VALUES (412, 2, 'Bình thường');
INSERT INTO PHONG VALUES (413, 2, 'Bình thường');

INSERT INTO PHONG VALUES (414, 3, 'Bình thường');
INSERT INTO PHONG VALUES (415, 3, 'Bình thường');



-- inset chi tiết đặt phòng
-- Hợp đồng cho Nguyễn Văn An
INSERT INTO CHITIETDATPHONG VALUES (1, 101, 2);
INSERT INTO CHITIETDATPHONG VALUES (1, 202, 1);

INSERT INTO CHITIETDATPHONG VALUES (2, 103, 2);
INSERT INTO CHITIETDATPHONG VALUES (2, 204, 2);

-- Hợp đồng cho Trần Thị Bình
INSERT INTO CHITIETDATPHONG VALUES (3, 305, 1);

-- Hợp đồng cho Lê Mạnh Cường
INSERT INTO CHITIETDATPHONG VALUES (4, 206, 3);

-- Hợp đồng cho Phạm Thị Diệu
INSERT INTO CHITIETDATPHONG VALUES (5, 207, 3);

-- Hợp đồng cho Hoàng Văn Huy
INSERT INTO CHITIETDATPHONG VALUES (6, 208, 2);
INSERT INTO CHITIETDATPHONG VALUES (6, 101, 1);

INSERT INTO CHITIETDATPHONG VALUES (7, 302, 2);
INSERT INTO CHITIETDATPHONG VALUES (7, 303, 2);

-- Hợp đồng cho Vũ Thị Linh
INSERT INTO CHITIETDATPHONG VALUES (8, 304, 1);

-- Hợp đồng cho Đặng Văn Lâm
INSERT INTO CHITIETDATPHONG VALUES (9, 305, 3);

-- Hợp đồng cho Bùi Thị Hiền
INSERT INTO CHITIETDATPHONG VALUES (10, 306, 2);

-- Hợp đồng cho Ngô Trần Văn Trung
INSERT INTO CHITIETDATPHONG VALUES (11, 307, 3);

INSERT INTO CHITIETDATPHONG VALUES (12, 308, 1);
INSERT INTO CHITIETDATPHONG VALUES (12, 401, 1);

INSERT INTO CHITIETDATPHONG VALUES (13, 402, 3);

-- Hợp đồng cho Đỗ Nguyễn Ngọc Nữ
INSERT INTO CHITIETDATPHONG VALUES (14, 103, 1);
INSERT INTO CHITIETDATPHONG VALUES (14, 204, 1);

INSERT INTO CHITIETDATPHONG VALUES (15, 305, 2);
INSERT INTO CHITIETDATPHONG VALUES (15, 206, 1);

-- Hợp đồng cho Nguyễn Thị Thu
INSERT INTO CHITIETDATPHONG VALUES (16, 105, 1);
INSERT INTO CHITIETDATPHONG VALUES (16, 303, 1);



-- Thêm dữ liệu vào bảng TRANGBI sử dụng sequence
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Tivi', 5000000, 31, 1);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Tủ lạnh', 7000000, 32, 0);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Điều hòa', 10000000, 30, 2);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Máy giặt', 6000000, 15, 1);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Lò vi sóng', 3000000, 15, 1);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Máy sấy tóc', 200000, 45, 3);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Bàn ủi', 400000, 39, 1);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Bình nước nóng', 2000000, 36, 0);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Máy pha cà phê', 1500000, 16, 0);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Quạt', 500000, 32, 2);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Khăn tắm', 60000, 59, 5);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Dép', 20000, 70, 2);
INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong) VALUES (TrangBi_Seq.NEXTVAL, 'Bình bông', 400000, 15, 2);


-- Thêm dữ liệu vào bảng KHUYENMAI sử dụng sequence
INSERT INTO KHUYENMAI (MaKM, TenKM, MoTaKM, NgayBatDau, NgayKetThuc, PhanTramKM) VALUES
(KhuyenMai_Seq.NEXTVAL, 'Khuyến mãi mùa hè', 'Giảm giá 10% cho tất cả các phòng trong tháng 6 và 7', TO_DATE('01-06-2024', 'DD-MM-YYYY'), TO_DATE('31-07-2024', 'DD-MM-YYYY'), 0.1);

INSERT INTO KHUYENMAI (MaKM, TenKM, MoTaKM, NgayBatDau, NgayKetThuc, PhanTramKM) VALUES
(KhuyenMai_Seq.NEXTVAL, 'Khuyến mãi cuối tuần', 'Giảm giá 5% cho các ngày cuối tuần từ thứ 6 đến chủ nhật', TO_DATE('01-01-2024', 'DD-MM-YYYY'), TO_DATE('31-12-2024', 'DD-MM-YYYY'), 0.05);

INSERT INTO KHUYENMAI (MaKM, TenKM, MoTaKM, NgayBatDau, NgayKetThuc, PhanTramKM) VALUES
(KhuyenMai_Seq.NEXTVAL, 'Khuyến mãi sinh nhật', 'Giảm giá 15% trong ngày sinh nhật của khách hàng', TO_DATE('01-01-2024', 'DD-MM-YYYY'), TO_DATE('31-12-2024', 'DD-MM-YYYY'), 0.15);

INSERT INTO KHUYENMAI (MaKM, TenKM, MoTaKM, NgayBatDau, NgayKetThuc, PhanTramKM) VALUES
(KhuyenMai_Seq.NEXTVAL, 'Khuyến mãi mùa đông', 'Giảm giá 10% cho tất cả các phòng trong tháng 12 và 1', TO_DATE('01-12-2024', 'DD-MM-YYYY'), TO_DATE('31-01-2025', 'DD-MM-YYYY'), 0.1);

-- Khuyến mãi cho Ngày Quốc tế Phụ nữ (8/3)
INSERT INTO KHUYENMAI (MaKM, TenKM, MoTaKM, NgayBatDau, NgayKetThuc, PhanTramKM) VALUES
(KhuyenMai_Seq.NEXTVAL, 'Khuyến mãi Ngày Phụ nữ 8/3', 'Chúc mừng Ngày Quốc tế Phụ nữ! Chương trình khuyến mãi dành riêng cho phái đẹp với ưu đãi giảm giá 20% cho tất cả các phụ nữ lưu trú trong ngày 8/3.',
TO_DATE('08-03-2024', 'DD-MM-YYYY'), TO_DATE('08-03-2024', 'DD-MM-YYYY'), 0.2);

-- Khuyến mãi cho Ngày Quốc tế thiếu nhi (1/6)
INSERT INTO KHUYENMAI (MaKM, TenKM, MoTaKM, NgayBatDau, NgayKetThuc, PhanTramKM) VALUES
(KhuyenMai_Seq.NEXTVAL, 'Khuyến mãi Ngày Lao động 1/6', 'Chúc mừng Ngày Quốc tế Thiếu nhi! Chương trình khuyến mãi đặc biệt dành cho tất cả các khách hàng với ưu đãi giảm giá 15% trong cả tuần từ 1/6 đến 7/6.',
TO_DATE('01-06-2024', 'DD-MM-YYYY'), TO_DATE('07-06-2024', 'DD-MM-YYYY'), 0.15);



-- Hóa đơn cho hợp đồng của Nguyễn Văn An (Hợp đồng số 1)
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES
(HoaDon_Seq.NEXTVAL, NULL, 1, TO_DATE('15-01-2023', 'DD-MM-YYYY'), 3000000, 0);

-- Hóa đơn cho hợp đồng của Nguyễn Văn An (Hợp đồng số 2)
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES
(HoaDon_Seq.NEXTVAL, NULL, 2, TO_DATE('20-03-2023', 'DD-MM-YYYY'), 6000000, 0);

-- Hóa đơn cho hợp đồng của Trần Thị Bình
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES
(HoaDon_Seq.NEXTVAL, NULL, 3, TO_DATE('06-02-2023', 'DD-MM-YYYY'), 4500000, 0);

-- Hóa đơn cho hợp đồng của Lê Mạnh Cường
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES
(HoaDon_Seq.NEXTVAL, NULL, 4, TO_DATE('10-04-2023', 'DD-MM-YYYY'), 5800000, 200000);

-- Hóa đơn cho hợp đồng của Phạm Thị Diệu
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES
(HoaDon_Seq.NEXTVAL, NULL, 5, TO_DATE('20-05-2023', 'DD-MM-YYYY'), 3600000, 0);

-- Hóa đơn cho hợp đồng của Hoàng Văn Huy (Hợp đồng số 1)
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES
(HoaDon_Seq.NEXTVAL, NULL, 6, TO_DATE('06-06-2023', 'DD-MM-YYYY'), 6200000, 0);

-- Hóa đơn cho hợp đồng của Hoàng Văn Huy (Hợp đồng số 2)
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES
(HoaDon_Seq.NEXTVAL, NULL, 7, TO_DATE('15-07-2023', 'DD-MM-YYYY'), 6300000, 50000);

-- Hóa đơn cho hợp đồng của Vũ Thị Linh
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES
(HoaDon_Seq.NEXTVAL, NULL, 8, TO_DATE('25-08-2023', 'DD-MM-YYYY'), 3300000, 0);

-- Hóa đơn cho hợp đồng của Đặng Văn Lâm
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES
(HoaDon_Seq.NEXTVAL, NULL, 9, TO_DATE('10-09-2023', 'DD-MM-YYYY'), 6500000, 0);

-- Hóa đơn cho hợp đồng của Bùi Thị Hiền
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES
(HoaDon_Seq.NEXTVAL, NULL, 10, TO_DATE('17-10-2023', 'DD-MM-YYYY'), 6400000, 0);

-- Hóa đơn cho hợp đồng của Ngô Trần Văn Trung (Hợp đồng số 1)
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES
(HoaDon_Seq.NEXTVAL, NULL, 11, TO_DATE('06-11-2023', 'DD-MM-YYYY'), 4700000, 0);

-- Hóa đơn cho hợp đồng của Ngô Trần Văn Trung (Hợp đồng số 2)
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES
(HoaDon_Seq.NEXTVAL, NULL, 12, TO_DATE('15-12-2023', 'DD-MM-YYYY'), 4600000, 0);

-- Hóa đơn cho hợp đồng của Ngô Trần Văn Trung (Hợp đồng số 3)
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES
(HoaDon_Seq.NEXTVAL, NULL, 13, TO_DATE('25-12-2023', 'DD-MM-YYYY'), 9000000, 0);

-- Hóa đơn cho hợp đồng của Đỗ Nguyễn Ngọc Nữ (Hợp đồng số 1)
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES
(HoaDon_Seq.NEXTVAL, NULL, 14, TO_DATE('17-01-2024', 'DD-MM-YYYY'), 5900000, 0);

-- Hóa đơn cho hợp đồng của Đỗ Nguyễn Ngọc Nữ (Hợp đồng số 2)
INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES
(HoaDon_Seq.NEXTVAL, NULL, 15, TO_DATE('20-03-2024', 'DD-MM-YYYY'), 4300000, 0);

INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES
(HoaDon_Seq.NEXTVAL, NULL, 16, TO_DATE('12-02-2024', 'DD-MM-YYYY'), 4300000, 0);


-- insert dữ liệu bảng hỏng trang bị
INSERT INTO HONGTRANGBI (MAHD, MATB) VALUES (4, 6);
INSERT INTO HONGTRANGBI (MAHD, MATB) VALUES (7, 10);


-- insert dữ liệu tạo hóa đơn
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (1, 2);
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (2, 3);
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (3, 2);
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (4, 3);
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (5, 3);
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (6, 2);
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (7, 2);
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (8, 2);
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (9, 2);
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (10, 3);
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (11, 2);
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (12, 2);
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (13, 2);
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (14, 2);
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (15, 2);
INSERT INTO TAOHOADON (MAHD, MANV) VALUES (16, 7);


-- Insert dữ liệu chấm công cho 6 nhân viên
INSERT INTO CHAMCONG (MaCC, MaNV, SoGioLamThem, SoNgayDiLam) VALUES (ChamCong_Seq.NEXTVAL, 1, 5, 18);
INSERT INTO CHAMCONG (MaCC, MaNV, SoGioLamThem, SoNgayDiLam) VALUES (ChamCong_Seq.NEXTVAL, 2, 2, 4);
INSERT INTO CHAMCONG (MaCC, MaNV, SoGioLamThem, SoNgayDiLam) VALUES (ChamCong_Seq.NEXTVAL, 3, 2, 3);
INSERT INTO CHAMCONG (MaCC, MaNV, SoGioLamThem, SoNgayDiLam) VALUES (ChamCong_Seq.NEXTVAL, 4, 3, 8);
INSERT INTO CHAMCONG (MaCC, MaNV, SoGioLamThem, SoNgayDiLam) VALUES (ChamCong_Seq.NEXTVAL, 5, 4, 10);
INSERT INTO CHAMCONG (MaCC, MaNV, SoGioLamThem, SoNgayDiLam) VALUES (ChamCong_Seq.NEXTVAL, 6, 10, 19);
INSERT INTO CHAMCONG (MaCC, MaNV, SoGioLamThem, SoNgayDiLam) VALUES (ChamCong_Seq.NEXTVAL, 7, 8, 2);
INSERT INTO CHAMCONG (MaCC, MaNV, SoGioLamThem, SoNgayDiLam) VALUES (ChamCong_Seq.NEXTVAL, 8, 18, 9);
INSERT INTO CHAMCONG (MaCC, MaNV, SoGioLamThem, SoNgayDiLam) VALUES (ChamCong_Seq.NEXTVAL, 9, 2, 11);
INSERT INTO CHAMCONG (MaCC, MaNV, SoGioLamThem, SoNgayDiLam) VALUES (ChamCong_Seq.NEXTVAL, 10, 0, 14);
INSERT INTO CHAMCONG (MaCC, MaNV, SoGioLamThem, SoNgayDiLam) VALUES (ChamCong_Seq.NEXTVAL, 11, 1, 2);

-- Insert bảng tham số
INSERT INTO THAMSO (TenThamSo, GiaTri) VALUES ('SoLuongKhachToiDa', 3);
INSERT INTO THAMSO (TenThamSo, GiaTri) VALUES ('HeSoKhachNuocNgoai', 1.5);
INSERT INTO THAMSO (TenThamSo, GiaTri) VALUES ('PhuThuKhachThu3', 0.25);
INSERT INTO THAMSO (TenThamSo, GiaTri) VALUES ('PhanTramCoc', 0.3);


COMMIT;
--======================================== TRIGGER ======================================
-- Chạy mã để tạo trigger
CREATE OR REPLACE TRIGGER trg_update_sohopdong
AFTER INSERT ON HOPDONG
FOR EACH ROW
BEGIN
  -- Cập nhật số hợp đồng của khách hàng tương ứng
  UPDATE KHACHHANG
  SET SoHopDong = SoHopDong + 1
  WHERE MaKH = :NEW.MaKH;
END;
/


-- Kiểm tra tuổi khách hàng phải trên 18 tuổi
CREATE OR REPLACE TRIGGER Check_TuoiKhachHang
BEFORE INSERT ON KHACHHANG
FOR EACH ROW
DECLARE
    customer_age NUMBER;
BEGIN
    -- Tính tuổi của khách hàng từ ngày sinh
    customer_age := TRUNC(MONTHS_BETWEEN(SYSDATE, :NEW.NgaySinh) / 12);

    -- Kiểm tra tuổi của khách hàng
    IF customer_age < 18 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Khách hàng phải đủ 18 tuổi trở lên.');
    END IF;
END;
/

-- Kiểm tra thời gian
CREATE OR REPLACE TRIGGER Check_ThoiGianLapHopDong
BEFORE INSERT OR UPDATE ON HOPDONG
FOR EACH ROW
BEGIN
  IF :NEW.TGTraPhong <= :NEW.TGNhanPhong THEN
    RAISE_APPLICATION_ERROR(-20001, 'Thời gian trả phòng phải sau thời gian nhận phòng.');
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
        RAISE_APPLICATION_ERROR(-20004, 'Có lỗi xảy ra trong quá trình thêm khách hàng.');
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
        RAISE_APPLICATION_ERROR(-20005, 'Có lỗi xảy ra trong quá trình thêm nhân viên.');
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
        RAISE_APPLICATION_ERROR(-20006, 'Có lỗi xảy ra trong quá trình thêm phòng.');
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
    -- Kiểm tra ngày bắt đầu và ngày kết thúc
    IF p_NgayKetThuc < p_NgayBatDau THEN
        RAISE_APPLICATION_ERROR(-20003, 'Ngày kết thúc không thể trước ngày bắt đầu');
    END IF;

    -- Thêm khuyến mãi
    INSERT INTO KHUYENMAI (MaKM, TenKM, MoTaKM, NgayBatDau, NgayKetThuc, PhanTramKM)
    VALUES (KhuyenMai_Seq.NEXTVAL, p_TenKM, p_MoTaKM, p_NgayBatDau, p_NgayKetThuc, p_PhanTramKM);

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20004, 'Có lỗi xảy ra trong quá trình thêm khuyến mãi.');
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
    -- Thêm trang bị
    INSERT INTO TRANGBI (MaTB, TenTB, GiaTB, SoLuong, SLHong)
    VALUES (TrangBi_Seq.NEXTVAL, p_TenTB, p_GiaTB, p_SoLuong, 0);

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20002, 'Có lỗi xảy ra trong quá trình thêm trang bị.');
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
    -- Lấy số lượng trang bị hiện tại của khách sạn
    SELECT SLHong INTO v_CurrentHong
    FROM TRANGBI
    WHERE MaTB = P_MaTB;

    SELECT soluong INTO v_soluongtb
    FROM TRANGBI
    WHERE MaTB = P_MaTB;

    -- Kiểm tra và cập nhật số lượng trang bị hỏng
    IF v_CurrentHong + p_SLHong <= v_soluongtb THEN
        UPDATE TRANGBI
        SET SLHong = v_CurrentHong + p_SLHong
        WHERE MaTB = P_MaTB;
    ELSE
        RAISE_APPLICATION_ERROR(-20003, 'Số lượng trang bị hỏng không thể lớn hơn số lượng trang bị.');
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'Trang bị không tồn tại.');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20002, 'Có lỗi xảy ra trong quá trình cập nhật.');
END Capnhat_sltrangbihong;
/


-- Procedure: Xoa_nhanvien
CREATE OR REPLACE PROCEDURE Xoa_nhanvien(
    P_MaNV NUMBER
)
IS
BEGIN
    -- Cập nhật tình trạng nhân viên
    UPDATE NHANVIEN
    SET TinhTrang = 'Nghỉ làm'
    WHERE MaNV = P_MaNV;

    -- Kiểm tra xem có cập nhật thành công
    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Nhân viên không tồn tại.');
    ELSE
        -- Xóa dữ liệu chấm công của nhân viên
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

    -- Xác nhận thay đổi
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        -- ROLLBACK nếu có lỗi xảy ra
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
    -- Lấy mã khách hàng từ trong hợp đồng
    SELECT MaKH INTO v_MaKH
    FROM HOPDONG
    WHERE MaHopDong = p_MaHopDong;

    -- Xóa chi tiết đặt phòng liên quan
    DELETE FROM CHITIETDATPHONG
    WHERE MaHopDong = p_MaHopDong;

    -- Xóa hợp đồng
    DELETE FROM HOPDONG
    WHERE MaHopDong = p_MaHopDong;

    -- Kiểm tra xóa thành công không
    v_countHopDong := SQL%ROWCOUNT;

    -- Kiểm tra nếu hợp đồng đã xóa thành công
    IF v_countHopDong > 0 THEN
        UPDATE KHACHHANG
        SET SoHopDong = SoHopDong - 1
        WHERE MaKH = v_MaKH;

        -- Xóa khách hàng nếu không còn hợp đồng
        DELETE FROM KHACHHANG
        WHERE MaKH = v_MaKH AND SoHopDong = 0;
    ELSE
        RAISE_APPLICATION_ERROR(-20002, 'Không thể xóa hợp đồng.');
    END IF;

    -- Xác nhận thay đổi
    COMMIT;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'Hợp đồng không tồn tại');
    WHEN OTHERS THEN
        -- ROLLBACK nếu có lỗi xảy ra
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
    -- Cập nhật thông tin trang bị
    UPDATE TRANGBI
    SET TenTB = p_TenTB,
        GiaTB = p_GiaTB,
        SoLuong = p_SoLuong,
        SLHong = p_SoLuongHong
    WHERE MaTB = p_MaTB;

    -- Kiểm tra cập nhật thành công không
    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Đã có lỗi xảy ra trong quá trình cập nhật trang bị.');
    END IF;

    -- Xác nhận thay đổi
    COMMIT;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'Trang bị không tồn tại.');
    WHEN OTHERS THEN
        -- ROLLBACK nếu có lỗi xảy ra
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
    -- Kiểm tra CCCD và lấy mã khách hàng
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
    -- Thêm mới hợp đồng
    INSERT INTO HOPDONG (MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, SoNguoiLon, SoTreEm, TinhTrangHD, HinhThucThue, TriGiaHD)
    VALUES (P_MaKH, SYSDATE, P_TGNhanPhong, P_TGTraPhong, P_SoNguoiLon, P_SoTreEm, P_TinhTrangHD, P_HinhThucThue, P_TriGiaHD)
    RETURNING MaHopDong INTO v_MaHopDong;

    -- Xác nhận thay đổi
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        -- Hoàn tác các thay đổi nếu có lỗi
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
    -- Thêm mới chi tiết đặt phòng
    INSERT INTO CHITIETDATPHONG (MaHopDong, MaPhong)
    VALUES (P_MaHopDong, P_MaPhong);

    -- Xác nhận các thay đổi
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        -- Hoàn tác các thay đổi nếu có lỗi
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20001, 'Lỗi khi thêm chi tiết đặt phòng: ' || SQLERRM);
END Them_chitietdatphong;
/


-- Procedure: Capnhat_tthopdong
CREATE OR REPLACE PROCEDURE Capnhat_tthopdong (
    P_MaHopDong NUMBER,
    P_TinhTrang NVARCHAR2
)
AS
BEGIN
    -- Cập nhật tình trạng hợp đồng
    UPDATE HopDong
    SET TinhTrangHD = P_TinhTrang
    WHERE MaHopDong = P_MaHopDong;

    -- Xác nhận thay đổi
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
    -- Thêm dữ liệu vào bảng HONGTRANGBI
    INSERT INTO HONGTRANGBI (MaTB, MaHD)
    VALUES (P_MaTB, P_MaHD);

    -- Xác nhận thay đổi
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        -- Xử lý lỗi khi không thể thêm vào bảng
        RAISE_APPLICATION_ERROR(-20001, 'Lỗi khi thêm vào bảng HONGTRANGBI: ' || SQLERRM);
END Them_hongtrangbi;
/


