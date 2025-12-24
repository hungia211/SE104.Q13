package View;

import Connection.JDBCUtil;
import DAO.ChiTietDatPhongDAO;
import DAO.HopDongDAO;
import DAO.KhachHangDAO;
import DAO.PhongDAO;
import DAO.TrangChuDAO;
import Model.KhachHangModel;
import Model.LoaiPhongModel;
import Model.NhanVienModel;
import Model.PhongModel;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.CardLayout;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JComboBox;
import javax.swing.DefaultCellEditor;
import java.sql.Connection;
import java.sql.SQLException;




/**
 *
 * @author HP
 */
public class PhongFrame extends javax.swing.JFrame {

    CardLayout cardlayout;
    private static NhanVienModel currentUser;
    private DefaultTableModel defaultTableModel = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JScrollPane scrollPane;
    private int maPhong;
    private ArrayList<PhongModel> DS_PHONG;
    private int current = 0;
    int SoPhongDat;
    String checkInDateTime;
    String checkOutDateTime;
    int maKHCu;
    // Mảng để lưu số phòng đã đặt
    ArrayList<Integer> soPhongDatList = new ArrayList<>();

    public PhongFrame() {
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        initComponents();
        this.setLocationRelativeTo(null);

        addSVG();
        khoiTaoBang(); // Gọi phương thức khởi tạo bảng
        inDanhSach(); // Gọi phương thức in danh sách ban đầu nếu cần thiết

        // Tạo các luồng sự kiện chuyển Panel
        cardlayout = (CardLayout) (mainPanel.getLayout());
        mainPanel.add(TraCuuPhongPanel, "datPhong");
        cardlayout.show(mainPanel, "datPhong");
        mainPanel.add(NhapThongTinKHPanel, "nhapThongTin");
        mainPanel.add(LapHopDongPanel, "lapHopDong");
        mainPanel.add(LoaiPhongjPanel, "loaiPhong");
        
    }

    public void addSVG() {
        svgLogo.setSVGImage("Image/logo.svg", 110, 110);
        svgPhong.setSVGImage("Image/phong.svg", 30, 30);
        svgKhach.setSVGImage("Image/khachhang.svg", 30, 30);
        svgNV.setSVGImage("Image/nhanvien.svg", 30, 30);
        svgHopDong.setSVGImage("Image/hopdong.svg", 30, 30);
        svgKM.setSVGImage("Image/khuyenmai.svg", 30, 30);
        svgTB.setSVGImage("Image/trangbi.svg", 30, 30);
        svgHoaDon.setSVGImage("Image/hoadon.svg", 30, 30);
        svgCC.setSVGImage("Image/chamcong.svg", 30, 30);
        svgL.setSVGImage("Image/luong.svg", 30, 30);

        svgPhong.setYColor();
        svgKhach.setColor();
        svgNV.setColor();
        svgHopDong.setColor();
        svgKM.setColor();
        svgTB.setColor();
        svgHoaDon.setColor();
        svgCC.setColor();
        svgL.setColor();
    }

