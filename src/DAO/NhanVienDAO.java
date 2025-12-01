package DAO;

import java.util.logging.Level;
import java.util.logging.Logger;
import Connection.JDBCUtil;
import Model.ChamCongModel;
import Model.NhanVienModel;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;

public class NhanVienDAO {

    public static NhanVienModel getUser(String user, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM NhanVien WHERE TaiKhoan=? AND MatKhau=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, password);

            rs = ps.executeQuery();
            if (rs.next()) {
                LocalDate NgaySinh = rs.getObject("NgaySinh", LocalDate.class);
                return new NhanVienModel(
                        rs.getInt("MaNV"),
                        rs.getString("TenNV"),
                        rs.getString("CCCD"),
                        NgaySinh,
                        rs.getString("GioiTinh"),
                        rs.getString("DiaChi"),
                        rs.getString("SDT"),
                        rs.getString("LoaiNV"),
                        rs.getString("TaiKhoan"),
                        rs.getString("MatKhau"),
                        rs.getLong("LuongCB")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public static ArrayList<NhanVienModel> getDSNhanVien() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<NhanVienModel> danhSach = new ArrayList<>();
        try {
            con = JDBCUtil.getConnection();

            con.setAutoCommit(false); // Tắt chế độ auto-commit

            String sql = "{? = call LayDanhSachNhanVien()}";
            var cs = con.prepareCall(sql);
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                LocalDate NgaySinh = rs.getObject("NgaySinh", LocalDate.class);
                danhSach.add(new NhanVienModel(
                        rs.getInt("MaNV"),
                        rs.getString("TenNV"),
                        rs.getString("CCCD"),
                        NgaySinh,
                        rs.getString("GioiTinh"),
                        rs.getString("DiaChi"),
                        rs.getString("SDT"),
                        rs.getString("LoaiNV"),
                        rs.getString("TaiKhoan"),
                        rs.getString("MatKhau"),
                        rs.getLong("LuongCB")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đóng kết nối và tài nguyên
        }
        return danhSach;
    }

    // Tra cứu nhân viên đầu vào là 5 option MaNV, tên NV, CCCD, SOoDDT và Loại NV
    public static ArrayList<NhanVienModel> TimNV(String option, String textInput) {
        ArrayList<NhanVienModel> DS_NV = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();

            // Tắt chế độ autocommit
            con.setAutoCommit(false);

            String sql = "{ ? = call TimNhanVien(?, ?) }";
            var cs = con.prepareCall(sql);
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setString(2, option);
            cs.setString(3, textInput);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                NhanVienModel nv_temp = new NhanVienModel();
                nv_temp.setMaNV(rs.getInt("MANV"));
                nv_temp.setTenNV(rs.getString("TENNV"));
                nv_temp.setCCCD(rs.getString("CCCD"));
                nv_temp.setNgaySinh(rs.getDate("NGAYSINH").toLocalDate());
                nv_temp.setGioiTinh(rs.getString("GIOITINH"));
                nv_temp.setDiaChi(rs.getString("DIACHI"));
                nv_temp.setSDT(rs.getString("SDT"));
                nv_temp.setLoaiNV(rs.getString("LOAINV"));
                nv_temp.setTaiKhoan(rs.getString("TAIKHOAN"));
                nv_temp.setMatKhau(rs.getString("MATKHAU"));
                nv_temp.setLuongCB(rs.getLong("LUONGCB"));
                DS_NV.add(nv_temp);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return DS_NV;
    }

    public static int demSoLuongNhanVien() {
        int soLuong = 0;

        try (Connection con = JDBCUtil.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) AS SoLuong FROM NHANVIEN"); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                soLuong = rs.getInt("SoLuong");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return soLuong;
    }

    public int ThemNV(NhanVienModel nv) throws SQLException {
        try (Connection con = JDBCUtil.getConnection(); var cs = con.prepareCall("{call ThemNhanVien(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {

             // Tắt chế độ autocommit
            con.setAutoCommit(false);
            
            // Thiết lập các tham số cho procedure
            cs.setString(1, nv.getTenNV());
            cs.setString(2, nv.getCCCD());
            cs.setString(3, nv.getGioiTinh());
            cs.setDate(4, java.sql.Date.valueOf(nv.getNgaySinh())); // Chuyển đổi LocalDate thành java.sql.Date
            cs.setString(5, nv.getDiaChi());
            cs.setString(6, nv.getSDT());
            cs.setString(7, nv.getLoaiNV());

            // Kiểm tra điều kiện mỗi loại nhân viên sẽ được cấp tài khoản và mật khẩu khác nhau
            switch (nv.getLoaiNV()) {
                case "Quản lý" -> {
                    cs.setString(8, "quanly" + (demSoLuongNhanVien() + 1));
                    cs.setString(9, "qlpass");
                    cs.setLong(10, 1200000);
                }
                case "Lễ tân" -> {
                    cs.setString(8, "letan" + (demSoLuongNhanVien() + 1));
                    cs.setString(9, "ltpass");
                    cs.setLong(10, 800000);
                }
                case "Tạp Vụ" -> {
                    cs.setNull(8, java.sql.Types.VARCHAR);
                    cs.setNull(9, java.sql.Types.VARCHAR);
                    cs.setLong(10, 500000);
                }

            }

            // Thực thi câu lệnh SQL
            return cs.executeUpdate();

        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1) { // Error code for unique constraint violation in Oracle
                if (ex.getMessage().contains("NHANVIEN_UNIQUE_CCCD")) {
                    JOptionPane.showMessageDialog(null, "Lỗi: CCCD đã tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else if (ex.getMessage().contains("NHANVIEN_UNIQUE_SDT")) {
                    JOptionPane.showMessageDialog(null, "Lỗi: Số điện thoại đã tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Lỗi: Vi phạm ràng buộc dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            return 0;
        }
    }

    public static int XoaNV(int value) throws SQLException {
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
//        System.out.println(value);
        String sql = "UPDATE NHANVIEN SET TINHTRANG = 'Nghỉ làm' WHERE MANV=?";
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ps.setInt(1, value);
        return ps.executeUpdate();
    }

    public static boolean CapNhatNV(int maNV, NhanVienModel nv) {
        try (Connection con = JDBCUtil.getConnection(); var cs = con.prepareCall("{call CapNhatNhanVien(?,?,?,?,?,?,?,?,?)}")) {

            // Thiết lập các tham số cho procedure
            cs.setInt(1, maNV);
            cs.setString(2, nv.getTenNV());
            cs.setString(3, nv.getCCCD());
            cs.setObject(4, nv.getNgaySinh());
            cs.setString(5, nv.getGioiTinh());
            cs.setString(6, nv.getDiaChi());
            cs.setString(7, nv.getSDT());
            cs.setString(8, nv.getMatKhau());
            cs.setLong(9, nv.getLuongCB());

            // Thực thi procedure
            cs.execute();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static NhanVienModel getNVtheoMaNV(int maNV) throws SQLException {
        // Tạo câu truy vấn 
        String sql = "SELECT * FROM NHANVIEN WHERE MaNV=?";

        // Tạo đối tượng connection
        Connection conn = null;
        NhanVienModel nv = null;

        try {
            conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, maNV);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String TenNV = rs.getString(2);
                String CCCD = rs.getString(3);
                LocalDate NgaySinh = rs.getDate(4).toLocalDate();
                String GioiTinh = rs.getString(5);
                String DiaChi = rs.getString(6);
                String SDT = rs.getString(7);
                String LoaiNV = rs.getString(8);
                String TaiKhoan = rs.getString(9);
                String MatKhau = rs.getString(10);
                long LuongCB = rs.getLong(11);

                // gọi constructor
                nv = new NhanVienModel(maNV, TenNV, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, LoaiNV, TaiKhoan, MatKhau, LuongCB);
            }

            rs.close();
            ps.close();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return nv;
    }

    public static NhanVienModel getNVtheoCCCD(String CCCD) throws SQLException {
        // Tạo câu truy vấn 
        String sql = "SELECT * FROM NHANVIEN WHERE CCCD=?";

        // Tạo đối tượng connection
        Connection conn = null;
        NhanVienModel nv = null;

        try {
            conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, CCCD);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int maNV = rs.getInt(1);
                String TenNV = rs.getString(2);
                LocalDate NgaySinh = rs.getDate(4).toLocalDate();
                String GioiTinh = rs.getString(5);
                String DiaChi = rs.getString(6);
                String SDT = rs.getString(7);
                String LoaiNV = rs.getString(8);
                String TaiKhoan = rs.getString(9);
                String MatKhau = rs.getString(10);
                long LuongCB = rs.getLong(11);

                // gọi constructor
                nv = new NhanVienModel(maNV, TenNV, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, LoaiNV, TaiKhoan, MatKhau, LuongCB);
            }

            rs.close();
            ps.close();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return nv;
    }
}
