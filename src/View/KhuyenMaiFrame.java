package View;

import DAO.KhuyenMaiDAO;
import DAO.TrangChuDAO;
import Model.KhuyenMaiModel;
import Model.NhanVienModel;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.CardLayout;
import java.awt.Component;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class KhuyenMaiFrame extends javax.swing.JFrame {

    private static NhanVienModel currentUser;
    CardLayout cardLayout;
    int maKMCu;
    private DefaultTableModel TableKM = new DefaultTableModel() {
        public boolean isCellEdittable(int row, int column) {
            return false;
        }
    };

    public KhuyenMaiFrame() {
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        initComponents();
        this.setLocationRelativeTo(null);
        addSVG();
        khoiTaoBang();
        inDanhSach();

        // Tạo các luồng sự kiện chuyển Panel
        cardLayout = (CardLayout) (mainCardLayoutKM.getLayout());
        mainCardLayoutKM.add(KhuyenMaiPanel, "khuyenMai");
        cardLayout.show(mainCardLayoutKM, "khuyenMai");
        mainCardLayoutKM.add(SuaKhuyenMaiPanel, "suaKhuyenMai");
        mainCardLayoutKM.add(ThemKhuyenMaiPanel, "themKhuyenMai");
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
        svgKM.setYColor();
        svgTB.setColor();
        svgHoaDon.setColor();
        svgCC.setColor();
        svgL.setColor();
    }

    public void khoiTaoBang() {
        TableKM = new DefaultTableModel();

        TableKM.addColumn("Mã KM");
        TableKM.addColumn("Tên KM");
        TableKM.addColumn("Mô tả KM");
        TableKM.addColumn("Ngày bắt đầu");
        TableKM.addColumn("Ngày kết thúc");
        TableKM.addColumn("Phần trăm KM");

        DanhSachKhuyenMaiJTable.setModel(TableKM);
    }

    public void inDanhSach() {
        ArrayList<KhuyenMaiModel> DSKM = KhuyenMaiDAO.getDSKhuyenMai();
        TableKM.setRowCount(0);
        for (KhuyenMaiModel kmm : DSKM) {
            TableKM.addRow(new Object[]{kmm.getMaKM(), kmm.getTenKM(), kmm.getMoTaKM(), kmm.getNgayBatDau(), kmm.getNgayKetThuc(), (int) (kmm.getPhanTramKM() * 100) + "%"});
        }

    }

    public void TraCuuKM() {
        String luachon = (String) this.TimKiemJComboBox.getSelectedItem();
        String input = this.NhapThongTinJTextField.getText().toLowerCase();
        TableKM.setRowCount(0);
        ArrayList<KhuyenMaiModel> DSKM = KhuyenMaiDAO.TimKM(luachon, input);

        for (KhuyenMaiModel kmm : DSKM) {
            TableKM.addRow(new Object[]{kmm.getMaKM(), kmm.getTenKM(), kmm.getMoTaKM(), kmm.getNgayBatDau(), kmm.getNgayKetThuc(), kmm.getPhanTramKM()});
        }

        if (DSKM.size() <= 0) {
            JOptionPane.showMessageDialog(rootPane, "Thông tin không tồn tại. Vui lòng thử lại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void hienThongTinKM() {
        try {
            KhuyenMaiModel km = KhuyenMaiDAO.getKMtheoMaKM(maKMCu);

            if (km != null) {
                sua_TenKhuyenMaijTextField.setText(km.getTenKM());
                sua_MoTaKhuyenMaijTextField.setText(km.getMoTaKM());

                Date NgayBatDau = GregorianCalendar.from(km.getNgayBatDau().atStartOfDay(ZoneId.systemDefault())).getTime();
                sua_NgayBatDaujCalendarComboBox.setDate(NgayBatDau);

                Date NgayKetThuc = GregorianCalendar.from(km.getNgayKetThuc().atStartOfDay(ZoneId.systemDefault())).getTime();
                sua_NgayKetThucjCalendarComboBox.setDate(NgayKetThuc);

                //ngaySinhjLabel.setText(("Ngày sinh: " + kh.getNgaySinh().toString()).formatted(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                sua_PhanTramKMjTextField.setText(String.valueOf((int) (km.getPhanTramKM() * 100)));

            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin khách hàng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } catch (java.sql.SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lấy thông tin khách hàng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
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
        mainCardLayoutKM = new javax.swing.JPanel();
        KhuyenMaiPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        TimKiemJComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        NhapThongTinJTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TraCuuJButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        DanhSachKhuyenMaiJTable = new javax.swing.JTable();
        SuaJButton1 = new javax.swing.JButton();
        XoaJButton = new javax.swing.JButton();
        ThemJButton = new javax.swing.JButton();
        ThemKhuyenMaiPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        them_TenKhuyenMaijTextField = new javax.swing.JTextField();
        them_PhanTramKMjTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        them_NgayBatDaujCalendarComboBox = new de.wannawork.jcalendar.JCalendarComboBox();
        jLabel12 = new javax.swing.JLabel();
        them_NgayKetThucjCalendarComboBox = new de.wannawork.jcalendar.JCalendarComboBox();
        jLabel13 = new javax.swing.JLabel();
        them_MoTaKMjTextField = new javax.swing.JTextField();
        them_HuyjButton = new javax.swing.JButton();
        Them_luuJButton1 = new javax.swing.JButton();
        SuaKhuyenMaiPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        hoTenjLable = new javax.swing.JLabel();
        sua_TenKhuyenMaijTextField = new javax.swing.JTextField();
        diaChijLabel = new javax.swing.JLabel();
        sua_PhanTramKMjTextField = new javax.swing.JTextField();
        ngaySinhjLabel = new javax.swing.JLabel();
        sua_NgayBatDaujCalendarComboBox = new de.wannawork.jcalendar.JCalendarComboBox();
        ngaySinhjLabel1 = new javax.swing.JLabel();
        sua_NgayKetThucjCalendarComboBox = new de.wannawork.jcalendar.JCalendarComboBox();
        CCCDjLabel = new javax.swing.JLabel();
        sua_MoTaKhuyenMaijTextField = new javax.swing.JTextField();
        sua_luujButton = new javax.swing.JButton();
        sua_huyjButton = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        KhuyenMaiLb.setForeground(new java.awt.Color(220, 220, 46));
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

        mainCardLayoutKM.setPreferredSize(new java.awt.Dimension(700, 600));
        mainCardLayoutKM.setLayout(new java.awt.CardLayout());

        KhuyenMaiPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(24, 24, 68));
        jLabel8.setText("KHUYẾN MÃI");

        TimKiemJComboBox.setBackground(new java.awt.Color(255, 255, 255));
        TimKiemJComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TimKiemJComboBox.setForeground(new java.awt.Color(0, 0, 0));
        TimKiemJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã KM", "Tên KM", "Ngày Bắt Đầu", "Ngày Kết Thúc", " ", " " }));
        TimKiemJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimKiemJComboBoxActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Tìm kiếm theo");

        NhapThongTinJTextField.setBackground(new java.awt.Color(255, 255, 255));
        NhapThongTinJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NhapThongTinJTextFieldActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nhập thông tin");

        TraCuuJButton.setBackground(new java.awt.Color(19, 19, 79));
        TraCuuJButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TraCuuJButton.setForeground(new java.awt.Color(255, 255, 255));
        TraCuuJButton.setText("Tra cứu");
        TraCuuJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TraCuuJButtonActionPerformed(evt);
            }
        });

        DanhSachKhuyenMaiJTable.setBackground(new java.awt.Color(255, 255, 255));
        DanhSachKhuyenMaiJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KM", "Tên KM", "Mô tả KM", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Phần Trăm KM"
            }
        ));
        jScrollPane1.setViewportView(DanhSachKhuyenMaiJTable);

        SuaJButton1.setBackground(new java.awt.Color(19, 19, 79));
        SuaJButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        SuaJButton1.setForeground(new java.awt.Color(255, 255, 255));
        SuaJButton1.setText("Sửa");
        SuaJButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuaJButton1ActionPerformed(evt);
            }
        });

        XoaJButton.setBackground(new java.awt.Color(19, 19, 79));
        XoaJButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        XoaJButton.setForeground(new java.awt.Color(255, 255, 255));
        XoaJButton.setText("Xóa");
        XoaJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XoaJButtonActionPerformed(evt);
            }
        });

        ThemJButton.setBackground(new java.awt.Color(19, 19, 79));
        ThemJButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ThemJButton.setForeground(new java.awt.Color(255, 255, 255));
        ThemJButton.setText("Thêm");
        ThemJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout KhuyenMaiPanelLayout = new javax.swing.GroupLayout(KhuyenMaiPanel);
        KhuyenMaiPanel.setLayout(KhuyenMaiPanelLayout);
        KhuyenMaiPanelLayout.setHorizontalGroup(
            KhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KhuyenMaiPanelLayout.createSequentialGroup()
                .addGap(256, 256, 256)
                .addComponent(jLabel8))
            .addGroup(KhuyenMaiPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(115, 115, 115)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(KhuyenMaiPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(TimKiemJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(NhapThongTinJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(TraCuuJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(KhuyenMaiPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(KhuyenMaiPanelLayout.createSequentialGroup()
                .addGap(390, 390, 390)
                .addComponent(ThemJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(XoaJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(SuaJButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        KhuyenMaiPanelLayout.setVerticalGroup(
            KhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KhuyenMaiPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel8)
                .addGap(30, 30, 30)
                .addGroup(KhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(6, 6, 6)
                .addGroup(KhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TimKiemJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(KhuyenMaiPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(NhapThongTinJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(KhuyenMaiPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(TraCuuJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(KhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ThemJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(XoaJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SuaJButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        mainCardLayoutKM.add(KhuyenMaiPanel, "card2");

        ThemKhuyenMaiPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(24, 24, 68));
        jLabel10.setText("KHUYẾN MÃI");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Nhập thông tin khách hàng cần thêm");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Tên khuyến mãi");

        them_TenKhuyenMaijTextField.setBackground(new java.awt.Color(255, 255, 255));

        them_PhanTramKMjTextField.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Phần trăm khuyến mãi");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Ngày bắt đầu");

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Ngày kết thúc");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Mô tả khuyến mãi ");

        them_MoTaKMjTextField.setBackground(new java.awt.Color(255, 255, 255));

        them_HuyjButton.setBackground(new java.awt.Color(24, 24, 68));
        them_HuyjButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        them_HuyjButton.setForeground(new java.awt.Color(255, 255, 255));
        them_HuyjButton.setText("Hủy");
        them_HuyjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                them_HuyjButtonActionPerformed(evt);
            }
        });

        Them_luuJButton1.setBackground(new java.awt.Color(24, 24, 68));
        Them_luuJButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Them_luuJButton1.setForeground(new java.awt.Color(255, 255, 255));
        Them_luuJButton1.setText("Thêm");
        Them_luuJButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Them_luuJButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ThemKhuyenMaiPanelLayout = new javax.swing.GroupLayout(ThemKhuyenMaiPanel);
        ThemKhuyenMaiPanel.setLayout(ThemKhuyenMaiPanelLayout);
        ThemKhuyenMaiPanelLayout.setHorizontalGroup(
            ThemKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThemKhuyenMaiPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(ThemKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13)
                    .addGroup(ThemKhuyenMaiPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel5))
                    .addGroup(ThemKhuyenMaiPanelLayout.createSequentialGroup()
                        .addGroup(ThemKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ThemKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(them_NgayBatDaujCalendarComboBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(them_TenKhuyenMaijTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                            .addComponent(jLabel7))
                        .addGap(130, 130, 130)
                        .addGroup(ThemKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11)
                            .addComponent(them_PhanTramKMjTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jLabel12)
                            .addComponent(them_NgayKetThucjCalendarComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(them_MoTaKMjTextField))
                .addContainerGap(135, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThemKhuyenMaiPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Them_luuJButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(them_HuyjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        ThemKhuyenMaiPanelLayout.setVerticalGroup(
            ThemKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThemKhuyenMaiPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(ThemKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(ThemKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ThemKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(them_TenKhuyenMaijTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(them_PhanTramKMjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(ThemKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ThemKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(them_NgayBatDaujCalendarComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(them_NgayKetThucjCalendarComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(them_MoTaKMjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addGroup(ThemKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(them_HuyjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Them_luuJButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        mainCardLayoutKM.add(ThemKhuyenMaiPanel, "card4");

        SuaKhuyenMaiPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(24, 24, 68));
        jLabel9.setText("KHUYẾN MÃI ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nhập thông tin cần sửa");

        hoTenjLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hoTenjLable.setForeground(new java.awt.Color(0, 0, 0));
        hoTenjLable.setText("Tên khuyến mãi ");

        sua_TenKhuyenMaijTextField.setBackground(new java.awt.Color(255, 255, 255));
        sua_TenKhuyenMaijTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sua_TenKhuyenMaijTextFieldActionPerformed(evt);
            }
        });

        diaChijLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        diaChijLabel.setForeground(new java.awt.Color(0, 0, 0));
        diaChijLabel.setText("Phần trăm khuyến mãi ");

        sua_PhanTramKMjTextField.setBackground(new java.awt.Color(255, 255, 255));
        sua_PhanTramKMjTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sua_PhanTramKMjTextFieldActionPerformed(evt);
            }
        });

        ngaySinhjLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ngaySinhjLabel.setForeground(new java.awt.Color(0, 0, 0));
        ngaySinhjLabel.setText("Ngày bắt đầu ");

        ngaySinhjLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ngaySinhjLabel1.setForeground(new java.awt.Color(0, 0, 0));
        ngaySinhjLabel1.setText("Ngày kết thúc ");

        CCCDjLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CCCDjLabel.setForeground(new java.awt.Color(0, 0, 0));
        CCCDjLabel.setText("Mô tả khuyến mãi ");

        sua_MoTaKhuyenMaijTextField.setBackground(new java.awt.Color(255, 255, 255));
        sua_MoTaKhuyenMaijTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sua_MoTaKhuyenMaijTextFieldActionPerformed(evt);
            }
        });

        sua_luujButton.setBackground(new java.awt.Color(24, 24, 68));
        sua_luujButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sua_luujButton.setForeground(new java.awt.Color(255, 255, 255));
        sua_luujButton.setText("Lưu");
        sua_luujButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sua_luujButtonActionPerformed(evt);
            }
        });

        sua_huyjButton.setBackground(new java.awt.Color(24, 24, 68));
        sua_huyjButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sua_huyjButton.setForeground(new java.awt.Color(255, 255, 255));
        sua_huyjButton.setText("Hủy");
        sua_huyjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sua_huyjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SuaKhuyenMaiPanelLayout = new javax.swing.GroupLayout(SuaKhuyenMaiPanel);
        SuaKhuyenMaiPanel.setLayout(SuaKhuyenMaiPanelLayout);
        SuaKhuyenMaiPanelLayout.setHorizontalGroup(
            SuaKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SuaKhuyenMaiPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(SuaKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SuaKhuyenMaiPanelLayout.createSequentialGroup()
                        .addGroup(SuaKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sua_TenKhuyenMaijTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(SuaKhuyenMaiPanelLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SuaKhuyenMaiPanelLayout.createSequentialGroup()
                        .addGroup(SuaKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hoTenjLable, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sua_NgayBatDaujCalendarComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ngaySinhjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(130, 130, 130)
                        .addGroup(SuaKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SuaKhuyenMaiPanelLayout.createSequentialGroup()
                                .addGroup(SuaKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sua_PhanTramKMjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(diaChijLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(SuaKhuyenMaiPanelLayout.createSequentialGroup()
                                .addGroup(SuaKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ngaySinhjLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(SuaKhuyenMaiPanelLayout.createSequentialGroup()
                                        .addComponent(sua_NgayKetThucjCalendarComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())))
                    .addGroup(SuaKhuyenMaiPanelLayout.createSequentialGroup()
                        .addGroup(SuaKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CCCDjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sua_MoTaKhuyenMaijTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SuaKhuyenMaiPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sua_luujButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sua_huyjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        SuaKhuyenMaiPanelLayout.setVerticalGroup(
            SuaKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SuaKhuyenMaiPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(SuaKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(SuaKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hoTenjLable, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(diaChijLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SuaKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sua_TenKhuyenMaijTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sua_PhanTramKMjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(SuaKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ngaySinhjLabel)
                    .addComponent(ngaySinhjLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SuaKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sua_NgayBatDaujCalendarComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sua_NgayKetThucjCalendarComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addComponent(CCCDjLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sua_MoTaKhuyenMaijTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addGroup(SuaKhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sua_luujButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sua_huyjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        mainCardLayoutKM.add(SuaKhuyenMaiPanel, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainCardLayoutKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainCardLayoutKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        cardLayout.show(mainCardLayoutKM, "khuyenMai");
        inDanhSach();
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

    private void TimKiemJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimKiemJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TimKiemJComboBoxActionPerformed

    private void NhapThongTinJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NhapThongTinJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NhapThongTinJTextFieldActionPerformed

    private void TraCuuJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TraCuuJButtonActionPerformed
        // TODO add your handling code here:
        String input = this.NhapThongTinJTextField.getText();
        if (input.equals("")) {
            inDanhSach();
        } else {
            TraCuuKM();
        }
    }//GEN-LAST:event_TraCuuJButtonActionPerformed

    private void XoaJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XoaJButtonActionPerformed
        // TODO add your handling code here:
        int removeIndex = this.DanhSachKhuyenMaiJTable.getSelectedRow();
        try {
            if (removeIndex == -1) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dữ liệu cần xóa!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else if (this.DanhSachKhuyenMaiJTable.getRowCount() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Dữ liệu trống!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else {
                Object maKMObject = this.DanhSachKhuyenMaiJTable.getValueAt(removeIndex, 0);
                int maKM = (Integer) maKMObject;
                int choice = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn xóa không ?", "Xác nhận xóa", JOptionPane.OK_CANCEL_OPTION);
                if (choice == JOptionPane.OK_OPTION) {
                    int row = KhuyenMaiDAO.XoaKM(maKM);
                    if (row > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Xóa thành công!", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Xóa không thành công. Vui lòng thử lại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Đã hủy xóa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                inDanhSach();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Lỗi!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_XoaJButtonActionPerformed

    private void ThemJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemJButtonActionPerformed
        // TODO add your handling code here:
        cardLayout.show(mainCardLayoutKM, "themKhuyenMai");
    }//GEN-LAST:event_ThemJButtonActionPerformed

    private void SuaJButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuaJButton1ActionPerformed
        // TODO add your handling code here:
        int selectedIndex = this.DanhSachKhuyenMaiJTable.getSelectedRow();

        try {
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dữ liệu cần sửa!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else if (this.DanhSachKhuyenMaiJTable.getRowCount() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Dữ liệu trống!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else {
                Object maKMObject = this.DanhSachKhuyenMaiJTable.getValueAt(selectedIndex, 0);
                this.maKMCu = (Integer) maKMObject;
                cardLayout.show(mainCardLayoutKM, "suaKhuyenMai");
                hienThongTinKM();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Error!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_SuaJButton1ActionPerformed

    private void sua_TenKhuyenMaijTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sua_TenKhuyenMaijTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sua_TenKhuyenMaijTextFieldActionPerformed

    private void sua_PhanTramKMjTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sua_PhanTramKMjTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sua_PhanTramKMjTextFieldActionPerformed

    private void sua_MoTaKhuyenMaijTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sua_MoTaKhuyenMaijTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sua_MoTaKhuyenMaijTextFieldActionPerformed

    private void sua_luujButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sua_luujButtonActionPerformed
        // Lấy thông tin nhập liệu mới
        String TenKM = this.sua_TenKhuyenMaijTextField.getText();
        String MoTaKM = this.sua_MoTaKhuyenMaijTextField.getText();
        java.sql.Date sqldate1 = new java.sql.Date(this.sua_NgayBatDaujCalendarComboBox.getDate().getTime());
        java.sql.Date sqldate2 = new java.sql.Date(this.sua_NgayKetThucjCalendarComboBox.getDate().getTime());
        Double PhanTramKM = Double.parseDouble(this.sua_PhanTramKMjTextField.getText());
        try {

            KhuyenMaiModel kmCu = KhuyenMaiDAO.getKMtheoMaKM(maKMCu);

            if (!TenKM.isEmpty()) {
                kmCu.setTenKM(TenKM);
            }
            if (!MoTaKM.isEmpty()) {
                kmCu.setMoTaKM(MoTaKM);
            }
            if (sqldate1 != null) {
                kmCu.setNgayBatDau(sqldate1.toLocalDate());
            }
            if (sqldate2 != null) {
                kmCu.setNgayKetThuc(sqldate2.toLocalDate());
            }
            kmCu.setPhanTramKM(PhanTramKM);
            // Thực hiện cập nhật trong cơ sở dữ liệu
            if (KhuyenMaiDAO.CapNhatKhuyenMai(maKMCu, kmCu)) {
                JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công!", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                this.sua_TenKhuyenMaijTextField.setText("");
                this.sua_MoTaKhuyenMaijTextField.setText("");
                this.sua_NgayBatDaujCalendarComboBox.setCalendar(null);
                this.sua_NgayKetThucjCalendarComboBox.setCalendar(null);
                this.sua_PhanTramKMjTextField.setText("");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Cập nhật không thành công. Vui lòng thử lại!",
                        "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Lỗi khi thực hiện truy vấn SQL!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Lỗi: Tham chiếu null!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Lỗi không xác định!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        cardLayout.show(mainCardLayoutKM, "khuyenMai");
        inDanhSach();
    }//GEN-LAST:event_sua_luujButtonActionPerformed

    private void sua_huyjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sua_huyjButtonActionPerformed
        cardLayout.show(mainCardLayoutKM, "khuyenMai");
        inDanhSach();
        this.sua_TenKhuyenMaijTextField.setText("");
        this.sua_MoTaKhuyenMaijTextField.setText("");
        this.sua_NgayBatDaujCalendarComboBox.setCalendar(null);
        this.sua_NgayKetThucjCalendarComboBox.setCalendar(null);
        this.sua_PhanTramKMjTextField.setText("");
    }//GEN-LAST:event_sua_huyjButtonActionPerformed

    private void them_HuyjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_them_HuyjButtonActionPerformed
        // TODO add your handling code here:
        cardLayout.show(mainCardLayoutKM, "khuyenMai");
        inDanhSach();
        this.them_TenKhuyenMaijTextField.setText("");
        this.them_MoTaKMjTextField.setText("");
        this.them_NgayBatDaujCalendarComboBox.setCalendar(null);
        this.them_NgayKetThucjCalendarComboBox.setCalendar(null);
        this.them_PhanTramKMjTextField.setText("");
    }//GEN-LAST:event_them_HuyjButtonActionPerformed

    private void Them_luuJButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Them_luuJButton1ActionPerformed
        // TODO add your handling code here: TenKM, MoTaKM, NgayBatDau, NgayKetThuc, PhanTramKM
        String TenKM = this.them_TenKhuyenMaijTextField.getText();
        String MoTaKM = this.them_MoTaKMjTextField.getText();
        java.sql.Date sqldate1 = new java.sql.Date(this.them_NgayBatDaujCalendarComboBox.getDate().getTime());
        java.sql.Date sqldate2 = new java.sql.Date(this.them_NgayKetThucjCalendarComboBox.getDate().getTime());
        Double PhanTramKM = Double.parseDouble(this.them_PhanTramKMjTextField.getText());
        KhuyenMaiModel KM = new KhuyenMaiModel(TenKM, MoTaKM, sqldate1.toLocalDate(), sqldate2.toLocalDate(), PhanTramKM);
        KhuyenMaiDAO kmdao = new KhuyenMaiDAO();
        if (kmdao.ThemKM(KM)) {
            JOptionPane.showMessageDialog(rootPane, "Thêm thành công!", "Thông báo", JOptionPane.PLAIN_MESSAGE);
            cardLayout.show(mainCardLayoutKM, "khuyenMai");
            inDanhSach();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Thêm không thành công. Vui lòng thử lại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        this.them_TenKhuyenMaijTextField.setText("");
        this.them_MoTaKMjTextField.setText("");
        this.them_NgayBatDaujCalendarComboBox.setCalendar(null);
        this.them_NgayKetThucjCalendarComboBox.setCalendar(null);
        this.them_PhanTramKMjTextField.setText("");
    }//GEN-LAST:event_Them_luuJButton1ActionPerformed

    private void TrangBiLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangBiLbMouseClicked
        // TODO add your handling code here:
        dispose();
        TrangBiFrame.main(currentUser);
    }//GEN-LAST:event_TrangBiLbMouseClicked

    private void trangChujLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trangChujLabelMouseClicked
        // TODO add your handling code here:
        dispose();
        TrangChuNVFrame.main(currentUser);
    }//GEN-LAST:event_trangChujLabelMouseClicked

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
            java.util.logging.Logger.getLogger(KhuyenMaiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new KhuyenMaiFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CCCDjLabel;
    private javax.swing.JLabel ChamCongLb;
    private javax.swing.JLabel DangXuatLb;
    private javax.swing.JTable DanhSachKhuyenMaiJTable;
    private javax.swing.JLabel HoaDonLb;
    private javax.swing.JLabel HopDongLb;
    private javax.swing.JLabel KhachHangLb;
    private javax.swing.JLabel KhuyenMaiLb;
    private javax.swing.JPanel KhuyenMaiPanel;
    private javax.swing.JPanel MenuPanel;
    private javax.swing.JLabel NhanVienLb;
    private javax.swing.JTextField NhapThongTinJTextField;
    private javax.swing.JButton SuaJButton1;
    private javax.swing.JPanel SuaKhuyenMaiPanel;
    private javax.swing.JButton ThemJButton;
    private javax.swing.JPanel ThemKhuyenMaiPanel;
    private javax.swing.JButton Them_luuJButton1;
    private javax.swing.JComboBox<String> TimKiemJComboBox;
    private javax.swing.JButton TraCuuJButton;
    private javax.swing.JLabel TrangBiLb;
    private javax.swing.JButton XoaJButton;
    private javax.swing.JLabel diaChijLabel;
    private javax.swing.JLabel hoTenjLable;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel luongLb;
    private javax.swing.JPanel mainCardLayoutKM;
    private javax.swing.JLabel ngaySinhjLabel;
    private javax.swing.JLabel ngaySinhjLabel1;
    private javax.swing.JLabel phongLb;
    private javax.swing.JTextField sua_MoTaKhuyenMaijTextField;
    private de.wannawork.jcalendar.JCalendarComboBox sua_NgayBatDaujCalendarComboBox;
    private de.wannawork.jcalendar.JCalendarComboBox sua_NgayKetThucjCalendarComboBox;
    private javax.swing.JTextField sua_PhanTramKMjTextField;
    private javax.swing.JTextField sua_TenKhuyenMaijTextField;
    private javax.swing.JButton sua_huyjButton;
    private javax.swing.JButton sua_luujButton;
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
    private javax.swing.JButton them_HuyjButton;
    private javax.swing.JTextField them_MoTaKMjTextField;
    private de.wannawork.jcalendar.JCalendarComboBox them_NgayBatDaujCalendarComboBox;
    private de.wannawork.jcalendar.JCalendarComboBox them_NgayKetThucjCalendarComboBox;
    private javax.swing.JTextField them_PhanTramKMjTextField;
    private javax.swing.JTextField them_TenKhuyenMaijTextField;
    private javax.swing.JLabel trangChujLabel;
    // End of variables declaration//GEN-END:variables

}
