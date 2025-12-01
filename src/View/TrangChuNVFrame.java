package View;

import DAO.TrangChuDAO;
import DAO.excelDAO;
import Model.NhanVienModel;
import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.JOptionPane;

public class TrangChuNVFrame extends javax.swing.JFrame {

    private static NhanVienModel currentUser;

    public TrangChuNVFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        addSVG();
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
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
        phongLb = new javax.swing.JLabel();
        luongLb = new javax.swing.JLabel();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        soKHtrongThangjButton = new javax.swing.JButton();
        soHopDongjButton = new javax.swing.JButton();
        doanhThuThangjButton = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 600));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(24, 24, 68));
        jLabel8.setText("TRANG NỘI BỘ KHÁCH SẠN");

        MenuPanel.setBackground(new java.awt.Color(24, 24, 68));
        MenuPanel.setPreferredSize(new java.awt.Dimension(200, 600));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(220, 220, 46));
        jLabel1.setText("BRIGHT STAR");

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

        phongLb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        phongLb.setForeground(new java.awt.Color(255, 255, 255));
        phongLb.setText("Phòng");
        phongLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                phongLbMouseClicked(evt);
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
                .addGap(0, 0, 0)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
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

        jPanel2.setBackground(new java.awt.Color(241, 241, 241));
        jPanel2.setPreferredSize(new java.awt.Dimension(190, 130));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(151, 151, 151));
        jLabel3.setText("74 người");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(151, 151, 151));
        jLabel4.setText("Số Khách hàng trong tháng");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 1, Short.MAX_VALUE)
                .addComponent(jLabel4))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jPanel3.setBackground(new java.awt.Color(241, 241, 241));
        jPanel3.setPreferredSize(new java.awt.Dimension(190, 130));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(151, 151, 151));
        jLabel2.setText("Số hợp đồng trong tháng");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(241, 241, 241));
        jPanel4.setPreferredSize(new java.awt.Dimension(190, 130));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(151, 151, 151));
        jLabel5.setText("Doanh thu theo năm");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(151, 151, 151));
        jLabel6.setText("566.932.100");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel5))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel6)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        soKHtrongThangjButton.setBackground(new java.awt.Color(24, 24, 68));
        soKHtrongThangjButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        soKHtrongThangjButton.setForeground(new java.awt.Color(255, 255, 255));
        soKHtrongThangjButton.setText("Xuất file excel");
        soKHtrongThangjButton.setToolTipText("");
        soKHtrongThangjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soKHtrongThangjButtonActionPerformed(evt);
            }
        });

        soHopDongjButton.setBackground(new java.awt.Color(24, 24, 68));
        soHopDongjButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        soHopDongjButton.setForeground(new java.awt.Color(255, 255, 255));
        soHopDongjButton.setText("Xuất file excel");
        soHopDongjButton.setToolTipText("");
        soHopDongjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soHopDongjButtonActionPerformed(evt);
            }
        });

        doanhThuThangjButton.setBackground(new java.awt.Color(24, 24, 68));
        doanhThuThangjButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        doanhThuThangjButton.setForeground(new java.awt.Color(255, 255, 255));
        doanhThuThangjButton.setText("Xuất file excel");
        doanhThuThangjButton.setToolTipText("");
        doanhThuThangjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doanhThuThangjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(MenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(145, 145, 145))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(soKHtrongThangjButton)
                        .addGap(113, 113, 113)
                        .addComponent(soHopDongjButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(doanhThuThangjButton)
                        .addGap(55, 55, 55))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(MenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel8)
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(soKHtrongThangjButton)
                    .addComponent(soHopDongjButton)
                    .addComponent(doanhThuThangjButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);

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
        svgL.setColor();
    }
    private void KhachHangLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KhachHangLbMouseClicked
        dispose();
        KhachHangFrame.main(currentUser);
    }//GEN-LAST:event_KhachHangLbMouseClicked

    private void NhanVienLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NhanVienLbMouseClicked
        System.out.println(currentUser.toString());
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


    private void phongLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_phongLbMouseClicked
        // TODO add your handling code here:
        dispose();
        PhongFrame.main(currentUser);
    }//GEN-LAST:event_phongLbMouseClicked

    private void luongLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_luongLbMouseClicked
        if (TrangChuDAO.KTLoaiNV(currentUser.getMaNV()) == 1) {
            dispose();
            LuongFrame.main(currentUser);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập vào chức năng này", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_luongLbMouseClicked

    private void KhuyenMaiLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KhuyenMaiLbMouseClicked
        // TODO add your handling code here:
        if (TrangChuDAO.KTLoaiNV(currentUser.getMaNV()) == 1) {
            dispose();
            KhuyenMaiFrame.main(currentUser);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập vào chức năng này", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_KhuyenMaiLbMouseClicked

    private void HopDongLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HopDongLbMouseClicked
        // TODO add your handling code here:
        dispose();
        HopDongFrame.main(currentUser);
    }//GEN-LAST:event_HopDongLbMouseClicked

    private void HoaDonLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HoaDonLbMouseClicked
        // TODO add your handling code here:
        dispose();
        HoaDonFrame.main(currentUser);
    }//GEN-LAST:event_HoaDonLbMouseClicked

    private void TrangBiLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangBiLbMouseClicked
        // TODO add your handling code here:
        dispose();
        TrangBiFrame.main(currentUser);
    }//GEN-LAST:event_TrangBiLbMouseClicked

    private void soKHtrongThangjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soKHtrongThangjButtonActionPerformed

        try {
            excelDAO dao = new excelDAO();
            dao.exportFileSoKHTrongThang();
            JOptionPane.showMessageDialog(this, "Xuất file excel thành công", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Xuất file excel thất bại", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_soKHtrongThangjButtonActionPerformed

    private void soHopDongjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soHopDongjButtonActionPerformed
        // TODO add your handling code here:
        try {
            excelDAO dao = new excelDAO();
            dao.exportFileHTThuePhong();
            JOptionPane.showMessageDialog(this, "Xuất file excel thành công", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Xuất file excel thất bại", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_soHopDongjButtonActionPerformed

    private void doanhThuThangjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doanhThuThangjButtonActionPerformed
        // TODO add your handling code here:
        try {
            excelDAO dao = new excelDAO();
            dao.exportFileDoanhThuTheoNam();
            JOptionPane.showMessageDialog(this, "Xuất file excel thành công", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Xuất file excel thất bại", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        }
        
    }//GEN-LAST:event_doanhThuThangjButtonActionPerformed

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

        java.awt.EventQueue.invokeLater(() -> {
            new TrangChuNVFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ChamCongLb;
    private javax.swing.JLabel DangXuatLb;
    private javax.swing.JLabel HoaDonLb;
    private javax.swing.JLabel HopDongLb;
    private javax.swing.JLabel KhachHangLb;
    private javax.swing.JLabel KhuyenMaiLb;
    private javax.swing.JPanel MenuPanel;
    private javax.swing.JLabel NhanVienLb;
    private javax.swing.JLabel TrangBiLb;
    private javax.swing.JButton doanhThuThangjButton;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel luongLb;
    private javax.swing.JLabel phongLb;
    private javax.swing.JButton soHopDongjButton;
    private javax.swing.JButton soKHtrongThangjButton;
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
