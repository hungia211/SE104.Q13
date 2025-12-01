package View;

import DAO.ChamCongDAO;
import DAO.NhanVienDAO;
import DAO.TrangChuDAO;
import Model.ChamCongModel;
import Model.NhanVienModel;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.CardLayout;
import java.awt.Component;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class NhanVienFrame extends javax.swing.JFrame {

    CardLayout cardlayout;

    private static NhanVienModel currentUser;

    private DefaultTableModel defaultTableModel = new DefaultTableModel() {
        public boolean isCellEdittable(int row, int column) {
            return false;
        }
    };

    int maNV;
    int maNVSua;
    NhanVienModel nv;
    ArrayList<NhanVienModel> DS_NV;
    int current = 0;

    public NhanVienFrame() {
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        initComponents();
        this.setLocationRelativeTo(null);
        addSVG();
        khoiTaoBang(); // Gọi phương thức khởi tạo bảng
        inDanhSach(); // Gọi phương thức in danh sách ban đầu nếu cần thiết

        // Tạo các luồng sự kiện chuyển Panel
        cardlayout = (CardLayout) (mainCardLayoutNV.getLayout());
        mainCardLayoutNV.add(NhanVienPanel, "nhanVien");
        cardlayout.show(mainCardLayoutNV, "nhanVien");
        mainCardLayoutNV.add(ThemNhanVienPanel, "themNhanVien");
        mainCardLayoutNV.add(SuaNhanVienPanel, "suaNhanVien");
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
        svgNV.setYColor();
        svgHopDong.setColor();
        svgKM.setColor();
        svgTB.setColor();
        svgHoaDon.setColor();
        svgCC.setColor();
        svgL.setColor();
    }
    public void khoiTaoBang() {
        defaultTableModel = new DefaultTableModel();

        defaultTableModel.addColumn("Mã NV");
        defaultTableModel.addColumn("Họ tên");
        defaultTableModel.addColumn("CCCD");
        defaultTableModel.addColumn("Ngày sinh");
        defaultTableModel.addColumn("Giới tính");
        defaultTableModel.addColumn("Địa chỉ");
        defaultTableModel.addColumn("SĐT");
        defaultTableModel.addColumn("Loại NV");
        defaultTableModel.addColumn("Tài khoản");
        defaultTableModel.addColumn("Mật khẩu");
        defaultTableModel.addColumn("Lương CB");

        danhSachNVTable.setModel(defaultTableModel);
    }

    public void inDanhSach() {
        // Tạo đối tượng danh sách nhân viên
        DS_NV = NhanVienDAO.getDSNhanVien();

        // Xóa tất cả các hàng hiện có trong bảng
        defaultTableModel.setRowCount(0);

        // Thêm dữ liệu vào bảng
        for (NhanVienModel nhanVien : DS_NV) {
            defaultTableModel.addRow(new Object[]{
                nhanVien.getMaNV(), nhanVien.getTenNV(), nhanVien.getCCCD(),
                nhanVien.getNgaySinh(), nhanVien.getGioiTinh(), nhanVien.getDiaChi(), nhanVien.getSDT(),
                nhanVien.getLoaiNV(), nhanVien.getTaiKhoan(), nhanVien.getMatKhau(), nhanVien.getLuongCB()
            });
        }

        for (int i = 0; i < defaultTableModel.getColumnCount(); i++) {
            TableColumn column = danhSachNVTable.getColumnModel().getColumn(i);
            int maxWidth = 0;

            // Tính toán chiều rộng dựa trên nội dung lớn nhất trong cột
            for (int row = 0; row < danhSachNVTable.getRowCount(); row++) {
                TableCellRenderer cellRenderer = danhSachNVTable.getCellRenderer(row, i);
                Component c = danhSachNVTable.prepareRenderer(cellRenderer, row, i);
                maxWidth = Math.max(c.getPreferredSize().width, maxWidth);
            }

            // Thiết lập chiều rộng cột + padding
            column.setPreferredWidth(maxWidth + 30);
        }
    }

    public void TraCuuNV() {
        // Lấy tùy chọn tìm kiếm và đầu vào từ người dùng
        String option = (String) this.timTheoComboBox.getSelectedItem();
        String input = this.nhapjTextField.getText().toLowerCase();

        // Xóa tất cả các hàng trong bảng
        defaultTableModel.setRowCount(0);

        // Tạo danh sách nhân viên để chứa kết quả tìm kiếm
        ArrayList<NhanVienModel> DS_NV = NhanVienDAO.TimNV(option, input);

        // Thêm kết quả tìm kiếm vào bảng
        for (NhanVienModel nv : DS_NV) {
            defaultTableModel.addRow(new Object[]{
                nv.getMaNV(), nv.getTenNV(), nv.getCCCD(),
                nv.getNgaySinh(), nv.getGioiTinh(), nv.getDiaChi(),
                nv.getSDT(), nv.getLoaiNV(), nv.getTaiKhoan(), nv.getMatKhau(), nv.getLuongCB()
            });
        }

        // Hiển thị thông báo nếu không tìm thấy kết quả
        if (DS_NV.size() <= 0) {
            JOptionPane.showMessageDialog(rootPane, "Thông tin không tồn tại. Vui lòng thử lại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void hienDuLieuSua() {
        // Lấy thông tin nhân viên từ cơ sở dữ liệu
        try {
            nv = NhanVienDAO.getNVtheoMaNV(maNVSua);
            // Sử dụng thông tin nv để cập nhật giao diện
            if (nv != null) {
                sua_hoTenjTextField.setText(nv.getTenNV());
                sua_CCCDjTextField.setText(nv.getCCCD());

                sua_gioiTinhjComboBox.setSelectedItem(nv.getGioiTinh());
                // chuyển đổi sang định dạng LocalDate 
                Date ngaySinh = GregorianCalendar.from(nv.getNgaySinh().atStartOfDay(ZoneId.systemDefault())).getTime();
                sua_ngaySinhjComboBox.setDate(ngaySinh);

                sua_diaChijTextField.setText(nv.getDiaChi());
                sua_SDTjTextField.setText(nv.getSDT());
                this.sua_CCCDjTextField.setEditable(false);
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin khách hàng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lấy thông tin khách hàng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
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
        mainCardLayoutNV = new javax.swing.JPanel();
        NhanVienPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        danhSachNVTable = new javax.swing.JTable();
        timTheoComboBox = new javax.swing.JComboBox<>();
        nhapjTextField = new javax.swing.JTextField();
        traCuujButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        suajButton = new javax.swing.JButton();
        xoajButton = new javax.swing.JButton();
        themjButton = new javax.swing.JButton();
        ThemNhanVienPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        them_hoTenjTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        them_CCCDjTextField = new javax.swing.JTextField();
        them_ngaySinhjComboBox = new de.wannawork.jcalendar.JCalendarComboBox();
        them_diaChijTextField = new javax.swing.JTextField();
        them_SDTjTextField = new javax.swing.JTextField();
        them_gioiTinhjComboBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        them_loaiNVjComboBox = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        them_huyjButton = new javax.swing.JButton();
        them_themjButton = new javax.swing.JButton();
        SuaNhanVienPanel = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        sua_hoTenjTextField = new javax.swing.JTextField();
        sua_CCCDjTextField = new javax.swing.JTextField();
        sua_gioiTinhjComboBox = new javax.swing.JComboBox<>();
        sua_ngaySinhjComboBox = new de.wannawork.jcalendar.JCalendarComboBox();
        sua_diaChijTextField = new javax.swing.JTextField();
        sua_SDTjTextField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        sua_huyjButton = new javax.swing.JButton();
        sua_suajButton = new javax.swing.JButton();

        jButton2.setText("jButton2");

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
        NhanVienLb.setForeground(new java.awt.Color(220, 220, 46));
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

        mainCardLayoutNV.setPreferredSize(new java.awt.Dimension(700, 600));
        mainCardLayoutNV.setLayout(new java.awt.CardLayout());

        NhanVienPanel.setBackground(new java.awt.Color(255, 255, 255));
        NhanVienPanel.setPreferredSize(new java.awt.Dimension(700, 600));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(24, 24, 68));
        jLabel8.setText("NHÂN VIÊN");

        danhSachNVTable.setBackground(new java.awt.Color(255, 255, 255));
        danhSachNVTable.setForeground(new java.awt.Color(0, 0, 0));
        danhSachNVTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Họ tên", "CCCD", "Ngày sinh", "Giới tính", "Địa chỉ", "SĐT", "Loại NV", "Tài khoản", "Mật khẩu", "Lương CB"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(danhSachNVTable);

        timTheoComboBox.setBackground(new java.awt.Color(255, 255, 255));
        timTheoComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        timTheoComboBox.setForeground(new java.awt.Color(0, 0, 0));
        timTheoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã NV", "Họ tên", "CCCD", "SĐT", "Loại NV", " " }));
        timTheoComboBox.setPreferredSize(new java.awt.Dimension(100, 40));

        nhapjTextField.setBackground(new java.awt.Color(255, 255, 255));
        nhapjTextField.setForeground(new java.awt.Color(0, 0, 0));
        nhapjTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nhapjTextFieldActionPerformed(evt);
            }
        });

        traCuujButton.setBackground(new java.awt.Color(24, 24, 68));
        traCuujButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        traCuujButton.setForeground(new java.awt.Color(255, 255, 255));
        traCuujButton.setText("Tra cứu");
        traCuujButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                traCuujButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Tìm kiếm theo");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nhập thông tin");

        suajButton.setBackground(new java.awt.Color(24, 24, 68));
        suajButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        suajButton.setForeground(new java.awt.Color(255, 255, 255));
        suajButton.setText("Sửa");
        suajButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suajButtonActionPerformed(evt);
            }
        });

        xoajButton.setBackground(new java.awt.Color(24, 24, 68));
        xoajButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        xoajButton.setForeground(new java.awt.Color(255, 255, 255));
        xoajButton.setText("Xóa");
        xoajButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoajButtonActionPerformed(evt);
            }
        });

        themjButton.setBackground(new java.awt.Color(24, 24, 68));
        themjButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        themjButton.setForeground(new java.awt.Color(255, 255, 255));
        themjButton.setText("Thêm");
        themjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout NhanVienPanelLayout = new javax.swing.GroupLayout(NhanVienPanel);
        NhanVienPanel.setLayout(NhanVienPanelLayout);
        NhanVienPanelLayout.setHorizontalGroup(
            NhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhanVienPanelLayout.createSequentialGroup()
                .addGap(266, 266, 266)
                .addComponent(jLabel8))
            .addGroup(NhanVienPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(NhanVienPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(timTheoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(nhapjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(traCuujButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(NhanVienPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(NhanVienPanelLayout.createSequentialGroup()
                .addGap(390, 390, 390)
                .addComponent(themjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(xoajButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(suajButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        NhanVienPanelLayout.setVerticalGroup(
            NhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhanVienPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel8)
                .addGap(9, 9, 9)
                .addGroup(NhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(NhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timTheoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nhapjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(traCuujButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(NhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(themjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xoajButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(suajButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        mainCardLayoutNV.add(NhanVienPanel, "card2");

        ThemNhanVienPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(24, 24, 68));
        jLabel9.setText("NHÂN VIÊN");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nhập thông tin nhân viên cần thêm");

        them_hoTenjTextField.setBackground(new java.awt.Color(255, 255, 255));
        them_hoTenjTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        them_hoTenjTextField.setForeground(new java.awt.Color(0, 0, 0));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Họ và tên");

        them_CCCDjTextField.setBackground(new java.awt.Color(255, 255, 255));
        them_CCCDjTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        them_CCCDjTextField.setForeground(new java.awt.Color(0, 0, 0));

        them_ngaySinhjComboBox.setBackground(new java.awt.Color(255, 255, 255));
        them_ngaySinhjComboBox.setForeground(new java.awt.Color(0, 0, 0));

        them_diaChijTextField.setBackground(new java.awt.Color(255, 255, 255));
        them_diaChijTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        them_diaChijTextField.setForeground(new java.awt.Color(0, 0, 0));

        them_SDTjTextField.setBackground(new java.awt.Color(255, 255, 255));
        them_SDTjTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        them_SDTjTextField.setForeground(new java.awt.Color(0, 0, 0));

        them_gioiTinhjComboBox.setBackground(new java.awt.Color(255, 255, 255));
        them_gioiTinhjComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        them_gioiTinhjComboBox.setForeground(new java.awt.Color(0, 0, 0));
        them_gioiTinhjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("CCCD");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Giới tính");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Ngày sinh");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Địa chỉ");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Số điện thoại");

        them_loaiNVjComboBox.setBackground(new java.awt.Color(255, 255, 255));
        them_loaiNVjComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        them_loaiNVjComboBox.setForeground(new java.awt.Color(0, 0, 0));
        them_loaiNVjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản lý", "Lễ tân", "Tạp Vụ" }));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Loại nhân viên");

        them_huyjButton.setBackground(new java.awt.Color(24, 24, 68));
        them_huyjButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        them_huyjButton.setForeground(new java.awt.Color(255, 255, 255));
        them_huyjButton.setText("Hủy");
        them_huyjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                them_huyjButtonActionPerformed(evt);
            }
        });

        them_themjButton.setBackground(new java.awt.Color(24, 24, 68));
        them_themjButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        them_themjButton.setForeground(new java.awt.Color(255, 255, 255));
        them_themjButton.setText("Thêm");
        them_themjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                them_themjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ThemNhanVienPanelLayout = new javax.swing.GroupLayout(ThemNhanVienPanel);
        ThemNhanVienPanel.setLayout(ThemNhanVienPanelLayout);
        ThemNhanVienPanelLayout.setHorizontalGroup(
            ThemNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThemNhanVienPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(ThemNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThemNhanVienPanelLayout.createSequentialGroup()
                        .addGroup(ThemNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ThemNhanVienPanelLayout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(ThemNhanVienPanelLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(ThemNhanVienPanelLayout.createSequentialGroup()
                                .addGroup(ThemNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(them_hoTenjTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(them_ngaySinhjComboBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(ThemNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ThemNhanVienPanelLayout.createSequentialGroup()
                                        .addComponent(them_diaChijTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(them_SDTjTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
                                    .addGroup(ThemNhanVienPanelLayout.createSequentialGroup()
                                        .addGroup(ThemNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(them_CCCDjTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(ThemNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(them_gioiTinhjComboBox, 0, 100, Short.MAX_VALUE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThemNhanVienPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(them_themjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(them_huyjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36))
                    .addGroup(ThemNhanVienPanelLayout.createSequentialGroup()
                        .addGroup(ThemNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addComponent(them_loaiNVjComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        ThemNhanVienPanelLayout.setVerticalGroup(
            ThemNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThemNhanVienPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(ThemNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel4))
                .addGap(74, 74, 74)
                .addGroup(ThemNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ThemNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(them_hoTenjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(them_CCCDjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(them_gioiTinhjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(ThemNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThemNhanVienPanelLayout.createSequentialGroup()
                        .addGroup(ThemNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(them_ngaySinhjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThemNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(them_diaChijTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(them_SDTjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(them_loaiNVjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(ThemNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(them_huyjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(them_themjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        mainCardLayoutNV.add(ThemNhanVienPanel, "card3");

        SuaNhanVienPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(24, 24, 68));
        jLabel14.setText("NHÂN VIÊN");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Nhập thông tin cần sửa");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Họ và tên");

        sua_hoTenjTextField.setBackground(new java.awt.Color(255, 255, 255));
        sua_hoTenjTextField.setForeground(new java.awt.Color(0, 0, 0));

        sua_CCCDjTextField.setBackground(new java.awt.Color(255, 255, 255));
        sua_CCCDjTextField.setForeground(new java.awt.Color(0, 0, 0));

        sua_gioiTinhjComboBox.setBackground(new java.awt.Color(255, 255, 255));
        sua_gioiTinhjComboBox.setForeground(new java.awt.Color(0, 0, 0));
        sua_gioiTinhjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        sua_ngaySinhjComboBox.setBackground(new java.awt.Color(255, 255, 255));
        sua_ngaySinhjComboBox.setForeground(new java.awt.Color(0, 0, 0));

        sua_diaChijTextField.setBackground(new java.awt.Color(255, 255, 255));
        sua_diaChijTextField.setForeground(new java.awt.Color(0, 0, 0));

        sua_SDTjTextField.setBackground(new java.awt.Color(255, 255, 255));
        sua_SDTjTextField.setForeground(new java.awt.Color(0, 0, 0));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Ngày sinh");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Địa chỉ");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Số điện thoại");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("CCCD");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Giới tính");

        sua_huyjButton.setBackground(new java.awt.Color(24, 24, 68));
        sua_huyjButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sua_huyjButton.setForeground(new java.awt.Color(255, 255, 255));
        sua_huyjButton.setText("Hủy");
        sua_huyjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sua_huyjButtonActionPerformed(evt);
            }
        });

        sua_suajButton.setBackground(new java.awt.Color(24, 24, 68));
        sua_suajButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sua_suajButton.setForeground(new java.awt.Color(255, 255, 255));
        sua_suajButton.setText("Lưu");
        sua_suajButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sua_suajButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SuaNhanVienPanelLayout = new javax.swing.GroupLayout(SuaNhanVienPanel);
        SuaNhanVienPanel.setLayout(SuaNhanVienPanelLayout);
        SuaNhanVienPanelLayout.setHorizontalGroup(
            SuaNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SuaNhanVienPanelLayout.createSequentialGroup()
                .addGroup(SuaNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(SuaNhanVienPanelLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(SuaNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SuaNhanVienPanelLayout.createSequentialGroup()
                                .addGroup(SuaNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                    .addComponent(sua_hoTenjTextField, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(SuaNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sua_CCCDjTextField)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(SuaNhanVienPanelLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel15))
                            .addGroup(SuaNhanVienPanelLayout.createSequentialGroup()
                                .addGroup(SuaNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(sua_ngaySinhjComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(SuaNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(sua_diaChijTextField)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(SuaNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(SuaNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(sua_gioiTinhjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(sua_SDTjTextField)
                                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))))
                    .addGroup(SuaNhanVienPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sua_suajButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(sua_huyjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37))
        );
        SuaNhanVienPanelLayout.setVerticalGroup(
            SuaNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SuaNhanVienPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(SuaNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGap(74, 74, 74)
                .addGroup(SuaNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SuaNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sua_hoTenjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sua_CCCDjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sua_gioiTinhjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(SuaNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SuaNhanVienPanelLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(SuaNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sua_ngaySinhjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SuaNhanVienPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(SuaNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sua_diaChijTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sua_SDTjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                .addGroup(SuaNhanVienPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sua_huyjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sua_suajButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        mainCardLayoutNV.add(SuaNhanVienPanel, "card4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainCardLayoutNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainCardLayoutNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void luongLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_luongLbMouseClicked
        if (TrangChuDAO.KTLoaiNV(currentUser.getMaNV()) == 1) {
            dispose();
            LuongFrame.main(currentUser);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập vào chức năng này", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_luongLbMouseClicked

    private void phongLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_phongLbMouseClicked
        // TODO add your handling code here:
        dispose();
        PhongFrame.main(currentUser);
    }//GEN-LAST:event_phongLbMouseClicked

    private void DangXuatLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DangXuatLbMouseClicked
        int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất không?", "Đăng xuất", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            dispose();
            DangNhapFrame.main(null);
        }
    }//GEN-LAST:event_DangXuatLbMouseClicked

    private void ChamCongLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChamCongLbMouseClicked
        System.out.println(currentUser.toString());
        if (TrangChuDAO.KTLoaiNV(currentUser.getMaNV()) == 1) {
            dispose();
            ChamCongFrame.main(currentUser);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập vào chức năng này", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_ChamCongLbMouseClicked

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

    private void NhanVienLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NhanVienLbMouseClicked
        cardlayout.show(mainCardLayoutNV, "nhanVien");
        inDanhSach();
    }//GEN-LAST:event_NhanVienLbMouseClicked

    private void KhachHangLbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KhachHangLbMouseClicked
        dispose();
        KhachHangFrame.main(currentUser);
    }//GEN-LAST:event_KhachHangLbMouseClicked

    private void nhapjTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nhapjTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nhapjTextFieldActionPerformed

    private void xoajButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoajButtonActionPerformed
        // TODO add your handling code here:
        int removeIndex = this.danhSachNVTable.getSelectedRow();
        try {
            if (removeIndex == -1) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dữ liệu cần xoá!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else if (this.danhSachNVTable.getRowCount() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Dữ liệu trống!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else {
                Object maNVObject = this.danhSachNVTable.getValueAt(removeIndex, 0);
                int maNV = (Integer) maNVObject;

                int choice = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn xóa không?", "Xác nhận xóa", JOptionPane.OK_CANCEL_OPTION);
                if (choice == JOptionPane.OK_OPTION) {
                    // Xử lý xóa hợp đồng ở đây
                    int row = ChamCongDAO.XoaCC(maNV);
                    if (row > 0) {
                        int row1 = NhanVienDAO.XoaNV(maNV);
                        JOptionPane.showMessageDialog(rootPane, "Xoá thành công!", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Xoá không thành công. Vui lòng thử lại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    // Người dùng hủy xóa
                    JOptionPane.showMessageDialog(rootPane, "Đã hủy xóa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }

                inDanhSach();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Error!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_xoajButtonActionPerformed

    private void themjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themjButtonActionPerformed
        // TODO add your handling code here:
        cardlayout.show(mainCardLayoutNV, "themNhanVien");
    }//GEN-LAST:event_themjButtonActionPerformed

    private void traCuujButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_traCuujButtonActionPerformed
        // TODO add your handling code here:
        // Lấy đầu vào từ người dùng
        String input = this.nhapjTextField.getText();

        // Kiểm tra đầu vào và gọi phương thức tương ứng
        if (input.equals("")) {
            inDanhSach();
        } else {
            TraCuuNV();
        }
    }//GEN-LAST:event_traCuujButtonActionPerformed

    private void them_huyjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_them_huyjButtonActionPerformed
        cardlayout.show(mainCardLayoutNV, "nhanVien");
        inDanhSach();
        this.them_hoTenjTextField.setText("");
        this.them_CCCDjTextField.setText("");
        this.them_diaChijTextField.setText("");
        this.them_SDTjTextField.setText("");
        this.them_ngaySinhjComboBox.setCalendar(null);
    }//GEN-LAST:event_them_huyjButtonActionPerformed

    private void them_themjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_them_themjButtonActionPerformed

        // Lầy dữ liệu từ các thanh JTextField
        String hoTen = this.them_hoTenjTextField.getText();

        String cccd = this.them_CCCDjTextField.getText();
        java.sql.Date sqldate = new java.sql.Date(this.them_ngaySinhjComboBox.getDate().getTime());
        String gioiTinh = (String) this.them_gioiTinhjComboBox.getSelectedItem();
        String diaChi = this.them_diaChijTextField.getText();
        String sdt = this.them_SDTjTextField.getText();
        String loainv = (String) this.them_loaiNVjComboBox.getSelectedItem();

        NhanVienModel nv = new NhanVienModel(hoTen, cccd, sqldate.toLocalDate(), gioiTinh, diaChi, sdt, loainv);
        NhanVienDAO nvDAO = new NhanVienDAO();

        try {
            System.out.println(nv.getMaNV());
            int row = nvDAO.ThemNV(nv);
            if (row > 0) {
                System.out.println(cccd);
                NhanVienModel nv1 = NhanVienDAO.getNVtheoCCCD(cccd);
                System.out.println(nv1.getMaNV());
                int row1 = ChamCongDAO.ThemCC(nv1.getMaNV());
                if (row1 > 0) {
                    // Thực hiện thêm mới chấm công cho nhân viên khi được thêm vào
                    JOptionPane.showMessageDialog(rootPane, "Thêm thành công!", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                    cardlayout.show(mainCardLayoutNV, "nhanVien");
                    inDanhSach();
                    this.them_hoTenjTextField.setText("");
                    this.them_CCCDjTextField.setText("");
                    this.them_diaChijTextField.setText("");
                    this.them_SDTjTextField.setText("");
                    this.them_ngaySinhjComboBox.setCalendar(null);
                }
            }
        } catch (SQLException ex) {

        }

    }//GEN-LAST:event_them_themjButtonActionPerformed

    private void sua_huyjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sua_huyjButtonActionPerformed
        cardlayout.show(mainCardLayoutNV, "nhanVien");
        inDanhSach();
    }//GEN-LAST:event_sua_huyjButtonActionPerformed

    private void sua_suajButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sua_suajButtonActionPerformed
        // Lấy thông tin nhập liệu mới
        String hoTen = this.sua_hoTenjTextField.getText();
        String cccd = this.sua_CCCDjTextField.getText();
        java.sql.Date sqldate = new java.sql.Date(this.sua_ngaySinhjComboBox.getDate().getTime());
        String gioiTinh = (String) this.sua_gioiTinhjComboBox.getSelectedItem();
        String diaChi = this.sua_diaChijTextField.getText();
        String sdt = this.sua_SDTjTextField.getText();

        try {
            // Lấy thông tin cũ của nhân viên từ cơ sở dữ liệu
            NhanVienModel nvCu = NhanVienDAO.getNVtheoMaNV(maNVSua);

            // Kiểm tra và cập nhật thông tin mới
            if (!hoTen.isEmpty()) {
                nvCu.setTenNV(hoTen);
            }
            if (!cccd.isEmpty()) {
                nvCu.setCCCD(cccd);
            }
            if (sqldate != null) {
                nvCu.setNgaySinh(sqldate.toLocalDate());
            }
            if (!gioiTinh.isEmpty()) {
                nvCu.setGioiTinh(gioiTinh);
            }
            if (!diaChi.isEmpty()) {
                nvCu.setDiaChi(diaChi);
            }
            if (!sdt.isEmpty()) {
                nvCu.setSDT(sdt);
            }

            int choice = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn sửa không?", "Xác nhận sửa", JOptionPane.OK_CANCEL_OPTION);
            if (choice == JOptionPane.OK_OPTION) {
                // Thực hiện cập nhật trong cơ sở dữ liệu
                if (NhanVienDAO.CapNhatNV(maNVSua, nvCu)) {
                    JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công!", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                    this.sua_hoTenjTextField.setText("");
                    this.sua_diaChijTextField.setText("");
                    this.sua_CCCDjTextField.setText("");
                    this.sua_SDTjTextField.setText("");
                    this.sua_ngaySinhjComboBox.setCalendar(null);
                    cardlayout.show(mainCardLayoutNV, "nhanVien");
                    inDanhSach();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Cập nhật không thành công. Vui lòng thử lại!",
                            "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Người dùng hủy xóa
                JOptionPane.showMessageDialog(rootPane, "Đã hủy sửa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }

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
    }//GEN-LAST:event_sua_suajButtonActionPerformed

    private void suajButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suajButtonActionPerformed
        // TODO add your handling code here:
        int selectedIndex = this.danhSachNVTable.getSelectedRow();

        try {
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dữ liệu cần sửa!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else if (this.danhSachNVTable.getRowCount() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Dữ liệu trống!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else {
                Object maNVObject = this.danhSachNVTable.getValueAt(selectedIndex, 0);
                this.maNVSua = (Integer) maNVObject;
                cardlayout.show(mainCardLayoutNV, "suaNhanVien");
                hienDuLieuSua();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Error!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_suajButtonActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        dispose();
        TrangChuNVFrame.main(currentUser);
    }//GEN-LAST:event_jLabel1MouseClicked

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
            java.util.logging.Logger.getLogger(NhanVienFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new NhanVienFrame().setVisible(true);
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
    private javax.swing.JPanel NhanVienPanel;
    private javax.swing.JPanel SuaNhanVienPanel;
    private javax.swing.JPanel ThemNhanVienPanel;
    private javax.swing.JLabel TrangBiLb;
    private javax.swing.JTable danhSachNVTable;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel luongLb;
    private javax.swing.JPanel mainCardLayoutNV;
    private javax.swing.JTextField nhapjTextField;
    private javax.swing.JLabel phongLb;
    private javax.swing.JTextField sua_CCCDjTextField;
    private javax.swing.JTextField sua_SDTjTextField;
    private javax.swing.JTextField sua_diaChijTextField;
    private javax.swing.JComboBox<String> sua_gioiTinhjComboBox;
    private javax.swing.JTextField sua_hoTenjTextField;
    private javax.swing.JButton sua_huyjButton;
    private de.wannawork.jcalendar.JCalendarComboBox sua_ngaySinhjComboBox;
    private javax.swing.JButton sua_suajButton;
    private javax.swing.JButton suajButton;
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
    private javax.swing.JTextField them_CCCDjTextField;
    private javax.swing.JTextField them_SDTjTextField;
    private javax.swing.JTextField them_diaChijTextField;
    private javax.swing.JComboBox<String> them_gioiTinhjComboBox;
    private javax.swing.JTextField them_hoTenjTextField;
    private javax.swing.JButton them_huyjButton;
    private javax.swing.JComboBox<String> them_loaiNVjComboBox;
    private de.wannawork.jcalendar.JCalendarComboBox them_ngaySinhjComboBox;
    private javax.swing.JButton them_themjButton;
    private javax.swing.JButton themjButton;
    private javax.swing.JComboBox<String> timTheoComboBox;
    private javax.swing.JButton traCuujButton;
    private javax.swing.JButton xoajButton;
    // End of variables declaration//GEN-END:variables

}
