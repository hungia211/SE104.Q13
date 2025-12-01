/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Connection.*;
import Model.LichSuModel;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author phuch
 */
public class LichSuDAO {
    public static int getSoHopDong(String cccd) throws SQLException {
    String query = "SELECT SoHopDong FROM KHACHHANG WHERE CCCD = ?";
    try (Connection connection = JDBCUtil.getConnection();
         PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, cccd);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("SoHopDong");
        } else {
            return -1; // No records found
        }
    } catch (SQLException e) {
        e.printStackTrace(); // This will print the stack trace along with the error message.
        return -1; // Return -1 to indicate an error
    }
}


   public static ArrayList<LichSuModel> getDSLS(){
    ArrayList<LichSuModel> DS_LichSu = new ArrayList<>();
    Connection con = null;
    try {
        String sql = "SELECT KHACHHANG.MaKH, HOPDONGTHUEPHONG.MaHopDong,HOPDONGTHUEPHONG.NgayLapHopDong, KHACHHANG.TenKH, HOPDONGTHUEPHONG.TGNhanPhong, HOPDONGTHUEPHONG.TGTraPhong, HOPDONGTHUEPHONG.TinhTrangHopDong FROM HOPDONGTHUEPHONG INNER JOIN KHACHHANG ON KHACHHANG.MaKH = HOPDONGTHUEPHONG.MaKH ";
        con = JDBCUtil.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
         while (rs.next()){
                LichSuModel lichsu_temp = new LichSuModel();
                lichsu_temp.setMaKH(rs.getInt("MaKH"));
                lichsu_temp.setMaHD(rs.getInt("MaHopDong"));
                lichsu_temp.setTenKH(rs.getString("TenKH"));
                lichsu_temp.settGlaphopdong(rs.getTimestamp("NgayLapHopDong").toLocalDateTime());
                lichsu_temp.settGNhanPhong(rs.getTimestamp("TGNhanPhong").toLocalDateTime());
                lichsu_temp.settGTraPhong(rs.getTimestamp("TGTraPhong").toLocalDateTime());
                lichsu_temp.setTinhtrangHD(rs.getString("TinhTrangHD"));
            DS_LichSu.add(lichsu_temp);
        }
    } catch (SQLException ex) {
        System.err.println("SQL Error: " + ex.getMessage());
        throw new RuntimeException("Error accessing database", ex);
    } finally {
        try {
            if (con != null) con.close();
        } catch (SQLException ex) {
            System.err.println("Error closing connection: " + ex.getMessage());
        }
    }
    return DS_LichSu;
}
   public static ArrayList<LichSuModel> timKH( String textInput){
        ArrayList<LichSuModel> DS_LS = new ArrayList<>();
        try {
            Connection con = null;
            con = JDBCUtil.getConnection();
            String sql = "SELECT KHACHHANG.MaKH, HOPDONG.MaHopDong, KHACHHANG.TenKH, HOPDONG.TGNhanPhong, HOPDONG.TGTraPhong,HOPDONG.TinhTrangHD,HOPDONG.NgayLapHopDong FROM HOPDONG INNER JOIN KHACHHANG ON KHACHHANG.MaKH = HOPDONG.MaKH WHERE KHACHHANG.CCCD=?";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, textInput);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                LichSuModel lichsu_temp = new LichSuModel();
                lichsu_temp.setMaKH(rs.getInt("MaKH"));
                lichsu_temp.setMaHD(rs.getInt("MaHopDong"));
                lichsu_temp.setTenKH(rs.getString("TenKH"));
                lichsu_temp.settGlaphopdong(rs.getTimestamp("NgayLapHopDong").toLocalDateTime());
                lichsu_temp.settGNhanPhong(rs.getTimestamp("TGNhanPhong").toLocalDateTime());
                lichsu_temp.settGTraPhong(rs.getTimestamp("TGTraPhong").toLocalDateTime());
                lichsu_temp.setTinhtrangHD(rs.getString("TinhTrangHD"));
                DS_LS.add(lichsu_temp);
            }
            con.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return DS_LS;
    }
}
