/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connection.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.CallableStatement;
import java.sql.Types;
import Model.LuongModel;
/**
 *
 * @author phuch
 */
public class LuongDAO {

    public static double luongcb;

    public LuongDAO() {

    }

    public static ArrayList<LuongModel> getDSLuong() {
        ArrayList<LuongModel> DS_Luong = new ArrayList();
        try {
            String sql = "SELECT NHANVIEN.MaNV, CHAMCONG.MaCC, NHANVIEN.TenNV, NHANVIEN.LoaiNV, NHANVIEN.LuongCB, CHAMCONG.SoNgayDiLam, CHAMCONG.SoGioLamThem FROM NHANVIEN INNER JOIN CHAMCONG ON NHANVIEN.MaNV = CHAMCONG.MaNV";
            Connection con = null;
            con = JDBCUtil.getConnection();
            PreparedStatement ps = (PreparedStatement) con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int MaNV = rs.getInt("MaNV");
                int MaCC = rs.getInt("MaCC");
                String HoTen = rs.getString("TenNv");
                String ChucVu = rs.getString("LoaiNV");
                Double LuongCB = rs.getDouble("LuongCB");
                int SoNgayDiLam = rs.getInt("SoNgayDiLam");
                int SoGioLamThem = rs.getInt("SoGioLamThem");
                LuongModel luong = new LuongModel(MaNV, MaCC, HoTen, ChucVu, LuongCB, SoNgayDiLam, SoGioLamThem);
                DS_Luong.add(luong);
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return DS_Luong;
    }

   public float TinhLuong(LuongModel luong) {
    float tongLuong = 0;
    Connection con = null;
    CallableStatement cstmt = null;
    try {
        con = JDBCUtil.getConnection();
        String call = "{ ? = call Tinh_TongLuong(?) }";
        cstmt = con.prepareCall(call);
        cstmt.registerOutParameter(1, Types.FLOAT); // Đăng ký kiểu dữ liệu trả về
        cstmt.setInt(2, luong.getMaNV()); // Truyền tham số vào
        cstmt.execute();
        tongLuong = cstmt.getFloat(1);
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (cstmt != null) cstmt.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    return tongLuong;
}


    public static boolean updateSauKhiTinhLuong() throws SQLException {
        Connection con = null;
        con = JDBCUtil.getConnection();
        String sql = "UPDATE CHAMCONG SET SoGioLamThem = 0, SoNgayDiLam = 0 ";
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        return ps.executeUpdate() > 0;
    }

    public static ArrayList<LuongModel> timNV(String option, String textInput) {
        ArrayList<LuongModel> DS_Luong = new ArrayList<>();
        try {
            Connection con = null;
            con = JDBCUtil.getConnection();
            String sql = null;
            switch (option) {
                case "Mã NV":
                    sql = "SELECT NHANVIEN.MaNV, CHAMCONG.MaCC, NHANVIEN.TenNV, NHANVIEN.LoaiNV, NHANVIEN.LuongCB, CHAMCONG.SoNgayDiLam, CHAMCONG.SoGioLamThem FROM NHANVIEN INNER JOIN CHAMCONG ON NHANVIEN.MaNV = CHAMCONG.MaNV WHERE NHANVIEN.MaNV =? ";
                    break;
                case "Tên NV":
                    sql = "SELECT NHANVIEN.MaNV, CHAMCONG.MaCC, NHANVIEN.TenNV, NHANVIEN.LoaiNV, NHANVIEN.LuongCB, CHAMCONG.SoNgayDiLam, CHAMCONG.SoGioLamThem FROM NHANVIEN INNER JOIN CHAMCONG ON NHANVIEN.MaNV = CHAMCONG.MaNV WHERE LOWER(NHANVIEN.TenNV) LIKE LOWER(?)";
                    textInput = "%" + textInput + "%";
                    break;
            }

            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, textInput);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LuongModel luong_temp = new LuongModel();
                luong_temp.setMaNV(rs.getInt("MaNV"));
                luong_temp.setMaCC(rs.getInt("MaCC"));
                luong_temp.setTenNV(rs.getString("TenNV"));
                luong_temp.setLoaiNV(rs.getString("LoaiNV"));
                luong_temp.setLuongCB(rs.getDouble("LuongCB"));
                luong_temp.setSNDL(rs.getInt("SoNgayDiLam"));
                luong_temp.setSGLT(rs.getInt("SoGioLamThem"));
                DS_Luong.add(luong_temp);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return DS_Luong;
    }
}
