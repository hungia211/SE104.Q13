package View;

import DAO.HoaDonDAO;
import DAO.TrangChuDAO;
import DAO.excelDAO;
import Model.HoaDonModel;
import Model.KhachHangModel;
import Model.NhanVienModel;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class HoaDonFrame extends javax.swing.JFrame {

    private static NhanVienModel currentUser;
    private DefaultTableModel TableHD;
    HoaDonJDialog dialog = new HoaDonJDialog();
    ArrayList<Vector> DS_HD;

    public HoaDonFrame() throws SQLException {
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        initComponents();
        addSVG();
        this.setLocationRelativeTo(null);
        TableHD = (DefaultTableModel) HoaDonJTable.getModel();
        LoadDanhSachHD();
        ngaySinhjComboBox.setDate(null);
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

        svgPhong.setColor();
        svgKhach.setColor();
        svgNV.setColor();
        svgHopDong.setColor();
        svgKM.setColor();
        svgTB.setColor();
        svgHoaDon.setYColor();
        svgCC.setColor();
        svgL.setColor();
    }

    /* select HOPDONG.MaKH,HOADON.MaKM,HOADON.MaHD,HOADON.MaHopDong ,TAOHOADON.MaNV ,HOADON.TongTien,HOADON.TienHongTB,HOPDONG.HinhThucThue FROM HOADON INNER JOIN TAOHOADON ON HOADON.MaHD=TAOHOADON.MaHD INNER JOIN HOPDONG ON HOADON.MaHopDong=HOPDONG.MaHopDong; */
    public void LoadDanhSachHD() throws SQLException {
        DefaultTableModel tableHD = (DefaultTableModel) HoaDonJTable.getModel();
        DS_HD = HoaDonDAO.getDataHD();
        tableHD.setRowCount(0);
        for (Vector temp : DS_HD) {
            tableHD.addRow(temp);
        }
    }

    public String doiNgay(Date d) {
        java.sql.Date sqlDate = new java.sql.Date(d.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateStr = dateFormat.format(sqlDate);
        return dateStr;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        HoaDonJTable = new javax.swing.JTable();
        NhapThongTinTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TraCuujButton = new javax.swing.JButton();
        ngaySinhjComboBox = new de.wannawork.jcalendar.JCalendarComboBox();
        XoaHetjButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        XuatHDjButton = new javax.swing.JButton();
        MenuPanel = new javax.swing.JPanel();
        trangChujLabel = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 600));

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        HoaDonJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn ", "Mã khách hàng ", "Mã nhân viên", "Ngày lập hóa đơn ", "Tổng tiền "
            }
        ));
        HoaDonJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HoaDonJTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(HoaDonJTable);

        NhapThongTinTextField.setBackground(new java.awt.Color(255, 255, 255));
        NhapThongTinTextField.setPreferredSize(new java.awt.Dimension(200, 40));
        NhapThongTinTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NhapThongTinTextFieldActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Tên khách hàng  ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Chọn ngày lập hóa đơn ");

        TraCuujButton.setBackground(new java.awt.Color(24, 24, 68));
        TraCuujButton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        TraCuujButton.setForeground(new java.awt.Color(255, 255, 255));
        TraCuujButton.setText("Tra cứu");
        TraCuujButton.setPreferredSize(new java.awt.Dimension(132, 40));
        TraCuujButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TraCuujButtonActionPerformed(evt);
            }
        });

        ngaySinhjComboBox.setBackground(new java.awt.Color(255, 255, 255));

        XoaHetjButton1.setBackground(new java.awt.Color(24, 24, 68));
        XoaHetjButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        XoaHetjButton1.setForeground(new java.awt.Color(255, 255, 255));
        XoaHetjButton1.setText("Làm mới");
        XoaHetjButton1.setActionCommand("làm mới");
        XoaHetjButton1.setPreferredSize(new java.awt.Dimension(132, 40));
        XoaHetjButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                XoaHetjButton1MouseClicked(evt);
            }
        });
        XoaHetjButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XoaHetjButton1ActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(24, 24, 68));
        jLabel8.setText("HÓA ĐƠN");

        XuatHDjButton.setBackground(new java.awt.Color(24, 24, 68));
        XuatHDjButton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        XuatHDjButton.setForeground(new java.awt.Color(255, 255, 255));
        XuatHDjButton.setText("Xuất file excel");
        XuatHDjButton.setPreferredSize(new java.awt.Dimension(132, 40));
        XuatHDjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XuatHDjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(ngaySinhjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(236, 236, 236)
                                    .addComponent(TraCuujButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(44, 44, 44)
                                    .addComponent(XoaHetjButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(XuatHDjButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(jLabel3)))
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(NhapThongTinTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(279, 279, 279))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel8)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ngaySinhjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(NhapThongTinTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TraCuujButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(XoaHetjButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(XuatHDjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        MenuPanel.setBackground(new java.awt.Color(24, 24, 68));
        MenuPanel.setPreferredSize(new java.awt.Dimension(200, 600));

        trangChujLabel.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        trangChujLabel.setForeground(new java.awt.Color(220, 220, 46));
        trangChujLabel.setText("BRIGHT STAR");
        trangChujLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trangChujLabelMouseClicked(evt);
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
        HoaDonLb.setForeground(new java.awt.Color(220, 220, 46));
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
                .addComponent(trangChujLabel))
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
                .addComponent(trangChujLabel)
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(MenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void display() {
        int selectedrow = HoaDonJTable.getSelectedRow();
        if (selectedrow != -1) {
            HoaDonModel hd = new HoaDonModel();
            hd = HoaDonDAO.getHDByID((int) HoaDonJTable.getValueAt(selectedrow, 0));
            setCTHD(hd);
            dialog.setVisible(true);

        }

    }

    public void setCTHD(HoaDonModel hd) {
        KhachHangModel kh = HoaDonDAO.getKhachHangByMaHD(hd.getMaHD());
        dialog.MaKhachHangjLabel.setText("Mã khách hàng : " + Integer.toString(kh.getMaKH()));
        dialog.TenKhachHangJLabel.setText("Tên khách hàng : " + kh.getTenKH());
        dialog.MaNVjLabel.setText("Mã nhân viên : " + Integer.toString(HoaDonDAO.getMaNVByMaHD(hd.getMaHD())));
        dialog.TenNVjLabel.setText("Tên nhân viên : " + HoaDonDAO.getTenNVByMaHD(hd.getMaHD()));

        // Hiển thị mã hóa đơn, chuyển đổi giá trị int sang String để hiển thị
        dialog.MaHoaDonjLabel.setText("Mã hóa đơn: " + Integer.toString(hd.getMaHD()));

        // Hiển thị mã khuyến mãi, chuyển đổi giá trị int sang String để hiển thị
        dialog.MaKhuyenMaijLabel.setText("Mã khuyến mãi: " + Integer.toString(hd.getMaKM()));

        // Hiển thị ngày hóa đơn, sử dụng phương thức toString() của LocalDate
        dialog.NgayHoaDonjLabel.setText("Ngày hóa đơn: " + hd.getNgayLapHD().toString());

        // Hiển thị tổng tiền, định dạng số để dễ đọc
        dialog.TienTongjLabel.setText("Tổng tiền: " + String.format("%,.0f", hd.getTongTien()) + " VND");

        // Hiển thị mã hợp đồng, chuyển đổi giá trị int sang String để hiển thị
        dialog.MaHopDongjLabel.setText("Mã hợp đồng: " + Integer.toString(hd.getMaHopDong()));
        dialog.TienHongTBjLabel.setText("Tiền hỏng trang bị : " + String.format("%,.0f", hd.getTienHongTB()) + " VND");
        ArrayList<Vector> cthd = HoaDonDAO.getCTHD(hd.getMaHD());
        System.out.println("Dữ liệu CTHD: " + cthd);
        DefaultTableModel tableModel = (DefaultTableModel) dialog.CTHDjTabel.getModel();
        tableModel.setRowCount(0);
        for (Vector row : cthd) {
            tableModel.addRow(row);
        }
    }
    private void NhapThongTinTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NhapThongTinTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NhapThongTinTextFieldActionPerformed

    private void TraCuujButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TraCuujButtonActionPerformed
        String ngay = (this.ngaySinhjComboBox.getDate() != null) ? doiNgay(this.ngaySinhjComboBox.getDate()) : null;
        String ten = (NhapThongTinTextField.getText() != null && !NhapThongTinTextField.getText().isEmpty()) ? NhapThongTinTextField.getText() : null;
        System.out.println(ngay);
        try {
            DefaultTableModel tableHD = (DefaultTableModel) HoaDonJTable.getModel();
            tableHD.setRowCount(0);
            if (ngay == null && ten == null) {
                LoadDanhSachHD();  // Nếu cả hai trường đều không có dữ liệu, tải lại danh sách hóa đơn
            } else if (ngay == null && ten != null) {
                // Nếu chỉ có tên, gọi hàm tìm kiếm theo tên
                ArrayList<Vector> DSHD = HoaDonDAO.getDSHDTheoTenKhachHang(ten);
                updateTable(DSHD);
            } else if (ngay != null && ten == null) {
                // Nếu chỉ có ngày, gọi hàm tìm kiếm theo ngày
                ArrayList<Vector> DSHD = HoaDonDAO.getDSHDTheoNgay(ngay);
                updateTable(DSHD);
            } else if (ngay != null && ten != null) {
                // Nếu cả ngày và tên đều có, gọi hàm tìm kiếm theo cả ngày và tên
                ArrayList<Vector> DSHD = HoaDonDAO.getDSHDTheoNgayVaTen(ngay, ten);
                updateTable(DSHD);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_TraCuujButtonActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void HoaDonJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HoaDonJTableMouseClicked

        display();
    }//GEN-LAST:event_HoaDonJTableMouseClicked

    private void XoaHetjButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XoaHetjButton1ActionPerformed
        ngaySinhjComboBox.setDate(null);
        NhapThongTinTextField.setText("");
    }//GEN-LAST:event_XoaHetjButton1ActionPerformed

    private void XoaHetjButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XoaHetjButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_XoaHetjButton1MouseClicked

    private void trangChujLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trangChujLabelMouseClicked
        // TODO add your handling code here:
        dispose();
        TrangChuNVFrame.main(currentUser);
    }//GEN-LAST:event_trangChujLabelMouseClicked

    private void KhachHangLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KhachHangLbMouseClicked
        dispose();
        KhachHangFrame.main(currentUser);
    }//GEN-LAST:event_KhachHangLbMouseClicked

    private void NhanVienLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NhanVienLbMouseClicked

        if (TrangChuDAO.KTLoaiNV(currentUser.getMaNV()) == 1) {
            dispose();
            NhanVienFrame.main(currentUser);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập vào chức năng này", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_NhanVienLbMouseClicked

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

    private void ChamCongLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChamCongLbMouseClicked
        System.out.println(currentUser.toString());
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

    private void XuatHDjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XuatHDjButtonActionPerformed
        // TODO add your handling code here:
        try {
            excelDAO dao = new excelDAO();
            dao.exportFileND();
            JOptionPane.showMessageDialog(this, "Xuất file excel thành công", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Xuất file excel thất bại", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        }

    }//GEN-LAST:event_XuatHDjButtonActionPerformed

    private void updateTable(ArrayList<Vector> DSHD) {
        DefaultTableModel tableHD = (DefaultTableModel) HoaDonJTable.getModel();
        tableHD.setRowCount(0);
        for (Vector temp : DSHD) {
            tableHD.addRow(temp);
        }
    }

    public static void main(NhanVienModel args) {

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
            java.util.logging.Logger.getLogger(ChamCongFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChamCongFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChamCongFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChamCongFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        currentUser = args;

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new HoaDonFrame().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(HoaDonFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ChamCongLb;
    private javax.swing.JLabel DangXuatLb;
    private javax.swing.JTable HoaDonJTable;
    private javax.swing.JLabel HoaDonLb;
    private javax.swing.JLabel HopDongLb;
    private javax.swing.JLabel KhachHangLb;
    private javax.swing.JLabel KhuyenMaiLb;
    private javax.swing.JPanel MenuPanel;
    private javax.swing.JLabel NhanVienLb;
    private javax.swing.JTextField NhapThongTinTextField;
    private javax.swing.JButton TraCuujButton;
    private javax.swing.JLabel TrangBiLb;
    private javax.swing.JButton XoaHetjButton1;
    private javax.swing.JButton XuatHDjButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel luongLb;
    private de.wannawork.jcalendar.JCalendarComboBox ngaySinhjComboBox;
    private javax.swing.JLabel phongLb;
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
    private javax.swing.JLabel trangChujLabel;
    // End of variables declaration//GEN-END:variables
}
