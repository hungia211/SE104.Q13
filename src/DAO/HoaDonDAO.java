package DAO;

import Model.KhachHangModel;
import Connection.JDBCUtil;
import Model.HoaDonModel;
import Model.HopDongModel;
import java.util.ArrayList;
import java.util.Vector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class HoaDonDAO {

    public static String getTenNVByMaHD(int MaHD) {
        String tenNV = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT NHANVIEN.TenNV FROM HOADON "
                    + "INNER JOIN TAOHOADON ON HOADON.MaHD = TAOHOADON.MaHD "
                    + "INNER JOIN HOPDONG ON HOADON.MaHopDong = HOPDONG.MaHopDong "
                    + "INNER JOIN NHANVIEN ON TAOHOADON.MaNV = NHANVIEN.MaNV "
                    + "WHERE HOADON.MaHD = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, MaHD);
            rs = ps.executeQuery();
            if (rs.next()) {
                tenNV = rs.getString("TenNV");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenNV;
    }

    public static int getMaNVByMaHD(int MaHD) {
        int maNV = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT NHANVIEN.MaNV FROM HOADON "
                    + "INNER JOIN TAOHOADON ON HOADON.MaHD = TAOHOADON.MaHD "
                    + "INNER JOIN NHANVIEN ON TAOHOADON.MaNV = NHANVIEN.MaNV "
                    + "WHERE HOADON.MaHD = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, MaHD);
            rs = ps.executeQuery();
            if (rs.next()) {
                maNV = rs.getInt("maNV");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maNV;
    }

    public static HoaDonModel getHDByID(int MaHD) {
        HoaDonModel hoaDon = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM HOADON WHERE MaHD = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, MaHD);
            rs = ps.executeQuery();
            if (rs.next()) {
                int maHopDong = rs.getInt("MaHopDong");
                int maKM = rs.getInt("MaKM");
                LocalDate ngayLapHD = rs.getDate("NgayLapHD").toLocalDate();
                double tongTien = rs.getDouble("TongTien");
                double tienHongTB = rs.getDouble("TienHongTB");

                hoaDon = new HoaDonModel(MaHD, maHopDong, maKM, ngayLapHD, tongTien, tienHongTB);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDon;
    }

    public static ArrayList<Vector> getDataHD() {
        ArrayList<Vector> DSHD = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            String sql = "select HOPDONG.MaKH,HOADON.MaHD ,TAOHOADON.MaNV ,HOADON.TongTien,HOADON.NgayLapHD FROM HOADON INNER JOIN TAOHOADON ON HOADON.MaHD=TAOHOADON.MaHD INNER JOIN HOPDONG ON HOADON.MaHopDong=HOPDONG.MaHopDong ORDER BY MAHD DESC";
            Connection con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN")); // Định dạng tiền tệ cho Việt Nam
            while (rs.next()) {
                Vector row = new Vector();
                DSHD.add(row);
                row.add(rs.getInt("MaHD"));
                row.add(rs.getInt("MaKH"));
                row.add(rs.getInt("MaNV"));
                java.sql.Date ngayLapHD = rs.getDate("NgayLapHD");
                String formattedDate = (ngayLapHD != null) ? ngayLapHD.toLocalDate().format(dateFormatter) : null; // Định dạng ngày lập hóa đơn
                row.add(formattedDate);
                double tongTien = rs.getDouble("TongTien");
                String formattedTongTienThue = String.format("%,.0f VND", tongTien);
                row.add(formattedTongTienThue);
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return DSHD;
    }

    public static ArrayList<Vector> getDSHDTheoTenKhachHang(String tenKhachHang) {
        ArrayList<Vector> DSHD = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            // Câu truy vấn SQL kết nối các bảng và truy xuất thông tin dựa trên tên khách hàng
            String sql = "SELECT HOADON.MaHD, KHACHHANG.MaKH, KHACHHANG.TenKH, TAOHOADON.MaNV, HOADON.TongTien, HOADON.NgayLapHD "
                    + "FROM HOADON "
                    + "INNER JOIN TAOHOADON ON HOADON.MaHD = TAOHOADON.MaHD "
                    + "INNER JOIN HOPDONG ON HOADON.MaHopDong = HOPDONG.MaHopDong "
                    + "INNER JOIN KHACHHANG ON HOPDONG.MaKH = KHACHHANG.MaKH "
                    + "WHERE LOWER(KHACHHANG.TenKH) LIKE LOWER(?)";

            // Tạo kết nối đến cơ sở dữ liệu
            Connection con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            // Đặt giá trị cho tham số của câu truy vấn
            ps.setString(1, "%" + tenKhachHang + "%");

            ResultSet rs = ps.executeQuery();
            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

            // Xử lý kết quả trả về từ truy vấn
            while (rs.next()) {
                Vector row = new Vector();
                row.add(rs.getInt("MaHD"));
                row.add(rs.getInt("MaKH"));
                row.add(rs.getInt("MaNV"));
                java.sql.Date ngayLapHD = rs.getDate("NgayLapHD");
                String formattedDate = (ngayLapHD != null) ? ngayLapHD.toLocalDate().format(dateFormatter) : null;
                row.add(formattedDate);
                double tongTien = rs.getDouble("TongTien");
                String formattedTongTienThue = String.format("%,.0f VND", tongTien);
                row.add(formattedTongTienThue);
                DSHD.add(row);
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return DSHD;
    }

    public static ArrayList<Vector> getDSHDTheoNgayVaTen(String ngay, String tenKhachHang) {
        ArrayList<Vector> DSHD = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            String sql = "SELECT HOADON.MaHD, KHACHHANG.MaKH, KHACHHANG.TenKH, TAOHOADON.MaNV, HOADON.TongTien, HOADON.NgayLapHD "
                    + "FROM HOADON "
                    + "INNER JOIN TAOHOADON ON HOADON.MaHD = TAOHOADON.MaHD "
                    + "INNER JOIN HOPDONG ON HOADON.MaHopDong = HOPDONG.MaHopDong "
                    + "INNER JOIN KHACHHANG ON HOPDONG.MaKH = KHACHHANG.MaKH "
                    + "WHERE TRUNC(HOADON.NgayLapHD) = TO_DATE(?, 'DD-MM-YYYY') "
                    + "AND LOWER(KHACHHANG.TenKH) LIKE LOWER(?)";

            Connection con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ngay);
            ps.setString(2, "%" + tenKhachHang + "%");

            ResultSet rs = ps.executeQuery();
            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

            while (rs.next()) {
                Vector row = new Vector();
                row.add(rs.getInt("MaHD"));
                row.add(rs.getInt("MaKH"));
                row.add(rs.getInt("MaNV"));
                java.sql.Date ngayLapHD = rs.getDate("NgayLapHD");
                String formattedDate = (ngayLapHD != null) ? ngayLapHD.toLocalDate().format(dateFormatter) : null;
                row.add(formattedDate);
                double tongTien = rs.getDouble("TongTien");
                String formattedTongTienThue = String.format("%,.0f VND", tongTien);
                row.add(formattedTongTienThue);
                DSHD.add(row);
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return DSHD;
    }

    public static ArrayList<Vector> getCTHD(int MaHD) {
        ArrayList<Vector> cthdList = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT PHONG.MaPhong, PHONG.Gia, HOPDONG.HinhThucThue, HOPDONG.TGNhanPhong, HOPDONG.TGTraPhong "
                    + "FROM HOADON "
                    + "JOIN HOPDONG ON HOADON.MaHopDong = HOPDONG.MaHopDong "
                    + "JOIN CHITIETDATPHONG ON HOPDONG.MaHopDong = CHITIETDATPHONG.MaHopDong "
                    + "JOIN PHONG ON CHITIETDATPHONG.MaPhong = PHONG.MaPhong "
                    + "WHERE HOADON.MaHD = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, MaHD);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vector cthd = new Vector<>();
                HopDongModel hopdong = HopDongDAO.getHDtheoMaHopDong(MaHD);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String ngayden = hopdong.getTGNhanPhong().format(formatter);
                String ngaydi = hopdong.getTGTraPhong().format(formatter);
                System.out.println("ngay den: " + ngayden);
                System.out.println("ngay di " + ngaydi);
                long sogio = tinhThoiGian(ngayden, ngaydi) / 60;
                long songay = tinhKhoangCach2Ngay(ngayden, ngaydi);
                System.out.println("so ngay: " + songay);
                double tongTienThue = 0;
                if ("Ngày".equals(rs.getString("HinhThucThue"))) {
                    tongTienThue = songay * rs.getDouble("Gia"); // Thuê theo ngày
                } else if ("Giờ".equals(rs.getString("HinhThucThue"))) {
                    tongTienThue = (sogio / 22.0) * rs.getDouble("Gia") * 1.5; // Thuê theo giờ
                }
                String formattedTongTienThue = String.format("%,.0f VND", tongTienThue);
                cthd.add(rs.getInt("MaPhong"));
                cthd.add(rs.getString("HinhThucThue"));
                java.sql.Date ngaynp = rs.getDate("TGNhanPhong");
                String formattedDatenp = (ngaynp != null) ? ngaynp.toLocalDate().format(dateFormatter) : null;
                java.sql.Date ngaytp = rs.getDate("TGTraPhong");
                String formattedDatetp = (ngaytp != null) ? ngaytp.toLocalDate().format(dateFormatter) : null;
                cthd.add(formattedDatenp);
                cthd.add(formattedDatetp);
                cthd.add(formattedTongTienThue);
                cthdList.add(cthd);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cthdList;
    }

    public static ArrayList<Vector> getDSHDTheoNgay(String ngay) {
        ArrayList<Vector> DSHD = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            String sql = "select HOPDONG.MaKH, HOADON.MaHD, TAOHOADON.MaNV, HOADON.TongTien, HOADON.NgayLapHD FROM HOADON INNER JOIN TAOHOADON ON HOADON.MaHD=TAOHOADON.MaHD INNER JOIN HOPDONG ON HOADON.MaHopDong=HOPDONG.MaHopDong WHERE NgayLapHD = TO_DATE(?,'DD-MM-YYYY')";
            Connection con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ngay); // Thêm giá trị ngay vào PreparedStatement
            ResultSet rs = ps.executeQuery();
            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN")); // Định dạng tiền tệ cho Việt Nam
            while (rs.next()) {
                Vector row = new Vector();

                row.add(rs.getInt("MaHD"));
                row.add(rs.getInt("MaKH"));
                row.add(rs.getInt("MaNV"));
                java.sql.Date ngayLapHD = rs.getDate("NgayLapHD");
                String formattedDate = (ngayLapHD != null) ? ngayLapHD.toLocalDate().format(dateFormatter) : null; // Định dạng ngày lập hóa đơn
                row.add(formattedDate);
                double tongTien = rs.getDouble("TongTien");
                String formattedTongTienThue = String.format("%,.0f VND", tongTien);
                row.add(formattedTongTienThue);
                DSHD.add(row);
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return DSHD;
    }

    public static KhachHangModel getKhachHangByMaHD(int MaHD) {
        KhachHangModel khachHang = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT KHACHHANG.* FROM KHACHHANG "
                    + "JOIN HOPDONG ON KHACHHANG.MaKH = HOPDONG.MaKH "
                    + "JOIN HOADON ON HOPDONG.MaHopDong = HOADON.MaHopDong "
                    + "WHERE HOADON.MaHD = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, MaHD);
            rs = ps.executeQuery();
            if (rs.next()) {
                int MaKH = rs.getInt("MaKH");
                String TenKH = rs.getString("TenKH");
                String CCCD = rs.getString("CCCD");
                LocalDate NgaySinh = rs.getDate("NgaySinh").toLocalDate();
                String GioiTinh = rs.getString("GioiTinh");
                String DiaChi = rs.getString("DiaChi");
                String SDT = rs.getString("SDT");
                int SoHopDong = rs.getInt("SoHopDong");

                khachHang = new KhachHangModel(MaKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khachHang;
    }

    public static int getMaxRow() {
        int row = 0;
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT MAX(MaHD) FROM HOADON")) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    row = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return row;
    }

    public static boolean themHoaDon(int maKM, int maHopDong, double tongtien, double dichvu, int maNV) {
        String sqlInsertHoaDon = "INSERT INTO HOADON VALUES (HoaDon_Seq.NEXTVAL, ?, ?, ?, ?, ?)";
        String sqlInsertTaoHoaDon = "INSERT INTO TAOHOADON (MaHD, MaNV) VALUES (?, ?)";

        try (Connection con = JDBCUtil.getConnection(); PreparedStatement psHoaDon = con.prepareStatement(sqlInsertHoaDon, new String[]{"MaHD"}); PreparedStatement psTaoHoaDon = con.prepareStatement(sqlInsertTaoHoaDon)) {

            con.setAutoCommit(false); // Bắt đầu giao dịch

            LocalDate currentDate = LocalDate.now();
            psHoaDon.setInt(1, maKM);
            psHoaDon.setInt(2, maHopDong);
            psHoaDon.setDate(3, java.sql.Date.valueOf(currentDate));
            psHoaDon.setDouble(4, tongtien);
            psHoaDon.setDouble(5, dichvu);
            psHoaDon.executeUpdate();

            // Lấy mã hóa đơn vừa được tạo
            try (ResultSet rs = psHoaDon.getGeneratedKeys()) {
                if (rs.next()) {
                    int maHD = rs.getInt(1);

                    // Thêm vào bảng TAOHOADON
                    psTaoHoaDon.setInt(1, maHD);
                    psTaoHoaDon.setInt(2, maNV);
                    psTaoHoaDon.executeUpdate();
                }
            }

            con.commit(); // Commit giao dịch
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            try (Connection con = JDBCUtil.getConnection()) {
                if (con != null) {
                    con.rollback(); // Rollback giao dịch nếu có lỗi
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static long tinhThoiGian(String checkInDateTime, String checkOutDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime checkIn = LocalDateTime.parse(checkInDateTime, formatter);
        LocalDateTime checkOut = LocalDateTime.parse(checkOutDateTime, formatter);

        long khoangThoiGianGiuaHaiThoiDiem = ChronoUnit.MINUTES.between(checkIn, checkOut);

        return khoangThoiGianGiuaHaiThoiDiem;
    }

    public static long tinhKhoangCach2Ngay(String startDateStr, String endDateStr) {
        // Định dạng ngày
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Chuyển chuỗi ngày sang LocalDate
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

        // Tính khoảng cách giữa hai ngày
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    public static boolean themHoaDonKhongMaKM(int maHopDong, double tongtien, double dichvu, int maNV) {
        String sqlInsertHoaDon = "INSERT INTO HOADON (MaHD, MaKM, MaHopDong, NgayLapHD, TongTien, TienHongTB) VALUES (HoaDon_Seq.NEXTVAL, NULL, ?, ?, ?, ?)";
        String sqlInsertTaoHoaDon = "INSERT INTO TAOHOADON (MaHD, MaNV) VALUES (?, ?)";

        try (Connection con = JDBCUtil.getConnection(); PreparedStatement psHoaDon = con.prepareStatement(sqlInsertHoaDon, new String[]{"MaHD"}); PreparedStatement psTaoHoaDon = con.prepareStatement(sqlInsertTaoHoaDon)) {

            con.setAutoCommit(false); // Bắt đầu giao dịch

            LocalDate currentDate = LocalDate.now();
            psHoaDon.setInt(1, maHopDong);
            psHoaDon.setDate(2, java.sql.Date.valueOf(currentDate));
            psHoaDon.setDouble(3, tongtien);
            psHoaDon.setDouble(4, dichvu);
            psHoaDon.executeUpdate();

            // Lấy mã hóa đơn vừa được tạo
            try (ResultSet rs = psHoaDon.getGeneratedKeys()) {
                if (rs.next()) {
                    int maHD = rs.getInt(1);

                    // Thêm vào bảng TAOHOADON
                    psTaoHoaDon.setInt(1, maHD);
                    psTaoHoaDon.setInt(2, maNV);
                    psTaoHoaDon.executeUpdate();
                }
            }

            con.commit(); // Commit giao dịch
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            try (Connection con = JDBCUtil.getConnection()) {
                if (con != null) {
                    con.rollback(); // Rollback giao dịch nếu có lỗi
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // lấy danh sách khách hàng có trong cơ sở dữ liệu
    public static ArrayList<HoaDonModel> getDSHoaDon() {

        // Tạo cái ArrayList lưu danh sách khách hàng
        ArrayList<HoaDonModel> DS_HD = new ArrayList<>();
        try {

            // Tạo câu truy vấn 
            String sql = "SELECT * FROM HOADON ORDER BY MAHD DESC";

            // Tạo đối tượng connection
            Connection conn = null;
            conn = JDBCUtil.getConnection();

            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            DS_HD.clear();

            while (rs.next()) {
                int MaHD = rs.getInt(1);
                int MaKM = rs.getInt(2);
                int MaHopDong = rs.getInt(3);
                LocalDate NgaylapHD = rs.getDate(4).toLocalDate();
                double TongTien = rs.getDouble(5);
                double TienHongTB = rs.getDouble(6);

                // gọi contructer
                HoaDonModel kh = new HoaDonModel(MaHD, MaKM, MaHopDong, NgaylapHD, TongTien, TienHongTB);
                DS_HD.add(kh);
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return DS_HD;
    }
    
    public static ArrayList<Vector> getDoanhThuTheoNam() {
        ArrayList<Vector> DS_DoanhThu = new ArrayList<>();

        try {
            String sql = "SELECT SUM(H.TONGTIEN) AS doanh_thu, EXTRACT(YEAR FROM H.NGAYLAPHD) AS nam "
                    + "FROM HOADON H "
                    + "GROUP BY EXTRACT(YEAR FROM H.NGAYLAPHD)";
            
            Connection con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getDouble("doanh_thu"));
                row.add(rs.getInt("nam"));
                DS_DoanhThu.add(row);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return DS_DoanhThu;
    }
    
}
