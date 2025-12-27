package DAO;

import Connection.JDBCUtil;
import Model.PhongModel;
import Model.LoaiPhongModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PhongDAO {

    // lấy danh sách phòng có trong cơ sở dữ liệu
    public static ArrayList<PhongModel> hungia_getDSPhong() {
        ArrayList<PhongModel> ds = new ArrayList<>();

        String sql = "SELECT P.MaPhong, P.TinhTrang, L.MaLoai, L.TenLoai, L.Gia "
                + "FROM PHONG P "
                + "JOIN LOAIPHONG L ON P.MaLoai = L.MaLoai "
                + "ORDER BY P.MaPhong ASC";

        try {
            Connection con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LoaiPhongModel loai = new LoaiPhongModel(
                        rs.getInt("MaLoai"),
                        rs.getString("TenLoai"),
                        rs.getLong("Gia")
                );

                ds.add(new PhongModel(
                        rs.getInt("MaPhong"),
                        loai,
                        rs.getString("TinhTrang")
                ));
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    // Lấy dánh ách phòng Bình thường cho khách hàng xem
    public static ArrayList<PhongModel> hungia_getDSPhongBinhThuong() {
        ArrayList<PhongModel> ds = new ArrayList<>();

        String sql = "SELECT P.MaPhong, P.TinhTrang, L.MaLoai, L.TenLoai, L.Gia "
                + "FROM PHONG P "
                + "JOIN LOAIPHONG L ON P.MaLoai = L.MaLoai "
                + "WHERE P.TinhTrang = 'Bình thường' "
                + "ORDER BY P.MaPhong ASC";

        try {
            Connection con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                // Tạo model loại phòng
                LoaiPhongModel loai = new LoaiPhongModel(
                        rs.getInt("MaLoai"),
                        rs.getString("TenLoai"),
                        rs.getLong("Gia")
                );

                // Tạo model phòng
                PhongModel phong = new PhongModel(
                        rs.getInt("MaPhong"),
                        loai,
                        rs.getString("TinhTrang")
                );

                ds.add(phong);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    // Lấy danh sách phòng theo ngày kiểm tra xem ngày hôm dó có phòng trống hay không 
    public static ArrayList<PhongModel> hungia_getDStheoNgay(String ngayNhan, String ngayTra) {
        ArrayList<PhongModel> dsPhong = new ArrayList<>();

        String sql
                = "SELECT P.MaPhong, L.MaLoai, L.TenLoai, P.TinhTrang, L.Gia "
                + "FROM PHONG P "
                + "JOIN LOAIPHONG L ON P.MaLoai = L.MaLoai "
                + "WHERE P.MaPhong NOT IN ( "
                + "   SELECT p.MaPhong "
                + "   FROM PHONG p "
                + "   JOIN CHITIETDATPHONG ct ON ct.MaPhong = p.MaPhong "
                + "   JOIN HOPDONG h ON h.MaHopDong = ct.MaHopDong "
                + "   WHERE h.TGNhanPhong < TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS') "
                + "     AND h.TGTraPhong > TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS') "
                + ") "
                + "ORDER BY P.MaPhong ASC";

        try {
            Connection con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            // Set tham số thời gian
            ps.setString(1, ngayTra);
            ps.setString(2, ngayNhan);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                LoaiPhongModel loai = new LoaiPhongModel(
                        rs.getInt("MaLoai"),
                        rs.getString("TenLoai"),
                        rs.getLong("Gia")
                );

                PhongModel p = new PhongModel(
                        rs.getInt("MaPhong"),
                        loai,
                        rs.getString("TinhTrang")
                );

                dsPhong.add(p);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsPhong;
    }

    // Lấy danh sách phòng theo ngày kiểm tra xem ngày hôm dó có phòng trống hay không và tính trnagj phòng bình thường cho khách hàng
    public static ArrayList<PhongModel> hungia_getDStheoNgayBinhThuong(String ngayNhan, String ngayTra) {
        ArrayList<PhongModel> dsPhong = new ArrayList<>();

        String sql
                = "SELECT P.MaPhong, L.MaLoai, L.TenLoai, P.TinhTrang, L.Gia "
                + "FROM PHONG P "
                + "JOIN LOAIPHONG L ON P.MaLoai = L.MaLoai "
                + "WHERE P.MaPhong NOT IN ( "
                + "   SELECT p.MaPhong "
                + "   FROM PHONG p "
                + "   JOIN CHITIETDATPHONG ct ON ct.MaPhong = p.MaPhong "
                + "   JOIN HOPDONG h ON h.MaHopDong = ct.MaHopDong "
                + "   WHERE h.TGNhanPhong < TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS') "
                + "     AND h.TGTraPhong > TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS') "
                + ") "
                + "ORDER BY P.MaPhong ASC";

        try {
            Connection con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            // Set tham số thời gian
            ps.setString(1, ngayTra);
            ps.setString(2, ngayNhan);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                LoaiPhongModel loai = new LoaiPhongModel(
                        rs.getInt("MaLoai"),
                        rs.getString("TenLoai"),
                        rs.getLong("Gia")
                );

                PhongModel p = new PhongModel(
                        rs.getInt("MaPhong"),
                        loai,
                        rs.getString("TinhTrang")
                );

                dsPhong.add(p);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsPhong;
    }

    // Lấy danh sách phòng lọc theo tên loại phòng 
    public static ArrayList<PhongModel> hungia_getDStheoLoaiPhong(String tenLoai, String ngayNhan, String ngayTra) {
        ArrayList<PhongModel> dsPhong = new ArrayList<>();

        String sql
                = "SELECT P.MaPhong, P.TinhTrang, L.MaLoai, L.TenLoai, L.Gia "
                + "FROM PHONG P "
                + "JOIN LOAIPHONG L ON P.MaLoai = L.MaLoai "
                + "WHERE L.TenLoai = ? "
                + "  AND P.MaPhong NOT IN ( "
                + "        SELECT p.MaPhong "
                + "        FROM PHONG p "
                + "        JOIN CHITIETDATPHONG ct ON ct.MaPhong = p.MaPhong "
                + "        JOIN HOPDONG h ON h.MaHopDong = ct.MaHopDong "
                + "        WHERE h.TGNhanPhong < TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS') "
                + "          AND h.TGTraPhong  > TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS') "
                + "  ) "
                + "ORDER BY P.MaPhong ASC";

        try {
            Connection con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, tenLoai);  // ví dụ: "A" hoặc "B" hoặc "C"
            ps.setString(2, ngayTra);
            ps.setString(3, ngayNhan);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                LoaiPhongModel loai = new LoaiPhongModel(
                        rs.getInt("MaLoai"),
                        rs.getString("TenLoai"),
                        rs.getLong("Gia")
                );

                PhongModel phong = new PhongModel(
                        rs.getInt("MaPhong"),
                        loai,
                        rs.getString("TinhTrang")
                );

                dsPhong.add(phong);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsPhong;
    }

    // Lấy danh sách phòng lọc theo tên loại phòng nhưng kèm theo tình trạng Bình Thường giúp cho khách hàng
    public static ArrayList<PhongModel> hungia_getDStheoLoaiPhongBinhThuong(String tenLoai, String ngayNhan, String ngayTra) {
        ArrayList<PhongModel> dsPhong = new ArrayList<>();

        String sql
                = "SELECT P.MaPhong, P.TinhTrang, L.MaLoai, L.TenLoai, L.Gia "
                + "FROM PHONG P "
                + "JOIN LOAIPHONG L ON P.MaLoai = L.MaLoai "
                + "WHERE L.TenLoai = ? "
                + "  AND P.TinhTrang = 'Bình thường' "
                + "  AND P.MaPhong NOT IN ( "
                + "        SELECT p.MaPhong "
                + "        FROM PHONG p "
                + "        JOIN CHITIETDATPHONG ct ON ct.MaPhong = p.MaPhong "
                + "        JOIN HOPDONG h ON h.MaHopDong = ct.MaHopDong "
                + "        WHERE h.TGNhanPhong < TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS') "
                + "          AND h.TGTraPhong  > TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS') "
                + "  ) "
                + "ORDER BY P.MaPhong ASC";

        try {
            Connection con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, tenLoai);  // ví dụ: "A" hoặc "B" hoặc "C"
            ps.setString(2, ngayTra);
            ps.setString(3, ngayNhan);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                LoaiPhongModel loai = new LoaiPhongModel(
                        rs.getInt("MaLoai"),
                        rs.getString("TenLoai"),
                        rs.getLong("Gia")
                );

                PhongModel phong = new PhongModel(
                        rs.getInt("MaPhong"),
                        loai,
                        rs.getString("TinhTrang")
                );

                dsPhong.add(phong);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsPhong;
    }

    // Lấy danh sách phòng khi lọc theo tầng 
    public static ArrayList<PhongModel> hungia_getDStheoTang(int tang, String ngayNhan, String ngayTra) {
        ArrayList<PhongModel> dsPhong = new ArrayList<>();

        String sql
                = "SELECT P.MaPhong, P.TinhTrang, L.MaLoai, L.TenLoai, L.Gia "
                + "FROM PHONG P "
                + "JOIN LOAIPHONG L ON P.MaLoai = L.MaLoai "
                + "WHERE TRUNC(P.MaPhong / 100) = ? "
                + "  AND P.MaPhong NOT IN ( "
                + "        SELECT p.MaPhong "
                + "        FROM PHONG p "
                + "        JOIN CHITIETDATPHONG ct ON ct.MaPhong = p.MaPhong "
                + "        JOIN HOPDONG h ON h.MaHopDong = ct.MaHopDong "
                + "        WHERE h.TGNhanPhong < TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS') "
                + "          AND h.TGTraPhong  > TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS') "
                + "  ) "
                + "ORDER BY P.MaPhong ASC";

        try {
            Connection con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, tang);      // tầng 1, 2, 3, 4...
            ps.setString(2, ngayTra);
            ps.setString(3, ngayNhan);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LoaiPhongModel loai = new LoaiPhongModel(
                        rs.getInt("MaLoai"),
                        rs.getString("TenLoai"),
                        rs.getLong("Gia")
                );

                PhongModel phong = new PhongModel(
                        rs.getInt("MaPhong"),
                        loai,
                        rs.getString("TinhTrang")
                );

                dsPhong.add(phong);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsPhong;
    }

    // Lấy danh sách phòng khi lọc theo tầng với các phòng cso tình trrangj bình thường
    public static ArrayList<PhongModel> hungia_getDStheoTangBinhThuong(int tang, String ngayNhan, String ngayTra) {
        ArrayList<PhongModel> dsPhong = new ArrayList<>();

        String sql
                = "SELECT P.MaPhong, P.TinhTrang, L.MaLoai, L.TenLoai, L.Gia "
                + "FROM PHONG P "
                + "JOIN LOAIPHONG L ON P.MaLoai = L.MaLoai "
                + "WHERE TRUNC(P.MaPhong / 100) = ? "
                + "  AND P.TinhTrang = 'Bình thường' "
                + "  AND P.MaPhong NOT IN ( "
                + "        SELECT p.MaPhong "
                + "        FROM PHONG p "
                + "        JOIN CHITIETDATPHONG ct ON ct.MaPhong = p.MaPhong "
                + "        JOIN HOPDONG h ON h.MaHopDong = ct.MaHopDong "
                + "        WHERE h.TGNhanPhong < TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS') "
                + "          AND h.TGTraPhong  > TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS') "
                + "  ) "
                + "ORDER BY P.MaPhong ASC";

        try {
            Connection con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, tang);      // tầng 1, 2, 3, 4...
            ps.setString(2, ngayTra);
            ps.setString(3, ngayNhan);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LoaiPhongModel loai = new LoaiPhongModel(
                        rs.getInt("MaLoai"),
                        rs.getString("TenLoai"),
                        rs.getLong("Gia")
                );

                PhongModel phong = new PhongModel(
                        rs.getInt("MaPhong"),
                        loai,
                        rs.getString("TinhTrang")
                );

                dsPhong.add(phong);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsPhong;
    }

    // Tra cứu phòng theo ngày giờ và cả loại phòng và tầng
    public static ArrayList<PhongModel> hungia_getDStheotracuu(String tenLoai, int tang, String ngayNhan, String ngayTra) {
        ArrayList<PhongModel> dsPhong = new ArrayList<>();

        String sql
                = "SELECT P.MaPhong, P.TinhTrang, L.MaLoai, L.TenLoai, L.Gia "
                + "FROM PHONG P "
                + "JOIN LOAIPHONG L ON P.MaLoai = L.MaLoai "
                + "WHERE L.TenLoai = ? "
                + "  AND TRUNC(P.MaPhong / 100) = ? "
                + "  AND P.MaPhong NOT IN ( "
                + "        SELECT p.MaPhong "
                + "        FROM PHONG p "
                + "        JOIN CHITIETDATPHONG ct ON ct.MaPhong = p.MaPhong "
                + "        JOIN HOPDONG h ON h.MaHopDong = ct.MaHopDong "
                + "        WHERE h.TGNhanPhong < TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS') "
                + "          AND h.TGTraPhong  > TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS') "
                + "  ) "
                + "ORDER BY P.MaPhong ASC";

        try {
            Connection con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, tenLoai);   // 'A', 'B', 'C'
            ps.setInt(2, tang);      // tầng 1, 2, 3, 4...
            ps.setString(3, ngayTra);
            ps.setString(4, ngayNhan);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LoaiPhongModel loai = new LoaiPhongModel(
                        rs.getInt("MaLoai"),
                        rs.getString("TenLoai"),
                        rs.getLong("Gia")
                );

                PhongModel phong = new PhongModel(
                        rs.getInt("MaPhong"),
                        loai,
                        rs.getString("TinhTrang")
                );

                dsPhong.add(phong);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsPhong;
    }

    // Tra cứu phòng theo ngày giờ và cả loại phòng và tầng với tình trạng bình thường cho khách hàng xem
    public static ArrayList<PhongModel> hungia_getDStheotracuuBinhThuong(String tenLoai, int tang, String ngayNhan, String ngayTra) {
        ArrayList<PhongModel> dsPhong = new ArrayList<>();

        String sql
                = "SELECT P.MaPhong, P.TinhTrang, L.MaLoai, L.TenLoai, L.Gia "
                + "FROM PHONG P "
                + "JOIN LOAIPHONG L ON P.MaLoai = L.MaLoai "
                + "WHERE L.TenLoai = ? "
                + "  AND TRUNC(P.MaPhong / 100) = ? "
                + "  AND P.TinhTrang = 'Bình thường' "
                + "  AND P.MaPhong NOT IN ( "
                + "        SELECT p.MaPhong "
                + "        FROM PHONG p "
                + "        JOIN CHITIETDATPHONG ct ON ct.MaPhong = p.MaPhong "
                + "        JOIN HOPDONG h ON h.MaHopDong = ct.MaHopDong "
                + "        WHERE h.TGNhanPhong < TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS') "
                + "          AND h.TGTraPhong  > TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS') "
                + "  ) "
                + "ORDER BY P.MaPhong ASC";

        try {
            Connection con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, tenLoai);
            ps.setInt(2, tang);      // tầng 1, 2, 3, 4...
            ps.setString(3, ngayTra);
            ps.setString(4, ngayNhan);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LoaiPhongModel loai = new LoaiPhongModel(
                        rs.getInt("MaLoai"),
                        rs.getString("TenLoai"),
                        rs.getLong("Gia")
                );

                PhongModel phong = new PhongModel(
                        rs.getInt("MaPhong"),
                        loai,
                        rs.getString("TinhTrang")
                );

                dsPhong.add(phong);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsPhong;
    }

    public static PhongModel getPhongTheoMa(int maPhong) throws SQLException {

        String sql = "SELECT P.MaPhong, P.TinhTrang, L.MaLoai, L.TenLoai, L.Gia "
                + "FROM PHONG P "
                + "JOIN LOAIPHONG L ON P.MaLoai = L.MaLoai "
                + "WHERE P.MaPhong = ?";

        PhongModel phong = null;

        try (Connection conn = JDBCUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maPhong);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    // Tạo object LoaiPhongModel
                    LoaiPhongModel loai = new LoaiPhongModel(
                            rs.getInt("MaLoai"),
                            rs.getString("TenLoai"),
                            rs.getLong("Gia")
                    );

                    // Tạo object PhongModel
                    phong = new PhongModel(
                            rs.getInt("MaPhong"),
                            loai,
                            rs.getString("TinhTrang")
                    );
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }

        return phong;
    }

    public static boolean updatePhong(
            int maPhong,
            String tinhTrang,
            int maLoai
    ) {
        String sql =
            "UPDATE PHONG SET TinhTrang = ?, MaLoai = ? WHERE MaPhong = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tinhTrang);
            ps.setInt(2, maLoai);
            ps.setInt(3, maPhong);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static String getTinhTrangPhong(int maPhong) {
        String sql = "SELECT TinhTrang FROM PHONG WHERE MaPhong = ?";
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maPhong);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("TinhTrang");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static ArrayList<LoaiPhongModel> getAllLoaiPhong() throws SQLException {
        ArrayList<LoaiPhongModel> list = new ArrayList<>();
        Connection con = JDBCUtil.getConnection();

        String sql = "SELECT MaLoai, TenLoai, Gia FROM LOAIPHONG ORDER BY MaLoai";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            LoaiPhongModel lp = new LoaiPhongModel(
                rs.getInt("MaLoai"),
                rs.getString("TenLoai"),
                rs.getLong("Gia")
            );
            list.add(lp);
        }

        con.close();
        return list;
    }
    
    public static int getMaLoaiTheoTen(String tenLoai) {
        String sql = "SELECT MaLoai FROM LOAIPHONG WHERE TenLoai = ?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tenLoai);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("MaLoai");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // không tìm thấy
    }
    
    public static boolean insertLoaiPhong(String tenLoai, long gia) {
        String sql = """
            INSERT INTO LOAIPHONG (MaLoai, TenLoai, Gia)
            VALUES (LoaiPhong_Seq.NEXTVAL, ?, ?)
        """;

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tenLoai);
            ps.setLong(2, gia);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            return false; // trùng tên
        }
    }

    public static boolean updateLoaiPhong(int maLoai, String tenLoai, long gia) {
        String sql = "UPDATE LOAIPHONG SET TenLoai = ?, Gia = ? WHERE MaLoai = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tenLoai);
            ps.setLong(2, gia);
            ps.setInt(3, maLoai);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean canDeleteLoaiPhong(int maLoai) {
        String sql = "SELECT COUNT(*) FROM PHONG WHERE MaLoai = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maLoai);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean deleteLoaiPhong(int maLoai) {
        String sql = "DELETE FROM LOAIPHONG WHERE MaLoai = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maLoai);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
