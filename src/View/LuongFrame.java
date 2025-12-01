/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import DAO.LuongDAO;
import DAO.TrangChuDAO;
import Model.NhanVienModel;
import Model.LuongModel;
import com.formdev.flatlaf.FlatIntelliJLaf;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author phuch
 */
public class LuongFrame extends javax.swing.JFrame {

    private static NhanVienModel currentUser;
    ArrayList<LuongModel> DS_Luong;
    private DefaultTableModel tblLuong_Model;
    private DefaultTableModel tblThongKeLuong_Model;
    private LuongDAO luongDAO;

    public LuongFrame() {
        initComponents();
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        addSVG();
        this.setLocationRelativeTo(null);
        DS_Luong = new ArrayList<>();
        this.luongDAO = new LuongDAO();
        DefaultTableModel DefaultTableModel;
        tblLuong_Model = (DefaultTableModel) this.LuongJTable.getModel();
        LoadDSLuong();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = LocalDate.now().format(formatter);
        NgayHienTai.setText(formattedDate);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MenuPanel17 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        KhachHangLb16 = new javax.swing.JLabel();
        NhanVienLb16 = new javax.swing.JLabel();
        HopDongLb16 = new javax.swing.JLabel();
        KhuyenMaiLb16 = new javax.swing.JLabel();
        TrangBiLb16 = new javax.swing.JLabel();
        HoaDonLb16 = new javax.swing.JLabel();
        ChamCongLb16 = new javax.swing.JLabel();
        DangXuatLb16 = new javax.swing.JLabel();
        phongLb16 = new javax.swing.JLabel();
        luongLb16 = new javax.swing.JLabel();
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
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        TimKiemTheoJCombobox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        NhapThongTinJTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        LuongJTable = new javax.swing.JTable();
        TraCuuButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        NgayHienTai = new javax.swing.JLabel();
        TaoMoiChamCongButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MenuPanel17.setBackground(new java.awt.Color(24, 24, 68));
        MenuPanel17.setPreferredSize(new java.awt.Dimension(200, 600));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(220, 220, 46));
        jLabel22.setText("BRIGHT STAR");
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });

        KhachHangLb16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        KhachHangLb16.setForeground(new java.awt.Color(255, 255, 255));
        KhachHangLb16.setText("Khách hàng");
        KhachHangLb16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KhachHangLb16MouseClicked(evt);
            }
        });

        NhanVienLb16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        NhanVienLb16.setForeground(new java.awt.Color(255, 255, 255));
        NhanVienLb16.setText("Nhân viên");
        NhanVienLb16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NhanVienLb16MouseClicked(evt);
            }
        });

        HopDongLb16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        HopDongLb16.setForeground(new java.awt.Color(255, 255, 255));
        HopDongLb16.setText("Hợp đồng");
        HopDongLb16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HopDongLb16MouseClicked(evt);
            }
        });

        KhuyenMaiLb16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        KhuyenMaiLb16.setForeground(new java.awt.Color(255, 255, 255));
        KhuyenMaiLb16.setText("Khuyến mãi");
        KhuyenMaiLb16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KhuyenMaiLb16MouseClicked(evt);
            }
        });

        TrangBiLb16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        TrangBiLb16.setForeground(new java.awt.Color(255, 255, 255));
        TrangBiLb16.setText("Trang bị");
        TrangBiLb16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TrangBiLb16MouseClicked(evt);
            }
        });

        HoaDonLb16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        HoaDonLb16.setForeground(new java.awt.Color(255, 255, 255));
        HoaDonLb16.setText("Hoá đơn");
        HoaDonLb16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HoaDonLb16MouseClicked(evt);
            }
        });

        ChamCongLb16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ChamCongLb16.setForeground(new java.awt.Color(255, 255, 255));
        ChamCongLb16.setText("Chấm công");
        ChamCongLb16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChamCongLb16MouseClicked(evt);
            }
        });

        DangXuatLb16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        DangXuatLb16.setForeground(new java.awt.Color(48, 90, 184));
        DangXuatLb16.setText("Đăng xuất");
        DangXuatLb16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DangXuatLb16MouseClicked(evt);
            }
        });

        phongLb16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        phongLb16.setForeground(new java.awt.Color(255, 255, 255));
        phongLb16.setText("Phòng");
        phongLb16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                phongLb16MouseClicked(evt);
            }
        });

        luongLb16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        luongLb16.setForeground(new java.awt.Color(220, 220, 46));
        luongLb16.setText("Lương");
        luongLb16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                luongLb16MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout MenuPanel17Layout = new javax.swing.GroupLayout(MenuPanel17);
        MenuPanel17.setLayout(MenuPanel17Layout);
        MenuPanel17Layout.setHorizontalGroup(
            MenuPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuPanel17Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(svgLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(MenuPanel17Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel22))
            .addGroup(MenuPanel17Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(phongLb16))
            .addGroup(MenuPanel17Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(KhachHangLb16))
            .addGroup(MenuPanel17Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(NhanVienLb16))
            .addGroup(MenuPanel17Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(HopDongLb16))
            .addGroup(MenuPanel17Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(KhuyenMaiLb16))
            .addGroup(MenuPanel17Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgTB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(TrangBiLb16))
            .addGroup(MenuPanel17Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(HoaDonLb16))
            .addGroup(MenuPanel17Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(ChamCongLb16))
            .addGroup(MenuPanel17Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(luongLb16))
            .addGroup(MenuPanel17Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(DangXuatLb16))
        );
        MenuPanel17Layout.setVerticalGroup(
            MenuPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuPanel17Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(svgLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel22)
                .addGap(16, 16, 16)
                .addGroup(MenuPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phongLb16))
                .addGap(10, 10, 10)
                .addGroup(MenuPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(KhachHangLb16))
                .addGap(10, 10, 10)
                .addGroup(MenuPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NhanVienLb16))
                .addGap(10, 10, 10)
                .addGroup(MenuPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HopDongLb16))
                .addGap(10, 10, 10)
                .addGroup(MenuPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(KhuyenMaiLb16))
                .addGap(10, 10, 10)
                .addGroup(MenuPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgTB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TrangBiLb16))
                .addGap(10, 10, 10)
                .addGroup(MenuPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HoaDonLb16))
                .addGap(10, 10, 10)
                .addGroup(MenuPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChamCongLb16))
                .addGap(10, 10, 10)
                .addGroup(MenuPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(luongLb16))
                .addGap(20, 20, 20)
                .addComponent(DangXuatLb16))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 600));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Tìm kiếm theo ");

        TimKiemTheoJCombobox.setBackground(new java.awt.Color(255, 255, 255));
        TimKiemTheoJCombobox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TimKiemTheoJCombobox.setForeground(new java.awt.Color(0, 0, 0));
        TimKiemTheoJCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã NV", "Tên NV" }));
        TimKiemTheoJCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimKiemTheoJComboboxActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nhập thông tin");

        NhapThongTinJTextField.setBackground(new java.awt.Color(255, 255, 255));
        NhapThongTinJTextField.setForeground(new java.awt.Color(0, 0, 0));
        NhapThongTinJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NhapThongTinJTextFieldActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        LuongJTable.setBackground(new java.awt.Color(255, 255, 255));
        LuongJTable.setForeground(new java.awt.Color(0, 0, 0));
        LuongJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Tên NV", "Chức vụ", "Lương CB", "Số ngày đi làm ", "Số giờ làm thêm ", "Tổng lương"
            }
        ));
        LuongJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LuongJTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(LuongJTable);

        TraCuuButton.setBackground(new java.awt.Color(24, 24, 68));
        TraCuuButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TraCuuButton.setForeground(new java.awt.Color(255, 255, 255));
        TraCuuButton.setText("Tra Cứu");
        TraCuuButton.setPreferredSize(new java.awt.Dimension(76, 27));
        TraCuuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TraCuuButtonMouseClicked(evt);
            }
        });
        TraCuuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TraCuuButtonActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("NGÀY HIỆN TẠI:");

        NgayHienTai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        NgayHienTai.setForeground(new java.awt.Color(0, 0, 0));

        TaoMoiChamCongButton.setBackground(new java.awt.Color(24, 24, 68));
        TaoMoiChamCongButton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        TaoMoiChamCongButton.setForeground(new java.awt.Color(255, 255, 255));
        TaoMoiChamCongButton.setText("Tạo mới chấm công");
        TaoMoiChamCongButton.setPreferredSize(new java.awt.Dimension(76, 27));
        TaoMoiChamCongButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TaoMoiChamCongButtonMouseClicked(evt);
            }
        });
        TaoMoiChamCongButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TaoMoiChamCongButtonActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(24, 24, 68));
        jLabel8.setText("LƯƠNG");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel8)
                .addGap(41, 41, 41)
                .addComponent(jLabel7)
                .addGap(8, 8, 8)
                .addComponent(NgayHienTai, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addGap(40, 40, 40)
                .addComponent(jLabel3))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(TimKiemTheoJCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(NhapThongTinJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(TraCuuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(TaoMoiChamCongButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(NgayHienTai, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel3)))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TimKiemTheoJCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NhapThongTinJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TraCuuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(TaoMoiChamCongButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MenuPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
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
        
        svgPhong.setColor();
        svgKhach.setColor();
        svgNV.setColor();
        svgHopDong.setColor();
        svgKM.setColor();
        svgTB.setColor();
        svgHoaDon.setColor();
        svgCC.setColor();
        svgL.setYColor();
    }
    private void NhapThongTinJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NhapThongTinJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NhapThongTinJTextFieldActionPerformed

    private void TimKiemTheoJComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimKiemTheoJComboboxActionPerformed

    }//GEN-LAST:event_TimKiemTheoJComboboxActionPerformed
    public void LoadDSLuong() {
        ArrayList<LuongModel> DS_Luong = new ArrayList<>();
        DS_Luong = LuongDAO.getDSLuong();
        DefaultTableModel tblHienThi = (DefaultTableModel) this.LuongJTable.getModel();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        tblHienThi.setRowCount(0);
        for (LuongModel luong : DS_Luong) {
            float luongnv = luongDAO.TinhLuong(luong);
            String formattedLuong = currencyFormat.format(luongnv);
            String formattedLuongCB = currencyFormat.format(luong.getLuongCB());
            tblLuong_Model.addRow(new Object[]{
                luong.getMaNV(),
                luong.getTenNV(),
                luong.getLoaiNV(),
                formattedLuongCB,
                luong.getSNDL(),
                luong.getSGLT(),
                formattedLuong
            });
        }

    }
    private void TraCuuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TraCuuButtonActionPerformed
        String input = this.NhapThongTinJTextField.getText();
        if (input.equals(""))
            LoadDSLuong();
        else
            TraCuuLuong();
    }//GEN-LAST:event_TraCuuButtonActionPerformed

    private void LuongJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LuongJTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LuongJTableMouseClicked

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void KhachHangLb16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KhachHangLb16MouseClicked
        dispose();
        KhachHangFrame.main(currentUser);
    }//GEN-LAST:event_KhachHangLb16MouseClicked

    private void NhanVienLb16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NhanVienLb16MouseClicked
        //        System.out.println(currentUser.toString());
        if (TrangChuDAO.KTLoaiNV(currentUser.getMaNV()) == 1) {
            dispose();
            NhanVienFrame.main(currentUser);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập vào chức năng này", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_NhanVienLb16MouseClicked

    private void ChamCongLb16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChamCongLb16MouseClicked
        if (TrangChuDAO.KTLoaiNV(currentUser.getMaNV()) == 1) {
            dispose();
            ChamCongFrame.main(currentUser);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập vào chức năng này", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_ChamCongLb16MouseClicked

    private void DangXuatLb16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DangXuatLb16MouseClicked
        int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất không?", "Đăng xuất", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            dispose();
            DangNhapFrame.main(null);
        }
    }//GEN-LAST:event_DangXuatLb16MouseClicked

    private void TaoMoiChamCongButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TaoMoiChamCongButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TaoMoiChamCongButtonActionPerformed

    private void TaoMoiChamCongButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaoMoiChamCongButtonMouseClicked
        int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn tạo mới chấm công không?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            try {
                luongDAO.updateSauKhiTinhLuong();
                LoadDSLuong();
            } catch (SQLException ex) {
                Logger.getLogger(LuongFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_TaoMoiChamCongButtonMouseClicked

    private void TraCuuButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TraCuuButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TraCuuButtonMouseClicked

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        // TODO add your handling code here:
        dispose();
        TrangChuNVFrame.main(currentUser);
    }//GEN-LAST:event_jLabel22MouseClicked

    private void phongLb16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_phongLb16MouseClicked
        // TODO add your handling code here:
        dispose();
        PhongFrame.main(currentUser);
    }//GEN-LAST:event_phongLb16MouseClicked

    private void HopDongLb16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HopDongLb16MouseClicked
        // TODO add your handling code here:
        dispose();
        HopDongFrame.main(currentUser);
    }//GEN-LAST:event_HopDongLb16MouseClicked

    private void KhuyenMaiLb16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KhuyenMaiLb16MouseClicked
        // TODO add your handling code here:
        if (TrangChuDAO.KTLoaiNV(currentUser.getMaNV()) == 1) {
            dispose();
            KhuyenMaiFrame.main(currentUser);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập vào chức năng này", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_KhuyenMaiLb16MouseClicked

    private void TrangBiLb16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangBiLb16MouseClicked
        // TODO add your handling code here:
        dispose();
        TrangBiFrame.main(currentUser);
    }//GEN-LAST:event_TrangBiLb16MouseClicked

    private void HoaDonLb16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HoaDonLb16MouseClicked
        // TODO add your handling code here:
        dispose();
        HoaDonFrame.main(currentUser);
    }//GEN-LAST:event_HoaDonLb16MouseClicked

    private void luongLb16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_luongLb16MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_luongLb16MouseClicked

    public void TraCuuLuong() {
        DefaultTableModel tblLuong_Model = (DefaultTableModel) this.LuongJTable.getModel();
        String option = String.valueOf(this.TimKiemTheoJCombobox.getSelectedItem());
        String input = this.NhapThongTinJTextField.getText();
        tblLuong_Model.setRowCount(0);
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        ArrayList<LuongModel> DS_Luong = new ArrayList<LuongModel>();
        DS_Luong = LuongDAO.timNV(option, input);
        for (LuongModel luong : DS_Luong) {
            float luongnv = luongDAO.TinhLuong(luong);
            String formattedLuong = currencyFormat.format(luongnv);
            String formattedLuongCB = currencyFormat.format(luong.getLuongCB());
            tblLuong_Model.addRow(new Object[]{
                luong.getMaNV(),
                luong.getTenNV(),
                luong.getLoaiNV(),
                formattedLuongCB,
                luong.getSNDL(),
                luong.getSGLT(),
                formattedLuong
            });
        }
        if (DS_Luong.size() <= 0) {
            JOptionPane.showMessageDialog(rootPane, "Thông tin không tồn tại. Vui lòng thử lại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(NhanVienModel args) {
        currentUser = args;
        currentUser.setMaNV(args.getMaNV());
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LuongFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ChamCongLb16;
    private javax.swing.JLabel DangXuatLb16;
    private javax.swing.JLabel HoaDonLb16;
    private javax.swing.JLabel HopDongLb16;
    private javax.swing.JLabel KhachHangLb16;
    private javax.swing.JLabel KhuyenMaiLb16;
    private javax.swing.JTable LuongJTable;
    private javax.swing.JPanel MenuPanel17;
    private javax.swing.JLabel NgayHienTai;
    private javax.swing.JLabel NhanVienLb16;
    private javax.swing.JTextField NhapThongTinJTextField;
    private javax.swing.JButton TaoMoiChamCongButton;
    private javax.swing.JComboBox<String> TimKiemTheoJCombobox;
    private javax.swing.JButton TraCuuButton;
    private javax.swing.JLabel TrangBiLb16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel luongLb16;
    private javax.swing.JLabel phongLb16;
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
    // End of variables declaration//GEN-END:variables
}
