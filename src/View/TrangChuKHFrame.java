package View;

import Connection.JDBCUtil;
import DAO.LichSuDAO;
import Model.KhuyenMaiModel;
import Model.LichSuModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLEditorKit;

public class TrangChuKHFrame extends javax.swing.JFrame {

    private JPanel mainPanel; // Được tạo trong NetBeans GUI Builder
    private ArrayList<JPanel> panelList;
    private static final int MAX_PANEL_COUNT = 10; // Giới hạn số lượng panel hiển thị
    Color panelColor = new Color(242, 231, 220);
    Color textColor = new Color(174, 165, 155);

    MoTaDialog mota = new MoTaDialog();
    
    private DefaultTableModel TableLS;
    ArrayList<LichSuModel> DS_LichSu = new ArrayList<LichSuModel>();

    public TrangChuKHFrame(ArrayList<KhuyenMaiModel> khuyenMaiList) {
        initComponents();
        this.setLocationRelativeTo(null);
        setTitle("Trang Khach Hang");
        setSize(910, 640);
        setLocationRelativeTo(null);
        initCS();
        initKM(khuyenMaiList);
        initLS();
        addSVG();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        TrangChuPanel = new javax.swing.JPanel();
        KhuyenMaiJLabel = new javax.swing.JLabel();
        ChinhSachJLabel = new javax.swing.JLabel();
        LichSuJLabel = new javax.swing.JLabel();
        DatPhongJLabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        tenP1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        ChinhSachPanel = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        DatPhongJLabel1 = new javax.swing.JLabel();
        KhuyenMaiJLabel1 = new javax.swing.JLabel();
        LichSuJLabel1 = new javax.swing.JLabel();
        ChinhSachJLabel1 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ChinhSachTextPane = new javax.swing.JTextPane();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        svgPolicy = new Image.SVGImage();
        KhuyenMaiPanel = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel20 = new javax.swing.JPanel();
        KM1jPanel = new javax.swing.JPanel();
        KM2jPanel = new javax.swing.JPanel();
        KM3jPanel = new javax.swing.JPanel();
        KM4jPanel = new javax.swing.JPanel();
        KM5jPanel = new javax.swing.JPanel();
        KM6jPanel = new javax.swing.JPanel();
        KM7jPanel = new javax.swing.JPanel();
        KM8jPanel = new javax.swing.JPanel();
        KM9jPanel = new javax.swing.JPanel();
        KM10jPanel = new javax.swing.JPanel();
        ChinhSachJLabel3 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        LichSuJLabel3 = new javax.swing.JLabel();
        KhuyenMaiJLabel3 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        DatPhongJLabel3 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        svgPC = new Image.SVGImage();
        LichSuPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        LichSuJTable = new javax.swing.JTable();
        NhapCCCDTextField = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        TraCuuButton = new javax.swing.JButton();
        LichSuCuaJLabel = new javax.swing.JLabel();
        KhachHangJLabel = new javax.swing.JLabel();
        SoDiemJLabel = new javax.swing.JLabel();
        SoDiemCuaKhachJLabel = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        DatPhongJLabel4 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        KhuyenMaiJLabel4 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        LichSuJLabel4 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        ChinhSachJLabel4 = new javax.swing.JLabel();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(700, 600));

