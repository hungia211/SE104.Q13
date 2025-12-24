package DAO;

import Connection.JDBCUtil;
import Model.HopDongModel;
import Model.KhachHangModel;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import javax.swing.JOptionPane;

public class HopDongDAO {

    // lấy danh sách khách hàng có trong cơ sở dữ liệu
    public static ArrayList<HopDongModel> getDSHopDong() {
        ArrayList<HopDongModel> DS_HopDong = new ArrayList<>();

        String sql = "SELECT * FROM HOPDONG ORDER BY MAHOPDONG DESC";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            DS_HopDong.clear();

            while (rs.next()) {
                int MaHopDong = rs.getInt("MAHOPDONG");
                int MaKH = rs.getInt("MAKH");
                LocalDateTime NgayLapHopDong = rs.getTimestamp("NGAYLAPHOPDONG").toLocalDateTime();
                LocalDateTime TGNhanPhong = rs.getTimestamp("TGNHANPHONG").toLocalDateTime();
                LocalDateTime TGTraPhong = rs.getTimestamp("TGTRAPHONG").toLocalDateTime();
                String TinhTrangHD = rs.getString("TINHTRANGHD");
                double TriGiaHopDong = rs.getDouble("TRIGIAHD");
                String HinhThucThue = rs.getString("HINHTHUCTHUE");

                HopDongModel hopDong = new HopDongModel(
                        MaHopDong,
                        MaKH,
                        NgayLapHopDong,
                        TGNhanPhong,
                        TGTraPhong,
                        TinhTrangHD,
                        TriGiaHopDong,
                        HinhThucThue
                );
                DS_HopDong.add(hopDong);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return DS_HopDong;
    }

    public static ArrayList<HopDongModel> getHopDongTheoTinhTrang(String textInput) {
        ArrayList<HopDongModel> DS_HopDong = new ArrayList<>();
        try {
            Connection con = null;
            con = JDBCUtil.getConnection();
            String sql = null;

            sql = "SELECT * FROM HOPDONG WHERE TinhTrangHD = ?";

            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, textInput);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HopDongModel hopdong_temp = new HopDongModel();
                hopdong_temp.setMaHopDong(rs.getInt("MAHOPDONG"));
                hopdong_temp.setMaKH(rs.getInt("MAKH"));
                hopdong_temp.setNgayLapHopDong(rs.getTimestamp("NGAYLAPHOPDONG").toLocalDateTime());
                hopdong_temp.setTGNhanPhong(rs.getTimestamp("TGNHANPHONG").toLocalDateTime());
                hopdong_temp.setTGTraPhong(rs.getTimestamp("TGTRAPHONG").toLocalDateTime());
                hopdong_temp.setTinhTrangHD(rs.getString("TINHTRANGHD"));
                hopdong_temp.setTriGiaHopDong(rs.getDouble("TRIGIAHD"));
                hopdong_temp.setHinhThucThue("HINHTHUCTHUE");
                DS_HopDong.add(hopdong_temp);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return DS_HopDong;
    }

    public static ArrayList<HopDongModel> TimHopDong(String option, String textInput) {
        ArrayList<HopDongModel> DS_HopDong = new ArrayList<>();
        try {
            Connection con = null;
            con = JDBCUtil.getConnection();
            String sql = null;
            switch (option) {
                case "Mã Hợp Đồng":
                    sql = "SELECT * FROM HOPDONG WHERE LOWER(MAHOPDONG) LIKE LOWER(?)";
                    textInput = "%" + textInput + "%"; // Để tìm theo chuỗi con
                    break;
                case "Mã KH":
                    sql = "SELECT * FROM HOPDONG WHERE LOWER(MAKH) LIKE LOWER(?)";
                    textInput = "%" + textInput + "%"; // Để tìm theo chuỗi con
                    break;
                case "Tình Trạng HĐ":
                    sql = "SELECT * FROM HOPDONG WHERE LOWER(TINHTRANGHD) LIKE LOWER(?)";
                    textInput = "%" + textInput + "%"; // Để tìm theo chuỗi con
                    break;
            }

            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, textInput);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HopDongModel hopdong_temp = new HopDongModel();
                hopdong_temp.setMaHopDong(rs.getInt("MAHOPDONG"));
                hopdong_temp.setMaKH(rs.getInt("MAKH"));
                hopdong_temp.setNgayLapHopDong(rs.getTimestamp("NGAYLAPHOPDONG").toLocalDateTime());
                hopdong_temp.setTGNhanPhong(rs.getTimestamp("TGNHANPHONG").toLocalDateTime());
                hopdong_temp.setTGTraPhong(rs.getTimestamp("TGTRAPHONG").toLocalDateTime());
                hopdong_temp.setTinhTrangHD(rs.getString("TINHTRANGHD"));
                hopdong_temp.setTriGiaHopDong(rs.getDouble("TRIGIAHD"));
                hopdong_temp.setHinhThucThue("HINHTHUCTHUE");
                DS_HopDong.add(hopdong_temp);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return DS_HopDong;
    }

    public static int XoaHopDong(int maHopDong) throws SQLException {
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "DELETE HOPDONG WHERE MAHOPDONG=?";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, maHopDong);

            return ps.executeUpdate();
        } catch (SQLException ex) {
            if (ex.getMessage().contains("ORA-02292; integrity constraint (DATABASE_DOAN_NPNT.FK_HOADON HOPDONG) violated - child record found https://docs.oracle.com/error-help/db/ora-02292/")) {
                JOptionPane.showMessageDialog(null, "Lỗi: Hợp đồng này đã thuộc một hóa đơn, không thể xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else if (ex.getMessage().contains("DATABASE_DOAN_NPNT.FK_HOADON HOPDONG")) {
                JOptionPane.showMessageDialog(null, "Lỗi: Hợp đồng này đã thuộc một hóa đơn, không thể xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi: Hợp đồng này đã thuộc một hóa đơn, không thể xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            return 0;
        }
    }

public static HopDongModel getHDtheoMaHopDong(int maHopDong) throws SQLException {
        String sql = "SELECT * FROM HOPDONG WHERE MAHOPDONG = ?";

        HopDongModel hopDong = null;

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maHopDong);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int MaKH = rs.getInt("MAKH");
                    LocalDateTime NgayLapHopDong = rs.getTimestamp("NGAYLAPHOPDONG").toLocalDateTime();
                    LocalDateTime TGNhanPhong = rs.getTimestamp("TGNHANPHONG").toLocalDateTime();
                    LocalDateTime TGTraPhong = rs.getTimestamp("TGTRAPHONG").toLocalDateTime();
                    String TinhTrangHD = rs.getString("TINHTRANGHD");
                    double TriGiaHopDong = rs.getDouble("TRIGIAHD");
                    String HinhThucThue = rs.getString("HINHTHUCTHUE");

                    hopDong = new HopDongModel(
                            MaKH,
                            NgayLapHopDong,
                            TGNhanPhong,
                            TGTraPhong,
                            TinhTrangHD,
                            TriGiaHopDong,
                            HinhThucThue
                    );
                }
            }
        }
        return hopDong;
    }

    public static boolean CapNhatHopDong(int maHopDong, HopDongModel hopDong) {
        String sql = "UPDATE HOPDONG SET TINHTRANGHD = ? WHERE MAHOPDONG = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // FIX: index param
            ps.setString(1, hopDong.getTinhTrangHD());
            ps.setInt(2, maHopDong);

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Thêm chi tiết đặt phòng với số khách
    public static int ThemCTDP(Connection con, int maHopDong, int maPhong, int soKhach) throws SQLException {
        String sql = "INSERT INTO CHITIETDATPHONG (SOKHACH, MAHOPDONG, MAPHONG) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, soKhach);
            ps.setInt(2, maHopDong);
            ps.setInt(3, maPhong);
            return ps.executeUpdate();
        }
    }

    public int hungia_ThemHopDongVaCTDP(int makh,
                                           String ngayNhan,
                                           String ngayTra,
                                           int soNL,
                                           int soTE,
                                           long tien,
                                           List<Integer> soPhongDatList) throws SQLException {
           Connection con = null;

           try {
               con = JDBCUtil.getConnection();
               con.setAutoCommit(false);

               // Bảng HOPDONG: MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong,
               // TinhTrangHD, LoaiKH, TriGiaHD, HinhThucThue
               // -> Không có cột số người, chỉ lưu tiền + hình thức thuê
               String sqlHopDong =
                       "INSERT INTO HOPDONG (MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, TinhTrangHD, TriGiaHD, HinhThucThue) " +
                       "VALUES (HopDong_Seq.NEXTVAL, ?, SYSTIMESTAMP, " +
                       "TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS'), " +
                       "TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS'), " +
                       "'Chưa xác nhận', ?, 'Ngày')";

               try (PreparedStatement psHopDong = con.prepareStatement(sqlHopDong, new String[]{"MAHOPDONG"})) {
                   psHopDong.setInt(1, makh);
                   psHopDong.setString(2, ngayNhan);
                   psHopDong.setString(3, ngayTra);
                   psHopDong.setLong(4, tien);

                   int row = psHopDong.executeUpdate();
                   if (row <= 0) {
                       con.rollback();
                       JOptionPane.showMessageDialog(null, "Không thể chèn hợp đồng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                       return 0;
                   }

                   // Lấy MaHopDong vừa tạo
                   int maHopDong = 0;
                   try (ResultSet rs = psHopDong.getGeneratedKeys()) {
                       if (rs.next()) {
                           maHopDong = rs.getInt(1);
                       }
                   }

                   if (maHopDong == 0) {
                       con.rollback();
                       JOptionPane.showMessageDialog(null, "Không thể lấy mã hợp đồng mới.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                       return 0;
                   }

                   int soKhach = soNL + soTE;

                   // Thêm chi tiết đặt phòng
                   for (int maPhong : soPhongDatList) {
                       int row1 = ThemCTDP(con, maHopDong, maPhong, soKhach);
                       if (row1 <= 0) {
                           con.rollback();
                           JOptionPane.showMessageDialog(
                                   null,
                                   "Không thể thêm phòng " + maPhong + " vào hợp đồng " + maHopDong,
                                   "Lỗi",
                                   JOptionPane.ERROR_MESSAGE
                           );
                           return 0;
                       }
                       System.out.println("Thêm thành công phòng " + maPhong + " vào hợp đồng " + maHopDong);
                   }

                   con.commit();
                   return 1;
               }

           } catch (SQLException ex) {
               if (con != null) {
                   try {
                       con.rollback();
                   } catch (SQLException e) {
                       e.printStackTrace();
                   }
               }

               JOptionPane.showMessageDialog(null, "Lỗi SQL: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
               ex.printStackTrace();
               return 0;
           } finally {
               if (con != null) {
                   try {
                       con.close();
                   } catch (SQLException e) {
                       e.printStackTrace();
                   }
               }
           }
    }

    public static int getMaHopDongMoiNhat() {
           // Khai báo biến MaHopDong với giá trị mặc định
           int maHopDong = 0;

           // Sử dụng try-with-resources để tự động quản lý tài nguyên
           try (
                   Connection con = JDBCUtil.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT MAX(MAHOPDONG) FROM HOPDONG"); ResultSet rs = ps.executeQuery()) {
               // Kiểm tra kết quả trả về từ truy vấn
               if (rs.next()) {
                   maHopDong = rs.getInt(1);
               }
           } catch (SQLException ex) {
               ex.printStackTrace();
           }

           // Trả về kết quả
           return maHopDong;
    }

    // Lấy giá phòng của (một hoặc nhiều) nằm trong hợp đồng đó - thuê theo ngày
    public static Double tongGiaPhongNgay(int maHD, long soNgayThue) {
        double tong = 0.0;

        // SQL lay cac tham so can dung
        String sqlThamSo = "SELECT TenThamSo, GiaTri FROM THAMSO " +
                           "WHERE TenThamSo IN ('SoLuongKhachToiDa', 'HeSoKhachNuocNgoai', 'PhuThuKhachThu3')";

        // SQL lay danh sach phong trong hop dong
        // FIX: gia khong con nam o PHONG.Gia ma nam o LOAIPHONG.Gia
        String sqlChiTiet =
            "SELECT LP.Gia AS Gia, CTDP.SoKhach, HD.LoaiKH " +
            "FROM PHONG P " +
            "JOIN LOAIPHONG LP ON P.MaLoai = LP.MaLoai " +
            "JOIN CHITIETDATPHONG CTDP ON P.MaPhong = CTDP.MaPhong " +
            "JOIN HOPDONG HD ON HD.MaHopDong = CTDP.MaHopDong " +
            "WHERE HD.MaHopDong = ?";

        try (Connection con = JDBCUtil.getConnection()) {

            // ================= LAY THAM SO =================
            int soKhachToiDa = 3;             // default
            double heSoKhachNuocNgoai = 1.0;  // default: khong tang
            double phuThuKhachThu3 = 0.0;     // default: khong phu thu

            try (PreparedStatement psTS = con.prepareStatement(sqlThamSo);
                 ResultSet rsTS = psTS.executeQuery()) {

                while (rsTS.next()) {
                    String ten = rsTS.getString("TenThamSo");
                    double giaTri = rsTS.getDouble("GiaTri");

                    switch (ten) {
                        case "SoLuongKhachToiDa":
                            soKhachToiDa = (int) giaTri;
                            break;
                        case "HeSoKhachNuocNgoai":
                            heSoKhachNuocNgoai = giaTri;
                            break;
                        case "PhuThuKhachThu3":
                            phuThuKhachThu3 = giaTri;
                            break;
                        default:
                            break;
                    }
                }
                System.out.println(soNgayThue);

                System.out.println("Tham so: soKhachToiDa=" + soKhachToiDa
                        + ", heSoKhachNuocNgoai=" + heSoKhachNuocNgoai
                        + ", phuThuKhachThu3=" + phuThuKhachThu3);
            }

            // ============= LAY CHI TIET PHONG + TINH TIEN=============
            try (PreparedStatement ps = con.prepareStatement(sqlChiTiet)) {
                ps.setInt(1, maHD);
                System.out.println("Executing query with maHD: " + maHD);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        double gia = rs.getDouble("Gia");  // Gia lay tu LOAIPHONG
                        int soKhach = rs.getInt("SoKhach");
                        String loaiKH = rs.getString("LoaiKH");

                        // Gia co ban cho 1 ngay / 1 phong
                        double tienMotNgay = gia;

                        // --- Phu thu neu vuot SoLuongKhachToiDa ---
                        if (soKhach >= soKhachToiDa) {
                            int soKhachVuot = 1;
                            double phuThuThemKhach = gia * phuThuKhachThu3 * soKhachVuot;
                            tienMotNgay += phuThuThemKhach;
                            System.out.println("Phu thu them khach: " + phuThuThemKhach);
                        }



                        double tienPhongTheoSoNgay = tienMotNgay * soNgayThue;
                        if (loaiKH != null &&
                            (loaiKH.equalsIgnoreCase("Nước ngoài") ||
                             loaiKH.equalsIgnoreCase("Nuoc ngoai"))) {

                            tienPhongTheoSoNgay = tienPhongTheoSoNgay*1.5;
                            System.out.println("Phu thu nuoc ngoai: ");
                        }
                        tong += tienPhongTheoSoNgay;

                        System.out.println("Gia co ban: " + gia);
                        System.out.println("So khach: " + soKhach);
                        System.out.println("LoaiKH: " + loaiKH);
                        System.out.println("Tien mot ngay (da phu thu): " + tienMotNgay);
                        System.out.println("So ngay thue: " + soNgayThue);
                        System.out.println("Tien phong cho phong nay: " + tienPhongTheoSoNgay);
                        System.out.println("Tong tam thoi: " + tong);
                    }
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tong;
    }







    // Cập nhật tình trạng hợp đồng sang đã thanh toán
    public static void capnhatTinhTrang(int mahd) {
        String sql = "UPDATE HOPDONG SET TinhTrangHD = 'Đã thanh toán' WHERE MaHopDong = ?";

        try (Connection con = JDBCUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, mahd);
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

   // Tinh tong gia phong thue theo gio (co phu thu)
   public static Double tongGiaPhongGio(int maHD, long soGioThue) {
       double tong = 0.0;

       // SQL lay cac tham so
       String sqlThamSo = "SELECT TenThamSo, GiaTri FROM THAMSO " +
                          "WHERE TenThamSo IN ('SoLuongKhachToiDa', 'HeSoKhachNuocNgoai', 'PhuThuKhachThu3')";

       // SQL lay danh sach phong trong hop dong
       // FIX: gia tu LOAIPHONG.Gia
       String sqlChiTiet =
           "SELECT LP.Gia AS Gia, CTDP.SoKhach, HD.LoaiKH " +
           "FROM PHONG P " +
           "JOIN LOAIPHONG LP ON P.MaLoai = LP.MaLoai " +
           "JOIN CHITIETDATPHONG CTDP ON P.MaPhong = CTDP.MaPhong " +
           "JOIN HOPDONG HD ON HD.MaHopDong = CTDP.MaHopDong " +
           "WHERE HD.MaHopDong = ?";

       try (Connection con = JDBCUtil.getConnection()) {

           // ====== LAY THAM SO ======
           int soKhachToiDa = 3;             // default
           double heSoKhachNuocNgoai = 1.0;  // default: khong tang
           double phuThuKhachThu3 = 0.0;     // default

           try (PreparedStatement psTS = con.prepareStatement(sqlThamSo);
                ResultSet rsTS = psTS.executeQuery()) {

               while (rsTS.next()) {
                   String ten = rsTS.getString("TenThamSo");
                   double giaTri = rsTS.getDouble("GiaTri");

                   switch (ten) {
                       case "SoLuongKhachToiDa":
                           soKhachToiDa = (int) giaTri;
                           break;
                       case "HeSoKhachNuocNgoai":
                           heSoKhachNuocNgoai = giaTri;
                           break;
                       case "PhuThuKhachThu3":
                           phuThuKhachThu3 = giaTri;
                           break;
                       default:
                           break;
                   }
               }

               System.out.println("Tham so: soKhachToiDa=" + soKhachToiDa
                       + ", heSoKhachNuocNgoai=" + heSoKhachNuocNgoai
                       + ", phuThuKhachThu3=" + phuThuKhachThu3);
           }

           // ====== LAY CHI TIET PHONG + TINH TIEN THEO GIO ======
           try (PreparedStatement ps = con.prepareStatement(sqlChiTiet)) {
               ps.setInt(1, maHD);

               try (ResultSet rs = ps.executeQuery()) {
                   while (rs.next()) {
                       double giaNgay = rs.getDouble("Gia");   // Gia ngay tu LOAIPHONG
                       int soKhach = rs.getInt("SoKhach");
                       String loaiKH = rs.getString("LoaiKH");

                       // Gia co ban 1 gio
                       double giaMotGio = giaNgay / 22.0 * 1.5;
                       double tienMotGio = giaMotGio;

                       // --- Phu thu neu vuot so khach toi da ---
                       if (soKhach >= soKhachToiDa) {
                           int soKhachVuot = 1;
                           double phuThuThemKhach = giaMotGio * phuThuKhachThu3 * soKhachVuot;
                           tienMotGio += phuThuThemKhach;
                           System.out.println("Phu thu them khach (gio): " + phuThuThemKhach);
                       }

                       double tienPhongTheoGio = tienMotGio * soGioThue;
                        if (loaiKH != null &&
                            (loaiKH.equalsIgnoreCase("Nước ngoài") ||
                             loaiKH.equalsIgnoreCase("Nuoc ngoai"))) {

                            tienPhongTheoGio = tienPhongTheoGio*1.5;
                            System.out.println("Phu thu nuoc ngoai: ");
                        }
                       tong += tienPhongTheoGio;

                       System.out.println("Gia ngay goc: " + giaNgay);
                       System.out.println("Gia mot gio (chua phu thu): " + giaMotGio);
                       System.out.println("So khach: " + soKhach);
                       System.out.println("LoaiKH: " + loaiKH);
                       System.out.println("Tien mot gio (da phu thu): " + tienMotGio);
                       System.out.println("So gio thue: " + soGioThue);
                       System.out.println("Tien phong cho phong nay: " + tienPhongTheoGio);
                       System.out.println("Tong tam thoi: " + tong);
                   }
               }
           }

       } catch (SQLException ex) {
           ex.printStackTrace();
       }

       return tong;
   }



    // Thêm hợp đồng thuê giờ (chưa gắn chi tiết phòng ở đây)
    public int ttunThemHopDong(int makh,
                               String checkInDateTime,
                               String checkOutDateTime,
                               int soNL, int soTE) throws SQLException {

        Connection con = null;

        try {
            con = JDBCUtil.getConnection();

            String sql = "INSERT INTO HOPDONG (MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, TinhTrangHD, TriGiaHD, HinhThucThue) "
                    + "VALUES (HopDong_Seq.NEXTVAL, ?, SYSTIMESTAMP, "
                    + "TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS'), "
                    + "TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS'), "
                    + "'Đã xác nhận', 0, 'Giờ')";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, makh);
                ps.setString(2, checkInDateTime);
                ps.setString(3, checkOutDateTime);

                // soNL, soTE hiện chưa có cột để lưu trong HOPDONG
                return ps.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Lỗi SQL: " + ex.getMessage());
            return 0;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static long tinhThoiGian(String checkInDateTime, String checkOutDateTime) {
        // Định dạng cho ngày giờ
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Chuyển đổi chuỗi thành LocalDateTime
        LocalDateTime checkIn = LocalDateTime.parse(checkInDateTime, formatter);
        LocalDateTime checkOut = LocalDateTime.parse(checkOutDateTime, formatter);

        // Tính khoảng thời gian giữa hai thời điểm
        long khoangThoiGianGiuaHaiThoiDiem = ChronoUnit.MINUTES.between(checkIn, checkOut);

        return khoangThoiGianGiuaHaiThoiDiem;
    }

    public static HopDongModel getHopDongTheoMaHP(int maHopDong) {
        Connection con = null;
        try {
            String sql = "SELECT * FROM HOPDONG WHERE MAHOPDONG = ?";
            HopDongModel hopdong_temp = new HopDongModel();
            con = JDBCUtil.getConnection();

            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, maHopDong);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                hopdong_temp.setMaHopDong(rs.getInt("MAHOPDONG"));
                hopdong_temp.setMaKH(rs.getInt("MAKH"));
                hopdong_temp.setNgayLapHopDong(rs.getTimestamp("NGAYLAPHOPDONG").toLocalDateTime());
                hopdong_temp.setTGNhanPhong(rs.getTimestamp("TGNHANPHONG").toLocalDateTime());
                hopdong_temp.setTGTraPhong(rs.getTimestamp("TGTRAPHONG").toLocalDateTime());
                hopdong_temp.setTinhTrangHD(rs.getString("TINHTRANGHD"));
                hopdong_temp.setTriGiaHopDong(rs.getDouble("TRIGIAHD"));
                hopdong_temp.setHinhThucThue("HINHTHUCTHUE");
            }
            con.close();
            return hopdong_temp;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static KhachHangModel getKHtheoMaHopDong(int maHopDong) throws SQLException {
        String sql = "SELECT k.* FROM HOPDONG h JOIN KHACHHANG k ON h.MAKH = k.MAKH WHERE MAHOPDONG = ?";
        // Tạo đối tượng connection
        Connection conn = null;
        KhachHangModel kh = null;

        try {
            conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, maHopDong);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int maKH = rs.getInt(1);
                String TenKH = rs.getString(2);
                String CCCD = rs.getString(3);
                LocalDate NgaySinh = rs.getDate(4).toLocalDate();
                String GioiTinh = rs.getString(5);
                String DiaChi = rs.getString(6);
                String SDT = rs.getString(7);
                int SoHopDong = rs.getInt(8);

                // gọi constructor
                kh = new KhachHangModel(maKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong);
            }

            rs.close();
            ps.close();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return kh;
    }

    public static ArrayList<HopDongModel> TimHopDongTheoSoPhong(int sophong) {
        ArrayList<HopDongModel> DS_HopDong = new ArrayList<>();

        String sql = "SELECT h.* FROM HOPDONG h "
                + "JOIN CHITIETDATPHONG c ON h.MAHOPDONG = c.MAHOPDONG "
                + "WHERE c.MAPHONG = ? "
                + "ORDER BY h.TGTRAPHONG DESC ";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, sophong);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int MaHopDong = rs.getInt("MAHOPDONG");
                    int MaKH = rs.getInt("MAKH");
                    LocalDateTime NgayLapHopDong = rs.getTimestamp("NGAYLAPHOPDONG").toLocalDateTime();
                    LocalDateTime TGNhanPhong = rs.getTimestamp("TGNHANPHONG").toLocalDateTime();
                    LocalDateTime TGTraPhong = rs.getTimestamp("TGTRAPHONG").toLocalDateTime();
                    String TinhTrangHD = rs.getString("TINHTRANGHD");
                    double TriGiaHopDong = rs.getDouble("TRIGIAHD");
                    String HinhThucThue = rs.getString("HINHTHUCTHUE");

                    HopDongModel hopDong = new HopDongModel(
                            MaHopDong, MaKH,
                            NgayLapHopDong, TGNhanPhong, TGTraPhong,
                            TinhTrangHD, TriGiaHopDong, HinhThucThue
                    );
                    DS_HopDong.add(hopDong);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return DS_HopDong;
    }

    public static ArrayList<HopDongModel> TimHopDongTheoCCCD(String CCCD) {
        ArrayList<HopDongModel> DS_HopDong = new ArrayList<>();

        String sql = "SELECT h.* FROM HOPDONG h "
                + "JOIN KHACHHANG k ON h.MAKH = k.MAKH "
                + "WHERE k.CCCD = ? "
                + "ORDER BY h.TGNHANPHONG DESC ";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, CCCD);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int MaHopDong = rs.getInt("MAHOPDONG");
                    int MaKH = rs.getInt("MAKH");
                    LocalDateTime NgayLapHopDong = rs.getTimestamp("NGAYLAPHOPDONG").toLocalDateTime();
                    LocalDateTime TGNhanPhong = rs.getTimestamp("TGNHANPHONG").toLocalDateTime();
                    LocalDateTime TGTraPhong = rs.getTimestamp("TGTRAPHONG").toLocalDateTime();
                    String TinhTrangHD = rs.getString("TINHTRANGHD");
                    double TriGiaHopDong = rs.getDouble("TRIGIAHD");
                    String HinhThucThue = rs.getString("HINHTHUCTHUE");

                    HopDongModel hopDong = new HopDongModel(
                            MaHopDong, MaKH,
                            NgayLapHopDong, TGNhanPhong, TGTraPhong,
                            TinhTrangHD, TriGiaHopDong, HinhThucThue
                    );
                    DS_HopDong.add(hopDong);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return DS_HopDong;
    }

    public static ArrayList<Vector> getSoKHTrongThang() {
        ArrayList<Vector> DS_SoKH = new ArrayList<>();

        // Bảng HOPDONG không có cột SONGUOILON -> phải lấy từ CHITIETDATPHONG.SoKhach
        String sql = "SELECT SUM(c.SOKHACH) AS tong_so_khach, "
                + "       EXTRACT(MONTH FROM h.TGNHANPHONG) AS thang, "
                + "       EXTRACT(YEAR FROM h.TGNHANPHONG) AS nam "
                + "FROM HOPDONG h "
                + "JOIN CHITIETDATPHONG c ON h.MAHOPDONG = c.MAHOPDONG "
                + "GROUP BY EXTRACT(MONTH FROM h.TGNHANPHONG), EXTRACT(YEAR FROM h.TGNHANPHONG) "
                + "ORDER BY EXTRACT(YEAR FROM h.TGNHANPHONG), EXTRACT(MONTH FROM h.TGNHANPHONG)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("tong_so_khach"));
                row.add(rs.getInt("thang"));
                row.add(rs.getInt("nam"));
                DS_SoKH.add(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return DS_SoKH;
    }

    public static ArrayList<Vector> getHTThuePhong() {
        ArrayList<Vector> DS_HTThue = new ArrayList<>();

        String sql = "SELECT HINHTHUCTHUE, COUNT(H.MAHOPDONG) as so_Hop_dong, "
                + "EXTRACT(MONTH FROM H.NGAYLAPHOPDONG) AS thang, "
                + "EXTRACT(YEAR FROM H.NGAYLAPHOPDONG) AS nam "
                + "FROM HOPDONG H "
                + "GROUP BY H.HINHTHUCTHUE, EXTRACT(MONTH FROM H.NGAYLAPHOPDONG), EXTRACT(YEAR FROM H.NGAYLAPHOPDONG) "
                + "ORDER BY EXTRACT(YEAR FROM H.NGAYLAPHOPDONG), EXTRACT(MONTH FROM H.NGAYLAPHOPDONG)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getString("HINHTHUCTHUE"));
                row.add(rs.getInt("so_Hop_dong"));
                row.add(rs.getInt("thang"));
                row.add(rs.getInt("nam"));
                DS_HTThue.add(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return DS_HTThue;
    }
}
