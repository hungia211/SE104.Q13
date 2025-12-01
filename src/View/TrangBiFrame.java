package View;

import DAO.TrangBiDAO;
import DAO.TrangChuDAO;
import Model.NhanVienModel;
import Model.TrangBiModel;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class TrangBiFrame extends javax.swing.JFrame {

    private static NhanVienModel currentUser;
    NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi", "VN"));

    public TrangBiFrame() {
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        initComponents();
        this.setLocationRelativeTo(null);
        addSVG(); // Thêm ảnh SVG vào Frame
        setTableTrangBi();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MenuPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PhongLb = new javax.swing.JLabel();
        NhanVienLb4 = new javax.swing.JLabel();
        HopDongLb4 = new javax.swing.JLabel();
        KhuyenMaiLb4 = new javax.swing.JLabel();
        TrangBiLb4 = new javax.swing.JLabel();
        HoaDonLb4 = new javax.swing.JLabel();
        ChamCongLb4 = new javax.swing.JLabel();
        DangXuatLb4 = new javax.swing.JLabel();
        KhachHangLb5 = new javax.swing.JLabel();
        LuongLb5 = new javax.swing.JLabel();
        svgLogo = new Image.SVGImage();
        svgKhach = new Image.SVGImage();
        svgPhong = new Image.SVGImage();
        svgHopDong = new Image.SVGImage();
        svgNV = new Image.SVGImage();
        svgKM = new Image.SVGImage();
        svgTB = new Image.SVGImage();
        svgHoaDon = new Image.SVGImage();
        svgL = new Image.SVGImage();
        svgCC = new Image.SVGImage();
        jPanel2 = new javax.swing.JPanel();
        TrangBiPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        thongtinTF = new javax.swing.JTextField();
        TraCuuButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTrangBi = new javax.swing.JTable();
        ThemButton = new javax.swing.JButton();
        XoaButton = new javax.swing.JButton();
        SuaButton = new javax.swing.JButton();
        filterComboBox = new javax.swing.JComboBox<>();
        ThemTrangBiPanel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        matbTF1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        tentbTF1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        giatbTF1 = new javax.swing.JTextField();
        slSpinner1 = new javax.swing.JSpinner();
        jLabel17 = new javax.swing.JLabel();
        LuuButton1 = new javax.swing.JButton();
        Huy2Button1 = new javax.swing.JButton();
        SuaTrangBiPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        matbTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tentbTF = new javax.swing.JTextField();
        giatbTF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        slSpinner = new javax.swing.JSpinner();
        LuuButton = new javax.swing.JButton();
        Huy2Button = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        slSpinner2 = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MenuPanel4.setBackground(new java.awt.Color(24, 24, 68));
        MenuPanel4.setPreferredSize(new java.awt.Dimension(200, 600));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(220, 220, 46));
        jLabel1.setText("BRIGHT STAR");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        PhongLb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        PhongLb.setForeground(new java.awt.Color(255, 255, 255));
        PhongLb.setText("Phòng");
        PhongLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PhongLbMouseClicked(evt);
            }
        });

        NhanVienLb4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        NhanVienLb4.setForeground(new java.awt.Color(255, 255, 255));
        NhanVienLb4.setText("Nhân viên");
        NhanVienLb4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NhanVienLbMouseClicked(evt);
            }
        });

        HopDongLb4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        HopDongLb4.setForeground(new java.awt.Color(255, 255, 255));
        HopDongLb4.setText("Hợp đồng");
        HopDongLb4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HopDongLb4MouseClicked(evt);
            }
        });

        KhuyenMaiLb4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        KhuyenMaiLb4.setForeground(new java.awt.Color(255, 255, 255));
        KhuyenMaiLb4.setText("Khuyến mãi");
        KhuyenMaiLb4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KhuyenMaiLb4MouseClicked(evt);
            }
        });

        TrangBiLb4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        TrangBiLb4.setForeground(new java.awt.Color(220, 220, 46));
        TrangBiLb4.setText("Trang bị");

        HoaDonLb4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        HoaDonLb4.setForeground(new java.awt.Color(255, 255, 255));
        HoaDonLb4.setText("Hoá đơn");
        HoaDonLb4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HoaDonLb4MouseClicked(evt);
            }
        });

        ChamCongLb4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ChamCongLb4.setForeground(new java.awt.Color(255, 255, 255));
        ChamCongLb4.setText("Chấm công");
        ChamCongLb4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChamCongLbMouseClicked(evt);
            }
        });

        DangXuatLb4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        DangXuatLb4.setForeground(new java.awt.Color(48, 90, 184));
        DangXuatLb4.setText("Đăng xuất");
        DangXuatLb4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DangXuatLbMouseClicked(evt);
            }
        });

        KhachHangLb5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        KhachHangLb5.setForeground(new java.awt.Color(255, 255, 255));
        KhachHangLb5.setText("Khách hàng");
        KhachHangLb5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KhachHangLb5KhachHangLbMouseClicked(evt);
            }
        });

        LuongLb5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        LuongLb5.setForeground(new java.awt.Color(255, 255, 255));
        LuongLb5.setText("Lương");
        LuongLb5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LuongLb5ChamCongLbMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout MenuPanel4Layout = new javax.swing.GroupLayout(MenuPanel4);
        MenuPanel4.setLayout(MenuPanel4Layout);
        MenuPanel4Layout.setHorizontalGroup(
            MenuPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuPanel4Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(svgLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(MenuPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1))
            .addGroup(MenuPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(PhongLb))
            .addGroup(MenuPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(KhachHangLb5))
            .addGroup(MenuPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(NhanVienLb4))
            .addGroup(MenuPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(HopDongLb4))
            .addGroup(MenuPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(KhuyenMaiLb4))
            .addGroup(MenuPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgTB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(TrangBiLb4))
            .addGroup(MenuPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(HoaDonLb4))
            .addGroup(MenuPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(ChamCongLb4))
            .addGroup(MenuPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(LuongLb5))
            .addGroup(MenuPanel4Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(DangXuatLb4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        MenuPanel4Layout.setVerticalGroup(
            MenuPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addGroup(MenuPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PhongLb))
                .addGap(10, 10, 10)
                .addGroup(MenuPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(KhachHangLb5))
                .addGap(10, 10, 10)
                .addGroup(MenuPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NhanVienLb4))
                .addGap(10, 10, 10)
                .addGroup(MenuPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HopDongLb4))
                .addGap(10, 10, 10)
                .addGroup(MenuPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(KhuyenMaiLb4))
                .addGap(10, 10, 10)
                .addGroup(MenuPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgTB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TrangBiLb4))
                .addGap(10, 10, 10)
                .addGroup(MenuPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HoaDonLb4))
                .addGap(10, 10, 10)
                .addGroup(MenuPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChamCongLb4))
                .addGap(10, 10, 10)
                .addGroup(MenuPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LuongLb5))
                .addGap(20, 20, 20)
                .addComponent(DangXuatLb4))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(700, 600));
        jPanel2.setLayout(new java.awt.CardLayout());

        TrangBiPanel.setBackground(new java.awt.Color(255, 255, 255));
        TrangBiPanel.setPreferredSize(new java.awt.Dimension(900, 600));

        jLabel8.setBackground(new java.awt.Color(51, 0, 204));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(19, 19, 79));
        jLabel8.setText("QUẢN LÝ TRANG BỊ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Tìm kiếm theo");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nhập thông tin");

        thongtinTF.setBackground(new java.awt.Color(255, 255, 255));

        TraCuuButton.setBackground(new java.awt.Color(19, 19, 79));
        TraCuuButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TraCuuButton.setForeground(new java.awt.Color(255, 255, 255));
        TraCuuButton.setText("Tra cứu");
        TraCuuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TraCuuButtonActionPerformed(evt);
            }
        });

        tableTrangBi.setBackground(new java.awt.Color(255, 255, 255));
        tableTrangBi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã trang bị", "Tên trang bị", "Giá trang bị", "Số lượng", "Số lượng hỏng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableTrangBi);

        ThemButton.setBackground(new java.awt.Color(19, 19, 79));
        ThemButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ThemButton.setForeground(new java.awt.Color(255, 255, 255));
        ThemButton.setText("Thêm");
        ThemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemButtonActionPerformed(evt);
            }
        });

        XoaButton.setBackground(new java.awt.Color(19, 19, 79));
        XoaButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        XoaButton.setForeground(new java.awt.Color(255, 255, 255));
        XoaButton.setText("Xoá");

        SuaButton.setBackground(new java.awt.Color(19, 19, 79));
        SuaButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        SuaButton.setForeground(new java.awt.Color(255, 255, 255));
        SuaButton.setText("Sửa");
        SuaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuaButtonActionPerformed(evt);
            }
        });

        filterComboBox.setBackground(new java.awt.Color(255, 255, 255));
        filterComboBox.setForeground(new java.awt.Color(0, 0, 0));
        filterComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả trang bị", "Mã trang bị", "Tên trang bị", "Giá trang bị", "Số lượng", "Số lượng hỏng" }));

        javax.swing.GroupLayout TrangBiPanelLayout = new javax.swing.GroupLayout(TrangBiPanel);
        TrangBiPanel.setLayout(TrangBiPanelLayout);
        TrangBiPanelLayout.setHorizontalGroup(
            TrangBiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TrangBiPanelLayout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(jLabel8))
            .addGroup(TrangBiPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel2)
                .addGap(85, 85, 85)
                .addComponent(jLabel3))
            .addGroup(TrangBiPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(filterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(thongtinTF, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(TraCuuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(TrangBiPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(TrangBiPanelLayout.createSequentialGroup()
                .addGap(380, 380, 380)
                .addComponent(ThemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(XoaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(SuaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        TrangBiPanelLayout.setVerticalGroup(
            TrangBiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TrangBiPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel8)
                .addGap(28, 28, 28)
                .addGroup(TrangBiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGroup(TrangBiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thongtinTF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TraCuuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(TrangBiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ThemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(XoaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SuaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.add(TrangBiPanel, "card2");

        ThemTrangBiPanel.setBackground(new java.awt.Color(255, 255, 255));
        ThemTrangBiPanel.setPreferredSize(new java.awt.Dimension(900, 600));

        jLabel12.setBackground(new java.awt.Color(51, 0, 204));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(19, 19, 79));
        jLabel12.setText("THÊM TRANG BỊ");

        jLabel13.setBackground(new java.awt.Color(0, 0, 0));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Thêm trang bị mới vào dữ liệu");

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Mã trang bị");

        matbTF1.setBackground(new java.awt.Color(255, 255, 255));
        matbTF1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Tên trang bị");

        tentbTF1.setBackground(new java.awt.Color(255, 255, 255));
        tentbTF1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel16.setBackground(new java.awt.Color(0, 0, 0));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Giá trang bị");

        giatbTF1.setBackground(new java.awt.Color(255, 255, 255));
        giatbTF1.setForeground(new java.awt.Color(0, 0, 0));

        slSpinner1.setModel(new javax.swing.SpinnerNumberModel());

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Số lượng");

        LuuButton1.setBackground(new java.awt.Color(0, 78, 212));
        LuuButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LuuButton1.setForeground(new java.awt.Color(255, 255, 255));
        LuuButton1.setText("THÊM");
        LuuButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LuuButton1ActionPerformed(evt);
            }
        });

        Huy2Button1.setBackground(new java.awt.Color(0, 78, 212));
        Huy2Button1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Huy2Button1.setForeground(new java.awt.Color(255, 255, 255));
        Huy2Button1.setText("HUỶ");
        Huy2Button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Huy2Button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ThemTrangBiPanelLayout = new javax.swing.GroupLayout(ThemTrangBiPanel);
        ThemTrangBiPanel.setLayout(ThemTrangBiPanelLayout);
        ThemTrangBiPanelLayout.setHorizontalGroup(
            ThemTrangBiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThemTrangBiPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel12))
            .addGroup(ThemTrangBiPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel13))
            .addGroup(ThemTrangBiPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel14))
            .addGroup(ThemTrangBiPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(matbTF1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(ThemTrangBiPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel15)
                .addGap(194, 194, 194)
                .addComponent(jLabel17))
            .addGroup(ThemTrangBiPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(tentbTF1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(slSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(ThemTrangBiPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel16))
            .addGroup(ThemTrangBiPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(giatbTF1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(ThemTrangBiPanelLayout.createSequentialGroup()
                .addGap(520, 520, 520)
                .addComponent(LuuButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(Huy2Button1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        ThemTrangBiPanelLayout.setVerticalGroup(
            ThemTrangBiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThemTrangBiPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel12)
                .addGap(8, 8, 8)
                .addComponent(jLabel13)
                .addGap(40, 40, 40)
                .addComponent(jLabel14)
                .addGap(10, 10, 10)
                .addComponent(matbTF1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(ThemTrangBiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel17))
                .addGap(10, 10, 10)
                .addGroup(ThemTrangBiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tentbTF1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(slSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jLabel16)
                .addGap(10, 10, 10)
                .addComponent(giatbTF1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130)
                .addGroup(ThemTrangBiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LuuButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Huy2Button1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.add(ThemTrangBiPanel, "card2");

        SuaTrangBiPanel.setBackground(new java.awt.Color(255, 255, 255));
        SuaTrangBiPanel.setPreferredSize(new java.awt.Dimension(900, 600));

        jLabel10.setBackground(new java.awt.Color(51, 0, 204));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(19, 19, 79));
        jLabel10.setText("SỬA TRANG BỊ");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Mã trang bị");

        matbTF.setBackground(new java.awt.Color(255, 255, 255));
        matbTF.setForeground(new java.awt.Color(0, 0, 0));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Cập nhật thông tin trang bị đã chọn");

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Tên trang bị");

        tentbTF.setBackground(new java.awt.Color(255, 255, 255));
        tentbTF.setForeground(new java.awt.Color(0, 0, 0));

        giatbTF.setBackground(new java.awt.Color(255, 255, 255));
        giatbTF.setForeground(new java.awt.Color(0, 0, 0));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Giá trang bị");

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Số lượng");

        LuuButton.setBackground(new java.awt.Color(0, 78, 212));
        LuuButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LuuButton.setForeground(new java.awt.Color(255, 255, 255));
        LuuButton.setText("LƯU");
        LuuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LuuButtonActionPerformed(evt);
            }
        });

        Huy2Button.setBackground(new java.awt.Color(0, 78, 212));
        Huy2Button.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Huy2Button.setForeground(new java.awt.Color(255, 255, 255));
        Huy2Button.setText("HUỶ");
        Huy2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Huy2ButtonActionPerformed(evt);
            }
        });

        jLabel18.setBackground(new java.awt.Color(0, 0, 0));
        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Số lượng hỏng");

        javax.swing.GroupLayout SuaTrangBiPanelLayout = new javax.swing.GroupLayout(SuaTrangBiPanel);
        SuaTrangBiPanel.setLayout(SuaTrangBiPanelLayout);
        SuaTrangBiPanelLayout.setHorizontalGroup(
            SuaTrangBiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SuaTrangBiPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel10))
            .addGroup(SuaTrangBiPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel5))
            .addGroup(SuaTrangBiPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel4))
            .addGroup(SuaTrangBiPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(matbTF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(SuaTrangBiPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel6)
                .addGap(194, 194, 194)
                .addComponent(jLabel11))
            .addGroup(SuaTrangBiPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(tentbTF, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(slSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(SuaTrangBiPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel7)
                .addGap(196, 196, 196)
                .addComponent(jLabel18))
            .addGroup(SuaTrangBiPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(giatbTF, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(slSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(SuaTrangBiPanelLayout.createSequentialGroup()
                .addGap(520, 520, 520)
                .addComponent(LuuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(Huy2Button, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        SuaTrangBiPanelLayout.setVerticalGroup(
            SuaTrangBiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SuaTrangBiPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel10)
                .addGap(8, 8, 8)
                .addComponent(jLabel5)
                .addGap(40, 40, 40)
                .addComponent(jLabel4)
                .addGap(10, 10, 10)
                .addComponent(matbTF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(SuaTrangBiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11))
                .addGap(10, 10, 10)
                .addGroup(SuaTrangBiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tentbTF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(slSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(SuaTrangBiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel18))
                .addGap(10, 10, 10)
                .addGroup(SuaTrangBiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(giatbTF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(slSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(130, 130, 130)
                .addGroup(SuaTrangBiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LuuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Huy2Button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.add(SuaTrangBiPanel, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MenuPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Thêm ảnh SVG vào chương trình
    private void addSVG() {
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

        svgPhong.setColor();
        svgKhach.setColor();
        svgNV.setColor();
        svgHopDong.setColor();
        svgKM.setColor();
        svgTB.setYColor();
        svgHoaDon.setColor();
        svgCC.setColor();
        svgL.setColor();
    }

    // Set dữ liệu và listener cho tableTrangBi
    private void setTableTrangBi() {
        DefaultTableModel tableModel = (DefaultTableModel) tableTrangBi.getModel();
        tableModel.setRowCount(0);

        ArrayList<TrangBiModel> listTrangBi = TrangBiDAO.getTrangBiData();

        for (TrangBiModel trangbi : listTrangBi) {
            Vector v = new Vector();
            v.add(trangbi.getMaTB());
            v.add(trangbi.getTenTB());
            v.add(numberFormat.format(trangbi.getGiaTB()) + " VND");
            v.add(trangbi.getSoLuong());
            v.add(trangbi.getSoLuongHong());
            tableModel.addRow(v);
        }

        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tableTrangBi.getModel());
        tableTrangBi.setRowSorter(rowSorter);

        thongtinTF.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = thongtinTF.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = thongtinTF.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }

        });

        for (ActionListener al : SuaButton.getActionListeners()) {
            SuaButton.removeActionListener(al);
        }
        SuaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableTrangBi.getSelectedRow();
                if (selectedRow >= 0) {
                    int modelRow = tableTrangBi.convertRowIndexToModel(selectedRow);
                    TrangBiModel luachon = listTrangBi.get(modelRow);
                    TrangBiModel tb = TrangBiDAO.getTrangBi_MaTB(luachon.getMaTB());
                    TrangBiPanel.setVisible(false);
                    SuaTrangBiPanel.setVisible(true);
                    SuaTrangBiPanel(tb);
                } else {
                    JOptionPane.showMessageDialog(null, "Bạn phải chọn một trang bị", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            }

            private void SuaTrangBiPanel(TrangBiModel tb) {
                matbTF.setText(String.valueOf(tb.getMaTB()));
                matbTF.setEditable(false);
                tentbTF.setText(tb.getTenTB());
                giatbTF.setText(String.valueOf(tb.getGiaTB()));
                slSpinner.setValue(tb.getSoLuong());
            }
        });

        for (ActionListener al : XoaButton.getActionListeners()) {
            XoaButton.removeActionListener(al);
        }
        XoaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableTrangBi.getSelectedRow();
                if (selectedRow >= 0) {
                    int modelRow = tableTrangBi.convertRowIndexToModel(selectedRow);
                    TrangBiModel luachon = listTrangBi.get(modelRow);
                    int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa trang bị này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        boolean check = TrangBiDAO.xoaTrangBi(luachon.getMaTB());
                        if (check) {
                            JOptionPane.showMessageDialog(null, "Xóa trang bị thành công");
                        } else {
                            JOptionPane.showMessageDialog(null, "Xóa trang bị thất bại");
                        }
                        setTableTrangBi();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Bạn phải chọn một trang bị", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }

            }
        });
    }

    // Tìm kiếm dựa vào TraCuuButton
    public void timTrangBi() {
        String selected = filterComboBox.getSelectedItem().toString();
        boolean isAll = false;

        if (selected.equals("Tất cả trang bị")) {
            isAll = true;
        } else if (selected.equals("Mã trang bị")) {
            selected = "MaTB";
        } else if (selected.equals("Tên trang bị")) {
            selected = "TenTB";
        } else if (selected.equals("Giá trang bị")) {
            selected = "GiaTB";
        } else if (selected.equals("Số lượng")) {
            selected = "SoLuong";
        } else if (selected.equals("Số lượng hỏng")) {
            selected = "SLHong";
        }

        if (!isAll) {
            DefaultTableModel tableModel = (DefaultTableModel) tableTrangBi.getModel();
            tableModel.setRowCount(0);
            ArrayList<TrangBiModel> tbList = TrangBiDAO.timTrangBi(selected, thongtinTF.getText());
            for (TrangBiModel trangbi : tbList) {
                Vector v = new Vector();
                v.add(trangbi.getMaTB());
                v.add(trangbi.getTenTB());
                v.add(numberFormat.format(trangbi.getGiaTB()) + " VND");
                v.add(trangbi.getSoLuong());
                v.add(trangbi.getSoLuongHong());
                tableModel.addRow(v);
            }
        } else {
            setTableTrangBi();
        }
    }

    private void ThemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemButtonActionPerformed
        TrangBiPanel.setVisible(false);
        matbTF1.setText(String.valueOf(TrangBiDAO.getMaxRow() + 1));
        ThemTrangBiPanel.setVisible(true);
    }//GEN-LAST:event_ThemButtonActionPerformed

    private void SuaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuaButtonActionPerformed

    }//GEN-LAST:event_SuaButtonActionPerformed

    private void TraCuuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TraCuuButtonActionPerformed
        timTrangBi();
    }//GEN-LAST:event_TraCuuButtonActionPerformed

    private void Huy2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Huy2ButtonActionPerformed
        TrangBiPanel.setVisible(true);
        SuaTrangBiPanel.setVisible(false);
    }//GEN-LAST:event_Huy2ButtonActionPerformed

    private void Huy2Button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Huy2Button1ActionPerformed
        TrangBiPanel.setVisible(true);
        ThemTrangBiPanel.setVisible(false);
        this.matbTF1.setEditable(false);
        tentbTF1.setText("");
        giatbTF1.setText("");
        slSpinner1.setValue(0);
    }//GEN-LAST:event_Huy2Button1ActionPerformed

    // Buuton thêm thông tin trang bị thêm
    private void LuuButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LuuButton1ActionPerformed
        int ma = Integer.parseInt(matbTF1.getText());
        String ten = tentbTF1.getText();
        double gia = Double.parseDouble(giatbTF1.getText());
        int sl = (Integer) slSpinner1.getValue();

        TrangBiModel tb = new TrangBiModel(ma, ten, gia, sl, 0);
        boolean check = TrangBiDAO.themTrangBi(tb);
        if (check) {
            JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại");
        }
        setTableTrangBi();
        TrangBiPanel.setVisible(true);
        ThemTrangBiPanel.setVisible(false);
    }//GEN-LAST:event_LuuButton1ActionPerformed

    // Button lưu thông tin trang bị sửa
    private void LuuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LuuButtonActionPerformed
        int ma = Integer.parseInt(matbTF.getText());
        String ten = tentbTF.getText();
        double gia = Double.parseDouble(giatbTF.getText());
        int sl = (Integer) slSpinner.getValue();
        int slh = (Integer) slSpinner2.getValue();

        TrangBiModel tb = new TrangBiModel(ma, ten, gia, sl, slh);
        boolean check = TrangBiDAO.suaTrangBi(tb);
        if (check) {
            JOptionPane.showMessageDialog(null, "Cập nhật thành công");
            TrangBiPanel.setVisible(true);
            SuaTrangBiPanel.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
        }
        setTableTrangBi();
    }//GEN-LAST:event_LuuButtonActionPerformed

    private void DangXuatLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DangXuatLbMouseClicked
        int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất không?", "Đăng xuất", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            dispose();
            DangNhapFrame.main(null);
        }
    }//GEN-LAST:event_DangXuatLbMouseClicked

    private void ChamCongLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChamCongLbMouseClicked
        if (TrangChuDAO.KTLoaiNV(currentUser.getMaNV()) == 1) {
            dispose();
            ChamCongFrame.main(currentUser);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập vào chức năng này", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_ChamCongLbMouseClicked

    private void NhanVienLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NhanVienLbMouseClicked

        if (TrangChuDAO.KTLoaiNV(currentUser.getMaNV()) == 1) {
            dispose();
            NhanVienFrame.main(currentUser);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập vào chức năng này", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_NhanVienLbMouseClicked

    private void KhachHangLb5KhachHangLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KhachHangLb5KhachHangLbMouseClicked
        // TODO add your handling code here:
        dispose();
        KhachHangFrame.main(currentUser);
    }//GEN-LAST:event_KhachHangLb5KhachHangLbMouseClicked

    private void LuongLb5ChamCongLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuongLb5ChamCongLbMouseClicked
        // TODO add your handling code here:
        if (TrangChuDAO.KTLoaiNV(currentUser.getMaNV()) == 1) {
            dispose();
            LuongFrame.main(currentUser);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập vào chức năng này", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_LuongLb5ChamCongLbMouseClicked

    private void PhongLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PhongLbMouseClicked
        // TODO add your handling code here:
        dispose();
        PhongFrame.main(currentUser);
    }//GEN-LAST:event_PhongLbMouseClicked

    private void HopDongLb4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HopDongLb4MouseClicked
        // TODO add your handling code here:
        dispose();
        HopDongFrame.main(currentUser);
    }//GEN-LAST:event_HopDongLb4MouseClicked

    private void KhuyenMaiLb4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KhuyenMaiLb4MouseClicked
        // TODO add your handling code here:
        if (TrangChuDAO.KTLoaiNV(currentUser.getMaNV()) == 1) {
            dispose();
            KhuyenMaiFrame.main(currentUser);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập vào chức năng này", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_KhuyenMaiLb4MouseClicked

    private void HoaDonLb4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HoaDonLb4MouseClicked
        // TODO add your handling code here:

        dispose();
        HoaDonFrame.main(currentUser);

    }//GEN-LAST:event_HoaDonLb4MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:

        dispose();
        TrangChuNVFrame.main(currentUser);

    }//GEN-LAST:event_jLabel1MouseClicked

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
            java.util.logging.Logger.getLogger(TrangBiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new TrangBiFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ChamCongLb4;
    private javax.swing.JLabel DangXuatLb4;
    private javax.swing.JLabel HoaDonLb4;
    private javax.swing.JLabel HopDongLb4;
    private javax.swing.JButton Huy2Button;
    private javax.swing.JButton Huy2Button1;
    private javax.swing.JLabel KhachHangLb5;
    private javax.swing.JLabel KhuyenMaiLb4;
    private javax.swing.JLabel LuongLb5;
    private javax.swing.JButton LuuButton;
    private javax.swing.JButton LuuButton1;
    private javax.swing.JPanel MenuPanel4;
    private javax.swing.JLabel NhanVienLb4;
    private javax.swing.JLabel PhongLb;
    private javax.swing.JButton SuaButton;
    private javax.swing.JPanel SuaTrangBiPanel;
    private javax.swing.JButton ThemButton;
    private javax.swing.JPanel ThemTrangBiPanel;
    private javax.swing.JButton TraCuuButton;
    private javax.swing.JLabel TrangBiLb4;
    private javax.swing.JPanel TrangBiPanel;
    private javax.swing.JButton XoaButton;
    private javax.swing.JComboBox<String> filterComboBox;
    private javax.swing.JTextField giatbTF;
    private javax.swing.JTextField giatbTF1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField matbTF;
    private javax.swing.JTextField matbTF1;
    private javax.swing.JSpinner slSpinner;
    private javax.swing.JSpinner slSpinner1;
    private javax.swing.JSpinner slSpinner2;
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
    private javax.swing.JTable tableTrangBi;
    private javax.swing.JTextField tentbTF;
    private javax.swing.JTextField tentbTF1;
    private javax.swing.JTextField thongtinTF;
    // End of variables declaration//GEN-END:variables
}