        jLabel2.setBackground(new java.awt.Color(24, 24, 68));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel2.setText("Trang khách hàng ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(229, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(211, 211, 211))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addContainerGap(545, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.CardLayout());

        TrangChuPanel.setBackground(new java.awt.Color(255, 255, 255));
        TrangChuPanel.setPreferredSize(new java.awt.Dimension(900, 600));

        KhuyenMaiJLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        KhuyenMaiJLabel.setForeground(new java.awt.Color(24, 24, 68));
        KhuyenMaiJLabel.setText("Khuyến mãi ");
        KhuyenMaiJLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KhuyenMaiJLabelMouseClicked(evt);
            }
        });

        ChinhSachJLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        ChinhSachJLabel.setForeground(new java.awt.Color(24, 24, 68));
        ChinhSachJLabel.setText("Chính sách ");
        ChinhSachJLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ChinhSachJLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChinhSachJLabelMouseClicked(evt);
            }
        });

        LichSuJLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        LichSuJLabel.setForeground(new java.awt.Color(24, 24, 68));
        LichSuJLabel.setText("Lịch sử đặt phòng ");
        LichSuJLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LichSuJLabelMouseClicked(evt);
            }
        });

        DatPhongJLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        DatPhongJLabel.setForeground(new java.awt.Color(24, 24, 68));
        DatPhongJLabel.setText("Đặt phòng");
        DatPhongJLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DatPhongJLabelMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(77, 13, 195));
        jLabel11.setText("BRIGHT STAR");

        jPanel2.setBackground(new java.awt.Color(24, 24, 68));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(24, 24, 68));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(24, 24, 68));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(24, 24, 68));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(77, 13, 195));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Các hạng phòng của chúng tôi");

        jPanel8.setBackground(new java.awt.Color(233, 233, 233));

        jButton2.setBackground(new java.awt.Color(24, 24, 68));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Xem thêm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Đơn - VIP");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setText(" Giá: 800000-1500000");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/r2.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel4)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(26, 26, 26)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(233, 233, 233));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText(" Giá: 100000- 800000");

        jButton1.setBackground(new java.awt.Color(24, 24, 68));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Xem thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tenP1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tenP1.setForeground(new java.awt.Color(153, 153, 153));
        tenP1.setText("Đơn - Thường");

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/r1.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(tenP1)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(tenP1)
                .addGap(26, 26, 26)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(233, 233, 233));

        jButton3.setBackground(new java.awt.Color(24, 24, 68));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Xem thêm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText(" Đôi - Thường");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 153, 153));
        jLabel9.setText(" Giá: 600000 - 1200000");

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/r3.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(26, 26, 26)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(233, 233, 233));

        jButton4.setBackground(new java.awt.Color(24, 24, 68));
        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Xem thêm");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Đôi - VIP");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 153, 153));
        jLabel10.setText(" Giá: 1200000 - 3000000");

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/r4.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel10)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel6)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(27, 27, 27)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(24, 24, 68));
        jButton5.setText("Trở về");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TrangChuPanelLayout = new javax.swing.GroupLayout(TrangChuPanel);
        TrangChuPanel.setLayout(TrangChuPanelLayout);
        TrangChuPanelLayout.setHorizontalGroup(
            TrangChuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TrangChuPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(TrangChuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TrangChuPanelLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(47, 47, 47)
                        .addComponent(DatPhongJLabel)
                        .addGap(40, 40, 40)
                        .addComponent(KhuyenMaiJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(LichSuJLabel)
                        .addGap(27, 27, 27)
                        .addComponent(ChinhSachJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TrangChuPanelLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addGroup(TrangChuPanelLayout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        TrangChuPanelLayout.setVerticalGroup(
            TrangChuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TrangChuPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(TrangChuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DatPhongJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(KhuyenMaiJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LichSuJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChinhSachJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(TrangChuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addGroup(TrangChuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ChinhSachJLabel.getAccessibleContext().setAccessibleDescription("");

        jPanel1.add(TrangChuPanel, "card2");

        ChinhSachPanel.setBackground(new java.awt.Color(255, 255, 255));
        ChinhSachPanel.setPreferredSize(new java.awt.Dimension(900, 600));

        jPanel12.setBackground(new java.awt.Color(77, 13, 195));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel13.setBackground(new java.awt.Color(24, 24, 68));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel14.setBackground(new java.awt.Color(24, 24, 68));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel15.setBackground(new java.awt.Color(24, 24, 68));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel16.setBackground(new java.awt.Color(24, 24, 68));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(24, 24, 68));
        jLabel12.setText("BRIGHT STAR");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        DatPhongJLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        DatPhongJLabel1.setForeground(new java.awt.Color(24, 24, 68));
        DatPhongJLabel1.setText("Đặt phòng");
        DatPhongJLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DatPhongJLabel1MouseClicked(evt);
            }
        });

        KhuyenMaiJLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        KhuyenMaiJLabel1.setForeground(new java.awt.Color(24, 24, 68));
        KhuyenMaiJLabel1.setText("Khuyến mãi ");
        KhuyenMaiJLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KhuyenMaiJLabel1MouseClicked(evt);
            }
        });

        LichSuJLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        LichSuJLabel1.setForeground(new java.awt.Color(24, 24, 68));
        LichSuJLabel1.setText("Lịch sử đặt phòng ");
        LichSuJLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LichSuJLabel1MouseClicked(evt);
            }
        });

        ChinhSachJLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        ChinhSachJLabel1.setForeground(new java.awt.Color(77, 13, 195));
        ChinhSachJLabel1.setText("Chính sách ");
        ChinhSachJLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ChinhSachJLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChinhSachJLabel1MouseClicked(evt);
            }
        });

        jPanel17.setBackground(new java.awt.Color(24, 24, 68));

        ChinhSachTextPane.setBackground(new java.awt.Color(255, 255, 255));
        ChinhSachTextPane.setForeground(new java.awt.Color(0, 0, 0));
        ChinhSachTextPane.setCaretColor(new java.awt.Color(255, 255, 255));
        ChinhSachTextPane.setSelectionColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(ChinhSachTextPane);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 255, 255));
        jLabel13.setText("BRIGHT STAR");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("CHÍNH SÁCH CỦA");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgPolicy, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(svgPolicy, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(jLabel14)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel13))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ChinhSachPanelLayout = new javax.swing.GroupLayout(ChinhSachPanel);
        ChinhSachPanel.setLayout(ChinhSachPanelLayout);
        ChinhSachPanelLayout.setHorizontalGroup(
            ChinhSachPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChinhSachPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(ChinhSachPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ChinhSachPanelLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(47, 47, 47)
                        .addComponent(DatPhongJLabel1)
                        .addGap(40, 40, 40)
                        .addComponent(KhuyenMaiJLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(LichSuJLabel1)
                        .addGap(27, 27, 27)
                        .addComponent(ChinhSachJLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ChinhSachPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(ChinhSachPanelLayout.createSequentialGroup()
                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        ChinhSachPanelLayout.setVerticalGroup(
            ChinhSachPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChinhSachPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(ChinhSachPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DatPhongJLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(KhuyenMaiJLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LichSuJLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChinhSachJLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(ChinhSachPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel1.add(ChinhSachPanel, "card3");

        KhuyenMaiPanel.setPreferredSize(new java.awt.Dimension(900, 600));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(700, 600));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        KM1jPanel.setBackground(new java.awt.Color(242, 231, 220));
        KM1jPanel.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout KM1jPanelLayout = new javax.swing.GroupLayout(KM1jPanel);
        KM1jPanel.setLayout(KM1jPanelLayout);
        KM1jPanelLayout.setHorizontalGroup(
            KM1jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 830, Short.MAX_VALUE)
        );
        KM1jPanelLayout.setVerticalGroup(
            KM1jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 39, Short.MAX_VALUE)
        );

        KM2jPanel.setBackground(new java.awt.Color(242, 231, 220));

        javax.swing.GroupLayout KM2jPanelLayout = new javax.swing.GroupLayout(KM2jPanel);
        KM2jPanel.setLayout(KM2jPanelLayout);
        KM2jPanelLayout.setHorizontalGroup(
            KM2jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 830, Short.MAX_VALUE)
        );
        KM2jPanelLayout.setVerticalGroup(
            KM2jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );

        KM3jPanel.setBackground(new java.awt.Color(242, 231, 220));

        javax.swing.GroupLayout KM3jPanelLayout = new javax.swing.GroupLayout(KM3jPanel);
        KM3jPanel.setLayout(KM3jPanelLayout);
        KM3jPanelLayout.setHorizontalGroup(
            KM3jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 830, Short.MAX_VALUE)
        );
        KM3jPanelLayout.setVerticalGroup(
            KM3jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        KM4jPanel.setBackground(new java.awt.Color(242, 231, 220));

        javax.swing.GroupLayout KM4jPanelLayout = new javax.swing.GroupLayout(KM4jPanel);
        KM4jPanel.setLayout(KM4jPanelLayout);
        KM4jPanelLayout.setHorizontalGroup(
            KM4jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        KM4jPanelLayout.setVerticalGroup(
            KM4jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        KM5jPanel.setBackground(new java.awt.Color(242, 231, 220));

        javax.swing.GroupLayout KM5jPanelLayout = new javax.swing.GroupLayout(KM5jPanel);
        KM5jPanel.setLayout(KM5jPanelLayout);
        KM5jPanelLayout.setHorizontalGroup(
            KM5jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        KM5jPanelLayout.setVerticalGroup(
            KM5jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        KM6jPanel.setBackground(new java.awt.Color(242, 231, 220));

        javax.swing.GroupLayout KM6jPanelLayout = new javax.swing.GroupLayout(KM6jPanel);
        KM6jPanel.setLayout(KM6jPanelLayout);
        KM6jPanelLayout.setHorizontalGroup(
            KM6jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        KM6jPanelLayout.setVerticalGroup(
            KM6jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 39, Short.MAX_VALUE)
        );

        KM7jPanel.setBackground(new java.awt.Color(242, 231, 220));

        javax.swing.GroupLayout KM7jPanelLayout = new javax.swing.GroupLayout(KM7jPanel);
        KM7jPanel.setLayout(KM7jPanelLayout);
        KM7jPanelLayout.setHorizontalGroup(
            KM7jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        KM7jPanelLayout.setVerticalGroup(
            KM7jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        KM8jPanel.setBackground(new java.awt.Color(242, 231, 220));

        javax.swing.GroupLayout KM8jPanelLayout = new javax.swing.GroupLayout(KM8jPanel);
        KM8jPanel.setLayout(KM8jPanelLayout);
        KM8jPanelLayout.setHorizontalGroup(
            KM8jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        KM8jPanelLayout.setVerticalGroup(
            KM8jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );

        KM9jPanel.setBackground(new java.awt.Color(242, 231, 220));

        javax.swing.GroupLayout KM9jPanelLayout = new javax.swing.GroupLayout(KM9jPanel);
        KM9jPanel.setLayout(KM9jPanelLayout);
        KM9jPanelLayout.setHorizontalGroup(
            KM9jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        KM9jPanelLayout.setVerticalGroup(
            KM9jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 41, Short.MAX_VALUE)
        );

        KM10jPanel.setBackground(new java.awt.Color(242, 231, 220));

        javax.swing.GroupLayout KM10jPanelLayout = new javax.swing.GroupLayout(KM10jPanel);
        KM10jPanel.setLayout(KM10jPanelLayout);
        KM10jPanelLayout.setHorizontalGroup(
            KM10jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        KM10jPanelLayout.setVerticalGroup(
            KM10jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 41, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(KM10jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(KM4jPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(KM1jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(KM2jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(KM3jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(KM5jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(KM6jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(KM7jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(KM8jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(KM9jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(KM1jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(KM2jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(KM3jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(KM4jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(KM5jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(KM6jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(KM7jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(KM8jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(KM9jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(KM10jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel20);

        ChinhSachJLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        ChinhSachJLabel3.setForeground(new java.awt.Color(24, 24, 68));
        ChinhSachJLabel3.setText("Chính sách ");
        ChinhSachJLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ChinhSachJLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChinhSachJLabel3MouseClicked(evt);
            }
        });

        jPanel18.setBackground(new java.awt.Color(24, 24, 68));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel21.setBackground(new java.awt.Color(24, 24, 68));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        LichSuJLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        LichSuJLabel3.setForeground(new java.awt.Color(24, 24, 68));
        LichSuJLabel3.setText("Lịch sử đặt phòng ");
        LichSuJLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LichSuJLabel3MouseClicked(evt);
            }
        });

        KhuyenMaiJLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        KhuyenMaiJLabel3.setForeground(new java.awt.Color(77, 13, 195));
        KhuyenMaiJLabel3.setText("Khuyến mãi ");

        jPanel22.setBackground(new java.awt.Color(77, 13, 195));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel23.setBackground(new java.awt.Color(24, 24, 68));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        DatPhongJLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        DatPhongJLabel3.setForeground(new java.awt.Color(24, 24, 68));
        DatPhongJLabel3.setText("Đặt phòng");
        DatPhongJLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DatPhongJLabel3MouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(24, 24, 68));
        jLabel17.setText("BRIGHT STAR");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        jPanel24.setBackground(new java.awt.Color(24, 24, 68));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(77, 13, 195));
        jLabel15.setText("KHUYẾN MÃI");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("CHƯƠNG TRÌNH");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel17)
                .addGap(47, 47, 47)
                .addComponent(DatPhongJLabel3)
                .addGap(40, 40, 40)
                .addComponent(KhuyenMaiJLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(LichSuJLabel3)
                .addGap(27, 27, 27)
                .addComponent(ChinhSachJLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(svgPC, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15))
                .addGap(66, 66, 66)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DatPhongJLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(KhuyenMaiJLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LichSuJLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChinhSachJLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(svgPC, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGap(180, 180, 180)
                                .addComponent(jLabel16)))
                        .addGap(3, 3, 3)
                        .addComponent(jLabel15))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout KhuyenMaiPanelLayout = new javax.swing.GroupLayout(KhuyenMaiPanel);
        KhuyenMaiPanel.setLayout(KhuyenMaiPanelLayout);
        KhuyenMaiPanelLayout.setHorizontalGroup(
            KhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        KhuyenMaiPanelLayout.setVerticalGroup(
            KhuyenMaiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(KhuyenMaiPanel, "card4");

        LichSuPanel.setBackground(new java.awt.Color(255, 255, 255));
        LichSuPanel.setPreferredSize(new java.awt.Dimension(900, 600));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(24, 24, 68));
        jLabel7.setText("XEM LỊCH SỬ ĐẶT PHÒNG");

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        LichSuJTable.setBackground(new java.awt.Color(255, 255, 255));
        LichSuJTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LichSuJTable.setForeground(new java.awt.Color(0, 0, 0));
        LichSuJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Ngày lập hợp đồng ", "Ngày nhận phòng ", "Ngày trả phòng ", "Tình trạng hợp đồng "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(LichSuJTable);

        NhapCCCDTextField.setBackground(new java.awt.Color(255, 255, 255));
        NhapCCCDTextField.setForeground(new java.awt.Color(0, 0, 0));
        NhapCCCDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NhapCCCDTextFieldActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Nhập CCCD");

        TraCuuButton.setBackground(new java.awt.Color(24, 24, 68));
        TraCuuButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TraCuuButton.setForeground(new java.awt.Color(255, 255, 255));
        TraCuuButton.setText("Tra cứu ");
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

        LichSuCuaJLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LichSuCuaJLabel.setForeground(new java.awt.Color(0, 0, 0));

        KhachHangJLabel.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        KhachHangJLabel.setForeground(new java.awt.Color(0, 51, 153));

        SoDiemJLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        SoDiemJLabel.setForeground(new java.awt.Color(0, 0, 0));
        SoDiemJLabel.setToolTipText("");

        SoDiemCuaKhachJLabel.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        SoDiemCuaKhachJLabel.setForeground(new java.awt.Color(0, 51, 153));
        SoDiemCuaKhachJLabel.setToolTipText("");

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

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(24, 24, 68));
        jLabel23.setText("BRIGHT STAR");
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });

        DatPhongJLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        DatPhongJLabel4.setForeground(new java.awt.Color(24, 24, 68));
        DatPhongJLabel4.setText("Đặt phòng");
        DatPhongJLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DatPhongJLabel4MouseClicked(evt);
            }
        });

        jPanel26.setBackground(new java.awt.Color(24, 24, 68));

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

        jPanel27.setBackground(new java.awt.Color(24, 24, 68));

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        KhuyenMaiJLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        KhuyenMaiJLabel4.setForeground(new java.awt.Color(24, 24, 68));
        KhuyenMaiJLabel4.setText("Khuyến mãi ");
        KhuyenMaiJLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KhuyenMaiJLabel4MouseClicked(evt);
            }
        });

        jPanel28.setBackground(new java.awt.Color(77, 13, 195));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        LichSuJLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        LichSuJLabel4.setForeground(new java.awt.Color(77, 13, 195));
        LichSuJLabel4.setText("Lịch sử đặt phòng ");
        LichSuJLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LichSuJLabel4MouseClicked(evt);
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

        ChinhSachJLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        ChinhSachJLabel4.setForeground(new java.awt.Color(24, 24, 68));
        ChinhSachJLabel4.setText("Chính sách ");
        ChinhSachJLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ChinhSachJLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChinhSachJLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout LichSuPanelLayout = new javax.swing.GroupLayout(LichSuPanel);
        LichSuPanel.setLayout(LichSuPanelLayout);
        LichSuPanelLayout.setHorizontalGroup(
            LichSuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LichSuPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(LichSuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LichSuPanelLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(47, 47, 47)
                        .addComponent(DatPhongJLabel4)
                        .addGap(40, 40, 40)
                        .addComponent(KhuyenMaiJLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(LichSuJLabel4)
                        .addGap(27, 27, 27)
                        .addComponent(ChinhSachJLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LichSuPanelLayout.createSequentialGroup()
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LichSuPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(150, 150, 150)
                        .addComponent(LichSuCuaJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LichSuPanelLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(326, 326, 326)
                        .addComponent(KhachHangJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LichSuPanelLayout.createSequentialGroup()
                        .addComponent(NhapCCCDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(TraCuuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(SoDiemJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(SoDiemCuaKhachJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        LichSuPanelLayout.setVerticalGroup(
            LichSuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LichSuPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(LichSuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DatPhongJLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(KhuyenMaiJLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LichSuJLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChinhSachJLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(LichSuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(LichSuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(LichSuPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(LichSuCuaJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(LichSuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LichSuPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel22))
                    .addComponent(KhachHangJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(LichSuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NhapCCCDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TraCuuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SoDiemJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SoDiemCuaKhachJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(LichSuPanel, "card5");

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 900, 600);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void initCS() {
        ChinhSachTextPane.setEditable(false);
        ChinhSachTextPane.setContentType("text/html");
        ChinhSachTextPane.setEditorKit(new HTMLEditorKit());
        setTextPane();
    }

    public void initKM(ArrayList<KhuyenMaiModel> khuyenMaiList) {
        panelList = new ArrayList<>();

        panelList.add(KM1jPanel);
        panelList.add(KM2jPanel);
        panelList.add(KM3jPanel);
        panelList.add(KM4jPanel);
        panelList.add(KM5jPanel);
        panelList.add(KM6jPanel);
        panelList.add(KM7jPanel);
        panelList.add(KM8jPanel);
        panelList.add(KM9jPanel);
        panelList.add(KM10jPanel);

        // Gán dữ liệu vào các panel đã tạo trong NetBeans
        for (int i = 0; i < khuyenMaiList.size() && i < MAX_PANEL_COUNT; i++) {
            updatePanel(panelList.get(i), khuyenMaiList.get(i));
        }
    }
    
    public void initLS() {
        TableLS = (DefaultTableModel) LichSuJTable.getModel();
        jScrollPane3.setVisible(false);
        LichSuJTable.setVisible(false);
    }
    
    public void addSVG() {
        svgPC.setSVGImage("Image/coupon.svg", 200, 200);
        svgPolicy.setSVGImage("Image/policy.svg", 50, 50);
        svgPolicy.setYColor();
    }

    private void updatePanel(JPanel panel, KhuyenMaiModel km) {
        panel.removeAll(); // Xóa các thành phần hiện tại nếu có

        int phanTramKM = (int) (km.getPhanTramKM() * 100);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 20);
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Giảm đến " + phanTramKM + "%");
        titledBorder.setTitleFont(titleFont);
        titledBorder.setTitleColor(Color.BLACK); // Đặt màu chữ là màu trắng

        titledBorder.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(panelColor, 2),
                BorderFactory.createEmptyBorder(50, 15, 0, 0)
        ));

        panel.setLayout(new GridLayout(4, 1)); // Thiết lập layout với 4 dòng

        // Tạo và định dạng các nhãn (labels)
        JLabel tenKMLabel = new JLabel(km.getTenKM());
        Font font = tenKMLabel.getFont();
        Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize() + 4); // Tăng kích thước font lên 4 điểm và đặt style in đậm
        tenKMLabel.setFont(boldFont);
        tenKMLabel.setHorizontalAlignment(JLabel.LEFT); // Căn lề trái 

        JLabel ngayBatDauLabel = new JLabel("Ngày bắt đầu: " + km.getNgayBatDau().toString());
        ngayBatDauLabel.setFont(new Font(font.getFontName(), Font.BOLD, font.getSize())); // Đặt style in đậm cho "Ngày bắt đầu"
        ngayBatDauLabel.setHorizontalAlignment(JLabel.LEFT); // Căn lề trái

        JLabel ngayKetThucLabel = new JLabel("Ngày kết thúc: " + km.getNgayKetThuc().toString());
        ngayKetThucLabel.setFont(new Font(font.getFontName(), Font.BOLD, font.getSize())); // Đặt style in đậm cho "Ngày kết thúc"
        ngayKetThucLabel.setHorizontalAlignment(JLabel.LEFT); // Căn lề trái

        JLabel moTaLabel = new JLabel(km.getMoTaKM());
        moTaLabel.setHorizontalAlignment(JLabel.LEFT); // Căn lề trái

        ngayBatDauLabel.setForeground(textColor);
        ngayKetThucLabel.setForeground(textColor);
        moTaLabel.setForeground(textColor);
        tenKMLabel.setForeground(textColor);

        // Thêm các thành phần vào panel theo thứ tự
        panel.add(tenKMLabel); // Dòng 1
        panel.add(ngayBatDauLabel); // Dòng 2
        panel.add(ngayKetThucLabel); // Dòng 3
        panel.add(moTaLabel); // Dòng 4

        // Cập nhật layout và vẽ lại giao diện
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Tạo khoảng cách bên ngoài panel
        panel.setBorder(titledBorder);
        panel.revalidate();
        panel.repaint();
    }

    public static ArrayList<KhuyenMaiModel> getDSKhuyenMai() {
        ArrayList<KhuyenMaiModel> DSKM = new ArrayList<>();

        try {
            String sql = "SELECT * FROM KHUYENMAI ORDER BY MaKM DESC";
            Connection con = null;
            con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            DSKM.clear();
            while (rs.next()) {
                int MaKM = rs.getInt(1);
                String TenKM = rs.getString(2);
                String MoTaKM = rs.getString(3);
                LocalDate NgayBatDau = rs.getDate(4).toLocalDate();
                LocalDate NgayKetThuc = rs.getDate(5).toLocalDate();
                Double PhanTramKM = rs.getDouble(6);
                KhuyenMaiModel sp = new KhuyenMaiModel(MaKM, TenKM, MoTaKM, NgayBatDau, NgayKetThuc, PhanTramKM);
                DSKM.add(sp);
            }
            con.close();
        } catch (java.sql.SQLException ex) {
            ex.printStackTrace();
        }
        return DSKM;
    }

    public void addNewKhuyenMai(KhuyenMaiModel newKM) {
        // Đẩy các dữ liệu cũ xuống dưới
        for (int i = panelList.size() - 1; i > 0; i--) {
            updatePanel(panelList.get(i), getKhuyenMaiModelFromPanel(panelList.get(i - 1)));
        }
        // Cập nhật dữ liệu mới vào panel đầu tiên
        updatePanel(panelList.get(0), newKM);
    }

    private KhuyenMaiModel getKhuyenMaiModelFromPanel(JPanel panel) {
        // Phương thức này giả định bạn có cách để lấy dữ liệu từ các panel
        // Trong thực tế, bạn cần đảm bảo các panel chứa đối tượng KhuyenMaiModel hoặc có cách lấy lại thông tin từ các JLabel.
        return new KhuyenMaiModel(0, "", "", LocalDate.now(), LocalDate.now(), 0.0);
    }

    public void setTextPane() {
        String htmlContent = "<html>"
                + "<body style='font-family:Arial, sans-serif;'>"
                + "<h1 style='color: #181848; text-align:center; font-size:15px;'>Chính Sách Khách Sạn</h1>"
                + "<p style='text-align:center; font-style:italic; font-size:10px;'>Chào mừng Quý khách đến với khách sạn của chúng tôi! Để đảm bảo rằng Quý khách có được trải nghiệm tốt nhất khi lưu trú, xin vui lòng tham khảo chính sách đặt phòng của chúng tôi sau đây:</p>"
                + "<h2 style='font-size:10px; text-decoration:underline;'>Quy định đặt phòng:</h2>"
                + "<ul style='font-size:10px;'>"
                + "<li>Sau khi yêu cầu đặt phòng được gửi đi, sẽ có nhân viên liên hệ để hoàn tất thủ tục đặt phòng.</li>"
                + "<li>Yêu cầu đặt phòng chỉ được xác nhận sau khi quý khách đã hoàn tất thanh toán tiền cọc thông qua xác minh của nhân viên.</li>"
                + "<li>Quý khách có thể kiểm tra tình trạng đặt phòng thông qua tra cứu lịch sử đặt phòng.</li>"
                + "<li>Các chi phí phát sinh trong quá trình lưu trú tại khách sạn phải được thanh toán lúc quý khách trả phòng.</li>"
                + "</ul>"
                + "<h2 style='font-size:10px; text-decoration:underline;'>Quy định về nhận trả phòng:</h2>"
                + "<ul style='font-size:10px;'>"
                + "<li>Thời gian nhận phòng từ 14h00. Khách sạn có thể hỗ trợ quý khách nhận phòng sớm hơn nếu có phòng trống.</li>"
                + "<li>Thời gian trả phòng là 12h00. Khách sạn sẽ có phụ phí nếu quý khách trả phòng trễ theo giá giờ thuê phòng quy định.</li>"
                + "</ul>"
                + "<h2 style='font-size:10px; text-decoration:underline;'>Quy định về hủy phòng:</h2>"
                + "<ul style='font-size:10px;'>"
                + "<li>Không chấp nhận hoàn trả tiền cọc tại bất kì thời điểm nào sau khi yêu cầu đặt phòng đã được xác nhận.</li>"
                + "<li>Trường hợp quý khách không đến nhận phòng mà không thông báo, quý khách sẽ mất cọc.</li>"
                + "</ul>"
                + "<h2 style='font-size:10px; text-decoration:underline;'>Quy định lưu trú tại khách sạn:</h2>"
                + "<ul style='font-size:10x;'>"
                + "<li>Không được mang thú nuôi vào khách sạn.</li>"
                + "<li>Không được nấu ăn trong phòng.</li>"
                + "<li>Tuyệt đối nghiêm cấm các hành vi vi phạm pháp luật trong khách sạn.</li>"
                + "<li>Vui lòng trả lại chìa khóa cho lễ tân khi làm thủ tục trả phòng.</li>"
                + "</ul>"
                + "<p style='text-align:center; font-style:italic; font-size:10px;'>Chính sách này nhằm đảm bảo quyền lợi và trách nhiệm của cả quý khách và khách sạn, với mong muốn mang lại trải nghiệm lưu trú thoải mái và an toàn cho quý khách. Mọi yêu cầu của quý khách đều được chúng tôi trân trọng và tiếp nhận. Chúng tôi cam kết sẽ nỗ lực hoàn tiện không ngừng để mang đến trải nghiệm tốt nhất cho quý khách.</p>"
                + "<br><br><br><br><br>" // Add 5 line breaks.
                + "<p style='text-align:center; font-size:9px;'>"
                + "<strong>Khách sạn Bright Star</strong><br>" // Make the hotel name bold.
                + "Điện thoại: (028) 372 51993<br>"
                + "Địa chỉ: 789 Khu phố 6, P.Linh Trung, Tp.Thủ Đức, Tp.Hồ Chí Minh<br>"
                + "Facebook: Khách sạn Bright Star"
                + "</p>"
                + "</body>"
                + "</html>"
                + "<br>"
                + "<br>";

        ChinhSachTextPane.setText(htmlContent);  // Đặt nội dung HTML vào JTextPane
    }
    
    private void TraCuuLichSu() throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        DefaultTableModel tblLichSu_Model = (DefaultTableModel) this.LichSuJTable.getModel();
        String input = this.NhapCCCDTextField.getText();
        tblLichSu_Model.setRowCount(0);
        ArrayList<LichSuModel> DS_LichSu = new ArrayList<LichSuModel>();
        DS_LichSu = LichSuDAO.timKH(input);
        if (!DS_LichSu.isEmpty()) {
            jScrollPane3.setVisible(true);
            LichSuJTable.setVisible(true);
            LichSuCuaJLabel.setText("Lịch sử của khách hàng: ");
            int sodiem = LichSuDAO.getSoHopDong(input);
            SoDiemJLabel.setText("Số điểm tích lũy: ");
            SoDiemCuaKhachJLabel.setText(String.valueOf(sodiem));
            KhachHangJLabel.setText(DS_LichSu.get(0).getTenKH());
            for (LichSuModel temp : DS_LichSu) {
                tblLichSu_Model.addRow(new Object[]{
                    temp.gettGlaphopdong().format(formatter),
                    temp.gettGNhanPhong().format(formatter),
                    temp.gettGTraPhong().format(formatter),
                    temp.getTinhtrangHD(),});
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Thông tin không tồn tại. Vui lòng thử lại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void LichSuJLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LichSuJLabelMouseClicked
        TrangChuPanel.setVisible(false);
        LichSuPanel.setVisible(true);
    }//GEN-LAST:event_LichSuJLabelMouseClicked

    private void ChinhSachJLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChinhSachJLabelMouseClicked
        TrangChuPanel.setVisible(false);
        ChinhSachPanel.setVisible(true);
        KhuyenMaiPanel.setVisible(false);
    }//GEN-LAST:event_ChinhSachJLabelMouseClicked

    private void LichSuJLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LichSuJLabel1MouseClicked
        ChinhSachPanel.setVisible(false);
        LichSuPanel.setVisible(true);
    }//GEN-LAST:event_LichSuJLabel1MouseClicked

    private void ChinhSachJLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChinhSachJLabel1MouseClicked

    }//GEN-LAST:event_ChinhSachJLabel1MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        TrangChuPanel.setVisible(true);
        ChinhSachPanel.setVisible(false);
        KhuyenMaiPanel.setVisible(false);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void ChinhSachJLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChinhSachJLabel3MouseClicked
        TrangChuPanel.setVisible(false);
        ChinhSachPanel.setVisible(true);
        KhuyenMaiPanel.setVisible(false);
    }//GEN-LAST:event_ChinhSachJLabel3MouseClicked

    private void LichSuJLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LichSuJLabel3MouseClicked
        KhuyenMaiPanel.setVisible(false);
        LichSuPanel.setVisible(true);
    }//GEN-LAST:event_LichSuJLabel3MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        TrangChuPanel.setVisible(true);
        ChinhSachPanel.setVisible(false);
        KhuyenMaiPanel.setVisible(false);
    }//GEN-LAST:event_jLabel17MouseClicked

    private void KhuyenMaiJLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KhuyenMaiJLabelMouseClicked
        TrangChuPanel.setVisible(false);
        ChinhSachPanel.setVisible(false);
        KhuyenMaiPanel.setVisible(true);
    }//GEN-LAST:event_KhuyenMaiJLabelMouseClicked

    private void KhuyenMaiJLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KhuyenMaiJLabel1MouseClicked
        TrangChuPanel.setVisible(false);
        ChinhSachPanel.setVisible(false);
        KhuyenMaiPanel.setVisible(true);
    }//GEN-LAST:event_KhuyenMaiJLabel1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        mota.tenP.setText("ĐƠN - THƯỜNG");
//        ImageIcon icon = new ImageIcon("/Image/r1.jpg");
//        mota.anhP.setIcon(icon);
        mota.mtP.setText("Mô tả: Được sử dụng phòng gym và nhiều tiện ích khác.");
        mota.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        mota.tenP.setText("ĐƠN - VIP");
//        ImageIcon icon = new ImageIcon("Image/r2.jpg");
//        mota.anhP.setIcon(icon);
        mota.mtP.setText("Mô tả: Được sử dụng phòng gym, Billiards và bể bơi vô cực.");
        mota.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        mota.tenP.setText("ĐÔI - THƯỜNG");
//        ImageIcon icon = new ImageIcon("Image/r3.jpg");
//        mota.anhP.setIcon(icon);
        mota.mtP.setText("Mô tả: Được sử dụng phòng gym và có dịch vụ cho cặp đôi.");
        mota.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        mota.tenP.setText("ĐÔI - VIP");
//        ImageIcon icon = new ImageIcon("Image/r4.jpg");
//        mota.anhP.setIcon(icon);
        mota.mtP.setText("Mô tả: Được sử dụng tất cả dịch vụ tiện ích của Bright Star.");
        mota.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void DatPhongJLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatPhongJLabelMouseClicked
        this.dispose();
        new DatPhongOnlFrame().setVisible(true);
    }//GEN-LAST:event_DatPhongJLabelMouseClicked

    private void DatPhongJLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatPhongJLabel1MouseClicked
        this.dispose();
        new DatPhongOnlFrame().setVisible(true);
    }//GEN-LAST:event_DatPhongJLabel1MouseClicked

    private void DatPhongJLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatPhongJLabel3MouseClicked
        this.dispose();
        new DatPhongOnlFrame().setVisible(true);
    }//GEN-LAST:event_DatPhongJLabel3MouseClicked

    private void NhapCCCDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NhapCCCDTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NhapCCCDTextFieldActionPerformed

    private void TraCuuButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TraCuuButtonMouseClicked

    }//GEN-LAST:event_TraCuuButtonMouseClicked

    private void TraCuuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TraCuuButtonActionPerformed
        try {
            TraCuuLichSu();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_TraCuuButtonActionPerformed

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        TrangChuPanel.setVisible(true);
        LichSuPanel.setVisible(false);
    }//GEN-LAST:event_jLabel23MouseClicked

    private void DatPhongJLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatPhongJLabel4MouseClicked
        this.dispose();
        new DatPhongOnlFrame().setVisible(true);
    }//GEN-LAST:event_DatPhongJLabel4MouseClicked

    private void LichSuJLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LichSuJLabel4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LichSuJLabel4MouseClicked

    private void ChinhSachJLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChinhSachJLabel4MouseClicked
        ChinhSachPanel.setVisible(true);
        LichSuPanel.setVisible(false);
    }//GEN-LAST:event_ChinhSachJLabel4MouseClicked

    private void KhuyenMaiJLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KhuyenMaiJLabel4MouseClicked
        KhuyenMaiPanel.setVisible(true);
        LichSuPanel.setVisible(false);
    }//GEN-LAST:event_KhuyenMaiJLabel4MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.dispose();
        new DangNhapFrame().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ChinhSachJLabel;
    private javax.swing.JLabel ChinhSachJLabel1;
    private javax.swing.JLabel ChinhSachJLabel3;
    private javax.swing.JLabel ChinhSachJLabel4;
    private javax.swing.JPanel ChinhSachPanel;
    private javax.swing.JTextPane ChinhSachTextPane;
    private javax.swing.JLabel DatPhongJLabel;
    private javax.swing.JLabel DatPhongJLabel1;
    private javax.swing.JLabel DatPhongJLabel3;
    private javax.swing.JLabel DatPhongJLabel4;
    private javax.swing.JPanel KM10jPanel;
    private javax.swing.JPanel KM1jPanel;
    private javax.swing.JPanel KM2jPanel;
    private javax.swing.JPanel KM3jPanel;
    private javax.swing.JPanel KM4jPanel;
    private javax.swing.JPanel KM5jPanel;
    private javax.swing.JPanel KM6jPanel;
    private javax.swing.JPanel KM7jPanel;
    private javax.swing.JPanel KM8jPanel;
    private javax.swing.JPanel KM9jPanel;
    private javax.swing.JLabel KhachHangJLabel;
    private javax.swing.JLabel KhuyenMaiJLabel;
    private javax.swing.JLabel KhuyenMaiJLabel1;
    private javax.swing.JLabel KhuyenMaiJLabel3;
    private javax.swing.JLabel KhuyenMaiJLabel4;
    private javax.swing.JPanel KhuyenMaiPanel;
    private javax.swing.JLabel LichSuCuaJLabel;
    private javax.swing.JLabel LichSuJLabel;
    private javax.swing.JLabel LichSuJLabel1;
    private javax.swing.JLabel LichSuJLabel3;
    private javax.swing.JLabel LichSuJLabel4;
    private javax.swing.JTable LichSuJTable;
    private javax.swing.JPanel LichSuPanel;
    private javax.swing.JTextField NhapCCCDTextField;
    private javax.swing.JLabel SoDiemCuaKhachJLabel;
    private javax.swing.JLabel SoDiemJLabel;
    private javax.swing.JButton TraCuuButton;
    private javax.swing.JPanel TrangChuPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private Image.SVGImage svgPC;
    private Image.SVGImage svgPolicy;
    private javax.swing.JLabel tenP1;
    // End of variables declaration//GEN-END:variables
}