    public void khoiTaoBang() {
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Mã Phòng");
        defaultTableModel.addColumn("Tên Loại phòng");
        defaultTableModel.addColumn("Tinh Trạng");
        defaultTableModel.addColumn("Giá Phòng");

        danhSachPHONGTable.setModel(defaultTableModel);
        danhSachPHONGTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    public void inDanhSach() {
        // Tạo đối tượng danh sách phòng
        DS_PHONG = PhongDAO.hungia_getDSPhong();

        // Xóa tất cả các hàng hiện có trong bảng
        defaultTableModel.setRowCount(0);

        // Thêm dữ liệu vào bảng
        for (PhongModel ph : DS_PHONG) {
            defaultTableModel.addRow(new Object[]{
                ph.getMaPhong(), ph.getLoaiPhong().getTenloai(), ph.getTinhTrang(), ph.getLoaiPhong().getGia()
            });
        }

        for (int i = 0; i < defaultTableModel.getColumnCount(); i++) {
            TableColumn column = danhSachPHONGTable.getColumnModel().getColumn(i);
            int maxWidth = 0;

            // Tính toán chiều rộng dựa trên nội dung lớn nhất trong cột
            for (int row = 0; row < danhSachPHONGTable.getRowCount(); row++) {
                TableCellRenderer cellRenderer = danhSachPHONGTable.getCellRenderer(row, i);
                Component c = danhSachPHONGTable.prepareRenderer(cellRenderer, row, i);
                maxWidth = Math.max(c.getPreferredSize().width, maxWidth);
            }

            // Thiết lập chiều rộng cột + padding
            column.setPreferredWidth(maxWidth + 30);
        }
    }

    public String doiNgay(Date d) {
        // Giả sử bạn đã có một đối tượng java.sql.Date
        java.sql.Date sqlDate = new java.sql.Date(d.getTime());

        // Định dạng ngày thành chuỗi
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateStr = dateFormat.format(sqlDate);

        return dateStr;
    }

    public void TraCuuPhongCoTime() {
        // Lấy lựa chọn từ UI
        String loaiPhong = (String) jComboBox1.getSelectedItem();   // A, B, C, Tất cả
        String tangStr = (String) jComboBox2.getSelectedItem();     // Tầng 1, Tầng 2, Tất cả

        // Lấy giờ từ JTextField và ngày từ JCalendarComboBox
        String checkInStr = checkInTime.getText();
        String checkOutStr = checkOutTime.getText();

        String checkInDateTimeStr = doiNgay(checkInDate.getDate()) + " " + checkInStr;
        String checkOutDateTimeStr = doiNgay(checkOutDate.getDate()) + " " + checkOutStr;

        System.out.println(checkInDateTimeStr);
        System.out.println(checkOutDateTimeStr);

        ArrayList<PhongModel> DS_Phong;

        // Xóa tất cả các hàng trong bảng
        defaultTableModel.setRowCount(0);

        // -------------------------------
        // 1) Người dùng chọn TẤT CẢ loại + TẤT CẢ tầng
        // -------------------------------
        if (loaiPhong.equals("Tất cả") && tangStr.equals("Tất cả")) {

            DS_Phong = PhongDAO.hungia_getDStheoNgay(checkInDateTimeStr, checkOutDateTimeStr);

            // -------------------------------
            // 2) Người dùng chọn TẦNG nhưng loại phòng = Tất cả
            // -------------------------------
        } else if (loaiPhong.equals("Tất cả")) {

            int tang = 0;
            if (!tangStr.equals("Tất cả")) {
                tang = Integer.parseInt(tangStr.split(" ")[1]);   // "Tầng 2" → 2
            }

            DS_Phong = PhongDAO.hungia_getDStheoTang(tang, checkInDateTimeStr, checkOutDateTimeStr);

            // -------------------------------
            // 3) Người dùng chọn LOẠI PHÒNG nhưng tầng = Tất cả
            // -------------------------------
        } else if (tangStr.equals("Tất cả")) {

            DS_Phong = PhongDAO.hungia_getDStheoLoaiPhong(loaiPhong, checkInDateTimeStr, checkOutDateTimeStr);

            // -------------------------------
            // 4) Người dùng chọn CẢ LOẠI và TẦNG
            // -------------------------------
        } else {

            int tang = Integer.parseInt(tangStr.split(" ")[1]);

            DS_Phong = PhongDAO.hungia_getDStheotracuu(loaiPhong, tang, checkInDateTimeStr, checkOutDateTimeStr);
        }

        // Kiểm tra chuỗi giờ rỗng
        if (checkInStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giờ check-in", "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (checkOutStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giờ check-out", "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Thêm kết quả tìm kiếm vào bảng
        for (PhongModel ph : DS_PHONG) {
            defaultTableModel.addRow(new Object[]{
                ph.getMaPhong(), ph.getLoaiPhong().getTenloai(), ph.getTinhTrang(), ph.getLoaiPhong().getGia()
            });
        }
        // Hiển thị thông báo nếu không tìm thấy kết quả
        if (DS_PHONG.size() <= 0) {
            JOptionPane.showMessageDialog(rootPane, "Không còn phòng trống. Vui lòng chọn thời gian khác!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void TraCuuPhongKhongTime() {

        // Lấy lựa chọn từ UI
        String loaiPhong = (String) jComboBox1.getSelectedItem();   // A, B, C, Tất cả
        String tangStr = (String) jComboBox2.getSelectedItem();     // Tầng 1, Tầng 2, Tất cả

        // Lấy ngày checkin / checkout
        String ngayNhan = doiNgay(checkInDate.getDate()) + " 14:00:00";
        String ngayTra = doiNgay(checkOutDate.getDate()) + " 12:00:00";

        ArrayList<PhongModel> DS_Phong;
        
        if (ngayTra.compareTo(ngayNhan) <= 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ngày trả phòng phải SAU ngày nhận phòng",
                    "Lỗi thời gian",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }


        // Xóa bảng cũ
        defaultTableModel.setRowCount(0);

        // -------------------------------
        // 1) Người dùng chọn TẤT CẢ loại + TẤT CẢ tầng
        // -------------------------------
        if (loaiPhong.equals("Tất cả") && tangStr.equals("Tất cả")) {

            DS_Phong = PhongDAO.hungia_getDStheoNgay(ngayNhan, ngayTra);

            // -------------------------------
            // 2) Người dùng chọn TẦNG nhưng loại phòng = Tất cả
            // -------------------------------
        } else if (loaiPhong.equals("Tất cả")) {

            int tang = 0;
            if (!tangStr.equals("Tất cả")) {
                tang = Integer.parseInt(tangStr.split(" ")[1]);   // "Tầng 2" → 2
            }

            DS_Phong = PhongDAO.hungia_getDStheoTang(tang, ngayNhan, ngayTra);

            // -------------------------------
            // 3) Người dùng chọn LOẠI PHÒNG nhưng tầng = Tất cả
            // -------------------------------
        } else if (tangStr.equals("Tất cả")) {

            DS_Phong = PhongDAO.hungia_getDStheoLoaiPhong(loaiPhong, ngayNhan, ngayTra);

            // -------------------------------
            // 4) Người dùng chọn CẢ LOẠI và TẦNG
            // -------------------------------
        } else {

            int tang = Integer.parseInt(tangStr.split(" ")[1]);

            DS_Phong = PhongDAO.hungia_getDStheotracuu(loaiPhong, tang, ngayNhan, ngayTra);
        }

        // -------------------------------
        // HIỂN THỊ LÊN BẢNG
        // -------------------------------
        for (PhongModel ph : DS_Phong) {
            defaultTableModel.addRow(new Object[]{
                ph.getMaPhong(),
                ph.getLoaiPhong().getTenloai(),
                ph.getTinhTrang(),
                ph.getLoaiPhong().getGia()
            });
        }

        // -------------------------------
        // Thông báo nếu không có dữ liệu
        // -------------------------------
        if (DS_Phong.isEmpty()) {
            JOptionPane.showMessageDialog(
                    rootPane,
                    "Vào thời gian này không có phòng trống. Vui lòng thử lại!",
                    "Thông báo",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void capNhatThongTin() {
        // Lấy thông tin nhập liệu mới
        String hoTen = this.them_hoTenjTextField.getText();
        java.sql.Date sqldate = new java.sql.Date(this.them_ngaySinhjComboBox.getDate().getTime());
        String gioiTinh = (String) this.them_gioiTinhjComboBox.getSelectedItem();
        String diaChi = this.them_diaChijTextField.getText();
        String sdt = this.them_SDTjTextField.getText();
        try {
            // Lấy thông tin cũ của khách hàng từ cơ sở dữ liệu
            KhachHangModel khCu = KhachHangDAO.getKHtheoMaKHK(maKHCu);

            // Kiểm tra và cập nhật thông tin mới
            if (!hoTen.isEmpty()) {
                khCu.setTenKH(hoTen);
            }
            if (sqldate != null) {
                khCu.setNgaySinh(sqldate.toLocalDate());
            }
            if (!gioiTinh.isEmpty()) {
                khCu.setGioiTinh(gioiTinh);
            }
            if (!diaChi.isEmpty()) {
                khCu.setDiaChi(diaChi);
            }
            if (!sdt.isEmpty()) {
                khCu.setSDT(sdt);
            }
            KhachHangDAO.CapNhatKH(maKHCu, khCu);

            // Thực hiện cập nhật trong cơ sở dữ liệu
        } catch (SQLException ex) {
            ex.printStackTrace(); // In ra thông tin lỗi chi tiết
            JOptionPane.showMessageDialog(rootPane, "Lỗi khi thực hiện truy vấn SQL!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Lỗi: Tham chiếu null!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Lỗi không xác định!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String themKHMoi() {
        String hoTen = this.them_hoTenjTextField.getText();
        String cccd = this.them_CCCDjTextField.getText();
        java.sql.Date sqldate = new java.sql.Date(this.them_ngaySinhjComboBox.getDate().getTime());
        String gioiTinh = (String) this.them_gioiTinhjComboBox.getSelectedItem();
        String diaChi = this.them_diaChijTextField.getText();
        String sdt = this.them_SDTjTextField.getText();
        KhachHangModel KH = new KhachHangModel(hoTen, cccd, sqldate.toLocalDate(), gioiTinh, diaChi, sdt, 0);
        KhachHangDAO khDAO = new KhachHangDAO();
        try {
            khDAO.ThemKH(KH);
            return KH.getCCCD();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    private String formatThoiGianThue(long tongPhut) {
        // Case đặc biệt: 1 ngày khách sạn (14h → 12h hôm sau)
        if (tongPhut == 22 * 60) { // 1320 phút
            return "1 ngày";
        }

        long soNgay = tongPhut / 1440;          // 1 ngày = 24h
        long soPhutConLai = tongPhut % 1440;
        long soGio = soPhutConLai / 60;
        long soPhut = soPhutConLai % 60;

        if (soNgay == 0) {
            // Dưới 1 ngày → chỉ hiển thị giờ / phút
            if (soPhut == 0) {
                return soGio + " giờ";
            }
            return soGio + " giờ " + soPhut + " phút";
        } else {
            // Từ ngày thứ 2 trở đi → ngày chuẩn 24h
            if (soGio == 0) {
                return soNgay + " ngày";
            }
            return soNgay + " ngày " + soGio + " giờ";
        }
    }

    
    private void hienPhongDaDat() throws SQLException {
        DefaultTableModel model = (DefaultTableModel) sokhachjTable.getModel();
        model.setRowCount(0);

        for (Integer maPhong : soPhongDatList) {
            PhongModel p = PhongDAO.getPhongTheoMa(maPhong);

            if (p != null) {
                model.addRow(new Object[]{
                    p.getMaPhong(),
                    p.getLoaiPhong().getTenloai(),
                    p.getLoaiPhong().getGia(),
                    1 // mặc định 1 khách
                });
            }
        }
    }
    
    private void setupSoKhachTable() {
        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Mã phòng", "Loại phòng", "Giá phòng", "Số khách"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // chỉ cho sửa cột Số khách
            }
        };
        
        sokhachjTable.setModel(model);

        TableColumn soKhachCol = sokhachjTable.getColumnModel().getColumn(3);
        JComboBox<Integer> combo = new JComboBox<>(new Integer[]{1, 2, 3});
        soKhachCol.setCellEditor(new DefaultCellEditor(combo));
    }


    public void hienThongTinKH() throws SQLException {
        KhachHangModel kh = KhachHangDAO.getKHtheoMaKHK(maKHCu);
        this.hdong_hoTenjLabel.setText("Họ và tên: " + kh.getTenKH());
        this.hdong_CCCDjLable.setText("CCCD: " + kh.getCCCD());
        // Giả sử kh.getNgaySinh() trả về LocalDate
        LocalDate localDate = kh.getNgaySinh();

        // Chuyển đổi LocalDate sang Date
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.hdong_NgaySinhjLabel.setText("Ngày sinh: " + doiNgay(date));
        this.hdong_GioiTinhjLabel.setText("Giới tính: " + kh.getGioiTinh());
        this.hdong_DiaChijLabel.setText("Địa chỉ: " + kh.getDiaChi());
        this.hdong_SDTjLabel.setText("SĐT: " + kh.getSDT());
        this.hdong_ngayNhanjLabel.setText("Thời gian nhận phòng: " + checkInDateTime);
        this.hdong_ngayTrajLabel.setText("Thời gian trả phòng: " + checkOutDateTime);
        long thoiGian = HopDongDAO.tinhThoiGian(checkInDateTime, checkOutDateTime);
        this.hdong_ngaythuejLabel.setText("Thời gian thuê: " + formatThoiGianThue(thoiGian));
        long tienCoc = HopDongDAO.tinhTienCoc(soPhongDatList, thoiGian);

        if (tienCoc == 0) {
            hdong_cocjLabel1.setText("Tiền cọc: Không cần cọc");
        } else {
            hdong_cocjLabel1.setText(
                "Tiền cọc: " + String.format("%,d", tienCoc) + " VNĐ"
            );
        }
        setupSoKhachTable();
        try {
            hienPhongDaDat();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Lỗi hiển thị phòng đã đặt",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private void loadLoaiPhongTable() throws SQLException {
        DefaultTableModel model = (DefaultTableModel) loaiPhongTable.getModel();
        model.setRowCount(0);

        for (LoaiPhongModel lp : PhongDAO.getAllLoaiPhong()) {
            model.addRow(new Object[]{
                lp.getMaLoai(),
                lp.getTenloai(),
                lp.getGia()
            });
        }

        resetForm();
    }
    
    private void resetForm() {
        txtTenLoai.setText("");
        txtGia.setText("");
        loaiPhongTable.clearSelection();

        Them_LPjButton.setEnabled(true);
        Sua_LPjButton.setEnabled(false);
        Xoa_LPjButton.setEnabled(false);
    }




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        MenuPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        KhachHangLb = new javax.swing.JLabel();
        NhanVienLb = new javax.swing.JLabel();
        HopDongLb = new javax.swing.JLabel();
        KhuyenMaiLb = new javax.swing.JLabel();
        TrangBiLb = new javax.swing.JLabel();
        HoaDonLb = new javax.swing.JLabel();
        ChamCongLb = new javax.swing.JLabel();
        DangXuatLb = new javax.swing.JLabel();
        luongLb = new javax.swing.JLabel();
        phongLb = new javax.swing.JLabel();
        svgLogo = new Image.SVGImage();
        svgPhong = new Image.SVGImage();
        svgKhach = new Image.SVGImage();
        svgNV = new Image.SVGImage();
        svgHopDong = new Image.SVGImage();
        svgKM = new Image.SVGImage();
        svgTB = new Image.SVGImage();
        svgHoaDon = new Image.SVGImage();
        svgCC = new Image.SVGImage();
        svgL = new Image.SVGImage();
        mainPanel = new javax.swing.JPanel();
        NhapThongTinKHPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        them_CCCDjTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        traCuuCCCDjButton = new javax.swing.JButton();
        them_hoTenjTextField = new javax.swing.JTextField();
        them_gioiTinhjComboBox = new javax.swing.JComboBox<>();
        them_ngaySinhjComboBox = new de.wannawork.jcalendar.JCalendarComboBox();
        them_diaChijTextField = new javax.swing.JTextField();
        them_SDTjTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        KH_HụyButton = new javax.swing.JButton();
        KH_DatPhongjButton = new javax.swing.JButton();
        thongBaojLable = new javax.swing.JLabel();
        LapHopDongPanel = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        hdong_CCCDjLable = new javax.swing.JLabel();
        hdong_NgaySinhjLabel = new javax.swing.JLabel();
        hdong_hoTenjLabel = new javax.swing.JLabel();
        hdong_GioiTinhjLabel = new javax.swing.JLabel();
        hdong_DiaChijLabel = new javax.swing.JLabel();
        hdong_SDTjLabel = new javax.swing.JLabel();
        hdong_huyjButton = new javax.swing.JButton();
        hdong_QuayLaijButton = new javax.swing.JButton();
        hdong_XacNhanjButton = new javax.swing.JButton();
        hdong_ngayNhanjLabel = new javax.swing.JLabel();
        hdong_ngayTrajLabel = new javax.swing.JLabel();
        hdong_cocjLabel1 = new javax.swing.JLabel();
        hdong_ngaythuejLabel = new javax.swing.JLabel();
        hdong_sokhachjLabel2 = new javax.swing.JLabel();
        danhsachphong_jScrollPane = new javax.swing.JScrollPane();
        sokhachjTable = new javax.swing.JTable();
        hdong_loaiKHJlable = new javax.swing.JLabel();
        rNoidia = new javax.swing.JRadioButton();
        rNuocngoai = new javax.swing.JRadioButton();
        TraCuuPhongPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        danhSachPHONGTable = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox1 = new javax.swing.JComboBox<>();
        checkInDate = new de.wannawork.jcalendar.JCalendarComboBox();
        checkOutDate = new de.wannawork.jcalendar.JCalendarComboBox();
        checkInTime = new javax.swing.JTextField();
        checkOutTime = new javax.swing.JTextField();
        traCuujButton1 = new javax.swing.JButton();
        datPhongjButton2 = new javax.swing.JButton();
        datPhongjButton3 = new javax.swing.JButton();
        LoaiPhongjButton = new javax.swing.JButton();
        LoaiPhongjPanel = new javax.swing.JPanel();
        TieudejLabel = new javax.swing.JLabel();
        tenloaijLabel = new javax.swing.JLabel();
        giajLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        loaiPhongTable = new javax.swing.JTable();
        Sua_LPjButton = new javax.swing.JButton();
        Quaylai_jButton = new javax.swing.JButton();
        Them_LPjButton = new javax.swing.JButton();
        Xoa_LPjButton = new javax.swing.JButton();
        txtTenLoai = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        Huy_jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MenuPanel.setBackground(new java.awt.Color(24, 24, 68));
        MenuPanel.setPreferredSize(new java.awt.Dimension(200, 600));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(220, 220, 46));
        jLabel1.setText("BRIGHT STAR");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        KhachHangLb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        KhachHangLb.setForeground(new java.awt.Color(255, 255, 255));
        KhachHangLb.setText("Khách hàng");
        KhachHangLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KhachHangLbMouseClicked(evt);
            }
        });

        NhanVienLb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        NhanVienLb.setForeground(new java.awt.Color(255, 255, 255));
        NhanVienLb.setText("Nhân viên");
        NhanVienLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NhanVienLbMouseClicked(evt);
            }
        });

        HopDongLb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        HopDongLb.setForeground(new java.awt.Color(255, 255, 255));
        HopDongLb.setText("Hợp đồng");
        HopDongLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HopDongLbMouseClicked(evt);
            }
        });

        KhuyenMaiLb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        KhuyenMaiLb.setForeground(new java.awt.Color(255, 255, 255));
        KhuyenMaiLb.setText("Khuyến mãi");
        KhuyenMaiLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KhuyenMaiLbMouseClicked(evt);
            }
        });

        TrangBiLb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        TrangBiLb.setForeground(new java.awt.Color(255, 255, 255));
        TrangBiLb.setText("Trang bị");
        TrangBiLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TrangBiLbMouseClicked(evt);
            }
        });

        HoaDonLb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        HoaDonLb.setForeground(new java.awt.Color(255, 255, 255));
        HoaDonLb.setText("Hoá đơn");
        HoaDonLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HoaDonLbMouseClicked(evt);
            }
        });

        ChamCongLb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ChamCongLb.setForeground(new java.awt.Color(255, 255, 255));
        ChamCongLb.setText("Chấm công");
        ChamCongLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChamCongLbMouseClicked(evt);
            }
        });

        DangXuatLb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        DangXuatLb.setForeground(new java.awt.Color(48, 90, 184));
        DangXuatLb.setText("Đăng xuất");
        DangXuatLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DangXuatLbMouseClicked(evt);
            }
        });

        luongLb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        luongLb.setForeground(new java.awt.Color(255, 255, 255));
        luongLb.setText("Lương");
        luongLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                luongLbMouseClicked(evt);
            }
        });

        phongLb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        phongLb.setForeground(new java.awt.Color(220, 220, 46));
        phongLb.setText("Phòng");
        phongLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                phongLbMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout MenuPanelLayout = new javax.swing.GroupLayout(MenuPanel);
        MenuPanel.setLayout(MenuPanelLayout);
        MenuPanelLayout.setHorizontalGroup(
            MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(svgLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1))
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(phongLb))
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(KhachHangLb))
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(NhanVienLb))
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(HopDongLb))
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(KhuyenMaiLb))
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgTB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(TrangBiLb))
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(HoaDonLb))
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(ChamCongLb))
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(luongLb))
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(DangXuatLb))
        );
        MenuPanelLayout.setVerticalGroup(
            MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phongLb))
                .addGap(10, 10, 10)
                .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(KhachHangLb))
                .addGap(10, 10, 10)
                .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NhanVienLb))
                .addGap(10, 10, 10)
                .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HopDongLb))
                .addGap(10, 10, 10)
                .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(KhuyenMaiLb))
                .addGap(10, 10, 10)
                .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgTB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TrangBiLb))
                .addGap(10, 10, 10)
                .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HoaDonLb))
                .addGap(10, 10, 10)
                .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChamCongLb))
                .addGap(10, 10, 10)
                .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(luongLb))
                .addGap(20, 20, 20)
                .addComponent(DangXuatLb))
        );

        mainPanel.setPreferredSize(new java.awt.Dimension(700, 600));
        mainPanel.setLayout(new java.awt.CardLayout());

        NhapThongTinKHPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(24, 24, 68));
        jLabel9.setText("NHẬP THÔNG TIN CÁ NHÂN");

        them_CCCDjTextField.setBackground(new java.awt.Color(255, 255, 255));
        them_CCCDjTextField.setForeground(new java.awt.Color(0, 0, 0));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Tra cứu CCCD");

        traCuuCCCDjButton.setBackground(new java.awt.Color(24, 24, 68));
        traCuuCCCDjButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        traCuuCCCDjButton.setForeground(new java.awt.Color(255, 255, 255));
        traCuuCCCDjButton.setText("Tra cứu");
        traCuuCCCDjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                traCuuCCCDjButtonActionPerformed(evt);
            }
        });

        them_hoTenjTextField.setBackground(new java.awt.Color(255, 255, 255));
        them_hoTenjTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        them_hoTenjTextField.setForeground(new java.awt.Color(0, 0, 0));

        them_gioiTinhjComboBox.setBackground(new java.awt.Color(255, 255, 255));
        them_gioiTinhjComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        them_gioiTinhjComboBox.setForeground(new java.awt.Color(0, 0, 0));
        them_gioiTinhjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        them_ngaySinhjComboBox.setBackground(new java.awt.Color(255, 255, 255));

        them_diaChijTextField.setBackground(new java.awt.Color(255, 255, 255));
        them_diaChijTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        them_diaChijTextField.setForeground(new java.awt.Color(0, 0, 0));

        them_SDTjTextField.setBackground(new java.awt.Color(255, 255, 255));
        them_SDTjTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        them_SDTjTextField.setForeground(new java.awt.Color(0, 0, 0));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Họ Tên ");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Giới tính");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Ngày Sinh");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Địa chỉ");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Số điện thoại");

        KH_HụyButton.setBackground(new java.awt.Color(24, 24, 68));
        KH_HụyButton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        KH_HụyButton.setForeground(new java.awt.Color(255, 255, 255));
        KH_HụyButton.setText("Hủy");
        KH_HụyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KH_HụyButtonActionPerformed(evt);
            }
        });

        KH_DatPhongjButton.setBackground(new java.awt.Color(24, 24, 68));
        KH_DatPhongjButton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        KH_DatPhongjButton.setForeground(new java.awt.Color(255, 255, 255));
        KH_DatPhongjButton.setText("Đặt phòng");
        KH_DatPhongjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KH_DatPhongjButtonActionPerformed(evt);
            }
        });

        thongBaojLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout NhapThongTinKHPanelLayout = new javax.swing.GroupLayout(NhapThongTinKHPanel);
        NhapThongTinKHPanel.setLayout(NhapThongTinKHPanelLayout);
        NhapThongTinKHPanelLayout.setHorizontalGroup(
            NhapThongTinKHPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhapThongTinKHPanelLayout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(jLabel9))
            .addGroup(NhapThongTinKHPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(NhapThongTinKHPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(them_CCCDjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(traCuuCCCDjButton))
            .addGroup(NhapThongTinKHPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(thongBaojLable, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(NhapThongTinKHPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(181, 181, 181)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(NhapThongTinKHPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(them_hoTenjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(them_ngaySinhjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(NhapThongTinKHPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(NhapThongTinKHPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(them_gioiTinhjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(them_diaChijTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(them_SDTjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(NhapThongTinKHPanelLayout.createSequentialGroup()
                .addGap(458, 458, 458)
                .addComponent(KH_HụyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(KH_DatPhongjButton))
        );
        NhapThongTinKHPanelLayout.setVerticalGroup(
            NhapThongTinKHPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhapThongTinKHPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel9)
                .addGap(45, 45, 45)
                .addComponent(jLabel6)
                .addGap(4, 4, 4)
                .addGroup(NhapThongTinKHPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NhapThongTinKHPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(them_CCCDjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(traCuuCCCDjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(thongBaojLable, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(NhapThongTinKHPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11))
                .addGap(6, 6, 6)
                .addGroup(NhapThongTinKHPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(them_hoTenjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(NhapThongTinKHPanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(them_ngaySinhjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(NhapThongTinKHPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(10, 10, 10)
                .addGroup(NhapThongTinKHPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(them_gioiTinhjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(them_diaChijTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(them_SDTjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(130, 130, 130)
                .addGroup(NhapThongTinKHPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(KH_HụyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(KH_DatPhongjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        mainPanel.add(NhapThongTinKHPanel, "card3");

        LapHopDongPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(24, 24, 68));
        jLabel16.setText("NHẬP THÔNG TIN HỢP ĐỒNG");

        hdong_CCCDjLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hdong_CCCDjLable.setForeground(new java.awt.Color(0, 0, 0));
        hdong_CCCDjLable.setText("CCCD");

        hdong_NgaySinhjLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hdong_NgaySinhjLabel.setForeground(new java.awt.Color(0, 0, 0));
        hdong_NgaySinhjLabel.setText("Ngày sinh");

        hdong_hoTenjLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hdong_hoTenjLabel.setForeground(new java.awt.Color(0, 0, 0));
        hdong_hoTenjLabel.setText("Họ tên");

        hdong_GioiTinhjLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hdong_GioiTinhjLabel.setForeground(new java.awt.Color(0, 0, 0));
        hdong_GioiTinhjLabel.setText("Giới tính");

        hdong_DiaChijLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hdong_DiaChijLabel.setForeground(new java.awt.Color(0, 0, 0));
        hdong_DiaChijLabel.setText("Địa chỉ");

        hdong_SDTjLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hdong_SDTjLabel.setForeground(new java.awt.Color(0, 0, 0));
        hdong_SDTjLabel.setText("SĐT");

        hdong_huyjButton.setBackground(new java.awt.Color(24, 24, 68));
        hdong_huyjButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hdong_huyjButton.setForeground(new java.awt.Color(255, 255, 255));
        hdong_huyjButton.setText("Hủy");
        hdong_huyjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hdong_huyjButtonActionPerformed(evt);
            }
        });

        hdong_QuayLaijButton.setBackground(new java.awt.Color(24, 24, 68));
        hdong_QuayLaijButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hdong_QuayLaijButton.setForeground(new java.awt.Color(255, 255, 255));
        hdong_QuayLaijButton.setText("Quay lại");
        hdong_QuayLaijButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hdong_QuayLaijButtonActionPerformed(evt);
            }
        });

        hdong_XacNhanjButton.setBackground(new java.awt.Color(24, 24, 68));
        hdong_XacNhanjButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hdong_XacNhanjButton.setForeground(new java.awt.Color(255, 255, 255));
        hdong_XacNhanjButton.setText("Xác nhận ");
        hdong_XacNhanjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hdong_XacNhanjButtonActionPerformed(evt);
            }
        });

        hdong_ngayNhanjLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hdong_ngayNhanjLabel.setForeground(new java.awt.Color(0, 0, 0));
        hdong_ngayNhanjLabel.setText("Thời gian nhận phòng");

        hdong_ngayTrajLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hdong_ngayTrajLabel.setForeground(new java.awt.Color(0, 0, 0));
        hdong_ngayTrajLabel.setText("Thời gian trả phòng");

        hdong_cocjLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hdong_cocjLabel1.setForeground(new java.awt.Color(0, 0, 0));
        hdong_cocjLabel1.setText("Tiền cọc");

        hdong_ngaythuejLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hdong_ngaythuejLabel.setForeground(new java.awt.Color(0, 0, 0));
        hdong_ngaythuejLabel.setText("Số ngày thuê");

        hdong_sokhachjLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hdong_sokhachjLabel2.setForeground(new java.awt.Color(0, 0, 0));
        hdong_sokhachjLabel2.setText("Số khách");

        sokhachjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã phòng", "Loại phòng", "Giá phòng", "Số khách"
            }
        ));
        danhsachphong_jScrollPane.setViewportView(sokhachjTable);
        sokhachjTable.getAccessibleContext().setAccessibleName("");
        sokhachjTable.getAccessibleContext().setAccessibleDescription("");
        sokhachjTable.getAccessibleContext().setAccessibleParent(sokhachjTable);

        hdong_loaiKHJlable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hdong_loaiKHJlable.setForeground(new java.awt.Color(0, 0, 0));
        hdong_loaiKHJlable.setText("Loại khách");

        buttonGroup1.add(rNoidia);
        rNoidia.setSelected(true);
        rNoidia.setText("Nội địa");
        rNoidia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rNoidiaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rNuocngoai);
        rNuocngoai.setText("Nước ngoài");

        javax.swing.GroupLayout LapHopDongPanelLayout = new javax.swing.GroupLayout(LapHopDongPanel);
        LapHopDongPanel.setLayout(LapHopDongPanelLayout);
        LapHopDongPanelLayout.setHorizontalGroup(
            LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                .addGroup(LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                        .addGap(370, 370, 370)
                        .addComponent(hdong_huyjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(hdong_QuayLaijButton)
                        .addGap(15, 15, 15)
                        .addComponent(hdong_XacNhanjButton))
                    .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(danhsachphong_jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hdong_SDTjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hdong_loaiKHJlable, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rNoidia, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rNuocngoai, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                                .addGroup(LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(hdong_sokhachjLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(hdong_cocjLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(hdong_ngaythuejLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(hdong_ngayTrajLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE))
                                .addGroup(LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(hdong_CCCDjLable, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LapHopDongPanelLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(hdong_NgaySinhjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LapHopDongPanelLayout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addComponent(hdong_GioiTinhjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LapHopDongPanelLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(hdong_DiaChijLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                                .addComponent(hdong_ngayNhanjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(hdong_hoTenjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                                .addGap(102, 102, 102)
                                .addComponent(jLabel16)))))
                .addGap(136, 136, 136))
        );
        LapHopDongPanelLayout.setVerticalGroup(
            LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel16)
                .addGap(40, 40, 40)
                .addGroup(LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hdong_ngayNhanjLabel)
                    .addComponent(hdong_hoTenjLabel))
                .addGap(18, 18, 18)
                .addGroup(LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hdong_ngayTrajLabel)
                    .addComponent(hdong_CCCDjLable))
                .addGap(18, 18, 18)
                .addGroup(LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hdong_ngaythuejLabel)
                    .addComponent(hdong_NgaySinhjLabel))
                .addGap(18, 18, 18)
                .addGroup(LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hdong_GioiTinhjLabel)
                    .addComponent(hdong_cocjLabel1))
                .addGap(18, 18, 18)
                .addGroup(LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hdong_DiaChijLabel)
                    .addComponent(hdong_sokhachjLabel2))
                .addGap(18, 18, 18)
                .addGroup(LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(danhsachphong_jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                        .addComponent(hdong_SDTjLabel)
                        .addGap(18, 18, 18)
                        .addComponent(hdong_loaiKHJlable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rNoidia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rNuocngoai)))
                .addGap(111, 111, 111)
                .addGroup(LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hdong_huyjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hdong_QuayLaijButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hdong_XacNhanjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        mainPanel.add(LapHopDongPanel, "card4");

        TraCuuPhongPanel.setBackground(new java.awt.Color(255, 255, 255));
        TraCuuPhongPanel.setForeground(new java.awt.Color(255, 255, 255));
        TraCuuPhongPanel.setToolTipText("");
        TraCuuPhongPanel.setPreferredSize(new java.awt.Dimension(900, 600));

        danhSachPHONGTable.setBackground(new java.awt.Color(255, 255, 255));
        danhSachPHONGTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        danhSachPHONGTable.setForeground(new java.awt.Color(0, 0, 0));
        danhSachPHONGTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Họ tên", "CCCD", "Ngày sinh", "Giới tính", "Địa chỉ", "SĐT", "Số HĐ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(danhSachPHONGTable);

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(24, 24, 68));
        jLabel8.setText("PHÒNG");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Thời gian nhận phòng");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Thời gian trả phòng");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Loại phòng");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tầng");

        jComboBox2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Tầng 1", "Tầng 2", "Tầng 3", "Tầng 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "A", "B", "C" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        checkInDate.setBackground(new java.awt.Color(255, 255, 255));
        checkInDate.setForeground(new java.awt.Color(0, 0, 0));
        checkInDate.setToolTipText("");
        checkInDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        checkOutDate.setBackground(new java.awt.Color(255, 255, 255));
        checkOutDate.setForeground(new java.awt.Color(0, 0, 0));
        checkOutDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        checkInTime.setBackground(new java.awt.Color(255, 255, 255));
        checkInTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkInTimeActionPerformed(evt);
            }
        });

        checkOutTime.setBackground(new java.awt.Color(255, 255, 255));

        traCuujButton1.setBackground(new java.awt.Color(24, 24, 68));
        traCuujButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        traCuujButton1.setForeground(new java.awt.Color(255, 255, 255));
        traCuujButton1.setText("Tra cứu");
        traCuujButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                traCuujButton1ActionPerformed(evt);
            }
        });

        datPhongjButton2.setBackground(new java.awt.Color(24, 24, 68));
        datPhongjButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        datPhongjButton2.setForeground(new java.awt.Color(255, 255, 255));
        datPhongjButton2.setText("Đặt phòng");
        datPhongjButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datPhongjButton2ActionPerformed(evt);
            }
        });

        datPhongjButton3.setBackground(new java.awt.Color(24, 24, 68));
        datPhongjButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        datPhongjButton3.setForeground(new java.awt.Color(255, 255, 255));
        datPhongjButton3.setText("Cập nhật phòng");
        datPhongjButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datPhongjButton3ActionPerformed(evt);
            }
        });

        LoaiPhongjButton.setBackground(new java.awt.Color(24, 24, 68));
        LoaiPhongjButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LoaiPhongjButton.setForeground(new java.awt.Color(255, 255, 255));
        LoaiPhongjButton.setText("Loại phòng");
        LoaiPhongjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoaiPhongjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TraCuuPhongPanelLayout = new javax.swing.GroupLayout(TraCuuPhongPanel);
        TraCuuPhongPanel.setLayout(TraCuuPhongPanelLayout);
        TraCuuPhongPanelLayout.setHorizontalGroup(
            TraCuuPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TraCuuPhongPanelLayout.createSequentialGroup()
                .addGroup(TraCuuPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TraCuuPhongPanelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(TraCuuPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(TraCuuPhongPanelLayout.createSequentialGroup()
                                .addComponent(LoaiPhongjButton)
                                .addGap(40, 40, 40)
                                .addComponent(datPhongjButton3)
                                .addGap(40, 40, 40)
                                .addComponent(datPhongjButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(TraCuuPhongPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(TraCuuPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(TraCuuPhongPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(89, 89, 89)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(TraCuuPhongPanelLayout.createSequentialGroup()
                                .addGroup(TraCuuPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(TraCuuPhongPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(TraCuuPhongPanelLayout.createSequentialGroup()
                                        .addGroup(TraCuuPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(checkInDate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(checkInTime, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(40, 40, 40)
                                        .addGroup(TraCuuPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(checkOutTime, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(checkOutDate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(40, 40, 40)
                                .addComponent(traCuujButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(38, 38, 38))
        );
        TraCuuPhongPanelLayout.setVerticalGroup(
            TraCuuPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TraCuuPhongPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel8)
                .addGap(11, 11, 11)
                .addGroup(TraCuuPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(TraCuuPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(TraCuuPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(10, 10, 10)
                .addGroup(TraCuuPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkInDate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkOutDate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(traCuujButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TraCuuPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkOutTime, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkInTime, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(TraCuuPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(datPhongjButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datPhongjButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LoaiPhongjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        mainPanel.add(TraCuuPhongPanel, "card2");

        LoaiPhongjPanel.setBackground(new java.awt.Color(255, 255, 255));
        LoaiPhongjPanel.setForeground(new java.awt.Color(0, 0, 0));
        LoaiPhongjPanel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        TieudejLabel.setBackground(new java.awt.Color(255, 255, 255));
        TieudejLabel.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        TieudejLabel.setForeground(new java.awt.Color(24, 24, 68));
        TieudejLabel.setText("LOẠI PHÒNG");

        tenloaijLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tenloaijLabel.setForeground(new java.awt.Color(0, 0, 0));
        tenloaijLabel.setText("Tên loại phòng");

        giajLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        giajLabel.setForeground(new java.awt.Color(0, 0, 0));
        giajLabel.setText("Giá phòng");

        loaiPhongTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã loại", "Tên loại", "Giá phòng"
            }
        ));
        loaiPhongTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loaiPhongTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(loaiPhongTable);

        Sua_LPjButton.setBackground(new java.awt.Color(24, 24, 68));
        Sua_LPjButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Sua_LPjButton.setForeground(new java.awt.Color(255, 255, 255));
        Sua_LPjButton.setText("Sửa");
        Sua_LPjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sua_LPjButtonActionPerformed(evt);
            }
        });

        Quaylai_jButton.setBackground(new java.awt.Color(24, 24, 68));
        Quaylai_jButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Quaylai_jButton.setForeground(new java.awt.Color(255, 255, 255));
        Quaylai_jButton.setText("Quay lại");
        Quaylai_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Quaylai_jButtonActionPerformed(evt);
            }
        });

        Them_LPjButton.setBackground(new java.awt.Color(24, 24, 68));
        Them_LPjButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Them_LPjButton.setForeground(new java.awt.Color(255, 255, 255));
        Them_LPjButton.setText("Thêm");
        Them_LPjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Them_LPjButtonActionPerformed(evt);
            }
        });

        Xoa_LPjButton.setBackground(new java.awt.Color(24, 24, 68));
        Xoa_LPjButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Xoa_LPjButton.setForeground(new java.awt.Color(255, 255, 255));
        Xoa_LPjButton.setText("Xóa");
        Xoa_LPjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Xoa_LPjButtonActionPerformed(evt);
            }
        });

        txtTenLoai.setBackground(new java.awt.Color(255, 255, 255));
        txtTenLoai.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtTenLoai.setForeground(new java.awt.Color(0, 0, 0));
        txtTenLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenLoaiActionPerformed(evt);
            }
        });

        txtGia.setBackground(new java.awt.Color(255, 255, 255));
        txtGia.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtGia.setForeground(new java.awt.Color(0, 0, 0));
        txtGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaActionPerformed(evt);
            }
        });

        Huy_jButton1.setBackground(new java.awt.Color(24, 24, 68));
        Huy_jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Huy_jButton1.setForeground(new java.awt.Color(255, 255, 255));
        Huy_jButton1.setText("Hủy");
        Huy_jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Huy_jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LoaiPhongjPanelLayout = new javax.swing.GroupLayout(LoaiPhongjPanel);
        LoaiPhongjPanel.setLayout(LoaiPhongjPanelLayout);
        LoaiPhongjPanelLayout.setHorizontalGroup(
            LoaiPhongjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoaiPhongjPanelLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(LoaiPhongjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(LoaiPhongjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TieudejLabel)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(LoaiPhongjPanelLayout.createSequentialGroup()
                            .addGroup(LoaiPhongjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(giajLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tenloaijLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(LoaiPhongjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTenLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(LoaiPhongjPanelLayout.createSequentialGroup()
                        .addComponent(Quaylai_jButton)
                        .addGap(30, 30, 30)
                        .addComponent(Huy_jButton1)
                        .addGap(40, 40, 40)
                        .addComponent(Them_LPjButton)
                        .addGap(40, 40, 40)
                        .addComponent(Sua_LPjButton)
                        .addGap(40, 40, 40)
                        .addComponent(Xoa_LPjButton)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        LoaiPhongjPanelLayout.setVerticalGroup(
            LoaiPhongjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoaiPhongjPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(TieudejLabel)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(LoaiPhongjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tenloaijLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(LoaiPhongjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(giajLabel))
                .addGap(30, 30, 30)
                .addGroup(LoaiPhongjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Quaylai_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Them_LPjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sua_LPjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Xoa_LPjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Huy_jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        mainPanel.add(LoaiPhongjPanel, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void KhachHangLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KhachHangLbMouseClicked
        dispose();
        KhachHangFrame.main(currentUser);

    }//GEN-LAST:event_KhachHangLbMouseClicked

    private void NhanVienLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NhanVienLbMouseClicked
        //        System.out.println(currentUser.toString());
        if (TrangChuDAO.KTLoaiNV(currentUser.getMaNV()) == 1) {
            dispose();
            NhanVienFrame.main(currentUser);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập vào chức năng này", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_NhanVienLbMouseClicked

    private void ChamCongLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChamCongLbMouseClicked
        if (TrangChuDAO.KTLoaiNV(currentUser.getMaNV()) == 1) {
            dispose();
            ChamCongFrame.main(currentUser);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập vào chức năng này", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_ChamCongLbMouseClicked

    private void DangXuatLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DangXuatLbMouseClicked
        int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất không?", "Đăng xuất", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            dispose();
            DangNhapFrame.main(null);
        }
    }//GEN-LAST:event_DangXuatLbMouseClicked

    private void luongLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_luongLbMouseClicked
        if (TrangChuDAO.KTLoaiNV(currentUser.getMaNV()) == 1) {
            dispose();
            LuongFrame.main(currentUser);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập vào chức năng này", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_luongLbMouseClicked

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void traCuujButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_traCuujButton1ActionPerformed

        String text1 = checkInTime.getText();
        String text2 = checkOutTime.getText();

        if (text1.isEmpty() && text2.isEmpty()) {
            TraCuuPhongKhongTime();
        } else if (!text1.isEmpty() && !text2.isEmpty()) {
            this.checkInDateTime = doiNgay(checkInDate.getDate()) + " " + checkInTime.getText();
            this.checkOutDateTime = doiNgay(checkOutDate.getDate()) + " " + checkOutTime.getText();
            // Định dạng cho ngày giờ
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            // Chuyển đổi chuỗi thành LocalDateTime
            LocalDateTime checkIn = LocalDateTime.parse(checkInDateTime, formatter);
            LocalDateTime checkOut = LocalDateTime.parse(checkOutDateTime, formatter);
            if (checkOut.isAfter(checkIn)) {
                TraCuuPhongCoTime();
            } else {
                // Hiển thị thông báo lỗi cho người dùng
                JOptionPane.showMessageDialog(null, "Thời gian trả phải sau thời gian nhận.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Nhập đầy đủ thông tin các thời gian đặt phòng", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_traCuujButton1ActionPerformed

    private void datPhongjButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datPhongjButton2ActionPerformed
        // TODO add your handling code here:
        int[] selectedRows = this.danhSachPHONGTable.getSelectedRows();

        try {
            if (selectedRows.length == 0) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn phòng muốn đặt!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else if (this.danhSachPHONGTable.getRowCount() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Dữ liệu trống!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else {
                
                // Kiểm tra bảo trì
                for (int selectedIndex : selectedRows) {
                    String tinhTrang = danhSachPHONGTable
                            .getValueAt(selectedIndex, 2) // cột Tình trạng
                            .toString();

                    if ("Bảo trì".equalsIgnoreCase(tinhTrang)) {
                        JOptionPane.showMessageDialog(
                                rootPane,
                                "Không thể đặt phòng vì có phòng đang BẢO TRÌ!",
                                "Không hợp lệ",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                }
                // Xử lý từng hàng được chọn
                soPhongDatList.clear(); // Xóa danh sách cũ trước khi thêm mới
                for (int selectedIndex : selectedRows) {
                    Object maPhongObject = danhSachPHONGTable.getValueAt(selectedIndex, 0);
                    Integer soPhongDat = (Integer) maPhongObject;
                    soPhongDatList.add(soPhongDat);
                }

                String text1 = checkInTime.getText();
                String text2 = checkOutTime.getText();

                if (text1.isEmpty() && text2.isEmpty()) {
                    String checkin = doiNgay(checkInDate.getDate());
                    this.checkInDateTime = checkin + " 14:00:00";
                    String checkout = doiNgay(checkOutDate.getDate());
                    this.checkOutDateTime = checkout + " 12:00:00";
                } else {
                    this.checkInDateTime = doiNgay(checkInDate.getDate()) + " " + checkInTime.getText();
                    this.checkOutDateTime = doiNgay(checkOutDate.getDate()) + " " + checkOutTime.getText();
                }

                // In danh sách các phòng đã đặt để kiểm tra
                System.out.println(soPhongDatList);
                long thoiGian = HopDongDAO.tinhThoiGian(checkInDateTime, checkOutDateTime);
                System.out.println("Thời gian giữa hai thời điểm là: " + thoiGian + " phút.");
                // Đặt lại các trường đầu vào
                this.them_ngaySinhjComboBox.setCalendar(null);
                them_gioiTinhjComboBox.setSelectedItem(null);
                cardlayout.show(mainPanel, "nhapThongTin");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Error!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_datPhongjButton2ActionPerformed

    private void traCuuCCCDjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_traCuuCCCDjButtonActionPerformed
        // Lấy đầu vào từ người dùng
        String CCCD = this.them_CCCDjTextField.getText();

        KhachHangModel kh = KhachHangDAO.getKHtheoCCCD(CCCD);
        // Sử dụng thông tin kh để cập nhật giao diện
        if (CCCD.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập CCCD, không được để trống!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else if (CCCD.length() != 12) {
            JOptionPane.showMessageDialog(rootPane, "CCCD phải đủ 12 số !", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else if (kh != null) {
            thongBaojLable.setText("");
            this.maKHCu = kh.getMaKH();
            them_hoTenjTextField.setText(kh.getTenKH());

            them_gioiTinhjComboBox.setSelectedItem(kh.getGioiTinh());
            // chuyển đổi sang định dạng LocalDate
            Date ngaySinh = GregorianCalendar.from(kh.getNgaySinh().atStartOfDay(ZoneId.systemDefault())).getTime();
            them_ngaySinhjComboBox.setDate(ngaySinh);

            them_diaChijTextField.setText(kh.getDiaChi());
            them_SDTjTextField.setText(kh.getSDT());
        } else {
            this.maKHCu = 0;
            thongBaojLable.setText("Thông tin không tồn tại, Vui lòng nhập thông tin cá nhân!");
            them_hoTenjTextField.setText("");
            them_gioiTinhjComboBox.setSelectedItem(null);
            them_ngaySinhjComboBox.setDate(null);
            them_diaChijTextField.setText("");
            them_SDTjTextField.setText("");
        }
    }//GEN-LAST:event_traCuuCCCDjButtonActionPerformed

    private void KH_HụyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KH_HụyButtonActionPerformed
        // TODO add your handling code here:
        cardlayout.show(mainPanel, "datPhong");
        inDanhSach();
        this.them_hoTenjTextField.setText("");
        this.them_CCCDjTextField.setText("");
        this.them_diaChijTextField.setText("");
        this.them_SDTjTextField.setText("");
        this.them_ngaySinhjComboBox.setCalendar(null);
        thongBaojLable.setText("");
    }//GEN-LAST:event_KH_HụyButtonActionPerformed

    private void KH_DatPhongjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KH_DatPhongjButtonActionPerformed
        // TODO add your handling code here:
        String CCCD = this.them_CCCDjTextField.getText();
        String SDT = this.them_SDTjTextField.getText();
        String hoTen = this.them_hoTenjTextField.getText();
        String diaChi = this.them_diaChijTextField.getText();
        if (CCCD.equals("") || SDT.equals("") || hoTen.equals("") || diaChi.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập đầy đủ thông tin, không được để trống!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else {
            if (maKHCu == 0) {
                String CCCDCu = themKHMoi();
                KhachHangModel kh = KhachHangDAO.getKHtheoCCCD(CCCDCu);
                this.maKHCu = kh.getMaKH();
                System.out.println(this.maKHCu);
                System.out.println(checkInDateTime);
                System.out.println(checkOutDateTime);
            } else {
                capNhatThongTin();
                System.out.println(this.maKHCu);
                System.out.println(checkInDateTime);
                System.out.println(checkOutDateTime);
            }
            cardlayout.show(mainPanel, "lapHopDong");
            try {
                hienThongTinKH();
            } catch (SQLException ex) {
                Logger.getLogger(DatPhongOnlFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_KH_DatPhongjButtonActionPerformed

    private void hdong_huyjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hdong_huyjButtonActionPerformed
        // TODO add your handling code here:
        cardlayout.show(mainPanel, "datPhong");
        inDanhSach();
        this.them_hoTenjTextField.setText("");
        this.them_CCCDjTextField.setText("");
        this.them_diaChijTextField.setText("");
        this.them_SDTjTextField.setText("");
        this.them_ngaySinhjComboBox.setCalendar(null);
        thongBaojLable.setText("");
    }//GEN-LAST:event_hdong_huyjButtonActionPerformed

    private void hdong_QuayLaijButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hdong_QuayLaijButtonActionPerformed
        // TODO add your handling code here:
        cardlayout.show(mainPanel, "nhapThongTin");
    }//GEN-LAST:event_hdong_QuayLaijButtonActionPerformed

    private void hdong_XacNhanjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hdong_XacNhanjButtonActionPerformed
       try {
        long tongPhut = HopDongDAO.tinhThoiGian(checkInDateTime, checkOutDateTime);
        String hinhThucThue = (tongPhut < 22 * 60) ? "Giờ" : "Ngày";

        String loaiKH = rNuocngoai.isSelected() ? "Nước ngoài" : "Nội địa";

        long tienCoc = HopDongDAO.tinhTienCoc(soPhongDatList, tongPhut);

        HopDongDAO hdDAO = new HopDongDAO();
        int row = hdDAO.ttunThemHopDong(
            maKHCu,
            checkInDateTime,
            checkOutDateTime,
            hinhThucThue,
            loaiKH,
            tienCoc
        );

        if (row <= 0) {
            JOptionPane.showMessageDialog(this, "Thêm hợp đồng thất bại");
            return;
        }

        int maHopDong = HopDongDAO.getMaHopDongMoiNhat();

        if (sokhachjTable.isEditing()) {
            sokhachjTable.getCellEditor().stopCellEditing();
        }

        DefaultTableModel model = (DefaultTableModel) sokhachjTable.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            int maPhong = (int) model.getValueAt(i, 0);
            int soKhach = (int) model.getValueAt(i, 3);

            ChiTietDatPhongDAO.ThemCTDP(maHopDong, maPhong, soKhach);
        }

        JOptionPane.showMessageDialog(this, "Thêm hợp đồng thành công!");
        cardlayout.show(mainPanel, "datPhong");

        inDanhSach(); 

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!");
    }


    }//GEN-LAST:event_hdong_XacNhanjButtonActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        dispose();
        TrangChuNVFrame.main(currentUser);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void phongLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_phongLbMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_phongLbMouseClicked

    private void HopDongLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HopDongLbMouseClicked
        // TODO add your handling code here:
        dispose();
        HopDongFrame.main(currentUser);
    }//GEN-LAST:event_HopDongLbMouseClicked

    private void KhuyenMaiLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KhuyenMaiLbMouseClicked
        // TODO add your handling code here:
        if (TrangChuDAO.KTLoaiNV(currentUser.getMaNV()) == 1) {
            dispose();
            KhuyenMaiFrame.main(currentUser);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập vào chức năng này", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_KhuyenMaiLbMouseClicked

    private void TrangBiLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangBiLbMouseClicked
        // TODO add your handling code here:
        dispose();
        TrangBiFrame.main(currentUser);
    }//GEN-LAST:event_TrangBiLbMouseClicked

    private void HoaDonLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HoaDonLbMouseClicked
        // TODO add your handling code here:
        dispose();
        HoaDonFrame.main(currentUser);
    }//GEN-LAST:event_HoaDonLbMouseClicked

    private void checkInTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkInTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkInTimeActionPerformed

    private void datPhongjButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datPhongjButton3ActionPerformed
        int[] selectedRows = danhSachPHONGTable.getSelectedRows();

        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng chọn 1 phòng để cập nhật!",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (selectedRows.length > 1) {
            JOptionPane.showMessageDialog(this,
                    "Chỉ được chọn 1 phòng!",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int row = selectedRows[0];
        int maPhong = (int) danhSachPHONGTable.getValueAt(row, 0);

        // Mở dialog
        CapNhatPhongDialog dialog = new CapNhatPhongDialog(this, true, maPhong);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        // dialog đóng xong mới chạy tới đây
        try {
            if (danhSachPHONGTable.isEditing()) {
                danhSachPHONGTable.getCellEditor().stopCellEditing();
            }
            inDanhSach();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                this,
                "Lỗi load lại danh sách phòng!",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_datPhongjButton3ActionPerformed

    private void rNoidiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rNoidiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rNoidiaActionPerformed

    private void LoaiPhongjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoaiPhongjButtonActionPerformed
        cardlayout.show(mainPanel, "loaiPhong");
        try {
            loadLoaiPhongTable();
        } catch (SQLException ex) {
            Logger.getLogger(PhongFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_LoaiPhongjButtonActionPerformed

    private void Quaylai_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Quaylai_jButtonActionPerformed
        cardlayout.show(mainPanel, "datPhong");
        inDanhSach();
    }//GEN-LAST:event_Quaylai_jButtonActionPerformed

    private void Them_LPjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Them_LPjButtonActionPerformed
        String ten = txtTenLoai.getText().trim();
        String giaStr = txtGia.getText().trim();

        if (ten.isEmpty() || giaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống!");
            return;
        }

        long gia;
        try {
            gia = Long.parseLong(giaStr);
            if (gia <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá phải là số > 0");
            return;
        }

        if (PhongDAO.insertLoaiPhong(ten, gia)) {
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
            try {
                loadLoaiPhongTable();
            } catch (SQLException ex) {
                Logger.getLogger(PhongFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tên loại phòng đã tồn tại!");
        }
    }//GEN-LAST:event_Them_LPjButtonActionPerformed

    private void Xoa_LPjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Xoa_LPjButtonActionPerformed
        int row = loaiPhongTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn loại phòng cần xóa!");
            return;
        }

        int maLoai = (int) loaiPhongTable.getValueAt(row, 0);

        if (!PhongDAO.canDeleteLoaiPhong(maLoai)) {
            JOptionPane.showMessageDialog(this,
                "Không thể xóa vì đang có phòng sử dụng loại này!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc muốn xóa?",
            "Xác nhận",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        if (PhongDAO.deleteLoaiPhong(maLoai)) {
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
            try {
                loadLoaiPhongTable();
            } catch (SQLException ex) {
                Logger.getLogger(PhongFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_Xoa_LPjButtonActionPerformed

    private void txtTenLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenLoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenLoaiActionPerformed

    private void txtGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaActionPerformed

    private void Huy_jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Huy_jButton1ActionPerformed
        resetForm();
    }//GEN-LAST:event_Huy_jButton1ActionPerformed

    private void Sua_LPjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sua_LPjButtonActionPerformed

        int row = loaiPhongTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn loại phòng cần sửa!");
            return;
        }

        int maLoai = (int) loaiPhongTable.getValueAt(row, 0);
        String ten = txtTenLoai.getText().trim();
        long gia = Long.parseLong(txtGia.getText().trim());

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Xác nhận cập nhật loại phòng?",
            "Xác nhận",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        if (PhongDAO.updateLoaiPhong(maLoai, ten, gia)) {
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
            try {
                loadLoaiPhongTable();
            } catch (SQLException ex) {
                Logger.getLogger(PhongFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_Sua_LPjButtonActionPerformed

    private void loaiPhongTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loaiPhongTableMouseClicked
        int row = loaiPhongTable.getSelectedRow();
        if (row == -1) return;

        txtTenLoai.setText(loaiPhongTable.getValueAt(row, 1).toString());
        txtGia.setText(loaiPhongTable.getValueAt(row, 2).toString());

        Them_LPjButton.setEnabled(false);
        Sua_LPjButton.setEnabled(true);
        Xoa_LPjButton.setEnabled(true);
    }//GEN-LAST:event_loaiPhongTableMouseClicked


    public static void main(NhanVienModel args) {
        currentUser = args;

        currentUser.setMaNV(args.getMaNV());

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangChuNVFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new PhongFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ChamCongLb;
    private javax.swing.JLabel DangXuatLb;
    private javax.swing.JLabel HoaDonLb;
    private javax.swing.JLabel HopDongLb;
    private javax.swing.JButton Huy_jButton1;
    private javax.swing.JButton KH_DatPhongjButton;
    private javax.swing.JButton KH_HụyButton;
    private javax.swing.JLabel KhachHangLb;
    private javax.swing.JLabel KhuyenMaiLb;
    private javax.swing.JPanel LapHopDongPanel;
    private javax.swing.JButton LoaiPhongjButton;
    private javax.swing.JPanel LoaiPhongjPanel;
    private javax.swing.JPanel MenuPanel;
    private javax.swing.JLabel NhanVienLb;
    private javax.swing.JPanel NhapThongTinKHPanel;
    private javax.swing.JButton Quaylai_jButton;
    private javax.swing.JButton Sua_LPjButton;
    private javax.swing.JButton Them_LPjButton;
    private javax.swing.JLabel TieudejLabel;
    private javax.swing.JPanel TraCuuPhongPanel;
    private javax.swing.JLabel TrangBiLb;
    private javax.swing.JButton Xoa_LPjButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private de.wannawork.jcalendar.JCalendarComboBox checkInDate;
    private javax.swing.JTextField checkInTime;
    private de.wannawork.jcalendar.JCalendarComboBox checkOutDate;
    private javax.swing.JTextField checkOutTime;
    private javax.swing.JTable danhSachPHONGTable;
    private javax.swing.JScrollPane danhsachphong_jScrollPane;
    private javax.swing.JButton datPhongjButton2;
    private javax.swing.JButton datPhongjButton3;
    private javax.swing.JLabel giajLabel;
    private javax.swing.JLabel hdong_CCCDjLable;
    private javax.swing.JLabel hdong_DiaChijLabel;
    private javax.swing.JLabel hdong_GioiTinhjLabel;
    private javax.swing.JLabel hdong_NgaySinhjLabel;
    private javax.swing.JButton hdong_QuayLaijButton;
    private javax.swing.JLabel hdong_SDTjLabel;
    private javax.swing.JButton hdong_XacNhanjButton;
    private javax.swing.JLabel hdong_cocjLabel1;
    private javax.swing.JLabel hdong_hoTenjLabel;
    private javax.swing.JButton hdong_huyjButton;
    private javax.swing.JLabel hdong_loaiKHJlable;
    private javax.swing.JLabel hdong_ngayNhanjLabel;
    private javax.swing.JLabel hdong_ngayTrajLabel;
    private javax.swing.JLabel hdong_ngaythuejLabel;
    private javax.swing.JLabel hdong_sokhachjLabel2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable loaiPhongTable;
    private javax.swing.JLabel luongLb;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel phongLb;
    private javax.swing.JRadioButton rNoidia;
    private javax.swing.JRadioButton rNuocngoai;
    private javax.swing.JTable sokhachjTable;
    private Image.SVGImage svgCC;
    private Image.SVGImage svgHoaDon;
    private Image.SVGImage svgHopDong;
    private Image.SVGImage svgKM;
    private Image.SVGImage svgKhach;
    private Image.SVGImage svgL;
    private Image.SVGImage svgLogo;
    private Image.SVGImage svgNV;
    private Image.SVGImage svgPhong;
    private Image.SVGImage svgTB;
    private javax.swing.JLabel tenloaijLabel;
    private javax.swing.JTextField them_CCCDjTextField;
    private javax.swing.JTextField them_SDTjTextField;
    private javax.swing.JTextField them_diaChijTextField;
    private javax.swing.JComboBox<String> them_gioiTinhjComboBox;
    private javax.swing.JTextField them_hoTenjTextField;
    private de.wannawork.jcalendar.JCalendarComboBox them_ngaySinhjComboBox;
    private javax.swing.JLabel thongBaojLable;
    private javax.swing.JButton traCuuCCCDjButton;
    private javax.swing.JButton traCuujButton1;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtTenLoai;
    // End of variables declaration//GEN-END:variables
}
