
package DAO;
import Connection.*;
import Model.NhanVienModel;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;





public class TrangChuDAO {
  public static int KTLoaiNV(int maUser) {
        // System.out.println(maUser);
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select LoaiNV from NhanVien  where MaNV=? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, maUser);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // System.out.println(rs.getString("LoaiNV"));
                switch (rs.getString("LoaiNV")) {
                    case "Quản lý" -> {
                        return 1;
                    }
                    case "Tạp vụ" -> {
                        return 2;
                    }
                    case "Lễ tân" -> {
                        return 3;
                    }
                    
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(TrangChuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
