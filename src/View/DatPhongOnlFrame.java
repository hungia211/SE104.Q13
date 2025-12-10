package View;

import DAO.ChiTietDatPhongDAO;
import DAO.HopDongDAO;
import DAO.KhachHangDAO;
import DAO.KhuyenMaiDAO;
import DAO.PhongDAO;
import Model.HopDongModel;
import Model.KhachHangModel;
import Model.KhuyenMaiModel;
import Model.PhongModel;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.CardLayout;
import java.awt.Component;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class DatPhongOnlFrame extends javax.swing.JFrame {

    CardLayout cardlayout;
    private DefaultTableModel defaultTableModel = new DefaultTableModel() {
        public boolean isCellEdittable(int row, int column) {
            return false;
        }
    };
    int SoPhongDat;
    String TGNhanPhong;
    String TGTraPhong;
    int maKHCu;
    long khoangtg;
    // Mảng để lưu số phòng đã đặt
    ArrayList<Integer> soPhongDatList = new ArrayList<>();

    public DatPhongOnlFrame() {
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        initComponents();
        this.setLocationRelativeTo(null);

        khoiTaoBang(); // Gọi phương thức khởi tạo bảng
        inDanhSach(); // Gọi phương thức in danh sách ban đầu nếu cần thiết

        // Tạo các luồng sự kiện chuyển Panel
        cardlayout = (CardLayout) (mainCardLayoutDPhong.getLayout());
        mainCardLayoutDPhong.add(DatPhongPanel, "datPhong");
        cardlayout.show(mainCardLayoutDPhong, "datPhong");
        mainCardLayoutDPhong.add(NhapThongTinKHPanel, "nhapThongTin");
        mainCardLayoutDPhong.add(LapHopDongPanel, "lapHongDong");
    }

    public void khoiTaoBang() {
        defaultTableModel = new DefaultTableModel();

        defaultTableModel.addColumn("Mã Phòng");
        defaultTableModel.addColumn("Tên Loại phòng");
        defaultTableModel.addColumn("Tinh Trạng");
        defaultTableModel.addColumn("Giá Phòng");

        danhSachPhongjTable.setModel(defaultTableModel);
        danhSachPhongjTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

    }

    public void inDanhSach() {
        // Tạo đối tượng danh sách phòng
        ArrayList<PhongModel> DS_Phong = PhongDAO.hungia_getDSPhongBinhThuong();

        // Xóa tất cả các hàng hiện có trong bảng
        defaultTableModel.setRowCount(0);

        // Thêm dữ liệu vào bảng
        for (PhongModel ph : DS_Phong) {
            defaultTableModel.addRow(new Object[]{
                ph.getMaPhong(), ph.getLoaiPhong().getTenloai(), ph.getTinhTrang(), ph.getLoaiPhong().getGia()
            });
        }

        for (int i = 0; i < defaultTableModel.getColumnCount(); i++) {
            TableColumn column = danhSachPhongjTable.getColumnModel().getColumn(i);
            int maxWidth = 0;

            // Tính toán chiều rộng dựa trên nội dung lớn nhất trong cột
            for (int row = 0; row < danhSachPhongjTable.getRowCount(); row++) {
                TableCellRenderer cellRenderer = danhSachPhongjTable.getCellRenderer(row, i);
                Component c = danhSachPhongjTable.prepareRenderer(cellRenderer, row, i);
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
    
    public void TraCuuPhong() {

        // Lấy dữ liệu tìm kiếm 
        String loaiPhong = (String) this.loaiPhongjComboBox.getSelectedItem();
        String tangStr = (String) this.kieuPhongjComboBox.getSelectedItem();

        String ngayNhan = doiNgay(this.ngayNhanjCalendarComboBox.getDate()) + " 14:00:00";
        String ngayTra = doiNgay(this.ngayTrajCalendarComboBox.getDate()) + " 12:00:00";

        ArrayList<PhongModel> DS_Phong;

        // Xóa bảng cũ
        defaultTableModel.setRowCount(0);

        // -------------------------------
        // 1) Người dùng chọn TẤT CẢ loại + TẤT CẢ tầng
        // -------------------------------
        if (loaiPhong.equals("Tất cả") && tangStr.equals("Tất cả")) {

            DS_Phong = PhongDAO.hungia_getDStheoNgayBinhThuong(ngayNhan, ngayTra);

            // -------------------------------
            // 2) Người dùng chọn TẦNG nhưng loại phòng = Tất cả
            // -------------------------------
        } else if (loaiPhong.equals("Tất cả")) {

            int tang = 0;
            if (!tangStr.equals("Tất cả")) {
                tang = Integer.parseInt(tangStr.split(" ")[1]);   // "Tầng 2" → 2
            }

            DS_Phong = PhongDAO.hungia_getDStheoTangBinhThuong(tang, ngayNhan, ngayTra);

            // -------------------------------
            // 3) Người dùng chọn LOẠI PHÒNG nhưng tầng = Tất cả
            // -------------------------------
        } else if (tangStr.equals("Tất cả")) {

            DS_Phong = PhongDAO.hungia_getDStheoLoaiPhongBinhThuong(loaiPhong, ngayNhan, ngayTra);

            // -------------------------------
            // 4) Người dùng chọn CẢ LOẠI và TẦNG
            // -------------------------------
        } else {

            int tang = Integer.parseInt(tangStr.split(" ")[1]);

            DS_Phong = PhongDAO.hungia_getDStheotracuuBinhThuong(loaiPhong, tang, ngayNhan, ngayTra);
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

    public static long tinhKhoangCach2Ngay(Date startDate, Date endDate) {
        // Chuyển đổi từ Date sang LocalDate
        LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Tính toán khoảng cách giữa hai ngày
        return ChronoUnit.DAYS.between(startLocalDate, endLocalDate);
    }

    public long tinhTongCoc(ArrayList<Integer> maPhong) throws SQLException {
        long sum = 0;
        for (int mp : maPhong) {
            PhongModel p = PhongDAO.getPhongTheoMa(mp);
            sum += p.getLoaiPhong().getGia();
        }
        return khoangtg * sum * 30 / 100;
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
        this.hdong_ngayNhanjLabel.setText("Ngày nhận phòng: " + TGNhanPhong);
        this.hdong_ngayTrajLabel.setText("Ngày trả phòng: " + TGTraPhong);
        long tienCoc = tinhTongCoc(soPhongDatList);
        this.hdong_TienCocjLabel.setText("Tiền cọc: " + String.valueOf(tienCoc));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainCardLayoutDPhong = new javax.swing.JPanel();
        DatPhongPanel = new javax.swing.JPanel();
        loaiPhongjComboBox = new javax.swing.JComboBox<>();
        kieuPhongjComboBox = new javax.swing.JComboBox<>();
        traCuuPhongjButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ngayNhanjCalendarComboBox = new de.wannawork.jcalendar.JCalendarComboBox();
        ngayTrajCalendarComboBox = new de.wannawork.jcalendar.JCalendarComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        danhSachPhongjTable = new javax.swing.JTable();
        DPhong_jButton = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        DatPhongJLabel4 = new javax.swing.JLabel();
        ChinhSachJLabel4 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
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
        jLabel14 = new javax.swing.JLabel();
        hdong_CCCDjLable = new javax.swing.JLabel();
        hdong_NgaySinhjLabel = new javax.swing.JLabel();
        hdong_hoTenjLabel = new javax.swing.JLabel();
        hdong_GioiTinhjLabel = new javax.swing.JLabel();
        hdong_DiaChijLabel = new javax.swing.JLabel();
        hdong_SDTjLabel = new javax.swing.JLabel();
        hdong_suajButton = new javax.swing.JButton();
        hdong_QuayLaijButton = new javax.swing.JButton();
        hdong_XacNhanjButton = new javax.swing.JButton();
        hdong_ngayNhanjLabel = new javax.swing.JLabel();
        hdong_ngayTrajLabel = new javax.swing.JLabel();
        hdong_ngayTrajLabel1 = new javax.swing.JLabel();
        hdong_ngayTrajLabel2 = new javax.swing.JLabel();
        hdong_SoTEjTextField = new javax.swing.JTextField();
        hdong_SoNLTextField = new javax.swing.JTextField();
        hdong_TienCocjLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainCardLayoutDPhong.setPreferredSize(new java.awt.Dimension(700, 600));
        mainCardLayoutDPhong.setLayout(new java.awt.CardLayout());

        DatPhongPanel.setBackground(new java.awt.Color(255, 255, 255));

        loaiPhongjComboBox.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        loaiPhongjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "A", "B", "C" }));

        kieuPhongjComboBox.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        kieuPhongjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Tầng 1", "Tầng 2", "Tầng 3", "Tầng 4" }));

        traCuuPhongjButton.setBackground(new java.awt.Color(0, 51, 153));
        traCuuPhongjButton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        traCuuPhongjButton.setForeground(new java.awt.Color(255, 255, 255));
        traCuuPhongjButton.setText("Tra Cứu");
        traCuuPhongjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                traCuuPhongjButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Loại Phòng");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Tầng");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Ngày Nhận Phòng"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Ngày Trả Phòng");

        danhSachPhongjTable.setBackground(new java.awt.Color(255, 255, 255));
        danhSachPhongjTable.setForeground(new java.awt.Color(0, 0, 0));
        danhSachPhongjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Số Phòng", "Loại Phòng", "Kiểu Phòng", "Giá Phòng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(danhSachPhongjTable);

        DPhong_jButton.setBackground(new java.awt.Color(24, 24, 68));
        DPhong_jButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        DPhong_jButton.setForeground(new java.awt.Color(255, 255, 255));
        DPhong_jButton.setText("Đặt phòng");
        DPhong_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DPhong_jButtonActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(24, 24, 68));
        jLabel23.setText("BRIGHT STAR");
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });

        jPanel25.setBackground(new java.awt.Color(24, 24, 68));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel26.setBackground(new java.awt.Color(77, 13, 195));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        DatPhongJLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        DatPhongJLabel4.setForeground(new java.awt.Color(77, 13, 195));
        DatPhongJLabel4.setText("Đặt phòng");
        DatPhongJLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DatPhongJLabel4MouseClicked(evt);
            }
        });

        ChinhSachJLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        ChinhSachJLabel4.setForeground(new java.awt.Color(24, 24, 68));
        ChinhSachJLabel4.setText("Quay lại");
        ChinhSachJLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ChinhSachJLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChinhSachJLabel4MouseClicked(evt);
            }
        });

        jPanel29.setBackground(new java.awt.Color(24, 24, 68));

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout DatPhongPanelLayout = new javax.swing.GroupLayout(DatPhongPanel);
        DatPhongPanel.setLayout(DatPhongPanelLayout);
        DatPhongPanelLayout.setHorizontalGroup(
            DatPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DatPhongPanelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(DatPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DatPhongPanelLayout.createSequentialGroup()
                        .addGroup(DatPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DatPhongPanelLayout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(47, 47, 47)
                                .addComponent(DatPhongJLabel4)
                                .addGap(30, 30, 30)
                                .addComponent(ChinhSachJLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(DatPhongPanelLayout.createSequentialGroup()
                                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DatPhongPanelLayout.createSequentialGroup()
                        .addGroup(DatPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(DatPhongPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(DPhong_jButton))
                            .addGroup(DatPhongPanelLayout.createSequentialGroup()
                                .addGroup(DatPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4)
                                    .addComponent(ngayNhanjCalendarComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(ngayTrajCalendarComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(DatPhongPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(21, 21, 21)
                                        .addComponent(loaiPhongjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(DatPhongPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(kieuPhongjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(DatPhongPanelLayout.createSequentialGroup()
                                        .addGap(100, 100, 100)
                                        .addComponent(traCuuPhongjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(53, 53, 53))))
        );
        DatPhongPanelLayout.setVerticalGroup(
            DatPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DatPhongPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(DatPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DatPhongJLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChinhSachJLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(DatPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(DatPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DatPhongPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(ngayNhanjCalendarComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(ngayTrajCalendarComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(DatPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loaiPhongjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(DatPhongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kieuPhongjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addComponent(traCuuPhongjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DatPhongPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(DPhong_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        mainCardLayoutDPhong.add(DatPhongPanel, "card2");

        NhapThongTinKHPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(24, 24, 68));
        jLabel9.setText("NHẬP THÔNG TIN CÁ NHÂN");

        them_CCCDjTextField.setBackground(new java.awt.Color(255, 255, 255));

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
        them_ngaySinhjComboBox.setForeground(new java.awt.Color(0, 0, 0));

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
        KH_DatPhongjButton.setText("Tiếp tục");
        KH_DatPhongjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KH_DatPhongjButtonActionPerformed(evt);
            }
        });

        thongBaojLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        thongBaojLable.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout NhapThongTinKHPanelLayout = new javax.swing.GroupLayout(NhapThongTinKHPanel);
        NhapThongTinKHPanel.setLayout(NhapThongTinKHPanelLayout);
        NhapThongTinKHPanelLayout.setHorizontalGroup(
            NhapThongTinKHPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhapThongTinKHPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(NhapThongTinKHPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(NhapThongTinKHPanelLayout.createSequentialGroup()
                        .addComponent(them_CCCDjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(traCuuCCCDjButton))
                    .addComponent(thongBaojLable, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(NhapThongTinKHPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(181, 181, 181)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(NhapThongTinKHPanelLayout.createSequentialGroup()
                        .addComponent(them_hoTenjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(them_ngaySinhjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(NhapThongTinKHPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(167, 167, 167)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(NhapThongTinKHPanelLayout.createSequentialGroup()
                        .addComponent(them_gioiTinhjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)
                        .addComponent(them_diaChijTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(them_SDTjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(129, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NhapThongTinKHPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(KH_HụyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(KH_DatPhongjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        NhapThongTinKHPanelLayout.setVerticalGroup(
            NhapThongTinKHPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhapThongTinKHPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel9)
                .addGap(28, 28, 28)
                .addComponent(jLabel6)
                .addGap(10, 10, 10)
                .addGroup(NhapThongTinKHPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(them_CCCDjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(traCuuCCCDjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                .addGroup(NhapThongTinKHPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(KH_HụyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(KH_DatPhongjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        mainCardLayoutDPhong.add(NhapThongTinKHPanel, "card3");

        LapHopDongPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(24, 24, 68));
        jLabel14.setText("NHẬP THÔNG TIN HỢP ĐỒNG");

        hdong_CCCDjLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hdong_CCCDjLable.setForeground(new java.awt.Color(0, 0, 0));
        hdong_CCCDjLable.setText("CCCD");

        hdong_NgaySinhjLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hdong_NgaySinhjLabel.setForeground(new java.awt.Color(0, 0, 0));
        hdong_NgaySinhjLabel.setText("Ngày Sinh");

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
        hdong_SDTjLabel.setText("Số điện thoại");

        hdong_suajButton.setBackground(new java.awt.Color(24, 24, 68));
        hdong_suajButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hdong_suajButton.setForeground(new java.awt.Color(255, 255, 255));
        hdong_suajButton.setText("Hủy");
        hdong_suajButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hdong_suajButtonActionPerformed(evt);
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
        hdong_ngayNhanjLabel.setText("Ngày nhận phòng");

        hdong_ngayTrajLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hdong_ngayTrajLabel.setForeground(new java.awt.Color(0, 0, 0));
        hdong_ngayTrajLabel.setText("Ngày trả phòng");

        hdong_ngayTrajLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hdong_ngayTrajLabel1.setForeground(new java.awt.Color(0, 0, 0));
        hdong_ngayTrajLabel1.setText("Số người lớn");

        hdong_ngayTrajLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hdong_ngayTrajLabel2.setForeground(new java.awt.Color(0, 0, 0));
        hdong_ngayTrajLabel2.setText("Số Trẻ em");

        hdong_SoTEjTextField.setBackground(new java.awt.Color(255, 255, 255));
        hdong_SoTEjTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hdong_SoTEjTextField.setForeground(new java.awt.Color(0, 0, 0));

        hdong_SoNLTextField.setBackground(new java.awt.Color(255, 255, 255));
        hdong_SoNLTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hdong_SoNLTextField.setForeground(new java.awt.Color(0, 0, 0));

        hdong_TienCocjLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hdong_TienCocjLabel.setForeground(new java.awt.Color(0, 0, 0));
        hdong_TienCocjLabel.setText("Tiền cọc");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("THÔNG TIN ĐẶT PHÒNG");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 153));
        jLabel8.setText("THÔNG TIN KHÁCH HÀNG");

        javax.swing.GroupLayout LapHopDongPanelLayout = new javax.swing.GroupLayout(LapHopDongPanel);
        LapHopDongPanel.setLayout(LapHopDongPanelLayout);
        LapHopDongPanelLayout.setHorizontalGroup(
            LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel14))
            .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(150, 150, 150)
                .addComponent(jLabel8))
            .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                        .addGap(560, 560, 560)
                        .addComponent(hdong_GioiTinhjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                        .addGap(340, 340, 340)
                        .addComponent(hdong_hoTenjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(hdong_ngayNhanjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                        .addGap(560, 560, 560)
                        .addComponent(hdong_SDTjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(hdong_ngayTrajLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                        .addGap(340, 340, 340)
                        .addComponent(hdong_CCCDjLable, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(hdong_TienCocjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(hdong_NgaySinhjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hdong_ngayTrajLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                        .addGap(340, 340, 340)
                        .addComponent(hdong_DiaChijLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(hdong_SoNLTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(hdong_ngayTrajLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(hdong_SoTEjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                .addGap(530, 530, 530)
                .addComponent(hdong_suajButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(hdong_QuayLaijButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(hdong_XacNhanjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        LapHopDongPanelLayout.setVerticalGroup(
            LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel14)
                .addGap(19, 19, 19)
                .addGroup(LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hdong_GioiTinhjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hdong_hoTenjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hdong_ngayNhanjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hdong_SDTjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hdong_ngayTrajLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hdong_CCCDjLable, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hdong_TienCocjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hdong_NgaySinhjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LapHopDongPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(hdong_ngayTrajLabel1))
                    .addComponent(hdong_DiaChijLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(hdong_SoNLTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(hdong_ngayTrajLabel2)
                .addGap(10, 10, 10)
                .addComponent(hdong_SoTEjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(LapHopDongPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hdong_suajButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hdong_QuayLaijButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hdong_XacNhanjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        mainCardLayoutDPhong.add(LapHopDongPanel, "card4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(mainCardLayoutDPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainCardLayoutDPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void KH_HụyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KH_HụyButtonActionPerformed
        // TODO add your handling code here:
        cardlayout.show(mainCardLayoutDPhong, "datPhong");
        inDanhSach();
        this.them_hoTenjTextField.setText("");
        this.them_CCCDjTextField.setText("");
        this.them_diaChijTextField.setText("");
        this.them_SDTjTextField.setText("");
        this.them_ngaySinhjComboBox.setCalendar(null);
        thongBaojLable.setText("");
    }//GEN-LAST:event_KH_HụyButtonActionPerformed

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
                System.out.println(TGNhanPhong);
                System.out.println(TGTraPhong);
            } else {
                capNhatThongTin();
                System.out.println(this.maKHCu);
                System.out.println(TGNhanPhong);
                System.out.println(TGTraPhong);
            }
            cardlayout.show(mainCardLayoutDPhong, "lapHongDong");
            try {
                hienThongTinKH();
            } catch (SQLException ex) {
                Logger.getLogger(DatPhongOnlFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_KH_DatPhongjButtonActionPerformed

    private void hdong_suajButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hdong_suajButtonActionPerformed
        // TODO add your handling code here:
        cardlayout.show(mainCardLayoutDPhong, "datPhong");
        inDanhSach();
        this.them_hoTenjTextField.setText("");
        this.them_CCCDjTextField.setText("");
        this.them_diaChijTextField.setText("");
        this.them_SDTjTextField.setText("");
        this.them_ngaySinhjComboBox.setCalendar(null);
        thongBaojLable.setText("");
        this.hdong_SoTEjTextField.setText("");
        this.hdong_SoNLTextField.setText("");
    }//GEN-LAST:event_hdong_suajButtonActionPerformed

    private void hdong_QuayLaijButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hdong_QuayLaijButtonActionPerformed
        // TODO add your handling code here:
        cardlayout.show(mainCardLayoutDPhong, "nhapThongTin");
    }//GEN-LAST:event_hdong_QuayLaijButtonActionPerformed

    private void hdong_XacNhanjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hdong_XacNhanjButtonActionPerformed
        // TODO add your handling code here:
        int soNL;
        int soTE;
        try {
            soNL = Integer.parseInt(this.hdong_SoNLTextField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên hợp lệ cho số người lớn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return; // hoặc bạn có thể thực hiện các hành động khác để xử lý lỗi
        }

        try {
            soTE = Integer.parseInt(this.hdong_SoTEjTextField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên hợp lệ cho số trẻ em.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return; // hoặc bạn có thể thực hiện các hành động khác để xử lý lỗi
        }
        if (soNL > 0) {
            HopDongDAO hdDAO = new HopDongDAO();
            try {

                System.out.println(soNL);
                System.out.println(soTE);
                long tong = tinhTongCoc(soPhongDatList);
                System.out.println(tong);
                int row = hdDAO.hungia_ThemHopDongVaCTDP(this.maKHCu, TGNhanPhong, TGTraPhong, soNL, soTE, tong, soPhongDatList);
                System.out.println(row);
                if (row > 0) {
//                    int mahopdong = HopDongDAO.getMaHopDongMoiNhat();
//                    System.out.println(mahopdong);
//                    int row1;
//                    for (int maphong : soPhongDatList) {
//                        row1 = ChiTietDatPhongDAO.ThemCTDP(mahopdong, maphong);
//                        if (row1 > 0) {
//                            System.out.println("Thêm thành công phòng " + maphong + " vào hợp đồng " + mahopdong);
//                        } else {
//                            System.out.println("Thêm không thành công phòng " + maphong);
//                        }
//                    }
                    JOptionPane.showMessageDialog(rootPane, "Thêm thành công!", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                    cardlayout.show(mainCardLayoutDPhong, "datPhong");
                    inDanhSach();
                    this.them_hoTenjTextField.setText("");
                    this.them_CCCDjTextField.setText("");
                    this.them_diaChijTextField.setText("");
                    this.them_SDTjTextField.setText("");
                    this.them_ngaySinhjComboBox.setCalendar(null);
                    this.hdong_SoTEjTextField.setText("");
                    this.hdong_SoNLTextField.setText("");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Thêm không thành công. Vui lòng thử lại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(DatPhongOnlFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Số người lớn phải lớn hơn 0!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_hdong_XacNhanjButtonActionPerformed

    private void ChinhSachJLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChinhSachJLabel4MouseClicked
        this.dispose();
        ArrayList<KhuyenMaiModel> khuyenMaiList = KhuyenMaiDAO.getDSKhuyenMai();
        new TrangChuKHFrame(khuyenMaiList).setVisible(true);
    }//GEN-LAST:event_ChinhSachJLabel4MouseClicked

    private void DatPhongJLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatPhongJLabel4MouseClicked
        
    }//GEN-LAST:event_DatPhongJLabel4MouseClicked

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        
    }//GEN-LAST:event_jLabel23MouseClicked

    private void DPhong_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DPhong_jButtonActionPerformed
        // TODO add your handling code here:
        int[] selectedRows = this.danhSachPhongjTable.getSelectedRows();

        try {
            if (selectedRows.length == 0) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dữ liệu cần sửa!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else if (this.danhSachPhongjTable.getRowCount() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Dữ liệu trống!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else {
                // Xử lý từng hàng được chọn
                soPhongDatList.clear(); // Xóa danh sách cũ trước khi thêm mới
                for (int selectedIndex : selectedRows) {
                    Object maPhongObject = danhSachPhongjTable.getValueAt(selectedIndex, 0);
                    Integer soPhongDat = (Integer) maPhongObject;
                    soPhongDatList.add(soPhongDat);
                }

                // Giả sử bạn có hai đối tượng Date
                Date ngayNhan = ngayNhanjCalendarComboBox.getDate();
                Date ngayTra = ngayTrajCalendarComboBox.getDate();

                this.khoangtg = tinhKhoangCach2Ngay(ngayNhan, ngayTra);
                System.out.println("Số ngày giữa hai ngày là: " + khoangtg);

                this.TGNhanPhong = doiNgay(this.ngayNhanjCalendarComboBox.getDate());
                this.TGTraPhong = doiNgay(this.ngayTrajCalendarComboBox.getDate());
                TGNhanPhong = TGNhanPhong + " 14:00:00";
                TGTraPhong = TGTraPhong + " 12:00:00";

                // In danh sách các phòng đã đặt để kiểm tra
                System.out.println(soPhongDatList);

                // Đặt lại các trường đầu vào
                this.them_ngaySinhjComboBox.setCalendar(null);
                them_gioiTinhjComboBox.setSelectedItem(null);
                cardlayout.show(mainCardLayoutDPhong, "nhapThongTin");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Error!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_DPhong_jButtonActionPerformed

    private void traCuuPhongjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_traCuuPhongjButtonActionPerformed
        // Giả sử bạn có các phương thức getDate() để lấy ngày từ các jCalendarComboBox
        Date ngayNhan = ngayNhanjCalendarComboBox.getDate();
        Date ngayTra = ngayTrajCalendarComboBox.getDate();

        // Kiểm tra nếu ngày trả sau ngày nhận
        if (ngayTra.after(ngayNhan)) {
            TraCuuPhong();
        } else {
            // Hiển thị thông báo lỗi cho người dùng
            JOptionPane.showMessageDialog(null, "Ngày trả phải sau ngày nhận.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_traCuuPhongjButtonActionPerformed

    public static void main() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DatPhongOnlFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatPhongOnlFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatPhongOnlFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatPhongOnlFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DatPhongOnlFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ChinhSachJLabel4;
    private javax.swing.JButton DPhong_jButton;
    private javax.swing.JLabel DatPhongJLabel4;
    private javax.swing.JPanel DatPhongPanel;
    private javax.swing.JButton KH_DatPhongjButton;
    private javax.swing.JButton KH_HụyButton;
    private javax.swing.JPanel LapHopDongPanel;
    private javax.swing.JPanel NhapThongTinKHPanel;
    private javax.swing.JTable danhSachPhongjTable;
    private javax.swing.JLabel hdong_CCCDjLable;
    private javax.swing.JLabel hdong_DiaChijLabel;
    private javax.swing.JLabel hdong_GioiTinhjLabel;
    private javax.swing.JLabel hdong_NgaySinhjLabel;
    private javax.swing.JButton hdong_QuayLaijButton;
    private javax.swing.JLabel hdong_SDTjLabel;
    private javax.swing.JTextField hdong_SoNLTextField;
    private javax.swing.JTextField hdong_SoTEjTextField;
    private javax.swing.JLabel hdong_TienCocjLabel;
    private javax.swing.JButton hdong_XacNhanjButton;
    private javax.swing.JLabel hdong_hoTenjLabel;
    private javax.swing.JLabel hdong_ngayNhanjLabel;
    private javax.swing.JLabel hdong_ngayTrajLabel;
    private javax.swing.JLabel hdong_ngayTrajLabel1;
    private javax.swing.JLabel hdong_ngayTrajLabel2;
    private javax.swing.JButton hdong_suajButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> kieuPhongjComboBox;
    private javax.swing.JComboBox<String> loaiPhongjComboBox;
    private javax.swing.JPanel mainCardLayoutDPhong;
    private de.wannawork.jcalendar.JCalendarComboBox ngayNhanjCalendarComboBox;
    private de.wannawork.jcalendar.JCalendarComboBox ngayTrajCalendarComboBox;
    private javax.swing.JTextField them_CCCDjTextField;
    private javax.swing.JTextField them_SDTjTextField;
    private javax.swing.JTextField them_diaChijTextField;
    private javax.swing.JComboBox<String> them_gioiTinhjComboBox;
    private javax.swing.JTextField them_hoTenjTextField;
    private de.wannawork.jcalendar.JCalendarComboBox them_ngaySinhjComboBox;
    private javax.swing.JLabel thongBaojLable;
    private javax.swing.JButton traCuuCCCDjButton;
    private javax.swing.JButton traCuuPhongjButton;
    // End of variables declaration//GEN-END:variables
}
